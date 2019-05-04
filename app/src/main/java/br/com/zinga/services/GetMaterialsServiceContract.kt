package br.com.zinga.services

import br.com.zinga.models.Material

interface GetMaterialsServiceContract {
    fun getMaterials(onSuccess: (ArrayList<Material>) -> Unit, onFailure: () -> Unit)
}