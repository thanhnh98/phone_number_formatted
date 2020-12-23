package com.thanh_nguyen.phonenumberformated;

import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;

import static java.lang.Math.abs;
import static java.lang.Math.max;
import static java.lang.Math.min;

public class PhoneNumberFormattedEditText extends androidx.appcompat.widget.AppCompatEditText {

    private PhoneValidateListener listener;
    private TextWatcher textWatcher;
    private int lastSelectionCursor = 0;
    private int previousCharsCount = 0;
    private int currentCharsCount = 0;
    private Character splitCharacter = '.';
    private final String ADDITION_CHAR = "\\.";


    public PhoneNumberFormattedEditText(Context context) {
        super(context);
        init();
    }

    public PhoneNumberFormattedEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PhoneNumberFormattedEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                previousCharsCount = getRealWordCount(s.toString());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String phoneNumber = getText().toString().trim();

                if (listener != null){
                    if (isTruePhoneNumber(phoneNumber))
                        listener.validPhoneNumber();
                    else
                        listener.invalidPhoneNumber();
                }

                if (!TextUtils.isEmpty(phoneNumber)) {
                    removeTextChangedListener(textWatcher);
                    phoneNumber = NormalizeHelper.formattedPhone(phoneNumber.replaceAll(ADDITION_CHAR, ""), splitCharacter);

                    lastSelectionCursor = getSelectionStart();
                    setText(phoneNumber);
                    currentCharsCount = getRealWordCount(phoneNumber);

                    if (currentCharsCount < previousCharsCount){//on delete
                        if (lastSelectionCursor + 1 >= previousCharsCount) //last cursor at the end of string
                            lastSelectionCursor = Integer.MAX_VALUE;

                        else if (abs(currentCharsCount - previousCharsCount) >= 2
                                && lastSelectionCursor - 1 >= 0
                                && lastSelectionCursor - 1 < phoneNumber.length()
                                && phoneNumber.charAt(lastSelectionCursor - 1) == splitCharacter) //add one space more
                            lastSelectionCursor = max(0, lastSelectionCursor - 1);
                    }

                    if (currentCharsCount >= previousCharsCount){ //on typing
                        if (lastSelectionCursor - 1 >= previousCharsCount) //last cursor at the end of string
                            lastSelectionCursor = Integer.MAX_VALUE;

                        else if (abs(currentCharsCount - previousCharsCount) >= 2
                                && lastSelectionCursor > 0
                                && lastSelectionCursor < phoneNumber.length()
                                && phoneNumber.charAt(lastSelectionCursor) == splitCharacter)//add one space more
                            lastSelectionCursor = min(phoneNumber.length(), lastSelectionCursor + 1);
                    }

                    setSelection(min(lastSelectionCursor, phoneNumber.length()));

                    addTextChangedListener(textWatcher);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };

        addTextChangedListener(textWatcher);
        setInputType(InputType.TYPE_CLASS_NUMBER);
    }

    private boolean isTruePhoneNumber(String phoneNumber) {
        if (TextUtils.isEmpty(phoneNumber))
            return false;

        String phone = NormalizeHelper.standardizedPhone(phoneNumber);

        int except = 0;
        for(int i =0; i<phone.length();i++){
            if(phone.charAt(i)== '0') except++;
            else break;
        }
        int len = phone.length()-except;

        return len>8;
    }

    public void setPhoneValidateListener(PhoneValidateListener listener){
        this.listener = listener;
    }

    public void setSplitChar(Character character){
        this.splitCharacter = character;
    }

    public interface PhoneValidateListener{
        void invalidPhoneNumber();
        void validPhoneNumber();
    }

    int getRealWordCount(String s){
        if (TextUtils.isEmpty(s))
            return 0;
        return s.replaceAll(ADDITION_CHAR,"").length();
    }
}
