package com.example.myapp05.Fragments.PagerAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.myapp05.Fragments.CityWeatherFragment
import com.example.myapp05.Fragments.LocationWeatherFragment

internal class PagerViewAdapter (fm:FragmentManager?):FragmentPagerAdapter(fm!!){
    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> {
            CityWeatherFragment()
        }
            1 -> {
            LocationWeatherFragment()
        }
            else -> CityWeatherFragment()
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        var title :String? = null
        if (position==0){
            title = "City Weather"
        } else if (position==1){
            title = "Location Weather"
        }
        return title
    }
}