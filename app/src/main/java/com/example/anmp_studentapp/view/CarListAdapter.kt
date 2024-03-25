package com.example.anmp_studentapp.view

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.anmp_studentapp.databinding.CarListItemBinding
import com.example.anmp_studentapp.model.Car

class CarListAdapter(val carList:ArrayList<Car>):RecyclerView.Adapter<CarListAdapter.CarViewHolder>() {
    class CarViewHolder(var binding:CarListItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val binding = CarListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CarViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return carList.size
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        with(holder.binding) {
            txtID.text = carList[position].id.toString()
            txtName.text = carList[position].make + " " + carList[position].model
        }
    }

    fun updateCarList(newCarList:ArrayList<Car>) {
        carList.clear()
        carList.addAll(newCarList)
        notifyDataSetChanged()
    }
}