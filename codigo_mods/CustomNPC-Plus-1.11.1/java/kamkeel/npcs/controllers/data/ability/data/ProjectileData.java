/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 */
package kamkeel.npcs.controllers.data.ability.data;

import kamkeel.npcs.controllers.data.ability.data.energy.EnergyAnchorData;
import kamkeel.npcs.controllers.data.ability.data.energy.EnergyDisplayData;
import kamkeel.npcs.controllers.data.ability.enums.AnchorPoint;
import net.minecraft.nbt.NBTTagCompound;

public class ProjectileData {
    public EnergyAnchorData anchor;
    public boolean colorOverride = false;
    public int innerColor = 0xFFFFFF;
    public int outerColor = 0x8888FF;

    public ProjectileData(AnchorPoint defaultAnchor) {
        this.anchor = new EnergyAnchorData(defaultAnchor);
    }

    public EnergyDisplayData resolveDisplay(EnergyDisplayData primary) {
        EnergyDisplayData resolved = primary.copy();
        if (this.colorOverride) {
            resolved.innerColor = this.innerColor;
            resolved.outerColor = this.outerColor;
        }
        return resolved;
    }

    public void writeNBT(NBTTagCompound nbt) {
        this.anchor.writeNBT(nbt);
        nbt.func_74757_a("colorOverride", this.colorOverride);
        if (this.colorOverride) {
            nbt.func_74768_a("overrideInnerColor", this.innerColor);
            nbt.func_74768_a("overrideOuterColor", this.outerColor);
        }
    }

    public void readNBT(NBTTagCompound nbt) {
        this.anchor.readNBT(nbt);
        this.colorOverride = nbt.func_74767_n("colorOverride");
        if (this.colorOverride) {
            this.innerColor = nbt.func_74762_e("overrideInnerColor");
            this.outerColor = nbt.func_74762_e("overrideOuterColor");
        }
    }

    public ProjectileData copy() {
        ProjectileData copy = new ProjectileData(this.anchor.anchorPoint);
        copy.anchor = this.anchor.copy();
        copy.colorOverride = this.colorOverride;
        copy.innerColor = this.innerColor;
        copy.outerColor = this.outerColor;
        return copy;
    }
}

