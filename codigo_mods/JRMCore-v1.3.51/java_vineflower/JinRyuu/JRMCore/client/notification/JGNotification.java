package JinRyuu.JRMCore.client.notification;

import java.time.Instant;
import java.util.Date;

public class JGNotification {
   public static final byte CATEGORY_ID_ALL = 0;
   public static final byte CATEGORY_ID_INFO = 1;
   public static final byte CATEGORY_ID_ERROR = 2;
   public static final byte CATEGORY_ID_SERVER = 3;
   public static final byte CATEGORY_ID_TUTORIAL = 4;
   public static final byte CATEGORY_ID_OTHER = 5;
   public static final String CATEGORY_TEXT_ALL = "All";
   public static final String CATEGORY_TEXT_INFO = "Info";
   public static final String CATEGORY_TEXT_ERROR = "Error";
   public static final String CATEGORY_TEXT_SERVER = "Server";
   public static final String CATEGORY_TEXT_TUTORIAL = "Tutorial";
   public static final String CATEGORY_TEXT_OTHER = "Other";
   public static final byte ICON_DEFAULT = 0;
   public static final byte BACKGROUND_DEFAULT = 0;
   public static final byte RENDER_TOP_RIGHT = 0;
   public String title;
   public String description;
   public byte category;
   public byte icon;
   public byte renderLocation;
   public int iconColor;
   public Instant received;

   public JGNotification(String title, String description, int category, int icon, int renderLocation, int iconColor) {
      this.title = title;
      this.description = description;
      this.category = (byte)category;
      this.icon = (byte)icon;
      this.renderLocation = (byte)renderLocation;
      this.iconColor = iconColor;
      this.received = Instant.now();
   }

   public JGNotification(JGNotification notification) {
      this.title = notification.title;
      this.category = notification.category;
      this.description = notification.description;
      this.icon = notification.icon;
      this.renderLocation = notification.renderLocation;
      this.iconColor = notification.iconColor;
      Date date = Date.from(notification.received);
      this.received = date.toInstant();
   }
}
