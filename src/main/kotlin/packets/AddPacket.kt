package packets

import commands.AddClient
import models.Flat

// Создаем класс-контейнер для объектов AddClient и Flat
data class AddPacket(val addClient: AddClient, val flat: Flat) : PackageData()

