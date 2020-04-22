package com.mateusmelodn.searchrecyclerview.util

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager

class UIHelper {
    companion object {
        fun showKeyboardForView(view: View) {
            val inputMethodManager = view.context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            if (view.requestFocus()) {
                inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
            }
        }
    }
}