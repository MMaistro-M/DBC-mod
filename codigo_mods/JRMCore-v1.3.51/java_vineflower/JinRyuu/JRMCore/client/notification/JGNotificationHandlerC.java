package JinRyuu.JRMCore.client.notification;

import JinRyuu.JRMCore.JRMCoreCliTicH;
import JinRyuu.JRMCore.JRMCoreClient;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.util.ResourceLocation;

public class JGNotificationHandlerC {
   public static void addNotification(JGNotification notification) {
      if (notification != null && JGNotificationHandler.isRightCategory(notification)) {
         int count = 1;
         if (JGNotificationHandler.notificationsCount.containsKey(notification.category)) {
            count = JGNotificationHandler.notificationsCount.get(notification.category) + 1;
            JGNotificationHandler.notificationsCount.put(notification.category, count);
         } else {
            JGNotificationHandler.notificationsCount.put(notification.category, count);
         }

         JGNotificationHandler.notifications.put(JGNotificationHandler.createKey(notification.category, count), notification);
         if (JGNotificationGUI.categoryState[notification.category] != 1 && JGNotificationGUI.categoryState[0] != 1) {
            JRMCoreClient.mc.func_147118_V().func_147682_a(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 2.0F));
            JRMCoreCliTicH.notificationPings.add(notification);
            JGNotificationHandler.hasNewContent = true;
         }
      }
   }
}
