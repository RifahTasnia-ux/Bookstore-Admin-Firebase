package com.example.adminapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.adminapp.databinding.ActivityHomeBinding
import com.example.adminapp.databinding.ActivityMainBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btneducational.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            intent.putExtra("check","1")
            startActivity(intent)
        }
        binding.btnfiction.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            intent.putExtra("check","2")
            startActivity(intent)
        }
        binding.btnchildren.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            intent.putExtra("check","3")
            startActivity(intent)
        }
        binding.btnnonfiction.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            intent.putExtra("check","4")
            startActivity(intent)
        }
    }
}