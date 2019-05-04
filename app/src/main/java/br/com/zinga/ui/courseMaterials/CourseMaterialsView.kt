package br.com.zinga.ui.courseMaterials

import br.com.zinga.models.Material

interface CourseMaterialsView {
    fun showMaterials(materials: List<Material>)
    fun showErrorToObtainMaterials()
    fun hideProgressBar()
}