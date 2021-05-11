package com.example.fragmentinsidefragment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fragmentinsidefragment.databinding.ActivityEpoxyRecyclerViewBinding

class EpoxyRecyclerViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityEpoxyRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().apply {
            add(binding.flContainer.id, EpoxyParentFragment())
            commit()
        }
    }
}