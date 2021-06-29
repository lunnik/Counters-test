package com.cornershop.counterstest.presentation.common.extension.loader

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import com.cornershop.counterstest.R
import com.cornershop.counterstest.databinding.DialogFullProgressCircleBinding

/* */
object ProgressDialog {

    /* */
    private var dialog: Dialog? = null

    /** */
    fun Fragment.showProgressDialog() =
        requireContext().showProgressDialog()

    /** */
    fun Context.showProgressDialog() {
        val binding = DialogFullProgressCircleBinding.inflate(LayoutInflater.from(this))
        dialog = Dialog(this, R.style.AppTheme_FullDialog).apply {
            setContentView(binding.root)
            setCancelable(false)
            show()
        }
    }

    /** */
    fun hideProgressDialog() {
        dialog?.dismiss()
    }

}