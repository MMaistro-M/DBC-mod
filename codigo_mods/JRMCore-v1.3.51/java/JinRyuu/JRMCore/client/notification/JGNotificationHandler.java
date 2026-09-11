/*
 * Decompiled with CFR 0.152.
 */
package JinRyuu.JRMCore.client.notification;

import JinRyuu.JRMCore.client.notification.JGNotification;
import JinRyuu.JRMCore.client.notification.JGNotificationHandlerC;
import java.util.ArrayList;
import java.util.HashMap;

public class JGNotificationHandler {
    public static final byte[] CATEGORY_IDS_ALL = new byte[]{0, 1, 2, 3, 4, 5};
    public static final String[] CATEGORY_TEXTS_ALL = new String[]{"All", "Info", "Error", "Server", "Tutorial", "Other"};
    public static final byte[] CATEGORY_IDS = new byte[]{1, 2, 3, 4, 5};
    public static final String[] CATEGORY_TEXTS = new String[]{"Info", "Error", "Server", "Tutorial", "Other"};
    public static HashMap<String, JGNotification> notifications = new HashMap();
    public static HashMap<Byte, Integer> notificationsCount = new HashMap();
    public static boolean hasNewContent = false;

    public static String getCategoryText(int category) {
        return CATEGORY_TEXTS[category];
    }

    public static int getCategoryID(String category) {
        int length = CATEGORY_TEXTS.length;
        for (int i = 0; i < length; ++i) {
            if (!category.toLowerCase().equals(CATEGORY_TEXTS[i].toLowerCase())) continue;
            return i;
        }
        return 0;
    }

    public static String getCategoryTextAll(int category) {
        return CATEGORY_TEXTS_ALL[category];
    }

    public static int getCategoryIDAll(String category) {
        int length = CATEGORY_TEXTS_ALL.length;
        for (int i = 0; i < length; ++i) {
            if (!category.toLowerCase().equals(CATEGORY_TEXTS_ALL[i].toLowerCase())) continue;
            return i;
        }
        return 0;
    }

    public static ArrayList<String> getAllNotificationsArray() {
        ArrayList<String> list = new ArrayList<String>();
        for (byte currentCategory : CATEGORY_IDS) {
            if (!notificationsCount.containsKey(currentCategory)) continue;
            int size = notificationsCount.get(currentCategory);
            for (int i = 0; i < size; ++i) {
                list.add(JGNotificationHandler.createKey(currentCategory, i + 1));
            }
        }
        return list;
    }

    public static ArrayList<String> getTimelineArray(boolean sortNewestToOldest) {
        int i;
        ArrayList<String> timelineArray = new ArrayList<String>();
        ArrayList<String> all = JGNotificationHandler.getAllNotificationsArray();
        int length = all.size();
        String[] timeline = new String[length];
        for (i = 0; i < length; ++i) {
            timeline[i] = all.get(i);
        }
        for (i = 0; i < length; ++i) {
            for (int j = 0; j < length; ++j) {
                if (!JGNotificationHandler.notifications.get((Object)timeline[i]).received.isBefore(JGNotificationHandler.notifications.get((Object)timeline[j]).received)) continue;
                String keyI = timeline[i];
                timeline[i] = timeline[j];
                timeline[j] = keyI;
            }
        }
        length = timeline.length;
        int n = i = sortNewestToOldest ? length - 1 : 0;
        while (sortNewestToOldest ? i >= 0 : i < length) {
            timelineArray.add(timeline[i]);
            if (sortNewestToOldest) {
                --i;
                continue;
            }
            ++i;
        }
        return timelineArray;
    }

    public static ArrayList<String> getNotificationsArray(int category, boolean sortNewestToOldest) {
        byte categoryB = (byte)category;
        ArrayList<String> list = new ArrayList<String>();
        if (categoryB == 0) {
            return JGNotificationHandler.getTimelineArray(sortNewestToOldest);
        }
        if (notificationsCount.containsKey(categoryB)) {
            int i;
            int size = notificationsCount.get(categoryB);
            int n = i = sortNewestToOldest ? size - 1 : 0;
            while (sortNewestToOldest ? i >= 0 : i < size) {
                list.add(JGNotificationHandler.createKey(categoryB, i + 1));
                if (sortNewestToOldest) {
                    --i;
                    continue;
                }
                ++i;
            }
        }
        return list;
    }

    public static boolean isRightCategory(JGNotification notification) {
        for (byte category : CATEGORY_IDS) {
            if (notification.category != category) continue;
            return true;
        }
        return false;
    }

    public static void addNotification(JGNotification notification) {
        JGNotificationHandlerC.addNotification(notification);
    }

    public static void removeNotification(String removedKeyID) {
        if (removedKeyID.length() > 0 && !notifications.isEmpty() && notifications.containsKey(removedKeyID)) {
            String[] values = removedKeyID.split(":");
            byte keyCategory = Byte.parseByte(values[0]);
            int removedKeyNumber = Integer.parseInt(values[1]);
            notifications.remove(removedKeyID);
            int remainingKeys = notificationsCount.get(keyCategory) - 1;
            if (remainingKeys < 1) {
                notificationsCount.remove(keyCategory);
                return;
            }
            notificationsCount.put(keyCategory, remainingKeys);
            for (int i = removedKeyNumber + 1; i < remainingKeys + 2; ++i) {
                JGNotification notification = new JGNotification(JGNotificationHandler.getNotification(keyCategory, i));
                notifications.remove(JGNotificationHandler.createKey(keyCategory, i));
                notifications.put(JGNotificationHandler.createKey(keyCategory, i - 1), notification);
            }
        }
    }

    public static void clearNotifications() {
        notifications.clear();
        notificationsCount.clear();
    }

    public static JGNotification getNotification(int category, int id) {
        return notifications.get(JGNotificationHandler.createKey(category, id));
    }

    public static String createKey(int category, int id) {
        return category + ":" + id;
    }
}

