/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.renderer.entity.RenderPlayer
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.client.renderer.texture.TextureUtil
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.client.IItemRenderer
 *  net.minecraftforge.client.IItemRenderer$ItemRenderType
 *  net.minecraftforge.client.IItemRenderer$ItemRendererHelper
 *  org.lwjgl.opengl.GL11
 *  org.lwjgl.opengl.GL14
 */
package JinRyuu.DragonBC.common.Items;

import JinRyuu.DragonBC.common.Blocks.BlocksDBC;
import JinRyuu.DragonBC.common.Items.ItemDragonRadar;
import JinRyuu.JRMCore.JRMCoreClient;
import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.JRMCoreHC;
import java.util.ArrayList;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;

public class ItemDBCRenderRad
implements IItemRenderer {
    protected String t;
    private int tick;
    private static final ResourceLocation RES_ITEM_GLINT = new ResourceLocation("textures/misc/enchanted_item_glint.png");
    private static final ResourceLocation RES_MAP_BACKGROUND = new ResourceLocation("textures/map/map_background.png");
    private static final ResourceLocation RES_UNDERWATER_OVERLAY = new ResourceLocation("textures/misc/underwater.png");
    ArrayList<double[]> dbs = new ArrayList();
    ArrayList<double[]> dbs2 = new ArrayList();
    private int db = 0;
    private static int gdb = 0;
    private int dbw = 0;
    public static int tc = 0;

    public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
        switch (type) {
            case EQUIPPED: {
                return false;
            }
            case INVENTORY: {
                return false;
            }
            case ENTITY: {
                return false;
            }
            case EQUIPPED_FIRST_PERSON: {
                return true;
            }
        }
        return false;
    }

    public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
        return false;
    }

    public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object ... data) {
        switch (type) {
            case EQUIPPED: 
            case INVENTORY: 
            case ENTITY: 
            case EQUIPPED_FIRST_PERSON: {
                GL11.glPushMatrix();
                float p_78440_1_ = 0.0625f;
                EntityClientPlayerMP entityclientplayermp = JRMCoreClient.mc.field_71439_g;
                float f2 = entityclientplayermp.field_70127_C + (entityclientplayermp.field_70125_A - entityclientplayermp.field_70127_C) * p_78440_1_;
                float f13 = 0.8f;
                float f1 = 0.0f;
                GL11.glTranslatef((float)0.35f, (float)-0.0f, (float)-0.3f);
                float f5 = 1.0f - f2 / 45.0f + 0.1f;
                if (f5 < 0.0f) {
                    f5 = 0.0f;
                }
                if (f5 > 1.0f) {
                    f5 = 1.0f;
                }
                f5 = -MathHelper.func_76134_b((float)(f5 * (float)Math.PI)) * 0.5f + 0.5f;
                GL11.glRotatef((float)(f5 * -85.0f), (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glRotatef((float)20.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glRotatef((float)-20.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)20.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glTranslatef((float)-0.5f, (float)0.0f, (float)-0.0f);
                this.renderItem();
                GL11.glPopMatrix();
            }
        }
    }

    public void drawTexturedModalRect(int p_73729_1_, int p_73729_2_, int p_73729_3_, int p_73729_4_, int p_73729_5_, int p_73729_6_) {
        float f = 0.00390625f;
        float f1 = 0.00390625f;
        Tessellator tessellator = Tessellator.field_78398_a;
        tessellator.func_78382_b();
        double zLevel = 0.0;
        tessellator.func_78374_a((double)(p_73729_1_ + 0), (double)(p_73729_2_ + p_73729_6_), zLevel, (double)((float)(p_73729_3_ + 0) * f), (double)((float)(p_73729_4_ + p_73729_6_) * f1));
        tessellator.func_78374_a((double)(p_73729_1_ + p_73729_5_), (double)(p_73729_2_ + p_73729_6_), zLevel, (double)((float)(p_73729_3_ + p_73729_5_) * f), (double)((float)(p_73729_4_ + p_73729_6_) * f1));
        tessellator.func_78374_a((double)(p_73729_1_ + p_73729_5_), (double)(p_73729_2_ + 0), zLevel, (double)((float)(p_73729_3_ + p_73729_5_) * f), (double)((float)(p_73729_4_ + 0) * f1));
        tessellator.func_78374_a((double)(p_73729_1_ + 0), (double)(p_73729_2_ + 0), zLevel, (double)((float)(p_73729_3_ + 0) * f), (double)((float)(p_73729_4_ + 0) * f1));
        tessellator.func_78381_a();
    }

    public void renderItem() {
        GL11.glPushMatrix();
        TextureManager texturemanager = JRMCoreClient.mc.func_110434_K();
        String wish = JRMCoreH.tjdbcAssts + ":radar.png";
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        ResourceLocation tx = new ResourceLocation(wish);
        int xSize = 166;
        int ySize = 166;
        boolean xMin = false;
        int yMin = 0;
        texturemanager.func_110577_a(tx);
        TextureUtil.func_152777_a((boolean)false, (boolean)false, (float)1.0f);
        Tessellator tessellator = Tessellator.field_78398_a;
        float f = 0.0f;
        float f1 = (float)xSize / 256.0f;
        float f2 = 0.0f;
        float f3 = (float)ySize / 256.0f;
        float f4 = -0.9f;
        float f5 = -0.5f;
        GL11.glEnable((int)32826);
        GL11.glTranslatef((float)(-f4 - 0.2f), (float)(-f5), (float)0.13f);
        float f6 = 0.75f;
        GL11.glScalef((float)f6, (float)f6, (float)f6);
        GL11.glRotatef((float)10.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glRotatef((float)50.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)0.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glTranslatef((float)-0.375f, (float)0.1625f, (float)0.0f);
        GL11.glPushMatrix();
        JRMCoreHC.ri(tessellator, f1, f2, f, f3, xSize, ySize, 0.0625f);
        GL11.glPopMatrix();
        ++this.tick;
        if (this.tick > 80) {
            if (this.tick > 90) {
                this.tick = 0;
            }
        } else {
            this.DragonRadar((EntityPlayer)JRMCoreClient.mc.field_71439_g);
        }
        GL11.glDisable((int)32826);
        texturemanager.func_110577_a(tx);
        TextureUtil.func_147945_b();
        GL11.glPopMatrix();
        if (ItemDragonRadar.cld) {
            ItemDragonRadar.cld = false;
            tc = 50;
        }
        if (tc > 0) {
            --tc;
        }
        GL11.glPushMatrix();
        texturemanager = JRMCoreClient.mc.func_110434_K();
        wish = JRMCoreH.tjdbcAssts + ":radar.png";
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        tx = new ResourceLocation(wish);
        xSize = 166;
        ySize = 17;
        xMin = false;
        yMin = 166;
        int yMax = 166 + ySize;
        texturemanager.func_110577_a(tx);
        TextureUtil.func_152777_a((boolean)false, (boolean)false, (float)1.0f);
        Tessellator tessellator2 = Tessellator.field_78398_a;
        float f7 = 0.0f;
        float f12 = (float)xSize / 256.0f;
        float f22 = (float)yMin / 256.0f;
        float f32 = (float)yMax / 256.0f;
        float f42 = -0.9f;
        float f52 = -1.16f + (float)tc * 5.0E-4f;
        GL11.glEnable((int)32826);
        GL11.glTranslatef((float)(-f42 - 0.2f), (float)(-f52), (float)0.13f);
        float f62 = 0.75f;
        GL11.glScalef((float)f62, (float)f62, (float)f62);
        GL11.glRotatef((float)10.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glRotatef((float)50.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)0.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glTranslatef((float)-0.635f, (float)0.2f, (float)0.097f);
        GL11.glPushMatrix();
        f7 = (float)ySize / 128.0f;
        GL11.glScalef((float)1.0f, (float)f7, (float)1.0f);
        JRMCoreHC.ri(tessellator2, f12, f22, f7, f32, xSize, ySize, 0.06f);
        GL11.glPopMatrix();
        GL11.glDisable((int)32826);
        texturemanager.func_110577_a(tx);
        TextureUtil.func_147945_b();
        GL11.glPopMatrix();
        EntityClientPlayerMP entityclientplayermp = JRMCoreClient.mc.field_71439_g;
        JRMCoreClient.mc.func_110434_K().func_110577_a(entityclientplayermp.func_110306_p());
        boolean j1 = false;
        GL11.glPushMatrix();
        GL11.glTranslatef((float)0.6f, (float)0.0f, (float)0.0f);
        GL11.glRotatef((float)-20.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)20.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glRotatef((float)-0.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glTranslatef((float)0.3f, (float)-0.1f, (float)-0.1f);
        j1 = true;
        j1 = true;
        Render render = RenderManager.field_78727_a.func_78713_a((Entity)entityclientplayermp);
        RenderPlayer renderplayer = (RenderPlayer)render;
        float f10 = 1.0f;
        GL11.glScalef((float)f10, (float)f10, (float)f10);
        renderplayer.func_82441_a((EntityPlayer)entityclientplayermp);
        GL11.glPopMatrix();
    }

    public void upd(EntityPlayer p) {
        if (p.field_71093_bK != this.dbw) {
            this.dbw = p.field_71093_bK;
            this.dbs.clear();
            this.dbs2.clear();
        }
        if (gdb >= 800) {
            gdb = 0;
            if (this.db == 0) {
                this.db = 1;
                this.dbs.clear();
            } else {
                this.db = 0;
                this.dbs2.clear();
            }
        }
        for (int j = 0; j < 16; ++j) {
            if (gdb % 50 != 0 || j != gdb / 50) continue;
            this.chk(p, j * 16 + 15, j * 16);
        }
        ++gdb;
    }

    public void chk(EntityPlayer p, int x, int n) {
        int l1 = MathHelper.func_76128_c((double)p.field_70165_t);
        int i11 = MathHelper.func_76128_c((double)p.field_70161_v);
        int m = 80;
        for (int j11 = l1 - m; j11 <= l1 + m; ++j11) {
            for (int j2 = i11 - m; j2 <= i11 + m; ++j2) {
                for (int k2 = x; k2 >= n; --k2) {
                    if (p.field_70170_p.func_147439_a(j11, k2, j2) != BlocksDBC.BlockDragonBall && p.field_70170_p.func_147439_a(j11, k2, j2) != BlocksDBC.BlockNamekDragonBall) continue;
                    double[] d = new double[]{j11, j2};
                    this.dbs.add(d);
                    this.dbs2.add(d);
                }
            }
        }
    }

    public void DragonRadar(EntityPlayer p) {
        int i;
        this.upd(p);
        int pitch = 0;
        for (i = 0; i < this.dbs.size(); ++i) {
            this.DragonDetect(this.dbs.get(i)[0] - p.field_70165_t, this.dbs.get(i)[1] - p.field_70161_v, pitch > 0 ? pitch : 0);
        }
        for (i = 0; i < this.dbs2.size(); ++i) {
            this.DragonDetect(this.dbs2.get(i)[0] - p.field_70165_t, this.dbs2.get(i)[1] - p.field_70161_v, pitch > 0 ? pitch : 0);
        }
    }

    public void DragonDetect(double x, double y, float f) {
        int guiLeft = 69;
        int guiTop = 92;
        String ic = "jinryuumodscore:icons.png";
        String ic2 = "jinryuudragonbc:icons3.png";
        if (x > -70.0 && x < 70.0 && y > -70.0 && y < 70.0) {
            GL11.glPushMatrix();
            GL11.glEnable((int)3042);
            GL11.glDisable((int)2896);
            GL11.glTranslatef((float)-0.005f, (float)-0.0025f, (float)0.0f);
            GL11.glTranslatef((float)0.567f, (float)0.44f, (float)0.0f);
            GL11.glRotatef((float)(-JRMCoreClient.mc.field_71439_g.field_70759_as - 180.0f), (float)0.0f, (float)0.0f, (float)1.0f);
            GL14.glBlendFuncSeparate((int)0, (int)1, (int)771, (int)0);
            GL11.glTranslatef((float)-0.567f, (float)-0.44f, (float)0.0f);
            GL11.glTranslatef((float)0.0f, (float)0.0f, (float)-0.065f);
            float f10 = 0.00609375f;
            GL11.glScalef((float)f10, (float)f10, (float)f10);
            GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            int xSize = 166;
            int ySize = 166;
            String var4 = "jinryuudragonbc:radarm.png";
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            ResourceLocation tx = new ResourceLocation(var4);
            JRMCoreClient.mc.field_71446_o.func_110577_a(tx);
            this.drawTexturedModalRect(-166, -166, 0, 0, xSize, ySize);
            GL11.glBlendFunc((int)773, (int)772);
            JRMCoreClient.mc.field_71460_t.func_78483_a(0.0);
            GL11.glTranslatef((float)1.0f, (float)-0.5f, (float)-0.01f);
            xSize = 8;
            ySize = 8;
            var4 = "jinryuudragonbc:detect.png";
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            tx = new ResourceLocation(var4);
            JRMCoreClient.mc.field_71446_o.func_110577_a(tx);
            this.drawTexturedModalRect((int)((double)guiLeft + x) - 166, (int)((double)guiTop + y) - 166, 0, 0, xSize, ySize);
            JRMCoreClient.mc.field_71460_t.func_78463_b(0.0);
            GL11.glEnable((int)2896);
            GL11.glDisable((int)3042);
            GL11.glPopMatrix();
        }
    }
}

