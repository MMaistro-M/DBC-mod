/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.gui.global;

import java.util.HashMap;
import java.util.Vector;
import kamkeel.npcs.network.PacketClient;
import kamkeel.npcs.network.packets.request.animation.AnimationClonePacket;
import kamkeel.npcs.network.packets.request.animation.AnimationGetPacket;
import kamkeel.npcs.network.packets.request.animation.AnimationRemovePacket;
import kamkeel.npcs.network.packets.request.animation.AnimationSavePacket;
import kamkeel.npcs.network.packets.request.animation.AnimationsGetPacket;
import kamkeel.npcs.network.packets.request.animation.BuiltInAnimationGetPacket;
import kamkeel.npcs.network.packets.request.category.CategoryItemsRequestPacket;
import kamkeel.npcs.network.packets.request.category.CategoryListRequestPacket;
import kamkeel.npcs.network.packets.request.category.CategoryMoveItemPacket;
import kamkeel.npcs.network.packets.request.category.CategoryRemovePacket;
import kamkeel.npcs.network.packets.request.category.CategorySavePacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import noppes.npcs.client.ClientEventHandler;
import noppes.npcs.client.NoppesUtil;
import noppes.npcs.client.gui.global.GuiNPCEditAnimation;
import noppes.npcs.client.gui.global.GuiNPCManageAnimations;
import noppes.npcs.client.gui.util.GuiCustomScroll;
import noppes.npcs.client.gui.util.GuiDirectoryCategorized;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.GuiTexturedButton;
import noppes.npcs.client.gui.util.GuiUtil;
import noppes.npcs.client.gui.util.SubGuiInterface;
import noppes.npcs.constants.EnumScrollData;
import noppes.npcs.controllers.data.Animation;
import noppes.npcs.controllers.data.AnimationData;
import noppes.npcs.controllers.data.Category;
import noppes.npcs.controllers.data.Frame;
import noppes.npcs.controllers.data.FramePart;
import noppes.npcs.entity.EntityCustomNpc;
import noppes.npcs.entity.EntityNPCInterface;
import org.lwjgl.opengl.GL11;

public class GuiAnimationDirectory
extends GuiDirectoryCategorized {
    public Animation animation = new Animation();
    public EntityNPCInterface npc;
    private EntityNPCInterface originalNpc;
    public boolean playingAnimation = false;
    private long prevTick;
    private boolean showingBuiltIn = false;
    private boolean currentIsBuiltIn = false;
    private HashMap<String, Integer> builtInData = new HashMap();

    public GuiAnimationDirectory(EntityNPCInterface npc) {
        this.originalNpc = npc;
        this.npc = GuiAnimationDirectory.createFakeNPC(npc);
        this.zoomed = 70.0f;
        PacketClient.sendClient(new AnimationsGetPacket());
    }

    private static EntityNPCInterface createFakeNPC(EntityNPCInterface original) {
        EntityCustomNpc fake = new EntityCustomNpc((World)Minecraft.func_71410_x().field_71441_e);
        fake.display.readToNBT(original.display.writeToNBT(new NBTTagCompound()));
        fake.display.name = "anim preview";
        fake.field_70131_O = original.field_70131_O;
        fake.field_70130_N = original.field_70130_N;
        fake.display.animationData.setEnabled(true);
        return fake;
    }

    @Override
    protected boolean hasCategories() {
        return !this.showingBuiltIn;
    }

    @Override
    protected void computeLayout() {
        if (this.showingBuiltIn) {
            this.leftPanelPercent = 0.0f;
            this.minLeftPanelW = 0;
        } else {
            this.leftPanelPercent = 0.15f;
            this.minLeftPanelW = 120;
        }
        super.computeLayout();
    }

    @Override
    protected void drawPanels() {
        if (this.showingBuiltIn) {
            if (this.rightPanelW > 0) {
                GuiUtil.drawRectD(this.rightX - 1, this.contentY - 1, this.rightX + this.rightPanelW + 1, this.originY + this.usableH + 1, this.panelBorder);
            }
        } else {
            super.drawPanels();
        }
    }

    @Override
    protected void initLeftPanel() {
        if (this.showingBuiltIn) {
            return;
        }
        super.initLeftPanel();
    }

    @Override
    protected String getTitle() {
        return this.showingBuiltIn ? "Animations (Built-in)" : "Animations";
    }

    @Override
    protected void requestCategoryList() {
        if (!this.showingBuiltIn) {
            PacketClient.sendClient(new CategoryListRequestPacket(2));
        }
    }

    @Override
    protected void requestItemsInCategory(int catId) {
        if (!this.showingBuiltIn) {
            PacketClient.sendClient(new CategoryItemsRequestPacket(2, catId));
        }
    }

    @Override
    protected void requestItemData(int itemId) {
        PacketClient.sendClient(new AnimationGetPacket(itemId));
    }

    @Override
    protected void onSaveCategory(Category cat) {
        PacketClient.sendClient(new CategorySavePacket(2, cat.writeNBT(new NBTTagCompound())));
    }

    @Override
    protected void onRemoveCategory(int catId) {
        PacketClient.sendClient(new CategoryRemovePacket(2, catId));
    }

    @Override
    protected void onAddItem(int catId) {
        if (this.showingBuiltIn) {
            return;
        }
        String name = "New";
        while (this.itemData.containsKey(name)) {
            name = name + "_";
        }
        Animation newAnim = new Animation(-1, name);
        PacketClient.sendClient(new AnimationSavePacket(newAnim.writeToNBT()));
    }

    @Override
    protected void onRemoveItem(int itemId) {
        if (this.showingBuiltIn) {
            return;
        }
        PacketClient.sendClient(new AnimationRemovePacket(itemId));
        this.animation = new Animation();
    }

    @Override
    protected void onEditItem() {
        if (this.animation != null && this.animation.id >= 0 && !this.currentIsBuiltIn) {
            NoppesUtil.openGUI((EntityPlayer)this.player, new GuiNPCEditAnimation(this, this.animation, this.npc));
        }
    }

    @Override
    protected void onCloneItem() {
        if (this.showingBuiltIn || this.currentIsBuiltIn) {
            return;
        }
        if (this.animation != null && this.animation.id >= 0) {
            PacketClient.sendClient(new AnimationClonePacket(this.animation.id));
        }
    }

    @Override
    protected void onItemReceived(NBTTagCompound compound) {
        this.animation = new Animation();
        this.animation.readFromNBT(compound);
        this.setPrevItemName(this.animation.name);
        this.currentIsBuiltIn = compound.func_74764_b("BuiltIn") && compound.func_74767_n("BuiltIn");
        this.playingAnimation = false;
        this.showFirstFrame();
    }

    private void showFirstFrame() {
        if (this.animation.id != -1 || this.currentIsBuiltIn) {
            AnimationData data = this.npc.display.animationData;
            data.setAnimation(new Animation());
            data.animation.smooth = this.animation.smooth;
            data.animation.loop = 0;
            if (!this.animation.frames.isEmpty()) {
                Frame firstFrame = new Frame();
                firstFrame.parent = data.animation;
                firstFrame.readFromNBT(this.animation.frames.get(0).writeToNBT());
                data.animation.addFrame(firstFrame);
            }
        }
    }

    @Override
    protected boolean hasSelectedItem() {
        if (this.currentIsBuiltIn) {
            return true;
        }
        return this.animation != null && this.animation.id >= 0;
    }

    @Override
    protected int getSelectedItemId() {
        return this.animation != null ? this.animation.id : -1;
    }

    @Override
    protected void sendMovePacket(int itemId, int destCatId) {
        PacketClient.sendClient(new CategoryMoveItemPacket(2, itemId, destCatId));
    }

    @Override
    protected GuiScreen getWindowedVariant() {
        return new GuiNPCManageAnimations(this.originalNpc, false, false);
    }

    @Override
    protected void saveCurrentItem() {
        if (!this.currentIsBuiltIn && this.animation != null && this.animation.id >= 0 && this.prevItemName != null && !this.prevItemName.isEmpty()) {
            PacketClient.sendClient(new AnimationSavePacket(this.animation.writeToNBT()));
            this.prevItemName = this.animation.name;
        }
    }

    @Override
    protected void onSubGuiClosed(SubGuiInterface subgui) {
        if (this.animation != null && this.animation.id >= 0) {
            this.setPrevItemName(this.animation.name);
            if (this.selectedCatId >= 0 && !this.showingBuiltIn) {
                this.requestItemsInCategory(this.selectedCatId);
            }
        }
    }

    @Override
    protected int initExtraTopBarButtons(int x, int topBtnY) {
        String toggleLabel = this.showingBuiltIn ? "gui.builtin" : "gui.custom";
        GuiNpcButton toggleBtn = new GuiNpcButton(60, x, topBtnY, 55, this.btnH, toggleLabel);
        toggleBtn.setTextColor(this.showingBuiltIn ? 0x55FFFF : 0xFFFFFF);
        this.addButton(toggleBtn);
        return x + 55 + 2;
    }

    @Override
    protected void initTopBar(int topBtnY) {
        super.initTopBar(topBtnY);
        if (this.showingBuiltIn) {
            if (this.getButton(50) != null) {
                this.getButton((int)50).field_146124_l = false;
            }
            if (this.getButton(54) != null) {
                this.getButton((int)54).field_146124_l = false;
            }
        }
    }

    @Override
    protected void initRightPanel(int startY) {
        boolean hasPlayback;
        int bottomRows = 2;
        boolean bl = hasPlayback = this.animation != null && !this.animation.frames.isEmpty() && this.hasSelectedItem();
        if (hasPlayback) {
            ++bottomRows;
            if (this.playingAnimation) {
                ++bottomRows;
            }
        }
        int bottomH = bottomRows * (this.btnH + this.gap) + 14;
        this.previewX = this.rightX;
        this.previewY = this.contentY;
        this.previewW = this.rightPanelW;
        this.previewH = this.contentH - bottomH - this.gap;
        if (hasPlayback) {
            int playY = this.contentY + this.previewH + this.gap;
            String animTexture = "customnpcs:textures/gui/animation.png";
            AnimationData data = this.npc.display.animationData;
            int btnX = this.rightX + 4;
            if (!this.playingAnimation || data.animation != null && data.animation.paused) {
                String statusKey = data.animation != null && data.animation.paused ? "animation.paused" : "animation.stopped";
                this.addLabel(new GuiNpcLabel(90, statusKey, btnX, playY + 5, 0xFFFFFF));
                this.addButton(new GuiTexturedButton(91, "", btnX + 65, playY, 11, 20, animTexture, 18, 71));
            } else {
                this.addLabel(new GuiNpcLabel(90, "animation.playing", btnX, playY + 5, 0xFFFFFF));
                this.addButton(new GuiTexturedButton(92, "", btnX + 65, playY, 14, 20, animTexture, 0, 71));
            }
            if (this.playingAnimation) {
                this.addButton(new GuiTexturedButton(93, "", btnX + 85, playY, 14, 20, animTexture, 33, 71));
                int frameInfoY = playY + this.btnH + this.gap;
                this.addLabel(new GuiNpcLabel(94, "", btnX, frameInfoY + 5, 0xFFFFFF));
            }
        }
        int btnY = this.contentY + this.contentH - this.btnH * 2 - this.gap;
        int halfW = (this.rightPanelW - this.gap) / 2;
        GuiNpcButton editBtn = new GuiNpcButton(51, this.rightX, btnY, halfW, this.btnH, "gui.edit");
        editBtn.field_146124_l = this.hasSelectedItem() && this.movePhase == 0 && !this.currentIsBuiltIn;
        this.addButton(editBtn);
        GuiNpcButton cloneBtn = new GuiNpcButton(52, this.rightX + halfW + this.gap, btnY, halfW, this.btnH, "gui.copy");
        cloneBtn.field_146124_l = this.hasSelectedItem() && this.movePhase == 0 && !this.currentIsBuiltIn;
        this.addButton(cloneBtn);
        int removeY = btnY + this.btnH + this.gap;
        GuiNpcButton removeBtn = new GuiNpcButton(53, this.rightX, removeY, this.rightPanelW, this.btnH, "gui.remove");
        removeBtn.field_146124_l = this.hasSelectedItem() && this.movePhase == 0 && !this.currentIsBuiltIn;
        removeBtn.setTextColor(0xFF5555);
        this.addButton(removeBtn);
    }

    @Override
    public void customScrollClicked(int i, int j, int k, GuiCustomScroll scroll) {
        if (scroll.id == 0 && this.showingBuiltIn) {
            String selected = this.itemScroll.getSelected();
            if (selected != null && !selected.equals(this.prevItemName)) {
                PacketClient.sendClient(new BuiltInAnimationGetPacket(selected));
                this.prevItemName = selected;
            }
            return;
        }
        super.customScrollClicked(i, j, k, scroll);
    }

    @Override
    public void setData(Vector<String> list, HashMap<String, Integer> data, EnumScrollData type) {
        if (type == EnumScrollData.BUILTIN_ANIMATIONS) {
            this.builtInData = data;
            if (this.showingBuiltIn) {
                this.itemData = data;
                this.itemScroll.setList(this.getItemSearchList());
                this.func_73866_w_();
            }
            return;
        }
        super.setData(list, data, type);
    }

    @Override
    public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
        AnimationData data = this.npc.display.animationData;
        if (!data.isActive() && this.playingAnimation) {
            this.playingAnimation = false;
            this.func_73866_w_();
        } else if (data.isActive()) {
            long time = this.field_146297_k.field_71441_e.func_82737_E();
            if (time != this.prevTick) {
                this.npc.display.animationData.increaseTime();
                GuiNpcLabel label = this.getLabel(94);
                if (label != null && data.animation != null) {
                    int frameIdx = data.animation.currentFrame;
                    int frameTime = data.animation.currentFrameTime;
                    int totalFrames = data.animation.frames.size();
                    Frame curFrame = (Frame)data.animation.currentFrame();
                    int duration = curFrame != null ? curFrame.getDuration() : 0;
                    label.label = frameIdx + 1 + "/" + totalFrames + " (" + frameTime + "/" + duration + "t)";
                }
            }
            this.prevTick = time;
        }
        super.func_73863_a(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void func_146284_a(GuiButton guibutton) {
        int id = guibutton.field_146127_k;
        if (id == 60) {
            this.showingBuiltIn = !this.showingBuiltIn;
            this.animation = new Animation();
            this.currentIsBuiltIn = false;
            this.playingAnimation = false;
            this.prevItemName = "";
            this.itemSearch = "";
            if (this.showingBuiltIn) {
                this.itemData = this.builtInData;
                this.selectedCatId = 0;
            } else {
                this.itemData = new HashMap();
                this.selectedCatId = -1;
                this.requestCategoryList();
            }
            this.func_73866_w_();
            return;
        }
        AnimationData data = this.npc.display.animationData;
        if (id == 91) {
            if (!this.playingAnimation || !data.isActive()) {
                this.animation.currentFrame = 0;
                this.animation.currentFrameTime = 0;
                for (Frame frame : this.animation.frames) {
                    for (FramePart framePart : frame.frameParts.values()) {
                        framePart.prevRotations = new float[]{0.0f, 0.0f, 0.0f};
                        framePart.prevPivots = new float[]{0.0f, 0.0f, 0.0f};
                    }
                }
            }
            this.playingAnimation = true;
            data.setAnimation(this.animation);
            data.animation.paused = false;
            this.func_73866_w_();
            return;
        }
        if (id == 92) {
            data.animation.paused = true;
            this.func_73866_w_();
            return;
        }
        if (id == 93) {
            this.playingAnimation = false;
            data.animation.paused = false;
            this.showFirstFrame();
            this.func_73866_w_();
            return;
        }
        super.func_146284_a(guibutton);
    }

    @Override
    protected void drawItemPreview(int centerX, int centerY, int mouseX, int mouseY, float partialTicks) {
        if (this.animation == null) {
            return;
        }
        if (!this.currentIsBuiltIn && this.animation.id == -1) {
            return;
        }
        float scale = (float)Math.min(this.previewW, this.previewH) / 200.0f;
        float renderZoom = this.zoomed * scale;
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        EntityNPCInterface entity = this.npc;
        GL11.glEnable((int)2903);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)centerX, (float)centerY, (float)60.0f);
        GL11.glScalef((float)(-renderZoom), (float)renderZoom, (float)renderZoom);
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        float f2 = ((EntityLivingBase)entity).field_70761_aq;
        float f3 = ((EntityLivingBase)entity).field_70177_z;
        float f4 = ((EntityLivingBase)entity).field_70125_A;
        float f7 = ((EntityLivingBase)entity).field_70759_as;
        float f5 = (float)centerX - (float)mouseX;
        float f6 = (float)(centerY - 50) - (float)mouseY;
        GL11.glRotatef((float)135.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        RenderHelper.func_74519_b();
        GL11.glRotatef((float)-135.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)(-((float)Math.atan(f6 / 800.0f)) * 20.0f), (float)1.0f, (float)0.0f, (float)0.0f);
        ((EntityLivingBase)entity).field_70760_ar = ((EntityLivingBase)entity).field_70761_aq = this.rotation;
        ((EntityLivingBase)entity).field_70126_B = ((EntityLivingBase)entity).field_70177_z = (float)Math.atan(f5 / 80.0f) * 40.0f + this.rotation;
        ((EntityLivingBase)entity).field_70125_A = -((float)Math.atan(f6 / 80.0f)) * 20.0f;
        ((EntityLivingBase)entity).field_70758_at = ((EntityLivingBase)entity).field_70759_as = ((EntityLivingBase)entity).field_70177_z;
        GL11.glTranslatef((float)0.0f, (float)((EntityLivingBase)entity).field_70129_M, (float)1.0f);
        RenderManager.field_78727_a.field_78735_i = 180.0f;
        ClientEventHandler.renderingEntityInGUI = true;
        try {
            RenderManager.field_78727_a.func_147940_a((Entity)entity, 0.0, 0.0, 0.0, 0.0f, 1.0f);
        }
        catch (Exception exception) {
            // empty catch block
        }
        ClientEventHandler.renderingEntityInGUI = false;
        ((EntityLivingBase)entity).field_70760_ar = ((EntityLivingBase)entity).field_70761_aq = f2;
        ((EntityLivingBase)entity).field_70126_B = ((EntityLivingBase)entity).field_70177_z = f3;
        ((EntityLivingBase)entity).field_70125_A = f4;
        ((EntityLivingBase)entity).field_70758_at = ((EntityLivingBase)entity).field_70759_as = f7;
        GL11.glPopMatrix();
        RenderHelper.func_74518_a();
        GL11.glDisable((int)32826);
        OpenGlHelper.func_77473_a((int)OpenGlHelper.field_77476_b);
        GL11.glDisable((int)3553);
        OpenGlHelper.func_77473_a((int)OpenGlHelper.field_77478_a);
        GL11.glClear((int)256);
    }

    @Override
    protected void drawItemDetails(int x, int y, int w) {
        if (this.animation == null) {
            return;
        }
        if (!this.currentIsBuiltIn && this.animation.id == -1) {
            return;
        }
        this.field_146289_q.func_85187_a(this.animation.name, x, y, 0xFFFFFF, true);
        this.field_146289_q.func_85187_a(StatCollector.func_74838_a((String)"animation.frames") + ": " + this.animation.frames.size(), x, y += 14, 0xB5B5B5, false);
        this.field_146289_q.func_85187_a(StatCollector.func_74838_a((String)"stats.speed") + ": " + this.animation.speed, x, y += 12, 0xB5B5B5, false);
        String loopStr = this.animation.loop == 0 ? StatCollector.func_74838_a((String)"gui.none") : (this.animation.loop == 1 ? StatCollector.func_74838_a((String)"animation.loop") : StatCollector.func_74838_a((String)"animation.mirror"));
        this.field_146289_q.func_85187_a(StatCollector.func_74838_a((String)"animation.loop") + ": " + loopStr, x, y += 12, 0xB5B5B5, false);
        if (this.currentIsBuiltIn) {
            this.field_146289_q.func_85187_a(StatCollector.func_74838_a((String)"gui.builtin.tag"), x, y += 12, 0x55FF55, false);
        }
    }

    @Override
    public void func_146281_b() {
        if (this.npc != null) {
            this.npc.display.animationData.setEnabled(false);
        }
        super.func_146281_b();
    }
}

