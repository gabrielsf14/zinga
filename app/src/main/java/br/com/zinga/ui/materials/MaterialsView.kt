package br.com.zinga.ui.materials

import br.com.zinga.models.Course

interface MaterialsView {
    fun showCourses(courses: List<Course>)
    fun showErrorToObtainCourses()
}