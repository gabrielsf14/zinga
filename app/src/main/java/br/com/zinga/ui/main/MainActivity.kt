package br.com.zinga.ui.main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import br.com.zinga.R
import br.com.zinga.extensions.removeShiftMode
import br.com.zinga.extensions.setLightStatusBar
import br.com.zinga.ui.about.AboutFragment
import br.com.zinga.ui.materials.MaterialsFragment
import br.com.zinga.ui.news.NewsFragment
import br.com.zinga.ui.qrcode.QrCodeFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var newsFragment = NewsFragment()
    private var materialsFragment = MaterialsFragment()
    private var aboutFragment = AboutFragment()
    private var qrCodeFragment = QrCodeFragment()

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                hideFragments()
                toolbar.title = "Últimas notícias"
                supportFragmentManager.beginTransaction().show(newsFragment).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_materials -> {
                hideFragments()
                toolbar.title = "Materiais"
                supportFragmentManager.beginTransaction().show(materialsFragment).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_qrcode -> {
                hideFragments()
                toolbar.title = "Qr Code"
                supportFragmentManager.beginTransaction().show(qrCodeFragment).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_project -> {
                hideFragments()
                toolbar.title = "Sobre"
                supportFragmentManager.beginTransaction().show(aboutFragment).commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        overridePendingTransition(R.anim.trans_in, R.anim.trans_out)

        navigation.removeShiftMode()
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        toolbar.title = "Últimas notícias"

        setLightStatusBar()
        setupFragments()
    }

    private fun setupFragments() {
        supportFragmentManager.beginTransaction().add(R.id.mainContent, newsFragment).commit()
        supportFragmentManager.beginTransaction().add(R.id.mainContent, materialsFragment).commit()
        supportFragmentManager.beginTransaction().add(R.id.mainContent, qrCodeFragment).commit()
        supportFragmentManager.beginTransaction().add(R.id.mainContent, aboutFragment).commit()

        supportFragmentManager.beginTransaction().hide(materialsFragment).commit()
        supportFragmentManager.beginTransaction().hide(aboutFragment).commit()
        supportFragmentManager.beginTransaction().hide(qrCodeFragment).commit()

        supportFragmentManager.beginTransaction().show(newsFragment).commit()
    }

    private fun hideFragments() {
        supportFragmentManager.fragments.forEach {
            supportFragmentManager.beginTransaction().hide(it).commit()
        }
    }
}
