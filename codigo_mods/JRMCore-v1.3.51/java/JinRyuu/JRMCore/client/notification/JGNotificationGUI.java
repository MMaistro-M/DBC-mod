/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package JinRyuu.JRMCore.client.notification;

import JinRyuu.JRMCore.JRMCoreGuiButtonEmpty;
import JinRyuu.JRMCore.JRMCoreGuiButtons00;
import JinRyuu.JRMCore.JRMCoreGuiButtonsA1;
import JinRyuu.JRMCore.JRMCoreGuiScreen;
import JinRyuu.JRMCore.JRMCoreGuiSliderX00;
import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.client.config.jrmc.JGConfigClientSettings;
import JinRyuu.JRMCore.client.config.jrmc.JGConfigsClient;
import JinRyuu.JRMCore.client.notification.JGNotification;
import JinRyuu.JRMCore.client.notification.JGNotificationHandler;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class JGNotificationGUI {
    public static final int NOTIFICATIONS_OPEN = 50011;
    public static final int NORMAL = 0;
    public static final int MUTED = 1;
    public static final int DISABLED = 2;
    public static final int TEXTURE_SIZE = 16;
    public static final int COLOR_WHITE = 0xFFFFFF;
    public static final int COLOR_RED = 14828830;
    public static final int COLOR_GREEN = 2940958;
    public static final int COLOR_BLUE = 2898406;
    public static final int COLOR_BLACK = 0;
    private static final int MODE_LIST = 0;
    private static final int MODE_SELECTION = 1;
    private static final int BUTTON_ID_START_SELECT = 19000;
    private static final int LIST_COUNT = 5;
    private static int listCount = 0;
    private static int buttonIDStartDelete = 19000 + listCount;
    private static int buttonIDEnd = buttonIDStartDelete + listCount;
    private static int buttonIDClearAll = buttonIDEnd + JGNotificationHandler.CATEGORY_IDS_ALL.length + 1;
    private static int buttonIDSort = buttonIDClearAll + 1;
    private static int buttonIDMute = buttonIDSort + 1;
    private static int buttonIDDisable = buttonIDMute + 1;
    private static ArrayList<JGNotification> notificationList = new ArrayList();
    private static ArrayList<String> notificationKeyList = new ArrayList();
    private static int mode = 0;
    public static byte currentCategory = 0;
    public static int[] categoryState = new int[]{0, 0, 0, 0, 0, 0};
    private static Instant now;
    private static boolean sortNewestToOldest;
    private static int categoryScroll;

    public static JRMCoreGuiScreen getGUIInstance() {
        return JRMCoreGuiScreen.instance;
    }

    public static void checkButtons(int buttonID) {
        if (buttonID == 50011) {
            categoryScroll = 0;
            JGNotificationGUI.getGUIInstance().scroll = 0;
            mode = 0;
            currentCategory = 0;
        }
        if (buttonID >= 19000 && buttonID < buttonIDEnd) {
            boolean remove = buttonID >= buttonIDStartDelete;
            int id = buttonID - (remove ? buttonIDStartDelete : 19000) - JGNotificationGUI.getGUIInstance().scroll;
            if (!remove) {
                int size = notificationList.size();
                for (int i = size - 1; i >= 0; --i) {
                    if (i == id) continue;
                    notificationList.remove(i);
                    notificationKeyList.remove(i);
                }
                categoryScroll = JGNotificationGUI.getGUIInstance().scroll;
                JGNotificationGUI.getGUIInstance().scroll = 0;
                mode = 1;
            } else if (currentCategory == 0) {
                JGNotificationHandler.removeNotification(notificationKeyList.get(id));
            } else {
                int scrollStart = 0;
                int selectedID = scrollStart + id + 1;
                JGNotificationHandler.removeNotification(notificationKeyList.get(id));
            }
        } else if (buttonID == buttonIDEnd) {
            JGNotificationGUI.getGUIInstance().scroll = categoryScroll;
            mode = 0;
        } else if (buttonID > buttonIDEnd && buttonID <= buttonIDEnd + JGNotificationHandler.CATEGORY_IDS_ALL.length) {
            int id = buttonID - buttonIDEnd - 1;
            currentCategory = (byte)id;
            mode = 0;
            categoryScroll = 0;
            JGNotificationGUI.getGUIInstance().scroll = 0;
        } else if (buttonID == buttonIDClearAll) {
            if (currentCategory == 0) {
                JGNotificationHandler.clearNotifications();
            } else if (JGNotificationHandler.notificationsCount.containsKey(currentCategory)) {
                int count = JGNotificationHandler.notificationsCount.get(currentCategory);
                for (int i = count - 1; i >= 0; --i) {
                    JGNotificationHandler.removeNotification(JGNotificationHandler.createKey(currentCategory, i + 1));
                }
                JGNotificationHandler.notificationsCount.remove(currentCategory);
            }
        } else if (buttonID == buttonIDSort) {
            sortNewestToOldest = !sortNewestToOldest;
        } else if (buttonID == buttonIDMute) {
            int all = 0;
            if (currentCategory == 0) {
                for (int i = 0; i < categoryState.length; ++i) {
                    if (i == 0) {
                        all = categoryState[currentCategory] == 1 ? 0 : 1;
                    }
                    JGNotificationGUI.categoryState[i] = all;
                }
            } else {
                JGNotificationGUI.categoryState[JGNotificationGUI.currentCategory] = categoryState[currentCategory] == 1 ? 0 : 1;
            }
            JGConfigClientSettings.init_notifications(JGConfigsClient.clientNotifications, false, false);
        } else if (buttonID == buttonIDDisable) {
            int all = 0;
            if (currentCategory == 0) {
                for (int i = 0; i < categoryState.length; ++i) {
                    if (i == 0) {
                        all = categoryState[currentCategory] == 2 ? 0 : 2;
                    }
                    JGNotificationGUI.categoryState[i] = all;
                }
            } else {
                JGNotificationGUI.categoryState[JGNotificationGUI.currentCategory] = categoryState[currentCategory] == 2 ? 0 : 2;
            }
            JGConfigClientSettings.init_notifications(JGConfigsClient.clientNotifications, false, false);
        }
    }

    public static void update(List buttonList, int width, int height, int posX, int posY, ScaledResolution var5, int var6, FontRenderer var8) {
        if (JGNotificationHandler.hasNewContent) {
            JGNotificationHandler.hasNewContent = false;
        }
        JGNotificationGUI.draw(buttonList, width, height, posX, posY, var5, var6, var8);
    }

    public static void draw(List buttonList, int width, int height, int posX, int posY, ScaledResolution var5, int var6, FontRenderer var8) {
        int xPos = 0;
        int yPos = 0;
        String text = "";
        int xSize = 256;
        int ySize = 159;
        int guiLeft = (width - xSize) / 2;
        int guiTop = (height - ySize) / 2;
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        JGNotificationGUI.getGUIInstance();
        ResourceLocation guiLocation = new ResourceLocation(JRMCoreGuiScreen.guiBG2);
        JGNotificationGUI.getGUIInstance();
        JRMCoreGuiScreen.mc.field_71446_o.func_110577_a(guiLocation);
        JGNotificationGUI.getGUIInstance().func_73729_b(guiLeft, guiTop, 0, 0, xSize, ySize);
        String n = JRMCoreH.trl("jrmc", "Back");
        int nw = var8.func_78256_a(n) + 8;
        xPos = guiLeft;
        yPos = guiTop + 160;
        buttonList.add(new JRMCoreGuiButtons00(mode == 0 ? 100 : buttonIDEnd, xPos, yPos, nw, 20, n, 15051074));
        xPos = guiLeft + 10;
        yPos = guiTop + 4;
        text = JGNotificationHandler.getCategoryTextAll(currentCategory);
        JGNotificationGUI.getGUIInstance();
        JRMCoreGuiScreen.drawStringWithBorder(var8, "Category: " + text.substring(0, 1).toUpperCase() + text.substring(1), xPos, yPos, 0xFFFFFF);
        now = Instant.now();
        for (int i = 0; i < JGNotificationHandler.CATEGORY_IDS_ALL.length; ++i) {
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            xPos = guiLeft - 20;
            yPos = guiTop + 20 + i * 20;
            buttonList.add(new JRMCoreGuiButtonEmpty(false, currentCategory == i ? 1 : 0, i + 1, categoryState != null ? categoryState[i] : 0, buttonIDEnd + 1 + i, xPos, yPos, 16, 16, "", categoryState[i] == 2 ? 12850949 : (categoryState[i] == 1 ? 12871948 : 0)));
            byte category = JGNotificationHandler.CATEGORY_IDS_ALL[i];
            int count = i == 0 ? JGNotificationHandler.notifications.size() : (JGNotificationHandler.notificationsCount.containsKey(category) ? JGNotificationHandler.notificationsCount.get(category) : 0);
            text = JGNotificationHandler.CATEGORY_TEXTS_ALL[i] + ": " + count;
            if (categoryState[i] != 0) {
                String state = "";
                state = categoryState[i] == 2 ? JRMCoreH.trl("jrmc", "Disabled") : JRMCoreH.trl("jrmc", "Muted");
                text = text + " (" + state + ")";
            }
            JGNotificationGUI.getGUIInstance();
            JRMCoreGuiScreen.drawDetails(JRMCoreH.cct(text, new Object[0]), xPos, yPos, 16, 16, JGNotificationGUI.getGUIInstance().x, JGNotificationGUI.getGUIInstance().y, var8);
        }
        if (mode == 0) {
            int count;
            if (notificationList.size() != 0) {
                notificationList.clear();
                notificationKeyList.clear();
            }
            ArrayList<String> notifications = JGNotificationHandler.getNotificationsArray(currentCategory, sortNewestToOldest);
            int scrollCount = count = notifications.size();
            listCount = count;
            buttonIDStartDelete = 19000 + listCount;
            buttonIDEnd = buttonIDStartDelete + listCount;
            buttonIDClearAll = buttonIDEnd + JGNotificationHandler.CATEGORY_IDS_ALL.length + 1;
            buttonIDSort = buttonIDClearAll + 1;
            buttonIDMute = buttonIDSort + 1;
            buttonIDDisable = buttonIDMute + 1;
            float m2 = 5.0f;
            int sz = 5;
            JGNotificationGUI.getGUIInstance().scrollMouseJump = 1;
            if (scrollCount > sz) {
                if ((float)scrollCount - m2 < (float)JGNotificationGUI.getGUIInstance().scroll) {
                    JGNotificationGUI.getGUIInstance().scroll = (int)((float)scrollCount - m2);
                } else if (JGNotificationGUI.getGUIInstance().scroll < 0) {
                    JGNotificationGUI.getGUIInstance().scroll = 0;
                }
                if (JGNotificationGUI.getGUIInstance().mousePressed && !JRMCoreGuiButtonsA1.clicked) {
                    JRMCoreGuiScreen jRMCoreGuiScreen = JGNotificationGUI.getGUIInstance();
                    float f = (float)scrollCount - m2;
                    JGNotificationGUI.getGUIInstance();
                    jRMCoreGuiScreen.scroll = (int)JRMCoreH.round(f * JRMCoreGuiScreen.scrollSide, 0);
                } else {
                    JGNotificationGUI.getGUIInstance();
                    JRMCoreGuiScreen.scrollSide = JRMCoreGuiSliderX00.sliderValue = (float)JGNotificationGUI.getGUIInstance().scroll / ((float)scrollCount - m2);
                }
            } else {
                JGNotificationGUI.getGUIInstance().scroll = 0;
            }
            if (scrollCount > sz) {
                JGNotificationGUI.getGUIInstance();
                if (JRMCoreGuiScreen.scrollSide > 0.0f) {
                    buttonList.add(new JRMCoreGuiButtonsA1(43, guiLeft + xSize / 2 + 110 - 1, guiTop + (ySize + 1) / 2 - 70, "i", false));
                }
                JGNotificationGUI.getGUIInstance();
                if (JRMCoreGuiScreen.scrollSide < 1.0f) {
                    buttonList.add(new JRMCoreGuiButtonsA1(44, guiLeft + xSize / 2 + 110 - 1, guiTop + (ySize + 1) / 2 + 60, "v", false));
                }
                int n2 = guiLeft + xSize / 2 + 110 - 1;
                boolean bl = JGNotificationGUI.getGUIInstance().mousePressed;
                JGNotificationGUI.getGUIInstance();
                buttonList.add(new JRMCoreGuiSliderX00(1000000, n2, guiTop + 25, bl, JRMCoreGuiScreen.scrollSide, 1.0f, false));
            }
            String[] buttons = new String[]{JRMCoreH.trl("jrmc", "Disable"), JRMCoreH.trl("jrmc", "Mute"), JRMCoreH.trl("jrmc", "Sort"), JRMCoreH.trl("jrmc", "RemoveAll")};
            int[] buttonIDs = new int[]{buttonIDDisable, buttonIDMute, buttonIDSort, buttonIDClearAll};
            int[] buttonIcons = new int[]{18, 17, sortNewestToOldest ? 19 : 20, 18};
            yPos = guiTop - 22;
            for (int i = 0; i < buttons.length; ++i) {
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                xPos = guiLeft + i * (i < buttons.length - 1 ? 20 : 30);
                buttonList.add(new JRMCoreGuiButtonEmpty(false, 0, buttonIcons[i], 0, buttonIDs[i], xPos, yPos, 16, 16, "", 0));
                text = buttons[i];
                JGNotificationGUI.getGUIInstance();
                JRMCoreGuiScreen.drawDetails(JRMCoreH.cct(text, new Object[0]), xPos, yPos, 16, 16, JGNotificationGUI.getGUIInstance().x, JGNotificationGUI.getGUIInstance().y, var8);
            }
            int scrollStart = JGNotificationGUI.getGUIInstance().scroll;
            int scrollMax = 0;
            scrollMax = scrollStart + 5 > count ? count : scrollStart + 5;
            int j = scrollStart;
            int k = 0;
            for (int i = scrollStart; i < scrollMax; ++i) {
                String key = notifications.get(i);
                if (!JGNotificationHandler.notifications.containsKey(key)) continue;
                JGNotification note = JGNotificationHandler.notifications.get(key);
                xPos = guiLeft + 10;
                yPos = guiTop + 20 + k * 26;
                int[] BG_COLOR = new int[]{0, 16763650, 16722690, 2993151, 3002773, 13815502};
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                if (note.iconColor != 0) {
                    JGNotificationGUI.color(note.iconColor);
                }
                ResourceLocation gui = new ResourceLocation(JRMCoreH.tjjrmc + ":note_background.png");
                JGNotificationGUI.getGUIInstance();
                JRMCoreGuiScreen.mc.field_71446_o.func_110577_a(gui);
                JGNotificationGUI.getGUIInstance().func_73729_b(xPos, yPos, 0, 0, 230, 26);
                xPos = guiLeft + 12;
                yPos = guiTop + 10 + 13 + k * 26 + 2;
                gui = new ResourceLocation(JRMCoreH.tjjrmc + ":note_category_icons.png");
                JGNotificationGUI.getGUIInstance();
                JRMCoreGuiScreen.mc.field_71446_o.func_110577_a(gui);
                int idY = note.icon * 16 / 256;
                int idX = note.icon * 16 - idY * 256;
                JGNotificationGUI.getGUIInstance().func_73729_b(xPos, yPos, idX, idY *= 16, 16, 16);
                notificationList.add(note);
                notificationKeyList.add(key);
                xPos = guiLeft + 35;
                yPos = guiTop + 22 + k * 26;
                JGNotificationGUI.getGUIInstance();
                text = JRMCoreGuiScreen.getStringShortened(var8, 175, note.title + " (" + JGNotificationGUI.getTime(note.received, false, true) + ")");
                var8.func_78276_b(text, xPos, yPos, 0xFFFFFF);
                xPos = guiLeft + 35;
                yPos = guiTop + 22 + k * 26;
                JGNotificationGUI.getGUIInstance();
                text = JRMCoreGuiScreen.getStringShortened(var8, 165, note.description);
                var8.func_78276_b(text.replace("/n", ""), xPos, yPos + 13, 0xFFFFFF);
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                xPos = guiLeft + 210;
                yPos = guiTop + 10 + 13 + k * 26 + 2;
                text = JRMCoreH.trl("jrmc", "Remove");
                buttonList.add(new JRMCoreGuiButtonEmpty(false, 0, 18, 0, buttonIDStartDelete + i, xPos, yPos, 16, 16, "", 0));
                JGNotificationGUI.getGUIInstance();
                JRMCoreGuiScreen.drawDetails(JRMCoreH.cct(text, new Object[0]), xPos, yPos, 16, 16, JGNotificationGUI.getGUIInstance().x, JGNotificationGUI.getGUIInstance().y, var8);
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                xPos = guiLeft + 10;
                yPos = guiTop + 20 + k * 26;
                buttonList.add(new JRMCoreGuiButtonEmpty(19000 + i, xPos, yPos, 195, 26, ""));
                ++j;
                ++k;
            }
        } else if (mode == 1) {
            int max;
            int count;
            if (notificationList.size() != 1) {
                return;
            }
            JGNotification note = notificationList.get(0);
            xPos = guiLeft + 12;
            yPos = guiTop + 20;
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            if (note.iconColor != 0) {
                JGNotificationGUI.color(note.iconColor);
            }
            ResourceLocation gui = new ResourceLocation(JRMCoreH.tjjrmc + ":note_category_icons.png");
            JGNotificationGUI.getGUIInstance();
            JRMCoreGuiScreen.mc.field_71446_o.func_110577_a(gui);
            int idY = note.icon * 16 / 256;
            int idX = note.icon * 16 - idY * 256;
            JGNotificationGUI.getGUIInstance().func_73729_b(xPos, yPos, idX, idY *= 16, 16, 16);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            xPos = guiLeft + 35;
            yPos = guiTop + 22 - 2 + 3;
            JGNotificationGUI.getGUIInstance();
            text = JRMCoreGuiScreen.getStringShortened(var8, 200, note.title);
            JGNotificationGUI.getGUIInstance();
            JRMCoreGuiScreen.drawStringWithBorder(var8, text, xPos, yPos, 0xFFFFFF);
            xPos = guiLeft + 45 - 30;
            yPos = guiTop + 45;
            text = note.description;
            String translatedLine = JRMCoreH.trl(text);
            int scrollCount = count = JRMCoreH.txt(translatedLine, "", 0, false, xPos, yPos, 215, 0, 0);
            int textboxWidth = count > 11 ? 215 : 230;
            float m2 = 11.0f;
            int sz = 11;
            JGNotificationGUI.getGUIInstance().scrollMouseJump = 2;
            if (scrollCount > sz) {
                if ((float)scrollCount - m2 < (float)JGNotificationGUI.getGUIInstance().scroll) {
                    JGNotificationGUI.getGUIInstance().scroll = (int)((float)scrollCount - m2);
                } else if (JGNotificationGUI.getGUIInstance().scroll < 0) {
                    JGNotificationGUI.getGUIInstance().scroll = 0;
                }
                if (JGNotificationGUI.getGUIInstance().mousePressed && !JRMCoreGuiButtonsA1.clicked) {
                    JRMCoreGuiScreen jRMCoreGuiScreen = JGNotificationGUI.getGUIInstance();
                    float f = (float)scrollCount - m2;
                    JGNotificationGUI.getGUIInstance();
                    jRMCoreGuiScreen.scroll = (int)JRMCoreH.round(f * JRMCoreGuiScreen.scrollSide, 0);
                } else {
                    JGNotificationGUI.getGUIInstance();
                    JRMCoreGuiScreen.scrollSide = JRMCoreGuiSliderX00.sliderValue = (float)JGNotificationGUI.getGUIInstance().scroll / ((float)scrollCount - m2);
                }
            } else {
                JGNotificationGUI.getGUIInstance().scroll = 0;
            }
            if (scrollCount > sz) {
                JGNotificationGUI.getGUIInstance();
                if (JRMCoreGuiScreen.scrollSide > 0.0f) {
                    buttonList.add(new JRMCoreGuiButtonsA1(43, guiLeft + xSize / 2 + 110 - 1, guiTop + (ySize + 1) / 2 - 70, "i", false));
                }
                JGNotificationGUI.getGUIInstance();
                if (JRMCoreGuiScreen.scrollSide < 1.0f) {
                    buttonList.add(new JRMCoreGuiButtonsA1(44, guiLeft + xSize / 2 + 110 - 1, guiTop + (ySize + 1) / 2 + 60, "v", false));
                }
                int n3 = guiLeft + xSize / 2 + 110 - 1;
                boolean bl = JGNotificationGUI.getGUIInstance().mousePressed;
                JGNotificationGUI.getGUIInstance();
                buttonList.add(new JRMCoreGuiSliderX00(1000000, n3, guiTop + 25, bl, JRMCoreGuiScreen.scrollSide, 1.0f, false));
            }
            if ((max = count - 11 - JGNotificationGUI.getGUIInstance().scroll) < 0) {
                max = 0;
            }
            JRMCoreH.txt(translatedLine, "", 0, true, xPos, yPos - 10 * JGNotificationGUI.getGUIInstance().scroll, textboxWidth, JGNotificationGUI.getGUIInstance().scroll, max);
        }
    }

    public static String getTime(Instant time, boolean longText, boolean showBiggest) {
        long timer;
        String text = "";
        Duration duration = Duration.between(time, now);
        if (duration.toDays() > 0L) {
            text = text + duration.toDays() + (longText ? " Day" + (duration.toDays() > 1L ? "s" : "") : "d");
            if (showBiggest) {
                return text;
            }
        }
        if (duration.toHours() > 0L) {
            if (showBiggest) {
                text = "";
            }
            timer = duration.toHours();
            text = text + (duration.toDays() > 0L ? " " : "") + (timer >= 24L ? timer - timer / 24L * 24L : timer) + (longText ? " Hour" + (timer > 1L ? "s" : "") : "h");
            if (showBiggest) {
                return text;
            }
        }
        if (duration.toMinutes() > 0L) {
            if (showBiggest) {
                text = "";
            }
            timer = duration.toMinutes();
            text = text + (duration.toHours() > 0L ? " " : "") + (timer >= 60L ? timer - timer / 60L * 60L : timer) + (longText ? " Minute" + (timer > 1L ? "s" : "") : "m");
            if (showBiggest) {
                return text;
            }
        }
        if (duration.toMillis() > 0L) {
            if (showBiggest) {
                text = "";
            }
            timer = duration.toMillis() / 1000L;
            text = text + (duration.toMinutes() > 0L ? " " : "") + (timer >= 60L ? timer - timer / 60L * 60L : timer) + (longText ? " Second" + (timer > 1L ? "s" : "") : "s");
            if (showBiggest) {
                return text;
            }
        }
        return text;
    }

    public static void color(int color, float alpha) {
        float h2 = (float)(color >> 16 & 0xFF) / 255.0f;
        float h3 = (float)(color >> 8 & 0xFF) / 255.0f;
        float h4 = (float)(color & 0xFF) / 255.0f;
        float h1 = 1.0f;
        int r = (int)(h2 * 254.0f);
        int g = (int)(h3 * 254.0f);
        int b = (int)(h4 * 254.0f);
        GL11.glColor4f((float)(h1 * h2), (float)(h1 * h3), (float)(h1 * h4), (float)alpha);
    }

    public static void color(int color) {
        float h2 = (float)(color >> 16 & 0xFF) / 255.0f;
        float h3 = (float)(color >> 8 & 0xFF) / 255.0f;
        float h4 = (float)(color & 0xFF) / 255.0f;
        float h1 = 1.0f;
        int r = (int)(h2 * 254.0f);
        int g = (int)(h3 * 254.0f);
        int b = (int)(h4 * 254.0f);
        GL11.glColor3f((float)(h1 * h2), (float)(h1 * h3), (float)(h1 * h4));
    }

    static {
        sortNewestToOldest = true;
        categoryScroll = 0;
    }
}

