package br.com.zinga.ui.materials

import br.com.zinga.repositories.CoursesRepository

class MaterialsPresenter(
    var view: MaterialsView,
    var coursesRepository: CoursesRepository
) {
    fun getCourses() {
        coursesRepository.getCourses({
            view.showCourses(it)
        }, {
            view.showErrorToObtainCourses()
        })
    }
}