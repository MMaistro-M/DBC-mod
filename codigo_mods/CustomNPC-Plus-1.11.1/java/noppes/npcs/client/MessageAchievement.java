/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.stats.Achievement
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.IChatComponent
 */
package noppes.npcs.client;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.stats.Achievement;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import noppes.npcs.CustomItems;

public class MessageAchievement
extends Achievement {
    private String description;
    private String message;

    public MessageAchievement(Item item, String message, String description) {
        super("", message, 0, 0, item, null);
        this.description = description;
        this.message = message;
    }

    public MessageAchievement(String message, String description) {
        this(CustomItems.letter == null ? Items.field_151121_aF : CustomItems.letter, message, description);
    }

    public IChatComponent func_150951_e() {
        return new ChatComponentText(this.message);
    }

    public String func_75989_e() {
        return this.description;
    }
}

