package br.com.zinga.ui.qrcode

import br.com.zinga.services.heroku.RegisterPresenceService

class QrCodePresenter(var view: QrCodeView) {

    var registerPresenceService: RegisterPresenceService =
        RegisterPresenceService()

    fun registerPresence(registration: String) {
        registerPresenceService.registerPresence(registration, {
            view.showRegisterSuccess()
        }, {
            view.showRegisterFailure()
        })
    }
}