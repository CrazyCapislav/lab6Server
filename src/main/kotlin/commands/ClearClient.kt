package commands

import com.fasterxml.jackson.annotation.JsonTypeName
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator
import models.Flat
import java.io.File
import java.io.FileOutputStream
/**
 * Очищает коллекцию
 */
@JsonTypeName("clear")
@JacksonXmlRootElement(localName = "clear")
class ClearClient : Command() {

    override val commandName: String = "clear"
    override fun writeString() {
        println("Очистка коллекции")
    }
    fun clear(folder: String) {
        val outputStream = FileOutputStream(File(folder))
        val hashSet = HashSet<Flat>()
        val xmlMapper = XmlMapper.builder().build()
        xmlMapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true)
        val xmlCollecion = xmlMapper.writeValueAsString(hashSet)
        outputStream.write(xmlCollecion.toByteArray())
        /**
         * Фактически идет перезапись коллекции на пустую
         */
        outputStream.close()
    }
}