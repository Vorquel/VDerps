package vorquel.mod.vderps.event;

import vorquel.mod.vderps.helper.Log;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class LandingPadHandler {
	
	@SubscribeEvent
	public void detectFallDamage(LivingHurtEvent event) {
		if(event.source != DamageSource.fall) return;
		event.setCanceled(true);
		Log.info("Fall Event");
		double x = event.entity.posX;
		double y = event.entity.posY;
		double z = event.entity.posZ;
		Log.info(y);
		Log.info("In");
		Log.info(event.entity.worldObj.getBlock((int)Math.floor(x), (int)Math.floor(y), (int)Math.floor(z)).getLocalizedName());
		Log.info("On");
		Log.info(event.entity.worldObj.getBlock((int)Math.floor(x), (int)Math.floor(y-1), (int)Math.floor(z)).getLocalizedName());
	}
}
