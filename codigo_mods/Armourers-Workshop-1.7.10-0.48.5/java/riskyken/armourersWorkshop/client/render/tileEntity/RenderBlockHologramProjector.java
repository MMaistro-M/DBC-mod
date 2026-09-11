/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.RenderGlobal
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraftforge.common.util.ForgeDirection
 *  org.lwjgl.opengl.GL11
 */
package riskyken.armourersWorkshop.client.render.tileEntity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.opengl.GL11;
import riskyken.armourersWorkshop.client.render.ItemStackRenderHelper;
import riskyken.armourersWorkshop.client.render.ModRenderHelper;
import riskyken.armourersWorkshop.client.skin.cache.ClientSkinCache;
import riskyken.armourersWorkshop.common.config.ConfigHandlerClient;
import riskyken.armourersWorkshop.common.skin.data.Skin;
import riskyken.armourersWorkshop.common.skin.data.SkinPointer;
import riskyken.armourersWorkshop.common.tileentities.TileEntityHologramProjector;
import riskyken.armourersWorkshop.utils.SkinNBTHelper;

@SideOnly(value=Side.CLIENT)
public class RenderBlockHologramProjector
extends TileEntitySpecialRenderer {
    public void renderTileEntityAt(TileEntityHologramProjector tileEntity, double x, double y, double z, float partialTickTime) {
        if (tileEntity.getPowerMode() != TileEntityHologramProjector.PowerMode.IGNORED && (tileEntity.getPowerMode() == TileEntityHologramProjector.PowerMode.HIGH ? !tileEntity.isPowered() : tileEntity.isPowered())) {
            return;
        }
        ItemStack itemStack = tileEntity.func_70301_a(0);
        SkinPointer skinPointer = SkinNBTHelper.getSkinPointerFromStack(itemStack);
        if (skinPointer == null) {
            return;
        }
        int rot = tileEntity.func_145832_p();
        GL11.glPushMatrix();
        GL11.glEnable((int)2977);
        GL11.glTranslated((double)(x + 0.5), (double)(y + 0.5), (double)(z + 0.5));
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        if (rot == 1) {
            GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        }
        if (rot == 2) {
            GL11.glRotatef((float)90.0f, (float)-1.0f, (float)0.0f, (float)0.0f);
        }
        if (rot == 3) {
            GL11.glRotatef((float)90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        }
        if (rot == 4) {
            GL11.glRotatef((float)90.0f, (float)0.0f, (float)0.0f, (float)-1.0f);
        }
        if (rot == 5) {
            GL11.glRotatef((float)90.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        }
        float scale = 0.0625f;
        GL11.glTranslated((double)((float)tileEntity.getOffsetX() * scale), (double)((float)tileEntity.getOffsetY() * scale), (double)((float)tileEntity.getOffsetZ() * scale));
        GL11.glScalef((float)-1.0f, (float)-1.0f, (float)1.0f);
        int speedX = tileEntity.getRotationSpeedX();
        int speedY = tileEntity.getRotationSpeedY();
        int speedZ = tileEntity.getRotationSpeedZ();
        float angleX = 0.0f;
        float angleY = 0.0f;
        float angleZ = 0.0f;
        if (speedX != 0) {
            angleX = System.currentTimeMillis() % (long)speedX;
            angleX = angleX / (float)speedX * 360.0f;
        }
        if (speedY != 0) {
            angleY = System.currentTimeMillis() % (long)speedY;
            angleY = angleY / (float)speedY * 360.0f;
        }
        if (speedZ != 0) {
            angleZ = System.currentTimeMillis() % (long)speedZ;
            angleZ = angleZ / (float)speedZ * 360.0f;
        }
        if (!tileEntity.isGlowing()) {
            ForgeDirection dir = ForgeDirection.getOrientation((int)tileEntity.func_145832_p());
            float xLight = tileEntity.field_145851_c;
            float yLight = tileEntity.field_145848_d;
            float zLight = tileEntity.field_145849_e;
            float offsetX = tileEntity.getOffsetX();
            float offsetY = tileEntity.getOffsetY();
            float offsetZ = tileEntity.getOffsetZ();
            switch (dir) {
                case UP: {
                    xLight += offsetX * scale;
                    yLight += offsetY * scale;
                    zLight += offsetZ * scale;
                    break;
                }
                case DOWN: {
                    xLight += -offsetX * scale;
                    yLight += -offsetY * scale;
                    zLight += offsetZ * scale;
                    break;
                }
                case EAST: {
                    xLight += offsetY * scale;
                    yLight += -offsetX * scale;
                    zLight += offsetZ * scale;
                    break;
                }
                case WEST: {
                    xLight += -offsetY * scale;
                    yLight += offsetX * scale;
                    zLight += offsetZ * scale;
                    break;
                }
                case NORTH: {
                    xLight += offsetX * scale;
                    yLight += -offsetZ * scale;
                    zLight += -offsetY * scale;
                    break;
                }
                case SOUTH: {
                    xLight += -offsetX * scale;
                    yLight += offsetY * scale;
                    zLight += offsetZ * scale;
                    break;
                }
            }
            ModRenderHelper.setLightingForBlock(tileEntity.func_145831_w(), (int)(xLight + 0.5f), (int)(yLight + 0.5f), (int)(zLight + 0.5f));
        }
        GL11.glPushMatrix();
        GL11.glTranslated((double)((float)(-tileEntity.getRotationOffsetX() + tileEntity.getRotationOffsetX()) * scale), (double)((float)(-tileEntity.getRotationOffsetY() + tileEntity.getRotationOffsetY()) * scale), (double)((float)(-tileEntity.getRotationOffsetZ() + tileEntity.getRotationOffsetZ()) * scale));
        if (tileEntity.getAngleX() != 0) {
            GL11.glRotatef((float)tileEntity.getAngleX(), (float)1.0f, (float)0.0f, (float)0.0f);
        }
        if (tileEntity.getAngleY() != 0) {
            GL11.glRotatef((float)tileEntity.getAngleY(), (float)0.0f, (float)1.0f, (float)0.0f);
        }
        if (tileEntity.getAngleZ() != 0) {
            GL11.glRotatef((float)tileEntity.getAngleZ(), (float)0.0f, (float)0.0f, (float)1.0f);
        }
        if (angleX != 0.0f) {
            GL11.glRotatef((float)angleX, (float)1.0f, (float)0.0f, (float)0.0f);
        }
        if (angleY != 0.0f) {
            GL11.glRotatef((float)angleY, (float)0.0f, (float)1.0f, (float)0.0f);
        }
        if (angleZ != 0.0f) {
            GL11.glRotatef((float)angleZ, (float)0.0f, (float)0.0f, (float)1.0f);
        }
        GL11.glTranslated((double)((float)tileEntity.getRotationOffsetX() * scale), (double)((float)tileEntity.getRotationOffsetY() * scale), (double)((float)tileEntity.getRotationOffsetZ() * scale));
        if (tileEntity.isGlowing()) {
            ModRenderHelper.disableLighting();
        }
        ModRenderHelper.enableAlphaBlend();
        Skin skin = ClientSkinCache.INSTANCE.getSkin(skinPointer);
        ItemStackRenderHelper.renderSkinWithHelper(skin, skinPointer, true, false);
        GL11.glPopMatrix();
        if (tileEntity.isShowRotationPoint()) {
            AxisAlignedBB aabb = AxisAlignedBB.func_72330_a((double)0.0, (double)0.0, (double)0.0, (double)scale, (double)scale, (double)scale);
            this.renderBox(aabb, 1.0f, 0.0f, 1.0f);
        }
        ModRenderHelper.disableAlphaBlend();
        if (tileEntity.isGlowing()) {
            ModRenderHelper.enableLighting();
        }
        GL11.glDisable((int)2977);
        GL11.glPopMatrix();
    }

    public void func_147500_a(TileEntity tileEntity, double x, double y, double z, float partialTickTime) {
        this.renderTileEntityAt((TileEntityHologramProjector)tileEntity, x, y, z, partialTickTime);
        if (ConfigHandlerClient.showSkinRenderBounds) {
            if (tileEntity != null && !(tileEntity instanceof TileEntityHologramProjector)) {
                return;
            }
            AxisAlignedBB aabb = tileEntity.getRenderBoundingBox().func_72329_c();
            aabb.func_72317_d(x - (double)tileEntity.field_145851_c, y - (double)tileEntity.field_145848_d, z - (double)tileEntity.field_145849_e);
            this.renderBox(aabb, 1.0f, 1.0f, 0.0f);
        }
    }

    private void renderBox(AxisAlignedBB aabb, float r, float g, float b) {
        float f1 = 0.002f;
        ModRenderHelper.disableLighting();
        GL11.glEnable((int)3042);
        GL11.glDisable((int)2896);
        OpenGlHelper.func_148821_a((int)770, (int)771, (int)1, (int)0);
        GL11.glColor4f((float)r, (float)g, (float)b, (float)0.4f);
        GL11.glLineWidth((float)1.0f);
        GL11.glDisable((int)3553);
        GL11.glDisable((int)2929);
        GL11.glDepthMask((boolean)false);
        RenderGlobal.func_147590_a((AxisAlignedBB)aabb.func_72331_e((double)f1, (double)f1, (double)f1), (int)-1);
        GL11.glDepthMask((boolean)true);
        GL11.glEnable((int)2929);
        GL11.glEnable((int)3553);
        GL11.glEnable((int)2896);
        GL11.glDisable((int)3042);
        ModRenderHelper.enableLighting();
    }
}

