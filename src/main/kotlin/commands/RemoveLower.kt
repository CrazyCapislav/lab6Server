package commands

import com.fasterxml.jackson.annotation.JsonTypeName
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import models.Flat

@JsonTypeName("removeLower")
@JacksonXmlRootElement(localName = "removeLower")
class RemoveLower : Command() {

    override val commandName: String = "remove_lower {element}"
    override fun writeString() {
        println("Удалить id ниже чем")
    }

    fun removeLower(collection: HashSet<Flat>, id: Long, arrayId: MutableList<Long>) {
        collection.removeIf { it.id < id }.also {
            if (it) arrayId.remove(id)
        }
    }
}