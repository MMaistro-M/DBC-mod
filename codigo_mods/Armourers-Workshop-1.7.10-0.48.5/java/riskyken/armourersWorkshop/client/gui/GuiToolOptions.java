/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.config.GuiUtils
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package riskyken.armourersWorkshop.client.gui;

import cpw.mods.fml.client.config.GuiUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import riskyken.armourersWorkshop.common.network.PacketHandler;
import riskyken.armourersWorkshop.common.network.messages.client.MessageClientGuiToolOptionUpdate;
import riskyken.armourersWorkshop.common.painting.tool.AbstractToolOption;
import riskyken.armourersWorkshop.common.painting.tool.IConfigurableTool;

@SideOnly(value=Side.CLIENT)
public class GuiToolOptions
extends GuiScreen {
    private static final ResourceLocation texture = new ResourceLocation("armourersWorkshop".toLowerCase(), "textures/gui/toolOptions.png");
    private static final int MARGIN_TOP = 22;
    private static final int MARGIN_LEFT = 6;
    private static final int CONTROL_PADDING = 6;
    private final int guiWidth;
    private int guiHeight;
    protected int guiLeft;
    protected int guiTop;
    protected ItemStack stack;
    private String guiName;
    private final ArrayList<AbstractToolOption> toolOptionsList;

    public GuiToolOptions(ItemStack stack) {
        this.stack = stack;
        this.toolOptionsList = new ArrayList();
        ((IConfigurableTool)stack.func_77973_b()).getToolOptions(this.toolOptionsList);
        this.guiWidth = 175;
        this.guiHeight = 61;
        this.guiName = stack.func_82833_r();
    }

    public void func_73866_w_() {
        int i;
        super.func_73866_w_();
        this.field_146292_n.clear();
        this.guiLeft = this.field_146294_l / 2 - this.guiWidth / 2;
        int controlHeight = 22;
        for (i = 0; i < this.toolOptionsList.size(); ++i) {
            controlHeight += this.toolOptionsList.get(i).getDisplayHeight() + 6;
        }
        this.guiHeight = controlHeight;
        this.guiTop = this.field_146295_m / 2 - this.guiHeight / 2;
        controlHeight = 22;
        for (i = 0; i < this.toolOptionsList.size(); ++i) {
            GuiButton control = this.toolOptionsList.get(i).getGuiControl(i, this.guiLeft + 6, controlHeight + this.guiTop, this.stack.func_77978_p());
            this.field_146292_n.add(control);
            controlHeight += this.toolOptionsList.get(i).getDisplayHeight() + 6;
        }
    }

    public void func_73863_a(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        Minecraft.func_71410_x().func_110434_K().func_110577_a(texture);
        int textureWidth = 176;
        int textureHeight = 62;
        int borderSize = 4;
        GuiUtils.drawContinuousTexturedBox((int)this.guiLeft, (int)this.guiTop, (int)0, (int)0, (int)this.guiWidth, (int)this.guiHeight, (int)textureWidth, (int)textureHeight, (int)borderSize, (float)this.field_73735_i);
        super.func_73863_a(p_73863_1_, p_73863_2_, p_73863_3_);
        this.renderGuiTitle(this.field_146289_q, this.guiName);
    }

    protected void func_73869_a(char key, int keyCode) {
        super.func_73869_a(key, keyCode);
        if (keyCode == 1 || keyCode == this.field_146297_k.field_71474_y.field_151445_Q.func_151463_i()) {
            this.field_146297_k.field_71439_g.func_71053_j();
        }
    }

    private void renderGuiTitle(FontRenderer fontRenderer, String name) {
        int xPos = this.guiWidth / 2 - fontRenderer.func_78256_a(name) / 2;
        fontRenderer.func_78276_b(name, this.guiLeft + xPos, this.guiTop + 6, 0x404040);
    }

    public boolean func_73868_f() {
        return false;
    }

    public void func_146281_b() {
        NBTTagCompound compound = new NBTTagCompound();
        this.writeToCompound(compound);
        PacketHandler.networkWrapper.sendToServer((IMessage)new MessageClientGuiToolOptionUpdate(compound));
    }

    public void writeToCompound(NBTTagCompound compound) {
        for (int i = 0; i < this.toolOptionsList.size(); ++i) {
            this.toolOptionsList.get(i).writeToNBT(compound, (GuiButton)this.field_146292_n.get(i));
        }
    }
}

