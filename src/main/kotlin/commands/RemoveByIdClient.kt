package commands
import com.fasterxml.jackson.annotation.JsonTypeName
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import models.Flat
/**
 * Один из классов команд
 */
@JsonTypeName("removeById")
@JacksonXmlRootElement(localName = "removeById")
class RemoveByIdClient : Command() {

    override val commandName: String = "remove_by_id id"
    override fun writeString() {
        println("Удалить по id")
    }

    fun removeById(collection: HashSet<Flat>, id: Long) {
        val removedFlat = collection.firstOrNull { it.id == id }
        if (removedFlat != null) {
            collection.remove(removedFlat)
            println("Элемент с ID $id успешно удален из коллекции")
        } else {
            println("Элемент с ID $id не найден в коллекции")
        }
    }
    fun removeById1(collection: HashSet<Flat>, id: Long): String {
        val removedFlat = collection.firstOrNull { it.id == id }
        if (removedFlat != null) {
            collection.remove(removedFlat)
            return "Элемент с ID $id успешно удален из коллекции"
        } else {
            return "Элемент с ID $id не найден в коллекции"
        }
    }
}