package com.udacity.asteroidradar.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.udacity.asteroidradar.databinding.TicketMainBinding
import com.udacity.asteroidradar.model.Asteroid

class MainAdapter(private val clickListener: AsteroidListener) :
    ListAdapter<Asteroid, MainAdapter.MyViewHolder>(DiffCallback) {


    companion object DiffCallback : DiffUtil.ItemCallback<Asteroid>() {
        override fun areItemsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }

    class MyViewHolder(private val binding: TicketMainBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(asteroid: Asteroid?, clickListener: AsteroidListener) {
            binding.asteroid = asteroid
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                return MyViewHolder(
                    TicketMainBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }
    }

    class AsteroidListener(val clickListener: (asteroid: Asteroid) -> Unit) {
        fun onClick(asteroid: Asteroid) = clickListener(asteroid)
    }
}