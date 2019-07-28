package br.com.zinga.services

import android.support.v4.util.ArrayMap
import retrofit2.Retrofit
import org.json.JSONObject
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback


class RegisterPresenceService {

    fun registerPresence(registration: String, onSuccess: () -> Unit, onFailure: () -> Unit) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://cursinho-zinga.herokuapp.com/")
            .build()
        val service = retrofit.create(RegisterPresenceServiceContract::class.java)

        val jsonParams = ArrayMap<String, String>()
        jsonParams["registration"] = registration

        val body = RequestBody.create(
            okhttp3.MediaType.parse("application/json; charset=utf-8"),
            JSONObject(jsonParams).toString()
        )
        val response = service.registerPresence(body)

        response.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, rawResponse: retrofit2.Response<ResponseBody>) {
                onSuccess()
            }
            override fun onFailure(call: Call<ResponseBody>, throwable: Throwable) {
               onFailure()
            }
        })
    }
}