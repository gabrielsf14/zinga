package br.com.zinga.ui.courseMaterials.adapters

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import br.com.zinga.R
import br.com.zinga.models.Course
import br.com.zinga.models.Material
import br.com.zinga.models.MaterialType
import br.com.zinga.ui.materials.adapters.CoursesListAdapter

class MaterialsListAdapter(var materials: List<Material>) : RecyclerView.Adapter<MaterialsListAdapter.MaterialsListViewHolder>() {

    lateinit var onItemClicked: (Material) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MaterialsListViewHolder {
        return MaterialsListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_material, parent, false));
    }

    override fun getItemCount(): Int {
        return materials.size
    }

    override fun onBindViewHolder(holder: MaterialsListViewHolder, position: Int) {
        val material = materials[position]

        holder.tvMaterialName.text = material.name
        holder.tvMaterialType.text = MaterialType.values()[material.type].formattedName()

        holder.itemView.setOnClickListener {
            onItemClicked(material)
        }
    }

    class MaterialsListViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvMaterialType: TextView = itemView.findViewById(R.id.tvMaterialType)
        val tvMaterialName: TextView = itemView.findViewById(R.id.tvMaterialName)
    }
}