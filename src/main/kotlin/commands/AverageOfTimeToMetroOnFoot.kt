package commands

import com.fasterxml.jackson.annotation.JsonTypeName
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import models.Flat
@JsonTypeName("average_of_time_to_metro_on_foot")
@JacksonXmlRootElement(localName = "average_of_time_to_metro_on_foot")
class AverageOfTimeToMetroOnFoot : Command() {

    override val commandName: String = "average_of_time_to_metro_on_foot"
    override fun writeString() {
        println("Среднее время до метро")
    }
    fun averageMetro(collection: HashSet<Flat>): String {
        return collection.map { it.timeToMetroOnFoot }.average().toString()

    }
}