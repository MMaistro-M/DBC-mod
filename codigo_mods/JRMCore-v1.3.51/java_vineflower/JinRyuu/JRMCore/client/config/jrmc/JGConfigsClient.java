package JinRyuu.JRMCore.client.config.jrmc;

import JinRyuu.JRMCore.JRMCore;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import java.io.File;
import net.minecraftforge.common.config.Configuration;

public class JGConfigsClient {
   public static final String CONFIG_JRMC = "/jingames/jrmc/";
   public static Configuration clientSettings;
   public static Configuration clientNotifications;

   public static void preInitClient(FMLPreInitializationEvent event) {
      JRMCore.configDir_client = new File(event.getModConfigurationDirectory().getPath() + "/jingames/jrmc/" + "client_settings.cfg");
      clientSettings = new Configuration(JRMCore.configDir_client);
      JGConfigClientSettings.init(clientSettings);
      JRMCore.configDir_clientNotifications = new File(event.getModConfigurationDirectory().getPath() + "/jingames/jrmc/" + "client_notifications.cfg");
      clientNotifications = new Configuration(JRMCore.configDir_clientNotifications);
      JGConfigClientSettings.initNotifications(clientNotifications);
   }
}
