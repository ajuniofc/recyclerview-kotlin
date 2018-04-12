package androidkotlin.iesb.com.br.tasklist.extension
import java.util.*


fun Calendar.formatToBrazilian(): String{
    val format = java.text.SimpleDateFormat("dd/MM/yyy")
    return format.format(this.time)
}
