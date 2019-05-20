package br.com.zinga.ui.news

import br.com.zinga.models.New

interface NewsView {
    fun showNews(news: List<New>)
    fun showErrorToObtainNews()
    fun hideProgressBar()
}