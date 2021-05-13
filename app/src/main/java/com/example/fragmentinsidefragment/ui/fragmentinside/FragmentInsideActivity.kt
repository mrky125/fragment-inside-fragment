package com.example.fragmentinsidefragment.ui.fragmentinside

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fragmentinsidefragment.databinding.ActivityInsideFragmentBinding

class FragmentInsideActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityInsideFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().apply {
            add(binding.flContainer.id, ParentFragment())
            commit()
        }
    }
}