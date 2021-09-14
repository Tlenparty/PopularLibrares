package com.geekbrains.popularlibraries

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import com.geekbrains.popularlibrares.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val counters = mutableListOf(0, 0, 0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCounter1.setOnClickListener {
            binding.btnCounter1.text = (++counters[0]).toString()
        }

        binding.btnCounter2.setOnClickListener {
            binding.btnCounter2.text = (++counters[1]).toString()
        }

        binding.btnCounter3.setOnClickListener {
            binding.btnCounter3.text = (++counters[2]).toString()
        }
        initViews()
    }

    private fun initViews() = with(binding){
        btnCounter1.text=counters[0].toString()
        btnCounter2.text=counters[1].toString()
        btnCounter3.text=counters[2].toString()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putIntArray("counters", counters.toIntArray())
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putIntArray("counters", counters.toIntArray())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val countersArray = savedInstanceState.getIntArray("counters")
        countersArray?.toList()?.let {
            counters.clear()
            counters.addAll(it)
        }
        initViews()
    }
}