package br.com.zinga.services.firebase

import android.util.Log
import br.com.zinga.models.New
import com.google.firebase.database.*

class GetNewsService : GetNewsServiceContract {

    private val fbDatabase = FirebaseDatabase.getInstance().getReference("Zinga/News")

    override fun getNews(onSuccess: (ArrayList<New>) -> Unit, onFailure: () -> Unit) {
        fbDatabase.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val t = object : GenericTypeIndicator<ArrayList<New>>() {}
                val news = dataSnapshot.getValue(t)!!
                onSuccess(news)
            }

            override fun onCancelled(error: DatabaseError) {
                onFailure()
                Log.w("zinga", "Failed to read value.", error.toException())
            }
        })
    }
}