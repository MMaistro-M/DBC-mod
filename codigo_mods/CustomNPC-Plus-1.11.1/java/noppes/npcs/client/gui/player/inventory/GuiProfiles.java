/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.GuiYesNo
 *  net.minecraft.client.gui.GuiYesNoCallback
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 *  org.lwjgl.input.Mouse
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.gui.player.inventory;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import kamkeel.npcs.controllers.data.profile.Profile;
import kamkeel.npcs.controllers.data.profile.ProfileInfoEntry;
import kamkeel.npcs.controllers.data.profile.Slot;
import kamkeel.npcs.network.PacketClient;
import kamkeel.npcs.network.packets.player.profile.ProfileChangePacket;
import kamkeel.npcs.network.packets.player.profile.ProfileCreatePacket;
import kamkeel.npcs.network.packets.player.profile.ProfileGetInfoPacket;
import kamkeel.npcs.network.packets.player.profile.ProfileGetPacket;
import kamkeel.npcs.network.packets.player.profile.ProfileRemovePacket;
import kamkeel.npcs.network.packets.player.profile.ProfileRenamePacket;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import noppes.npcs.api.handler.data.ISlot;
import noppes.npcs.client.CustomNpcResourceListener;
import noppes.npcs.client.NoppesUtil;
import noppes.npcs.client.gui.SubGuiEditText;
import noppes.npcs.client.gui.player.inventory.GuiCNPCInventory;
import noppes.npcs.client.gui.util.GuiCustomScroll;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.ICustomScrollListener;
import noppes.npcs.client.gui.util.IGuiData;
import noppes.npcs.client.gui.util.ISubGuiListener;
import noppes.npcs.client.gui.util.SubGuiInterface;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import tconstruct.client.tabs.AbstractTab;

public class GuiProfiles
extends GuiCNPCInventory
implements ISubGuiListener,
ICustomScrollListener,
IGuiData,
GuiYesNoCallback {
    private final ResourceLocation resource = new ResourceLocation("customnpcs", "textures/gui/standardbg.png");
    private GuiCustomScroll scroll;
    public HashMap<String, Integer> data = new HashMap();
    private String selected = null;
    private String rename;
    private Profile profile;
    private Slot slot;
    private HashMap<Integer, List<ProfileInfoEntry>> slotInfoMap = new HashMap();
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy HH:mm");
    private int scrollY = 0;
    private int maxScrollY = 0;
    private boolean draggingScrollbar = false;
    private int lastMouseY = 0;

    public GuiProfiles() {
        this.xSize = 280;
        this.ySize = 180;
        this.drawDefaultBackground = false;
        this.title = "";
        PacketClient.sendClient(new ProfileGetPacket());
        PacketClient.sendClient(new ProfileGetInfoPacket());
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        int y = this.guiTop + 4;
        this.addButton(new GuiNpcButton(1, this.guiLeft + 4, y += 144, 60, 20, "gui.change"));
        this.addButton(new GuiNpcButton(2, this.guiLeft + 4 + 63, y, 60, 20, "gui.rename"));
        this.addButton(new GuiNpcButton(3, this.guiLeft + 4 + 63, y += 22, 60, 20, "gui.remove"));
        this.addButton(new GuiNpcButton(4, this.guiLeft + 4, y, 60, 20, "gui.create"));
        if (this.scroll == null) {
            this.scroll = new GuiCustomScroll(this, 0);
            this.scroll.setSize(123, 141);
        }
        this.scroll.guiLeft = this.guiLeft + 4;
        this.scroll.guiTop = this.guiTop + 4;
        this.scroll.setList(new ArrayList<String>(this.data.keySet()));
        this.addScroll(this.scroll);
    }

    @Override
    protected void func_146284_a(GuiButton guibutton) {
        GuiYesNo guiyesno;
        if (guibutton instanceof AbstractTab) {
            return;
        }
        if (guibutton.field_146127_k <= -100) {
            super.func_146284_a(guibutton);
            return;
        }
        if (guibutton.field_146127_k == 4) {
            guiyesno = new GuiYesNo((GuiYesNoCallback)this, StatCollector.func_74838_a((String)"profile.create.message"), StatCollector.func_74838_a((String)"gui.sure"), 104);
            this.displayGuiScreen((GuiScreen)guiyesno);
        }
        if (this.slot == null) {
            return;
        }
        if (guibutton.field_146127_k == 1) {
            guiyesno = new GuiYesNo((GuiYesNoCallback)this, this.slot.getName(), StatCollector.func_74838_a((String)"profile.change.message"), 101);
            this.displayGuiScreen((GuiScreen)guiyesno);
        }
        if (guibutton.field_146127_k == 2) {
            this.setSubGui(new SubGuiEditText(this.slot.getName()));
        }
        if (guibutton.field_146127_k == 3) {
            guiyesno = new GuiYesNo((GuiYesNoCallback)this, this.slot.getName(), StatCollector.func_74838_a((String)"gui.delete"), 103);
            this.displayGuiScreen((GuiScreen)guiyesno);
        }
    }

    @Override
    public void func_73863_a(int mouseX, int mouseY, float f) {
        this.func_146276_q_();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.field_146297_k.field_71446_o.func_110577_a(this.resource);
        this.func_73729_b(this.guiLeft, this.guiTop, 0, 0, 252, 195);
        this.func_73729_b(this.guiLeft + 252, this.guiTop, 188, 0, 67, 195);
        super.func_73863_a(mouseX, mouseY, f);
        if (this.hasSubGui()) {
            return;
        }
        this.renderScrollableScreen(mouseX, mouseY);
    }

    private void renderScrollableScreen(int mouseX, int mouseY) {
        this.func_73733_a(this.guiLeft + 133, this.guiTop + 4, this.guiLeft + this.xSize + 33, this.guiTop + 24, -1072689136, -1072689136);
        this.func_73730_a(this.guiLeft + 133, this.guiLeft + this.xSize + 33, this.guiTop + 25, -16777216 + CustomNpcResourceListener.DefaultTextColor);
        this.func_73733_a(this.guiLeft + 133, this.guiTop + 27, this.guiLeft + this.xSize + 33, this.guiTop + this.ySize + 9, -1609560048, -1609560048);
        if (this.profile != null && this.slot != null) {
            String topBarText = this.slot.getName();
            int textWidth = this.field_146289_q.func_78256_a(topBarText);
            int barCenterX = this.guiLeft + 133 + (this.xSize + 33 - 133) / 2;
            int textX = barCenterX - textWidth / 2;
            this.field_146289_q.func_85187_a(topBarText, textX, this.guiTop + 10, 0xFFFFFF, true);
            GL11.glEnable((int)3089);
            ScaledResolution sr = new ScaledResolution(this.field_146297_k, this.field_146297_k.field_71443_c, this.field_146297_k.field_71440_d);
            int scaleFactor = sr.func_78325_e();
            int scissorX = (this.guiLeft + 133) * scaleFactor;
            int scissorY = (sr.func_78328_b() - (this.guiTop + this.ySize + 6)) * scaleFactor;
            int scissorW = (this.xSize + 33 - 133) * scaleFactor;
            int scissorH = (this.ySize + 4 - 27) * scaleFactor;
            GL11.glScissor((int)scissorX, (int)scissorY, (int)scissorW, (int)scissorH);
            GL11.glPushMatrix();
            GL11.glTranslatef((float)0.0f, (float)(-this.scrollY), (float)0.0f);
            int spacing = 14;
            int y = this.guiTop + 30;
            int xLabel = this.guiLeft + 136;
            int boxRightX = this.guiLeft + this.xSize + 30;
            String label = StatCollector.func_74838_a((String)"profile.lastLoaded");
            String result = DATE_FORMAT.format(new Date(this.slot.getLastLoaded()));
            int labelColor = 16751659;
            int resultColor = 16767833;
            this.field_146289_q.func_85187_a(label, xLabel, y, labelColor, false);
            int resultWidth = this.field_146289_q.func_78256_a(result);
            int resultX = boxRightX - resultWidth - 5;
            this.field_146289_q.func_85187_a(result, resultX, y, resultColor, false);
            y += spacing;
            if (this.slot.isTemporary()) {
                label = StatCollector.func_74838_a((String)"profile.temporary");
                result = ("" + this.slot.isTemporary()).toUpperCase();
                labelColor = 16539869;
                resultColor = 3538049;
                this.field_146289_q.func_85187_a(label, xLabel, y, labelColor, false);
                resultWidth = this.field_146289_q.func_78256_a(result);
                resultX = boxRightX - resultWidth - 5;
                this.field_146289_q.func_85187_a(result, resultX, y, resultColor, false);
                y += spacing;
            }
            if (this.slotInfoMap.containsKey(this.slot.getId())) {
                List<ProfileInfoEntry> infoEntries = this.slotInfoMap.get(this.slot.getId());
                for (ProfileInfoEntry entry : infoEntries) {
                    label = StatCollector.func_74838_a((String)entry.getLabel());
                    result = StatCollector.func_74838_a((String)entry.getResult());
                    labelColor = entry.getLabelColor();
                    resultColor = entry.getResultColor();
                    this.field_146289_q.func_85187_a(label, xLabel, y, labelColor, false);
                    resultWidth = this.field_146289_q.func_78256_a(result);
                    resultX = boxRightX - resultWidth - 5;
                    this.field_146289_q.func_85187_a(result, resultX, y, resultColor, false);
                    y += spacing;
                }
            }
            this.maxScrollY = Math.max(0, y - (this.guiTop + this.ySize - 35));
            GL11.glPopMatrix();
            GL11.glDisable((int)3089);
        }
        if (this.maxScrollY > 0) {
            this.drawScrollbar();
        }
    }

    @Override
    public void func_146274_d() {
        int delta;
        super.func_146274_d();
        if (this.isMouseOverScrollBox(this.mouseX, this.mouseY) && (delta = Mouse.getEventDWheel()) != 0) {
            this.scrollY -= delta / 7;
            if (this.scrollY < 0) {
                this.scrollY = 0;
            }
            if (this.scrollY > this.maxScrollY) {
                this.scrollY = this.maxScrollY;
            }
        }
        if (this.draggingScrollbar) {
            if (Mouse.isButtonDown((int)0)) {
                int mouseDiff = this.mouseY - this.lastMouseY;
                this.scrollY += mouseDiff * this.maxScrollY / (this.ySize - 70);
                this.scrollY = Math.max(0, Math.min(this.scrollY, this.maxScrollY));
                this.lastMouseY = this.mouseY;
            } else {
                this.draggingScrollbar = false;
            }
        }
    }

    private void drawScrollbar() {
        int scrollbarX = this.guiLeft + this.xSize + 30;
        int scrollbarY = this.guiTop + 27;
        int scrollbarHeight = this.ySize - 18;
        int thumbHeight = Math.max(10, scrollbarHeight * scrollbarHeight / (this.maxScrollY + scrollbarHeight));
        int thumbY = scrollbarY + this.scrollY * (scrollbarHeight - thumbHeight) / this.maxScrollY;
        GuiProfiles.func_73734_a((int)scrollbarX, (int)scrollbarY, (int)(scrollbarX + 5), (int)(scrollbarY + scrollbarHeight), (int)-13421773);
        GuiProfiles.func_73734_a((int)scrollbarX, (int)thumbY, (int)(scrollbarX + 5), (int)(thumbY + thumbHeight), (int)-5592406);
    }

    private boolean isMouseOverScrollBox(int mouseX, int mouseY) {
        return mouseX >= this.guiLeft + 133 && mouseX <= this.guiLeft + this.xSize + 33 && mouseY >= this.guiTop + 27 && mouseY <= this.guiTop + this.ySize + 9;
    }

    @Override
    public void func_73869_a(char c, int i) {
        super.func_73869_a(c, i);
        if (i == 1 || !this.hasSubGui() && this.isInventoryKey(i)) {
            this.close();
        }
    }

    @Override
    public void save() {
    }

    public void func_73878_a(boolean result, int id) {
        NoppesUtil.openGUI((EntityPlayer)this.player, this);
        if (!result) {
            return;
        }
        if (id == 104) {
            PacketClient.sendClient(new ProfileCreatePacket());
        }
        if (this.slot == null) {
            return;
        }
        if (id == 101) {
            PacketClient.sendClient(new ProfileChangePacket(this.slot.getId()));
        }
        if (id == 102 && this.rename != null && !this.rename.equalsIgnoreCase(this.slot.getName())) {
            PacketClient.sendClient(new ProfileRenamePacket(this.slot.getId(), this.rename));
        }
        if (id == 103) {
            PacketClient.sendClient(new ProfileRemovePacket(this.slot.getId()));
        }
    }

    @Override
    public void subGuiClosed(SubGuiInterface subgui) {
        if (subgui instanceof SubGuiEditText && !((SubGuiEditText)subgui).cancelled) {
            this.rename = ((SubGuiEditText)subgui).text;
            GuiYesNo guiyesno = new GuiYesNo((GuiYesNoCallback)this, this.rename, StatCollector.func_74838_a((String)"profile.rename.message"), 102);
            this.displayGuiScreen((GuiScreen)guiyesno);
        }
    }

    @Override
    public void customScrollClicked(int i, int j, int k, GuiCustomScroll guiCustomScroll) {
        if (guiCustomScroll.id == 0) {
            this.slot = null;
            this.selected = this.scroll.getSelected();
            if (this.profile != null && this.selected != null && !this.selected.isEmpty()) {
                for (ISlot checkSlot : this.profile.getSlots().values()) {
                    if (checkSlot.getId() != this.data.get(this.selected).intValue()) continue;
                    this.slot = (Slot)checkSlot;
                    break;
                }
            }
        }
    }

    @Override
    public void customScrollDoubleClicked(String selection, GuiCustomScroll scroll) {
    }

    @Override
    public void setGuiData(NBTTagCompound compound) {
        this.slot = null;
        if (compound.func_74764_b("CHANGE_PACKET")) {
            this.readProfileCompound(compound.func_74775_l("PROFILE"));
            this.slotInfoMap = ProfileGetInfoPacket.readProfileInfo(compound.func_74775_l("PROFILE_INFO"));
        } else if (compound.func_74764_b("PROFILE")) {
            this.readProfileCompound(compound);
        } else if (compound.func_74764_b("PROFILE_INFO")) {
            this.slotInfoMap = ProfileGetInfoPacket.readProfileInfo(compound);
        }
        if (this.scroll != null) {
            this.scroll.setSelected("");
        }
        this.func_73866_w_();
    }

    private void readProfileCompound(NBTTagCompound compound) {
        this.profile = new Profile((EntityPlayer)this.field_146297_k.field_71439_g, compound);
        this.data = new HashMap();
        String currentSlot = "\u00a7e";
        String otherSlot = "\u00a7f";
        for (ISlot slot1 : this.profile.getSlots().values()) {
            String name = slot1.getId() + " - " + slot1.getName();
            name = this.profile.currentSlotId == slot1.getId() ? currentSlot + name : otherSlot + name;
            this.data.put(name, slot1.getId());
        }
    }

    @Override
    public void func_73864_a(int mouseX, int mouseY, int button) {
        super.func_73864_a(mouseX, mouseY, button);
        if (button == 0 && this.isMouseOverScrollbar(mouseX, mouseY)) {
            int scrollbarY = this.guiTop + 27;
            int scrollbarHeight = this.ySize - 35;
            int thumbHeight = Math.max(10, scrollbarHeight * scrollbarHeight / (this.maxScrollY + scrollbarHeight));
            int thumbY = scrollbarY + this.scrollY * (scrollbarHeight - thumbHeight) / this.maxScrollY;
            if (mouseY >= thumbY && mouseY <= thumbY + thumbHeight) {
                this.draggingScrollbar = true;
                this.lastMouseY = mouseY;
            } else {
                this.scrollY = (mouseY - scrollbarY) * this.maxScrollY / scrollbarHeight;
                this.scrollY = Math.max(0, Math.min(this.scrollY, this.maxScrollY));
            }
        }
    }

    @Override
    public void func_146273_a(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
        if (this.draggingScrollbar) {
            int mouseDiff = mouseY - this.lastMouseY;
            this.scrollY += mouseDiff * this.maxScrollY / (this.ySize - 70);
            this.scrollY = Math.max(0, Math.min(this.scrollY, this.maxScrollY));
            this.lastMouseY = mouseY;
        }
    }

    @Override
    protected void func_146286_b(int mouseX, int mouseY, int state) {
        super.func_146286_b(mouseX, mouseY, state);
        if (state == 0) {
            this.draggingScrollbar = false;
        }
    }

    private boolean isMouseOverScrollbar(int mouseX, int mouseY) {
        int scrollbarX = this.guiLeft + this.xSize + 30;
        int scrollbarY = this.guiTop + 27;
        int scrollbarHeight = this.ySize - 35;
        return mouseX >= scrollbarX && mouseX <= scrollbarX + 5 && mouseY >= scrollbarY && mouseY <= scrollbarY + scrollbarHeight;
    }
}

