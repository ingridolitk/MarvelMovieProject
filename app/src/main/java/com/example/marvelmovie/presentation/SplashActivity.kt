package com.example.marvelmovie.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.marvelmovie.MainActivity
import com.example.marvelmovie.R
import com.example.marvelmovie.databinding.ActivitySplashBinding

private lateinit var binding: ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupSplash()
        supportActionBar?.hide()
    }

    private fun setupSplash() = with(binding) {
        tvSplash.alpha = 0f
        tvSplash.animate().setDuration(1000).alpha(1f).withEndAction {
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }
}