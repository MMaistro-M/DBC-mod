/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.world.World
 */
package JinRyuu.JRMCore.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLiving;
import net.minecraft.world.World;

@SideOnly(value=Side.CLIENT)
public class EntityNull
extends EntityLiving {
    public EntityNull(World w) {
        super(w);
    }
}

