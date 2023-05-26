import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement

@JacksonXmlRootElement(localName = "message")
class Message(private val content: String) {
    private val xmlMapper: XmlMapper = XmlMapper()
    constructor() : this("")
    fun getContent(): String {
        return content
    }

    fun toXml(): String {
        return xmlMapper.writeValueAsString(this)
    }
}