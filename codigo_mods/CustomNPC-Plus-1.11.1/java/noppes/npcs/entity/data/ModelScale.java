/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.entity.data;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.api.entity.data.IModelScale;
import noppes.npcs.api.entity.data.IModelScalePart;
import noppes.npcs.entity.data.ModelScalePart;
import noppes.npcs.util.ValueUtil;

public class ModelScale
implements IModelScale {
    public ModelScalePart head = new ModelScalePart();
    public ModelScalePart body = new ModelScalePart();
    public ModelScalePart arms = new ModelScalePart();
    public ModelScalePart legs = new ModelScalePart();

    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.func_74782_a("HeadConfig", (NBTBase)this.head.writeToNBT());
        compound.func_74782_a("BodyConfig", (NBTBase)this.body.writeToNBT());
        compound.func_74782_a("ArmsConfig", (NBTBase)this.arms.writeToNBT());
        compound.func_74782_a("LegsConfig", (NBTBase)this.legs.writeToNBT());
        return compound;
    }

    public void readFromNBT(NBTTagCompound compound) {
        this.head.readFromNBT(compound.func_74775_l("HeadConfig"));
        this.body.readFromNBT(compound.func_74775_l("BodyConfig"));
        this.arms.readFromNBT(compound.func_74775_l("ArmsConfig"));
        this.legs.readFromNBT(compound.func_74775_l("LegsConfig"));
    }

    @Override
    public IModelScalePart getPart(int part) {
        switch (ValueUtil.clamp(part, 0, 3)) {
            case 0: {
                return this.head;
            }
            case 1: {
                return this.body;
            }
            case 2: {
                return this.arms;
            }
            case 3: {
                return this.legs;
            }
        }
        return null;
    }
}

