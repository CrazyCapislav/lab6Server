package packets

import commands.HelpClient

data class HelpPacket(val help: HelpClient) : PackageData() {
    constructor() : this(HelpClient())
}