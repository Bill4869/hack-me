package com.example.tohackmeapp

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item_single.view.*

class TaskListAdapter(var taskList: List<Todo>, val clickListener: (Todo) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(task: Todo, clickListener: (Todo) -> Unit) {

            // old cardView
//            itemView.tvTitle.text = task.title
//            itemView.tvExplanation.text = task.explanation
//            itemView.tvLevel.text = "Level : " + task.level.toString()
//            itemView.tvTag.text = task.tag
//
//


            // new cardView
            itemView.tvTitle.text = task.title
            itemView.tvExplanation.text = task.explanation
            itemView.tvLevel.text = "Level : " + task.level.toString()

            var tag = ""
            when (task.tag) {
                "physical" -> {tag = "運動"}
                "intelligence" -> {tag = "学習"}
                "lifestyle" -> {tag = "生活"}
                "others" -> {tag = "その他"}
            }

            itemView.tvTag.text = tag

            when (task.tag) {
                "physical" -> itemView.btnTag.setBackgroundResource(R.drawable.physical_dot)
                "intelligence" -> itemView.btnTag.setBackgroundResource(R.drawable.intelligence_dot)
                "lifestyle" -> itemView.btnTag.setBackgroundResource(R.drawable.lifestyle_dot)
                "others" -> itemView.btnTag.setBackgroundResource(R.drawable.others_dot)

            }

//            if (task.status) itemView.cardView.setCardBackgroundColor(Color.rgb(186, 255, 201))
//            else itemView.cardView.setCardBackgroundColor(Color.rgb(255, 179, 186))
            if  (task.status) itemView.status.isChecked = true

            itemView.setOnClickListener{
                clickListener(task)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_single, parent, false)
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_single, parent, false)

        return TaskViewHolder(view)
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as TaskViewHolder).bind(taskList[position], clickListener)

    }
}