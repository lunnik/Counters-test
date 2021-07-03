package com.cornershop.counterstest.presentation.common.extension.message.dialog

import android.content.Context
import android.content.DialogInterface
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.cornershop.counterstest.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

/** */
fun Fragment.showCommonDialog(
    @StringRes titleRes: Int = R.string.error_creating_counter_title,
    @StringRes contentRes: Int = R.string.connection_error_description,
    @StringRes negativeActionRes: Int = R.string.ok,
    @StringRes positiveActionRes: Int = R.string.cancel,
    negativeAction: (() -> Unit)? = null,
    positiveAction: () -> Unit,
) {
    requireContext().showCommonDialog(
        titleRes,
        contentRes,
        negativeActionRes,
        positiveActionRes,
        negativeAction,
        positiveAction
    )
}

/** */
fun Context.showCommonDialog(
    @StringRes titleRes: Int = R.string.error_creating_counter_title,
    @StringRes contentRes: Int = R.string.connection_error_description,
    @StringRes negativeActionRes: Int = R.string.ok,
    @StringRes positiveActionRes: Int = R.string.cancel,
    negativeAction: (() -> Unit)? = null,
    positiveAction: () -> Unit,
) {
    val dialogBuilder = MaterialAlertDialogBuilder(this, R.style.Theme_Dialog)
        .setTitle(titleRes)
        .setMessage(contentRes)
        .setPositiveButton(positiveActionRes, buildDialogInterfaceOnClickListener(positiveAction))
        .setCancelable(false)
    negativeAction?.apply {
        dialogBuilder.setNegativeButton(
            negativeActionRes,
            buildDialogInterfaceOnClickListener(negativeAction)
        )
    }
    dialogBuilder.create().show()
}


/** */
fun Context.showCommonDialog(
   titleRes: String ,
    @StringRes negativeActionRes: Int = R.string.ok,
    @StringRes positiveActionRes: Int = R.string.cancel,
    negativeAction: (() -> Unit)? = null,
    positiveAction: () -> Unit,
) {
    val dialogBuilder = MaterialAlertDialogBuilder(this, R.style.Theme_Dialog)
        .setTitle(titleRes)
        .setPositiveButton(positiveActionRes, buildDialogInterfaceOnClickListener(positiveAction))
        .setCancelable(false)
    negativeAction?.apply {
        dialogBuilder.setNegativeButton(
            negativeActionRes,
            buildDialogInterfaceOnClickListener(negativeAction)
        )
    }
    dialogBuilder.create().show()
}



/* */
private fun buildDialogInterfaceOnClickListener(action: (() -> Unit)? = null) =
    DialogInterface.OnClickListener { dialog, _ ->
        action?.invoke()
        dialog.dismiss()
    }