package br.com.zinga.ui

import android.app.Activity
import android.support.design.widget.BottomSheetDialog
import android.widget.Button
import android.widget.EditText
import br.com.zinga.R
import br.com.zinga.extensions.showKeyboard

object BottomSheetPassword {

    private var bottomSheetDialog: BottomSheetDialog? = null

    fun show(activity: Activity, onContinueClick: (String, String) -> Unit){
        bottomSheetDialog = BottomSheetDialog(activity)
        val sheetView = activity.layoutInflater.inflate(R.layout.password_bottom_sheet_view, null)
        val btnContinue: Button = sheetView.findViewById(R.id.btnContinue)
        val etUser: EditText = sheetView.findViewById(R.id.etUser)
        val etPassword: EditText = sheetView.findViewById(R.id.etPassword)
        etUser.setText("")
        etPassword.setText("")
        btnContinue.setOnClickListener {
            onContinueClick(etUser.text.toString(), etPassword.text.toString())
        }
        bottomSheetDialog?.setContentView(sheetView)
        activity.showKeyboard()
        bottomSheetDialog?.show()
    }

    fun hide() {
        bottomSheetDialog?.dismiss()
    }
}