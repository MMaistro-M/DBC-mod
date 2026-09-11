/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler
 *  cpw.mods.fml.client.registry.RenderingRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.renderer.ItemRenderer
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraftforge.client.ForgeHooksClient
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.renderer.blocks;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import java.awt.Color;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.ForgeHooksClient;
import noppes.npcs.CustomItems;
import noppes.npcs.blocks.BlockScripted;
import noppes.npcs.blocks.tiles.TileScripted;
import noppes.npcs.client.TextBlockClient;
import noppes.npcs.client.renderer.blocks.BlockRendererInterface;
import org.lwjgl.opengl.GL11;

public class BlockScriptedRenderer
extends BlockRendererInterface {
    private static final RenderBlocks renderBlocks = new RenderBlocks();
    private static final Random random = new Random();

    public BlockScriptedRenderer() {
        ((BlockScripted)CustomItems.scripted).renderId = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)this);
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        TileScripted tile = (TileScripted)world.func_147438_o(x, y, z);
        Block blockModel = tile.blockModel;
        if (!BlockScriptedRenderer.overrideModel() && blockModel != null) {
            GL11.glPushMatrix();
            GL11.glRotatef((float)tile.rotationY, (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glRotatef((float)tile.rotationX, (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glRotatef((float)tile.rotationZ, (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glScalef((float)tile.scaleX, (float)tile.scaleY, (float)tile.scaleZ);
            if (blockModel != CustomItems.scripted) {
                tile.renderFullBlock = renderer.func_147805_b(blockModel, x, y, z);
            } else {
                renderer.func_147784_q(blockModel, x, y, z);
                tile.renderFullBlock = true;
            }
            GL11.glPopMatrix();
        } else {
            renderer.func_147784_q(CustomItems.scripted, x, y, z);
            tile.renderFullBlock = true;
        }
        return true;
    }

    public int getRenderId() {
        return CustomItems.scripted.func_149645_b();
    }

    public void func_147500_a(TileEntity te, double x, double y, double z, float partialTicks) {
        TileScripted tile = (TileScripted)te;
        if (!tile.renderFullBlock) {
            GL11.glPushMatrix();
            GL11.glDisable((int)3042);
            RenderHelper.func_74519_b();
            GL11.glTranslatef((float)((float)(x + 0.5)), (float)((float)y), (float)((float)(z + 0.5)));
            GL11.glRotatef((float)tile.rotationY, (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glRotatef((float)tile.rotationX, (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glRotatef((float)tile.rotationZ, (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glScalef((float)tile.scaleX, (float)tile.scaleY, (float)tile.scaleZ);
            GL11.glTranslatef((float)0.0f, (float)0.5f, (float)0.0f);
            this.renderItem(te, tile.itemModel);
            GL11.glPopMatrix();
        }
        if (!tile.text1.text.isEmpty()) {
            this.drawText(tile.text1, x, y, z);
        }
        if (!tile.text2.text.isEmpty()) {
            this.drawText(tile.text2, x, y, z);
        }
        if (!tile.text3.text.isEmpty()) {
            this.drawText(tile.text3, x, y, z);
        }
        if (!tile.text4.text.isEmpty()) {
            this.drawText(tile.text4, x, y, z);
        }
        if (!tile.text5.text.isEmpty()) {
            this.drawText(tile.text5, x, y, z);
        }
        if (!tile.text6.text.isEmpty()) {
            this.drawText(tile.text6, x, y, z);
        }
    }

    private void drawText(TileScripted.TextPlane text1, double x, double y, double z) {
        if (text1.textBlock == null || text1.textHasChanged) {
            text1.textBlock = new TextBlockClient(text1.text, 336, true, Minecraft.func_71410_x().field_71439_g);
            text1.textHasChanged = false;
        }
        GL11.glDisable((int)3042);
        GL11.glEnable((int)2896);
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)(x + 0.5)), (float)((float)(y + 0.5)), (float)((float)(z + 0.5)));
        GL11.glRotatef((float)text1.rotationY, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)text1.rotationX, (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glRotatef((float)text1.rotationZ, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glScalef((float)text1.scale, (float)text1.scale, (float)1.0f);
        GL11.glTranslatef((float)text1.offsetX, (float)text1.offsetY, (float)text1.offsetZ);
        float f1 = 0.6666667f;
        float f3 = 0.0133f * f1;
        GL11.glTranslatef((float)0.0f, (float)0.5f, (float)0.01f);
        GL11.glScalef((float)f3, (float)(-f3), (float)f3);
        GL11.glNormal3f((float)0.0f, (float)0.0f, (float)(-1.0f * f3));
        GL11.glDepthMask((boolean)false);
        FontRenderer fontrenderer = Minecraft.func_71410_x().field_71466_p;
        float lineOffset = 0.0f;
        if (text1.textBlock.lines.size() < 14) {
            lineOffset = (14.0f - (float)text1.textBlock.lines.size()) / 2.0f;
        }
        for (int i = 0; i < text1.textBlock.lines.size(); ++i) {
            String text = text1.textBlock.lines.get(i).func_150254_d();
            fontrenderer.func_78276_b(text, -fontrenderer.func_78256_a(text) / 2, (int)((double)(lineOffset + (float)i) * ((double)fontrenderer.field_78288_b - 0.3)), 0);
        }
        GL11.glDepthMask((boolean)true);
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glPopMatrix();
    }

    private void renderItem(TileEntity tile, ItemStack stack) {
        Minecraft mc = Minecraft.func_71410_x();
        if (stack != null) {
            mc.field_71446_o.func_110577_a(stack.func_77973_b() instanceof ItemBlock ? TextureMap.field_110575_b : TextureMap.field_110576_c);
            GL11.glScalef((float)2.0f, (float)2.0f, (float)2.0f);
            if (!ForgeHooksClient.renderEntityItem((EntityItem)new EntityItem(tile.func_145831_w(), (double)tile.field_145851_c, (double)tile.field_145848_d, (double)tile.field_145849_e, stack), (ItemStack)stack, (float)0.0f, (float)0.0f, (Random)tile.func_145831_w().field_73012_v, (TextureManager)mc.field_71446_o, (RenderBlocks)renderBlocks, (int)1)) {
                GL11.glScalef((float)0.5f, (float)0.5f, (float)0.5f);
                if (stack.func_77973_b() instanceof ItemBlock && (RenderBlocks.func_147739_a((int)Block.func_149634_a((Item)stack.func_77973_b()).func_149645_b()) || Block.func_149634_a((Item)stack.func_77973_b()) instanceof BlockScripted)) {
                    renderBlocks.func_147800_a(Block.func_149634_a((Item)stack.func_77973_b()), stack.func_77960_j(), 1.0f);
                } else {
                    int renderPass = 0;
                    do {
                        IIcon icon;
                        if ((icon = stack.func_77973_b().getIcon(stack, renderPass)) == null) continue;
                        Color color = new Color(stack.func_77973_b().func_82790_a(stack, renderPass));
                        GL11.glColor3ub((byte)((byte)color.getRed()), (byte)((byte)color.getGreen()), (byte)((byte)color.getBlue()));
                        float f = icon.func_94209_e();
                        float f1 = icon.func_94212_f();
                        float f2 = icon.func_94206_g();
                        float f3 = icon.func_94210_h();
                        GL11.glTranslatef((float)-0.5f, (float)-0.5f, (float)0.0f);
                        ItemRenderer.func_78439_a((Tessellator)Tessellator.field_78398_a, (float)f1, (float)f2, (float)f, (float)f3, (int)icon.func_94211_a(), (int)icon.func_94216_b(), (float)0.0625f);
                        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
                    } while (++renderPass < stack.func_77973_b().getRenderPasses(stack.func_77960_j()));
                }
            }
        }
    }

    private void renderBlock(TileScripted tile, Block b) {
        GL11.glPushMatrix();
        this.func_147499_a(TextureMap.field_110575_b);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glEnable((int)3042);
        GL11.glDisable((int)2884);
        GL11.glTranslatef((float)-0.5f, (float)0.0f, (float)0.5f);
        renderBlocks.func_147784_q(b, tile.field_145851_c, tile.field_145848_d, tile.field_145849_e);
        if (b.func_149653_t() && random.nextInt(12) == 1) {
            b.func_149734_b(tile.func_145831_w(), tile.field_145851_c, tile.field_145848_d, tile.field_145849_e, random);
        }
        GL11.glPopMatrix();
    }

    public static boolean overrideModel() {
        ItemStack held = Minecraft.func_71410_x().field_71439_g.func_70694_bm();
        if (held == null) {
            return false;
        }
        return held.func_77973_b() == CustomItems.wand || held.func_77973_b() == CustomItems.scripter;
    }

    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
    }

    @Override
    public boolean shouldRender3DInInventory(int modelId) {
        return false;
    }
}

