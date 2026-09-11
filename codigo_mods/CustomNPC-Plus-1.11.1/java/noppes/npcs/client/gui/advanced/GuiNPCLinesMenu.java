/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.client.gui.advanced;

import kamkeel.npcs.network.PacketClient;
import kamkeel.npcs.network.packets.request.mainmenu.MainmenuAdvancedSavePacket;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.client.NoppesUtil;
import noppes.npcs.client.gui.GuiNPCLinesEdit;
import noppes.npcs.client.gui.util.GuiNPCInterface2;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcButtonYesNo;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.entity.EntityNPCInterface;

public class GuiNPCLinesMenu
extends GuiNPCInterface2 {
    public GuiNPCLinesMenu(EntityNPCInterface npc) {
        super(npc);
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        this.addButton(new GuiNpcButton(0, this.guiLeft + 85, this.guiTop + 20, "World Lines"));
        this.addButton(new GuiNpcButton(1, this.guiLeft + 85, this.guiTop + 43, "Attack Lines"));
        this.addButton(new GuiNpcButton(2, this.guiLeft + 85, this.guiTop + 66, "Interact Lines"));
        this.addButton(new GuiNpcButton(5, this.guiLeft + 85, this.guiTop + 89, "Killed Lines"));
        this.addButton(new GuiNpcButton(6, this.guiLeft + 85, this.guiTop + 112, "Kill Lines"));
        this.addLabel(new GuiNpcLabel(16, "Random Lines", this.guiLeft + 85, this.guiTop + 157));
        this.addButton(new GuiNpcButtonYesNo(16, this.guiLeft + 175, this.guiTop + 152, !this.npc.advanced.orderedLines));
    }

    @Override
    protected void func_146284_a(GuiButton guibutton) {
        int id = guibutton.field_146127_k;
        if (id == 0) {
            NoppesUtil.openGUI((EntityPlayer)this.player, new GuiNPCLinesEdit(this.npc, this.npc.advanced.worldLines));
        }
        if (id == 1) {
            NoppesUtil.openGUI((EntityPlayer)this.player, new GuiNPCLinesEdit(this.npc, this.npc.advanced.attackLines));
        }
        if (id == 2) {
            NoppesUtil.openGUI((EntityPlayer)this.player, new GuiNPCLinesEdit(this.npc, this.npc.advanced.interactLines));
        }
        if (id == 5) {
            NoppesUtil.openGUI((EntityPlayer)this.player, new GuiNPCLinesEdit(this.npc, this.npc.advanced.killedLines));
        }
        if (id == 6) {
            NoppesUtil.openGUI((EntityPlayer)this.player, new GuiNPCLinesEdit(this.npc, this.npc.advanced.killLines));
        }
        if (id == 16) {
            this.npc.advanced.orderedLines = !((GuiNpcButtonYesNo)guibutton).getBoolean();
        }
    }

    @Override
    public void save() {
        PacketClient.sendClient(new MainmenuAdvancedSavePacket(this.npc.advanced.writeToNBT(new NBTTagCompound())));
    }
}

