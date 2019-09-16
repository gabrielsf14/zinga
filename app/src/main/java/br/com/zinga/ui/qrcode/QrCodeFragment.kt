package br.com.zinga.ui.qrcode

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import br.com.zinga.Settings
import br.com.zinga.extensions.hideKeyboard
import com.google.zxing.WriterException
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel
import kotlinx.android.synthetic.main.fragment_qr_code.*
import me.ydcool.lib.qrmodule.encoding.QrGenerator
import br.com.zinga.R
import br.com.zinga.extensions.showAlert
import br.com.zinga.ui.BottomSheetPassword
import br.com.zinga.utils.Preferences


class QrCodeFragment : Fragment(), QrCodeView {

    lateinit var presenter: QrCodePresenter
    lateinit var dialog: ProgressDialog

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_qr_code, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = QrCodePresenter(this)

        val registration = Settings.getRegistration(activity!!)
        if (registration.isNotEmpty()) {
            ivQrCode.setImageBitmap(generateQrCode(registration))
            tvRegistration.text = "Matrícula: $registration"
            btnGenerateAnother.visibility = View.VISIBLE
            llRegistration.visibility = View.VISIBLE
            llRegistrationEnter.visibility = View.GONE
        }

        btnGenerate.setOnClickListener {
            if (etRegistration.text.isNotEmpty()) {
                activity?.hideKeyboard(etRegistration)
                progressBar.visibility = View.VISIBLE
                llRegistrationEnter.visibility = View.GONE
                Handler().postDelayed({
                    progressBar.visibility = View.GONE
                    try {
                        val newRegistration = etRegistration.text.toString()
                        ivQrCode.setImageBitmap(generateQrCode(newRegistration))
                        Settings.saveRegistration(activity!!, newRegistration)
                        tvRegistration.text = "Matrícula: $newRegistration"
                        btnGenerateAnother.visibility = View.VISIBLE
                        llRegistration.visibility = View.VISIBLE
                        etRegistration.setText("")
                    } catch (e: WriterException) {
                        e.printStackTrace()
                    }
                }, 1000)
            }
        }

        btnGenerateAnother.setOnClickListener {
            btnGenerateAnother.visibility = View.GONE
            llRegistrationEnter.visibility = View.VISIBLE
            llRegistration.visibility = View.GONE
        }

        btnRead.setOnClickListener {
            val integrator = IntentIntegrator.forSupportFragment(this)
            integrator.setOrientationLocked(false)
            integrator.setPrompt("Alinhe a câmera com o qrcode.")
            integrator.initiateScan()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result?.contents != null) {
            dialog = ProgressDialog.show(activity!!, "", "Buscando matrícula...")
            val usernameFromPreferences = Preferences.load(activity!!, "username")
            if (usernameFromPreferences.isNotEmpty()) {
                val passwordFromPreferences = Preferences.load(activity!!, "password")
                presenter.registerPresence(result.contents, usernameFromPreferences, passwordFromPreferences)
            } else {
                BottomSheetPassword.show(activity!!) { username, password ->
                    Preferences.save(activity!!, "username", username)
                    Preferences.save(activity!!, "password", password)
                    BottomSheetPassword.hide()
                    presenter.registerPresence(result.contents, username, password)
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun showRegisterFailure() {
        dialog.dismiss()
        activity?.showAlert("Não foi possível registrar a presença.")
    }

    override fun showRegisterSuccess() {
        dialog.dismiss()
        activity?.showAlert("Presença registrada com sucesso!")
    }

    private fun generateQrCode(string: String) : Bitmap {
        return QrGenerator.Builder()
            .content(string)
            .qrSize(280)
            .margin(0)
            .color(Color.BLACK)
            .bgColor(Color.WHITE)
            .ecc(ErrorCorrectionLevel.H)
            .overlay(activity, R.mipmap.ic_launcher_zinga_round)
            .overlaySize(75)
            .overlayAlpha(255)
            .overlayXfermode(PorterDuff.Mode.SRC_IN)
            .encode()
    }
}
