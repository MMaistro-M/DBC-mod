/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.RenderGlobal
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.IBlockAccess
 *  net.minecraftforge.client.event.RenderWorldLastEvent
 *  net.minecraftforge.common.MinecraftForge
 *  org.lwjgl.opengl.GL11
 */
package riskyken.armourersWorkshop.client.render.tileEntity;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Collections;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.MinecraftForge;
import org.lwjgl.opengl.GL11;
import riskyken.armourersWorkshop.client.model.block.ModelBlockSkinnable;
import riskyken.armourersWorkshop.client.render.ModRenderHelper;
import riskyken.armourersWorkshop.client.render.SkinPartRenderData;
import riskyken.armourersWorkshop.client.render.SkinPartRenderer;
import riskyken.armourersWorkshop.client.skin.cache.ClientSkinCache;
import riskyken.armourersWorkshop.common.blocks.BlockSkinnable;
import riskyken.armourersWorkshop.common.config.ConfigHandlerClient;
import riskyken.armourersWorkshop.common.skin.data.Skin;
import riskyken.armourersWorkshop.common.skin.data.SkinPointer;
import riskyken.armourersWorkshop.common.tileentities.TileEntitySkinnable;
import riskyken.armourersWorkshop.common.tileentities.TileEntitySkinnableChild;

@SideOnly(value=Side.CLIENT)
public class RenderBlockSkinnable
extends TileEntitySpecialRenderer {
    private static final ModelBlockSkinnable loadingModel = new ModelBlockSkinnable();
    private ArrayList<RenderLast> renderList;

    public RenderBlockSkinnable() {
        MinecraftForge.EVENT_BUS.register((Object)this);
        this.renderList = new ArrayList();
    }

    @SubscribeEvent
    public void onRenderWorldLast(RenderWorldLastEvent event) {
        Minecraft mc = Minecraft.func_71410_x();
        mc.field_71424_I.func_76320_a("renderListSort");
        Collections.sort(this.renderList);
        mc.field_71424_I.func_76319_b();
        mc.field_71460_t.func_78463_b((double)event.partialTicks);
        RenderHelper.func_74519_b();
        ModRenderHelper.enableAlphaBlend();
        Minecraft.func_71410_x().field_71424_I.func_76320_a("skinnableBlock");
        for (int i = 0; i < this.renderList.size(); ++i) {
            RenderLast rl = this.renderList.get(i);
            this.renderTileEntityAt((TileEntitySkinnable)rl.tileEntity, rl.x, rl.y, rl.z, event.partialTicks);
        }
        Minecraft.func_71410_x().field_71424_I.func_76319_b();
        RenderHelper.func_74518_a();
        this.renderList.clear();
        ModRenderHelper.disableAlphaBlend();
        mc.field_71460_t.func_78483_a((double)event.partialTicks);
    }

    public void renderTileEntityAt(TileEntitySkinnable tileEntity, double x, double y, double z, float partialTickTime) {
        ModRenderHelper.setLightingForBlock(tileEntity.func_145831_w(), tileEntity.field_145851_c, tileEntity.field_145848_d, tileEntity.field_145849_e);
        SkinPointer skinPointer = tileEntity.getSkinPointer();
        if (skinPointer != null) {
            Skin skin = ClientSkinCache.INSTANCE.getSkin(skinPointer);
            if (skin != null) {
                this.renderSkin(tileEntity, x, y, z, skin);
            } else {
                ClientSkinCache.INSTANCE.requestSkinFromServer(skinPointer);
                GL11.glPushMatrix();
                GL11.glTranslated((double)(x + 0.5), (double)(y + 0.5), (double)(z + 0.5));
                loadingModel.render(tileEntity, partialTickTime, 0.0625f);
                GL11.glPopMatrix();
            }
        }
    }

    private void renderSkin(TileEntitySkinnable tileEntity, double x, double y, double z, Skin skin) {
        int rotation = tileEntity.func_145832_p();
        double distance = Minecraft.func_71410_x().field_71439_g.func_70011_f((double)((float)tileEntity.field_145851_c + 0.5f), (double)((float)tileEntity.field_145848_d + 0.5f), (double)((float)tileEntity.field_145849_e + 0.5f));
        if (ConfigHandlerClient.showLodLevels) {
            int colour = 65280;
            int lod = MathHelper.func_76128_c((double)(distance / ConfigHandlerClient.lodDistance));
            if ((lod = MathHelper.func_76125_a((int)lod, (int)0, (int)ConfigHandlerClient.maxLodLevels)) == 1) {
                colour = 0xFFFF00;
            } else if (lod == 2) {
                colour = 0xFF0000;
            } else if (lod == 3) {
                colour = 0xFF00FF;
            }
            AxisAlignedBB aabb = AxisAlignedBB.func_72330_a((double)x, (double)y, (double)z, (double)(x + 1.0), (double)(y + 1.0), (double)(z + 1.0));
            GL11.glDisable((int)2896);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glLineWidth((float)1.0f);
            GL11.glDisable((int)3553);
            RenderGlobal.func_147590_a((AxisAlignedBB)aabb, (int)colour);
            GL11.glEnable((int)3553);
            GL11.glEnable((int)2896);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        }
        GL11.glTranslated((double)(x + 0.5), (double)(y + 0.5), (double)(z + 0.5));
        GL11.glScalef((float)-1.0f, (float)-1.0f, (float)1.0f);
        if (rotation != 0) {
            GL11.glRotatef((float)(90.0f * (float)rotation), (float)0.0f, (float)1.0f, (float)0.0f);
        }
        for (int i = 0; i < skin.getParts().size(); ++i) {
            SkinPartRenderData renderData = new SkinPartRenderData(skin.getParts().get(i), 0.0625f, tileEntity.getSkinPointer().getSkinDye(), null, distance, true, false, false, null);
            SkinPartRenderer.INSTANCE.renderPart(renderData);
        }
        if (rotation != 0) {
            GL11.glRotatef((float)(90.0f * (float)(-rotation)), (float)0.0f, (float)1.0f, (float)0.0f);
        }
        GL11.glScalef((float)-1.0f, (float)-1.0f, (float)1.0f);
        GL11.glTranslated((double)(-x - 0.5), (double)(-y - 0.5), (double)(-z - 0.5));
    }

    public void func_147500_a(TileEntity tileEntity, double x, double y, double z, float partialTickTime) {
        if (!(tileEntity instanceof TileEntitySkinnableChild)) {
            this.renderList.add(new RenderLast(tileEntity, x, y, z));
        }
        if (ConfigHandlerClient.showSkinBlockBounds) {
            if (!(tileEntity.func_145838_q() instanceof BlockSkinnable)) {
                return;
            }
            BlockSkinnable block = (BlockSkinnable)tileEntity.func_145838_q();
            block.func_149719_a((IBlockAccess)tileEntity.func_145831_w(), tileEntity.field_145851_c, tileEntity.field_145848_d, tileEntity.field_145849_e);
            double minX = block.func_149704_x();
            double minY = block.func_149665_z();
            double minZ = block.func_149706_B();
            double maxX = block.func_149753_y();
            double maxY = block.func_149669_A();
            double maxZ = block.func_149693_C();
            float f1 = 0.002f;
            AxisAlignedBB aabb = AxisAlignedBB.func_72330_a((double)minX, (double)minY, (double)minZ, (double)maxX, (double)maxY, (double)maxZ);
            aabb.func_72317_d(x, y, z);
            GL11.glEnable((int)3042);
            GL11.glDisable((int)2896);
            OpenGlHelper.func_148821_a((int)770, (int)771, (int)1, (int)0);
            GL11.glColor4f((float)0.0f, (float)1.0f, (float)0.0f, (float)0.4f);
            GL11.glLineWidth((float)1.0f);
            GL11.glDisable((int)3553);
            GL11.glDepthMask((boolean)false);
            RenderGlobal.func_147590_a((AxisAlignedBB)aabb.func_72331_e((double)f1, (double)f1, (double)f1), (int)-1);
            GL11.glDepthMask((boolean)true);
            GL11.glEnable((int)3553);
            GL11.glEnable((int)2896);
            GL11.glDisable((int)3042);
        }
        if (ConfigHandlerClient.showSkinRenderBounds) {
            if (tileEntity instanceof TileEntitySkinnableChild) {
                return;
            }
            float f1 = 0.002f;
            AxisAlignedBB aabb = tileEntity.getRenderBoundingBox().func_72329_c();
            aabb.func_72317_d(x - (double)tileEntity.field_145851_c, y - (double)tileEntity.field_145848_d, z - (double)tileEntity.field_145849_e);
            GL11.glEnable((int)3042);
            GL11.glDisable((int)2896);
            OpenGlHelper.func_148821_a((int)770, (int)771, (int)1, (int)0);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)0.0f, (float)0.4f);
            GL11.glLineWidth((float)1.0f);
            GL11.glDisable((int)3553);
            GL11.glDepthMask((boolean)false);
            RenderGlobal.func_147590_a((AxisAlignedBB)aabb.func_72331_e((double)f1, (double)f1, (double)f1), (int)-1);
            GL11.glDepthMask((boolean)true);
            GL11.glEnable((int)3553);
            GL11.glEnable((int)2896);
            GL11.glDisable((int)3042);
        }
    }

    private class RenderLast
    implements Comparable<RenderLast> {
        public final TileEntity tileEntity;
        public final double x;
        public final double y;
        public final double z;

        public RenderLast(TileEntity tileEntity, double x, double y, double z) {
            this.tileEntity = tileEntity;
            this.x = x;
            this.y = y;
            this.z = z;
        }

        @Override
        public int compareTo(RenderLast o) {
            EntityClientPlayerMP player = Minecraft.func_71410_x().field_71439_g;
            double dist = this.getDistanceFrom(player.field_70165_t, player.field_70163_u + (double)player.func_70047_e(), player.field_70161_v);
            double otherDist = o.getDistanceFrom(player.field_70165_t, player.field_70163_u + (double)player.func_70047_e(), player.field_70161_v);
            return (int)((otherDist - dist) * 128.0);
        }

        public double getDistanceFrom(double x, double y, double z) {
            double d3 = (double)this.tileEntity.field_145851_c + 0.5 - x;
            double d4 = (double)this.tileEntity.field_145848_d + 0.5 - y;
            double d5 = (double)this.tileEntity.field_145849_e + 0.5 - z;
            return d3 * d3 + d4 * d4 + d5 * d5;
        }
    }
}

