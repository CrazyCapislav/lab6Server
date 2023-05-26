package commands

import com.fasterxml.jackson.annotation.JsonTypeName
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import models.*

@JsonTypeName("updateById")
@JacksonXmlRootElement(localName = "updateById")
class UpdateById : Command() {
    /**
     * Изменяет элемент коллекции по ID
     */
    override val commandName: String = "update id {element}"
    override fun writeString() {
        println("Изменение элемента")
    }
    /**
     * Частично копирует команду Add
     */
    fun update(flats: HashSet<Flat>, id:Long ){
        val flat = flats.find{it.id == id}
        if (flat == null) {
            println("flat с id $id не найден.")
            return
        }
        println("Текущее имя: ${flat.name}, текущие координаты: ${flat.coordinates}, текущая площадь: ${flat.area}, текущее количество комнат: ${flat.numberOfRooms}, текущая жилплощадь: ${flat.livingSpace}, текущее расстояние до метро: ${flat.timeToMetroOnFoot}, текущая мебель: ${flat.furnish}, текущий дом:${flat.house}")
        print("Введите новое имя: ")

        val newName = readLine().toString()
        println("Введите новые координаты:x ")
        val newX = getLongInput()
        println("Введите новые координаты:y ")
        val newY = getLongInput()
        val newCoordinates = Coordinates(newX, newY)
        println("Введите новую площадь ")
        val newArea = getIntInput()
        println("Введите новое количество комнат")
        val newNumberOfRooms = getIntInput()
        println("Введите новую жилплощадь")
        val newLivingSpace = getDoubleInput()
        println("Введите новое расстояние до метро")
        val newTimeToMetroOnFoot = getIntInput()
        println("Введите нужна ли мебель(Да/Нет)")
        println("Нужен ли мебель?(Да/Нет)")
        var read = readLine().toString()
        val furnishFlag: Boolean
        while (true) {
            when (read) {
                "Да" -> {
                    furnishFlag = true; break
                }

                "Нет" -> {
                    furnishFlag = false; break
                }

                else -> {
                    println("Неправильное значение введите еще раз!(Да/Нет)"); read = readLine().toString()
                }
            }
        }
        val newFurnish: Furnish?
        if (furnishFlag){
            println("Введите наличие мебели (FINE, BAD, LITTLE)")
            read = readLine().toString()
            while (true) {
                read = when (read) {
                    "BAD" -> {
                        break
                    }

                    "FINE" -> {
                        break
                    }

                    "LITTLE" -> {
                        break
                    }

                    else -> {
                        println("Неправильное значение введите еще раз!(FINE, BAD, LITTLE"); readLine().toString()
                    }

                }
            }
            newFurnish = createFurnish(read)
        }
        else{
            newFurnish = null
        }
        println("Нужен ли дом?(Да/Нет)")
        read = readLine().toString()
        val houseFlag: Boolean
        while (true) {
            when (read) {
                "Да" -> {
                    houseFlag = true; break
                }

                "Нет" -> {
                    houseFlag = false; break
                }

                else -> {
                    println("Неправильное значение введите еще раз!(Да/Нет)"); read = readLine().toString()
                }
            }
        }
        val newHouse: House?
        if (houseFlag) {

            println("Введите параметры дома (название)")

            val houseName = readLine().toString()

            println("Введите параметры дома (год)")

            val houseYear = getLongInput()

            println("Введите параметры дома (количество лифтов)")

            val houseNumberOfLifts= getLongInput()
            newHouse = createHouse(houseName, houseYear, houseNumberOfLifts)
        }
        else{
            newHouse = null
        }
        flat.name = newName
        flat.coordinates = newCoordinates
        flat.timeToMetroOnFoot = newTimeToMetroOnFoot
        flat.furnish = newFurnish
        flat.house = newHouse
        flat.area = newArea
        flat.numberOfRooms = newNumberOfRooms
        flat.livingSpace = newLivingSpace

        println("Изменения сохранены")
    }
}