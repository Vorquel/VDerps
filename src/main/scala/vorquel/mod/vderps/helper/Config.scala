package vorquel.mod.vderps.helper

import java.io.File

import net.minecraftforge.common.config.Configuration

object Config {

  var config: Configuration = null

  def init(file: File) {
    Log.trace("Config.init()")
    config = new Configuration(file)
    try {
      config.load()
    }
    catch {
      case e: Exception => {
        throw new RuntimeException("Configuration loading failure")
      }
    }
    finally {
      config.save()
    }
  }
}
