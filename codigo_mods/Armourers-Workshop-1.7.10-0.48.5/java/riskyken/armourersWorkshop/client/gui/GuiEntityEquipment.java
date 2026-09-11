/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.client.resources.I18n
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package riskyken.armourersWorkshop.client.gui;

import java.util.ArrayList;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinType;
import riskyken.armourersWorkshop.client.gui.GuiHelper;
import riskyken.armourersWorkshop.common.inventory.ContainerEntityEquipment;
import riskyken.armourersWorkshop.common.inventory.InventoryEntitySkin;

public class GuiEntityEquipment
extends GuiContainer {
    private static final ResourceLocation texture = new ResourceLocation("armourersWorkshop".toLowerCase(), "textures/gui/entitySkinInventory.png");
    private final InventoryEntitySkin skinInventory;

    public GuiEntityEquipment(InventoryPlayer playerInventory, InventoryEntitySkin skinInventory) {
        super((Container)new ContainerEntityEquipment(playerInventory, skinInventory));
        this.skinInventory = skinInventory;
    }

    public void func_73866_w_() {
        super.func_73866_w_();
        this.field_146999_f = 176;
        this.field_147000_g = 148;
    }

    protected void func_146979_b(int mouseX, int mouseY) {
        GuiHelper.renderLocalizedGuiName(this.field_146289_q, this.field_146999_f, "entityEquipment");
        this.field_146289_q.func_78276_b(I18n.func_135052_a((String)"container.inventory", (Object[])new Object[0]), 8, this.field_147000_g - 96 + 2, 0x404040);
    }

    protected void func_146976_a(float p_146976_1_, int mouseX, int mouseY) {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.field_146297_k.func_110434_K().func_110577_a(texture);
        this.func_73729_b(this.field_147003_i, this.field_147009_r, 0, 0, this.field_146999_f, this.field_147000_g);
        ArrayList<ISkinType> skinTypes = this.skinInventory.getSkinTypes();
        for (int i = 0; i < skinTypes.size(); ++i) {
            this.func_73729_b(this.field_147003_i + 7 + i * 18, this.field_147009_r + 20, 0, 148, 18, 18);
        }
    }
}

