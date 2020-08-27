package com.example.tohackmeapp

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item_single.view.*

class TaskListAdapter (var taskList: List<Todo>, val clickListener: (Todo) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    class TaskViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(task: Todo, clickListener: (Todo) -> Unit) {
            itemView.tvTitle.text = task.title
            itemView.tvExplanation.text = task.explanation
            itemView.tvLevel.text = "Level : " + task.level.toString()
            itemView.tvTag.text = task.tag


            if (task.status) itemView.cardView.setCardBackgroundColor(Color.rgb(92, 184, 92))
            else itemView.cardView.setCardBackgroundColor(Color.rgb(217, 83, 79))

            itemView.setOnClickListener{
                clickListener(task)
            }
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
        (holder as TaskViewHolder).bind(taskList[position], clickListener)

    }
}