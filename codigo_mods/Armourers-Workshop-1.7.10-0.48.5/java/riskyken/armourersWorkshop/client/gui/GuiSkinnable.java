/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.config.GuiUtils
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.client.resources.I18n
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package riskyken.armourersWorkshop.client.gui;

import cpw.mods.fml.client.config.GuiUtils;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import riskyken.armourersWorkshop.client.gui.GuiHelper;
import riskyken.armourersWorkshop.client.lib.LibGuiResources;
import riskyken.armourersWorkshop.common.inventory.ContainerSkinnable;
import riskyken.armourersWorkshop.common.skin.data.Skin;
import riskyken.armourersWorkshop.common.skin.data.SkinProperties;
import riskyken.armourersWorkshop.common.tileentities.TileEntitySkinnable;

public class GuiSkinnable
extends GuiContainer {
    private static final ResourceLocation TEXTURE = new ResourceLocation(LibGuiResources.SKINNABLE);
    private final TileEntitySkinnable tileEntity;
    private final boolean ender;
    private int invWidth;
    private int invHeight;

    public GuiSkinnable(InventoryPlayer invPlayer, TileEntitySkinnable tileEntity, Skin skin) {
        super((Container)new ContainerSkinnable(invPlayer, tileEntity, skin));
        this.tileEntity = tileEntity;
        this.ender = SkinProperties.PROP_BLOCK_ENDER_INVENTORY.getValue(skin.getProperties());
        this.invWidth = SkinProperties.PROP_BLOCK_INVENTORY_WIDTH.getValue(skin.getProperties());
        this.invHeight = SkinProperties.PROP_BLOCK_INVENTORY_HEIGHT.getValue(skin.getProperties());
        if (this.ender) {
            this.invWidth = 9;
            this.invHeight = 3;
        }
    }

    public void func_73866_w_() {
        this.field_146999_f = 176;
        this.field_147000_g = this.invHeight * 18 + 125;
        super.func_73866_w_();
    }

    protected void func_146979_b(int p_146979_1_, int p_146979_2_) {
        if (this.tileEntity.hasCustomName()) {
            String name = this.tileEntity.getCustomName();
            int width = this.field_146289_q.func_78256_a(name);
            this.field_146289_q.func_78276_b(name, this.field_146999_f / 2 - width / 2, 6, 0x404040);
        } else {
            GuiHelper.renderLocalizedGuiName(this.field_146289_q, this.field_146999_f, "skinnable");
        }
        this.field_146289_q.func_78276_b(I18n.func_135052_a((String)"container.inventory", (Object[])new Object[0]), 8, this.field_147000_g - 96 + 2, 0x404040);
    }

    protected void func_146976_a(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.field_146297_k.func_110434_K().func_110577_a(TEXTURE);
        GuiUtils.drawContinuousTexturedBox((int)this.field_147003_i, (int)this.field_147009_r, (int)0, (int)0, (int)this.field_146999_f, (int)this.field_147000_g, (int)176, (int)74, (int)4, (float)this.field_73735_i);
        this.func_73729_b(this.field_147003_i + 7, this.field_147009_r + this.field_147000_g - 85, 0, 180, 162, 76);
        for (int ix = 0; ix < this.invWidth; ++ix) {
            for (int iy = 0; iy < this.invHeight; ++iy) {
                this.func_73729_b(this.field_147003_i + ix * 18 + (this.field_146999_f / 2 - this.invWidth * 18 / 2), this.field_147009_r + iy * 18 + 20, 238, 0, 18, 18);
            }
        }
    }
}

