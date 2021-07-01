package com.cornershop.counterstest.presentation.common.extension.android.view

import android.content.res.ColorStateList
import androidx.core.widget.addTextChangedListener
import com.cornershop.counterstest.R
import com.cornershop.counterstest.presentation.common.extension.android.getColorFromResource
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


/** */
fun TextInputLayout.setErrorMessage(errorMessage: String?) {
    error = errorMessage
    val color = context.getColorFromResource(
        if (errorMessage == null)
            R.color.white
        else R.color.til_error_color
    )
    setEndIconTintList(ColorStateList.valueOf(color))
}

/** */
fun TextInputEditText.setClearErrorListenerOnType(textInputLayout: TextInputLayout) {
    addTextChangedListener {
        textInputLayout.setErrorMessage(null)
    }
}
