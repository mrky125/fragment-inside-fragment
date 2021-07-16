package com.example.fragmentinsidefragment.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fragmentinsidefragment.databinding.ActivityParentRecyclerBinding
import com.example.fragmentinsidefragment.ui.concat.ConcatFragment

class ParentRecyclerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityParentRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(binding.flContainer.id, ConcatFragment())
            .commit()
    }
}