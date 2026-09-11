/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.audio.ISound
 *  net.minecraft.client.audio.PositionedSoundRecord
 *  net.minecraft.util.ResourceLocation
 */
package JinRyuu.JRMCore.client.notification;

import JinRyuu.JRMCore.JRMCoreCliTicH;
import JinRyuu.JRMCore.JRMCoreClient;
import JinRyuu.JRMCore.client.notification.JGNotification;
import JinRyuu.JRMCore.client.notification.JGNotificationGUI;
import JinRyuu.JRMCore.client.notification.JGNotificationHandler;
import net.minecraft.client.audio.ISound;
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
                JRMCoreClient.mc.func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_147674_a((ResourceLocation)new ResourceLocation("gui.button.press"), (float)2.0f));
                JRMCoreCliTicH.notificationPings.add(notification);
                JGNotificationHandler.hasNewContent = true;
            }
        }
    }
}

