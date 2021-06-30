package com.cornershop.counterstest.presentation.common.extension.loader

import android.content.Context
import android.view.MenuItem
import android.widget.ProgressBar
import androidx.fragment.app.Fragment

object ProgressMenuItem {

    /* */
    private var menuItem: MenuItem? = null

    /** */
    fun Fragment.showProgressMenuItem(menuItem: MenuItem) =
        requireContext().showProgressMenuItem(menuItem)

    /** */
    fun Context.showProgressMenuItem(item: MenuItem) {
        menuItem = item
        menuItem?.actionView = ProgressBar(this);
    }

    /** */
    fun hideProgressMenuItem() {
        menuItem?.actionView = null
    }
}