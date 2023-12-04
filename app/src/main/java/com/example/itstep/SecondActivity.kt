package com.example.itstep

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.itstep.databinding.ActivityMainBinding
import com.example.itstep.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            imageView.setImageResource(intent.getIntExtra("icon", 0))
            textView.text = intent.getStringExtra("name")
        }

    }
}