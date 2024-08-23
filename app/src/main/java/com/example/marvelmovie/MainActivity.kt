package com.example.marvelmovie

import android.os.Bundle
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.viewpager2.widget.ViewPager2
import com.example.marvelmovie.databinding.ActivityMainBinding
import com.example.marvelmovie.presentation.adapter.FragmentAdapter
import com.example.marvelmovie.presentation.descripton.DescriptionFragment
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setupViewPager()
    }

    private fun setupViewPager() {
        val viewPager = binding.viewPager

        viewPager.adapter = FragmentAdapter(this)
        viewPager.isUserInputEnabled = false

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
            }
        })

    }
}