package com.cornershop.counterstest.presentation.common.extension.android.view

import android.widget.TextView

/* */
fun TextView.setTextOrGoneIfBlank(value: String?) {
    if (value.isNullOrBlank())
        gone()
    else {
        text = value
        visible()
    }
}