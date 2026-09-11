/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.client.resources.I18n
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package riskyken.armourersWorkshop.client.gui;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import riskyken.armourersWorkshop.client.gui.GuiHelper;
import riskyken.armourersWorkshop.client.gui.controls.GuiIconButton;
import riskyken.armourersWorkshop.client.gui.controls.GuiLabeledTextField;
import riskyken.armourersWorkshop.client.lib.LibGuiResources;
import riskyken.armourersWorkshop.common.inventory.ContainerOutfitMaker;
import riskyken.armourersWorkshop.common.network.PacketHandler;
import riskyken.armourersWorkshop.common.network.messages.client.MessageClientGuiButton;
import riskyken.armourersWorkshop.common.network.messages.client.MessageClientGuiOutfitMakerUpdate;
import riskyken.armourersWorkshop.common.tileentities.TileEntityOutfitMaker;

@SideOnly(value=Side.CLIENT)
public class GuiOutfitMaker
extends GuiContainer {
    private static final ResourceLocation TEXTURE = new ResourceLocation(LibGuiResources.OUTFIT_MAKER);
    private final TileEntityOutfitMaker tileEntity;
    private GuiLabeledTextField textItemName;
    private GuiLabeledTextField textFlavour;
    private GuiIconButton iconButtonLoad;
    private GuiIconButton iconButtonSave;

    public GuiOutfitMaker(EntityPlayer entityPlayer, TileEntityOutfitMaker tileEntity) {
        super((Container)new ContainerOutfitMaker(entityPlayer, tileEntity));
        this.tileEntity = tileEntity;
        this.field_146999_f = 176;
        this.field_147000_g = 240;
    }

    public void func_73866_w_() {
        super.func_73866_w_();
        this.field_146292_n.clear();
        this.textItemName = new GuiLabeledTextField(this.field_146289_q, this.field_147003_i + 8, this.field_147009_r + 18, 158, 16);
        this.textItemName.func_146203_f(40);
        this.textItemName.func_146180_a(this.tileEntity.getOutfitName());
        this.textItemName.setEmptyLabel(GuiHelper.getLocalizedControlName(this.tileEntity.func_145825_b(), "skinName"));
        this.textFlavour = new GuiLabeledTextField(this.field_146289_q, this.field_147003_i + 8, this.field_147009_r + 38, 158, 16);
        this.textFlavour.func_146203_f(40);
        this.textFlavour.func_146180_a(this.tileEntity.getOutfitFlavour());
        this.textFlavour.setEmptyLabel(GuiHelper.getLocalizedControlName(this.tileEntity.func_145825_b(), "skinFlavour"));
        this.iconButtonLoad = new GuiIconButton((GuiScreen)this, 0, this.field_147003_i + 6, this.field_147009_r + 120, 20, 20, GuiHelper.getLocalizedControlName(this.tileEntity.func_145825_b(), "load"), TEXTURE).setIconLocation(176, 240, 16, 16);
        this.iconButtonSave = new GuiIconButton((GuiScreen)this, 1, this.field_147003_i + this.field_146999_f - 20 - 6, this.field_147009_r + 120, 20, 20, GuiHelper.getLocalizedControlName(this.tileEntity.func_145825_b(), "save"), TEXTURE).setIconLocation(176, 224, 16, 16);
        this.field_146292_n.add(this.iconButtonSave);
    }

    protected void func_146284_a(GuiButton button) {
        MessageClientGuiButton message = new MessageClientGuiButton((byte)button.field_146127_k);
        PacketHandler.networkWrapper.sendToServer((IMessage)message);
    }

    protected void func_73864_a(int mouseX, int mouseY, int mouseButton) {
        this.textItemName.func_146192_a(mouseX, mouseY, mouseButton);
        this.textFlavour.func_146192_a(mouseX, mouseY, mouseButton);
        if (mouseButton == 1) {
            if (this.textItemName.func_146206_l()) {
                this.textItemName.func_146180_a("");
            }
            if (this.textFlavour.func_146206_l()) {
                this.textFlavour.func_146180_a("");
            }
        }
        super.func_73864_a(mouseX, mouseY, mouseButton);
    }

    protected void func_73869_a(char typedChar, int keyCode) {
        boolean typed = false;
        if (!typed) {
            typed = this.textItemName.func_146201_a(typedChar, keyCode);
        }
        if (!typed) {
            typed = this.textFlavour.func_146201_a(typedChar, keyCode);
        }
        if (typed) {
            String sendTextName = this.textItemName.func_146179_b().trim();
            String sendTextFlavour = this.textFlavour.func_146179_b().trim();
            boolean textChanged = false;
            if (!sendTextName.equals(this.tileEntity.getOutfitName())) {
                this.tileEntity.setOutfitName(sendTextName);
                textChanged = true;
            }
            if (!sendTextFlavour.equals(this.tileEntity.getOutfitFlavour())) {
                this.tileEntity.setOutfitFlavour(sendTextFlavour);
                textChanged = true;
            }
            if (textChanged) {
                this.updateProperty(sendTextName, sendTextFlavour);
            }
        }
        if (!typed) {
            super.func_73869_a(typedChar, keyCode);
        }
    }

    public void updateProperty(String sendTextName, String sendTextFlavour) {
        PacketHandler.networkWrapper.sendToServer((IMessage)new MessageClientGuiOutfitMakerUpdate(sendTextName, sendTextFlavour));
    }

    protected void func_146976_a(float partialTicks, int mouseX, int mouseY) {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.field_146297_k.field_71446_o.func_110577_a(TEXTURE);
        this.func_73729_b(this.field_147003_i, this.field_147009_r, 0, 0, this.field_146999_f, this.field_147000_g);
        this.textItemName.func_146194_f();
        this.textFlavour.func_146194_f();
    }

    protected void func_146979_b(int mouseX, int mouseY) {
        GuiHelper.renderLocalizedGuiName(this.field_146289_q, this.field_146999_f, this.tileEntity.func_145825_b());
        this.field_146289_q.func_78276_b(I18n.func_135052_a((String)"container.inventory", (Object[])new Object[0]), 8, this.field_147000_g - 96 + 2, 0x404040);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)(-this.field_147003_i), (float)(-this.field_147009_r), (float)0.0f);
        this.iconButtonLoad.drawRollover(this.field_146297_k, mouseX, mouseY);
        this.iconButtonSave.drawRollover(this.field_146297_k, mouseX, mouseY);
        GL11.glPopMatrix();
    }
}

