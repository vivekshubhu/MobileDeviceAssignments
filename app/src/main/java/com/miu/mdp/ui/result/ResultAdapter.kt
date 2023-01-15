package com.miu.mdp.ui.result

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.miu.mdp.databinding.ItemResultBinding

class ResultAdapter : ListAdapter<ResultModel, ResultAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemResultBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun updateList(list: List<ResultModel>) = submitList(list)


    inner class ViewHolder(private val binding: ItemResultBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ResultModel) {
            binding.apply {
                questionTextView.text = item.quiz.question
                correctAnswerTextView.text = item.correctAnswer
                yourAnswerTextView.text = item.yourAnswer
                correctAnswerTextView.setTextColor(
                    ContextCompat.getColor(
                        root.context,
                        android.R.color.holo_green_dark
                    )
                )
                yourAnswerTextView.setTextColor(
                    ContextCompat.getColor(
                        root.context,
                        if (correctAnswerTextView.text == yourAnswerTextView.text) android.R.color.holo_green_dark else android.R.color.holo_red_dark
                    )
                )
            }

        }
    }

    class DiffCallback : DiffUtil.ItemCallback<ResultModel>() {
        override fun areItemsTheSame(oldItem: ResultModel, newItem: ResultModel) =
            oldItem.quiz.id == newItem.quiz.id

        override fun areContentsTheSame(oldItem: ResultModel, newItem: ResultModel) =
            oldItem == newItem
    }
}