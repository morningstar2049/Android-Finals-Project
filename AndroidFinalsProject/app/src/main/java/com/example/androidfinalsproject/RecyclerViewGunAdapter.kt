package com.example.androidfinalsproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RecyclerViewGunAdapter(private val gunList: List<Gun>) : RecyclerView.Adapter<RecyclerViewGunAdapter.GunViewHolder>() {

    class GunViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private var imageView : ImageView = itemView.findViewById(R.id.imageView)
        private var textView1 : TextView = itemView.findViewById(R.id.textView)
        private var textView2: TextView = itemView.findViewById(R.id.textView2)

        fun setData(gun: Gun){
            textView1.text = gun.title

            textView2.text = gun.description

            Glide.with(itemView).load(gun.imageUrl).into(imageView)
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GunViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        return GunViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: GunViewHolder, position: Int) {
        holder.setData(gunList[position])

    }

    override fun getItemCount(): Int {
        return gunList.size
    }
}