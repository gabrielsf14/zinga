package br.com.zinga.services.firebase

import android.util.Log
import br.com.zinga.models.Material
import com.google.firebase.database.*

class GetMaterialsService : GetMaterialsServiceContract {

    private val fbDatabase = FirebaseDatabase.getInstance().getReference("Zinga/Materials")

    override fun getMaterials(onSuccess: (ArrayList<Material>) -> Unit, onFailure: () -> Unit) {
        fbDatabase.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val t = object : GenericTypeIndicator<ArrayList<Material>>() {}
                val materials = dataSnapshot.getValue(t)!!
                onSuccess(materials)
            }

            override fun onCancelled(error: DatabaseError) {
                onFailure()
                Log.w("zinga", "Failed to read value.", error.toException())
            }
        })
    }
}