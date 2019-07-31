package br.com.zinga.services.firebase

import br.com.zinga.models.New

interface GetNewsServiceContract {
    fun getNews(onSuccess: (ArrayList<New>) -> Unit, onFailure: () -> Unit)
}