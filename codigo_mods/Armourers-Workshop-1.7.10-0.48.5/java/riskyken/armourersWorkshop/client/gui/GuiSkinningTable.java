/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.client.resources.I18n
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package riskyken.armourersWorkshop.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import riskyken.armourersWorkshop.client.gui.GuiHelper;
import riskyken.armourersWorkshop.common.inventory.ContainerSkinningTable;
import riskyken.armourersWorkshop.common.tileentities.TileEntitySkinningTable;

public class GuiSkinningTable
extends GuiContainer {
    private static final ResourceLocation texture = new ResourceLocation("armourersWorkshop".toLowerCase(), "textures/gui/skinningTable.png");
    private final TileEntitySkinningTable tileEntity;

    public GuiSkinningTable(InventoryPlayer invPlayer, TileEntitySkinningTable tileEntity) {
        super((Container)new ContainerSkinningTable(invPlayer, tileEntity));
        this.tileEntity = tileEntity;
    }

    public void func_73866_w_() {
        super.func_73866_w_();
        this.field_146999_f = 176;
        this.field_147000_g = 176;
    }

    protected void func_146979_b(int mouseX, int mouseY) {
        GuiHelper.renderLocalizedGuiName(this.field_146289_q, this.field_146999_f, "skinningTable", 2630166);
        this.field_146289_q.func_78276_b(I18n.func_135052_a((String)"container.inventory", (Object[])new Object[0]), 8, this.field_147000_g - 96 + 2, 2630166);
    }

    protected void func_146976_a(float p_146976_1_, int mouseX, int mouseY) {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        Minecraft.func_71410_x().func_110434_K().func_110577_a(texture);
        this.func_73729_b(this.field_147003_i, this.field_147009_r, 0, 0, this.field_146999_f, this.field_147000_g);
    }
}

