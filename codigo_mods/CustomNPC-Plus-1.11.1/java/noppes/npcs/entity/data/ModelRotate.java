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
import noppes.npcs.api.entity.data.IModelRotate;
import noppes.npcs.api.entity.data.IModelRotatePart;
import noppes.npcs.entity.data.ModelRotatePart;
import noppes.npcs.util.ValueUtil;

public class ModelRotate
implements IModelRotate {
    public boolean whileStanding = true;
    public boolean whileAttacking = false;
    public boolean whileMoving = false;
    public ModelRotatePart head = new ModelRotatePart();
    public ModelRotatePart body = new ModelRotatePart();
    public ModelRotatePart larm = new ModelRotatePart();
    public ModelRotatePart rarm = new ModelRotatePart();
    public ModelRotatePart lleg = new ModelRotatePart();
    public ModelRotatePart rleg = new ModelRotatePart();

    public NBTTagCompound writeToNBT() {
        NBTTagCompound compound = new NBTTagCompound();
        compound.func_74757_a("PuppetStanding", this.whileStanding);
        compound.func_74757_a("PuppetAttacking", this.whileAttacking);
        compound.func_74757_a("PuppetMoving", this.whileMoving);
        compound.func_74782_a("PuppetHead", (NBTBase)this.head.writeToNBT());
        compound.func_74782_a("PuppetBody", (NBTBase)this.body.writeToNBT());
        compound.func_74782_a("PuppetRArm", (NBTBase)this.rarm.writeToNBT());
        compound.func_74782_a("PuppetLArm", (NBTBase)this.larm.writeToNBT());
        compound.func_74782_a("PuppetRLeg", (NBTBase)this.rleg.writeToNBT());
        compound.func_74782_a("PuppetLLeg", (NBTBase)this.lleg.writeToNBT());
        return compound;
    }

    public void readFromNBT(NBTTagCompound compound) {
        this.whileStanding = compound.func_74767_n("PuppetStanding");
        this.whileAttacking = compound.func_74767_n("PuppetAttacking");
        this.whileMoving = compound.func_74767_n("PuppetMoving");
        this.head.readFromNBT(compound.func_74775_l("PuppetHead"));
        this.body.readFromNBT(compound.func_74775_l("PuppetBody"));
        this.rarm.readFromNBT(compound.func_74775_l("PuppetRArm"));
        this.larm.readFromNBT(compound.func_74775_l("PuppetLArm"));
        this.rleg.readFromNBT(compound.func_74775_l("PuppetRLeg"));
        this.lleg.readFromNBT(compound.func_74775_l("PuppetLLeg"));
    }

    @Override
    public boolean whileStanding() {
        return this.whileStanding;
    }

    @Override
    public void whileStanding(boolean whileStanding) {
        this.whileStanding = whileStanding;
    }

    @Override
    public boolean whileAttacking() {
        return this.whileAttacking;
    }

    @Override
    public void whileAttacking(boolean whileAttacking) {
        this.whileAttacking = whileAttacking;
    }

    @Override
    public boolean whileMoving() {
        return this.whileMoving;
    }

    @Override
    public void whileMoving(boolean whileMoving) {
        this.whileMoving = whileMoving;
    }

    @Override
    public IModelRotatePart getPart(int part) {
        switch (ValueUtil.clamp(part, 0, 5)) {
            case 0: {
                return this.head;
            }
            case 1: {
                return this.body;
            }
            case 2: {
                return this.larm;
            }
            case 3: {
                return this.rarm;
            }
            case 4: {
                return this.lleg;
            }
            case 5: {
                return this.rleg;
            }
        }
        return null;
    }
}

