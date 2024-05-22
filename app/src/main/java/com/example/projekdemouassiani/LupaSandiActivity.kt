package com.example.projekdemouassiani

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projekdemouassiani.databinding.ActivityLupaSandiBinding
import com.google.firebase.auth.FirebaseAuth

class LupaSandiActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLupaSandiBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_lupa_sandi)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding = ActivityLupaSandiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.btnKirim.setOnClickListener {
            val email : String = binding.etEmalls.text.toString().trim()

            if (email.isEmpty()){
                binding.etEmalls.error = "Masukkan Email"
                binding.etEmalls.requestFocus()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.etEmalls.error = "Email Tidak Valid"
                binding.etEmalls.requestFocus()
                return@setOnClickListener
            }

            FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(this, "cek email untuk reset password", Toast.LENGTH_SHORT).show()
                    Intent(this, LoginActivity::class.java).also {
                        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(it)
                    }
                }
                else {
                    Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.tvkembali.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}