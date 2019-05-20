package br.com.zinga.repositories

import br.com.zinga.models.New
import br.com.zinga.services.GetNewsService
import br.com.zinga.services.GetNewsServiceContract

object NewsRepository : Repository {

    var news: ArrayList<New> = arrayListOf()
    var getNewsService: GetNewsServiceContract = GetNewsService()

    override fun save() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun load() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clear() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun getNews(onSuccess: (ArrayList<New>) -> Unit, onFailure: () -> Unit) {
        getNewsService.getNews({
            news = it
            onSuccess(news)
        }, onFailure)
    }
}