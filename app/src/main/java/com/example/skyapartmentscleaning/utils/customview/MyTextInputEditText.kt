package com.example.skyapartmentscleaning.utils.customview

import android.content.Context
import android.util.AttributeSet
import android.view.KeyEvent

import com.google.android.material.textfield.TextInputEditText

class MyTextInputEditText :
    TextInputEditText {
    constructor(context: Context) : super(context) {}
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {}
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun onKeyPreIme(keyCode: Int, event: KeyEvent?): Boolean {
        if (event?.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            dispatchKeyEvent(event);
            return false;
        }
        return super.onKeyPreIme(keyCode, event)
    }
}