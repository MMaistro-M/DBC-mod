/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.EnumCreatureAttribute
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.client.gui.mainmenu;

import kamkeel.npcs.addon.client.DBCClient;
import kamkeel.npcs.network.PacketClient;
import kamkeel.npcs.network.packets.request.mainmenu.MainmenuStatsGetPacket;
import kamkeel.npcs.network.packets.request.mainmenu.MainmenuStatsSavePacket;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.DataStats;
import noppes.npcs.client.gui.SubGuiNpcMeleeProperties;
import noppes.npcs.client.gui.SubGuiNpcProjectiles;
import noppes.npcs.client.gui.SubGuiNpcRangeProperties;
import noppes.npcs.client.gui.SubGuiNpcResistanceProperties;
import noppes.npcs.client.gui.SubGuiNpcRespawn;
import noppes.npcs.client.gui.util.GuiNPCInterface2;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcButtonYesNo;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.GuiNpcTextField;
import noppes.npcs.client.gui.util.IGuiData;
import noppes.npcs.client.gui.util.ITextfieldListener;
import noppes.npcs.entity.EntityNPCInterface;

public class GuiNpcStats
extends GuiNPCInterface2
implements ITextfieldListener,
IGuiData {
    private DataStats stats;

    public GuiNpcStats(EntityNPCInterface npc) {
        super(npc, 2);
        this.stats = npc.stats;
        PacketClient.sendClient(new MainmenuStatsGetPacket());
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        int y = this.guiTop + 10;
        this.addLabel(new GuiNpcLabel(0, "stats.health", this.guiLeft + 5, y + 5));
        this.addTextField(new GuiNpcTextField(0, this, this.guiLeft + 82, y, 185, 18, String.format("%.0f", this.stats.maxHealth) + ""));
        this.getTextField((int)0).doublesOnly = true;
        this.getTextField(0).setMinMaxDefaultDouble(0.0, Double.MAX_VALUE, 20.0);
        this.addLabel(new GuiNpcLabel(1, "stats.aggro", this.guiLeft + 275, y + 5));
        this.addTextField(new GuiNpcTextField(1, this, this.field_146289_q, this.guiLeft + 355, y, 56, 18, this.stats.aggroRange + ""));
        this.getTextField((int)1).integersOnly = true;
        this.getTextField(1).setMinMaxDefault(1, 96, 2);
        this.addButton(new GuiNpcButton(0, this.guiLeft + 82, y += 22, 56, 20, "selectServer.edit"));
        this.addLabel(new GuiNpcLabel(2, "stats.respawn", this.guiLeft + 5, y + 5));
        this.addButton(new GuiNpcButton(2, this.guiLeft + 82, y += 22, 56, 20, "selectServer.edit"));
        this.addLabel(new GuiNpcLabel(5, "stats.meleeproperties", this.guiLeft + 5, y + 5));
        this.addButton(new GuiNpcButton(3, this.guiLeft + 82, y += 22, 56, 20, "selectServer.edit"));
        this.addLabel(new GuiNpcLabel(6, "stats.rangedproperties", this.guiLeft + 5, y + 5));
        this.addButton(new GuiNpcButton(9, this.guiLeft + 217, y, 56, 20, "selectServer.edit"));
        this.addLabel(new GuiNpcLabel(7, "stats.projectileproperties", this.guiLeft + 140, y + 5));
        this.addTextField(new GuiNpcTextField(14, this, this.guiLeft + 355, y, 56, 20, String.format("%.0f", Float.valueOf(this.stats.healthRegen)) + "").setFloatsOnly());
        this.addLabel(new GuiNpcLabel(14, "stats.regenhealth", this.guiLeft + 275, y + 5));
        this.addButton(new GuiNpcButton(15, this.guiLeft + 82, y += 34, 56, 20, "selectServer.edit"));
        this.addLabel(new GuiNpcLabel(15, "potion.resistance", this.guiLeft + 5, y + 5));
        this.addButton(new GuiNpcButton(21, this.guiLeft + 217, y, 56, 20, new String[]{"gui.no", "gui.yes"}, this.stats.resistances.disableDamage ? 1 : 0));
        this.addLabel(new GuiNpcLabel(21, "stats.disabledamage", this.guiLeft + 140, y + 5));
        this.addTextField(new GuiNpcTextField(16, this, this.guiLeft + 355, y, 56, 20, String.format("%.0f", Float.valueOf(this.stats.combatRegen)) + "").setFloatsOnly());
        this.addLabel(new GuiNpcLabel(16, "stats.combatregen", this.guiLeft + 275, y + 5));
        this.addButton(new GuiNpcButton(4, this.guiLeft + 82, y += 34, 56, 20, new String[]{"gui.no", "gui.yes"}, this.npc.func_70045_F() ? 1 : 0));
        this.addLabel(new GuiNpcLabel(10, "stats.fireimmune", this.guiLeft + 5, y + 5));
        this.addButton(new GuiNpcButton(5, this.guiLeft + 217, y, 56, 20, new String[]{"stats.never", "stats.inWater", "stats.inAir"}, this.stats.drowningType));
        this.addLabel(new GuiNpcLabel(11, "stats.candrown", this.guiLeft + 140, y + 5));
        this.addButton(new GuiNpcButton(23, this.guiLeft + 358, y, 56, 20, new String[]{"display.all", "gui.none", "NPCs", "Players", "Both"}, this.stats.collidesWith));
        this.addLabel(new GuiNpcLabel(23, "stats.collides", this.guiLeft + 275, y + 5));
        this.addButton(new GuiNpcButton(6, this.guiLeft + 82, y += 22, 56, 20, new String[]{"gui.no", "gui.yes"}, this.stats.burnInSun ? 1 : 0));
        this.addLabel(new GuiNpcLabel(12, "stats.burninsun", this.guiLeft + 5, y + 5));
        this.addButton(new GuiNpcButton(7, this.guiLeft + 217, y, 56, 20, new String[]{"gui.no", "gui.yes"}, this.stats.noFallDamage ? 1 : 0));
        this.addLabel(new GuiNpcLabel(13, "stats.nofalldamage", this.guiLeft + 140, y + 5));
        this.addLabel(new GuiNpcLabel(22, "ai.cobwebAffected", this.guiLeft + 275, y + 5));
        this.addButton(new GuiNpcButton(22, this.guiLeft + 358, y, 56, 20, new String[]{"gui.no", "gui.yes"}, this.npc.stats.ignoreCobweb ? 0 : 1));
        this.addButton(new GuiNpcButtonYesNo(17, this.guiLeft + 82, y += 22, 56, 20, this.stats.potionImmune));
        this.addLabel(new GuiNpcLabel(17, "stats.potionImmune", this.guiLeft + 5, y + 5));
        this.addButton(new GuiNpcButtonYesNo(18, this.guiLeft + 217, y, 56, 20, this.stats.attackInvisible));
        this.addLabel(new GuiNpcLabel(18, "stats.attackInvisible", this.guiLeft + 140, y + 5));
        this.addLabel(new GuiNpcLabel(34, "stats.creaturetype", this.guiLeft + 275, y + 5));
        this.addButton(new GuiNpcButton(8, this.guiLeft + 358, y, 56, 20, new String[]{"stats.normal", "stats.undead", "stats.arthropod"}, this.stats.creatureType.ordinal()));
        DBCClient.Instance.showDBCStatButtons(this, (EntityLivingBase)this.npc);
    }

    @Override
    public void unFocused(GuiNpcTextField textfield) {
        if (textfield.id == 0) {
            this.stats.maxHealth = Math.floor(Double.parseDouble(textfield.func_146179_b()));
            this.npc.func_70691_i((float)this.stats.maxHealth);
        } else if (textfield.id == 1) {
            this.stats.aggroRange = textfield.getInteger();
        } else if (textfield.id == 14) {
            this.stats.healthRegen = (float)Math.floor(Float.parseFloat(textfield.func_146179_b()));
        } else if (textfield.id == 16) {
            this.stats.combatRegen = (float)Math.floor(Float.parseFloat(textfield.func_146179_b()));
        }
    }

    @Override
    protected void func_146284_a(GuiButton guibutton) {
        GuiNpcButton button = (GuiNpcButton)guibutton;
        if (button.field_146127_k == 0) {
            this.setSubGui(new SubGuiNpcRespawn(this.stats));
        } else if (button.field_146127_k == 2) {
            this.setSubGui(new SubGuiNpcMeleeProperties(this.stats));
        } else if (button.field_146127_k == 3) {
            this.setSubGui(new SubGuiNpcRangeProperties(this.stats));
        } else if (button.field_146127_k == 4) {
            this.npc.setImmuneToFire(button.getValue() == 1);
        } else if (button.field_146127_k == 5) {
            this.npc.stats.drowningType = button.getValue();
        } else if (button.field_146127_k == 6) {
            this.stats.burnInSun = button.getValue() == 1;
        } else if (button.field_146127_k == 7) {
            this.stats.noFallDamage = button.getValue() == 1;
        } else if (button.field_146127_k == 8) {
            this.stats.creatureType = EnumCreatureAttribute.values()[button.getValue()];
        } else if (button.field_146127_k == 9) {
            this.setSubGui(new SubGuiNpcProjectiles(this.stats));
        } else if (button.field_146127_k == 15) {
            this.setSubGui(new SubGuiNpcResistanceProperties(this.stats.resistances));
        } else if (button.field_146127_k == 17) {
            this.stats.potionImmune = ((GuiNpcButtonYesNo)guibutton).getBoolean();
        } else if (button.field_146127_k == 18) {
            this.stats.attackInvisible = ((GuiNpcButtonYesNo)guibutton).getBoolean();
        } else if (button.field_146127_k == 21) {
            this.stats.resistances.disableDamage = ((GuiNpcButton)guibutton).getValue() == 1;
        } else if (button.field_146127_k == 22) {
            this.stats.ignoreCobweb = button.getValue() == 0;
        } else if (button.field_146127_k == 23) {
            this.stats.collidesWith = button.getValue();
        }
        if (guibutton instanceof GuiNpcButton) {
            DBCClient.Instance.showDBCStatActionPerformed(this, (GuiNpcButton)guibutton);
        }
    }

    @Override
    public void save() {
        PacketClient.sendClient(new MainmenuStatsSavePacket(this.stats.writeToNBT(new NBTTagCompound())));
    }

    @Override
    public void setGuiData(NBTTagCompound compound) {
        this.stats.readToNBT(compound);
        this.func_73866_w_();
    }
}

