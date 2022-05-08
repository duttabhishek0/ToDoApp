package com.abhishek.todoapp.screens.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.abhishek.todoapp.databinding.TaskItemBinding
import com.abhishek.todoapp.model.Task

class TaskAdapter : PagingDataAdapter<Task, TaskViewHolder>(SHOW_COMPARATOR) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            TaskItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        ).apply {
            binding.root.setOnClickListener {
                getItem(bindingAdapterPosition)?.let { it1 ->
                    callback?.onClick(
                        binding.cardView,
                        it1
                    )
                }
            }

            binding.delete.setOnClickListener {
                getItem(bindingAdapterPosition)?.let { it1 ->
                    deletecallback?.onClick(
                        it1
                    )
                }
            }
        }
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.binding.cardView.transitionName = getItem(position)?.taskId.toString()
        holder.binding.task = getItem(position)
        holder.binding.executePendingBindings()
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == itemCount) SHOW_ITEM else LOADING_ITEM
    }

    companion object {
        private val SHOW_COMPARATOR = object : DiffUtil.ItemCallback<Task>() {
            override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean =
                oldItem.taskId == newItem.taskId

            override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean =
                oldItem == newItem
        }
        private const val SHOW_ITEM = 0
        const val LOADING_ITEM = 1
    }

    var callback: Callback? = null

    interface Callback {
        fun onClick(view: CardView, item: Task)
    }

    interface DeleteCallback {
        fun onClick(item: Task)
    }

    var deletecallback: DeleteCallback? = null
}
