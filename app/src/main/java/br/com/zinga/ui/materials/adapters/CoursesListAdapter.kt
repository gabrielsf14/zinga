package br.com.zinga.ui.materials.adapters

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import br.com.zinga.R
import br.com.zinga.models.Course

class CoursesListAdapter(var courses: List<Course>) : RecyclerView.Adapter<CoursesListAdapter.CourseListViewHolder>() {

    lateinit var onItemClicked: (Course) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseListViewHolder {
        return CourseListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_course, parent, false));
    }

    override fun getItemCount(): Int {
        return courses.size
    }

    override fun onBindViewHolder(holder: CourseListViewHolder, position: Int) {
        val course = courses[position]

        holder.tvCourseName.text = course.name

        holder.itemView.setOnClickListener {
            onItemClicked(course)
        }

        when (course.name) {
            "Física" -> holder.ivCourseImg.setImageResource(R.drawable.fisica)
            "Matemática" -> holder.ivCourseImg.setImageResource(R.drawable.mtm)
            else -> {}
        }
        holder.ivCourseImg.setColorFilter(ContextCompat.getColor(holder.ivCourseImg.context, R.color.colorAccent))
    }

    class CourseListViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivCourseImg: ImageView = itemView.findViewById(R.id.ivCourseImg)
        val tvCourseName: TextView = itemView.findViewById(R.id.tvCourseName)
    }
}

