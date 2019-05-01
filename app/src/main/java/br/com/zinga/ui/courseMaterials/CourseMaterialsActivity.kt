package br.com.zinga.ui.courseMaterials

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.zinga.R
import br.com.zinga.extensions.setLightStatusBar
import br.com.zinga.models.Course
import kotlinx.android.synthetic.main.activity_course_materials.*

class CourseMaterialsActivity : AppCompatActivity() {

    lateinit var course: Course

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLightStatusBar()
        overridePendingTransition(R.anim.trans_in, R.anim.trans_out)
        setContentView(R.layout.activity_course_materials)

        course = intent.getSerializableExtra("course") as Course

        toolbar.title = course.name
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }
}
