package packets

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement

/**
 * Абстрактный класс для пакетов данных.
 */
@JacksonXmlRootElement(localName = "PackageData")
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "packageType"
)
@JsonSubTypes(
    JsonSubTypes.Type(value = AddPacket::class, name = "add"),
    JsonSubTypes.Type(value = HelpPacket::class, name = "help")
)
abstract class PackageData() {
}