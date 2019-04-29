package br.com.zinga.repositories

import android.util.Log
import br.com.zinga.R
import br.com.zinga.models.Course
import br.com.zinga.services.GetCoursesService
import br.com.zinga.services.GetCoursesServiceContract
import com.google.firebase.database.*

object CoursesRepository : Repository {

    var courses: ArrayList<Course> = arrayListOf()
    var getCoursesService: GetCoursesServiceContract = GetCoursesService()

    override fun save() {

    }

    override fun load() {

    }

    override fun clear() {

    }

    fun getCourses(onSuccess: (ArrayList<Course>) -> Unit, onFailure: () -> Unit) {
        getCoursesService.getCourses({
            courses = it
            onSuccess(courses)
        }, onFailure)
    }
}