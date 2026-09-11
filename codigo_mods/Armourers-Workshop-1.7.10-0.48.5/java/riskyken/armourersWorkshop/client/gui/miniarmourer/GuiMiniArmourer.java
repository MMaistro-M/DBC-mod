/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.config.GuiButtonExt
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.client.resources.I18n
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package riskyken.armourersWorkshop.client.gui.miniarmourer;

import cpw.mods.fml.client.config.GuiButtonExt;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import riskyken.armourersWorkshop.client.gui.GuiHelper;
import riskyken.armourersWorkshop.common.inventory.ContainerMiniArmourer;
import riskyken.armourersWorkshop.common.tileentities.TileEntityMiniArmourer;

@SideOnly(value=Side.CLIENT)
public class GuiMiniArmourer
extends GuiContainer {
    private static final ResourceLocation texture = new ResourceLocation("armourersWorkshop".toLowerCase(), "textures/gui/miniArmourer.png");
    private TileEntityMiniArmourer tileEntity;

    public GuiMiniArmourer(InventoryPlayer invPlayer, TileEntityMiniArmourer tileEntity) {
        super((Container)new ContainerMiniArmourer(invPlayer, tileEntity));
        this.tileEntity = tileEntity;
        this.field_146999_f = 176;
        this.field_147000_g = 176;
    }

    public void func_73866_w_() {
        super.func_73866_w_();
        String guiName = this.tileEntity.func_145825_b();
        this.field_146292_n.clear();
        this.field_146292_n.add(new GuiButtonExt(0, this.field_147003_i + 58, this.field_147009_r + 53, 50, 12, GuiHelper.getLocalizedControlName(guiName, "save")));
        this.field_146292_n.add(new GuiButtonExt(1, this.field_147003_i + 58, this.field_147009_r + 53 + 13, 50, 12, GuiHelper.getLocalizedControlName(guiName, "load")));
    }

    protected void func_146979_b(int p_146979_1_, int p_146979_2_) {
        GuiHelper.renderLocalizedGuiName(this.field_146289_q, this.field_146999_f, this.tileEntity.func_145825_b());
        this.field_146289_q.func_78276_b(I18n.func_135052_a((String)"container.inventory", (Object[])new Object[0]), 8, this.field_147000_g - 96 + 2, 0x404040);
        String labelBuildingAccess = GuiHelper.getLocalizedControlName(this.tileEntity.func_145825_b(), "label.buildingAccess");
        this.field_146289_q.func_78279_b(labelBuildingAccess, 5, 21, 170, 0x404040);
    }

    protected void func_146976_a(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        Minecraft.func_71410_x().func_110434_K().func_110577_a(texture);
        this.func_73729_b(this.field_147003_i, this.field_147009_r, 0, 0, this.field_146999_f, this.field_147000_g);
    }
}

