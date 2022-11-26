package com.example.alarmintent

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createAlarm("This is an alarm!",20,50)
        sendMessage("Hala Feek")
        val mapIntent: Intent = Uri.parse(
            "geo:0,0?q=1600+Amphitheatre+Parkway,+Mountain+View,+California").let { location ->
        // Or map point based on latitude/longitude
        // val location:
         Uri.parse("geo:37.422219,-122.08364?z=14") // z param is zoom level
         Intent(Intent.ACTION_VIEW, location)
         }
        startActivity(mapIntent);
    }

    fun createAlarm(Message : String?,Hour : Int?, Minute : Int?){
        val alarmIntent = Intent(AlarmClock.ACTION_SET_ALARM).apply {
            putExtra(AlarmClock.EXTRA_MESSAGE,Message)
            putExtra(AlarmClock.EXTRA_HOUR,Hour)
            putExtra(AlarmClock.EXTRA_MINUTES,Minute)

        }

        if(alarmIntent.resolveActivity(packageManager) != null)
            startActivity(alarmIntent)

    }
    fun sendMessage(Message : String?)
    {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT,Message)
            type = "text/plain"
        }
        try{
            startActivity(sendIntent)
        }
        catch(e : ActivityNotFoundException)
        {
            Toast.makeText(applicationContext,"Activity not found",Toast.LENGTH_LONG)

        }
    }
}