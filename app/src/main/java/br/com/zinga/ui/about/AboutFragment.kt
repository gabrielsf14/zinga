package br.com.zinga.ui.about

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.zinga.R
import kotlinx.android.synthetic.main.fragment_about.*

class AboutFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnSignup.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://bit.ly/zinga_inscricao"))
            startActivity(intent)
        }

        btnInstagram.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/cursinhodozinga"))
            startActivity(intent)
        }

        btnFacebook.setOnClickListener {
            startActivity(openFacebookIntent())
        }
    }

    private fun openFacebookIntent(): Intent {
        return try {
            activity?.packageManager?.getPackageInfo("com.facebook.katana", 0)
            Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/1197937917047331"))
        } catch (e: Exception) {
            Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/cursinhodozinga"))
        }
    }
}
