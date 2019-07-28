package br.com.zinga.extensions

import android.app.Activity
import android.graphics.Point
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.R.string.cancel
import android.app.AlertDialog
import android.content.DialogInterface



fun Activity.getScreenHeight(): Int {
    val display = this.windowManager.defaultDisplay
    val size = Point()
    display.getSize(size)
    return size.y
}

fun Activity.getScreenWidth(): Int {
    val display = this.windowManager.defaultDisplay
    val size = Point()
    display.getSize(size)
    return size.x
}

fun Activity.setLightStatusBar() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        var flags = this.window.decorView.systemUiVisibility
        flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        this.window.decorView.systemUiVisibility = flags
    }
}

fun Activity.clearLightStatusBar() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        var flags = this.window.decorView.systemUiVisibility
        flags = flags and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
        this.window.decorView.systemUiVisibility = flags
    }
}

fun Activity.hideKeyboard(v: View) {
    val imm = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(this.currentFocus?.windowToken, 0)
    v.clearFocus()
}

fun Activity.getRootView() : View {
    return (this.findViewById(android.R.id.content) as ViewGroup).getChildAt(0) as ViewGroup
}

fun Activity.showKeyboard() {
    val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
}

fun Activity.showAlert(message: String) {
    val builder1 = AlertDialog.Builder(this)
    builder1.setMessage(message)
    builder1.setCancelable(true)

    builder1.setPositiveButton("Ok") { dialog, _ ->
        dialog.cancel()
    }

    val alert11 = builder1.create()
    alert11.show()
}
