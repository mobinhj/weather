package com.example.weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.weather.Fragments.PagerAdapter.PagerViewAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var mPagerAdapter:PagerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mPagerAdapter = PagerViewAdapter(supportFragmentManager)
        viewPager.adapter=mPagerAdapter
        tabBar.setupWithViewPager(viewPager)
    }
}






