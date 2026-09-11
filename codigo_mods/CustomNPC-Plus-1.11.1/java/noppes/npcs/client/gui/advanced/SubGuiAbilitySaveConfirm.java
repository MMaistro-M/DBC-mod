/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 */
package noppes.npcs.client.gui.advanced;

import java.util.Set;
import kamkeel.npcs.controllers.data.ability.Ability;
import kamkeel.npcs.network.PacketClient;
import kamkeel.npcs.network.packets.request.ability.CustomAbilitySavePacket;
import net.minecraft.client.gui.GuiButton;
import noppes.npcs.client.gui.advanced.SubGuiDuplicateNameConfirm;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.IAbilityConfigCallback;
import noppes.npcs.client.gui.util.ISubGuiListener;
import noppes.npcs.client.gui.util.SubGuiInterface;

public class SubGuiAbilitySaveConfirm
extends SubGuiInterface
implements ISubGuiListener {
    private final Ability ability;
    private final IAbilityConfigCallback callback;
    private final Set<String> existingNames;
    private boolean saved = false;

    public SubGuiAbilitySaveConfirm(Ability ability) {
        this(ability, null, null);
    }

    public SubGuiAbilitySaveConfirm(Ability ability, IAbilityConfigCallback callback, Set<String> existingNames) {
        this.ability = ability;
        this.callback = callback;
        this.existingNames = existingNames;
        this.setBackground("menubg.png");
        this.xSize = 200;
        this.ySize = 100;
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        int y = this.guiTop + 10;
        this.addLabel(new GuiNpcLabel(0, "ability.save.confirm", this.guiLeft + 10, y));
        this.addLabel(new GuiNpcLabel(1, "'" + this.ability.getName() + "'?", this.guiLeft + 10, y += 12));
        this.addButton(new GuiNpcButton(0, this.guiLeft + 30, y += 30, 60, 20, "gui.yes"));
        this.addButton(new GuiNpcButton(1, this.guiLeft + 110, y, 60, 20, "gui.no"));
    }

    @Override
    public void buttonEvent(GuiButton guibutton) {
        int id = guibutton.field_146127_k;
        if (id == 0) {
            if (this.existingNames != null && this.existingNames.contains(this.ability.getName())) {
                this.setSubGui(new SubGuiDuplicateNameConfirm());
                return;
            }
            this.doSave();
        } else if (id == 1) {
            this.close();
        }
    }

    private void doSave() {
        this.saved = true;
        PacketClient.sendClient(new CustomAbilitySavePacket(this.ability.writeNBT(false)));
        if (this.callback != null) {
            this.callback.onAbilitySaved(this.ability);
        }
        this.close();
    }

    public boolean wasSaved() {
        return this.saved;
    }

    @Override
    public void subGuiClosed(SubGuiInterface subgui) {
        if (subgui instanceof SubGuiDuplicateNameConfirm) {
            SubGuiDuplicateNameConfirm confirm = (SubGuiDuplicateNameConfirm)subgui;
            if (confirm.isConfirmed()) {
                this.doSave();
            } else {
                if (confirm.isBack()) {
                    return;
                }
                this.close();
            }
        }
    }
}

