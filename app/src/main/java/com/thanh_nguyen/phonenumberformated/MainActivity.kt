package com.thanh_nguyen.phonenumberformated

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), PhoneNumberFormattedEditText.PhoneValidateListener {

    private lateinit var edtPhone: PhoneNumberFormattedEditText
    private lateinit var tvStatus: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        edtPhone = findViewById(R.id.edt_phone)
        edtPhone.setSplitChar('-')
        edtPhone.setPhoneValidateListener(this)

        tvStatus = findViewById(R.id.tv_status)

    }

    override fun invalidPhoneNumber() {
        tvStatus.text = "Invalid"
        tvStatus.setTextColor(resources.getColor(R.color.red))
    }

    override fun validPhoneNumber() {
        tvStatus.text = "Accepted"
        tvStatus.setTextColor(resources.getColor(R.color.green))
    }
}