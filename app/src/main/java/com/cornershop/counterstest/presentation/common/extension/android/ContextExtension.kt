package com.cornershop.counterstest.presentation.common.extension.android

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.cornershop.counterstest.R
import com.google.android.material.snackbar.Snackbar


/**
 *
 */
fun <T> Context.showLongToast(value: T) =
    showToast(value, Toast.LENGTH_LONG)

/**
 *
 */
fun <T> Context.showToast(value: T, length: Int = Toast.LENGTH_SHORT) {
    val message = when (value) {
        is Int -> try {
            getString(value)
        } catch (e: Exception) {
            value.toString()
        }
        else -> value.toString()
    }
    Toast.makeText(this, message, length).show()
}

/** */
fun Fragment.showSnackBar(@StringRes messageRes: Int, duration: Int = Snackbar.LENGTH_LONG) {
    val stringMessage: String = getString(messageRes)
    showSnackBar(stringMessage)
}

/** */
fun Fragment.showSnackBar(message: String, duration: Int = Snackbar.LENGTH_LONG) {
    val snackBar = Snackbar.make(view ?: return, message, duration)
    val backgroundColor = ContextCompat.getColor(requireContext(), R.color.orange)
    snackBar.view.setBackgroundColor(backgroundColor)
    val textColor = ContextCompat.getColor(requireContext(), android.R.color.black)
    snackBar.setTextColor(textColor)
    snackBar.show()
}


/**
 *
 */
fun <T> Context.navigateTo(javaClass: Class<T>, clearTop: Boolean = false) {
    Intent(this, javaClass).apply {
        if (clearTop)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(this)
    }
}

/** */
fun Context.getColorFromResource(@ColorRes colorResource: Int): Int =
    ContextCompat.getColor(this, colorResource)
