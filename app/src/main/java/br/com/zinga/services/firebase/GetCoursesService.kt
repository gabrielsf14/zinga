package br.com.zinga.services.firebase

import android.util.Log
import br.com.zinga.models.Course
import com.google.firebase.database.*

class GetCoursesService : GetCoursesServiceContract {

    private val fbDatabase = FirebaseDatabase.getInstance().getReference("Zinga/Courses")

    override fun getCourses(onSuccess: (ArrayList<Course>) -> Unit, onFailure: () -> Unit) {
        fbDatabase.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val t = object : GenericTypeIndicator<ArrayList<Course>>() {}
                val courses = dataSnapshot.getValue(t)!!
                onSuccess(courses)
            }

            override fun onCancelled(error: DatabaseError) {
                onFailure()
                Log.w("zinga", "Failed to read value.", error.toException())
            }
        })
    }
}