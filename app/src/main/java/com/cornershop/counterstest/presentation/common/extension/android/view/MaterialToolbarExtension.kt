package com.cornershop.counterstest.presentation.common.extension.android.view

import androidx.fragment.app.Fragment
import com.google.android.material.appbar.MaterialToolbar

/**
 *
 */
fun MaterialToolbar.setNavigationOnClickListenerAsActivityBackPressed(fragment: Fragment) {
    setNavigationOnClickListener {
        fragment.requireActivity().onBackPressed()
    }
}