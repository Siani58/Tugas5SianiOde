package com.example.projekdemouassiani

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.util.Patterns
import android.widget.Toast
import com.example.projekdemouassiani.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var binding:ActivityLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.btnLogin.setOnClickListener {
            val email : String = binding.etEmaillog.text.toString().trim()
            val password : String = binding.etPasswordlog.text.toString().trim()

            if (email.isEmpty()){
                binding.etEmaillog.error = "Input Email"
                binding.etEmaillog.requestFocus()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.etEmaillog.error = "Invalid Email"
                binding.etEmaillog.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty() || password.length < 6){
                binding.etPasswordlog.error = "password more than 6 characters"
                binding.etPasswordlog.requestFocus()
                return@setOnClickListener
            }

            loginUser(email, password)
        }

        binding.tvregisterlog.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        binding.tvlupakatasandilog.setOnClickListener {
            Intent(this, LupaSandiActivity::class.java).also {
                startActivity(it)
            }
        }
    }

    private fun loginUser(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                Intent(this, MainActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
            else {
                Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        if (firebaseAuth.currentUser != null) {
            Intent(this, MainActivity::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(it)
            }
        }
    }
}