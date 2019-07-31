package br.com.zinga.services.firebase

import br.com.zinga.models.Course

interface GetCoursesServiceContract {
    fun getCourses(onSuccess: (ArrayList<Course>) -> Unit, onFailure: () -> Unit)
}