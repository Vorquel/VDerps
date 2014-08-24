package vorquel.mod.vderps.helper

import cpw.mods.fml.common.FMLLog
import org.apache.logging.log4j.Level

object Log {

  private def log(level: Level, obj: Any) {
    FMLLog.log(Ref.MOD_ID, level, String.valueOf(obj))
  }

  def fatal(obj: Any) {
    log(Level.FATAL, obj)
  }

  def error(obj: Any) {
    log(Level.ERROR, obj)
  }

  def warn(obj: Any) {
    log(Level.WARN, obj)
  }

  def info(obj: Any) {
    log(Level.INFO, obj)
  }

  def debug(obj: Any) {
    log(Level.DEBUG, obj)
  }

  def trace(obj: Any) {
    log(Level.TRACE, obj)
  }
}
