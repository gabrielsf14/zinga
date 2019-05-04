package br.com.zinga.ui.courseMaterials

import br.com.zinga.models.Course
import br.com.zinga.models.Material
import br.com.zinga.repositories.MaterialsRepository

class CourseMaterialsPresenter(
    var view: CourseMaterialsView,
    var materialsRepository: MaterialsRepository
) {

    fun getCachedMaterialsOfCourse(course: Course) : List<Material> {
        val materialsOfCourse = materialsRepository.getMaterialsOfCourse(course)
        if (materialsOfCourse.isNotEmpty()) {
            view.hideProgressBar()
        }
        return materialsOfCourse
    }

    fun getMaterialsOfCourse(course: Course) {
        materialsRepository.getMaterials({
            view.showMaterials(materialsRepository.getMaterialsOfCourse(course))
        }, {
            view.showErrorToObtainMaterials()
        })
    }
}