package br.com.zinga.ui.materials


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.zinga.R
import br.com.zinga.models.Course
import br.com.zinga.repositories.CoursesRepository
import br.com.zinga.ui.materials.adapters.CoursesListAdapter
import kotlinx.android.synthetic.main.fragment_materials.*

class MaterialsFragment : Fragment(), MaterialsView {

    lateinit var presenter: MaterialsPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_materials, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = MaterialsPresenter(this, CoursesRepository)

        presenter.getCourses()
    }

    override fun showCourses(courses: List<Course>) {
        rvCourses.layoutManager = GridLayoutManager(activity!!, 2)
        rvCourses.adapter = CoursesListAdapter(courses)
    }

    override fun showErrorToObtainCourses() {

    }
}
