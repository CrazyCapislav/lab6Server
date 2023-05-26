package commands

import com.fasterxml.jackson.annotation.JsonTypeName
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import models.Flat
@JsonTypeName("addIfMax")
@JacksonXmlRootElement(localName = "addIfMax")
class AddIfMax : Command() {
    override val commandName: String = "add_if_max {element}"
    override fun writeString() {
        println("Добавить элемент если id больше максимального")
    }
    fun addIfM(flats: HashSet<Flat>, id: Long, arrayId : MutableList<Long>) {
        val maxId = flats.maxByOrNull { it.id }?.id ?: 0
        if (id > maxId) {
            val add = AddClient()
            val newFlat = add.start(flats, arrayId)
            println("Элемент $newFlat добавлен в коллекцию")
        } else {
            println("Значение ID элемента меньше максимального значения ID в коллекции")
        }
    }
}