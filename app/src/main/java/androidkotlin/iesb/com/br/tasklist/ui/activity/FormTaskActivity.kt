package androidkotlin.iesb.com.br.tasklist.ui.activity

import android.app.DatePickerDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidkotlin.iesb.com.br.tasklist.extension.formatToBrazilian
import androidkotlin.iesb.com.br.tasklist.extension.parseToCalendar
import androidkotlin.iesb.com.br.tasklist.model.Task
import kotlinx.android.synthetic.main.activity_form_task.*
import java.util.*
import kotlin.iesb.com.br.tasklist.R

class FormTaskActivity : AppCompatActivity() {
    private var titleView: EditText? = null
    private var descriptionView: EditText? = null
    private var dateView: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_task)
        titleView = form_task_title
        descriptionView = form_task_description
        dateView = form_task_date
        configDateField()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.form_task_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.form_task_menu_save ->{
                saveTask()
                finish()
                return true
            }

        }
        return super.onOptionsItemSelected(item)
    }

    private fun saveTask() {
        val title = titleView?.text.toString()
        val description = descriptionView?.text.toString()
        val dateString = dateView?.text.toString()
        val date = dateString?.parseToCalendar()

        val task = Task(title = title,
                description = description, date = date)

    }

    private fun configDateField() {
        val today = Calendar.getInstance()

        val year = today.get(Calendar.YEAR)
        val month = today.get(Calendar.MONTH)
        val day = today.get(Calendar.DAY_OF_MONTH)

        dateView?.let {
            with(it){
                setText(today.formatToBrazilian())
                setOnClickListener {
                    DatePickerDialog(this@FormTaskActivity,
                            { _, year, month, day ->
                                val selectedDate = Calendar.getInstance()
                                selectedDate.set(year, month, day)
                                setText(selectedDate.formatToBrazilian())
                            }, year, month, day).show()
                }
            }
        }
    }
}
