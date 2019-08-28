package br.com.zinga.services.heroku

import android.support.v4.util.ArrayMap
import retrofit2.Retrofit
import org.json.JSONObject
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback


class RegisterPresenceService : BaseService() {

    fun registerPresence(registration: String, username: String, password: String, onSuccess: () -> Unit, onFailure: () -> Unit) {
        val service = retrofit.create(RegisterPresenceServiceContract::class.java)
        val jsonParams = ArrayMap<String, String>()
        jsonParams["registration"] = registration
        jsonParams["username"] = username
        jsonParams["password"] = password
        mountBody(jsonParams)
        response = service.registerPresence(body)
        request(onSuccess, onFailure)
    }
}