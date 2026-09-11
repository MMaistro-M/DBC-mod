/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 */
package noppes.npcs.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import noppes.npcs.entity.EntityProjectile;

public class EntityMagicProjectile
extends EntityProjectile {
    private EntityPlayer player;
    private ItemStack equiped;

    public EntityMagicProjectile(World par1World) {
        super(par1World);
    }

    public EntityMagicProjectile(World par1World, EntityPlayer player, ItemStack item, boolean isNPC) {
        super(par1World, (EntityLivingBase)player, item, isNPC);
        this.player = player;
        this.equiped = player.field_71071_by.func_70448_g();
    }

    @Override
    public void func_70071_h_() {
        if (!(this.field_70170_p.field_72995_K || this.player != null && this.player.field_71071_by.func_70448_g() == this.equiped)) {
            this.func_70106_y();
        }
        super.func_70071_h_();
    }

    public String func_70005_c_() {
        return StatCollector.func_74838_a((String)"entity.throwableitem.name");
    }
}

