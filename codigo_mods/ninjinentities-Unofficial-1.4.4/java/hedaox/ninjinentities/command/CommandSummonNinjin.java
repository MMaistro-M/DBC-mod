/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.ICommand
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.world.World
 */
package hedaox.ninjinentities.command;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

public class CommandSummonNinjin
implements ICommand {
    private final List<String> aliases;
    private final List<String> listOfAllEntitiesNames = new ArrayList<String>();
    protected String fullEntityName;
    protected Entity summonedEntity;

    public CommandSummonNinjin() {
        this.aliases = new ArrayList<String>();
        this.aliases.add("summonninjin");
        this.aliases.add("sumnj");
        for (String entityName : EntityList.field_75625_b.keySet()) {
            if (!(entityName instanceof String) || !entityName.contains("ninjinentities")) continue;
            this.listOfAllEntitiesNames.add(entityName);
        }
    }

    public String func_71517_b() {
        return "summonninjin";
    }

    public String func_71518_a(ICommandSender p_71518_1_) {
        return "summonninjin <text>";
    }

    public List func_71514_a() {
        return this.aliases;
    }

    public void func_71515_b(ICommandSender sender, String[] argString) {
        World world = sender.func_130014_f_();
        if (argString.length == 0) {
            sender.func_145747_a((IChatComponent)new ChatComponentText("Summoning:\u00b7" + argString[0]));
            return;
        }
        sender.func_145747_a((IChatComponent)new ChatComponentText("Summoning:\u00b7" + argString[0]));
        this.fullEntityName = argString[0];
        if (EntityList.field_75625_b.containsKey(this.fullEntityName)) {
            this.summonedEntity = EntityList.func_75620_a((String)this.fullEntityName, (World)world);
            this.summonedEntity.func_70107_b((double)sender.func_82114_b().field_71574_a, (double)sender.func_82114_b().field_71572_b, (double)sender.func_82114_b().field_71573_c);
            world.func_72838_d(this.summonedEntity);
        } else {
            sender.func_145747_a((IChatComponent)new ChatComponentText("Entity not found"));
        }
    }

    public boolean func_71519_b(ICommandSender sender) {
        if (sender instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)sender;
            return player.func_70003_b(3, "");
        }
        return true;
    }

    public List<String> func_71516_a(ICommandSender sender, String[] strArray) {
        ArrayList<String> strListComp = new ArrayList<String>();
        if (strArray.length == 1) {
            for (String strCom : this.listOfAllEntitiesNames) {
                if (strCom.length() <= strArray[0].length() || !strArray[0].startsWith(strCom.substring(0, strArray[0].length()))) continue;
                strListComp.add(strCom);
            }
        }
        return strListComp;
    }

    public boolean func_82358_a(String[] p_82358_1_, int p_82358_2_) {
        return false;
    }

    public int compareTo(Object o) {
        return 0;
    }
}

