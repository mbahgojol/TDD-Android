package com.blank.chapter10

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewmodel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewmodel.getCount().observe(this, Observer {
            tvCount.text = it.toString()
        })

        btnTambah.setOnClickListener {
            viewmodel.increment()
        }

        btnKurang.setOnClickListener {
            viewmodel.decrement()
        }
    }
}