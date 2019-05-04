package br.com.zinga.ui.courseMaterials

import android.content.Intent
import android.graphics.drawable.GradientDrawable
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import br.com.zinga.R
import br.com.zinga.extensions.setLightStatusBar
import br.com.zinga.models.Course
import br.com.zinga.models.Material
import br.com.zinga.repositories.MaterialsRepository
import br.com.zinga.ui.courseMaterials.adapters.MaterialsListAdapter
import kotlinx.android.synthetic.main.activity_course_materials.*

class CourseMaterialsActivity : AppCompatActivity(), CourseMaterialsView {

    lateinit var course: Course
    lateinit var presenter: CourseMaterialsPresenter
    lateinit var materialsListAdapter: MaterialsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLightStatusBar()
        overridePendingTransition(R.anim.trans_in, R.anim.trans_out)
        setContentView(R.layout.activity_course_materials)

        presenter = CourseMaterialsPresenter(this, MaterialsRepository)

        course = intent.getSerializableExtra("course") as Course

        toolbar.title = course.name
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        btnTryAgain.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            presenter.getMaterialsOfCourse(course)
        }

        materialsListAdapter = MaterialsListAdapter(presenter.getCachedMaterialsOfCourse(course))
        materialsListAdapter.onItemClicked = {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it.url))
            startActivity(intent)
        }
        rvMaterials.layoutManager = LinearLayoutManager(this)
        rvMaterials.adapter = materialsListAdapter
        rvMaterials.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))

        presenter.getMaterialsOfCourse(course)
    }

    override fun showMaterials(materials: List<Material>) {
        hideProgressBar()
        materialsListAdapter.materials = materials
        materialsListAdapter.notifyDataSetChanged()
    }

    override fun showErrorToObtainMaterials() {
        llNoConnection.visibility = View.VISIBLE
        hideProgressBar()
    }

    override fun hideProgressBar() {
        progressBar.visibility = View.GONE //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.trans_in2, R.anim.trans_out2)
    }
}
