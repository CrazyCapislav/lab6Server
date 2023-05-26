package models

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement

@JacksonXmlRootElement(localName = "house")
class House(
    @JacksonXmlProperty(localName = "houseName")
    val name: String? = "null",
    @JacksonXmlProperty(localName = "houseYear")
    val year: Long = -1,
    @JacksonXmlProperty(localName = "houseNumberOfLifts")
    val numberOfLifts: Long = -1
) {
    override fun toString(): String {
        return "House(name='$name', year=$year, numberOfLifts=$numberOfLifts)"
    }
}
//name Поле не может быть null
//year Значение поля должно быть больше 0
//numberOfLifts Значение поля должно быть больше 0