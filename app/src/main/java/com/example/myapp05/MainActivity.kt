package com.example.myapp05

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.example.myapp05.Fragments.PagerAdapter.PagerViewAdapter
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity() {
   private lateinit var mViewPager:ViewPager
    private lateinit var mPagerAdapter:PagerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mPagerAdapter = PagerViewAdapter(supportFragmentManager)
        viewPager.adapter=mPagerAdapter
        tabBar.setupWithViewPager(viewPager)



    }

}






