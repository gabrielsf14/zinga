package br.com.zinga.repositories

import br.com.zinga.models.News
import br.com.zinga.services.GetNewsService
import br.com.zinga.services.GetNewsServiceContract

object NewsRepository : Repository {

    var news: ArrayList<News> = arrayListOf()
    var getNewsService: GetNewsServiceContract = GetNewsService()

    override fun save() {

    }

    override fun load() {

    }

    override fun clear() {

    }

    fun getNews(onSuccess: (ArrayList<News>) -> Unit, onFailure: () -> Unit) {
        getNewsService.getNews({
            news = it
            onSuccess(news)
        }, onFailure)
    }}