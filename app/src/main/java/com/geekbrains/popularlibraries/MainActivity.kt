package com.geekbrains.popularlibraries

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.geekbrains.popularlibrares.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MainView {
    private lateinit var binding: ActivityMainBinding
    private val presenter = MainPresenter(view = this,model = CountersModel)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val listenerOne = View.OnClickListener {
            presenter.counterOneButtonClick()
        }

        val listenerTwo = View.OnClickListener {
            presenter.counterTwoButtonClick()
        }

        val listenerThree = View.OnClickListener {
            presenter.counterThreeButtonClick()
        }

        binding.btnCounter1.setOnClickListener(listenerOne)
        binding.btnCounter2.setOnClickListener(listenerTwo)
        binding.btnCounter3.setOnClickListener(listenerThree)
    }

    override fun setOneButtonText(text: String) = with(binding) {
       btnCounter1.text = text
    }

    override fun setTwoButtonText(text: String) = with(binding) {
        btnCounter2.text = text
    }

    override fun setThreeButtonText(text: String) = with(binding) {
        btnCounter3.text = text
    }
}