/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.event.FMLInterModComms
 *  net.minecraft.nbt.NBTTagCompound
 */
package riskyken.armourersWorkshop.common.addons;

import cpw.mods.fml.common.event.FMLInterModComms;
import net.minecraft.nbt.NBTTagCompound;
import riskyken.armourersWorkshop.common.addons.ModAddon;

public class AddonAquaTweaks
extends ModAddon {
    public AddonAquaTweaks() {
        super("AquaTweaks", "Aqua Tweaks");
    }

    @Override
    public void init() {
        NBTTagCompound compound = new NBTTagCompound();
        compound.func_74778_a("modid", "armourersWorkshop");
        compound.func_74778_a("block", "block.mannequin");
        FMLInterModComms.sendMessage((String)this.getModId(), (String)"registerAquaConnectable", (NBTTagCompound)compound);
        compound = new NBTTagCompound();
        compound.func_74778_a("modid", "armourersWorkshop");
        compound.func_74778_a("block", "block.doll");
        FMLInterModComms.sendMessage((String)this.getModId(), (String)"registerAquaConnectable", (NBTTagCompound)compound);
        compound = new NBTTagCompound();
        compound.func_74778_a("modid", "armourersWorkshop");
        compound.func_74778_a("block", "block.miniArmourer");
        FMLInterModComms.sendMessage((String)this.getModId(), (String)"registerAquaConnectable", (NBTTagCompound)compound);
        compound = new NBTTagCompound();
        compound.func_74778_a("modid", "armourersWorkshop");
        compound.func_74778_a("block", "block.skinnable");
        FMLInterModComms.sendMessage((String)this.getModId(), (String)"registerAquaConnectable", (NBTTagCompound)compound);
    }
}

