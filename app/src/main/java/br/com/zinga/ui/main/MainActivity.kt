package br.com.zinga.ui.main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import br.com.zinga.R
import br.com.zinga.extensions.setLightStatusBar
import br.com.zinga.ui.about.AboutFragment
import br.com.zinga.ui.materials.MaterialsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var materialsFragment = MaterialsFragment()
    private var aboutFragment = AboutFragment()

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                hideFragments()
                toolbar.title = "Materiais"
                supportFragmentManager.beginTransaction().show(materialsFragment).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_proves -> {
                hideFragments()
                toolbar.title = "Simulados"
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_project -> {
                hideFragments()
                toolbar.title = "Sobre o projeto"
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

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        setLightStatusBar()
        setupFragments()

//        overridePendingTransition(R.anim.trans_right_out, R.anim.trans_out)
    }

    private fun setupFragments() {
        supportFragmentManager.beginTransaction().add(R.id.mainContent, materialsFragment).commit()
        supportFragmentManager.beginTransaction().add(R.id.mainContent, aboutFragment).commit()
        supportFragmentManager.beginTransaction().hide(aboutFragment).commit()
        supportFragmentManager.beginTransaction().show(materialsFragment).commit()
    }

    private fun hideFragments() {
        supportFragmentManager.fragments.forEach {
            supportFragmentManager.beginTransaction().hide(it).commit()
        }
    }
}
