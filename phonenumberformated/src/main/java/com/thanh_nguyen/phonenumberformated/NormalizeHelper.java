package com.thanh_nguyen.phonenumberformated;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NormalizeHelper {
    public static String standardizedPhone(String phone) {
        String result = "";
        for (Character c : phone.toCharArray())
        {
            if (c >= 48 && c <= 57)
                result += c;
        }

        return result;
    }

    public static String formattedPhone(String phoneNumber) {
        if (phoneNumber.length() < 10)
        {
            String reg = "^(\\d{0,3})(\\d{0,3})(\\d{0,3})$";
            return formatPhoneNumberWithRegex(reg, phoneNumber);
        }
        else if (phoneNumber.length() == 11)
        {
            String reg0xx = "^(\\d{0,4})(\\d{0,3})(\\d{0,4})$";
            return formatPhoneNumberWithRegex(reg0xx, phoneNumber);
        }
        else if((phoneNumber.length() >= 10)){
            String reg0xx = "^(\\d{0,4})(\\d{0,3})(\\d{0,3})(\\d{0,3})(\\d{0,3})(\\d{0,3})(\\d{0,3})(\\d{0,3})(\\d{0,3})$";
            return formatPhoneNumberWithRegex(reg0xx, phoneNumber);
        }else{
            return phoneNumber;
        }
    }

    public static String formattedPhone(String phoneNumber, Character splitChar) {
        if (phoneNumber.length() < 10)
        {
            String reg = "^(\\d{0,3})(\\d{0,3})(\\d{0,3})$";
            return formatPhoneNumberWithRegex(reg, phoneNumber, splitChar);
        }
        else if (phoneNumber.length() == 11)
        {
            String reg0xx = "^(\\d{0,4})(\\d{0,3})(\\d{0,4})$";
            return formatPhoneNumberWithRegex(reg0xx, phoneNumber, splitChar);
        }
        else if((phoneNumber.length() >= 10)){
            String reg0xx = "^(\\d{0,4})(\\d{0,3})(\\d{0,3})(\\d{0,3})(\\d{0,3})(\\d{0,3})(\\d{0,3})(\\d{0,3})(\\d{0,3})$";
            return formatPhoneNumberWithRegex(reg0xx, phoneNumber, splitChar);
        }else{
            return phoneNumber;
        }
    }

    private static String formatPhoneNumberWithRegex(String reg, String phoneNumber) {
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(phoneNumber);
        if (matcher.find())
        {
            String format = matcher.group(1);
            for (int i = 2; i <= matcher.groupCount(); i++)
            {
                if (!matcher.group(i).isEmpty())
                    format = format + "." + matcher.group(i);
            }

            return format;
        }

        return phoneNumber;
    }

    private static String formatPhoneNumberWithRegex(String reg, String phoneNumber, Character splitChar) {
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(phoneNumber);
        if (matcher.find())
        {
            String format = matcher.group(1);
            for (int i = 2; i <= matcher.groupCount(); i++)
            {
                if (!matcher.group(i).isEmpty())
                    format = format + "." + matcher.group(i);
            }

            return format;
        }

        return phoneNumber;
    }
}
