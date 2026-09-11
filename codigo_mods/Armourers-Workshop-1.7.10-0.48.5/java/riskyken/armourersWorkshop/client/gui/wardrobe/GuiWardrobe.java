/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.inventory.GuiInventory
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.resources.I18n
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.util.ResourceLocation
 *  org.apache.logging.log4j.Level
 *  org.lwjgl.BufferUtils
 *  org.lwjgl.input.Mouse
 *  org.lwjgl.opengl.GL11
 */
package riskyken.armourersWorkshop.client.gui.wardrobe;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Color;
import java.nio.FloatBuffer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.Level;
import org.lwjgl.BufferUtils;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import riskyken.armourersWorkshop.client.gui.GuiHelper;
import riskyken.armourersWorkshop.client.gui.controls.GuiTab;
import riskyken.armourersWorkshop.client.gui.controls.GuiTabPanel;
import riskyken.armourersWorkshop.client.gui.controls.GuiTabbed;
import riskyken.armourersWorkshop.client.gui.wardrobe.tab.GuiTabWardrobeColourSettings;
import riskyken.armourersWorkshop.client.gui.wardrobe.tab.GuiTabWardrobeDisplaySettings;
import riskyken.armourersWorkshop.client.gui.wardrobe.tab.GuiTabWardrobeOutfits;
import riskyken.armourersWorkshop.client.gui.wardrobe.tab.GuiTabWardrobeSkins;
import riskyken.armourersWorkshop.client.lib.LibGuiResources;
import riskyken.armourersWorkshop.client.render.ModRenderHelper;
import riskyken.armourersWorkshop.common.data.PlayerPointer;
import riskyken.armourersWorkshop.common.inventory.ContainerSkinWardrobe;
import riskyken.armourersWorkshop.common.inventory.slot.SlotHidable;
import riskyken.armourersWorkshop.common.wardrobe.EquipmentWardrobeData;
import riskyken.armourersWorkshop.common.wardrobe.ExPropsPlayerSkinData;
import riskyken.armourersWorkshop.proxies.ClientProxy;
import riskyken.armourersWorkshop.utils.ModLogger;

@SideOnly(value=Side.CLIENT)
public class GuiWardrobe
extends GuiTabbed {
    private static final ResourceLocation TEXTURE_1 = new ResourceLocation(LibGuiResources.WARDROBE_1);
    private static final ResourceLocation TEXTURE_2 = new ResourceLocation(LibGuiResources.WARDROBE_2);
    private static final ResourceLocation TEXTURE_TAB = new ResourceLocation(LibGuiResources.WARDROBE_TABS);
    private final GuiTabWardrobeSkins tabSkins;
    private final GuiTabWardrobeOutfits tabOutfits;
    private final GuiTabWardrobeDisplaySettings tabDisplaySettings;
    private final GuiTabWardrobeColourSettings tabColourSettings;
    ExPropsPlayerSkinData customEquipmentData;
    EquipmentWardrobeData equipmentWardrobeData;
    EntityPlayer player;
    private boolean rotatingPlayer = false;
    private float playerRotation = 45.0f;
    private int lastMouseX;
    private int lastMouseY;
    String guiName = "equipmentWardrobe";

    public GuiWardrobe(InventoryPlayer inventory, ExPropsPlayerSkinData customEquipmentData) {
        super(new ContainerSkinWardrobe(inventory, customEquipmentData), false, TEXTURE_TAB);
        this.field_146999_f = 278;
        this.field_147000_g = 240;
        this.player = inventory.field_70458_d;
        this.customEquipmentData = customEquipmentData;
        PlayerPointer playerPointer = new PlayerPointer(this.player);
        this.equipmentWardrobeData = ClientProxy.equipmentWardrobeHandler.getEquipmentWardrobeData(playerPointer);
        if (this.equipmentWardrobeData == null) {
            this.equipmentWardrobeData = new EquipmentWardrobeData();
            ModLogger.log(Level.ERROR, "Unable to get skin info for player: " + this.player.getDisplayName());
        }
        this.tabSkins = new GuiTabWardrobeSkins(0, (GuiScreen)this);
        this.tabOutfits = new GuiTabWardrobeOutfits(1, (GuiScreen)this);
        this.tabDisplaySettings = new GuiTabWardrobeDisplaySettings(2, (GuiScreen)this, this.player, customEquipmentData, this.equipmentWardrobeData);
        this.tabColourSettings = new GuiTabWardrobeColourSettings(3, (GuiScreen)this, this.player, customEquipmentData, this.equipmentWardrobeData);
        this.tabList.add(this.tabSkins);
        this.tabList.add(this.tabOutfits);
        this.tabList.add(this.tabDisplaySettings);
        this.tabList.add(this.tabColourSettings);
        this.tabController.addTab(new GuiTab(GuiHelper.getLocalizedControlName(this.guiName, "tab.skins")).setIconLocation(52, 0).setTabTextureSize(26, 30).setPadding(0, 4, 3, 3));
        this.tabController.addTab(new GuiTab(GuiHelper.getLocalizedControlName(this.guiName, "tab.outfits")).setIconLocation(116, 0).setTabTextureSize(26, 30).setPadding(0, 4, 3, 3));
        this.tabController.addTab(new GuiTab(GuiHelper.getLocalizedControlName(this.guiName, "tab.displaySettings")).setIconLocation(68, 0).setTabTextureSize(26, 30).setPadding(0, 4, 3, 3));
        this.tabController.addTab(new GuiTab(GuiHelper.getLocalizedControlName(this.guiName, "tab.colourSettings")).setIconLocation(84, 0).setTabTextureSize(26, 30).setPadding(0, 4, 3, 3));
        this.tabController.setActiveTabIndex(activeTab);
        this.tabChanged();
    }

    public ContainerSkinWardrobe getContainer() {
        return (ContainerSkinWardrobe)this.field_147002_h;
    }

    private void setSlotVisibilitySkins(boolean visible) {
        this.setSlotVisibility(this.getContainer().getIndexSkinsStart(), this.getContainer().getIndexSkinsEnd(), visible);
    }

    private void setSlotVisibilityOutfits(boolean visible) {
        this.setSlotVisibility(this.getContainer().getIndexOutfitStart(), this.getContainer().getIndexOutfitEnd(), visible);
    }

    private void setSlotVisibility(int start, int end, boolean visible) {
        for (int i = start; i < end; ++i) {
            Object slot = this.field_147002_h.field_75151_b.get(i);
            if (slot == null || !(slot instanceof SlotHidable)) continue;
            ((SlotHidable)((Object)slot)).setVisible(visible);
        }
    }

    @Override
    protected void tabChanged() {
        super.tabChanged();
        this.setSlotVisibilitySkins(activeTab == this.tabSkins.getTabId());
        this.setSlotVisibilityOutfits(activeTab == this.tabOutfits.getTabId());
    }

    protected void func_146979_b(int mouseX, int mouseY) {
        GuiHelper.renderLocalizedGuiName(this.field_146289_q, this.field_146999_f, "equipmentWardrobe");
        for (int i = 0; i < this.tabList.size(); ++i) {
            GuiTabPanel tab = (GuiTabPanel)((Object)this.tabList.get(i));
            if (tab.getTabId() != activeTab) continue;
            tab.drawForegroundLayer(mouseX, mouseY);
        }
        this.field_146289_q.func_78276_b(I18n.func_135052_a((String)"container.inventory", (Object[])new Object[0]), 58, this.field_147000_g - 96 + 2, 0x404040);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)(-this.field_147003_i), (float)(-this.field_147009_r), (float)0.0f);
        this.tabController.drawHoverText(this.field_146297_k, mouseX, mouseY);
        GL11.glPopMatrix();
    }

    protected void func_146976_a(float partialTickTime, int mouseX, int mouseY) {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.field_146297_k.field_71446_o.func_110577_a(TEXTURE_1);
        this.func_73729_b(this.field_147003_i, this.field_147009_r, 0, 0, 256, 240);
        this.field_146297_k.field_71446_o.func_110577_a(TEXTURE_2);
        this.func_73729_b(this.field_147003_i + 256, this.field_147009_r, 0, 0, 22, 151);
        for (int i = 0; i < this.tabList.size(); ++i) {
            GuiTabPanel tab = (GuiTabPanel)((Object)this.tabList.get(i));
            if (tab.getTabId() != activeTab) continue;
            tab.drawBackgroundLayer(partialTickTime, mouseX, mouseY);
        }
        if (this.rotatingPlayer) {
            this.playerRotation += (float)(mouseX - this.lastMouseX);
            if (this.playerRotation < 0.0f) {
                this.playerRotation += 360.0f;
            }
            if (this.playerRotation > 360.0f) {
                this.playerRotation -= 360.0f;
            }
        }
        this.lastMouseX = mouseX;
        this.lastMouseY = mouseY;
    }

    @Override
    protected void func_73864_a(int mouseX, int mouseY, int button) {
        if (button == 1) {
            this.rotatingPlayer = true;
        }
        super.func_73864_a(mouseX, mouseY, button);
    }

    @Override
    protected void func_146286_b(int mouseX, int mouseY, int button) {
        super.func_146286_b(mouseX, mouseY, button);
        if (button == 1) {
            this.rotatingPlayer = false;
        }
    }

    public void drawPlayerPreview(int x, int y, int mouseX, int mouseY) {
        this.drawPlayerPreview(x, y, mouseX, mouseY, false);
    }

    public Color drawPlayerPreview(int x, int y, int mouseX, int mouseY, boolean selectingColour) {
        Color colour = new Color(255, 255, 255);
        float boxX = (float)x + 42.5f;
        float boxY = (float)y + 125.0f;
        boolean overPlayerBox = false;
        if (mouseX >= x + 8 & mouseX < x + 8 + 71 && mouseY >= y + 27 & mouseY < y + 27 + 111) {
            overPlayerBox = true;
        }
        if (!overPlayerBox) {
            ModRenderHelper.enableScissorScaled(x + 8, y + 27, 71, 111);
        }
        GL11.glPushMatrix();
        GL11.glPushAttrib((int)1048575);
        GL11.glTranslatef((float)boxX, (float)boxY, (float)50.0f);
        GL11.glRotatef((float)-20.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glRotatef((float)this.playerRotation, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glTranslatef((float)0.0f, (float)0.0f, (float)-50.0f);
        if (selectingColour) {
            this.renderEntityWithoutLighting(0, 0, 45, 0.0f, 0.0f, (EntityLivingBase)this.field_146297_k.field_71439_g);
            colour = this.getColourAtPos(Mouse.getX(), Mouse.getY());
        }
        GuiInventory.func_147046_a((int)0, (int)0, (int)45, (float)0.0f, (float)0.0f, (EntityLivingBase)this.field_146297_k.field_71439_g);
        GL11.glPopAttrib();
        GL11.glPopMatrix();
        if (!overPlayerBox) {
            ModRenderHelper.disableScissor();
        }
        return colour;
    }

    private void renderEntityWithoutLighting(int xPos, int yPos, int scale, float yaw, float pitch, EntityLivingBase entity) {
        GL11.glEnable((int)2903);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)xPos, (float)yPos, (float)50.0f);
        GL11.glScalef((float)(-scale), (float)scale, (float)scale);
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        float f2 = entity.field_70761_aq;
        float f3 = entity.field_70177_z;
        float f4 = entity.field_70125_A;
        float f5 = entity.field_70758_at;
        float f6 = entity.field_70759_as;
        GL11.glRotatef((float)135.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        RenderHelper.func_74518_a();
        ModRenderHelper.disableLighting();
        GL11.glRotatef((float)-135.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)(-((float)Math.atan(pitch / 40.0f)) * 20.0f), (float)1.0f, (float)0.0f, (float)0.0f);
        entity.field_70761_aq = (float)Math.atan(yaw / 40.0f) * 20.0f;
        entity.field_70177_z = (float)Math.atan(yaw / 40.0f) * 40.0f;
        entity.field_70125_A = -((float)Math.atan(pitch / 40.0f)) * 20.0f;
        entity.field_70759_as = entity.field_70177_z;
        entity.field_70758_at = entity.field_70177_z;
        GL11.glTranslatef((float)0.0f, (float)entity.field_70129_M, (float)0.0f);
        RenderManager.field_78727_a.field_78735_i = 180.0f;
        RenderManager.field_78727_a.func_147940_a((Entity)entity, 0.0, 0.0, 0.0, 0.0f, 1.0f);
        entity.field_70761_aq = f2;
        entity.field_70177_z = f3;
        entity.field_70125_A = f4;
        entity.field_70758_at = f5;
        entity.field_70759_as = f6;
        GL11.glPopMatrix();
        RenderHelper.func_74518_a();
        GL11.glDisable((int)32826);
        OpenGlHelper.func_77473_a((int)OpenGlHelper.field_77476_b);
        GL11.glDisable((int)3553);
        OpenGlHelper.func_77473_a((int)OpenGlHelper.field_77478_a);
    }

    private Color getColourAtPos(int x, int y) {
        FloatBuffer buffer = BufferUtils.createFloatBuffer((int)3);
        GL11.glReadPixels((int)x, (int)y, (int)1, (int)1, (int)6407, (int)5126, (FloatBuffer)buffer);
        int r = Math.round(buffer.get() * 255.0f);
        int g = Math.round(buffer.get() * 255.0f);
        int b = Math.round(buffer.get() * 255.0f);
        return new Color(r, g, b);
    }
}

