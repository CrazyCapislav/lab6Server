package commands


import com.fasterxml.jackson.annotation.JsonTypeName
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import models.Flat

/**
 * Один из классов команд
 */
@JsonTypeName("show")
@JacksonXmlRootElement(localName = "show")
class ShowClient : Command() {

    override val commandName: String = "show"
    override fun writeString() {
        println("Имена элементов коллекции")
    }

    fun showElement(collection: HashSet<Flat>) {
        if (collection.isEmpty()) {
            println("Коллекция пуста")
        } else {
            collection.forEach { el ->
                println(el.toString())
            }
        }
    }
    fun showString(collection: HashSet<Flat>): String {
        val stringBuilder = StringBuilder()

        if (collection.isEmpty()) {
            stringBuilder.append("Коллекция пуста")
        } else {
            collection.forEach { el ->
                stringBuilder.append(el.toString()).append(" |||")
            }
        }

        return stringBuilder.toString()
    }
}
