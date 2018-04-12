package androidkotlin.iesb.com.br.tasklist.model

import java.util.*

class Task(val title: String = "Tarefa Indefinida",
           val description: String?,
           val date: Calendar = Calendar.getInstance()) {
}