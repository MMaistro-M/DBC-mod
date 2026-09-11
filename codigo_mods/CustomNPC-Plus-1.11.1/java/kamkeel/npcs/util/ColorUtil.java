/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.ChatStyle
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.IChatComponent
 */
package kamkeel.npcs.util;

import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;

public class ColorUtil {
    public static int[] colorTableInts = new int[]{0xFFFFFF, 15905331, 15106265, 10072818, 0xE6E633, 8440858, 15905484, 0x4D4D4D, 0x999999, 5085618, 11691750, 0x3366CC, 8412493, 6717491, 0xCC4D4D, 0x1A1A1A};
    private static final String LABEL = "\u00a76[\u00a7eCNPC+\u00a76] ";

    public static float[] hexToRGB(int hex) {
        float r = (float)(hex >> 16 & 0xFF) / 255.0f;
        float g = (float)(hex >> 8 & 0xFF) / 255.0f;
        float b = (float)(hex & 0xFF) / 255.0f;
        return new float[]{r, g, b};
    }

    public static EnumChatFormatting getFormattingByChar(char c) {
        for (EnumChatFormatting format : EnumChatFormatting.values()) {
            if (format.func_96298_a() != c) continue;
            return format;
        }
        return null;
    }

    public static IChatComponent assembleComponent(String text) {
        ChatComponentText composite = new ChatComponentText("");
        ChatComponentText current = new ChatComponentText("");
        ChatStyle currentStyle = new ChatStyle();
        int i = 0;
        while (i < text.length()) {
            char c = text.charAt(i);
            if (c == '\u00a7' && i + 1 < text.length()) {
                char code;
                EnumChatFormatting formatting;
                if (current.func_150260_c().length() > 0) {
                    current.func_150255_a(currentStyle.func_150206_m());
                    composite.func_150257_a((IChatComponent)current);
                    current = new ChatComponentText("");
                }
                if ((formatting = ColorUtil.getFormattingByChar(code = text.charAt(i + 1))) != null) {
                    if ("0123456789abcdef".indexOf(Character.toLowerCase(code)) >= 0) {
                        currentStyle = new ChatStyle().func_150238_a(formatting);
                    } else if (formatting == EnumChatFormatting.RESET) {
                        currentStyle = new ChatStyle();
                    } else {
                        switch (formatting) {
                            case BOLD: {
                                currentStyle = currentStyle.func_150227_a(Boolean.TRUE);
                                break;
                            }
                            case ITALIC: {
                                currentStyle = currentStyle.func_150217_b(Boolean.TRUE);
                                break;
                            }
                            case UNDERLINE: {
                                currentStyle = currentStyle.func_150228_d(Boolean.TRUE);
                                break;
                            }
                            case STRIKETHROUGH: {
                                currentStyle = currentStyle.func_150225_c(Boolean.TRUE);
                                break;
                            }
                            case OBFUSCATED: {
                                currentStyle = currentStyle.func_150237_e(Boolean.TRUE);
                                break;
                            }
                        }
                    }
                }
                i += 2;
                continue;
            }
            current.func_150258_a(String.valueOf(c));
            ++i;
        }
        if (current.func_150260_c().length() > 0) {
            current.func_150255_a(currentStyle.func_150206_m());
            composite.func_150257_a((IChatComponent)current);
        }
        return composite;
    }

    public static void sendMessage(ICommandSender sender, String text) {
        text = "\u00a77" + text;
        IChatComponent component = ColorUtil.assembleComponent(text);
        sender.func_145747_a(component);
    }

    public static void sendMessage(ICommandSender sender, String format, Object ... obs) {
        ColorUtil.sendMessage(sender, String.format(format, obs));
    }

    public static void sendResult(ICommandSender sender, String text) {
        text = "\u00a76[\u00a7eCNPC+\u00a76] \u00a7a" + text;
        IChatComponent component = ColorUtil.assembleComponent(text);
        sender.func_145747_a(component);
    }

    public static void sendResult(ICommandSender sender, String format, Object ... obs) {
        ColorUtil.sendResult(sender, String.format(format, obs));
    }

    public static void sendError(ICommandSender sender, String text) {
        text = "\u00a76[\u00a7eCNPC+\u00a76] \u00a74Error: \u00a7c" + text;
        IChatComponent component = ColorUtil.assembleComponent(text);
        sender.func_145747_a(component);
    }

    public static void sendError(ICommandSender sender, String format, Object ... obs) {
        ColorUtil.sendError(sender, String.format(format, obs));
    }
}

