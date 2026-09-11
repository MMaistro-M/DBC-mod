package JinRyuu.JRMCore.client.notification;

import java.util.ArrayList;
import java.util.HashMap;

public class JGNotificationHandler {
   public static final byte[] CATEGORY_IDS_ALL = new byte[]{0, 1, 2, 3, 4, 5};
   public static final String[] CATEGORY_TEXTS_ALL = new String[]{"All", "Info", "Error", "Server", "Tutorial", "Other"};
   public static final byte[] CATEGORY_IDS = new byte[]{1, 2, 3, 4, 5};
   public static final String[] CATEGORY_TEXTS = new String[]{"Info", "Error", "Server", "Tutorial", "Other"};
   public static HashMap<String, JGNotification> notifications = new HashMap<>();
   public static HashMap<Byte, Integer> notificationsCount = new HashMap<>();
   public static boolean hasNewContent = false;

   public static String getCategoryText(int category) {
      return CATEGORY_TEXTS[category];
   }

   public static int getCategoryID(String category) {
      int length = CATEGORY_TEXTS.length;

      for (int i = 0; i < length; i++) {
         if (category.toLowerCase().equals(CATEGORY_TEXTS[i].toLowerCase())) {
            return i;
         }
      }

      return 0;
   }

   public static String getCategoryTextAll(int category) {
      return CATEGORY_TEXTS_ALL[category];
   }

   public static int getCategoryIDAll(String category) {
      int length = CATEGORY_TEXTS_ALL.length;

      for (int i = 0; i < length; i++) {
         if (category.toLowerCase().equals(CATEGORY_TEXTS_ALL[i].toLowerCase())) {
            return i;
         }
      }

      return 0;
   }

   public static ArrayList<String> getAllNotificationsArray() {
      ArrayList<String> list = new ArrayList<>();

      for (byte currentCategory : CATEGORY_IDS) {
         if (notificationsCount.containsKey(currentCategory)) {
            int size = notificationsCount.get(currentCategory);

            for (int i = 0; i < size; i++) {
               list.add(createKey(currentCategory, i + 1));
            }
         }
      }

      return list;
   }

   public static ArrayList<String> getTimelineArray(boolean sortNewestToOldest) {
      ArrayList<String> timelineArray = new ArrayList<>();
      ArrayList<String> all = getAllNotificationsArray();
      int length = all.size();
      String[] timeline = new String[length];

      for (int i = 0; i < length; i++) {
         timeline[i] = all.get(i);
      }

      for (int i = 0; i < length; i++) {
         for (int j = 0; j < length; j++) {
            if (notifications.get(timeline[i]).received.isBefore(notifications.get(timeline[j]).received)) {
               String keyI = timeline[i];
               timeline[i] = timeline[j];
               timeline[j] = keyI;
            }
         }
      }

      length = timeline.length;
      int i = sortNewestToOldest ? length - 1 : 0;

      while (sortNewestToOldest ? i >= 0 : i < length) {
         timelineArray.add(timeline[i]);
         if (sortNewestToOldest) {
            i--;
         } else {
            i++;
         }
      }

      return timelineArray;
   }

   public static ArrayList<String> getNotificationsArray(int category, boolean sortNewestToOldest) {
      byte categoryB = (byte)category;
      ArrayList<String> list = new ArrayList<>();
      if (categoryB == 0) {
         return getTimelineArray(sortNewestToOldest);
      }

      if (notificationsCount.containsKey(categoryB)) {
         int size = notificationsCount.get(categoryB);
         int i = sortNewestToOldest ? size - 1 : 0;

         while (sortNewestToOldest ? i >= 0 : i < size) {
            list.add(createKey(categoryB, i + 1));
            if (sortNewestToOldest) {
               i--;
            } else {
               i++;
            }
         }
      }

      return list;
   }

   public static boolean isRightCategory(JGNotification notification) {
      for (byte category : CATEGORY_IDS) {
         if (notification.category == category) {
            return true;
         }
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

         for (int i = removedKeyNumber + 1; i < remainingKeys + 2; i++) {
            JGNotification notification = new JGNotification(getNotification(keyCategory, i));
            notifications.remove(createKey(keyCategory, i));
            notifications.put(createKey(keyCategory, i - 1), notification);
         }
      }
   }

   public static void clearNotifications() {
      notifications.clear();
      notificationsCount.clear();
   }

   public static JGNotification getNotification(int category, int id) {
      return notifications.get(createKey(category, id));
   }

   public static String createKey(int category, int id) {
      return category + ":" + id;
   }
}
