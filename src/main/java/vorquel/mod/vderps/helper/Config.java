package vorquel.mod.vderps.helper;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class Config {
	
	public static Configuration config;
	
	public static void init(File file) {
		Log.trace("Config.init()");
		config = new Configuration(file);
		try {
			config.load();
		} catch(Exception e) {
			throw new RuntimeException("Configuration loading failure");
		} finally {
			config.save();
		}
	}
}
