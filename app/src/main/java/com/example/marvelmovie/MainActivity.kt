package com.example.marvelmovie

import android.os.Bundle
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
        val tabLayout = binding.tabLayoutopc

        viewPager.adapter = FragmentAdapter(this)
        viewPager.isUserInputEnabled = false

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = getTabTitle(position)
        }.attach()
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
            }
        })
    }

    private fun getTabTitle(position: Int): String {
        return when (position) {
            0 -> "Todos os filmes"
            1 -> "Favoritos"
            else -> ""
        }
    }
}