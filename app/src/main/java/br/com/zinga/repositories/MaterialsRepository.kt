package br.com.zinga.repositories

import br.com.zinga.models.Course
import br.com.zinga.models.Material
import br.com.zinga.services.firebase.GetMaterialsService
import br.com.zinga.services.firebase.GetMaterialsServiceContract

object MaterialsRepository : Repository {

    var materials: ArrayList<Material> = arrayListOf()
    var getMaterialsService: GetMaterialsServiceContract =
        GetMaterialsService()

    override fun save() {

    }

    override fun load() {

    }

    override fun clear() {

    }

    fun getMaterials(onSuccess: (ArrayList<Material>) -> Unit, onFailure: () -> Unit) {
        getMaterialsService.getMaterials({
            materials = it
            onSuccess(materials)
        }, onFailure)
    }

    fun getMaterialsOfCourse(course: Course) : List<Material> {
        return materials.filter { material -> material.courseId == course.id }
    }
}