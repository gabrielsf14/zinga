package br.com.zinga.ui.news.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import br.com.zinga.R
import br.com.zinga.models.Material
import br.com.zinga.models.MaterialType
import br.com.zinga.models.New

class NewsListAdapter(var news: List<New>) : RecyclerView.Adapter<NewsListAdapter.NewsListViewHolder>() {

    lateinit var onItemClicked: (New) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsListViewHolder {
        return NewsListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false))
    }

    override fun getItemCount(): Int {
        return news.size
    }

    override fun onBindViewHolder(holder: NewsListViewHolder, position: Int) {
        val new = news[position]

        holder.tvNewTitle.text = new.title
        holder.tvNewDate.text = new.date

        holder.itemView.setOnClickListener {
            onItemClicked(new)
        }
    }

    class NewsListViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNewTitle: TextView = itemView.findViewById(R.id.tvNewTitle)
        val tvNewDate: TextView = itemView.findViewById(R.id.tvNewDate)
    }
}