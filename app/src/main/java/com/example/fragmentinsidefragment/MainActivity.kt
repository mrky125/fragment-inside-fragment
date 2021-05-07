package com.example.fragmentinsidefragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fragmentinsidefragment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().apply {
            add(binding.flContainer.id, ParentFragment())
            commit()
        }
    }
}