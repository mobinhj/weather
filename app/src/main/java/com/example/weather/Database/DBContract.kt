package com.example.weather.Database

import android.provider.BaseColumns

class DBContract {
    class CityEntry :BaseColumns {
        companion object {
            const val TABLE_CITY = "cities"
            const val COLUMN_CITY = "city"
            const val COLUMN_TEMP = "temp"
            const val COLUMN_DATE = "date"
            const val COLUMN_ICON = "icon"
        }
    }
}