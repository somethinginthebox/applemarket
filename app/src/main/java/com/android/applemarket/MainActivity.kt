package com.android.applemarket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.applemarket.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // 데이터 리스트
        val dataList = mutableListOf<MarketItem>()
        dataList.add(MarketItem(R.drawable.sample1,
            getString(R.string.sample1_Name),getString(R.string.sample1_data),getString(R.string.sample1_seller),
            resources.getInteger(R.integer.sample1_price),getString(R.string.sample1_address),resources.getInteger(R.integer.sample1_like),resources.getInteger(R.integer.sample1_chatting)))
        dataList.add(MarketItem(R.drawable.sample2,
            getString(R.string.sample2_Name),getString(R.string.sample2_data),getString(R.string.sample2_seller),
            resources.getInteger(R.integer.sample2_price),getString(R.string.sample2_address),resources.getInteger(R.integer.sample2_like),resources.getInteger(R.integer.sample2_chatting)))

        //
        binding.recyclerView.adapter = MyAdapter(dataList)


        val adapter = MyAdapter(dataList)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)


        //토스트
        adapter.itemClick = object : MyAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {
                val name: String = dataList[position].aName
                Toast.makeText(this@MainActivity," $name 선택!", Toast.LENGTH_SHORT).show()
            }
        }

    }
}