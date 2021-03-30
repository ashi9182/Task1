package com.example.task1.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.task1.R
import com.example.task1.models.Car
import kotlinx.android.synthetic.main.single_car.view.*

class CarsAdapter(private val cars : List<Car>) : RecyclerView.Adapter<CarsAdapter.CarsViewHolder>(){

    inner class CarsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_car,parent,false)
        return CarsViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarsViewHolder, position: Int) {
        holder.itemView.tv_modelname.text = cars[position].modelName
        holder.itemView.tv_modelYear.text = cars[position].modelYear
        holder.itemView.tv_index.text = cars[position].index.toString()
    }

    override fun getItemCount(): Int {
        return cars.size
    }

}