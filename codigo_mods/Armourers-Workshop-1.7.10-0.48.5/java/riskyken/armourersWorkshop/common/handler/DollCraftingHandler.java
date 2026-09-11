/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityFallingBlock
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.IWorldAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.world.WorldEvent$Load
 */
package riskyken.armourersWorkshop.common.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IWorldAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.WorldEvent;
import riskyken.armourersWorkshop.common.blocks.BlockMannequin;
import riskyken.armourersWorkshop.common.blocks.ModBlocks;
import riskyken.armourersWorkshop.utils.ModLogger;

public class DollCraftingHandler
implements IWorldAccess {
    public DollCraftingHandler() {
        MinecraftForge.EVENT_BUS.register((Object)this);
    }

    @SubscribeEvent
    public void onLoadWorld(WorldEvent.Load event) {
        ModLogger.log(String.format("Adding world access to world %s", event.world.toString()));
        event.world.func_72954_a((IWorldAccess)this);
    }

    public void func_147586_a(int x, int y, int z) {
    }

    public void func_147588_b(int x, int y, int z) {
    }

    public void func_147585_a(int minX, int minY, int minZ, int maxX, int maxY, int maxZ) {
    }

    public void func_72704_a(String soundName, double x, double y, double z, float volume, float pitch) {
    }

    public void func_85102_a(EntityPlayer player, String soundName, double x, double y, double z, float volume, float pitch) {
    }

    public void func_72708_a(String particleType, double x, double y, double z, double velX, double velY, double velZ) {
    }

    public void func_72703_a(Entity entity) {
    }

    public void func_72709_b(Entity entity) {
        int z;
        int y;
        int x;
        Block block;
        World world = entity.field_70170_p;
        if (!world.field_72995_K && entity instanceof EntityFallingBlock && ((EntityFallingBlock)entity).func_145805_f() == Blocks.field_150467_bQ && (block = world.func_147439_a(x = MathHelper.func_76128_c((double)entity.field_70165_t), y = MathHelper.func_76128_c((double)entity.field_70163_u) - 1, z = MathHelper.func_76128_c((double)entity.field_70161_v))) == ModBlocks.mannequin) {
            ((BlockMannequin)block).convertToDoll(world, x, y, z);
        }
    }

    public void func_72702_a(String recordName, int x, int y, int z) {
    }

    public void func_82746_a(int p_82746_1_, int p_82746_2_, int p_82746_3_, int p_82746_4_, int p_82746_5_) {
    }

    public void func_72706_a(EntityPlayer p_72706_1_, int p_72706_2_, int p_72706_3_, int p_72706_4_, int p_72706_5_, int p_72706_6_) {
    }

    public void func_147587_b(int p_147587_1_, int p_147587_2_, int p_147587_3_, int p_147587_4_, int p_147587_5_) {
    }

    public void func_147584_b() {
    }
}

