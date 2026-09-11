/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler
 *  cpw.mods.fml.client.registry.RenderingRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.renderer.blocks;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import noppes.npcs.CustomItems;
import noppes.npcs.blocks.BlockWeaponRack;
import noppes.npcs.blocks.tiles.TileWeaponRack;
import noppes.npcs.client.model.blocks.ModelWeaponRack;
import noppes.npcs.client.renderer.blocks.BlockRendererInterface;
import org.lwjgl.opengl.GL11;

public class BlockWeaponRackRenderer
extends BlockRendererInterface {
    private final ModelWeaponRack model = new ModelWeaponRack();

    public BlockWeaponRackRenderer() {
        ((BlockWeaponRack)CustomItems.weaponsRack).renderId = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)this);
    }

    public void func_147500_a(TileEntity var1, double var2, double var4, double var6, float var8) {
        TileWeaponRack tile = (TileWeaponRack)var1;
        GL11.glDisable((int)32826);
        GL11.glEnable((int)3008);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)var2 + 0.5f), (float)((float)var4 + 1.34f), (float)((float)var6 + 0.5f));
        GL11.glScalef((float)0.9f, (float)0.9f, (float)0.9f);
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)(90 * tile.rotation), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        BlockWeaponRackRenderer.setWoodTexture(var1.func_145832_p());
        this.model.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
        if (!this.playerTooFar(tile)) {
            for (int i = 0; i < 3; ++i) {
                this.doRender(tile.func_70301_a(i), i);
            }
        }
        GL11.glPopMatrix();
    }

    private void doRender(ItemStack item, int pos) {
        if (item == null || item.func_77973_b() == null || item.func_77973_b() instanceof ItemBlock) {
            return;
        }
        GL11.glPushMatrix();
        GL11.glTranslatef((float)(-0.4f + (float)pos * 0.37f), (float)0.8f, (float)0.23f);
        GL11.glScalef((float)0.5f, (float)0.5f, (float)0.5f);
        if (item.func_77973_b().func_77629_n_()) {
            GL11.glTranslatef((float)0.14f, (float)0.0f, (float)0.5f);
            GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        } else {
            GL11.glRotatef((float)-90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        }
        GL11.glRotatef((float)-200.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)-50.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        if (item.func_77973_b().func_77623_v()) {
            for (int k = 0; k <= item.func_77973_b().getRenderPasses(item.func_77960_j()); ++k) {
                int i = item.func_77973_b().func_82790_a(item, k);
                float f12 = (float)(i >> 16 & 0xFF) / 255.0f;
                float f4 = (float)(i >> 8 & 0xFF) / 255.0f;
                float f5 = (float)(i & 0xFF) / 255.0f;
                GL11.glColor4f((float)f12, (float)f4, (float)f5, (float)1.0f);
                RenderManager.field_78727_a.field_78721_f.func_78443_a((EntityLivingBase)Minecraft.func_71410_x().field_71439_g, item, k);
            }
        } else {
            int k = item.func_77973_b().func_82790_a(item, 0);
            float f11 = (float)(k >> 16 & 0xFF) / 255.0f;
            float f12 = (float)(k >> 8 & 0xFF) / 255.0f;
            float f4 = (float)(k & 0xFF) / 255.0f;
            GL11.glColor4f((float)f11, (float)f12, (float)f4, (float)1.0f);
            RenderManager.field_78727_a.field_78721_f.func_78443_a((EntityLivingBase)Minecraft.func_71410_x().field_71439_g, item, 0);
        }
        GL11.glPopMatrix();
    }

    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)-0.3f, (float)0.15f, (float)0.0f);
        GL11.glScalef((float)0.9f, (float)0.7f, (float)0.9f);
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        BlockWeaponRackRenderer.setWoodTexture(metadata);
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        this.model.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
        GL11.glPopMatrix();
    }

    public int getRenderId() {
        return CustomItems.weaponsRack.func_149645_b();
    }

    @Override
    public int specialRenderDistance() {
        return 26;
    }
}

