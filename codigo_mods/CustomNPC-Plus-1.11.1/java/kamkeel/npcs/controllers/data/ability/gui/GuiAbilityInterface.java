/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  org.lwjgl.input.Mouse
 *  org.lwjgl.opengl.GL11
 */
package kamkeel.npcs.controllers.data.ability.gui;

import java.util.ArrayList;
import java.util.List;
import kamkeel.npcs.client.renderer.TelegraphRenderer;
import kamkeel.npcs.controllers.data.telegraph.TelegraphInstance;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import noppes.npcs.client.ClientEventHandler;
import noppes.npcs.client.gui.util.GuiNPCInterface2;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.entity.EntityNPCInterface;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class GuiAbilityInterface
extends GuiNPCInterface2 {
    protected static float CAMERA_ZOOM = 45.0f;
    public static float NPC_FACING_YAW = 160.0f;
    protected static float CAMERA_PITCH = 10.0f;
    protected static float viewRotation = 0.0f;
    private GuiNpcButton btnLeft;
    private GuiNpcButton btnRight;
    public EntityNPCInterface npc;
    protected double npcStartX;
    protected double npcStartY;
    protected double npcStartZ;
    protected boolean trackingNpcMovement = false;
    protected List<Entity> previewEntities = new ArrayList<Entity>();
    protected TelegraphInstance previewTelegraph;
    protected int previewX;
    protected int previewY;
    protected int previewWidth;
    protected int previewHeight;
    protected int npcScreenX;
    protected int npcScreenY;
    public int xOffset = 0;
    public int yOffset = 0;

    public GuiAbilityInterface(EntityNPCInterface npc) {
        this(npc, true);
    }

    public GuiAbilityInterface(EntityNPCInterface npc, boolean hasMenuNpc) {
        super(hasMenuNpc ? npc : null);
        this.npc = npc;
        this.drawDefaultBackground = false;
        CAMERA_ZOOM = 30.0f;
        NPC_FACING_YAW = 310.0f;
        CAMERA_PITCH = 5.0f;
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        this.previewX = this.guiLeft + 10;
        this.previewY = this.guiTop + 10;
        this.previewWidth = 200;
        this.previewHeight = 180;
        this.npcScreenX = this.previewX + (int)((float)this.previewWidth * 0.33f) + this.xOffset;
        this.npcScreenY = this.previewY + (int)((float)this.previewHeight * 0.85f) + this.yOffset;
        int btnY = this.guiTop + 192;
        this.btnLeft = new GuiNpcButton(680, this.guiLeft + 10, btnY, 20, 20, "<");
        this.addButton(this.btnLeft);
        this.btnRight = new GuiNpcButton(681, this.guiLeft + 32, btnY, 20, 20, ">");
        this.addButton(this.btnRight);
    }

    @Override
    public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
        if (Mouse.isButtonDown((int)0)) {
            if (this.btnLeft != null && this.btnLeft.func_146116_c(this.field_146297_k, mouseX, mouseY)) {
                viewRotation += partialTicks * 2.0f;
            } else if (this.btnRight != null && this.btnRight.func_146116_c(this.field_146297_k, mouseX, mouseY)) {
                viewRotation -= partialTicks * 2.0f;
            }
        }
        super.func_73863_a(mouseX, mouseY, partialTicks);
        this.drawPreview(mouseX, mouseY, partialTicks);
    }

    protected void drawPreview(int mouseX, int mouseY, float partialTicks) {
        if (this.hasSubGui()) {
            return;
        }
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glEnable((int)3089);
        this.setScissorClip(this.previewX, this.previewY, this.previewWidth, this.previewHeight);
        EntityNPCInterface renderEntity = this.npc;
        this.npc.field_70760_ar = this.npc.field_70761_aq = NPC_FACING_YAW;
        this.npc.field_70126_B = this.npc.field_70177_z = NPC_FACING_YAW;
        this.npc.field_70758_at = this.npc.field_70759_as = NPC_FACING_YAW;
        ((EntityLivingBase)renderEntity).field_70760_ar = ((EntityLivingBase)renderEntity).field_70761_aq = NPC_FACING_YAW;
        ((EntityLivingBase)renderEntity).field_70126_B = ((EntityLivingBase)renderEntity).field_70177_z = NPC_FACING_YAW;
        ((EntityLivingBase)renderEntity).field_70758_at = ((EntityLivingBase)renderEntity).field_70759_as = NPC_FACING_YAW;
        ((EntityLivingBase)renderEntity).field_70125_A = 0.0f;
        double npcDeltaX = 0.0;
        double npcDeltaY = 0.0;
        double npcDeltaZ = 0.0;
        if (this.trackingNpcMovement) {
            double interpX = this.npc.field_70169_q + (this.npc.field_70165_t - this.npc.field_70169_q) * (double)partialTicks;
            double interpY = this.npc.field_70167_r + (this.npc.field_70163_u - this.npc.field_70167_r) * (double)partialTicks;
            double interpZ = this.npc.field_70166_s + (this.npc.field_70161_v - this.npc.field_70166_s) * (double)partialTicks;
            npcDeltaX = interpX - this.npcStartX;
            npcDeltaY = interpY - this.npcStartY;
            npcDeltaZ = interpZ - this.npcStartZ;
        }
        GL11.glEnable((int)2903);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)this.npcScreenX, (float)this.npcScreenY, (float)300.0f);
        GL11.glScalef((float)(-CAMERA_ZOOM), (float)CAMERA_ZOOM, (float)CAMERA_ZOOM);
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)CAMERA_PITCH, (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glRotatef((float)viewRotation, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)135.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        RenderHelper.func_74519_b();
        GL11.glRotatef((float)-135.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glTranslatef((float)0.0f, (float)((EntityLivingBase)renderEntity).field_70129_M, (float)0.0f);
        RenderManager.field_78727_a.field_78735_i = 180.0f;
        ClientEventHandler.renderingEntityInGUI = true;
        RenderManager.field_78727_a.func_147940_a((Entity)renderEntity, npcDeltaX, npcDeltaY, npcDeltaZ, 0.0f, partialTicks);
        this.renderPreviewEntities(partialTicks);
        this.renderPreviewTelegraph(partialTicks);
        ClientEventHandler.renderingEntityInGUI = false;
        GL11.glPopMatrix();
        RenderHelper.func_74518_a();
        GL11.glDisable((int)32826);
        OpenGlHelper.func_77473_a((int)OpenGlHelper.field_77476_b);
        GL11.glDisable((int)3553);
        OpenGlHelper.func_77473_a((int)OpenGlHelper.field_77478_a);
        GL11.glDisable((int)3089);
        GL11.glClear((int)256);
    }

    protected void renderPreviewEntities(float partialTicks) {
        boolean lightingWasEnabled = GL11.glIsEnabled((int)2896);
        double refX = this.trackingNpcMovement ? this.npcStartX : this.npc.field_70165_t;
        double refY = this.trackingNpcMovement ? this.npcStartY : this.npc.field_70163_u;
        double refZ = this.trackingNpcMovement ? this.npcStartZ : this.npc.field_70161_v;
        for (Entity entity : this.previewEntities) {
            if (entity == null || entity.field_70128_L) continue;
            double offsetX = entity.field_70169_q + (entity.field_70165_t - entity.field_70169_q) * (double)partialTicks - refX;
            double offsetY = entity.field_70167_r + (entity.field_70163_u - entity.field_70167_r) * (double)partialTicks - refY;
            double offsetZ = entity.field_70166_s + (entity.field_70161_v - entity.field_70166_s) * (double)partialTicks - refZ;
            offsetY -= (double)this.npc.field_70129_M;
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glDisable((int)2896);
            try {
                RenderManager.field_78727_a.func_147940_a(entity, offsetX, offsetY, offsetZ, entity.field_70177_z, partialTicks);
            }
            catch (Exception exception) {}
        }
        if (lightingWasEnabled) {
            GL11.glEnable((int)2896);
        }
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }

    protected void renderPreviewTelegraph(float partialTicks) {
        if (this.previewTelegraph == null) {
            return;
        }
        if (TelegraphRenderer.Instance == null) {
            return;
        }
        double refX = this.trackingNpcMovement ? this.npcStartX : this.npc.field_70165_t;
        double refY = this.trackingNpcMovement ? this.npcStartY : this.npc.field_70163_u;
        double refZ = this.trackingNpcMovement ? this.npcStartZ : this.npc.field_70161_v;
        double offsetX = this.previewTelegraph.getInterpolatedX(partialTicks) - refX;
        double offsetY = this.previewTelegraph.getInterpolatedY(partialTicks) - refY;
        double offsetZ = this.previewTelegraph.getInterpolatedZ(partialTicks) - refZ;
        TelegraphRenderer.Instance.renderTelegraphInGUI(this.previewTelegraph, offsetX, offsetY -= (double)this.npc.field_70129_M, offsetZ, 1.0f, partialTicks);
    }

    public void setPreviewTelegraph(TelegraphInstance telegraph) {
        this.previewTelegraph = telegraph;
    }

    protected void setScissorClip(int x, int y, int width, int height) {
        ScaledResolution sr = new ScaledResolution(this.field_146297_k, this.field_146297_k.field_71443_c, this.field_146297_k.field_71440_d);
        int scale = sr.func_78325_e();
        int scaledY = this.field_146297_k.field_71440_d - (y + height) * scale;
        GL11.glScissor((int)(x * scale), (int)scaledY, (int)(width * scale), (int)(height * scale));
    }

    public void startTrackingMovement() {
        this.npcStartX = this.npc.field_70165_t;
        this.npcStartY = this.npc.field_70163_u;
        this.npcStartZ = this.npc.field_70161_v;
        this.trackingNpcMovement = true;
    }

    public void stopTrackingMovement() {
        this.trackingNpcMovement = false;
    }

    public void addPreviewEntity(Entity entity) {
        if (entity != null && !this.previewEntities.contains(entity)) {
            this.previewEntities.add(entity);
        }
    }

    public void removePreviewEntity(Entity entity) {
        this.previewEntities.remove(entity);
    }

    public void clearPreviewEntities() {
        this.previewEntities.clear();
    }

    public List<Entity> getPreviewEntities() {
        return this.previewEntities;
    }

    public float getNpcFacingYaw() {
        return NPC_FACING_YAW;
    }

    @Override
    protected void drawBackground() {
        super.drawBackground();
        this.func_73733_a(this.previewX, this.previewY, this.previewX + this.previewWidth, this.previewY + this.previewHeight, -1072689136, -804253680);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }

    @Override
    public boolean func_73868_f() {
        return false;
    }

    @Override
    public void func_73869_a(char par1, int par2) {
        boolean hadSubGui = this.hasSubGui();
        super.func_73869_a(par1, par2);
        if (par2 == 1 && !hadSubGui) {
            this.close();
        }
    }

    @Override
    public void close() {
        this.field_146297_k.func_147108_a((GuiScreen)null);
        this.field_146297_k.func_71381_h();
    }

    @Override
    public void save() {
    }

    @Override
    protected void func_146284_a(GuiButton btn) {
        if (btn.field_146127_k == 670) {
            this.close();
        }
    }
}

