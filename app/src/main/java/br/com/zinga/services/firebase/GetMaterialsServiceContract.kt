package br.com.zinga.services.firebase

import br.com.zinga.models.Material

interface GetMaterialsServiceContract {
    fun getMaterials(onSuccess: (ArrayList<Material>) -> Unit, onFailure: () -> Unit)
}