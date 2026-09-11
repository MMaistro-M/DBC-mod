/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockIce
 *  net.minecraft.block.BlockLeavesBase
 *  net.minecraft.block.BlockVine
 *  net.minecraft.entity.player.EntityPlayerMP
 */
package foxz.command;

import foxz.commandhelper.ChMcLogger;
import foxz.commandhelper.annotations.Command;
import foxz.commandhelper.annotations.SubCommand;
import foxz.commandhelper.permissions.OpOnly;
import foxz.commandhelper.permissions.PlayerOnly;
import java.util.Arrays;
import java.util.Set;
import kamkeel.npcs.network.PacketHandler;
import kamkeel.npcs.network.enums.EnumConfigOperation;
import kamkeel.npcs.network.packets.data.ConfigCommandPacket;
import net.minecraft.block.Block;
import net.minecraft.block.BlockIce;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.block.BlockVine;
import net.minecraft.entity.player.EntityPlayerMP;
import noppes.npcs.CustomNpcs;
import noppes.npcs.config.ConfigItem;
import noppes.npcs.config.ConfigMain;
import noppes.npcs.controllers.ChunkController;

@Command(name="config", desc="Some config things you can set ", usage="")
public class CmdConfig
extends ChMcLogger {
    public CmdConfig(Object sender) {
        super(sender);
    }

    @SubCommand(desc="Disable/Enable the natural leaves decay", usage="<true/false>", permissions={OpOnly.class}, hasEmptyCall=true)
    public boolean leavesdecay(String[] args) {
        if (args.length == 0) {
            this.sendmessage("LeavesDecay: " + ConfigMain.LeavesDecayEnabled);
        } else {
            ConfigMain.LeavesDecayEnabled = Boolean.parseBoolean(args[0]);
            ConfigMain.LeavesDecayEnabledProperty.set(ConfigMain.LeavesDecayEnabled);
            if (ConfigMain.config.hasChanged()) {
                ConfigMain.config.save();
            }
            Set names = Block.field_149771_c.func_148742_b();
            for (String name : names) {
                Block block = (Block)Block.field_149771_c.func_82594_a(name);
                if (!(block instanceof BlockLeavesBase)) continue;
                block.func_149675_a(ConfigMain.LeavesDecayEnabled);
            }
            this.sendmessage("LeavesDecay is now " + ConfigMain.LeavesDecayEnabled);
        }
        return true;
    }

    @SubCommand(desc="Disable/Enable the vines growing", usage="<true/false>", permissions={OpOnly.class}, hasEmptyCall=true)
    public boolean vinegrowth(String[] args) {
        if (args.length == 0) {
            this.sendmessage("VineGrowth: " + ConfigMain.VineGrowthEnabled);
        } else {
            ConfigMain.VineGrowthEnabled = Boolean.parseBoolean(args[0]);
            ConfigMain.VineGrowthEnabledProperty.set(ConfigMain.VineGrowthEnabled);
            if (ConfigMain.config.hasChanged()) {
                ConfigMain.config.save();
            }
            Set names = Block.field_149771_c.func_148742_b();
            for (String name : names) {
                Block block = (Block)Block.field_149771_c.func_82594_a(name);
                if (!(block instanceof BlockVine)) continue;
                block.func_149675_a(ConfigMain.VineGrowthEnabled);
            }
            this.sendmessage("VineGrowth is now " + ConfigMain.VineGrowthEnabled);
        }
        return true;
    }

    @SubCommand(desc="Disable/Enable the ice melting", usage="<true/false>", permissions={OpOnly.class}, hasEmptyCall=true)
    public boolean icemelts(String[] args) {
        if (args.length == 0) {
            this.sendmessage("IceMelts: " + ConfigMain.IceMeltsEnabled);
        } else {
            ConfigMain.IceMeltsEnabled = Boolean.parseBoolean(args[0]);
            ConfigMain.IceMeltsEnabledProperty.set(ConfigMain.IceMeltsEnabled);
            if (ConfigMain.config.hasChanged()) {
                ConfigMain.config.save();
            }
            Set names = Block.field_149771_c.func_148742_b();
            for (String name : names) {
                Block block = (Block)Block.field_149771_c.func_82594_a(name);
                if (!(block instanceof BlockIce)) continue;
                block.func_149675_a(ConfigMain.IceMeltsEnabled);
            }
            this.sendmessage("IceMelts is now " + ConfigMain.IceMeltsEnabled);
        }
        return true;
    }

    @SubCommand(desc="Disable/Enable guns shooting", usage="<true/false>", permissions={OpOnly.class}, hasEmptyCall=true)
    public boolean guns(String[] args) {
        if (args.length == 0) {
            this.sendmessage("GunsEnabled: " + ConfigItem.GunsEnabled);
        } else {
            ConfigItem.GunsEnabled = Boolean.parseBoolean(args[0]);
            ConfigItem.GunsEnabledProperty.set(ConfigItem.GunsEnabled);
            if (ConfigMain.config.hasChanged()) {
                ConfigMain.config.save();
            }
            this.sendmessage("GunsEnabled is now " + ConfigItem.GunsEnabled);
        }
        return true;
    }

    @SubCommand(desc="Freezes/Unfreezes npcs", usage="<true/false>", permissions={OpOnly.class}, hasEmptyCall=true)
    public boolean freezenpcs(String[] args) {
        if (args.length == 0) {
            this.sendmessage("Frozen NPCs: " + CustomNpcs.FreezeNPCs);
        } else {
            CustomNpcs.FreezeNPCs = Boolean.parseBoolean(args[0]);
            this.sendmessage("FrozenNPCs is now " + CustomNpcs.FreezeNPCs);
        }
        return true;
    }

    @SubCommand(desc="Set how many active chunkloaders you can have", usage="<number>", permissions={OpOnly.class}, hasEmptyCall=true)
    public boolean chunkloaders(String[] args) {
        if (args.length == 0) {
            this.sendmessage("ChunkLoaders: " + ChunkController.Instance.size() + "/" + ConfigMain.ChunkLoaders);
        } else {
            int size;
            try {
                ConfigMain.ChunkLoaders = Integer.parseInt(args[0]);
            }
            catch (NumberFormatException ex) {
                this.sendmessage("Didnt get a number");
                return false;
            }
            ConfigMain.ChunkLoadersProperty.set(ConfigMain.ChunkLoaders);
            if (ConfigMain.config.hasChanged()) {
                ConfigMain.config.save();
            }
            if ((size = ChunkController.Instance.size()) > ConfigMain.ChunkLoaders) {
                ChunkController.Instance.unload(size - ConfigMain.ChunkLoaders);
                this.sendmessage(size - ConfigMain.ChunkLoaders + " chunksloaders unloaded");
            }
            this.sendmessage("ChunkLoaders: " + ChunkController.Instance.size() + "/" + ConfigMain.ChunkLoaders);
        }
        return true;
    }

    @SubCommand(desc="Get/Set font", usage="[type] [size]", permissions={PlayerOnly.class}, hasEmptyCall=true)
    public void font(String[] args) {
        int size = 18;
        if (args.length > 1) {
            try {
                size = Integer.parseInt(args[args.length - 1]);
                args = Arrays.copyOfRange(args, 0, args.length - 1);
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
        String font = "";
        for (int i = 0; i < args.length; ++i) {
            font = font + " " + args[i];
        }
        PacketHandler.Instance.sendToPlayer(new ConfigCommandPacket(EnumConfigOperation.FONT, font.trim(), size), (EntityPlayerMP)this.pcParam);
    }
}

