package br.com.zinga.services.heroku

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterPresenceServiceContract {
    @POST("/presences/register_presence")
    fun registerPresence(@Body body: RequestBody) : Call<ResponseBody>
}