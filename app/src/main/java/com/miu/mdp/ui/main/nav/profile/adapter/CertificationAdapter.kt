package com.miu.mdp.ui.main.nav.profile.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.miu.mdp.R
import com.miu.mdp.data.Certification
import com.miu.mdp.databinding.ItemCertificationBinding

class CertificationAdapter(var onItemClick: ((Certification) -> Unit)?) : ListAdapter<Certification, CertificationAdapter.ViewHolder>(
    DiffCallback()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCertificationBinding
            .inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
        holder.itemView.setOnClickListener { onItemClick?.let { it1 -> it1(currentItem) } }
    }

    inner class ViewHolder(private val binding: ItemCertificationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(certification: Certification){
            Glide.with(binding.root.context)
                .load(
                  certification.imageUrl
                ).placeholder(R.drawable.ic_baseline_android_24)
                .dontAnimate()
                .into(binding.ivLogo)
            binding.tvCertificationTitle.text= certification.certificationTitle

        }
    }


    class DiffCallback : DiffUtil.ItemCallback<Certification>() {
        override fun areItemsTheSame(oldItem: Certification, newItem: Certification) =
            oldItem.certificationTitle == newItem.certificationTitle

        override fun areContentsTheSame(oldItem: Certification, newItem: Certification) =
            oldItem == newItem
    }
}