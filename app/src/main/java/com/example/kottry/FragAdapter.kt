package com.example.kottry

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FragAdapter(private val list: List<Model>) :
    RecyclerView.Adapter<FragAdapter.FragViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FragViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return FragViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: FragViewHolder, position: Int) {
        val model: Model = list[position]
        holder.bind(model)
    }

    class FragViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.frag_view_holder, parent, false)) {

        private var txtName: TextView = itemView.findViewById(R.id.txt_name)
        private var txtDesc: TextView = itemView.findViewById(R.id.txt_desc)

        fun bind(model: Model) {
            txtName.text = model.name
            txtDesc.text = model.desc
        }
    }
}