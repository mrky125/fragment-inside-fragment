package com.example.fragmentinsidefragment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fragmentinsidefragment.databinding.ActivityMainBinding
import com.example.fragmentinsidefragment.ui.epoxy.EpoxyMultiPagingActivity
import com.example.fragmentinsidefragment.ui.epoxy.EpoxyPagingActivity
import com.example.fragmentinsidefragment.ui.epoxy.EpoxyRecyclerViewActivity
import com.example.fragmentinsidefragment.ui.fragmentinside.FragmentInsideActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupClickListener()
    }

    private fun setupClickListener() {
        binding.btnFragmentInsideActivity.setOnClickListener {
            startActivity(Intent(this, FragmentInsideActivity::class.java))
        }
        binding.btnEpoxyRecyclerView.setOnClickListener {
            startActivity(Intent(this, EpoxyRecyclerViewActivity::class.java))
        }
        binding.btnEpoxyPaging.setOnClickListener {
            startActivity(Intent(this, EpoxyPagingActivity::class.java))
        }
        binding.btnEpoxyMultiTypePaging.setOnClickListener {
            startActivity(Intent(this, EpoxyMultiPagingActivity::class.java))
        }
    }
}