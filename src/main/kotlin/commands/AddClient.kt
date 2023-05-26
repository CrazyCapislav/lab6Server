package commands

import com.fasterxml.jackson.annotation.JsonTypeName
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import models.Coordinates
import models.Flat
import models.Furnish
import models.House

/**
 * Класс, реализующий команду добавления элемента.
 *
 * @since 1.0
 */
@JacksonXmlRootElement(localName = "Add")
@JsonTypeName("add")
class AddClient : Command() {
    /**
     * Название команды.
     */
    override val commandName: String = "add {element}"

    /**
     * Вывод строки при запуске команды.
     */
    override fun writeString() {
        println("Введите имя элемента")
    }

    /**
     * Создание нового элемента и добавление его в хранилище.
     *
     * @param hashSet Хранилище элементов.
     * @param arrayId Массив идентификаторов элементов.
     * @return Хранилище элементов с добавленным новым элементом.
     */
    fun start(hashSet: HashSet<Flat>, arrayId: MutableList<Long>): HashSet<Flat> {
        addElement(hashSet, createElement(arrayId))
        return hashSet
    }

    /**
     * Создание нового элемента на основе введенных пользователем данных.
     *
     * @param arrayId Массив идентификаторов элементов.
     * @return Новый элемент.
     */
    fun createElement(arrayId: MutableList<Long>): Flat {
        println("Генерация id")
        var i: Long = 1
        while (arrayId.contains(i)) {
            i++
        }
        val id = i
        println("Ваш ID $id")
        println("Введите имя")
        var read: String = readLine().toString()
        val name: String = nullBlankCheck(read)
        println("Введите координаты (x)")
        val x = getLongInput()
        println("Введите координаты (y)")
        read = getLongInput().toString()
        while (read.toLong() <= -566) {
            println("Должно быть больше -566!")
            read = getLongInput().toString()
        }
        val y = read.toLong()
        println("Введите площадь")
        read = getIntInput().toString()
        while (read.toInt() <= 0) {
            println("Должно быть больше 0!")
            read = getIntInput().toString()
        }
        val area = read.toInt()
        println("Введите количество комнат")
        read = getIntInput().toString()
        while (read.toInt() <= 0) {
            println("Должно быть больше 0!")
            read = getIntInput().toString()
        }
        val numberOfRooms = read.toInt()
        //
        println("Введите жилую площадь (десятичная дробь)")
        read = getDoubleInput().toString()
        while (read.toDouble() <= 0) {
            println("Должно быть больше 0!")
            read = getLongInput().toString()
        }
        val livingSpace = read.toDouble()
        //
        println("Введите расстояние до метро в футах")
        read = getIntInput().toString()
        while (read.toInt() <= 0) {
            println("Должно быть больше 0!")
            read = getLongInput().toString()
        }
        val timeToMetroOnFoot = read.toInt()
        println("Нужен ли мебель?(Да/Нет)")
        read = readLine().toString()
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
        val furnish: Furnish?
        if (furnishFlag) {
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
            furnish = createFurnish(read)
        } else {
            furnish = null
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
        val house: House?
        if (houseFlag) {

            println("Введите параметры дома (название)")

            val houseName = readLine().toString()

            println("Введите параметры дома (год)")

            val houseYear = getLongInput()

            println("Введите параметры дома (количество лифтов)")

            val houseNumberOfLifts = getLongInput()
            house = createHouse(houseName, houseYear, houseNumberOfLifts)
        } else {
            house = null
        }
        return Flat(
            id,
            name,
            createCoordinates(x, y),
            area,
            numberOfRooms,
            livingSpace,
            timeToMetroOnFoot,
            furnish,
            house
        )
    }


    fun createElement2(): Flat {
        println("Введите id")
        val id = getLongInput()
        println("Введите имя")
        var read: String = readLine().toString()
        val name: String = nullBlankCheck(read)
        println("Введите координаты (x)")
        val x = getLongInput()
        println("Введите координаты (y)")
        read = getLongInput().toString()
        while (read.toLong() <= -566) {
            println("Должно быть больше -566!")
            read = getLongInput().toString()
        }
        val y = read.toLong()
        println("Введите площадь")
        read = getIntInput().toString()
        while (read.toInt() <= 0) {
            println("Должно быть больше 0!")
            read = getIntInput().toString()
        }
        val area = read.toInt()
        println("Введите количество комнат")
        read = getIntInput().toString()
        while (read.toInt() <= 0) {
            println("Должно быть больше 0!")
            read = getIntInput().toString()
        }
        val numberOfRooms = read.toInt()
        //
        println("Введите жилую площадь (десятичная дробь)")
        read = getDoubleInput().toString()
        while (read.toDouble() <= 0) {
            println("Должно быть больше 0!")
            read = getLongInput().toString()
        }
        val livingSpace = read.toDouble()
        //
        println("Введите расстояние до метро в футах")
        read = getIntInput().toString()
        while (read.toInt() <= 0) {
            println("Должно быть больше 0!")
            read = getLongInput().toString()
        }
        val timeToMetroOnFoot = read.toInt()
        println("Нужен ли мебель?(Да/Нет)")
        read = readLine().toString()
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
        val furnish: Furnish?
        if (furnishFlag) {
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
            furnish = createFurnish(read)
        } else {
            furnish = null
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
        val house: House?
        if (houseFlag) {

            println("Введите параметры дома (название)")

            val houseName = readLine().toString()

            println("Введите параметры дома (год)")

            val houseYear = getLongInput()

            println("Введите параметры дома (количество лифтов)")

            val houseNumberOfLifts = getLongInput()
            house = createHouse(houseName, houseYear, houseNumberOfLifts)
        } else {
            house = null
        }
        return Flat(
            id,
            name,
            createCoordinates(x, y),
            area,
            numberOfRooms,
            livingSpace,
            timeToMetroOnFoot,
            furnish,
            house
        )
    }
}

/**
 * Проверка строки на наличие пустых символов.
 *
 * @param string Входная строка.
 * @return Строка без пустых символов.
 */
private fun nullBlankCheck(string: String): String {
    var string1 = string
    while (string1.isBlank()) {
        println("Пустая строка! Введите значение")
        string1 = readLine().toString()
    }
    return string1
}

/**
 * Создание объекта типа Furnish на основе строки.
 *
 * @param string Строка с наличием мебели.
 * @return Объект типа Furnish.
 */
fun createFurnish(string: String): Furnish {
    return when (string) {
        "FINE" -> Furnish.FINE
        "BAD" -> Furnish.BAD
        "LITTLE" -> Furnish.LITTLE
        else -> {
            println("WTF"); Furnish.BAD
        }
    }
}

/**
 * Создание объекта типа Coordinates.
 *
 * @param x Координата x.
 * @param y Координата y.
 * @return Объект типа Coordinates.
 */
private fun createCoordinates(x: Long, y: Long): Coordinates {
    return Coordinates(x, y)
}

/**
 * Создание объекта типа House на основе переданных параметров.
 *
 * @param name Название дома.
 * @param year Год постройки дома.
 * @param numberOfLifts Количество лифтов в доме.
 * @return Объект типа House.
 */
fun createHouse(name: String, year: Long, numberOfLifts: Long): House {
    return House(name, year, numberOfLifts)
}

/**
 * Добавление элемента в HashSet.
 *
 * @param hashSet HashSet, в который нужно добавить элемент.
 * @param element Добавляемый элемент.
 */
private fun addElement(hashSet: HashSet<Flat>, element: Flat) {
    hashSet.add(element)
}

/**
 * Получение ввода от пользователя в формате Long.
 *
 * @return Введенное пользователем значение в формате Long.
 */
fun getLongInput(): Long {
    while (true) {
        try {
            val input = readLine()!!
            return input.toLong()
        } catch (e: NumberFormatException) {
            println("Неверный формат числа, попробуйте еще раз")
        }
    }
}

/**
 * Получение ввода от пользователя в формате Int.
 *
 * @return Введенное пользователем значение в формате Int.
 */
fun getIntInput(): Int {
    while (true) {
        try {
            val input = readLine()!!
            return input.toInt()
        } catch (e: NumberFormatException) {
            println("Неверный формат числа, попробуйте еще раз")
        }
    }
}

/**
 * Получение ввода от пользователя в формате Double.
 *
 * @return Введенное пользователем значение в формате Double.
 */
fun getDoubleInput(): Double {
    while (true) {
        try {
            val input = readLine()!!
            return input.toDouble()
        } catch (e: NumberFormatException) {
            println("Неверный формат числа, попробуйте еще раз")
        }
    }
}