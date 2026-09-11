/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.World
 *  net.minecraftforge.common.IExtendedEntityProperties
 */
package noppes.npcs.controllers;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import noppes.npcs.api.entity.IEntity;

public class ScriptEntityData
implements IExtendedEntityProperties {
    public IEntity base;

    public ScriptEntityData(IEntity base) {
        this.base = base;
    }

    public void saveNBTData(NBTTagCompound compound) {
    }

    public void loadNBTData(NBTTagCompound compound) {
    }

    public void init(Entity entity, World world) {
    }
}

