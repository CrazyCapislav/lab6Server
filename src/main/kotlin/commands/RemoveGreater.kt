package commands

import com.fasterxml.jackson.annotation.JsonTypeName
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import models.Flat

@JsonTypeName("removeGreater")
@JacksonXmlRootElement(localName = "removeGreater")
class RemoveGreater : Command() {

    override val commandName: String = "remove_lower {element}"
    override fun writeString() {
        println("Удалить id ниже чем")
    }

    fun removeGreater(collection: HashSet<Flat>, id: Long, arrayId: MutableList<Long>) {
        collection.removeIf { it.id > id }.also {
            if (it) arrayId.remove(id)
        }
    }
}