/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 */
package riskyken.armourersWorkshop.client.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import riskyken.armourersWorkshop.client.gui.controls.GuiCheckBox;
import riskyken.armourersWorkshop.client.render.SkinItemRenderHelper;
import riskyken.armourersWorkshop.common.config.ConfigHandlerClient;

@SideOnly(value=Side.CLIENT)
public class GuiDebugTool
extends GuiScreen {
    protected final int guiWidth;
    protected final int guiHeight;
    protected int guiLeft;
    protected int guiTop;
    private GuiCheckBox checkWireframe;
    private GuiCheckBox checkArmourerDebugRenders;
    private GuiCheckBox checkShowLodLevel;
    private GuiCheckBox checkShowSkinBlockBounds;
    private GuiCheckBox checkShowSkinRenderBounds;
    private GuiCheckBox checkDebugItemRenders;

    public GuiDebugTool() {
        this.guiWidth = 180;
        this.guiHeight = 138;
    }

    public void func_73866_w_() {
        this.guiLeft = this.field_146294_l / 2 - this.guiWidth / 2;
        this.guiTop = this.field_146295_m / 2 - this.guiHeight / 2;
        this.field_146292_n.clear();
        this.checkWireframe = new GuiCheckBox(-1, this.guiLeft + 5, this.guiTop + 5, "wire frame", ConfigHandlerClient.wireframeRender);
        this.checkWireframe.setTextColour(-1118482);
        this.checkArmourerDebugRenders = new GuiCheckBox(-1, this.guiLeft + 5, this.guiTop + 15, "armourer debug renders", ConfigHandlerClient.showArmourerDebugRender);
        this.checkArmourerDebugRenders.setTextColour(-1118482);
        this.checkShowLodLevel = new GuiCheckBox(-1, this.guiLeft + 5, this.guiTop + 25, "show lod levels", ConfigHandlerClient.showLodLevels);
        this.checkShowLodLevel.setTextColour(-1118482);
        this.checkShowSkinBlockBounds = new GuiCheckBox(-1, this.guiLeft + 5, this.guiTop + 35, "show skin block bounds", ConfigHandlerClient.showSkinBlockBounds);
        this.checkShowSkinBlockBounds.setTextColour(-1118482);
        this.checkShowSkinRenderBounds = new GuiCheckBox(-1, this.guiLeft + 5, this.guiTop + 45, "show skin render bounds", ConfigHandlerClient.showSkinRenderBounds);
        this.checkShowSkinRenderBounds.setTextColour(-1118482);
        this.checkDebugItemRenders = new GuiCheckBox(-1, this.guiLeft + 5, this.guiTop + 55, "show item debug renders", SkinItemRenderHelper.debugShowFullBounds);
        this.checkDebugItemRenders.setTextColour(-1118482);
        this.field_146292_n.add(this.checkWireframe);
        this.field_146292_n.add(this.checkArmourerDebugRenders);
        this.field_146292_n.add(this.checkShowLodLevel);
        this.field_146292_n.add(this.checkShowSkinBlockBounds);
        this.field_146292_n.add(this.checkShowSkinRenderBounds);
        this.field_146292_n.add(this.checkDebugItemRenders);
    }

    protected void func_146284_a(GuiButton button) {
        if (button == this.checkWireframe) {
            ConfigHandlerClient.wireframeRender = this.checkWireframe.isChecked();
        }
        if (button == this.checkArmourerDebugRenders) {
            ConfigHandlerClient.showArmourerDebugRender = this.checkArmourerDebugRenders.isChecked();
        }
        if (button == this.checkShowLodLevel) {
            ConfigHandlerClient.showLodLevels = this.checkShowLodLevel.isChecked();
        }
        if (button == this.checkShowSkinBlockBounds) {
            ConfigHandlerClient.showSkinBlockBounds = this.checkShowSkinBlockBounds.isChecked();
        }
        if (button == this.checkShowSkinRenderBounds) {
            ConfigHandlerClient.showSkinRenderBounds = this.checkShowSkinRenderBounds.isChecked();
        }
        if (button == this.checkDebugItemRenders) {
            SkinItemRenderHelper.debugShowFullBounds = this.checkDebugItemRenders.isChecked();
            SkinItemRenderHelper.debugShowPartBounds = this.checkDebugItemRenders.isChecked();
            SkinItemRenderHelper.debugShowTextureBounds = this.checkDebugItemRenders.isChecked();
            SkinItemRenderHelper.debugShowTargetBounds = this.checkDebugItemRenders.isChecked();
        }
    }

    public void func_73863_a(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
        this.func_73733_a(this.guiLeft, this.guiTop, this.guiLeft + this.guiWidth, this.guiTop + this.guiHeight, -1072689136, -804253680);
        super.func_73863_a(p_73863_1_, p_73863_2_, p_73863_3_);
    }
}

