package com.example.tohackmeapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item_single.view.*

class TaskListAdapter (var taskList: List<Todo>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    class TaskViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(task: Todo) {
            itemView.tvTitle.text = task.title
            itemView.tvExplanation.text = task.explanation
            itemView.tvLevel.text = "Level : " + task.level.toString()
            itemView.tvTag.text = task.tag
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_single, parent, false)
        return  TaskViewHolder(view)
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as TaskViewHolder).bind(taskList[position])

    }
}