/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.RenderGlobal
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.MovingObjectPosition$MovingObjectType
 *  net.minecraft.world.World
 *  net.minecraftforge.client.event.DrawBlockHighlightEvent
 *  net.minecraftforge.client.event.RenderGameOverlayEvent
 *  net.minecraftforge.client.event.RenderGameOverlayEvent$ElementType
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.common.util.ForgeDirection
 *  org.lwjgl.opengl.GL11
 */
package riskyken.armourersWorkshop.client.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.opengl.GL11;
import riskyken.armourersWorkshop.api.common.skin.data.ISkinPointer;
import riskyken.armourersWorkshop.client.skin.cache.ClientSkinCache;
import riskyken.armourersWorkshop.common.blocks.ModBlocks;
import riskyken.armourersWorkshop.common.items.ItemDebugTool;
import riskyken.armourersWorkshop.common.items.ModItems;
import riskyken.armourersWorkshop.common.skin.data.Skin;
import riskyken.armourersWorkshop.common.skin.data.SkinPointer;
import riskyken.armourersWorkshop.common.skin.type.SkinTypeRegistry;
import riskyken.armourersWorkshop.common.tileentities.TileEntitySkinnable;
import riskyken.armourersWorkshop.utils.SkinNBTHelper;
import riskyken.armourersWorkshop.utils.UtilPlayer;

@SideOnly(value=Side.CLIENT)
public class BlockHighlightRenderHandler {
    public BlockHighlightRenderHandler() {
        MinecraftForge.EVENT_BUS.register((Object)this);
    }

    @SubscribeEvent
    public void onDrawBlockHighlightEvent(DrawBlockHighlightEvent event) {
        EntityPlayer player = event.player;
        World world = event.player.field_70170_p;
        MovingObjectPosition target = event.target;
        if (target != null && target.field_72313_a != MovingObjectPosition.MovingObjectType.BLOCK) {
            return;
        }
        int x = target.field_72311_b;
        int y = target.field_72312_c;
        int z = target.field_72309_d;
        int side = target.field_72310_e;
        Block block = world.func_147439_a(x, y, z);
        if (block == ModBlocks.mannequin) {
            this.drawMannequinBlockBounds(world, x, y, z, player, block, event.partialTicks);
            event.setCanceled(true);
        }
        if (event.currentItem != null && event.currentItem.func_77973_b() == ModItems.equipmentSkin) {
            SkinPointer skinPointer = SkinNBTHelper.getSkinPointerFromStack(event.currentItem);
            ForgeDirection sideDir = ForgeDirection.getOrientation((int)side);
            if (skinPointer != null && skinPointer.getIdentifier().getSkinType() == SkinTypeRegistry.skinBlock) {
                this.drawSkinnableBlockHelper(world, x += sideDir.offsetX, y += sideDir.offsetY, z += sideDir.offsetZ, side, player, event.partialTicks, skinPointer);
            }
        }
    }

    @SubscribeEvent
    public void onRenderGameOverlayEvent(RenderGameOverlayEvent event) {
        int i;
        Minecraft mc = Minecraft.func_71410_x();
        EntityClientPlayerMP player = mc.field_71439_g;
        World world = player.field_70170_p;
        if (player.func_71045_bC() == null || player.func_71045_bC().func_77973_b() != ModItems.debugTool) {
            return;
        }
        if (event.type != RenderGameOverlayEvent.ElementType.TEXT) {
            return;
        }
        MovingObjectPosition target = Minecraft.func_71410_x().field_71476_x;
        if (target != null && target.field_72313_a != MovingObjectPosition.MovingObjectType.BLOCK) {
            return;
        }
        int x = target.field_72311_b;
        int y = target.field_72312_c;
        int z = target.field_72309_d;
        Block block = world.func_147439_a(x, y, z);
        FontRenderer fontRenderer = mc.field_71466_p;
        ArrayList<String> textLines = new ArrayList<String>();
        textLines.add("name: " + block.func_149732_F());
        textLines.add("meta: " + world.func_72805_g(x, y, z));
        if (block instanceof ItemDebugTool.IDebug) {
            ItemDebugTool.IDebug debug = (ItemDebugTool.IDebug)block;
            debug.getDebugHoverText(world, x, y, z, textLines);
        }
        int centerX = event.resolution.func_78326_a() / 2;
        int centerY = event.resolution.func_78328_b() / 2;
        int longestLine = 0;
        for (i = 0; i < textLines.size(); ++i) {
            int sWidth = fontRenderer.func_78256_a(textLines.get(i));
            longestLine = Math.max(longestLine, sWidth);
        }
        for (i = 0; i < textLines.size(); ++i) {
            fontRenderer.func_78261_a(textLines.get(i), centerX - longestLine / 2, 5 + fontRenderer.field_78288_b * i, -1);
        }
    }

    private void drawMannequinBlockBounds(World world, int x, int y, int z, EntityPlayer player, Block block, float partialTicks) {
        int meta = world.func_72805_g(x, y, z);
        double xOff = player.field_70142_S + (player.field_70165_t - player.field_70142_S) * (double)partialTicks;
        double yOff = player.field_70137_T + (player.field_70163_u - player.field_70137_T) * (double)partialTicks;
        double zOff = player.field_70136_U + (player.field_70161_v - player.field_70136_U) * (double)partialTicks;
        double minX = (double)x + block.func_149704_x();
        double minY = (double)y + block.func_149665_z();
        double minZ = (double)z + block.func_149706_B();
        double maxX = (double)x + block.func_149753_y();
        double maxY = (double)y + block.func_149669_A();
        double maxZ = (double)z + block.func_149693_C();
        if (meta == 0) {
            maxY += 1.0;
        }
        if (meta == 1) {
            minY -= 1.0;
        }
        float f1 = 0.002f;
        AxisAlignedBB aabb = AxisAlignedBB.func_72330_a((double)minX, (double)minY, (double)minZ, (double)maxX, (double)maxY, (double)maxZ);
        aabb.func_72317_d(-xOff, -yOff, -zOff);
        GL11.glEnable((int)3042);
        OpenGlHelper.func_148821_a((int)770, (int)771, (int)1, (int)0);
        GL11.glColor4f((float)0.0f, (float)0.0f, (float)0.0f, (float)0.4f);
        GL11.glLineWidth((float)2.0f);
        GL11.glDisable((int)3553);
        GL11.glDepthMask((boolean)false);
        RenderGlobal.func_147590_a((AxisAlignedBB)aabb.func_72331_e((double)f1, (double)f1, (double)f1), (int)-1);
        GL11.glDepthMask((boolean)true);
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
    }

    private void drawSkinnableBlockHelper(World world, int x, int y, int z, int side, EntityPlayer player, float partialTicks, ISkinPointer skinPointer) {
        Skin skin = ClientSkinCache.INSTANCE.getSkin(skinPointer, false);
        if (skin == null) {
            return;
        }
        double xOff = player.field_70142_S + (player.field_70165_t - player.field_70142_S) * (double)partialTicks;
        double yOff = player.field_70137_T + (player.field_70163_u - player.field_70137_T) * (double)partialTicks;
        double zOff = player.field_70136_U + (player.field_70161_v - player.field_70136_U) * (double)partialTicks;
        float f1 = 0.002f;
        float scale = 0.0625f;
        ForgeDirection dir = UtilPlayer.getDirectionSide(player).getOpposite();
        for (int ix = 0; ix < 3; ++ix) {
            for (int iy = 0; iy < 3; ++iy) {
                for (int iz = 0; iz < 3; ++iz) {
                    float[] bounds = TileEntitySkinnable.getBlockBounds(skin, -ix + 2, iy, iz, dir);
                    if (bounds == null) continue;
                    double minX = bounds[0];
                    double minY = bounds[1];
                    double minZ = bounds[2];
                    double maxX = bounds[3];
                    double maxY = bounds[4];
                    double maxZ = bounds[5];
                    AxisAlignedBB aabb = AxisAlignedBB.func_72330_a((double)minX, (double)minY, (double)minZ, (double)maxX, (double)maxY, (double)maxZ);
                    aabb.func_72317_d(-xOff - 1.0, -yOff, -zOff - 1.0);
                    aabb.func_72317_d((double)(dir.offsetX * -1), 0.0, (double)(dir.offsetZ * -1));
                    aabb.func_72317_d((double)x, (double)y, (double)z);
                    aabb.func_72317_d((double)ix, (double)iy, (double)iz);
                    GL11.glEnable((int)3042);
                    OpenGlHelper.func_148821_a((int)770, (int)771, (int)1, (int)0);
                    GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)0.75f);
                    if (!world.func_147437_c(x + ix - 1 - dir.offsetX, y + iy, z + iz - 1 - dir.offsetZ)) {
                        GL11.glColor4f((float)1.0f, (float)0.0f, (float)0.0f, (float)0.75f);
                    }
                    GL11.glLineWidth((float)1.0f);
                    GL11.glDisable((int)3553);
                    GL11.glDepthMask((boolean)false);
                    GL11.glDisable((int)2929);
                    RenderGlobal.func_147590_a((AxisAlignedBB)aabb.func_72331_e((double)f1, (double)f1, (double)f1), (int)-1);
                    GL11.glEnable((int)2929);
                    GL11.glDepthMask((boolean)true);
                    GL11.glEnable((int)3553);
                    GL11.glDisable((int)3042);
                }
            }
        }
    }
}

