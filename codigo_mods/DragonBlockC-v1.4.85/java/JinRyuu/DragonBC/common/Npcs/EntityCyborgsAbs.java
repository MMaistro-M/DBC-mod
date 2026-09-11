/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.world.World
 */
package JinRyuu.DragonBC.common.Npcs;

import JinRyuu.DragonBC.common.Npcs.EntityCyborgs;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class EntityCyborgsAbs
extends EntityCyborgs {
    public int randomSoundDelay = 0;

    public EntityCyborgsAbs(World par1World) {
        super(par1World);
    }

    private void becomeAngryAt(Entity par1Entity) {
        this.field_70789_a = par1Entity;
        this.angerLevel = 400 + this.field_70146_Z.nextInt(400);
        this.randomSoundDelay = this.field_70146_Z.nextInt(40);
    }
}

