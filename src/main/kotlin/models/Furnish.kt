package models

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText

@JacksonXmlRootElement(localName = "furnish")
enum class Furnish {
    @JacksonXmlText
    FINE,
    @JacksonXmlText
    BAD,
    @JacksonXmlText
    LITTLE
}