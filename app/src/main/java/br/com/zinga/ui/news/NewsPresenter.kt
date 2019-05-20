package br.com.zinga.ui.news

import br.com.zinga.models.New
import br.com.zinga.repositories.NewsRepository

class NewsPresenter(
    var view: NewsView,
    var newsRepository: NewsRepository
) {
    fun getCachedNews() : List<New> {
        if (newsRepository.news.isNotEmpty()) {
            view.hideProgressBar()
        }
        return newsRepository.news
    }

    fun getNews() {
        newsRepository.getNews({
            view.showNews(it)
        }, {
            view.showErrorToObtainNews()
        })
    }
}