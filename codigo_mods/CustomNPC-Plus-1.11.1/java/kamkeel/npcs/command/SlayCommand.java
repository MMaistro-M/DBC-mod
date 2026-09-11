/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandException
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.boss.EntityDragon
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.item.EntityXPOrb
 *  net.minecraft.entity.monster.EntityGhast
 *  net.minecraft.entity.monster.EntityMob
 *  net.minecraft.entity.passive.EntityAnimal
 *  net.minecraft.entity.passive.EntityHorse
 *  net.minecraft.entity.passive.EntityTameable
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.util.AxisAlignedBB
 */
package kamkeel.npcs.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kamkeel.npcs.command.CommandKamkeelBase;
import kamkeel.npcs.util.ColorUtil;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.AxisAlignedBB;
import noppes.npcs.entity.EntityNPCInterface;

public class SlayCommand
extends CommandKamkeelBase {
    public Map<String, Class<?>> SlayMap = new LinkedHashMap();

    public SlayCommand() {
        this.SlayMap.clear();
        this.SlayMap.put("all", EntityLivingBase.class);
        this.SlayMap.put("mobs", EntityMob.class);
        this.SlayMap.put("animals", EntityAnimal.class);
        this.SlayMap.put("items", EntityItem.class);
        this.SlayMap.put("xporbs", EntityXPOrb.class);
        this.SlayMap.put("npcs", EntityNPCInterface.class);
        HashMap list = new HashMap(EntityList.field_75625_b);
        for (String name : list.keySet()) {
            Class cls = (Class)list.get(name);
            if (EntityNPCInterface.class.isAssignableFrom(cls) || !EntityLivingBase.class.isAssignableFrom(cls)) continue;
            this.SlayMap.put(name.toLowerCase(), (Class<?>)list.get(name));
        }
        this.SlayMap.remove("monster");
        this.SlayMap.remove("mob");
    }

    public String func_71517_b() {
        return "slay";
    }

    @Override
    public String getDescription() {
        return "Kills given entity within range. Tab to see options.";
    }

    @Override
    public String getUsage() {
        return "<type>.. [range]";
    }

    @Override
    public void func_71515_b(ICommandSender sender, String[] args) throws CommandException {
        EntityPlayerMP player = (EntityPlayerMP)sender;
        ArrayList toDelete = new ArrayList();
        boolean deleteNPCs = false;
        for (String delete : args) {
            Class<?> cls = this.SlayMap.get(delete = delete.toLowerCase());
            if (cls != null) {
                toDelete.add(cls);
            }
            if (delete.equals("mobs")) {
                toDelete.add(EntityGhast.class);
                toDelete.add(EntityDragon.class);
            }
            if (!delete.equals("npcs")) continue;
            deleteNPCs = true;
        }
        int count = 0;
        int range = 120;
        try {
            range = Integer.parseInt(args[args.length - 1]);
        }
        catch (NumberFormatException numberFormatException) {
            // empty catch block
        }
        AxisAlignedBB box = player.field_70121_D.func_72314_b((double)range, (double)range, (double)range);
        List list = sender.func_130014_f_().func_72872_a(EntityLivingBase.class, box);
        for (Entity entity : list) {
            if (entity instanceof EntityPlayer || entity instanceof EntityTameable && ((EntityTameable)entity).func_70909_n() || entity instanceof EntityNPCInterface && !deleteNPCs || !this.delete(entity, toDelete)) continue;
            ++count;
        }
        if (toDelete.contains(EntityXPOrb.class)) {
            list = sender.func_130014_f_().func_72872_a(EntityXPOrb.class, box);
            for (Entity entity : list) {
                entity.field_70128_L = true;
                ++count;
            }
        }
        if (toDelete.contains(EntityItem.class)) {
            list = sender.func_130014_f_().func_72872_a(EntityItem.class, box);
            for (Entity entity : list) {
                entity.field_70128_L = true;
                ++count;
            }
        }
        ColorUtil.sendResult(sender, count + " entities deleted");
    }

    private boolean delete(Entity entity, ArrayList<Class<?>> toDelete) {
        for (Class<?> delete : toDelete) {
            if (delete == EntityAnimal.class && entity instanceof EntityHorse || !delete.isAssignableFrom(entity.getClass())) continue;
            entity.field_70128_L = true;
            return true;
        }
        return false;
    }

    public List func_71516_a(ICommandSender sender, String[] args) {
        return SlayCommand.func_71530_a((String[])args, (String[])this.SlayMap.keySet().toArray(new String[this.SlayMap.size()]));
    }
}

