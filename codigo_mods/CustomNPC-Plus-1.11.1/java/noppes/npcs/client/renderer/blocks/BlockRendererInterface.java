/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.entity.RenderItem
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.IBlockAccess
 */
package noppes.npcs.client.renderer.blocks;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;

public abstract class BlockRendererInterface
extends TileEntitySpecialRenderer
implements ISimpleBlockRenderingHandler {
    protected static final ResourceLocation Stone = new ResourceLocation("customnpcs", "textures/cache/stone.png");
    protected static final ResourceLocation Iron = new ResourceLocation("customnpcs", "textures/cache/iron_block.png");
    protected static final ResourceLocation Gold = new ResourceLocation("customnpcs", "textures/cache/gold_block.png");
    protected static final ResourceLocation Diamond = new ResourceLocation("customnpcs", "textures/cache/diamond_block.png");
    protected static final ResourceLocation PlanksOak = new ResourceLocation("customnpcs", "textures/cache/planks_oak.png");
    protected static final ResourceLocation PlanksBigOak = new ResourceLocation("customnpcs", "textures/cache/planks_big_oak.png");
    protected static final ResourceLocation PlanksSpruce = new ResourceLocation("customnpcs", "textures/cache/planks_spruce.png");
    protected static final ResourceLocation PlanksBirch = new ResourceLocation("customnpcs", "textures/cache/planks_birch.png");
    protected static final ResourceLocation PlanksAcacia = new ResourceLocation("customnpcs", "textures/cache/planks_acacia.png");
    protected static final ResourceLocation PlanksJungle = new ResourceLocation("customnpcs", "textures/cache/planks_jungle.png");
    protected static final ResourceLocation Steel = new ResourceLocation("customnpcs", "textures/models/legacy/Steel.png");
    protected static final ResourceLocation Logs = new ResourceLocation("textures/blocks/log_oak.png");
    protected static final ResourceLocation Cobble = new ResourceLocation("textures/blocks/cobblestone.png");
    protected static final ResourceLocation Flame = new ResourceLocation("customnpcs", "textures/models/flame.png");
    protected static final RenderItem renderer = new RenderItem();

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        return false;
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }

    public boolean playerTooFar(TileEntity tile) {
        Minecraft mc = Minecraft.func_71410_x();
        double d6 = mc.field_71451_h.field_70165_t - (double)tile.field_145851_c;
        double d7 = mc.field_71451_h.field_70163_u - (double)tile.field_145848_d;
        double d8 = mc.field_71451_h.field_70161_v - (double)tile.field_145849_e;
        return d6 * d6 + d7 * d7 + d8 * d8 > (double)(this.specialRenderDistance() * this.specialRenderDistance());
    }

    public int specialRenderDistance() {
        return 20;
    }

    public static void setWoodTexture(int meta) {
        TextureManager manager = Minecraft.func_71410_x().func_110434_K();
        if (meta == 1) {
            manager.func_110577_a(PlanksSpruce);
        } else if (meta == 2) {
            manager.func_110577_a(PlanksBirch);
        } else if (meta == 3) {
            manager.func_110577_a(PlanksJungle);
        } else if (meta == 4) {
            manager.func_110577_a(PlanksAcacia);
        } else if (meta == 5) {
            manager.func_110577_a(PlanksBigOak);
        } else {
            manager.func_110577_a(PlanksOak);
        }
    }

    public static void setMaterialTexture(int meta) {
        TextureManager manager = Minecraft.func_71410_x().func_110434_K();
        if (meta == 1) {
            manager.func_110577_a(Stone);
        } else if (meta == 2) {
            manager.func_110577_a(Iron);
        } else if (meta == 3) {
            manager.func_110577_a(Gold);
        } else if (meta == 4) {
            manager.func_110577_a(Diamond);
        } else {
            manager.func_110577_a(PlanksOak);
        }
    }
}

