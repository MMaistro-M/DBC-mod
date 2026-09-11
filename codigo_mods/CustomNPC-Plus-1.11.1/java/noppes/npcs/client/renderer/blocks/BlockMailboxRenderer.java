/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler
 *  cpw.mods.fml.client.registry.RenderingRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.IBlockAccess
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.renderer.blocks;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import noppes.npcs.CustomItems;
import noppes.npcs.blocks.BlockMailbox;
import noppes.npcs.client.model.blocks.ModelMailbox;
import noppes.npcs.client.model.blocks.ModelMailboxWow;
import noppes.npcs.client.model.blocks.legacy.ModelLegacyMailboxUS;
import noppes.npcs.config.ConfigClient;
import org.lwjgl.opengl.GL11;

public class BlockMailboxRenderer
extends TileEntitySpecialRenderer
implements ISimpleBlockRenderingHandler {
    private final ModelMailbox mailbox = new ModelMailbox();
    private static final ResourceLocation mailbox_texture = new ResourceLocation("customnpcs", "textures/models/mailbox.png");
    private final ModelLegacyMailboxUS model = new ModelLegacyMailboxUS();
    private final ModelMailboxWow model2 = new ModelMailboxWow();
    private static final ResourceLocation text1 = new ResourceLocation("customnpcs", "textures/models/legacy/mailbox1.png");
    private static final ResourceLocation text2 = new ResourceLocation("customnpcs", "textures/models/mailbox2.png");

    public BlockMailboxRenderer() {
        ((BlockMailbox)CustomItems.mailbox).renderId = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)this);
    }

    public void func_147500_a(TileEntity var1, double var2, double var4, double var6, float var8) {
        int meta = var1.func_145832_p() | 4;
        int type = var1.func_145832_p() >> 2;
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)var2 + 0.5f), (float)((float)var4 + 1.5f), (float)((float)var6 + 0.5f));
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)(90 * meta), (float)0.0f, (float)1.0f, (float)0.0f);
        if (type == 0) {
            if (ConfigClient.LegacyMailbox) {
                this.func_147499_a(text1);
                this.model.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
            } else {
                GL11.glScalef((float)0.99f, (float)1.0f, (float)0.99f);
                this.func_147499_a(mailbox_texture);
                this.mailbox.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
            }
        }
        if (type == 1) {
            this.func_147499_a(text2);
            this.model2.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
        }
        GL11.glPopMatrix();
    }

    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)0.0f, (float)0.74f, (float)0.0f);
        GL11.glScalef((float)0.9f, (float)0.86f, (float)0.9f);
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        if (metadata == 0) {
            if (ConfigClient.LegacyMailbox) {
                this.func_147499_a(text1);
                this.model.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
            } else {
                this.func_147499_a(mailbox_texture);
                this.mailbox.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
            }
        }
        if (metadata == 1) {
            this.func_147499_a(text2);
            this.model2.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
        }
        GL11.glPopMatrix();
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        return false;
    }

    public int getRenderId() {
        return CustomItems.mailbox.func_149645_b();
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }
}

