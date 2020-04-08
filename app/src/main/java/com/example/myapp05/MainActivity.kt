package com.example.myapp05

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.net.URL
import java.util.*
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {


    val app_code = "xdjT6t-y6emD9tugkEW_7w"
    val api = "NGkPDa4koRes4s9mNW0s"
    var long :Double? = 0.0
    var lot :Double? = 0.0
    var city :String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        update_at.text = Date().toString()
        submitButton.setOnClickListener {
            city = cityEditText.text.toString()
            if (city != "") {
                   weatherTask().execute()
               }else {
                Toast.makeText(this,"Please enter a City",Toast.LENGTH_SHORT).show()
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
                response = URL("https://weather.cit.api.here.com/weather/1.0/report.json?product=observation&name=$city&oneobservation=true&app_id=$api&app_code=$app_code").readText(Charsets.UTF_8)
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
                val latitude = observation.getString("latitude")+" N"
                val longitude = observation.getString("longitude")+" E"
                Picasso.with(this@MainActivity).load(icone).into(imageSeason)

                latTExtView.text = latitude
                longTextView.text = longitude

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
