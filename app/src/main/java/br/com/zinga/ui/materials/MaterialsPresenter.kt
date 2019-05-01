package br.com.zinga.ui.materials

import br.com.zinga.models.Course
import br.com.zinga.repositories.CoursesRepository

class MaterialsPresenter(
    var view: MaterialsView,
    var coursesRepository: CoursesRepository
) {

    fun getCachedCourses() : List<Course> {
        if (coursesRepository.courses.isNotEmpty()) {
            view.hideProgressBar()
        }
        return coursesRepository.courses
    }

    fun getCourses() {
        coursesRepository.getCourses({
            view.showCourses(it)
        }, {
            view.showErrorToObtainCourses()
        })
    }
}