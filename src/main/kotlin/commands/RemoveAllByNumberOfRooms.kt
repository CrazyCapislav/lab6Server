package commands

import com.fasterxml.jackson.annotation.JsonTypeName
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import models.Flat
@JsonTypeName("remove_all_by_number_of_rooms")
@JacksonXmlRootElement(localName = "remove_all_by_number_of_rooms")
class RemoveAllByNumberOfRooms : Command() {

    override val commandName: String = "remove_all_by_number_of_rooms"
    override fun writeString() {
        println("Удалить все с числом комнат")
    }
    fun removeByRooms(collection: HashSet<Flat>, numberOfRooms: Int, arrayId: MutableList<Long>){
        val toRemove = collection.filter { it.numberOfRooms == numberOfRooms }
        toRemove.forEach { flat ->
            if (collection.remove(flat)) {
                arrayId.remove(flat.id)
            }
        }
    }
}