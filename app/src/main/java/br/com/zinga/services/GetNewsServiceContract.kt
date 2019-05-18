package br.com.zinga.services

import br.com.zinga.models.News

interface GetNewsServiceContract {
    fun getNews(onSuccess: (ArrayList<News>) -> Unit, onFailure: () -> Unit)
}