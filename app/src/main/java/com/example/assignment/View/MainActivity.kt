package com.example.assignment.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.core.widget.NestedScrollView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment.R
import com.example.assignment.adapters.MyAdapter
import com.example.assignment.Model.Data
import com.example.assignment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: MyAdapter
private lateinit var binding: ActivityMainBinding
    private val dataList = mutableListOf<Data>() // This list holds filtered data
    private val originalList = mutableListOf<Data>() // This list holds the original data
    //data list


    private val visibleThreshold = 10 // Number of items from the end of the list to start loading more
    private var isLoading = false
    private var isLastPage = false
    private var currentPage = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)
        originalList.addAll(
            listOf(
                Data(R.drawable.img, "John Doe", "Android Developer","Senior Android Developer", "https://www.teachmint.com/"),
            Data(R.drawable.imgs, "Jane Smith","iOS Developer", "4+ Year iOS Development Experience", "https://www.github.com/"),
            Data(R.drawable.img, "Alice Johnson","Web Developer", "Web Development in PHP", "https://www.facebook.com/"),
            Data(R.drawable.img, "John Doe", "Android Developer","Senior Android Developer", "https://www.teachmint.com/"),
            Data(R.drawable.imgs, "Jane Smith","iOS Developer", "4+ Year iOS Development Experience", "https://www.github.com/"),
            Data(R.drawable.img, "Alice Johnson","Web Developer", "Web Development in PHP", "https://www.facebook.com/"),
            Data(R.drawable.img, "John Doe", "Android Developer","Senior Android Developer", "https://www.teachmint.com/"),
            Data(R.drawable.imgs, "Jane Smith","iOS Developer", "4+ Year iOS Development Experience", "https://www.github.com/"),
            Data(R.drawable.img, "Alice Johnson","Web Developer", "Web Development in PHP", "https://www.facebook.com/"),
            Data(R.drawable.img, "John Doe", "Android Developer","Senior Android Developer", "https://www.teachmint.com/"),
            Data(R.drawable.imgs, "Jane Smith","iOS Developer", "4+ Year iOS Development Experience", "https://www.github.com/"),
            Data(R.drawable.img, "Alice Johnson","Web Developer", "Web Development in PHP", "https://www.facebook.com/"),
            Data(R.drawable.img, "John Doe", "Android Developer","Senior Android Developer", "https://www.teachmint.com/"),
            Data(R.drawable.imgs, "Jane Smith","iOS Developer", "4+ Year iOS Development Experience", "https://www.github.com/"),
            Data(R.drawable.img, "Alice Johnson","Web Developer", "Web Development in PHP", "https://www.facebook.com/"),


            )
        )


        // Initialize dataList with provided data
        dataList.addAll(
            listOf(
                Data(R.drawable.img, "John Doe", "Android Developer", "Senior Android Developer", "https://www.teachmint.com/"),
                Data(R.drawable.imgs, "Jane Smith", "iOS Developer", "4+ Year iOS Development Experience", "https://www.github.com/"),
                Data(R.drawable.img, "Alice Johnson", "Web Developer", "Web Development in PHP", "https://www.facebook.com/"),
                Data(R.drawable.img, "John Doe", "Android Developer", "Senior Android Developer", "https://www.teachmint.com/"),
                Data(R.drawable.imgs, "Jane Smith", "iOS Developer", "4+ Year iOS Development Experience", "https://www.github.com/"),
                Data(R.drawable.img, "Alice Johnson", "Web Developer", "Web Development in PHP", "https://www.facebook.com/"),
                Data(R.drawable.img, "John Doe", "Android Developer", "Senior Android Developer", "https://www.teachmint.com/"),
                Data(R.drawable.imgs, "Jane Smith", "iOS Developer", "4+ Year iOS Development Experience", "https://www.github.com/"),
                Data(R.drawable.img, "Alice Johnson", "Web Developer", "Web Development in PHP", "https://www.facebook.com/"),
                Data(R.drawable.img, "John Doe", "Android Developer", "Senior Android Developer", "https://www.teachmint.com/")
            )
        )
        //show item list here
        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        adapter = MyAdapter(this,dataList)
        binding.recyclerview.adapter = adapter

        // Set up SearchView
        binding.searchview.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrBlank()) {
                    // If the search query is empty, show the original list
                    dataList.clear()
                    dataList.addAll(originalList)
                    adapter.notifyDataSetChanged()
                } else {
                    // Filter the original list based on the search query
                    dataList.clear()
                    originalList.filterTo(dataList) { data ->
                        data.name.contains(newText, ignoreCase = true)
                    }
                    adapter.notifyDataSetChanged()
                }
                return true
            }
        })
        binding.nestedScrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, _, scrollY, _, _ ->
            if (!isLoading && !isLastPage && v.getChildAt(0).measuredHeight <= scrollY + v.height + 200) {
                loadMoreItems(binding.progressBar)
            }
        })
    }

    private fun loadMoreItems(progressBar: ProgressBar) {
        isLoading = true
        binding.progressBar.isVisible = true // Show progress bar
        // Simulate loading more items
        val startIndex = currentPage * visibleThreshold
        val endIndex = startIndex + visibleThreshold
        if (endIndex >= dataList.size) {
            isLastPage = true
        }
        adapter.notifyDataSetChanged()
        currentPage++
        isLoading = false
        binding.progressBar.isVisible = false // Hide progress bar
    }
}