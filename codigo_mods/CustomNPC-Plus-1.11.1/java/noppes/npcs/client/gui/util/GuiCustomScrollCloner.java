/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.util.StatCollector
 */
package noppes.npcs.client.gui.util;

import java.util.UUID;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.StatCollector;
import noppes.npcs.client.gui.GuiNpcMobSpawner;
import noppes.npcs.client.gui.util.GuiCustomScroll;
import noppes.npcs.client.gui.util.IClonerGui;
import noppes.npcs.controllers.data.Tag;

public class GuiCustomScrollCloner
extends GuiCustomScroll {
    private final IClonerGui parent;

    public GuiCustomScrollCloner(IClonerGui parent, int id) {
        super((GuiScreen)parent, id);
        this.parent = parent;
    }

    @Override
    protected void drawItems() {
        int l = 0;
        for (int i = 0; i < this.list.size(); ++i) {
            int j = 4;
            int k = 14 * l + 4 - this.scrollY;
            ++l;
            if (k < 4 || k + 12 >= this.ySize) continue;
            int xOffset = this.scrollHeight < this.ySize - 8 ? 0 : 10;
            String displayString = StatCollector.func_74838_a((String)((String)this.list.get(i)));
            String text = "";
            float maxWidth = (float)(this.xSize + xOffset - 8) * 0.8f;
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
            int itemColor = this.colors.getOrDefault(this.list.get(i), 0xFFFFFF);
            if (this.multipleSelection && this.selectedList.contains(this.list.get(i)) || !this.multipleSelection && this.selected == i) {
                this.func_73728_b(j - 2, k - 4, k + 10, -1);
                this.func_73728_b(j + this.xSize - 18 + xOffset, k - 4, k + 10, -1);
                this.func_73730_a(j - 2, j + this.xSize - 18 + xOffset, k - 3, -1);
                this.func_73730_a(j - 2, j + this.xSize - 18 + xOffset, k + 10, -1);
                this.field_146289_q.func_78276_b(text, j, k, itemColor);
            } else if (i == this.hover) {
                this.field_146289_q.func_78276_b(text, j, k, 65280);
            } else {
                this.field_146289_q.func_78276_b(text, j, k, itemColor);
            }
            if (this.parent.getShowingClones() != 0 && this.parent.getShowingClones() != 2 || this.parent.getTagMap() == null) continue;
            int tagStartX = j + this.field_146289_q.func_78256_a(displayString) + 3;
            if (GuiNpcMobSpawner.displayTags != 0 && GuiNpcMobSpawner.displayTags != 1 || !this.parent.getTagMap().hasClone((String)this.list.get(i))) continue;
            for (UUID tagUUID : this.parent.getTagMap().getUUIDsList((String)this.list.get(i))) {
                Tag tag = this.parent.getTags().get(tagUUID);
                if (tag == null || GuiNpcMobSpawner.displayTags != 1 && tag.getIsHidden()) continue;
                this.field_146289_q.func_78276_b("[" + tag.name + "]", tagStartX, k, tag.color);
                tagStartX += this.field_146289_q.func_78256_a("[" + tag.name + "]") + 3;
            }
        }
    }
}

