/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.ChatStyle
 */
package noppes.npcs.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import noppes.npcs.NoppesStringUtils;
import noppes.npcs.TextBlock;
import noppes.npcs.client.ClientProxy;
import noppes.npcs.controllers.data.Dialog;

public class TextBlockClient
extends TextBlock {
    private ChatStyle style = new ChatStyle();
    public int color = 0xE0E0E0;
    public int titleColor = 0xE0E0E0;
    public int titlePos = 0;
    private String name;
    private ICommandSender sender;

    public TextBlockClient(ICommandSender sender, Dialog dialog, Object ... obs) {
        this(dialog.text, dialog.textWidth, false, obs);
        this.color = dialog.color;
        this.titleColor = dialog.titleColor;
        this.titlePos = dialog.titlePos;
        this.sender = sender;
    }

    public TextBlockClient(String name, String text, int lineWidth, int color, Object ... obs) {
        this(text, lineWidth, false, obs);
        this.color = color;
        this.name = name;
    }

    public String getName() {
        if (this.sender != null) {
            return this.sender.func_70005_c_();
        }
        return this.name;
    }

    public TextBlockClient(String text, int lineWidth, boolean mcFont, Object ... obs) {
        text = NoppesStringUtils.formatText(text, obs);
        String line = "";
        text = text.replace("\n", " \n ");
        text = text.replace("\r", " \r ");
        String[] words = text.split(" ");
        FontRenderer font = Minecraft.func_71410_x().field_71466_p;
        for (String word : words) {
            char c;
            if (word.isEmpty()) continue;
            if (word.length() == 1 && ((c = word.charAt(0)) == '\r' || c == '\n')) {
                this.addLine(line);
                line = "";
                continue;
            }
            String newLine = line.isEmpty() ? word : line + " " + word;
            if ((mcFont ? font.func_78256_a(newLine) : ClientProxy.Font.width(newLine)) > lineWidth) {
                this.addLine(line);
                line = word.trim();
                continue;
            }
            line = newLine;
        }
        if (!line.isEmpty()) {
            this.addLine(line);
        }
    }

    private void addLine(String text) {
        ChatComponentText line = new ChatComponentText(text);
        line.func_150255_a(this.style);
        this.lines.add(line);
    }
}

