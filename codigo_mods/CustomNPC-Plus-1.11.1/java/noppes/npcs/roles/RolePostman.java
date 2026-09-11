/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.IChatComponent
 */
package noppes.npcs.roles;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import noppes.npcs.CustomNpcs;
import noppes.npcs.NpcMiscInventory;
import noppes.npcs.constants.EnumGuiType;
import noppes.npcs.controllers.PlayerDataController;
import noppes.npcs.entity.EntityNPCInterface;
import noppes.npcs.roles.RoleInterface;

public class RolePostman
extends RoleInterface {
    public NpcMiscInventory inventory = new NpcMiscInventory(1);
    private List<EntityPlayer> recentlyChecked = new ArrayList<EntityPlayer>();
    private List<EntityPlayer> toCheck;

    public RolePostman(EntityNPCInterface npc) {
        super(npc);
    }

    @Override
    public boolean aiShouldExecute() {
        if (this.npc.field_70173_aa % 20 != 0) {
            return false;
        }
        this.toCheck = this.npc.field_70170_p.func_72872_a(EntityPlayer.class, this.npc.field_70121_D.func_72314_b(10.0, 10.0, 10.0));
        this.toCheck.removeAll(this.recentlyChecked);
        List listMax = this.npc.field_70170_p.func_72872_a(EntityPlayer.class, this.npc.field_70121_D.func_72314_b(20.0, 20.0, 20.0));
        this.recentlyChecked.retainAll(listMax);
        this.recentlyChecked.addAll(this.toCheck);
        for (EntityPlayer player : this.toCheck) {
            if (!PlayerDataController.Instance.hasMail(player)) continue;
            player.func_145747_a((IChatComponent)new ChatComponentTranslation("You've got mail", new Object[0]));
        }
        return false;
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbttagcompound) {
        nbttagcompound.func_74782_a("PostInv", (NBTBase)this.inventory.getToNBT());
        return nbttagcompound;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbttagcompound) {
        this.inventory.setFromNBT(nbttagcompound.func_74775_l("PostInv"));
    }

    @Override
    public void interact(EntityPlayer player) {
        player.openGui((Object)CustomNpcs.instance, EnumGuiType.PlayerMailman.ordinal(), player.field_70170_p, 1, 1, 0);
    }
}

