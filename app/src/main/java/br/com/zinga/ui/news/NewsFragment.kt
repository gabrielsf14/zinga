package br.com.zinga.ui.news

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import br.com.zinga.R
import br.com.zinga.models.New
import br.com.zinga.repositories.NewsRepository
import br.com.zinga.ui.courseMaterials.CourseMaterialsActivity
import br.com.zinga.ui.news.adapters.NewsListAdapter
import kotlinx.android.synthetic.main.fragment_news.*

class NewsFragment : Fragment(), NewsView {

    lateinit var presenter: NewsPresenter
    lateinit var newsListAdapter: NewsListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = NewsPresenter(this, NewsRepository)

        newsListAdapter = NewsListAdapter(presenter.getCachedNews())
        newsListAdapter.onItemClicked = {
            val intent = Intent(activity, CourseMaterialsActivity::class.java)
            intent.putExtra("new", it)
            startActivity(intent)
        }
        rvNews.addItemDecoration(DividerItemDecoration(activity, LinearLayoutManager.VERTICAL))
        rvNews.layoutManager = LinearLayoutManager(activity)
        rvNews.adapter = newsListAdapter

        btnTryAgain.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            presenter.getNews()
        }

        presenter.getNews()
    }

    override fun showNews(news: List<New>) {
        hideProgressBar()
        llNoConnection.visibility = View.GONE
        newsListAdapter.news = news
        newsListAdapter.notifyDataSetChanged()    }

    override fun showErrorToObtainNews() {
        llNoConnection.visibility = View.VISIBLE
        hideProgressBar()
    }

    override fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }
}
