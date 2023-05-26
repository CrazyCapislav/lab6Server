package commands

abstract class CommandServer {

    abstract val commandName: String
    abstract fun writeString()
    fun writeCommandName(){
        println(commandName)
    }
}