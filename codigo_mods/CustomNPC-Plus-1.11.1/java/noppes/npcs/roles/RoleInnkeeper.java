/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.IChatComponent
 */
package noppes.npcs.roles;

import java.util.HashMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import noppes.npcs.controllers.data.InnDoorData;
import noppes.npcs.entity.EntityNPCInterface;
import noppes.npcs.roles.RoleInterface;

public class RoleInnkeeper
extends RoleInterface {
    private String innName = "Inn";
    private HashMap<String, InnDoorData> doors = new HashMap();

    public RoleInnkeeper(EntityNPCInterface npc) {
        super(npc);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbttagcompound) {
        nbttagcompound.func_74778_a("InnName", this.innName);
        nbttagcompound.func_74782_a("InnDoors", this.nbtInnDoors(this.doors));
        return nbttagcompound;
    }

    private NBTBase nbtInnDoors(HashMap<String, InnDoorData> doors1) {
        NBTTagList nbttaglist = new NBTTagList();
        if (doors1 == null) {
            return nbttaglist;
        }
        HashMap<String, InnDoorData> doors2 = doors1;
        for (String name : doors2.keySet()) {
            InnDoorData door = doors2.get(name);
            if (door == null) continue;
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            nbttagcompound.func_74778_a("Name", name);
            nbttagcompound.func_74768_a("posX", door.x);
            nbttagcompound.func_74768_a("posY", door.y);
            nbttagcompound.func_74768_a("posZ", door.z);
            nbttaglist.func_74742_a((NBTBase)nbttagcompound);
        }
        return nbttaglist;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbttagcompound) {
        this.innName = nbttagcompound.func_74779_i("InnName");
        this.doors = this.getInnDoors(nbttagcompound.func_150295_c("InnDoors", 10));
    }

    private HashMap<String, InnDoorData> getInnDoors(NBTTagList tagList) {
        HashMap<String, InnDoorData> list = new HashMap<String, InnDoorData>();
        for (int i = 0; i < tagList.func_74745_c(); ++i) {
            NBTTagCompound nbttagcompound = tagList.func_150305_b(i);
            String name = nbttagcompound.func_74779_i("Name");
            InnDoorData door = new InnDoorData();
            door.x = nbttagcompound.func_74762_e("posX");
            door.y = nbttagcompound.func_74762_e("posY");
            door.z = nbttagcompound.func_74762_e("posZ");
            list.put(name, door);
        }
        return list;
    }

    @Override
    public void interact(EntityPlayer player) {
        this.npc.say(player, this.npc.advanced.getInteractLine());
        if (this.doors.isEmpty()) {
            player.func_145747_a((IChatComponent)new ChatComponentTranslation("No Rooms available", new Object[0]));
        }
    }
}

