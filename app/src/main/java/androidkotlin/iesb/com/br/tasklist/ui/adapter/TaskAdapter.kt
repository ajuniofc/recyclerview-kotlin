package androidkotlin.iesb.com.br.tasklist.ui.adapter

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.task_list_item.view.*
import androidkotlin.iesb.com.br.tasklist.extension.formatToBrazilian
import androidkotlin.iesb.com.br.tasklist.model.Task
import kotlin.iesb.com.br.tasklist.R

class TaskAdapter(private val tasks: MutableList<Task>): Adapter<TaskAdapter.TaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.task_list_item, parent, false)
        return TaskViewHolder(view)
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    override fun onBindViewHolder(holder: TaskViewHolder?, position: Int) {
        val task = tasks.get(position)
        holder?.let {
            it.bindView(task)
        }
    }

    class TaskViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val title = itemView.task_item_title
        val description = itemView.task_item_description
        val date = itemView.task_item_date

        fun bindView(task: Task){
            title.text = task.title
            description.text = task.description
            date.text = task.date.formatToBrazilian()
        }
    }
}