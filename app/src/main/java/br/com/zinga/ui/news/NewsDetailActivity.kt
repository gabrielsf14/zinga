package br.com.zinga.ui.news

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.zinga.R
import br.com.zinga.extensions.setLightStatusBar
import br.com.zinga.models.New
import kotlinx.android.synthetic.main.activity_news_detail.*

class NewsDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)
        setLightStatusBar()
        overridePendingTransition(R.anim.trans_in, R.anim.trans_out)

        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        val new = intent.getSerializableExtra("new") as New

        tvNewTitle.text = new.title
        tvNewDate.text = new.date
        tvNewContent.text = new.content
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.trans_in2, R.anim.trans_out2)
    }
}
