/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package riskyken.armourersWorkshop.client.gui.mannequin;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import riskyken.armourersWorkshop.client.gui.GuiHelper;
import riskyken.armourersWorkshop.client.gui.controls.GuiTab;
import riskyken.armourersWorkshop.client.gui.controls.GuiTabPanel;
import riskyken.armourersWorkshop.client.gui.controls.GuiTabbed;
import riskyken.armourersWorkshop.client.gui.mannequin.GuiMannequinTabExtraRenders;
import riskyken.armourersWorkshop.client.gui.mannequin.GuiMannequinTabInventory;
import riskyken.armourersWorkshop.client.gui.mannequin.GuiMannequinTabOffset;
import riskyken.armourersWorkshop.client.gui.mannequin.GuiMannequinTabRotations;
import riskyken.armourersWorkshop.client.gui.mannequin.GuiMannequinTabSkinHair;
import riskyken.armourersWorkshop.client.gui.mannequin.GuiMannequinTabTexture;
import riskyken.armourersWorkshop.client.lib.LibGuiResources;
import riskyken.armourersWorkshop.common.inventory.ContainerMannequin;
import riskyken.armourersWorkshop.common.tileentities.TileEntityMannequin;

@SideOnly(value=Side.CLIENT)
public class GuiMannequin
extends GuiTabbed {
    private static final ResourceLocation texture = new ResourceLocation(LibGuiResources.MANNEQUIN);
    private static final ResourceLocation textureTabs = new ResourceLocation(LibGuiResources.MANNEQUIN_TABS);
    public final TileEntityMannequin tileEntity;
    private final String inventoryName;
    public GuiMannequinTabRotations tabRotations;
    public GuiMannequinTabInventory tabInventory;
    public GuiMannequinTabOffset tabOffset;
    public GuiMannequinTabSkinHair tabSkinAndHair;
    public GuiMannequinTabTexture tabTexture;
    public GuiMannequinTabExtraRenders tabExtraRenders;

    public GuiMannequin(InventoryPlayer invPlayer, TileEntityMannequin tileEntity) {
        super(new ContainerMannequin(invPlayer, tileEntity), true, textureTabs);
        this.tileEntity = tileEntity;
        this.inventoryName = tileEntity.func_145825_b();
        this.tabInventory = new GuiMannequinTabInventory(0, (GuiScreen)this, tileEntity);
        this.tabRotations = new GuiMannequinTabRotations(1, (GuiScreen)this, this.inventoryName, tileEntity.getBipedRotations());
        this.tabOffset = new GuiMannequinTabOffset(2, (GuiScreen)this, this.inventoryName, tileEntity);
        this.tabSkinAndHair = new GuiMannequinTabSkinHair(3, (GuiScreen)this, tileEntity);
        this.tabTexture = new GuiMannequinTabTexture(4, (GuiScreen)this, tileEntity);
        this.tabExtraRenders = new GuiMannequinTabExtraRenders(5, (GuiScreen)this, this.inventoryName, tileEntity);
        this.tabList.add(this.tabInventory);
        this.tabList.add(this.tabRotations);
        this.tabList.add(this.tabOffset);
        this.tabList.add(this.tabSkinAndHair);
        this.tabList.add(this.tabTexture);
        this.tabList.add(this.tabExtraRenders);
        this.tabController.addTab(new GuiTab(GuiHelper.getLocalizedControlName(this.inventoryName, "tab.inventory")).setIconLocation(78, 0).setAnimation(8, 150));
        this.tabController.addTab(new GuiTab(GuiHelper.getLocalizedControlName(this.inventoryName, "tab.rotations")).setIconLocation(94, 0).setAnimation(8, 150));
        this.tabController.addTab(new GuiTab(GuiHelper.getLocalizedControlName(this.inventoryName, "tab.offset")).setIconLocation(110, 0).setAnimation(8, 150));
        this.tabController.addTab(new GuiTab(GuiHelper.getLocalizedControlName(this.inventoryName, "tab.skinAndHair")).setIconLocation(126, 0).setAnimation(8, 150));
        this.tabController.addTab(new GuiTab(GuiHelper.getLocalizedControlName(this.inventoryName, "tab.name")).setIconLocation(142, 0).setAnimation(8, 150));
        this.tabController.addTab(new GuiTab(GuiHelper.getLocalizedControlName(this.inventoryName, "tab.extraRenders")).setIconLocation(158, 0).setAnimation(8, 150));
        this.tabController.setActiveTabIndex(activeTab);
        this.tabChanged();
    }

    @Override
    public void func_73866_w_() {
        this.field_146999_f = this.field_146294_l;
        this.field_147000_g = this.field_146295_m;
        super.func_73866_w_();
        this.field_146292_n.clear();
        this.field_146292_n.add(this.tabController);
    }

    public void func_146276_q_() {
    }

    protected void func_146979_b(int mouseX, int mouseY) {
        String append = null;
        if (this.tileEntity.getGameProfile() != null) {
            append = this.tileEntity.getGameProfile().getName();
        }
        if (this.tileEntity.getIsDoll()) {
            GuiHelper.renderLocalizedGuiName(this.field_146289_q, this.field_146999_f, "doll", append, 0x404040);
        } else {
            GuiHelper.renderLocalizedGuiName(this.field_146289_q, this.field_146999_f, this.tileEntity.func_145825_b(), append, 0x404040);
        }
        for (int i = 0; i < this.tabList.size(); ++i) {
            GuiTabPanel tab = (GuiTabPanel)((Object)this.tabList.get(i));
            if (tab.getTabId() != activeTab) continue;
            tab.drawForegroundLayer(mouseX, mouseY);
        }
        GL11.glPushMatrix();
        GL11.glTranslatef((float)(-this.field_147003_i), (float)(-this.field_147009_r), (float)0.0f);
        this.tabController.drawHoverText(this.field_146297_k, mouseX, mouseY);
        GL11.glPopMatrix();
    }

    protected void func_146976_a(float partialTickTime, int mouseX, int mouseY) {
        this.field_146297_k.field_71446_o.func_110577_a(texture);
        for (int i = 0; i < this.tabList.size(); ++i) {
            GuiTabPanel tab = (GuiTabPanel)((Object)this.tabList.get(i));
            if (tab.getTabId() != activeTab) continue;
            tab.drawBackgroundLayer(partialTickTime, mouseX, mouseY);
        }
    }
}

