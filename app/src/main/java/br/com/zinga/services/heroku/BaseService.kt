package br.com.zinga.services.heroku

import android.support.v4.util.ArrayMap
import okhttp3.RequestBody
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit

open class BaseService {

    val retrofit = Retrofit.Builder()
        .baseUrl("https://cursinho-zinga.herokuapp.com/")
        .build()!!
    protected lateinit var response: Call<ResponseBody>
    protected lateinit var body: RequestBody

    fun mountBody(jsonParams: ArrayMap<String, String>) {
        body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), JSONObject(jsonParams).toString())
    }

    fun request(onSuccess: () -> Unit, onFailure: () -> Unit) {
        response.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, rawResponse: retrofit2.Response<ResponseBody>) {
                if(rawResponse.code() in 400..598){
                    onFailure()
                } else {
                    onSuccess()
                }
            }
            override fun onFailure(call: Call<ResponseBody>, throwable: Throwable) {
                onFailure()
            }
        })
    }
}