package com.thanh_nguyen.phonenumberformated

import java.util.regex.Pattern

object NormalizeHelper {
    @JvmStatic
    fun standardizedPhone(phone: String): String {
        var result = ""
        for (c in phone.toCharArray()) {
            if (c.toInt() in 48..57) result += c
        }
        return result
    }

    fun formattedPhone(phoneNumber: String): String {
        return when {
            phoneNumber.length < 10 -> {
                val reg = "^(\\d{0,3})(\\d{0,3})(\\d{0,3})$"
                formatPhoneNumberWithRegex(reg, phoneNumber)
            }
            phoneNumber.length == 11 -> {
                val reg0xx = "^(\\d{0,4})(\\d{0,3})(\\d{0,4})$"
                formatPhoneNumberWithRegex(reg0xx, phoneNumber)
            }
            phoneNumber.length >= 10 -> {
                val reg0xx =
                    "^(\\d{0,4})(\\d{0,3})(\\d{0,3})(\\d{0,3})(\\d{0,3})(\\d{0,3})(\\d{0,3})(\\d{0,3})(\\d{0,3})$"
                formatPhoneNumberWithRegex(reg0xx, phoneNumber)
            }
            else -> {
                phoneNumber
            }
        }
    }

    @JvmStatic
    fun formattedPhone(phoneNumber: String, splitChar: Char): String {
        return when {
            phoneNumber.length < 10 -> {
                val reg = "^(\\d{0,3})(\\d{0,3})(\\d{0,3})$"
                formatPhoneNumberWithRegex(reg, phoneNumber, splitChar)
            }
            phoneNumber.length == 11 -> {
                val reg0xx = "^(\\d{0,4})(\\d{0,3})(\\d{0,4})$"
                formatPhoneNumberWithRegex(reg0xx, phoneNumber, splitChar)
            }
            phoneNumber.length >= 10 -> {
                val reg0xx =
                    "^(\\d{0,4})(\\d{0,3})(\\d{0,3})(\\d{0,3})(\\d{0,3})(\\d{0,3})(\\d{0,3})(\\d{0,3})(\\d{0,3})$"
                formatPhoneNumberWithRegex(reg0xx, phoneNumber, splitChar)
            }
            else -> {
                phoneNumber
            }
        }
    }

    private fun formatPhoneNumberWithRegex(reg: String, phoneNumber: String): String {
        val pattern = Pattern.compile(reg)
        val matcher = pattern.matcher(phoneNumber)
        if (matcher.find()) {
            var format = matcher.group(1)
            for (i in 2..matcher.groupCount()) {
                if (matcher.group(i).isNotEmpty()) format = format + "." + matcher.group(i)
            }
            return format
        }
        return phoneNumber
    }

    private fun formatPhoneNumberWithRegex(
        reg: String,
        phoneNumber: String,
        splitChar: Char
    ): String {
        val pattern = Pattern.compile(reg)
        val matcher = pattern.matcher(phoneNumber)
        if (matcher.find()) {
            var format = matcher.group(1)
            for (i in 2..matcher.groupCount()) {
                if (!matcher.group(i).isEmpty()) format = format + splitChar + matcher.group(i)
            }
            return format
        }
        return phoneNumber
    }
}