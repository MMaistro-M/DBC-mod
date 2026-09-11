/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.world.IWorldAccess
 */
package somehussar.janino;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.IWorldAccess;
import noppes.npcs.entity.EntityNPCInterface;

public class EntityUnloadListener
implements IWorldAccess {
    public void func_147586_a(int p_147586_1_, int p_147586_2_, int p_147586_3_) {
    }

    public void func_147588_b(int p_147588_1_, int p_147588_2_, int p_147588_3_) {
    }

    public void func_147585_a(int p_147585_1_, int p_147585_2_, int p_147585_3_, int p_147585_4_, int p_147585_5_, int p_147585_6_) {
    }

    public void func_72704_a(String soundName, double x, double y, double z, float volume, float pitch) {
    }

    public void func_85102_a(EntityPlayer p_85102_1_, String p_85102_2_, double p_85102_3_, double p_85102_5_, double p_85102_7_, float p_85102_9_, float p_85102_10_) {
    }

    public void func_72708_a(String p_72708_1_, double p_72708_2_, double p_72708_4_, double p_72708_6_, double p_72708_8_, double p_72708_10_, double p_72708_12_) {
    }

    public void func_72703_a(Entity p_72703_1_) {
    }

    public void func_72709_b(Entity entity) {
        if (entity instanceof EntityNPCInterface) {
            EntityNPCInterface entityNPCInterface = (EntityNPCInterface)entity;
        }
    }

    public void func_72702_a(String p_72702_1_, int p_72702_2_, int p_72702_3_, int p_72702_4_) {
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

