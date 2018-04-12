package androidkotlin.iesb.com.br.tasklist.ui.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import androidkotlin.iesb.com.br.tasklist.model.ListType
import kotlinx.android.synthetic.main.activity_task_list.*
import java.util.*
import androidkotlin.iesb.com.br.tasklist.model.Task
import androidkotlin.iesb.com.br.tasklist.ui.adapter.TaskAdapter
import kotlin.iesb.com.br.tasklist.R

class TaskListActivity : AppCompatActivity(), View.OnClickListener {

    private var recyclerView: RecyclerView? = null
    private var addButton: ImageButton? = null
    private var listType: ListType? = ListType.LIST

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_list)
        recyclerView = task_list
        addButton = task_list_add_button
        addButton?.setOnClickListener(this)

    }

    override fun onResume() {
        super.onResume()
        configList()
    }

    private fun configList() {
        recyclerView?.adapter = TaskAdapter(list())
        adapterBy(listType)
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.task_list_add_button ->{
                startActivity(Intent(TaskListActivity@this, FormTaskActivity::class.java))
                return
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.task_list_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.task_list_menu_list ->{
                adapterBy(ListType.LIST)
                return true
            }
            R.id.task_list_menu_grid ->{
                adapterBy(ListType.GRID)
                return true
            }
            R.id.task_list_menu_note ->{
                adapterBy(ListType.NOTE)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun adapterBy(type: ListType?) {
        listType = type
        when(type){
            ListType.LIST ->{
                recyclerView?.layoutManager = LinearLayoutManager(this)
                return
            }
            ListType.GRID ->{
                recyclerView?.layoutManager = GridLayoutManager(this,2)
                return
            }
            ListType.NOTE ->{
                recyclerView?.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                return
            }
        }
    }

    private fun list(): MutableList<Task> {
        var list = mutableListOf<Task>()
        list.add(Task(title = "Tarefa 1", description = "Descricao teste da tarefa", date = Calendar.getInstance()))
        list.add(Task(title = "Tarefa 2", description = "Descricao teste da tarefa 2 para verificar o tamanho da nota", date = Calendar.getInstance()))
        list.add(Task(title = "Tarefa 3", description = "Descricao", date = Calendar.getInstance()))
        list.add(Task(title = "Tarefa 4", description = "Desc", date = Calendar.getInstance()))
        list.add(Task(title = "Tarefa 5", description = "Descricao muito louca para verificar o quanto esse layout é massa haha", date = Calendar.getInstance()))
        return list
    }
}
