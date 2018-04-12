package androidkotlin.iesb.com.br.tasklist.extension

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by admin on 12/04/2018.
 */
fun String.parseToCalendar(): Calendar{
    val brazilianFormat = SimpleDateFormat("dd/MM/yyyy")
    val convertDate = brazilianFormat.parse(this)
    val date = Calendar.getInstance()
    date.time = convertDate
    return date
}