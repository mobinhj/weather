package com.example.myapp05

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.net.URL
import java.util.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_city.*

class MainActivity : AppCompatActivity() {


    val appCode = "xdjT6t-y6emD9tugkEW_7w"
    val api = "NGkPDa4koRes4s9mNW0s"
    var city: String = "sari"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tabLayout = tabBar
        val tabItemCity = tabCity
        val tabItemMap = tabMap
        val viewPager = viewPager
//        update_at.text = Date().toString()
        val pagerAdapter = pagerAdapter(supportFragmentManager, tabLayout.tabCount)
        viewPager.adapter = pagerAdapter
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    viewPager.currentItem = tab.position
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }
}






