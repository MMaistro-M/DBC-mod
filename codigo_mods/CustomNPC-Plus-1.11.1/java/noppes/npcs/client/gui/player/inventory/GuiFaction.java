/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.gui.player.inventory;

import java.util.ArrayList;
import kamkeel.npcs.network.PacketClient;
import kamkeel.npcs.network.packets.player.CheckPlayerValue;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import noppes.npcs.client.CustomNpcResourceListener;
import noppes.npcs.client.gui.player.inventory.GuiCNPCInventory;
import noppes.npcs.client.gui.util.GuiButtonNextPage;
import noppes.npcs.client.gui.util.IGuiData;
import noppes.npcs.controllers.data.Faction;
import noppes.npcs.controllers.data.PlayerFactionData;
import org.lwjgl.opengl.GL11;
import tconstruct.client.tabs.AbstractTab;

public class GuiFaction
extends GuiCNPCInventory
implements IGuiData {
    private final ResourceLocation resource = new ResourceLocation("customnpcs", "textures/gui/standardbg.png");
    private ArrayList<Faction> playerFactions = new ArrayList();
    private int page = 0;
    private int pages = 1;
    private GuiButtonNextPage buttonNextPage;
    private GuiButtonNextPage buttonPreviousPage;

    public GuiFaction() {
        this.xSize = 280;
        this.ySize = 180;
        this.drawDefaultBackground = false;
        this.title = "";
        PacketClient.sendClient(new CheckPlayerValue(CheckPlayerValue.Type.Faction));
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        this.buttonNextPage = new GuiButtonNextPage(1, this.guiLeft + (this.xSize + 35) / 2 + 25, this.guiTop + 170, true);
        this.field_146292_n.add(this.buttonNextPage);
        this.buttonPreviousPage = new GuiButtonNextPage(2, this.guiLeft + (this.xSize + 35) / 2 - 40, this.guiTop + 170, false);
        this.field_146292_n.add(this.buttonPreviousPage);
        this.updateButtons();
    }

    @Override
    public void func_73863_a(int i, int j, float f) {
        this.func_146276_q_();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.field_146297_k.field_71446_o.func_110577_a(this.resource);
        this.func_73729_b(this.guiLeft, this.guiTop, 0, 0, 252, 195);
        this.func_73729_b(this.guiLeft + 252, this.guiTop, 188, 0, 67, 195);
        if (this.playerFactions.isEmpty()) {
            String noFaction = StatCollector.func_74838_a((String)"faction.nostanding");
            this.field_146289_q.func_78276_b(noFaction, this.guiLeft + (this.xSize - this.field_146289_q.func_78256_a(noFaction)) / 2, this.guiTop + 80, CustomNpcResourceListener.DefaultTextColor);
        } else {
            this.renderScreen();
        }
        super.func_73863_a(i, j, f);
    }

    private void renderScreen() {
        int size = 10;
        if (this.playerFactions.size() % 10 != 0 && this.page == this.pages) {
            size = this.playerFactions.size() % 10;
        }
        int hLine = 5;
        if (size < 5) {
            hLine = size;
        }
        int count = -1;
        for (int id = 0; id < size; ++id) {
            ++count;
            Faction faction = this.playerFactions.get((this.page - 1) * 10 + id);
            String name = faction.name;
            String points = " : " + faction.defaultPoints;
            String standing = StatCollector.func_74838_a((String)"faction.friendly");
            int color = 65280;
            if (faction.defaultPoints < faction.neutralPoints) {
                standing = StatCollector.func_74838_a((String)"faction.unfriendly");
                color = 0xFF0000;
                points = points + "/" + faction.neutralPoints;
            } else if (faction.defaultPoints < faction.friendlyPoints) {
                standing = StatCollector.func_74838_a((String)"faction.neutral");
                color = 0xF2FF00;
                points = points + "/" + faction.friendlyPoints;
            } else {
                points = points + "/-";
            }
            this.func_73728_b(this.guiLeft + (this.xSize + 45) / 2, this.guiTop + this.ySize - 15, this.guiTop + 13, -16777216 + CustomNpcResourceListener.DefaultTextColor);
            if (count < 5) {
                this.func_73730_a(this.guiLeft + 2, this.guiLeft + this.xSize + 35, this.guiTop + 14 + count * 30, -16777216 + CustomNpcResourceListener.DefaultTextColor);
                this.field_146289_q.func_78276_b(name, this.guiLeft + (this.xSize + 45 - this.field_146289_q.func_78256_a(name)) / 4, this.guiTop + 19 + count * 30, faction.color);
                this.field_146289_q.func_78276_b(standing, this.guiLeft + (this.xSize + 45) / 4 - this.field_146289_q.func_78256_a(standing), this.guiTop + 33 + count * 30, color);
                this.field_146289_q.func_78276_b(points, this.guiLeft + (this.xSize + 45) / 4, this.guiTop + 33 + count * 30, CustomNpcResourceListener.DefaultTextColor);
            } else {
                this.field_146289_q.func_78276_b(name, this.guiLeft + 3 * (this.xSize + 45 - this.field_146289_q.func_78256_a(name)) / 4, this.guiTop + 19 + (count - 5) * 30, faction.color);
                this.field_146289_q.func_78276_b(standing, this.guiLeft + 3 * (this.xSize + 45) / 4 - this.field_146289_q.func_78256_a(standing) - 10, this.guiTop + 33 + (count - 5) * 30, color);
                this.field_146289_q.func_78276_b(points, this.guiLeft + 3 * (this.xSize + 45) / 4 - 10, this.guiTop + 33 + (count - 5) * 30, CustomNpcResourceListener.DefaultTextColor);
            }
            this.func_73730_a(this.guiLeft + 2, this.guiLeft + this.xSize + 35, this.guiTop + 14 + hLine * 30, -16777216 + CustomNpcResourceListener.DefaultTextColor);
        }
        if (this.pages > 1) {
            String s = this.page + "/" + this.pages;
            this.field_146289_q.func_78276_b(s, this.guiLeft + (this.xSize + 45 - this.field_146289_q.func_78256_a(s)) / 2, this.guiTop + 175, CustomNpcResourceListener.DefaultTextColor);
        }
    }

    @Override
    protected void func_146284_a(GuiButton guibutton) {
        if (guibutton instanceof AbstractTab) {
            return;
        }
        if (guibutton.field_146127_k <= -100) {
            super.func_146284_a(guibutton);
            return;
        }
        if (!(guibutton instanceof GuiButtonNextPage)) {
            return;
        }
        int id = guibutton.field_146127_k;
        if (id == 1) {
            ++this.page;
        }
        if (id == 2) {
            --this.page;
        }
        this.updateButtons();
    }

    private void updateButtons() {
        this.buttonNextPage.setVisible(this.page < this.pages);
        this.buttonPreviousPage.setVisible(this.page > 1);
    }

    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
    }

    @Override
    public void func_73869_a(char c, int i) {
        if (i == 1 || this.isInventoryKey(i)) {
            this.close();
        }
    }

    @Override
    public void save() {
    }

    @Override
    public void setGuiData(NBTTagCompound compound) {
        this.playerFactions = new ArrayList();
        NBTTagList list = compound.func_150295_c("FactionList", 10);
        for (int i = 0; i < list.func_74745_c(); ++i) {
            Faction faction = new Faction();
            faction.readNBT(list.func_150305_b(i));
            this.playerFactions.add(faction);
        }
        PlayerFactionData data = new PlayerFactionData();
        data.loadNBTData(compound);
        for (int id : data.factionData.keySet()) {
            int points = data.factionData.get(id);
            for (Faction faction : this.playerFactions) {
                if (faction.id != id) continue;
                faction.defaultPoints = points;
            }
        }
        this.pages = (this.playerFactions.size() - 1) / 10;
        ++this.pages;
        this.page = 1;
        this.updateButtons();
    }
}

