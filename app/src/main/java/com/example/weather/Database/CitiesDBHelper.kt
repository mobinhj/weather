package com.example.weather.Database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.weather.Fragments.CityWeatherFragment

class CitiesDBHelper(context: Context) :SQLiteOpenHelper(context,DATABASE_NAME ,null ,DATABASE_VERSION){

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_ENTRIES)
    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }
    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }
    @Throws(SQLiteConstraintException::class)
    fun insertCity(city:CityModel):Boolean{
    val db = writableDatabase
    val values = ContentValues()
    values.put(DBContract.CityEntry.COLUMN_CITY, city.city)
    values.put(DBContract.CityEntry.COLUMN_DATE, city.date)
    values.put(DBContract.CityEntry.COLUMN_TEMP, city.temp)
    values.put(DBContract.CityEntry.COLUMN_ICON, city.icon)
    val newRowId = db.insert(DBContract.CityEntry.TABLE_CITY, null, values)
    return true
}
    @SuppressLint("Recycle")
    fun readAllCities():ArrayList<CityModel>{
        val cities = ArrayList<CityModel>()
        val db = writableDatabase
        var cursor :Cursor? = null
        try {
            cursor = db.rawQuery("select * from " +
                    DBContract.CityEntry.TABLE_CITY, null)
        }catch (e: SQLiteException) {
            db.execSQL(SQL_CREATE_ENTRIES)
            return ArrayList()
        }
        var city : String
        var date : String
        var temp : String
        var icon : String
        if (cursor!!.moveToFirst()){
            while (!cursor.isAfterLast){
                city = cursor.getString(cursor.getColumnIndex(DBContract.CityEntry.COLUMN_CITY))
                date = cursor.getString(cursor.getColumnIndex(DBContract.CityEntry.COLUMN_DATE))
                temp =  cursor.getString(cursor.getColumnIndex(DBContract.CityEntry.COLUMN_TEMP))
                icon =  cursor.getString(cursor.getColumnIndex(DBContract.CityEntry.COLUMN_ICON))

                cities.add(CityModel(city,date, temp, icon))
                cursor.moveToNext()
            }
        }
        return cities
    }
    companion object {
        // If you change the database schema, you must increment the database version.
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "FeedReader.db"
        private const val SQL_CREATE_ENTRIES =
            "CREATE TABLE " + DBContract.CityEntry.TABLE_CITY + " (" +
                    DBContract.CityEntry.COLUMN_DATE + " TEXT DATE," +
                    DBContract.CityEntry.COLUMN_CITY + " TEXT," +
                    DBContract.CityEntry.COLUMN_TEMP + " TEXT,"+
                    DBContract.CityEntry.COLUMN_ICON + " TEXT)"
        private const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " +
                DBContract.CityEntry.TABLE_CITY
    }



}