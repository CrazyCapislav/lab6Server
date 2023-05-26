import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator
import com.fasterxml.jackson.module.kotlin.readValue
import commands.*
import models.Flat
import java.io.*
import java.net.ServerSocket
import java.util.logging.Logger

class Server(private val port: Int) {

    private val logger: Logger = Logger.getLogger(Server::class.java.name)

    fun start() {
        logger.info("Запуск сервера на порту $port...")
        val serverSocket = ServerSocket(port)
        logger.info("Сервер запущен.")
        var arrayId: MutableList<Long> = mutableListOf()
        val collectionFolder: String = "file1.txt"
        var file = File(collectionFolder)
        /**
         * Проверка файла с данными коллекции
         */
        if (!file.exists()) {
            logger.info("Файл не найден: $collectionFolder")
            file = File("file1.txt")
            file.createNewFile()
            logger.info("Создан новый файл: file1.txt")
            logger.info("Запись в файл: file1.txt")
            val outputStream = FileOutputStream(file)
            val hashSet = HashSet<Flat>()
            val xmlMapper = XmlMapper.builder().build()
            xmlMapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true)
            val xmlCollection = xmlMapper.writeValueAsString(hashSet)
            outputStream.write(xmlCollection.toByteArray())
            outputStream.close()

            logger.info("Файл с данными коллекции создан и инициализирован.")
        } else {
            logger.info("Файл с данными коллекции найден: $collectionFolder")
        }

        val xmlMapper1 = XmlMapper.builder().build()
        val xmlHashSet: HashSet<Flat> = xmlMapper1.readValue(file, object : TypeReference<HashSet<Flat>>() {})
        arrayId.addAll(xmlHashSet.map { it.id })
//        println(arrayId.toString())

        while (true) {
            val clientSocket = serverSocket.accept()
            logger.info("Новое подключение: ${clientSocket.inetAddress.hostAddress}")

            val reader = BufferedReader(InputStreamReader(clientSocket.getInputStream()))
            val writer = PrintWriter(clientSocket.getOutputStream(), true)

            while (true) {
                val request = reader.readLine()
                logger.info("Получен запрос от клиента: $request")

                // Обработка запроса и формирование ответа
                var response = ""
                val xmlMapper2 = XmlMapper()
                val requestObj = xmlMapper2.readValue<Command>(request)

                when (requestObj) {
                    is HelpClient -> {
                        response = requestObj.toString()
                    }
                    is ShowClient -> {
                        response = requestObj.showString(xmlHashSet)
                    }
                    is PrintUniqueTimeToMetroOnFoot -> {
                        response = requestObj.uniqueTime(xmlHashSet)
                    }
                    is AverageOfTimeToMetroOnFoot -> {
                        response = requestObj.averageMetro(xmlHashSet)
                    }
                    is ClearClient -> {
                        xmlHashSet.clear()
                        arrayId.clear()
                        response = "Коллекция успешно очищена"
                    }
                    is RemoveLower -> {
                        val requestId = reader.readLine()
                        val xmlMapper3 = XmlMapper()
                        val id = xmlMapper3.readValue(requestId, String::class.java).toLong()
                        val removeLower = RemoveLower()
                        removeLower.removeLower(xmlHashSet, id, arrayId)
                        response = "Элементы с id меньше чем $id удалены"
                    }
                    is RemoveGreater -> {
                        val requestId = reader.readLine()
                        val xmlMapper3 = XmlMapper()
                        val id = xmlMapper3.readValue(requestId, String::class.java).toLong()
                        val removeGreater = RemoveGreater()
                        removeGreater.removeGreater(xmlHashSet, id, arrayId)
                        response = "Элементы с id больше чем $id удалены"
                    }
                    is AddClient -> {
                        val requestFlat = reader.readLine()
                        val xmlMapper3 = XmlMapper()
                        val requestFlat1 = xmlMapper3.readValue<Flat>(requestFlat)

                        if (arrayId.contains(requestFlat1.id)) {
                            response = "Ошибка: объект с указанным id уже существует"
                        } else {
                            xmlHashSet.add(requestFlat1)
                            arrayId.add(requestFlat1.id)
                            val show = ShowClient()
                            show.showElement(xmlHashSet)
                            response = "Объект добавлен в коллекцию"
                        }
                    }

                    is AddIfMax -> {
                        val requestFlat = reader.readLine()
                        val xmlMapper3 = XmlMapper()
                        val requestFlat1 = xmlMapper3.readValue<Flat>(requestFlat)
                        val maxId = xmlHashSet.maxByOrNull { it.id }?.id ?: 0
                        if (requestFlat1.id > maxId) {
                            xmlHashSet.add(requestFlat1)
                            arrayId.add(requestFlat1.id)
                            response = "Элемент добавлен"
                        } else {
                            response = "Id меньше максимального"
                        }
                    }
                    is RemoveByIdClient -> {
                        val requestId = reader.readLine()
                        val xmlMapper3 = XmlMapper()
                        val id = xmlMapper3.readValue(requestId, String::class.java).toLong()

                        if (arrayId.contains(id)) {
                            val removeByIdClient = RemoveByIdClient()
                            removeByIdClient.removeById1(xmlHashSet, id)
                            arrayId.remove(id)
                            response = "Объект удален"

                        } else {
                            response = "Объект не найден"
                        }
                    }
                    is RemoveAllByNumberOfRooms -> {
                        val requestNOR = reader.readLine()
                        val xmlMapper3 = XmlMapper()
                        val NOR = xmlMapper3.readValue(requestNOR, String::class.java).toInt()
                        val removeAllByNumberOfRooms = RemoveAllByNumberOfRooms()
                        removeAllByNumberOfRooms.removeByRooms(xmlHashSet, NOR, arrayId)
                        response = "Объекты с $NOR комнатами удалены"
                    }
                    is UpdateById -> {
                        val requestId = reader.readLine()
                        val xmlMapper3 = XmlMapper()
                        val id = xmlMapper3.readValue(requestId, String::class.java).toLong()
                        val requestFlat = reader.readLine()
                        val xmlMapper = XmlMapper()
                        val requestFlat1 = xmlMapper.readValue<Flat>(requestFlat)

                        if (arrayId.contains(id)) {
                            val removeByIdClient = RemoveByIdClient()
                            removeByIdClient.removeById1(xmlHashSet, id)
                            arrayId.remove(id)
                            xmlHashSet.add(requestFlat1)
                            arrayId.add(requestFlat1.id)
                            response = "Объект изменён"

                        } else {
                            response = "Объект не найден"
                        }
                    }
                    else -> {
                        // Обработка других типов объектов
                        logger.info("Обработка объекта неизвестного типа: $requestObj")
                        response = "Обработка объекта неизвестного типа"
                    }
                }

                val message = Message(response)
                val xmlResponse = message.toXml()
                writer.println(xmlResponse)
                logger.info("Отправлен ответ клиенту: $xmlResponse")

                if (request == "exit") {
                    break
                }
            }

            reader.close()
            writer.close()
            clientSocket.close()
        }
    }
}

fun main() {
    val serverPort = 8080

    val server = Server(serverPort)
    server.start()
}