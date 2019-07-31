package br.com.zinga.repositories

import br.com.zinga.models.Course
import br.com.zinga.services.firebase.GetCoursesService
import br.com.zinga.services.firebase.GetCoursesServiceContract

object CoursesRepository : Repository {

    var courses: ArrayList<Course> = arrayListOf()
    var getCoursesService: GetCoursesServiceContract =
        GetCoursesService()

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