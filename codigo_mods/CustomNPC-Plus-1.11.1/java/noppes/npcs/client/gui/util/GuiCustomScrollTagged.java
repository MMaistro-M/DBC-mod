/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.util.StatCollector
 */
package noppes.npcs.client.gui.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.StatCollector;
import noppes.npcs.client.gui.util.GuiCustomScroll;
import noppes.npcs.controllers.TagController;
import noppes.npcs.controllers.data.Tag;

public class GuiCustomScrollTagged
extends GuiCustomScroll {
    private HashMap<String, HashSet<UUID>> itemTagMap = new HashMap();
    private boolean showTags = true;

    public GuiCustomScrollTagged(GuiScreen parent, int id) {
        super(parent, id);
    }

    public void setItemTagMap(HashMap<String, HashSet<UUID>> tagMap) {
        this.itemTagMap = tagMap != null ? tagMap : new HashMap();
    }

    public HashMap<String, HashSet<UUID>> getItemTagMap() {
        return this.itemTagMap;
    }

    public void setShowTags(boolean show) {
        this.showTags = show;
    }

    @Override
    protected void drawItems() {
        block0: for (int i = 0; i < this.list.size(); ++i) {
            Integer customColor;
            float maxWidth;
            int j = 4;
            int k = 14 * i + 4 - this.scrollY;
            if (k < 4 || k + 12 >= this.ySize) continue;
            int xOffset = this.scrollHeight < this.ySize - 8 ? 0 : 10;
            String rawEntry = (String)this.list.get(i);
            String displayString = StatCollector.func_74838_a((String)rawEntry);
            String text = "";
            float f = maxWidth = this.showTags && this.itemTagMap.containsKey(rawEntry) ? (float)(this.xSize + xOffset - 8) * 0.5f : (float)(this.xSize + xOffset - 8) * 0.8f;
            if ((float)this.field_146289_q.func_78256_a(displayString) > maxWidth) {
                for (int h = 0; h < displayString.length(); ++h) {
                    char c = displayString.charAt(h);
                    if ((float)this.field_146289_q.func_78256_a(text = text + c) > maxWidth) break;
                }
                if (displayString.length() > text.length()) {
                    text = text + "...";
                }
            } else {
                text = displayString;
            }
            if ((customColor = (Integer)this.colors.get(rawEntry)) != null) {
                if (!text.isEmpty()) {
                    this.field_146289_q.func_78276_b(text, j, k, customColor.intValue());
                }
            } else if (this.multipleSelection && this.selectedList.contains(text) || !this.multipleSelection && this.selected == i) {
                this.func_73728_b(j - 2, k - 4, k + 10, -1);
                this.func_73728_b(j + this.xSize - 18 + xOffset, k - 4, k + 10, -1);
                this.func_73730_a(j - 2, j + this.xSize - 18 + xOffset, k - 3, -1);
                this.func_73730_a(j - 2, j + this.xSize - 18 + xOffset, k + 10, -1);
                this.field_146289_q.func_78276_b(text, j, k, 0xFFFFFF);
            } else if (i == this.hover) {
                this.field_146289_q.func_78276_b(text, j, k, 65280);
            } else {
                this.field_146289_q.func_78276_b(text, j, k, 0xFFFFFF);
            }
            if (!this.showTags || !this.itemTagMap.containsKey(rawEntry)) continue;
            int tagStartX = j + this.field_146289_q.func_78256_a(text) + 3;
            int maxX = j + this.xSize - 18 + xOffset;
            HashSet<UUID> tagUUIDs = this.itemTagMap.get(rawEntry);
            TagController tc = TagController.getInstance();
            if (tc == null || tagUUIDs == null) continue;
            for (UUID uuid : tagUUIDs) {
                Tag tag = tc.getTagFromUUID(uuid);
                if (tag == null || tag.hideTag) continue;
                String tagText = "[" + tag.name + "]";
                int tagWidth = this.field_146289_q.func_78256_a(tagText);
                if (tagStartX + tagWidth > maxX) continue block0;
                this.field_146289_q.func_78276_b(tagText, tagStartX, k, tag.color);
                tagStartX += tagWidth + 3;
            }
        }
    }
}

