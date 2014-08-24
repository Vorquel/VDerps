package vorquel.mod.vderps.handler

import cpw.mods.fml.common.eventhandler.SubscribeEvent
import net.minecraft.util.DamageSource
import net.minecraftforge.event.entity.living.LivingHurtEvent
import vorquel.mod.vderps.helper.Log

object LandingPadHandler {

  @SubscribeEvent
  def detectFallDamage(event: LivingHurtEvent) {
    if(event.source ne DamageSource.fall) return
    event.setCanceled(true)
    Log.info("Fall Event")
    val x: Double = event.entity.posX
    val y: Double = event.entity.posY
    val z: Double = event.entity.posZ
    Log.info(y)
    Log.info("In")
    Log.info(event.entity.worldObj.getBlock(Math.floor(x).asInstanceOf[Int], Math.floor(y).asInstanceOf[Int], Math.floor(z).asInstanceOf[Int]).getLocalizedName)
    Log.info("On")
    Log.info(event.entity.worldObj.getBlock(Math.floor(x).asInstanceOf[Int], Math.floor(y - 1).asInstanceOf[Int], Math.floor(z).asInstanceOf[Int]).getLocalizedName)
  }
}
