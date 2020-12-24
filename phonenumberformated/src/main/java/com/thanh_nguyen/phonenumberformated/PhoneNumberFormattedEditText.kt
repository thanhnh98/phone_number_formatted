package com.thanh_nguyen.phonenumberformated

import android.content.Context
import android.text.Editable
import android.text.InputType
import android.text.TextUtils
import android.text.TextWatcher
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import com.thanh_nguyen.phonenumberformated.NormalizeHelper.formattedPhone
import com.thanh_nguyen.phonenumberformated.NormalizeHelper.standardizedPhone
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

class PhoneNumberFormattedEditText : AppCompatEditText {
    private var listener: PhoneValidateListener? = null
    private var textWatcher: TextWatcher? = null
    private var lastSelectionCursor = 0
    private var previousCharsCount = 0
    private var currentCharsCount = 0
    var splitChar = '.'

    constructor(context: Context?) : super(context) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                previousCharsCount = getRealWordCount(s.toString())
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                var phoneNumber = text.toString().trim { it <= ' ' }
                if (listener != null) {
                    if (isTruePhoneNumber(phoneNumber)) listener!!.validPhoneNumber() else listener!!.invalidPhoneNumber()
                }
                if (!TextUtils.isEmpty(phoneNumber)) {
                    removeTextChangedListener(textWatcher)
                    phoneNumber =
                        formattedPhone(phoneNumber.replace("" + splitChar, ""), splitChar)
                    lastSelectionCursor = selectionStart
                    setText(phoneNumber)
                    currentCharsCount = getRealWordCount(phoneNumber)
                    if (currentCharsCount < previousCharsCount) { //on delete
                        if (lastSelectionCursor + 1 >= previousCharsCount) //last cursor at the end of string
                            lastSelectionCursor =
                                Int.MAX_VALUE else if (abs(currentCharsCount - previousCharsCount) >= 2 && lastSelectionCursor - 1 >= 0 && lastSelectionCursor - 1 < phoneNumber.length && phoneNumber[lastSelectionCursor - 1] == splitChar) //add one space more
                            lastSelectionCursor = max(0, lastSelectionCursor - 1)
                    }
                    if (currentCharsCount >= previousCharsCount) { //on typing
                        if (lastSelectionCursor - 1 >= previousCharsCount) //last cursor at the end of string
                            lastSelectionCursor =
                                Int.MAX_VALUE else if (abs(currentCharsCount - previousCharsCount) >= 2 && lastSelectionCursor > 0 && lastSelectionCursor < phoneNumber.length && phoneNumber[lastSelectionCursor] == splitChar) //add one space more
                            lastSelectionCursor =
                                Math.min(phoneNumber.length, lastSelectionCursor + 1)
                    }
                    setSelection(min(lastSelectionCursor, phoneNumber.length))
                    addTextChangedListener(textWatcher)
                }
            }

            override fun afterTextChanged(s: Editable) {}
        }
        addTextChangedListener(textWatcher)
        inputType = InputType.TYPE_CLASS_NUMBER
    }

    private fun isTruePhoneNumber(phoneNumber: String): Boolean {
        if (TextUtils.isEmpty(phoneNumber)) return false
        val phone = standardizedPhone(phoneNumber)
        var except = 0
        for (element in phone) {
            if (element == '0')
                except++
            else
                break
        }
        val len = phone.length - except
        return len > 8
    }

    fun setPhoneValidateListener(listener: PhoneValidateListener?) {
        this.listener = listener
    }

    interface PhoneValidateListener {
        fun invalidPhoneNumber()
        fun validPhoneNumber()
    }

    fun getRealWordCount(s: String): Int {
        return if (TextUtils.isEmpty(s)) 0 else s.replace("" + splitChar, "").length
    }
}