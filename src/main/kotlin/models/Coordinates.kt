package models
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement

//@Serializable
@JacksonXmlRootElement(localName = "coordinates")
class Coordinates (
    @JacksonXmlProperty(localName = "coordinatesX")
    val x: Long = 0,
    @JacksonXmlProperty(localName = "coordinatesY")
    val y: Long = 0
) {
    override fun toString(): String {
        return "models.Coordinates(x=$x, y=$y)"
    }
}

//x Поле не может быть null
//y Значение поля должно быть больше -566