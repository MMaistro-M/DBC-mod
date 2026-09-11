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
package riskyken.armourersWorkshop.client.gui.hologramprojector;

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
import riskyken.armourersWorkshop.client.gui.hologramprojector.GuiHologramProjectorTabAngle;
import riskyken.armourersWorkshop.client.gui.hologramprojector.GuiHologramProjectorTabExtra;
import riskyken.armourersWorkshop.client.gui.hologramprojector.GuiHologramProjectorTabInventory;
import riskyken.armourersWorkshop.client.gui.hologramprojector.GuiHologramProjectorTabOffset;
import riskyken.armourersWorkshop.client.gui.hologramprojector.GuiHologramProjectorTabRotationOffset;
import riskyken.armourersWorkshop.client.gui.hologramprojector.GuiHologramProjectorTabRotationSpeed;
import riskyken.armourersWorkshop.client.lib.LibGuiResources;
import riskyken.armourersWorkshop.common.inventory.ContainerHologramProjector;
import riskyken.armourersWorkshop.common.tileentities.TileEntityHologramProjector;

@SideOnly(value=Side.CLIENT)
public class GuiHologramProjector
extends GuiTabbed {
    private static final ResourceLocation texture = new ResourceLocation(LibGuiResources.HOLOGRAM_PROJECTOR);
    private static final ResourceLocation textureTabs = new ResourceLocation(LibGuiResources.HOLOGRAM_PROJECTOR_TABS);
    private static final String DEGREE = "\u00b0";
    private final TileEntityHologramProjector tileEntity;
    private final String inventoryName;
    public GuiHologramProjectorTabInventory tabInventory;
    public GuiHologramProjectorTabOffset tabOffset;
    public GuiHologramProjectorTabAngle tabAngle;
    public GuiHologramProjectorTabRotationOffset tabRotationOffset;
    public GuiHologramProjectorTabRotationSpeed tabRotationSpeed;
    public GuiHologramProjectorTabExtra tabExtra;
    private boolean loadingGui;

    public GuiHologramProjector(InventoryPlayer invPlayer, TileEntityHologramProjector tileEntity) {
        super(new ContainerHologramProjector(invPlayer, tileEntity), true, textureTabs);
        this.tileEntity = tileEntity;
        this.inventoryName = tileEntity.func_145825_b();
        this.tabInventory = new GuiHologramProjectorTabInventory(0, (GuiScreen)this);
        this.tabOffset = new GuiHologramProjectorTabOffset(1, (GuiScreen)this, this.inventoryName, tileEntity);
        this.tabAngle = new GuiHologramProjectorTabAngle(2, (GuiScreen)this, this.inventoryName, tileEntity);
        this.tabRotationOffset = new GuiHologramProjectorTabRotationOffset(3, (GuiScreen)this, this.inventoryName, tileEntity);
        this.tabRotationSpeed = new GuiHologramProjectorTabRotationSpeed(4, (GuiScreen)this, this.inventoryName, tileEntity);
        this.tabExtra = new GuiHologramProjectorTabExtra(5, (GuiScreen)this, this.inventoryName, tileEntity);
        this.tabList.add(this.tabInventory);
        this.tabList.add(this.tabOffset);
        this.tabList.add(this.tabAngle);
        this.tabList.add(this.tabRotationOffset);
        this.tabList.add(this.tabRotationSpeed);
        this.tabList.add(this.tabExtra);
        this.tabController.addTab(new GuiTab(GuiHelper.getLocalizedControlName(this.inventoryName, "tab.inventory")).setIconLocation(52, 0).setAnimation(8, 150));
        this.tabController.addTab(new GuiTab(GuiHelper.getLocalizedControlName(this.inventoryName, "tab.offset")).setIconLocation(84, 0).setAnimation(8, 150));
        this.tabController.addTab(new GuiTab(GuiHelper.getLocalizedControlName(this.inventoryName, "tab.angle")).setIconLocation(116, 0).setAnimation(8, 150));
        this.tabController.addTab(new GuiTab(GuiHelper.getLocalizedControlName(this.inventoryName, "tab.rotationOffset")).setIconLocation(68, 0).setAnimation(8, 150));
        this.tabController.addTab(new GuiTab(GuiHelper.getLocalizedControlName(this.inventoryName, "tab.rotationSpeed")).setIconLocation(100, 0).setAnimation(4, 150));
        this.tabController.addTab(new GuiTab(GuiHelper.getLocalizedControlName(this.inventoryName, "tab.extra")).setIconLocation(132, 0).setAnimation(8, 150));
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

    protected void func_146976_a(float partialTickTime, int mouseX, int mouseY) {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.field_146297_k.field_71446_o.func_110577_a(texture);
        for (int i = 0; i < this.tabList.size(); ++i) {
            GuiTabPanel tab = (GuiTabPanel)((Object)this.tabList.get(i));
            if (tab.getTabId() != activeTab) continue;
            tab.drawBackgroundLayer(partialTickTime, mouseX, mouseY);
        }
    }

    protected void func_146979_b(int mouseX, int mouseY) {
        GuiHelper.renderLocalizedGuiName(this.field_146289_q, this.field_146999_f, this.tileEntity.func_145825_b());
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
}

