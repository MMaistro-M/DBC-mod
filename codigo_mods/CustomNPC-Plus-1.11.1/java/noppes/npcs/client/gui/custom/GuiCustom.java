/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.GuiTextField
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.nbt.NBTTagString
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.input.Keyboard
 *  org.lwjgl.input.Mouse
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.gui.custom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kamkeel.npcs.network.PacketClient;
import kamkeel.npcs.network.packets.player.customgui.CustomGuiButtonPacket;
import kamkeel.npcs.network.packets.player.customgui.CustomGuiClosePacket;
import kamkeel.npcs.network.packets.player.customgui.CustomGuiUnfocusedPacket;
import kamkeel.npcs.network.packets.player.customgui.CustomScrollClickPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import noppes.npcs.api.gui.ICustomGuiComponent;
import noppes.npcs.client.gui.custom.components.CustomGuiButton;
import noppes.npcs.client.gui.custom.components.CustomGuiLabel;
import noppes.npcs.client.gui.custom.components.CustomGuiLine;
import noppes.npcs.client.gui.custom.components.CustomGuiScrollComponent;
import noppes.npcs.client.gui.custom.components.CustomGuiTextField;
import noppes.npcs.client.gui.custom.components.CustomGuiTexturedRect;
import noppes.npcs.client.gui.custom.interfaces.IClickListener;
import noppes.npcs.client.gui.custom.interfaces.IDataHolder;
import noppes.npcs.client.gui.custom.interfaces.IGuiComponent;
import noppes.npcs.client.gui.util.GuiCustomScroll;
import noppes.npcs.client.gui.util.ICustomScrollListener;
import noppes.npcs.client.gui.util.IGuiData;
import noppes.npcs.containers.ContainerCustomGui;
import noppes.npcs.scripted.gui.ScriptGui;
import noppes.npcs.scripted.gui.ScriptGuiButton;
import noppes.npcs.scripted.gui.ScriptGuiComponent;
import noppes.npcs.scripted.gui.ScriptGuiLabel;
import noppes.npcs.scripted.gui.ScriptGuiLine;
import noppes.npcs.scripted.gui.ScriptGuiScroll;
import noppes.npcs.scripted.gui.ScriptGuiTextField;
import noppes.npcs.scripted.gui.ScriptGuiTexturedRect;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class GuiCustom
extends GuiScreen
implements ICustomScrollListener,
IGuiData {
    ScriptGui gui;
    int xSize;
    int ySize;
    public static int guiLeft;
    public static int guiTop;
    ResourceLocation background;
    public String[] hoverText;
    Map<Integer, IGuiComponent> components = new HashMap<Integer, IGuiComponent>();
    List<IClickListener> clickListeners = new ArrayList<IClickListener>();
    List<CustomGuiTextField> keyListeners = new ArrayList<CustomGuiTextField>();
    List<IDataHolder> dataHolders = new ArrayList<IDataHolder>();
    public Container inventorySlots;
    private Slot theSlot;
    private Slot clickedSlot;
    private boolean isRightMouseClick;
    private ItemStack draggedStack;
    private int field_147011_y;
    private int field_147010_z;
    private Slot returningStackDestSlot;
    private long returningStackTime;
    private ItemStack returningStack;
    private Slot field_146985_D;
    private long field_146986_E;
    protected final Set field_147008_s = new HashSet();
    protected boolean field_147007_t;
    private int field_146987_F;
    private int field_146988_G;
    private boolean field_146995_H;
    private int field_146996_I;
    private long field_146997_J;
    private Slot field_146998_K;
    private int field_146992_L;
    private boolean field_146993_M;
    private ItemStack field_146994_N;
    public boolean closeOnEsc = true;
    public String prevScrollClicked = null;

    public GuiCustom(ContainerCustomGui container) {
        this.inventorySlots = container;
        this.field_146995_H = true;
        this.field_146291_p = true;
    }

    public void func_73866_w_() {
        super.func_73866_w_();
        this.field_146297_k.field_71439_g.field_71070_bA = this.inventorySlots;
        if (this.gui != null) {
            guiLeft = (this.field_146294_l - this.xSize) / 2;
            guiTop = (this.field_146295_m - this.ySize) / 2;
            this.components.clear();
            this.clickListeners.clear();
            this.keyListeners.clear();
            this.dataHolders.clear();
            for (ICustomGuiComponent c : this.gui.getComponents()) {
                this.addComponent(c);
            }
        }
    }

    public void func_73876_c() {
        super.func_73876_c();
        for (IDataHolder component : this.dataHolders) {
            if (!(component instanceof GuiTextField)) continue;
            ((GuiTextField)component).func_146178_a();
        }
        if (!this.field_146297_k.field_71439_g.func_70089_S() || this.field_146297_k.field_71439_g.field_70128_L) {
            this.field_146297_k.field_71439_g.func_71053_j();
        }
    }

    public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
        this.hoverText = null;
        if (this.background != null) {
            this.drawBackgroundTexture();
        }
        Iterator<IGuiComponent> var4 = this.components.values().iterator();
        while (var4.hasNext()) {
            GL11.glEnable((int)3042);
            OpenGlHelper.func_148821_a((int)770, (int)771, (int)1, (int)0);
            GL11.glDisable((int)3008);
            IGuiComponent component = var4.next();
            component.onRender(this.field_146297_k, mouseX, mouseY, Mouse.getDWheel(), partialTicks);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glDisable((int)2896);
            GL11.glEnable((int)3008);
        }
        if (this.hoverText != null) {
            this.func_146283_a(Arrays.asList(this.hoverText), mouseX, mouseY);
        }
        this.drawScreenSuper(mouseX, mouseY, partialTicks);
    }

    public void drawScreenSuper(int mouseX, int mouseY, float partialTicks) {
        ItemStack itemstack;
        int k1;
        int k = guiLeft;
        int l = guiTop;
        GL11.glDisable((int)32826);
        RenderHelper.func_74518_a();
        GL11.glDisable((int)2896);
        GL11.glDisable((int)2929);
        super.func_73863_a(mouseX, mouseY, partialTicks);
        RenderHelper.func_74520_c();
        GL11.glPushMatrix();
        GL11.glTranslatef((float)k, (float)l, (float)0.0f);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glEnable((int)32826);
        this.theSlot = null;
        int short1 = 240;
        int short2 = 240;
        OpenGlHelper.func_77475_a((int)OpenGlHelper.field_77476_b, (float)((float)short1 / 1.0f), (float)((float)short2 / 1.0f));
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        for (int i1 = 0; i1 < this.inventorySlots.field_75151_b.size(); ++i1) {
            Slot slot = (Slot)this.inventorySlots.field_75151_b.get(i1);
            this.drawSlot(slot);
            if (!this.isMouseOverSlot(slot, mouseX, mouseY) || !slot.func_111238_b()) continue;
            this.theSlot = slot;
            GL11.glDisable((int)2896);
            GL11.glDisable((int)2929);
            int j1 = slot.field_75223_e;
            k1 = slot.field_75221_f;
            GL11.glColorMask((boolean)true, (boolean)true, (boolean)true, (boolean)false);
            this.func_73733_a(j1, k1, j1 + 16, k1 + 16, -2130706433, -2130706433);
            GL11.glColorMask((boolean)true, (boolean)true, (boolean)true, (boolean)true);
            GL11.glEnable((int)2896);
            GL11.glEnable((int)2929);
        }
        GL11.glDisable((int)2896);
        GL11.glEnable((int)2896);
        InventoryPlayer inventoryplayer = this.field_146297_k.field_71439_g.field_71071_by;
        ItemStack itemStack = itemstack = this.draggedStack == null ? inventoryplayer.func_70445_o() : this.draggedStack;
        if (itemstack != null) {
            int b0 = 8;
            k1 = this.draggedStack == null ? 8 : 16;
            String s = null;
            if (this.draggedStack != null && this.isRightMouseClick) {
                itemstack = itemstack.func_77946_l();
                itemstack.field_77994_a = MathHelper.func_76123_f((float)((float)itemstack.field_77994_a / 2.0f));
            } else if (this.field_147007_t && this.field_147008_s.size() > 1) {
                itemstack = itemstack.func_77946_l();
                itemstack.field_77994_a = this.field_146996_I;
                if (itemstack.field_77994_a == 0) {
                    s = "" + EnumChatFormatting.YELLOW + "0";
                }
            }
            this.drawItemStack(itemstack, mouseX - k - b0, mouseY - l - k1, s);
        }
        if (this.returningStack != null) {
            float f1 = (float)(Minecraft.func_71386_F() - this.returningStackTime) / 100.0f;
            if (f1 >= 1.0f) {
                f1 = 1.0f;
                this.returningStack = null;
            }
            k1 = this.returningStackDestSlot.field_75223_e - this.field_147011_y;
            int j2 = this.returningStackDestSlot.field_75221_f - this.field_147010_z;
            int l1 = this.field_147011_y + (int)((float)k1 * f1);
            int i2 = this.field_147010_z + (int)((float)j2 * f1);
            this.drawItemStack(this.returningStack, l1, i2, null);
        }
        GL11.glPopMatrix();
        if (inventoryplayer.func_70445_o() == null && this.theSlot != null && this.theSlot.func_75216_d()) {
            ItemStack itemstack1 = this.theSlot.func_75211_c();
            this.func_146285_a(itemstack1, mouseX, mouseY);
        }
        GL11.glEnable((int)2896);
        GL11.glEnable((int)2929);
        RenderHelper.func_74519_b();
    }

    private void drawItemStack(ItemStack p_146982_1_, int p_146982_2_, int p_146982_3_, String p_146982_4_) {
        GL11.glTranslatef((float)0.0f, (float)0.0f, (float)32.0f);
        this.field_73735_i = 200.0f;
        GuiCustom.field_146296_j.field_77023_b = 200.0f;
        FontRenderer font = null;
        if (p_146982_1_ != null) {
            font = p_146982_1_.func_77973_b().getFontRenderer(p_146982_1_);
        }
        if (font == null) {
            font = this.field_146289_q;
        }
        field_146296_j.func_82406_b(font, this.field_146297_k.func_110434_K(), p_146982_1_, p_146982_2_, p_146982_3_);
        field_146296_j.func_94148_a(font, this.field_146297_k.func_110434_K(), p_146982_1_, p_146982_2_, p_146982_3_ - (this.draggedStack == null ? 0 : 8), p_146982_4_);
        this.field_73735_i = 0.0f;
        GuiCustom.field_146296_j.field_77023_b = 0.0f;
    }

    private boolean isMouseOverSlot(Slot slot, int posX, int posY) {
        return this.isPointInRegion(slot.field_75223_e, slot.field_75221_f, 16, 16, posX, posY);
    }

    protected boolean isPointInRegion(int slotDisplayX, int slotDisplayY, int slotSizeX, int slotSizeY, int posX, int posY) {
        int k1 = guiLeft;
        int l1 = guiTop;
        return (posX -= k1) >= slotDisplayX - 1 && posX < slotDisplayX + slotSizeX + 1 && (posY -= l1) >= slotDisplayY - 1 && posY < slotDisplayY + slotSizeY + 1;
    }

    private void drawSlot(Slot p_146977_1_) {
        IIcon iicon;
        int i = p_146977_1_.field_75223_e;
        int j = p_146977_1_.field_75221_f;
        ItemStack itemstack = p_146977_1_.func_75211_c();
        boolean flag = false;
        boolean flag1 = p_146977_1_ == this.clickedSlot && this.draggedStack != null && !this.isRightMouseClick;
        ItemStack itemstack1 = this.field_146297_k.field_71439_g.field_71071_by.func_70445_o();
        String s = null;
        if (p_146977_1_ == this.clickedSlot && this.draggedStack != null && this.isRightMouseClick && itemstack != null) {
            itemstack = itemstack.func_77946_l();
            itemstack.field_77994_a /= 2;
        } else if (this.field_147007_t && this.field_147008_s.contains(p_146977_1_) && itemstack1 != null) {
            if (this.field_147008_s.size() == 1) {
                return;
            }
            if (Container.func_94527_a((Slot)p_146977_1_, (ItemStack)itemstack1, (boolean)true) && this.inventorySlots.func_94531_b(p_146977_1_)) {
                itemstack = itemstack1.func_77946_l();
                flag = true;
                Container.func_94525_a((Set)this.field_147008_s, (int)this.field_146987_F, (ItemStack)itemstack, (int)(p_146977_1_.func_75211_c() == null ? 0 : p_146977_1_.func_75211_c().field_77994_a));
                if (itemstack.field_77994_a > itemstack.func_77976_d()) {
                    s = EnumChatFormatting.YELLOW + "" + itemstack.func_77976_d();
                    itemstack.field_77994_a = itemstack.func_77976_d();
                }
                if (itemstack.field_77994_a > p_146977_1_.func_75219_a()) {
                    s = EnumChatFormatting.YELLOW + "" + p_146977_1_.func_75219_a();
                    itemstack.field_77994_a = p_146977_1_.func_75219_a();
                }
            } else {
                this.field_147008_s.remove(p_146977_1_);
                this.func_146980_g();
            }
        }
        this.field_73735_i = 100.0f;
        GuiCustom.field_146296_j.field_77023_b = 100.0f;
        if (itemstack == null && (iicon = p_146977_1_.func_75212_b()) != null) {
            GL11.glDisable((int)2896);
            GL11.glEnable((int)3042);
            this.field_146297_k.func_110434_K().func_110577_a(TextureMap.field_110576_c);
            this.func_94065_a(i, j, iicon, 16, 16);
            GL11.glDisable((int)3042);
            GL11.glEnable((int)2896);
            flag1 = true;
        }
        if (!flag1) {
            if (flag) {
                GuiCustom.func_73734_a((int)i, (int)j, (int)(i + 16), (int)(j + 16), (int)-2130706433);
            }
            GL11.glEnable((int)2929);
            field_146296_j.func_82406_b(this.field_146289_q, this.field_146297_k.func_110434_K(), itemstack, i, j);
            field_146296_j.func_94148_a(this.field_146289_q, this.field_146297_k.func_110434_K(), itemstack, i, j, s);
        }
        GuiCustom.field_146296_j.field_77023_b = 0.0f;
        this.field_73735_i = 0.0f;
    }

    private void func_146980_g() {
        ItemStack itemstack = this.field_146297_k.field_71439_g.field_71071_by.func_70445_o();
        if (itemstack != null && this.field_147007_t) {
            this.field_146996_I = itemstack.field_77994_a;
            for (Slot slot : this.field_147008_s) {
                ItemStack itemstack1 = itemstack.func_77946_l();
                int i = slot.func_75211_c() == null ? 0 : slot.func_75211_c().field_77994_a;
                Container.func_94525_a((Set)this.field_147008_s, (int)this.field_146987_F, (ItemStack)itemstack1, (int)i);
                if (itemstack1.field_77994_a > itemstack1.func_77976_d()) {
                    itemstack1.field_77994_a = itemstack1.func_77976_d();
                }
                if (itemstack1.field_77994_a > slot.func_75219_a()) {
                    itemstack1.field_77994_a = slot.func_75219_a();
                }
                this.field_146996_I -= itemstack1.field_77994_a - i;
            }
        }
    }

    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
    }

    void drawBackgroundTexture() {
        this.field_146297_k.func_110434_K().func_110577_a(this.background);
        this.func_73729_b(guiLeft, guiTop, 0, 0, this.xSize, this.ySize);
    }

    private void addComponent(ICustomGuiComponent component) {
        ScriptGuiComponent c = (ScriptGuiComponent)component;
        switch (c.getType()) {
            case 0: {
                CustomGuiButton button = CustomGuiButton.fromComponent((ScriptGuiButton)component);
                button.setParent(this);
                this.components.put(button.getID(), button);
                this.addClickListener(button);
                break;
            }
            case 1: {
                CustomGuiLabel lbl = CustomGuiLabel.fromComponent((ScriptGuiLabel)component);
                lbl.setParent(this);
                this.components.put(lbl.getID(), lbl);
                break;
            }
            case 2: {
                CustomGuiTexturedRect rect = CustomGuiTexturedRect.fromComponent((ScriptGuiTexturedRect)component);
                rect.setParent(this);
                this.components.put(rect.getID(), rect);
                break;
            }
            case 3: {
                CustomGuiTextField textField = CustomGuiTextField.fromComponent((ScriptGuiTextField)component);
                textField.setParent(this);
                this.components.put(textField.getID(), textField);
                this.addDataHolder(textField);
                this.addClickListener(textField);
                this.addKeyListener(textField);
                break;
            }
            case 4: {
                CustomGuiScrollComponent scroll = new CustomGuiScrollComponent(this.field_146297_k, this, component.getID(), ((ScriptGuiScroll)component).isMultiSelect());
                scroll.fromComponent((ScriptGuiScroll)component);
                scroll.setParent(this);
                this.components.put(scroll.getID(), scroll);
                this.addDataHolder(scroll);
                this.addClickListener(scroll);
                break;
            }
            case 6: {
                CustomGuiLine line = CustomGuiLine.fromComponent((ScriptGuiLine)component);
                this.components.put(line.getID(), line);
            }
        }
    }

    protected void func_146284_a(GuiButton button) {
        super.func_146284_a(button);
        PacketClient.sendClient(new CustomGuiButtonPacket(button.field_146127_k, this.updateGui().toNBT()));
    }

    public void buttonClick(CustomGuiButton button) {
        PacketClient.sendClient(new CustomGuiButtonPacket(button.field_146127_k, this.updateGui().toNBT()));
    }

    @Override
    public void customScrollClicked(int i, int j, int k, GuiCustomScroll scroll) {
        PacketClient.sendClient(new CustomScrollClickPacket(this.updateGui().toNBT(), scroll.id, scroll.selected, this.getScrollSelection((CustomGuiScrollComponent)scroll), false));
        if (Integer.toString(scroll.selected).equals(this.prevScrollClicked)) {
            PacketClient.sendClient(new CustomScrollClickPacket(this.updateGui().toNBT(), scroll.id, scroll.selected, this.getScrollSelection((CustomGuiScrollComponent)scroll), true));
        }
        this.prevScrollClicked = Integer.toString(scroll.selected);
    }

    public void func_146281_b() {
        if (this.gui != null) {
            PacketClient.sendClient(new CustomGuiClosePacket(this.updateGui().toNBT()));
        }
        if (this.field_146297_k.field_71439_g != null) {
            this.inventorySlots.func_75134_a((EntityPlayer)this.field_146297_k.field_71439_g);
        }
    }

    public void onTextFieldUnfocused(CustomGuiTextField textField) {
        PacketClient.sendClient(new CustomGuiUnfocusedPacket(textField.getID(), this.updateGui().toNBT()));
    }

    public ScriptGui updateGui() {
        for (IDataHolder component : this.dataHolders) {
            this.gui.updateComponent(component.toComponent());
        }
        return this.gui;
    }

    public NBTTagCompound getScrollSelection(CustomGuiScrollComponent scroll) {
        NBTTagList list = new NBTTagList();
        if (scroll.multiSelect) {
            for (String s : scroll.getSelectedList()) {
                list.func_74742_a((NBTBase)new NBTTagString(s));
            }
        } else {
            list.func_74742_a((NBTBase)new NBTTagString(scroll.getSelected()));
        }
        NBTTagCompound selection = new NBTTagCompound();
        selection.func_74782_a("selection", (NBTBase)list);
        return selection;
    }

    protected void func_73869_a(char typedChar, int keyCode) {
        if (keyCode == 1 && this.closeOnEsc) {
            this.field_146297_k.field_71439_g.func_71053_j();
        }
        this.checkHotbarKeys(keyCode);
        if (this.theSlot != null && this.theSlot.func_75216_d()) {
            if (keyCode == this.field_146297_k.field_71474_y.field_74322_I.func_151463_i()) {
                this.handleMouseClick(this.theSlot, this.theSlot.field_75222_d, 0, 3);
            } else if (keyCode == this.field_146297_k.field_71474_y.field_74316_C.func_151463_i()) {
                this.handleMouseClick(this.theSlot, this.theSlot.field_75222_d, GuiCustom.func_146271_m() ? 1 : 0, 4);
            }
        }
        for (CustomGuiTextField listener : this.keyListeners) {
            listener.keyTyped(typedChar, keyCode);
        }
        if (this.field_146297_k.field_71474_y.field_151445_Q.func_151463_i() != keyCode && !this.field_146297_k.field_71474_y.field_151445_Q.func_151470_d()) {
            super.func_73869_a(typedChar, keyCode);
        }
    }

    public void func_73864_a(int mouseX, int mouseY, int mouseButton) {
        super.func_73864_a(mouseX, mouseY, mouseButton);
        boolean flag = mouseButton == this.field_146297_k.field_71474_y.field_74322_I.func_151463_i() + 100;
        Slot slot = this.getSlotAtPosition(mouseX, mouseY);
        long l = Minecraft.func_71386_F();
        this.field_146993_M = this.field_146998_K == slot && l - this.field_146997_J < 250L && this.field_146992_L == mouseButton;
        this.field_146995_H = false;
        if (mouseButton == 0 || mouseButton == 1 || flag) {
            boolean flag1 = true;
            int k1 = -1;
            for (int i = 0; i < this.inventorySlots.field_75151_b.size(); ++i) {
                Slot invSlot = (Slot)this.inventorySlots.field_75151_b.get(i);
                if (!invSlot.equals(slot)) continue;
                flag1 = false;
            }
            if (slot != null) {
                k1 = slot.field_75222_d;
            }
            if (flag1) {
                k1 = -999;
            }
            if (this.field_146297_k.field_71474_y.field_85185_A && flag1 && this.field_146297_k.field_71439_g.field_71071_by.func_70445_o() == null) {
                this.field_146297_k.func_147108_a((GuiScreen)null);
                return;
            }
            if (k1 != -1) {
                if (this.field_146297_k.field_71474_y.field_85185_A) {
                    if (slot != null && slot.func_75216_d()) {
                        this.clickedSlot = slot;
                        this.draggedStack = null;
                        this.isRightMouseClick = mouseButton == 1;
                    } else {
                        this.clickedSlot = null;
                    }
                } else if (!this.field_147007_t) {
                    if (this.field_146297_k.field_71439_g.field_71071_by.func_70445_o() == null) {
                        if (mouseButton == this.field_146297_k.field_71474_y.field_74322_I.func_151463_i() + 100) {
                            this.handleMouseClick(slot, k1, mouseButton, 3);
                        } else {
                            boolean flag2 = k1 != -999 && (Keyboard.isKeyDown((int)42) || Keyboard.isKeyDown((int)54));
                            int b0 = 0;
                            if (flag2) {
                                this.field_146994_N = slot != null && slot.func_75216_d() ? slot.func_75211_c() : null;
                                b0 = 1;
                            } else if (k1 == -999) {
                                b0 = 4;
                            }
                            this.handleMouseClick(slot, k1, mouseButton, b0);
                        }
                        this.field_146995_H = true;
                    } else {
                        this.field_147007_t = true;
                        this.field_146988_G = mouseButton;
                        this.field_147008_s.clear();
                        if (mouseButton == 0) {
                            this.field_146987_F = 0;
                        } else if (mouseButton == 1) {
                            this.field_146987_F = 1;
                        }
                    }
                }
            }
        }
        this.field_146998_K = slot;
        this.field_146997_J = l;
        this.field_146992_L = mouseButton;
        for (IClickListener listener : this.clickListeners) {
            listener.mouseClicked(this, mouseX, mouseY, mouseButton);
        }
    }

    public boolean func_73868_f() {
        return this.gui == null || this.gui.doesPauseGame();
    }

    public void addDataHolder(IDataHolder component) {
        this.dataHolders.add(component);
    }

    public void addKeyListener(CustomGuiTextField component) {
        this.keyListeners.add(component);
    }

    public void addClickListener(IClickListener component) {
        this.clickListeners.add(component);
    }

    @Override
    public void setGuiData(NBTTagCompound compound) {
        Minecraft mc = Minecraft.func_71410_x();
        ScriptGui gui = (ScriptGui)new ScriptGui().fromNBT(compound);
        ((ContainerCustomGui)this.inventorySlots).setGui(gui, (EntityPlayer)mc.field_71439_g);
        this.gui = gui;
        this.xSize = gui.getWidth();
        this.ySize = gui.getHeight();
        this.closeOnEsc = gui.doesCloseOnEscape();
        if (!gui.getBackgroundTexture().isEmpty()) {
            this.background = new ResourceLocation(gui.getBackgroundTexture());
        }
        this.func_73866_w_();
    }

    protected boolean checkHotbarKeys(int p_146983_1_) {
        if (this.field_146297_k.field_71439_g.field_71071_by.func_70445_o() == null && this.theSlot != null) {
            for (int j = 0; j < 9; ++j) {
                if (p_146983_1_ != this.field_146297_k.field_71474_y.field_151456_ac[j].func_151463_i()) continue;
                this.handleMouseClick(this.theSlot, this.theSlot.field_75222_d, j, 2);
                return true;
            }
        }
        return false;
    }

    protected void handleMouseClick(Slot slot, int index, int p_146984_3_, int p_146984_4_) {
        if (slot != null) {
            index = slot.field_75222_d;
        }
        this.field_146297_k.field_71442_b.func_78753_a(this.inventorySlots.field_75152_c, index, p_146984_3_, p_146984_4_, (EntityPlayer)this.field_146297_k.field_71439_g);
    }

    protected void func_146286_b(int p_146286_1_, int p_146286_2_, int p_146286_3_) {
        super.func_146286_b(p_146286_1_, p_146286_2_, p_146286_3_);
        Slot slot = this.getSlotAtPosition(p_146286_1_, p_146286_2_);
        int l = guiLeft;
        int i1 = guiTop;
        boolean flag = p_146286_1_ < l || p_146286_2_ < i1 || p_146286_1_ >= l + this.xSize || p_146286_2_ >= i1 + this.ySize;
        int j1 = -1;
        if (slot != null) {
            j1 = slot.field_75222_d;
        }
        if (flag) {
            j1 = -999;
        }
        if (this.field_146993_M && slot != null && p_146286_3_ == 0 && this.inventorySlots.func_94530_a((ItemStack)null, slot)) {
            if (GuiCustom.func_146272_n()) {
                if (slot != null && slot.field_75224_c != null && this.field_146994_N != null) {
                    for (Slot slot1 : this.inventorySlots.field_75151_b) {
                        if (slot1 == null || !slot1.func_82869_a((EntityPlayer)this.field_146297_k.field_71439_g) || !slot1.func_75216_d() || slot1.field_75224_c != slot.field_75224_c || !Container.func_94527_a((Slot)slot1, (ItemStack)this.field_146994_N, (boolean)true)) continue;
                        this.handleMouseClick(slot1, slot1.field_75222_d, p_146286_3_, 1);
                    }
                }
            } else {
                this.handleMouseClick(slot, j1, p_146286_3_, 6);
            }
            this.field_146993_M = false;
            this.field_146997_J = 0L;
        } else {
            if (this.field_147007_t && this.field_146988_G != p_146286_3_) {
                this.field_147007_t = false;
                this.field_147008_s.clear();
                this.field_146995_H = true;
                return;
            }
            if (this.field_146995_H) {
                this.field_146995_H = false;
                return;
            }
            if (this.clickedSlot != null && this.field_146297_k.field_71474_y.field_85185_A) {
                if (p_146286_3_ == 0 || p_146286_3_ == 1) {
                    if (this.draggedStack == null && slot != this.clickedSlot) {
                        this.draggedStack = this.clickedSlot.func_75211_c();
                    }
                    boolean flag1 = Container.func_94527_a((Slot)slot, (ItemStack)this.draggedStack, (boolean)false);
                    if (j1 != -1 && this.draggedStack != null && flag1) {
                        this.handleMouseClick(this.clickedSlot, this.clickedSlot.field_75222_d, p_146286_3_, 0);
                        this.handleMouseClick(slot, j1, 0, 0);
                        if (this.field_146297_k.field_71439_g.field_71071_by.func_70445_o() != null) {
                            this.handleMouseClick(this.clickedSlot, this.clickedSlot.field_75222_d, p_146286_3_, 0);
                            this.field_147011_y = p_146286_1_ - l;
                            this.field_147010_z = p_146286_2_ - i1;
                            this.returningStackDestSlot = this.clickedSlot;
                            this.returningStack = this.draggedStack;
                            this.returningStackTime = Minecraft.func_71386_F();
                        } else {
                            this.returningStack = null;
                        }
                    } else if (this.draggedStack != null) {
                        this.field_147011_y = p_146286_1_ - l;
                        this.field_147010_z = p_146286_2_ - i1;
                        this.returningStackDestSlot = this.clickedSlot;
                        this.returningStack = this.draggedStack;
                        this.returningStackTime = Minecraft.func_71386_F();
                    }
                    this.draggedStack = null;
                    this.clickedSlot = null;
                }
            } else if (this.field_147007_t && !this.field_147008_s.isEmpty()) {
                this.handleMouseClick(null, -999, Container.func_94534_d((int)0, (int)this.field_146987_F), 5);
                for (Slot slot1 : this.field_147008_s) {
                    this.handleMouseClick(slot1, slot1.field_75222_d, Container.func_94534_d((int)1, (int)this.field_146987_F), 5);
                }
                this.handleMouseClick(null, -999, Container.func_94534_d((int)2, (int)this.field_146987_F), 5);
            } else if (this.field_146297_k.field_71439_g.field_71071_by.func_70445_o() != null) {
                if (p_146286_3_ == this.field_146297_k.field_71474_y.field_74322_I.func_151463_i() + 100) {
                    this.handleMouseClick(slot, j1, p_146286_3_, 3);
                } else {
                    boolean flag1;
                    boolean bl = flag1 = j1 != -999 && (Keyboard.isKeyDown((int)42) || Keyboard.isKeyDown((int)54));
                    if (flag1) {
                        this.field_146994_N = slot != null && slot.func_75216_d() ? slot.func_75211_c() : null;
                    }
                    this.handleMouseClick(slot, j1, p_146286_3_, flag1 ? 1 : 0);
                }
            }
        }
        if (this.field_146297_k.field_71439_g.field_71071_by.func_70445_o() == null) {
            this.field_146997_J = 0L;
        }
        this.field_147007_t = false;
    }

    private Slot getSlotAtPosition(int posX, int posY) {
        for (int k = 0; k < this.inventorySlots.field_75151_b.size(); ++k) {
            Slot slot = (Slot)this.inventorySlots.field_75151_b.get(k);
            if (!this.isMouseOverSlot(slot, posX, posY)) continue;
            return slot;
        }
        return null;
    }

    protected void func_146273_a(int p_146273_1_, int p_146273_2_, int p_146273_3_, long p_146273_4_) {
        Slot slot = this.getSlotAtPosition(p_146273_1_, p_146273_2_);
        ItemStack itemstack = this.field_146297_k.field_71439_g.field_71071_by.func_70445_o();
        if (this.clickedSlot != null && this.field_146297_k.field_71474_y.field_85185_A) {
            if (p_146273_3_ == 0 || p_146273_3_ == 1) {
                if (this.draggedStack == null) {
                    if (slot != this.clickedSlot) {
                        this.draggedStack = this.clickedSlot.func_75211_c().func_77946_l();
                    }
                } else if (this.draggedStack.field_77994_a > 1 && slot != null && Container.func_94527_a((Slot)slot, (ItemStack)this.draggedStack, (boolean)false)) {
                    long i1 = Minecraft.func_71386_F();
                    if (this.field_146985_D == slot) {
                        if (i1 - this.field_146986_E > 500L) {
                            this.handleMouseClick(this.clickedSlot, this.clickedSlot.field_75222_d, 0, 0);
                            this.handleMouseClick(slot, slot.field_75222_d, 1, 0);
                            this.handleMouseClick(this.clickedSlot, this.clickedSlot.field_75222_d, 0, 0);
                            this.field_146986_E = i1 + 750L;
                            --this.draggedStack.field_77994_a;
                        }
                    } else {
                        this.field_146985_D = slot;
                        this.field_146986_E = i1;
                    }
                }
            }
        } else if (this.field_147007_t && slot != null && itemstack != null && itemstack.field_77994_a > this.field_147008_s.size() && Container.func_94527_a((Slot)slot, (ItemStack)itemstack, (boolean)true) && slot.func_75214_a(itemstack) && this.inventorySlots.func_94531_b(slot)) {
            this.field_147008_s.add(slot);
            this.func_146980_g();
        }
    }
}

