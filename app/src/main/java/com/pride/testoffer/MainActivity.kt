package com.pride.testoffer

import android.icu.number.NumberFormatter.with
import android.icu.number.NumberRangeFormatter.with
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.pride.testoffer.data.data.Model
import com.pride.testoffer.databinding.ActivityMainBinding
import com.pride.testofferwall.data.AllIds
import com.pride.testofferwall.data.Data
import com.pride.testofferwall.data.TypeWork
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val viewModel by lazy { ViewModelProvider(this).get(Model::class.java) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getIds1()
        viewModel.myIdList.observe(this, object : Observer<AllIds> {
            override fun onChanged(t: AllIds?) {
                if (t != null) {
                    calc(t)
                }
            }

        })
    }

    fun calc(list: AllIds) {
        var listId: ArrayList<String> = arrayListOf("sdfg")

        for (item in list.data) {
            listId.add(item.id)
        }
        listId.removeAt(0)

        var i = 0
        binding.button.setOnClickListener {
            if (i >= listId.size) i = 0
            viewModel.getType1("api/v1/object/${listId.get(i)}")
            viewModel.myTypeList.observe(this, object : Observer<TypeWork> {
                override fun onChanged(t: TypeWork?) {
                    if (t != null) {
                        elemView(t)
                    }
                }

            })
            i++

        }
    }

    fun elemView(element: TypeWork) {
        when (element.type) {
            "text" -> {
                binding.webView.visibility = View.GONE
                binding.imageView.visibility = View.GONE

                binding.textView.visibility = View.VISIBLE
                binding.textView.text = element.message
            }
            "webview" -> {
                binding.textView.visibility = View.GONE
                binding.imageView.visibility = View.GONE

                binding.webView.visibility = View.VISIBLE
                binding.webView.loadUrl(element.url)
            }
            "image" -> {
                binding.textView.visibility = View.GONE
                binding.webView.visibility = View.GONE

                binding.imageView.visibility = View.VISIBLE
                Picasso.get().load(element.url).into(binding.imageView)
            }
            else -> {
                binding.imageView.visibility = View.GONE
                binding.textView.visibility = View.GONE
                binding.webView.visibility = View.GONE
            }

        }
    }
}