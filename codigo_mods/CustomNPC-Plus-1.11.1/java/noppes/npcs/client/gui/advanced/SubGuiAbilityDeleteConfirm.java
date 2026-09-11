/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 */
package noppes.npcs.client.gui.advanced;

import kamkeel.npcs.network.PacketClient;
import kamkeel.npcs.network.packets.request.ability.CustomAbilityRemovePacket;
import net.minecraft.client.gui.GuiButton;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.SubGuiInterface;

public class SubGuiAbilityDeleteConfirm
extends SubGuiInterface {
    private final String abilityName;
    private final Runnable onDeleted;

    public SubGuiAbilityDeleteConfirm(String abilityName) {
        this(abilityName, null);
    }

    public SubGuiAbilityDeleteConfirm(String abilityName, Runnable onDeleted) {
        this.abilityName = abilityName;
        this.onDeleted = onDeleted;
        this.setBackground("menubg.png");
        this.xSize = 200;
        this.ySize = 100;
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        int y = this.guiTop + 10;
        this.addLabel(new GuiNpcLabel(0, "ability.delete.confirm", this.guiLeft + 10, y));
        this.addLabel(new GuiNpcLabel(1, "'" + this.abilityName + "'?", this.guiLeft + 10, y += 12));
        this.addButton(new GuiNpcButton(0, this.guiLeft + 30, y += 30, 60, 20, "gui.yes"));
        this.addButton(new GuiNpcButton(1, this.guiLeft + 110, y, 60, 20, "gui.no"));
    }

    @Override
    public void buttonEvent(GuiButton guibutton) {
        int id = guibutton.field_146127_k;
        if (id == 0) {
            PacketClient.sendClient(new CustomAbilityRemovePacket(this.abilityName));
            if (this.onDeleted != null) {
                this.onDeleted.run();
            }
            this.close();
        } else if (id == 1) {
            this.close();
        }
    }
}

