package com.example.myapp05

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import org.json.JSONObject
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {


    val API = "NGkPDa4koRes4s9mNW0s"
    var long :Double? = 0.0
    var lot :Double? = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        submitButton.setOnClickListener {

            long = lang.text.toString().toDouble()
            lot = lat.text.toString().toDouble()
            if (long != 0.0 && lot != 0.0) {
                   weatherTask().execute()
               }else {
                errorText.visibility = View.VISIBLE
            }
        }
    }
    inner class weatherTask() : AsyncTask<String ,Void, String>(){
        override fun onPreExecute() {
            super.onPreExecute()
            loader.visibility = View.VISIBLE
            mainContainer.visibility = View.GONE
            errorText.visibility = View.GONE
        }
        override fun doInBackground(vararg params: String?): String? {
            var response : String?
            try {
                response = URL("https://weather.cit.api.here.com/weather/1.0/report.json?product=observation&latitude=$lot&longitude=$long&oneobservation=true&app_id=$API&app_code=xdjT6t-y6emD9tugkEW_7w").readText(Charsets.UTF_8)
            }catch (e:Exception){
                response = null
            }
            return response
        }
        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            try {
                val jsonObj = JSONObject(result)
                val observations = jsonObj.getJSONObject("observations")
                val location = observations.getJSONArray("location").getJSONObject(0)
                val observation = location.getJSONArray("observation").optJSONObject(0)
                val city = observation.getString("city")
                val temper = observation.getString("temperature")+"˚C"
                val icone = observation.getString("iconLink")
                val country =observation.getString("country")
                Picasso.with(this@MainActivity).load(icone).into(imageSeason)

                update_at.text = Date().toString()
                temp.text = temper
                address.text = city + "," + country

                loader.visibility = View.GONE
                mainContainer.visibility = View.VISIBLE
            } catch (e: Exception) {
               loader.visibility = View.GONE
                errorText.visibility = View.VISIBLE
            }
        }
    }
}
