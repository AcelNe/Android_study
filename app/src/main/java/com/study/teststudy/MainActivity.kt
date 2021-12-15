package com.study.teststudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.study.teststudy.databinding.ActivityMainBinding
import com.study.teststudy.databinding.ActivityPageBinding

class MainActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnRemember.setOnClickListener {
            CameraActivity()
        }
    }
}