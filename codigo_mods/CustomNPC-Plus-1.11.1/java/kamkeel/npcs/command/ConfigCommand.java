/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockIce
 *  net.minecraft.block.BlockLeavesBase
 *  net.minecraft.block.BlockVine
 *  net.minecraft.command.CommandException
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.util.ResourceLocation
 */
package kamkeel.npcs.command;

import foxz.utils.Market;
import java.util.Arrays;
import java.util.Set;
import kamkeel.npcs.command.CommandKamkeelBase;
import kamkeel.npcs.network.PacketHandler;
import kamkeel.npcs.network.enums.EnumConfigOperation;
import kamkeel.npcs.network.packets.data.ConfigCommandPacket;
import kamkeel.npcs.util.ColorUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockIce;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.block.BlockVine;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;
import noppes.npcs.CustomNpcs;
import noppes.npcs.config.ConfigItem;
import noppes.npcs.config.ConfigMain;
import noppes.npcs.controllers.ChunkController;
import noppes.npcs.controllers.PlayerDataController;

public class ConfigCommand
extends CommandKamkeelBase {
    public String func_71517_b() {
        return "config";
    }

    @Override
    public String getDescription() {
        return "Some config things you can set";
    }

    @CommandKamkeelBase.SubCommand(desc="Disable/Enable the natural leaves decay", usage="[true/false]", permission=4)
    public void leavesdecay(ICommandSender sender, String[] args) {
        if (args.length == 0) {
            ColorUtil.sendResult(sender, "LeavesDecay: \u00a7c" + ConfigMain.LeavesDecayEnabled);
        } else {
            ConfigMain.LeavesDecayEnabled = Boolean.parseBoolean(args[0]);
            ConfigMain.LeavesDecayEnabledProperty.set(ConfigMain.LeavesDecayEnabled);
            if (ConfigMain.config.hasChanged()) {
                ConfigMain.config.save();
            }
            Set names = Block.field_149771_c.func_148742_b();
            for (ResourceLocation name : names) {
                Block block = (Block)Block.field_149771_c.func_82594_a((Object)name);
                if (!(block instanceof BlockLeavesBase)) continue;
                block.func_149675_a(ConfigMain.LeavesDecayEnabled);
            }
            ColorUtil.sendResult(sender, "LeavesDecay is now \u00a7c" + ConfigMain.LeavesDecayEnabled);
        }
    }

    @CommandKamkeelBase.SubCommand(desc="Disable/Enable the vines growing", usage="[true/false]", permission=4)
    public void vinegrowth(ICommandSender sender, String[] args) {
        if (args.length == 0) {
            ColorUtil.sendResult(sender, "VineGrowth: \u00a7c" + ConfigMain.VineGrowthEnabled);
        } else {
            ConfigMain.VineGrowthEnabled = Boolean.parseBoolean(args[0]);
            ConfigMain.VineGrowthEnabledProperty.set(ConfigMain.VineGrowthEnabled);
            if (ConfigMain.config.hasChanged()) {
                ConfigMain.config.save();
            }
            Set names = Block.field_149771_c.func_148742_b();
            for (ResourceLocation name : names) {
                Block block = (Block)Block.field_149771_c.func_82594_a((Object)name);
                if (!(block instanceof BlockVine)) continue;
                block.func_149675_a(ConfigMain.VineGrowthEnabled);
            }
            ColorUtil.sendResult(sender, "VineGrowth is now \u00a7c" + ConfigMain.VineGrowthEnabled);
        }
    }

    @CommandKamkeelBase.SubCommand(desc="Disable/Enable the ice melting", usage="[true/false]", permission=4)
    public void icemelts(ICommandSender sender, String[] args) {
        if (args.length == 0) {
            ColorUtil.sendResult(sender, "IceMelts: \u00a7c" + ConfigMain.IceMeltsEnabled);
        } else {
            ConfigMain.IceMeltsEnabled = Boolean.parseBoolean(args[0]);
            ConfigMain.IceMeltsEnabledProperty.set(ConfigMain.IceMeltsEnabled);
            if (ConfigMain.config.hasChanged()) {
                ConfigMain.config.save();
            }
            Set names = Block.field_149771_c.func_148742_b();
            for (ResourceLocation name : names) {
                Block block = (Block)Block.field_149771_c.func_82594_a((Object)name);
                if (!(block instanceof BlockIce)) continue;
                block.func_149675_a(ConfigMain.IceMeltsEnabled);
            }
            ColorUtil.sendResult(sender, "IceMelts is now \u00a7c" + ConfigMain.IceMeltsEnabled);
        }
    }

    @CommandKamkeelBase.SubCommand(desc="Disable/Enable guns shooting", usage="[true/false]", permission=4)
    public void guns(ICommandSender sender, String[] args) {
        if (args.length == 0) {
            ColorUtil.sendResult(sender, "GunsEnabled: \u00a7c" + ConfigItem.GunsEnabled);
        } else {
            ConfigItem.GunsEnabled = Boolean.parseBoolean(args[0]);
            ConfigItem.GunsEnabledProperty.set(ConfigItem.GunsEnabled);
            if (ConfigMain.config.hasChanged()) {
                ConfigMain.config.save();
            }
            ColorUtil.sendResult(sender, "GunsEnabled is now \u00a7c" + ConfigItem.GunsEnabled);
        }
    }

    @CommandKamkeelBase.SubCommand(desc="Freezes/Unfreezes npcs", usage="[true/false]", permission=4)
    public void freezenpcs(ICommandSender sender, String[] args) {
        if (args.length == 0) {
            ColorUtil.sendResult(sender, "Frozen NPCs: \u00a7c" + CustomNpcs.FreezeNPCs);
        } else {
            CustomNpcs.FreezeNPCs = Boolean.parseBoolean(args[0]);
            ColorUtil.sendResult(sender, "FrozenNPCs is now \u00a7c" + CustomNpcs.FreezeNPCs);
        }
    }

    @CommandKamkeelBase.SubCommand(desc="Set number of active chunkloaders", usage="<number>", permission=4)
    public void chunkloaders(ICommandSender sender, String[] args) throws CommandException {
        if (args.length == 0) {
            ColorUtil.sendResult(sender, "ChunkLoaders: \u00a7c" + ChunkController.Instance.size() + "\u00a77/\u00a7c" + ConfigMain.ChunkLoaders);
        } else {
            int size;
            try {
                ConfigMain.ChunkLoaders = Integer.parseInt(args[0]);
            }
            catch (NumberFormatException ex) {
                ColorUtil.sendError(sender, "Did not get a number: " + args[0]);
                return;
            }
            ConfigMain.ChunkLoadersProperty.set(ConfigMain.ChunkLoaders);
            if (ConfigMain.config.hasChanged()) {
                ConfigMain.config.save();
            }
            if ((size = ChunkController.Instance.size()) > ConfigMain.ChunkLoaders) {
                ChunkController.Instance.unload(size - ConfigMain.ChunkLoaders);
                ColorUtil.sendResult(sender, size - ConfigMain.ChunkLoaders + " chunksloaders unloaded");
            }
            ColorUtil.sendResult(sender, "ChunkLoaders: \u00a7c" + ChunkController.Instance.size() + "\u00a77/\u00a7c" + ConfigMain.ChunkLoaders);
        }
    }

    @CommandKamkeelBase.SubCommand(desc="Generate PlayerData to JSON/DAT", usage="[fileType] [convert]", permission=4)
    public void playerdata(ICommandSender sender, String[] args) throws CommandException {
        String fileType = ConfigMain.DatFormat ? "DAT" : "JSON";
        if (args.length == 0) {
            ColorUtil.sendResult(sender, "PlayerData Filetype: \u00a7c" + fileType);
        } else if (args.length == 1) {
            ColorUtil.sendResult(sender, "Please write the word 'convert' at the end to confirm.  \u00a7c<dat/json> convert");
        } else {
            if (args.length != 2) {
                ColorUtil.sendError(sender, "Two many arguments");
                return;
            }
            String formatType = args[0].toLowerCase();
            if (!formatType.equals("dat") && !formatType.equals("json")) {
                ColorUtil.sendError(sender, "Invalid Format Type - Please use dat or json");
                return;
            }
            String convert = args[1].toLowerCase();
            if (!convert.equals("convert")) {
                ColorUtil.sendError(sender, "Please enter the word 'convert' at the end");
                return;
            }
            boolean convertToDat = formatType.equals("dat");
            EntityPlayerMP send = null;
            if (sender instanceof EntityPlayerMP) {
                send = (EntityPlayerMP)sender;
            }
            ColorUtil.sendResult(sender, "Started Conversion Process for PlayerData");
            PlayerDataController.Instance.convertPlayerFiles(send, convertToDat);
        }
    }

    @CommandKamkeelBase.SubCommand(desc="Generate Market to JSON/DAT", usage="[fileType] [convert]", permission=4)
    public void market(ICommandSender sender, String[] args) throws CommandException {
        String fileType = ConfigMain.MarketDatFormat ? "DAT" : "JSON";
        if (args.length == 0) {
            ColorUtil.sendResult(sender, "Market Filetype: \u00a7c" + fileType);
        } else if (args.length == 1) {
            ColorUtil.sendResult(sender, "Please write the word 'convert' at the end to confirm.  \u00a7c<dat/json> convert");
        } else {
            if (args.length != 2) {
                ColorUtil.sendError(sender, "Two many arguments");
                return;
            }
            String formatType = args[0].toLowerCase();
            if (!formatType.equals("dat") && !formatType.equals("json")) {
                ColorUtil.sendError(sender, "Invalid Format Type - Please use dat or json");
                return;
            }
            String convert = args[1].toLowerCase();
            if (!convert.equals("convert")) {
                ColorUtil.sendError(sender, "Please enter the word 'convert' at the end");
                return;
            }
            boolean convertToDat = formatType.equals("dat");
            EntityPlayerMP send = null;
            if (sender instanceof EntityPlayerMP) {
                send = (EntityPlayerMP)sender;
            }
            ColorUtil.sendResult(sender, "Started Conversion Process for Market");
            Market.convertMarketFiles(send, convertToDat);
        }
    }

    @CommandKamkeelBase.SubCommand(desc="Get/Set font", usage="[type] [size]", permission=4)
    public void font(ICommandSender sender, String[] args) {
        if (!(sender instanceof EntityPlayerMP)) {
            return;
        }
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
        PacketHandler.Instance.sendToPlayer(new ConfigCommandPacket(EnumConfigOperation.FONT, font.trim(), size), (EntityPlayerMP)sender);
    }
}

