/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.config.GuiButtonExt
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.StatCollector
 *  org.lwjgl.input.Mouse
 *  org.lwjgl.opengl.GL11
 */
package riskyken.armourersWorkshop.client.gui.miniarmourer;

import cpw.mods.fml.client.config.GuiButtonExt;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinPartType;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinType;
import riskyken.armourersWorkshop.client.gui.controls.GuiDropDownList;
import riskyken.armourersWorkshop.client.gui.miniarmourer.GuiMiniArmourerBuildingModel;
import riskyken.armourersWorkshop.client.render.ModRenderHelper;
import riskyken.armourersWorkshop.common.blocks.ModBlocks;
import riskyken.armourersWorkshop.common.items.ModItems;
import riskyken.armourersWorkshop.common.network.PacketHandler;
import riskyken.armourersWorkshop.common.network.messages.client.MessageClientGuiSetArmourerSkinType;
import riskyken.armourersWorkshop.common.skin.type.SkinTypeRegistry;
import riskyken.armourersWorkshop.common.tileentities.TileEntityMiniArmourer;
import riskyken.armourersWorkshop.utils.UtilColour;

@SideOnly(value=Side.CLIENT)
public class GuiMiniArmourerBuilding
extends GuiScreen
implements GuiDropDownList.IDropDownListCallback {
    public TileEntityMiniArmourer tileEntity;
    private GuiMiniArmourerBuildingModel model;
    private GuiDropDownList dropDownSkins;
    private GuiDropDownList dropDownParts;

    public GuiMiniArmourerBuilding(TileEntityMiniArmourer tileEntity) {
        this.tileEntity = tileEntity;
        this.model = new GuiMiniArmourerBuildingModel(this, Minecraft.func_71410_x(), tileEntity);
        this.model.currentSkinType = tileEntity.getSkinType();
    }

    public void func_73866_w_() {
        super.func_73866_w_();
        this.field_146292_n.clear();
        this.field_146292_n.add(new GuiButtonExt(0, this.field_146294_l - 60, this.field_146295_m - 18, 60, 18, "Exit"));
        this.field_146292_n.add(new GuiButtonExt(1, 0, this.field_146295_m - 18, 60, 18, "Cookies"));
        this.dropDownParts = new GuiDropDownList(3, 84, 2, 80, "", this);
        this.dropDownSkins = new GuiDropDownList(2, 2, 2, 80, "", this);
        ArrayList<ISkinType> skinTypes = SkinTypeRegistry.INSTANCE.getRegisteredSkinTypes();
        int skinCount = 0;
        for (int i = 0; i < skinTypes.size(); ++i) {
            ISkinType skinType = skinTypes.get(i);
            if (skinType == SkinTypeRegistry.oldSkinSkirt) continue;
            String skinLocalizedName = SkinTypeRegistry.INSTANCE.getLocalizedSkinTypeName(skinType);
            String skinRegistryName = skinType.getRegistryName();
            this.dropDownSkins.addListItem(skinLocalizedName, skinRegistryName, skinType.enabled());
            if (skinType == this.tileEntity.getSkinType()) {
                this.dropDownSkins.setListSelectedIndex(skinCount);
                this.updatePartsDropDown(skinType);
            }
            ++skinCount;
        }
        this.field_146292_n.add(this.dropDownSkins);
        this.field_146292_n.add(this.dropDownParts);
    }

    protected void func_146284_a(GuiButton button) {
        if (button.field_146127_k == 0) {
            this.field_146297_k.field_71439_g.func_71053_j();
        }
    }

    public void func_73876_c() {
        super.func_73876_c();
        if (Mouse.isCreated()) {
            int dWheel = Mouse.getDWheel();
            if (dWheel < 0) {
                this.model.zoom -= 10.0f;
            } else if (dWheel > 0) {
                this.model.zoom += 10.0f;
            }
        }
    }

    public void func_73863_a(int mouseX, int mouseY, float p_73863_3_) {
        this.model.currentSkinType = this.tileEntity.getSkinType();
        this.model.stack = this.tileEntity.func_70301_a(0);
        GuiMiniArmourerBuilding.func_73734_a((int)0, (int)0, (int)this.field_146294_l, (int)this.field_146295_m, (int)-16777216);
        GL11.glEnable((int)32826);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        RenderHelper.func_74519_b();
        this.model.drawScreen(mouseX, mouseY);
        GL11.glDisable((int)2929);
        ModRenderHelper.disableLighting();
        RenderHelper.func_74520_c();
        super.func_73863_a(mouseX, mouseY, p_73863_3_);
        this.renderToolButtons();
        this.renderCubeButtons();
        String guiSizeLabel = "Gui Size: " + this.field_146294_l + " * " + this.field_146295_m;
        String zoomLabel = "Zoom: " + this.model.zoom;
        String guiName = this.tileEntity.func_145825_b();
        String localizedName = "inventory." + "armourersWorkshop".toLowerCase() + ":" + guiName + ".name";
        localizedName = StatCollector.func_74838_a((String)localizedName);
        this.drawTextCentered(localizedName, this.field_146294_l / 2, 2, UtilColour.getMinecraftColor(0, UtilColour.ColourFamily.MINECRAFT));
        this.drawTextCentered("WARNING - This block is unfinished.", this.field_146294_l / 2, 12, 0xFF0000);
        this.drawTextCentered("!!! Do not use !!!", this.field_146294_l / 2, 22, 0xFF0000);
        this.drawTextCentered(guiSizeLabel, this.field_146294_l / 2, this.field_146295_m - 10, UtilColour.getMinecraftColor(0, UtilColour.ColourFamily.MINECRAFT));
        this.drawTextCentered(zoomLabel, this.field_146294_l / 2, this.field_146295_m - 20, UtilColour.getMinecraftColor(0, UtilColour.ColourFamily.MINECRAFT));
    }

    private void renderToolButtons() {
        GuiMiniArmourerBuilding.func_73734_a((int)(this.field_146294_l - 18), (int)2, (int)(this.field_146294_l - 2), (int)18, (int)-2130706433);
        ItemStack[] tools = new ItemStack[]{new ItemStack(ModItems.paintbrush, 1), new ItemStack(ModItems.paintRoller, 1), new ItemStack(ModItems.burnTool, 1), new ItemStack(ModItems.dodgeTool, 1), new ItemStack(ModItems.colourPicker, 1), new ItemStack(ModItems.colourNoiseTool, 1), new ItemStack(ModItems.shadeNoiseTool, 1)};
        for (int i = 0; i < tools.length; ++i) {
            this.renderItemInGUI(tools[i], this.field_146294_l - 18, 2 + 18 * i);
        }
    }

    private void renderCubeButtons() {
        ItemStack[] buildingBlocks = new ItemStack[]{new ItemStack(ModBlocks.colourable, 1), new ItemStack(ModBlocks.colourableGlass, 1), new ItemStack(ModBlocks.colourableGlowing, 1), new ItemStack(ModBlocks.colourableGlassGlowing, 1)};
        for (int i = 0; i < buildingBlocks.length; ++i) {
            this.renderItemInGUI(buildingBlocks[i], this.field_146294_l - 36, 2 + 18 * i);
        }
    }

    private void drawTextCentered(String text, int x, int y, int colour) {
        int stringWidth = this.field_146289_q.func_78256_a(text);
        this.field_146289_q.func_78276_b(text, x - stringWidth / 2, y, colour);
    }

    private void renderItemInGUI(ItemStack stack, int x, int y) {
        field_146296_j.func_82406_b(this.field_146289_q, this.field_146297_k.field_71446_o, stack, x, y);
    }

    protected void func_73864_a(int p_73864_1_, int p_73864_2_, int p_73864_3_) {
        super.func_73864_a(p_73864_1_, p_73864_2_, p_73864_3_);
    }

    protected void func_73869_a(char key, int keyCode) {
        super.func_73869_a(key, keyCode);
        if (keyCode == 1 || keyCode == this.field_146297_k.field_71474_y.field_151445_Q.func_151463_i()) {
            this.field_146297_k.field_71439_g.func_71053_j();
        }
    }

    public boolean func_73868_f() {
        return false;
    }

    @Override
    public void onDropDownListChanged(GuiDropDownList dropDownList) {
        if (dropDownList == this.dropDownSkins) {
            GuiDropDownList.DropDownListItem listItem = dropDownList.getListSelectedItem();
            ISkinType skinType = SkinTypeRegistry.INSTANCE.getSkinTypeFromRegistryName(listItem.tag);
            this.updatePartsDropDown(skinType);
            PacketHandler.networkWrapper.sendToServer((IMessage)new MessageClientGuiSetArmourerSkinType(skinType));
        }
        if (dropDownList == this.dropDownParts) {
            ISkinPartType skinPartType;
            String partName = this.dropDownParts.getListSelectedItem().tag;
            this.model.currentSkinPartType = skinPartType = SkinTypeRegistry.INSTANCE.getSkinPartFromRegistryName(partName);
        }
    }

    private void updatePartsDropDown(ISkinType skinType) {
        ISkinPartType skinPartType;
        ArrayList<ISkinPartType> partsList = skinType.getSkinParts();
        this.dropDownParts.clearList();
        for (int i = 0; i < partsList.size(); ++i) {
            skinPartType = partsList.get(i);
            String skinLocalizedName = SkinTypeRegistry.INSTANCE.getLocalizedSkinPartTypeName(skinPartType);
            String skinRegistryName = skinPartType.getRegistryName();
            this.dropDownParts.addListItem(skinLocalizedName, skinRegistryName, true);
        }
        this.dropDownParts.setListSelectedIndex(0);
        String partName = this.dropDownParts.getListSelectedItem().tag;
        this.model.currentSkinPartType = skinPartType = SkinTypeRegistry.INSTANCE.getSkinPartFromRegistryName(partName);
    }
}

