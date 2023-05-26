package commands

import com.fasterxml.jackson.annotation.JsonTypeName
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import models.Flat

/**
 * Уникальные значения времени до метро
 */
@JsonTypeName("print_unique_time_to_metro_on_foot")
@JacksonXmlRootElement(localName = "print_unique_time_to_metro_on_foot")
class PrintUniqueTimeToMetroOnFoot : Command() {

    override val commandName: String = "print_unique_time_to_metro_on_foot"
    override fun writeString() {
        println("Уникальные расстояния до метро")
    }
    fun uniqueTime(collection: HashSet<Flat>): String {
        return collection.map { it.timeToMetroOnFoot }.distinct().toString()
    }
}