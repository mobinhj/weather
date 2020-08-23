package com.example.weather.Fragments.PagerAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.weather.Fragments.CityWeatherFragment
import com.example.weather.Fragments.HistoryFragment
import com.example.weather.Fragments.LocationWeatherFragment

@Suppress("DEPRECATION", "UNREACHABLE_CODE")
internal class PagerViewAdapter (fm:FragmentManager?):FragmentPagerAdapter(fm!!){
    lateinit var historyFragment: HistoryFragment
    lateinit var cityFragment: CityWeatherFragment
    lateinit var locationFragment: LocationWeatherFragment

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> CityWeatherFragment()
            1 -> LocationWeatherFragment()
            else -> { return HistoryFragment()}
//            else -> CityWeatherFragment()
        }

        if (position == 0) {
            if (!this::cityFragment.isInitialized) {
                cityFragment = CityWeatherFragment()
            }

            return cityFragment;
        }

        if (position == 1) {
            if (!this::locationFragment.isInitialized) {
                locationFragment = LocationWeatherFragment()
            }

            return locationFragment;
        }

        if (!this::historyFragment.isInitialized) {
            historyFragment = HistoryFragment()
        }

        return historyFragment;
    }

    override fun getCount(): Int {
        return 3
    }
    override fun getPageTitle(position: Int): CharSequence? {
        var title :String? = null
        when (position) {
            0 -> {
                title = "City Weather"
            }
            1 -> {
                title = "Location Weather"
            }
            2 -> {
                title = "History"
            }
        }
        return title
    }
}