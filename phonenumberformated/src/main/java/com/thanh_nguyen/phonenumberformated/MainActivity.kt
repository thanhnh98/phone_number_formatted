package com.thanh_nguyen.phonenumberformated

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    public fun hiThere(){
        Toast.makeText(this, "hi",Toast.LENGTH_SHORT).show()
    }
}