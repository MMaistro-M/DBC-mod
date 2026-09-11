/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.command.server.CommandBlockLogic
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.entity.projectile.EntityArrow
 *  net.minecraft.entity.projectile.EntityThrowable
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.tileentity.MobSpawnerBaseLogic
 *  net.minecraft.tileentity.MobSpawnerBaseLogic$WeightedRandomMinecart
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.tileentity.TileEntityCommandBlock
 *  net.minecraft.tileentity.TileEntityMobSpawner
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package noppes.npcs;

import io.netty.buffer.ByteBuf;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import kamkeel.npcs.controllers.AbilityController;
import kamkeel.npcs.controllers.SyncController;
import kamkeel.npcs.controllers.data.ability.Ability;
import kamkeel.npcs.network.PacketHandler;
import kamkeel.npcs.network.packets.data.ParticlePacket;
import kamkeel.npcs.network.packets.data.PlayerDataInfoPacket;
import kamkeel.npcs.network.packets.data.gui.GuiClosePacket;
import kamkeel.npcs.network.packets.data.gui.GuiErrorPacket;
import kamkeel.npcs.network.packets.data.gui.GuiOpenPacket;
import kamkeel.npcs.network.packets.data.gui.GuiTeleporterPacket;
import kamkeel.npcs.network.packets.data.large.GuiDataPacket;
import kamkeel.npcs.network.packets.data.large.ScrollDataPacket;
import kamkeel.npcs.network.packets.data.large.ScrollListPacket;
import kamkeel.npcs.network.packets.data.npc.DeleteNpcPacket;
import kamkeel.npcs.network.packets.data.npc.DialogPacket;
import kamkeel.npcs.network.packets.data.npc.EditNpcPacket;
import kamkeel.npcs.network.packets.data.npc.RolePacket;
import kamkeel.npcs.network.packets.data.script.ScriptedParticlePacket;
import kamkeel.npcs.util.ByteBufUtils;
import net.minecraft.command.server.CommandBlockLogic;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.MobSpawnerBaseLogic;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityCommandBlock;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import noppes.npcs.CustomItems;
import noppes.npcs.CustomNpcs;
import noppes.npcs.EventHooks;
import noppes.npcs.NBTTags;
import noppes.npcs.NoppesUtilPlayer;
import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.config.ConfigMain;
import noppes.npcs.constants.EnumGuiType;
import noppes.npcs.constants.EnumPlayerData;
import noppes.npcs.constants.EnumRoleType;
import noppes.npcs.constants.EnumScrollData;
import noppes.npcs.containers.ContainerManageBanks;
import noppes.npcs.containers.ContainerManageRecipes;
import noppes.npcs.controllers.AnimationController;
import noppes.npcs.controllers.BankController;
import noppes.npcs.controllers.CustomEffectController;
import noppes.npcs.controllers.DialogController;
import noppes.npcs.controllers.FactionController;
import noppes.npcs.controllers.LinkedItemController;
import noppes.npcs.controllers.MagicController;
import noppes.npcs.controllers.PlayerDataController;
import noppes.npcs.controllers.PlayerQuestController;
import noppes.npcs.controllers.QuestController;
import noppes.npcs.controllers.RecipeController;
import noppes.npcs.controllers.ServerCloneController;
import noppes.npcs.controllers.TagController;
import noppes.npcs.controllers.TransportController;
import noppes.npcs.controllers.data.Animation;
import noppes.npcs.controllers.data.Bank;
import noppes.npcs.controllers.data.CustomEffect;
import noppes.npcs.controllers.data.Dialog;
import noppes.npcs.controllers.data.DialogCategory;
import noppes.npcs.controllers.data.DialogOption;
import noppes.npcs.controllers.data.Faction;
import noppes.npcs.controllers.data.LinkedItem;
import noppes.npcs.controllers.data.Magic;
import noppes.npcs.controllers.data.MagicCycle;
import noppes.npcs.controllers.data.MagicData;
import noppes.npcs.controllers.data.PlayerBankData;
import noppes.npcs.controllers.data.PlayerData;
import noppes.npcs.controllers.data.PlayerDialogData;
import noppes.npcs.controllers.data.PlayerFactionData;
import noppes.npcs.controllers.data.PlayerQuestData;
import noppes.npcs.controllers.data.PlayerTransportData;
import noppes.npcs.controllers.data.Quest;
import noppes.npcs.controllers.data.QuestCategory;
import noppes.npcs.controllers.data.QuestData;
import noppes.npcs.controllers.data.RecipeAnvil;
import noppes.npcs.controllers.data.RecipeCarpentry;
import noppes.npcs.controllers.data.Tag;
import noppes.npcs.controllers.data.TransportCategory;
import noppes.npcs.controllers.data.TransportLocation;
import noppes.npcs.entity.EntityDialogNpc;
import noppes.npcs.entity.EntityNPCInterface;
import noppes.npcs.roles.RoleTransporter;
import noppes.npcs.scripted.NpcAPI;
import noppes.npcs.scripted.ScriptSound;
import noppes.npcs.scripted.event.player.DialogEvent;

public class NoppesUtilServer {
    private static HashMap<String, Quest> editingQuests = new HashMap();

    public static IPlayer getIPlayer(EntityPlayer p) {
        return (IPlayer)NpcAPI.Instance().getIEntity((Entity)p);
    }

    public static UUID getUUID(Entity entity) {
        return entity instanceof EntityPlayer ? ((EntityPlayer)entity).func_146103_bH().getId() : entity.func_110124_au();
    }

    public static boolean isScriptableItem(Item item) {
        return item == CustomItems.scripted_item || item == CustomItems.linked_item;
    }

    public static void setEditingNpc(EntityPlayer player, EntityNPCInterface npc) {
        PlayerData data = PlayerData.get(player);
        data.editingNpc = npc;
        if (npc != null) {
            PacketHandler.Instance.sendToPlayer(new EditNpcPacket(npc.func_145782_y()), (EntityPlayerMP)player);
        }
    }

    public static EntityNPCInterface getEditingNpc(EntityPlayer player) {
        PlayerData data = PlayerData.get(player);
        return data.editingNpc;
    }

    public static void setEditingQuest(EntityPlayer player, Quest quest) {
        editingQuests.put(player.func_70005_c_(), quest);
    }

    public static Quest getEditingQuest(EntityPlayer player) {
        return editingQuests.get(player.func_70005_c_());
    }

    public static void sendRoleData(EntityPlayer player, EntityNPCInterface npc) {
        if (npc.advanced.role == EnumRoleType.None) {
            return;
        }
        NBTTagCompound comp = new NBTTagCompound();
        npc.roleInterface.writeToNBT(comp);
        comp.func_74768_a("EntityId", npc.func_145782_y());
        comp.func_74768_a("Role", npc.advanced.role.ordinal());
        PacketHandler.Instance.sendToPlayer(new RolePacket(comp), (EntityPlayerMP)player);
    }

    public static void sendFactionDataAll(EntityPlayerMP player) {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for (Faction faction : FactionController.getInstance().factions.values()) {
            map.put(faction.name, faction.id);
        }
        ScrollDataPacket.sendScrollData(player, map, EnumScrollData.OPTIONAL);
    }

    public static void sendAnimationDataAll(EntityPlayerMP player) {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for (Animation animation : AnimationController.getInstance().animations.values()) {
            map.put(animation.name, animation.id);
        }
        ScrollDataPacket.sendScrollData(player, map, EnumScrollData.ANIMATIONS);
    }

    public static void sendBuiltInAnimationData(EntityPlayerMP player) {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for (String name : AnimationController.getInstance().builtInAnimations.keySet()) {
            map.put(AnimationController.getInstance().builtInAnimations.get(name).getName(), -1);
        }
        ScrollDataPacket.sendScrollData(player, map, EnumScrollData.BUILTIN_ANIMATIONS);
    }

    public static void sendTagDataAll(EntityPlayerMP player) {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for (Tag tag : TagController.getInstance().tags.values()) {
            map.put(tag.name, tag.id);
        }
        ScrollDataPacket.sendScrollData(player, map, EnumScrollData.OPTIONAL);
    }

    public static void sendBankDataAll(EntityPlayerMP player) {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for (Bank bank : BankController.getInstance().banks.values()) {
            map.put(bank.name, bank.id);
        }
        ScrollDataPacket.sendScrollData(player, map, EnumScrollData.OPTIONAL);
    }

    public static NBTTagCompound writeItem(ItemStack item, NBTTagCompound nbt) {
        String resourcelocation = Item.field_150901_e.func_148750_c((Object)item.func_77973_b());
        nbt.func_74778_a("id", resourcelocation == null ? "minecraft:air" : resourcelocation.toString());
        nbt.func_74774_a("Count", (byte)item.field_77994_a);
        nbt.func_74777_a("Damage", (short)item.func_77960_j());
        if (item.field_77990_d != null) {
            nbt.func_74782_a("tag", (NBTBase)item.field_77990_d);
        }
        return nbt;
    }

    public static ItemStack readItem(NBTTagCompound nbt) {
        Item item = nbt.func_150297_b("id", 8) ? NoppesUtilServer.getByNameOrId(nbt.func_74779_i("id")) : Item.func_150899_d((int)nbt.func_74765_d("id"));
        if (item == null) {
            return null;
        }
        ItemStack itemstack = new ItemStack(item);
        itemstack.field_77994_a = nbt.func_74771_c("Count");
        itemstack.func_77964_b((int)nbt.func_74765_d("Damage"));
        if (itemstack.func_77960_j() < 0) {
            itemstack.func_77964_b(0);
        }
        if (nbt.func_150297_b("tag", 10)) {
            itemstack.field_77990_d = nbt.func_74775_l("tag");
        }
        return itemstack;
    }

    public static Item getByNameOrId(String id) {
        Item item = (Item)Item.field_150901_e.func_82594_a(id);
        if (item == null) {
            try {
                return Item.func_150899_d((int)Integer.parseInt(id));
            }
            catch (NumberFormatException numberFormatException) {
                // empty catch block
            }
        }
        return item;
    }

    public static void openDialog(EntityPlayer player, EntityNPCInterface npc, Dialog dia, int optionId) {
        Dialog dialog = dia.copy(player);
        PlayerData playerdata = PlayerData.get(player);
        if (!npc.isRemote()) {
            if (EventHooks.onNPCDialog(npc, player, dialog.id, optionId, dialog)) {
                return;
            }
            if (EventHooks.onDialogOpen(player, new DialogEvent.DialogOpen((IPlayer)NpcAPI.Instance().getIEntity((Entity)player), dialog.id, optionId, dialog))) {
                return;
            }
        }
        if (npc instanceof EntityDialogNpc || dia.id < 0) {
            dialog.hideNPC = true;
            PacketHandler.Instance.sendToPlayer(new DialogPacket(npc.func_70005_c_(), -1, dialog.writeToNBT(new NBTTagCompound())), (EntityPlayerMP)player);
        } else {
            PacketHandler.Instance.sendToPlayer(new DialogPacket("Real", npc.func_145782_y(), dialog.writeToNBT(new NBTTagCompound())), (EntityPlayerMP)player);
        }
        dia.factionOptions.addPoints(player);
        if (dialog.hasQuest()) {
            PlayerQuestController.addActiveQuest(new QuestData(dialog.getQuest()), player);
        }
        if (!dialog.command.isEmpty()) {
            NoppesUtilServer.runCommand(player, npc.func_70005_c_(), dialog.command);
        }
        if (dialog.mail.isValid()) {
            PlayerDataController.Instance.addPlayerMessage(player.func_70005_c_(), dialog.mail);
        }
        PlayerDialogData data = PlayerData.get((EntityPlayer)player).dialogData;
        if (!data.dialogsRead.contains(dialog.id) && dialog.id >= 0) {
            data.dialogsRead.add(dialog.id);
            playerdata.updateClient = true;
        }
        NoppesUtilServer.setEditingNpc(player, npc);
    }

    public static void runCommand(EntityPlayer player, String name, String command) {
        NoppesUtilServer.runCommand((EntityLivingBase)player, name, command, player);
    }

    public static void runCommand(EntityLivingBase executer, String name, String command, EntityPlayer player) {
        if (player != null) {
            command = command.replace("@dp", player.func_70005_c_());
        }
        command = command.replace("@npc", name);
        command = command.trim();
        String[] commands = command.split("@x");
        TileEntityCommandBlock tile = new TileEntityCommandBlock();
        tile.func_145834_a(executer.field_70170_p);
        tile.field_145851_c = MathHelper.func_76128_c((double)executer.field_70165_t);
        tile.field_145848_d = MathHelper.func_76128_c((double)executer.field_70163_u);
        tile.field_145849_e = MathHelper.func_76128_c((double)executer.field_70161_v);
        CommandBlockLogic logic = tile.func_145993_a();
        for (String cmd : commands) {
            logic.func_145752_a(cmd.trim());
            logic.func_145754_b("@" + name);
            logic.func_145755_a(executer.field_70170_p);
        }
    }

    public static void runCommand(World world, String name, String command) {
        TileEntityCommandBlock tile = new TileEntityCommandBlock();
        tile.func_145834_a(world.field_73011_w.field_76579_a);
        tile.field_145851_c = 0;
        tile.field_145848_d = 0;
        tile.field_145849_e = 0;
        command = command.trim();
        String[] commands = command.split("@x");
        CommandBlockLogic logic = tile.func_145993_a();
        for (String cmd : commands) {
            logic.func_145752_a(cmd.trim());
            logic.func_145754_b("@" + name);
            logic.func_145755_a(world);
        }
    }

    public static void consumeItemStack(int i, EntityPlayer player) {
        ItemStack item = player.field_71071_by.func_70448_g();
        if (player.field_71075_bZ.field_75098_d || item == null) {
            return;
        }
        --item.field_77994_a;
        if (item.field_77994_a <= 0) {
            player.func_71028_bD();
        }
    }

    public static void sendOpenGui(EntityPlayer player, EnumGuiType gui, EntityNPCInterface npc) {
        NoppesUtilServer.sendOpenGui(player, gui, npc, 0, 0, 0);
    }

    public static void sendOpenGuiNoDelay(EntityPlayer player, EnumGuiType gui, EntityNPCInterface npc) {
        NoppesUtilServer.sendOpenGuiNoDelay(player, gui, npc, 0, 0, 0);
    }

    public static void sendOpenGui(EntityPlayer player, EnumGuiType gui, EntityNPCInterface npc, int i, int j, int k) {
        if (!(player instanceof EntityPlayerMP)) {
            return;
        }
        NoppesUtilServer.setEditingNpc(player, npc);
        NoppesUtilServer.sendExtraData(player, npc, gui, i, j, k);
        if (CustomNpcs.proxy.getServerGuiElement(gui.ordinal(), player, player.field_70170_p, i, j, k) != null) {
            player.openGui((Object)CustomNpcs.instance, gui.ordinal(), player.field_70170_p, i, j, k);
            return;
        }
        GuiOpenPacket.openGUI((EntityPlayerMP)player, gui, i, j, k);
        ArrayList<String> list = NoppesUtilServer.getScrollData(player, gui, npc);
        if (list == null || list.isEmpty()) {
            return;
        }
        ScrollListPacket.sendList((EntityPlayerMP)player, list, EnumScrollData.OPTIONAL);
    }

    public static void sendOpenGuiNoDelay(EntityPlayer player, EnumGuiType gui, EntityNPCInterface npc, int i, int j, int k) {
        if (!(player instanceof EntityPlayerMP)) {
            return;
        }
        NoppesUtilServer.setEditingNpc(player, npc);
        NoppesUtilServer.sendExtraData(player, npc, gui, i, j, k);
        if (CustomNpcs.proxy.getServerGuiElement(gui.ordinal(), player, player.field_70170_p, i, j, k) != null) {
            player.openGui((Object)CustomNpcs.instance, gui.ordinal(), player.field_70170_p, i, j, k);
            return;
        }
        GuiOpenPacket.openGUI((EntityPlayerMP)player, gui, i, j, k);
        ArrayList<String> list = NoppesUtilServer.getScrollData(player, gui, npc);
        if (list == null || list.isEmpty()) {
            return;
        }
        ScrollListPacket.sendList((EntityPlayerMP)player, list, EnumScrollData.OPTIONAL);
    }

    private static void sendExtraData(EntityPlayer player, EntityNPCInterface npc, EnumGuiType gui, int i, int j, int k) {
        if (gui == EnumGuiType.PlayerFollower || gui == EnumGuiType.PlayerFollowerHire || gui == EnumGuiType.PlayerTrader || gui == EnumGuiType.PlayerTransporter) {
            NoppesUtilServer.sendRoleData(player, npc);
        }
    }

    private static ArrayList<String> getScrollData(EntityPlayer player, EnumGuiType gui, EntityNPCInterface npc) {
        if (gui == EnumGuiType.PlayerTransporter) {
            RoleTransporter role = (RoleTransporter)npc.roleInterface;
            ArrayList<String> list = new ArrayList<String>();
            TransportLocation location = role.getLocation();
            String name = role.getLocation().name;
            for (TransportLocation loc : location.category.getDefaultLocations()) {
                if (list.contains(loc.name)) continue;
                list.add(loc.name);
            }
            PlayerTransportData playerdata = PlayerData.get((EntityPlayer)player).transportData;
            for (int i : playerdata.transports) {
                TransportLocation loc = TransportController.getInstance().getTransport(i);
                if (loc == null || !location.category.locations.containsKey(loc.id) || list.contains(loc.name)) continue;
                list.add(loc.name);
            }
            list.remove(name);
            return list;
        }
        return null;
    }

    public static void spawnParticle(Entity entity, String particle, int dimension) {
        PacketHandler.Instance.sendTracking(new ParticlePacket(entity.field_70165_t, entity.field_70163_u, entity.field_70161_v, entity.field_70131_O, entity.field_70130_N, entity.field_70129_M, particle), entity);
    }

    public static void spawnScriptedParticle(NBTTagCompound compound, int dimensionId) {
        PacketHandler.Instance.sendToDimension(new ScriptedParticlePacket(compound), dimensionId);
    }

    public static void playSound(int id, ScriptSound sound) {
        List list = MinecraftServer.func_71276_C().func_71203_ab().field_72404_b;
        for (EntityPlayer player : list) {
            NoppesUtilPlayer.playSoundTo((EntityPlayerMP)player, id, sound);
        }
    }

    public static void playSound(ScriptSound sound) {
        List list = MinecraftServer.func_71276_C().func_71203_ab().field_72404_b;
        for (EntityPlayer player : list) {
            NoppesUtilPlayer.playSoundTo((EntityPlayerMP)player, sound);
        }
    }

    public static void stopSound(int id) {
        List list = MinecraftServer.func_71276_C().func_71203_ab().field_72404_b;
        for (EntityPlayer player : list) {
            NoppesUtilPlayer.stopSoundFor((EntityPlayerMP)player, id);
        }
    }

    public static void pauseSounds() {
        List list = MinecraftServer.func_71276_C().func_71203_ab().field_72404_b;
        for (EntityPlayer player : list) {
            NoppesUtilPlayer.pauseSoundsFor((EntityPlayerMP)player);
        }
    }

    public static void continueSounds() {
        List list = MinecraftServer.func_71276_C().func_71203_ab().field_72404_b;
        for (EntityPlayer player : list) {
            NoppesUtilPlayer.continueSoundsFor((EntityPlayerMP)player);
        }
    }

    public static void stopSounds() {
        List list = MinecraftServer.func_71276_C().func_71203_ab().field_72404_b;
        for (EntityPlayer player : list) {
            NoppesUtilPlayer.stopSoundsFor((EntityPlayerMP)player);
        }
    }

    public static void deleteNpc(EntityNPCInterface npc, EntityPlayer player) {
        PacketHandler.Instance.sendTracking(new DeleteNpcPacket(npc.func_145782_y()), (Entity)npc);
    }

    public static void createMobSpawner(int x, int y, int z, NBTTagCompound comp, EntityPlayer player) {
        MobSpawnerBaseLogic logic;
        comp.func_82580_o("Pos");
        ServerCloneController.Instance.cleanTags(comp);
        if (comp.func_74779_i("id").equalsIgnoreCase("entityhorse")) {
            player.func_145747_a((IChatComponent)new ChatComponentTranslation("Currently you cant create horse spawner, its a minecraft bug", new Object[0]));
            return;
        }
        player.field_70170_p.func_147449_b(x, y, z, Blocks.field_150474_ac);
        TileEntityMobSpawner tile = (TileEntityMobSpawner)player.field_70170_p.func_147438_o(x, y, z);
        MobSpawnerBaseLogic mobSpawnerBaseLogic = logic = tile.func_145881_a();
        mobSpawnerBaseLogic.getClass();
        logic.func_98277_a(new MobSpawnerBaseLogic.WeightedRandomMinecart(mobSpawnerBaseLogic, comp, comp.func_74779_i("id")));
    }

    public static void sendQuestCategoryData(EntityPlayerMP player) {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for (QuestCategory category : QuestController.Instance.categories.values()) {
            map.put(category.title, category.id);
        }
        ScrollDataPacket.sendScrollData(player, map, EnumScrollData.OPTIONAL);
    }

    public static void sendPlayerData(EnumPlayerData type, EntityPlayerMP player, String name) throws IOException {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        if (type == EnumPlayerData.Players) {
            for (String username : PlayerDataController.Instance.nameUUIDs.keySet()) {
                map.put(username, 0);
            }
            for (String username : MinecraftServer.func_71276_C().func_71203_ab().func_72369_d()) {
                map.put(username, 0);
            }
        } else {
            PlayerData playerdata = PlayerDataController.Instance.getDataFromUsername(name);
            if (type == EnumPlayerData.Dialog) {
                PlayerDialogData data = playerdata.dialogData;
                for (int questId : data.dialogsRead) {
                    Dialog dialog = DialogController.Instance.dialogs.get(questId);
                    if (dialog == null) continue;
                    map.put(dialog.category.title + ": " + dialog.title, questId);
                }
            } else if (type == EnumPlayerData.Quest) {
                Quest quest;
                PlayerQuestData data = playerdata.questData;
                for (int questId : data.activeQuests.keySet()) {
                    quest = QuestController.Instance.quests.get(questId);
                    if (quest == null) continue;
                    map.put(quest.category.title + ": " + quest.title + " (Active)", questId);
                }
                for (int questId : data.finishedQuests.keySet()) {
                    quest = QuestController.Instance.quests.get(questId);
                    if (quest == null) continue;
                    map.put(quest.category.title + ": " + quest.title + " (Finished)", questId);
                }
            } else if (type == EnumPlayerData.Transport) {
                PlayerTransportData data = playerdata.transportData;
                for (int questId : data.transports) {
                    TransportLocation location = TransportController.getInstance().getTransport(questId);
                    if (location == null) continue;
                    map.put(location.category.title + ": " + location.name, questId);
                }
            } else if (type == EnumPlayerData.Bank) {
                PlayerBankData data = playerdata.bankData;
                for (int bankId : data.banks.keySet()) {
                    Bank bank = BankController.getInstance().banks.get(bankId);
                    if (bank == null) continue;
                    map.put(bank.name, bankId);
                }
            } else if (type == EnumPlayerData.Factions) {
                PlayerFactionData data = playerdata.factionData;
                for (int factionId : data.factionData.keySet()) {
                    Faction faction = FactionController.getInstance().factions.get(factionId);
                    if (faction == null) continue;
                    map.put(faction.name + "(" + data.getFactionPoints(factionId) + ")", factionId);
                }
            }
        }
        ScrollDataPacket.sendScrollData(player, map, EnumScrollData.OPTIONAL);
    }

    public static void removePlayerData(ByteBuf buffer, EntityPlayerMP player) throws IOException {
        Object data;
        int id = buffer.readInt();
        if (EnumPlayerData.values().length <= id) {
            return;
        }
        String name = ByteBufUtils.readString(buffer);
        EnumPlayerData type = EnumPlayerData.values()[id];
        EntityPlayerMP pl = MinecraftServer.func_71276_C().func_71203_ab().func_152612_a(name);
        PlayerData playerdata = null;
        playerdata = pl == null ? PlayerDataController.Instance.getDataFromUsername(name) : PlayerData.get((EntityPlayer)pl);
        if (type == EnumPlayerData.Players) {
            File file;
            String fileType = ".json";
            if (ConfigMain.DatFormat) {
                fileType = ".dat";
            }
            if ((file = new File(PlayerDataController.Instance.getSaveDir(), playerdata.uuid + fileType)).exists()) {
                file.delete();
            }
            if (pl != null) {
                PlayerDataController.Instance.removePlayerDataCache(pl.func_110124_au().toString());
                PlayerDataController.Instance.nameUUIDs.remove(name);
                PlayerDataController.Instance.savePlayerDataMap();
                int currentSlot = playerdata.profileSlot;
                playerdata.setNBT(new NBTTagCompound());
                playerdata.profileSlot = currentSlot;
                NoppesUtilServer.sendPlayerData(type, player, name);
                playerdata.save();
                return;
            }
        }
        if (type == EnumPlayerData.Quest) {
            data = playerdata.questData;
            int questId = buffer.readInt();
            ((PlayerQuestData)data).activeQuests.remove(questId);
            ((PlayerQuestData)data).finishedQuests.remove(questId);
            playerdata.save();
        }
        if (type == EnumPlayerData.Dialog) {
            data = playerdata.dialogData;
            ((PlayerDialogData)data).dialogsRead.remove(buffer.readInt());
            playerdata.save();
        }
        if (type == EnumPlayerData.Transport) {
            data = playerdata.transportData;
            ((PlayerTransportData)data).transports.remove(buffer.readInt());
            playerdata.save();
        }
        if (type == EnumPlayerData.Bank) {
            data = playerdata.bankData;
            ((PlayerBankData)data).banks.remove(buffer.readInt());
            playerdata.save();
        }
        if (type == EnumPlayerData.Factions) {
            data = playerdata.factionData;
            ((PlayerFactionData)data).factionData.remove(buffer.readInt());
            playerdata.save();
        }
        if (type == EnumPlayerData.Magic) {
            data = playerdata.magicData;
            ((MagicData)data).removeMagic(buffer.readInt());
            playerdata.save();
        }
        if (pl != null) {
            SyncController.syncPlayer(pl);
        }
        NoppesUtilServer.sendPlayerData(type, player, name);
    }

    public static void regenPlayerData(EntityPlayerMP player) throws IOException {
        PlayerDataController.Instance.generatePlayerMap(player);
    }

    public static void sendRecipeData(EntityPlayerMP player, int size) {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        if (size == 3) {
            for (RecipeCarpentry recipe : RecipeController.Instance.globalRecipes.values()) {
                map.put(recipe.name, recipe.id);
            }
        } else if (size == 4) {
            for (RecipeCarpentry recipe : RecipeController.Instance.carpentryRecipes.values()) {
                map.put(recipe.name, recipe.id);
            }
        } else {
            for (RecipeAnvil recipe : RecipeController.Instance.anvilRecipes.values()) {
                map.put(recipe.name, recipe.id);
            }
        }
        ScrollDataPacket.sendScrollData(player, map, EnumScrollData.OPTIONAL);
    }

    public static void sendDialogData(EntityPlayerMP player, DialogCategory category) {
        if (category == null) {
            return;
        }
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for (Dialog dialog : category.dialogs.values()) {
            map.put(dialog.title, dialog.id);
        }
        ScrollDataPacket.sendScrollData(player, map, EnumScrollData.OPTIONAL);
    }

    public static void sendDialogGroup(EntityPlayerMP player, DialogCategory category) {
        if (category == null) {
            return;
        }
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for (Dialog dialog : category.dialogs.values()) {
            map.put(dialog.title, dialog.id);
        }
        ScrollDataPacket.sendScrollData(player, map, EnumScrollData.DIALOG_GROUP);
    }

    public static void sendQuestData(EntityPlayerMP player, QuestCategory category) {
        if (category == null) {
            return;
        }
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for (Quest quest : category.quests.values()) {
            map.put(quest.title, quest.id);
        }
        ScrollDataPacket.sendScrollData(player, map, EnumScrollData.OPTIONAL);
    }

    public static void sendQuestGroup(EntityPlayerMP player, QuestCategory category) {
        if (category == null) {
            return;
        }
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for (Quest quest : category.quests.values()) {
            map.put(quest.title, quest.id);
        }
        ScrollDataPacket.sendScrollData(player, map, EnumScrollData.QUEST_GROUP);
    }

    public static void sendCustomEffectDataAll(EntityPlayerMP player) {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for (CustomEffect effect : CustomEffectController.getInstance().getCustomEffects().values()) {
            map.put(effect.name, effect.id);
        }
        ScrollDataPacket.sendScrollData(player, map, EnumScrollData.OPTIONAL);
    }

    public static void sendLinkedItemDataAll(EntityPlayerMP player) {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for (LinkedItem linkedItem : LinkedItemController.getInstance().linkedItems.values()) {
            map.put(linkedItem.name, linkedItem.id);
        }
        ScrollDataPacket.sendScrollData(player, map, EnumScrollData.OPTIONAL);
    }

    public static void sendTransportCategoryData(EntityPlayerMP player) {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for (TransportCategory category : TransportController.getInstance().categories.values()) {
            map.put(category.title, category.id);
        }
        ScrollDataPacket.sendScrollData(player, map, EnumScrollData.OPTIONAL);
    }

    public static void sendTransportData(EntityPlayerMP player, int categoryid) {
        TransportCategory category = TransportController.getInstance().categories.get(categoryid);
        if (category == null) {
            return;
        }
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for (TransportLocation transport : category.locations.values()) {
            map.put(transport.name, transport.id);
        }
        ScrollDataPacket.sendScrollData(player, map, EnumScrollData.OPTIONAL);
    }

    public static void sendNpcDialogs(EntityPlayer player) {
        EntityNPCInterface npc = NoppesUtilServer.getEditingNpc(player);
        if (npc == null) {
            return;
        }
        for (int pos : npc.dialogs.keySet()) {
            DialogOption option = npc.dialogs.get(pos);
            if (option == null || !option.hasDialog()) continue;
            NBTTagCompound compound = option.writeNBT();
            compound.func_74768_a("Position", pos);
            GuiDataPacket.sendGuiData((EntityPlayerMP)player, compound);
        }
    }

    public static void sendMagicInfo(EntityPlayerMP player, boolean cycles) {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        if (cycles) {
            for (MagicCycle magicCycle : MagicController.getInstance().cycles.values()) {
                map.put(magicCycle.name, magicCycle.id);
            }
            ScrollDataPacket.sendScrollData(player, map, EnumScrollData.MAGIC_CYCLES);
        } else {
            for (Magic magic : MagicController.getInstance().magics.values()) {
                map.put(magic.name, magic.id);
            }
            ScrollDataPacket.sendScrollData(player, map, EnumScrollData.MAGIC);
        }
    }

    public static void sendAbilityTypesInfo(EntityPlayerMP player) {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        AbilityController controller = AbilityController.Instance;
        if (controller != null) {
            int index = 0;
            for (String typeId : controller.getTypes()) {
                if (!controller.isAllowedByNPC(typeId) && !controller.isAllowedByBoth(typeId)) continue;
                map.put(typeId, index++);
            }
        }
        ScrollDataPacket.sendScrollData(player, map, EnumScrollData.ABILITY_TYPES);
    }

    public static void sendBuiltInAbilitiesData(EntityPlayerMP player) {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        AbilityController controller = AbilityController.Instance;
        if (controller != null) {
            for (String name : controller.getAbilityNames()) {
                Ability ability = controller.getAbility(name);
                if (ability == null) continue;
                map.put(ability.getName(), ability.getAllowedBy().ordinal());
            }
        }
        ScrollDataPacket.sendScrollData(player, map, EnumScrollData.BUILTIN_ABILITIES);
    }

    public static void sendCustomAbilitiesData(EntityPlayerMP player) {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        AbilityController controller = AbilityController.Instance;
        if (controller != null) {
            for (String name : controller.getCustomAbilityNames()) {
                Ability ability = controller.getCustomAbility(name);
                if (ability == null) continue;
                map.put(name, ability.getAllowedBy().ordinal());
            }
        }
        ScrollDataPacket.sendScrollData(player, map, EnumScrollData.CUSTOM_ABILITIES);
    }

    public static void sendChainedAbilitiesData(EntityPlayerMP player) {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        int index = 0;
        for (String name : AbilityController.Instance.getChainedAbilityNamesSet()) {
            map.put(name, index++);
        }
        ScrollDataPacket.sendScrollData(player, map, EnumScrollData.CHAINED_ABILITIES);
    }

    public static DialogOption setNpcDialog(int slot, int dialogId, EntityPlayer player) throws IOException {
        return NoppesUtilServer.setNpcDialog(slot, dialogId, NoppesUtilServer.getEditingNpc(player));
    }

    public static DialogOption setNpcDialog(int slot, int dialogId, EntityNPCInterface npc) {
        if (npc == null) {
            return null;
        }
        if (!npc.dialogs.containsKey(slot)) {
            npc.dialogs.put(slot, new DialogOption());
        }
        DialogOption option = npc.dialogs.get(slot);
        option.dialogId = dialogId;
        if (option.hasDialog()) {
            option.title = option.getDialog().title;
        }
        return option;
    }

    public static void saveTileEntity(EntityPlayerMP player, NBTTagCompound compound) {
        int z;
        int y;
        int x = compound.func_74762_e("x");
        TileEntity tile = player.field_70170_p.func_147438_o(x, y = compound.func_74762_e("y"), z = compound.func_74762_e("z"));
        if (tile != null) {
            tile.func_145839_a(compound);
        }
    }

    public static void setClonerGui(EntityPlayerMP player, int x, int y, int z) {
        if (player == null) {
            return;
        }
        GuiOpenPacket.openGUI(player, EnumGuiType.Cloner, x, y, z);
    }

    public static void setTeleporterGUI(EntityPlayerMP player) {
        if (player == null) {
            return;
        }
        PacketHandler.Instance.sendToPlayer(new GuiTeleporterPacket(), player);
    }

    public static void setRecipeGui(EntityPlayerMP player, RecipeCarpentry recipe) {
        if (recipe == null) {
            return;
        }
        if (!(player.field_71070_bA instanceof ContainerManageRecipes)) {
            return;
        }
        ContainerManageRecipes container = (ContainerManageRecipes)player.field_71070_bA;
        container.setRecipe(recipe);
        GuiDataPacket.sendGuiData(player, recipe.writeNBT());
    }

    public static void setRecipeAnvilGui(EntityPlayerMP player, RecipeAnvil recipe) {
        if (recipe == null) {
            return;
        }
        if (!(player.field_71070_bA instanceof ContainerManageRecipes)) {
            return;
        }
        ContainerManageRecipes container = (ContainerManageRecipes)player.field_71070_bA;
        container.setRecipe(recipe);
        GuiDataPacket.sendGuiData(player, recipe.writeNBT());
    }

    public static void sendBank(EntityPlayerMP player, Bank bank) {
        NBTTagCompound compound = new NBTTagCompound();
        bank.writeEntityToNBT(compound);
        GuiDataPacket.sendGuiData(player, compound);
        if (player.field_71070_bA instanceof ContainerManageBanks) {
            ((ContainerManageBanks)player.field_71070_bA).setBank(bank);
        }
        player.func_71110_a(player.field_71070_bA, player.field_71070_bA.func_75138_a());
    }

    public static void sendNearbyNpcs(EntityPlayerMP player) {
        List npcs = player.field_70170_p.func_72872_a(EntityNPCInterface.class, player.field_70121_D.func_72314_b(120.0, 120.0, 120.0));
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for (EntityNPCInterface npc : npcs) {
            if (npc.field_70128_L) continue;
            float distance = player.func_70032_d((Entity)npc);
            DecimalFormat df = new DecimalFormat("#.#");
            String s = df.format(distance);
            if (distance < 10.0f) {
                s = "0" + s;
            }
            map.put(s + " : " + npc.display.name, npc.func_145782_y());
        }
        ScrollDataPacket.sendScrollData(player, map, EnumScrollData.OPTIONAL);
    }

    public static void sendGuiError(EntityPlayer player, int i) {
        GuiErrorPacket.errorGUI((EntityPlayerMP)player, i, new NBTTagCompound());
    }

    public static void sendGuiClose(EntityPlayerMP player, int i, NBTTagCompound comp) {
        if (player.field_71070_bA != player.field_71069_bz) {
            player.field_71070_bA = player.field_71069_bz;
        }
        GuiClosePacket.closeGUI(player, i, comp);
    }

    public static Entity spawnCloneWithProtection(NBTTagCompound compound, int x, int y, int z, World worldObj) {
        ServerCloneController.Instance.cleanTags(compound);
        compound.func_74782_a("Pos", (NBTBase)NBTTags.nbtDoubleList((double)x + 0.5, y, (double)z + 0.5));
        Entity entity = EntityList.func_75615_a((NBTTagCompound)compound, (World)worldObj);
        if (entity == null) {
            return null;
        }
        entity.func_70107_b((double)x + 0.5, (double)y, (double)z + 0.5);
        if (entity instanceof EntityNPCInterface) {
            EntityNPCInterface npc = (EntityNPCInterface)entity;
            npc.ais.startPos = new int[]{MathHelper.func_76128_c((double)npc.field_70165_t), MathHelper.func_76128_c((double)npc.field_70163_u), MathHelper.func_76128_c((double)npc.field_70161_v)};
            npc.field_70173_aa = 0;
            npc.totalTicksAlive = 0L;
        }
        worldObj.func_72838_d(entity);
        entity.field_70137_T = entity.field_70163_u = (double)(y + 1);
        entity.field_70167_r = entity.field_70163_u;
        return entity;
    }

    public static Entity getEntityFromNBT(NBTTagCompound compound, int x, int y, int z, World worldObj) {
        ServerCloneController.Instance.cleanTags(compound);
        compound.func_74782_a("Pos", (NBTBase)NBTTags.nbtDoubleList((double)x + 0.5, y + 1, (double)z + 0.5));
        Entity entity = EntityList.func_75615_a((NBTTagCompound)compound, (World)worldObj);
        if (entity == null) {
            return null;
        }
        if (entity instanceof EntityNPCInterface) {
            EntityNPCInterface npc = (EntityNPCInterface)entity;
            npc.ais.startPos = new int[]{MathHelper.func_76128_c((double)npc.field_70165_t), MathHelper.func_76128_c((double)npc.field_70163_u), MathHelper.func_76128_c((double)npc.field_70161_v)};
            npc.field_70173_aa = 0;
            npc.totalTicksAlive = 0L;
        }
        return entity;
    }

    public static Entity spawnClone(NBTTagCompound compound, int x, int y, int z, World worldObj) {
        Entity entity = NoppesUtilServer.getEntityFromNBT(compound, x, y, z, worldObj);
        if (entity == null) {
            return null;
        }
        entity.field_71093_bK = worldObj.field_73011_w.field_76574_g;
        int i = MathHelper.func_76128_c((double)(entity.field_70165_t / 16.0));
        int j = MathHelper.func_76128_c((double)(entity.field_70161_v / 16.0));
        if (!entity.field_98038_p && !worldObj.func_72904_c((int)entity.field_70165_t, (int)entity.field_70163_u, (int)entity.field_70161_v, (int)entity.field_70165_t, (int)entity.field_70163_u, (int)entity.field_70161_v)) {
            return null;
        }
        worldObj.func_72964_e(i, j).func_76612_a(entity);
        worldObj.field_72996_f.add(entity);
        worldObj.func_72923_a(entity);
        return entity;
    }

    public static boolean isOp(EntityPlayer player) {
        return MinecraftServer.func_71276_C().func_71203_ab().func_152596_g(player.func_146103_bH());
    }

    public static void GivePlayerItem(Entity entity, EntityPlayer player, ItemStack item) {
        if (entity.field_70170_p.field_72995_K || item == null) {
            return;
        }
        item = item.func_77946_l();
        float f = 0.7f;
        double d = (double)(entity.field_70170_p.field_73012_v.nextFloat() * f) + (double)(1.0f - f);
        double d1 = (double)(entity.field_70170_p.field_73012_v.nextFloat() * f) + (double)(1.0f - f);
        double d2 = (double)(entity.field_70170_p.field_73012_v.nextFloat() * f) + (double)(1.0f - f);
        EntityItem entityitem = new EntityItem(entity.field_70170_p, entity.field_70165_t + d, entity.field_70163_u + d1, entity.field_70161_v + d2, item);
        entityitem.field_145804_b = 2;
        entity.field_70170_p.func_72838_d((Entity)entityitem);
        int i = item.field_77994_a;
        if (player.field_71071_by.func_70441_a(item)) {
            entity.field_70170_p.func_72956_a((Entity)entityitem, "random.pop", 0.2f, ((entity.field_70170_p.field_73012_v.nextFloat() - entity.field_70170_p.field_73012_v.nextFloat()) * 0.7f + 1.0f) * 2.0f);
            player.func_71001_a((Entity)entityitem, i);
            if (item.field_77994_a <= 0) {
                entityitem.func_70106_y();
            }
        }
    }

    public static EntityPlayer getPlayer(UUID id) {
        List list = MinecraftServer.func_71276_C().func_71203_ab().field_72404_b;
        for (EntityPlayer player : list) {
            if (!id.equals(player.func_110124_au())) continue;
            return player;
        }
        return null;
    }

    public static Entity getEntityFromUUID(World world, UUID uuid) {
        for (Object obj : world.field_72996_f) {
            Entity entity;
            if (!(obj instanceof Entity) || !(entity = (Entity)obj).func_110124_au().equals(uuid)) continue;
            return entity;
        }
        return null;
    }

    public static EntityPlayer getPlayerByName(String playername) {
        return MinecraftServer.func_71276_C().func_71203_ab().func_152612_a(playername);
    }

    public static Entity GetDamageSource(DamageSource damagesource) {
        Entity entity = damagesource.func_76346_g();
        if (entity == null) {
            entity = damagesource.func_76364_f();
        }
        if (entity instanceof EntityArrow && ((EntityArrow)entity).field_70250_c instanceof EntityLivingBase) {
            entity = ((EntityArrow)entity).field_70250_c;
        } else if (entity instanceof EntityThrowable) {
            entity = ((EntityThrowable)entity).func_85052_h();
        }
        return entity;
    }

    public static void isGUIOpen(ByteBuf buffer, EntityPlayer player) throws IOException {
        PlayerData playerdata = PlayerData.get(player);
        boolean isGUIOpen = buffer.readBoolean();
        playerdata.setGUIOpen(isGUIOpen);
    }

    public static boolean IsItemStackNull(ItemStack is) {
        return is == null || is.field_77994_a == 0 || is.func_77973_b() == null;
    }

    public static String millisToTime(long millis) {
        long seconds = millis / 1000L;
        long minutes = seconds / 60L;
        long hours = minutes / 60L;
        long days = hours / 24L;
        seconds %= 60L;
        minutes %= 60L;
        hours %= 24L;
        StringBuilder sb = new StringBuilder();
        if (days > 0L) {
            sb.append(days).append(" day").append(days == 1L ? "" : "s").append(", ");
        }
        if (hours > 0L) {
            sb.append(hours).append(" hour").append(hours == 1L ? "" : "s").append(", ");
        }
        if (minutes > 0L) {
            sb.append(minutes).append(" minute").append(minutes == 1L ? "" : "s").append(", ");
        }
        sb.append(seconds).append(" second").append(seconds == 1L ? "" : "s");
        return sb.toString();
    }

    public static void sendPlayerDataInfo(String playerName, EntityPlayerMP player) {
        Quest quest;
        EntityPlayerMP pl = MinecraftServer.func_71276_C().func_71203_ab().func_152612_a(playerName);
        PlayerData playerdata = pl == null ? PlayerDataController.Instance.getDataFromUsername(playerName) : PlayerData.get((EntityPlayer)pl);
        HashMap<String, Integer> questActiveMap = new HashMap<String, Integer>();
        HashMap<String, Integer> questFinishedMap = new HashMap<String, Integer>();
        HashMap<String, Integer> questCategoriesMap = new HashMap<String, Integer>();
        QuestController qc = QuestController.Instance;
        PlayerQuestData qData = playerdata.questData;
        for (Integer questId : qData.activeQuests.keySet()) {
            if (!qc.quests.containsKey(questId)) continue;
            quest = qc.quests.get(questId);
            questActiveMap.put(quest.category.title + ": " + quest.title, questId);
            if (questCategoriesMap.containsKey(quest.category.title)) continue;
            questCategoriesMap.put(quest.category.title, quest.category.id);
        }
        for (Integer questId : qData.finishedQuests.keySet()) {
            if (!qc.quests.containsKey(questId)) continue;
            quest = qc.quests.get(questId);
            questFinishedMap.put(quest.category.title + ": " + quest.title, questId);
            if (questCategoriesMap.containsKey(quest.category.title)) continue;
            questCategoriesMap.put(quest.category.title, quest.category.id);
        }
        HashMap<String, Integer> dialogReadMap = new HashMap<String, Integer>();
        HashMap<String, Integer> dialogCategoriesMap = new HashMap<String, Integer>();
        PlayerDialogData dData = playerdata.dialogData;
        for (Integer dialogId : dData.dialogsRead) {
            if (!DialogController.Instance.dialogs.containsKey(dialogId)) continue;
            Dialog dialog = DialogController.Instance.dialogs.get(dialogId);
            dialogReadMap.put(dialog.category.title + ": " + dialog.title, dialogId);
            if (dialogCategoriesMap.containsKey(dialog.category.title)) continue;
            dialogCategoriesMap.put(dialog.category.title, dialog.category.id);
        }
        HashMap<String, Integer> transportLocationsMap = new HashMap<String, Integer>();
        HashMap<String, Integer> transportCategoriesMap = new HashMap<String, Integer>();
        PlayerTransportData tData = playerdata.transportData;
        TransportController tc = TransportController.getInstance();
        for (Integer id : tData.transports) {
            TransportLocation location = tc.getTransport(id);
            if (location == null) continue;
            transportLocationsMap.put(location.category.title + ": " + location.name, id);
            if (transportCategoriesMap.containsKey(location.category.title)) continue;
            transportCategoriesMap.put(location.category.title, location.category.id);
        }
        HashMap<String, Integer> bankMap = new HashMap<String, Integer>();
        PlayerBankData bData = playerdata.bankData;
        BankController bc = BankController.getInstance();
        for (Integer bankId : bData.banks.keySet()) {
            if (!bc.banks.containsKey(bankId)) continue;
            Bank bank = bc.banks.get(bankId);
            bankMap.put(bank.name, bankId);
        }
        HashMap<String, Integer> factionMap = new HashMap<String, Integer>();
        PlayerFactionData fData = playerdata.factionData;
        FactionController fc = FactionController.getInstance();
        for (Integer factionId : fData.factionData.keySet()) {
            if (!fc.factions.containsKey(factionId)) continue;
            Faction faction = fc.factions.get(factionId);
            factionMap.put(faction.name + "(" + fData.getFactionPoints(factionId) + ")", factionId);
        }
        PlayerDataInfoPacket packet = new PlayerDataInfoPacket(playerName, questCategoriesMap, questActiveMap, questFinishedMap, dialogCategoriesMap, dialogReadMap, transportCategoriesMap, transportLocationsMap, bankMap, factionMap, playerdata.magicData);
        PacketHandler.Instance.sendToPlayer(packet, player);
    }

    public static void removePlayerDataInfo(String playerName, int tabType, int value, EntityPlayerMP player) {
        EntityPlayerMP pl = MinecraftServer.func_71276_C().func_71203_ab().func_152612_a(playerName);
        EnumPlayerData enumPlayerData = EnumPlayerData.values()[tabType];
        PlayerData playerdata = pl == null ? PlayerDataController.Instance.getDataFromUsername(playerName) : PlayerData.get((EntityPlayer)pl);
        if (enumPlayerData == EnumPlayerData.Quest) {
            playerdata.questData.activeQuests.remove(value);
            playerdata.questData.finishedQuests.remove(value);
        }
        if (enumPlayerData == EnumPlayerData.Dialog) {
            playerdata.dialogData.dialogsRead.remove(value);
        }
        if (enumPlayerData == EnumPlayerData.Transport) {
            playerdata.transportData.transports.remove(value);
        }
        if (enumPlayerData == EnumPlayerData.Bank) {
            playerdata.bankData.banks.remove(value);
        }
        if (enumPlayerData == EnumPlayerData.Factions) {
            playerdata.factionData.factionData.remove(value);
        }
        if (enumPlayerData == EnumPlayerData.Magic) {
            playerdata.magicData.removeMagic(value);
        }
        playerdata.save();
        if (pl != null) {
            SyncController.syncPlayer(pl);
        }
    }

    public static void savePlayerDataInfo(String playerName, int tabType, NBTTagCompound compound, EntityPlayerMP player) {
        EntityPlayerMP pl = MinecraftServer.func_71276_C().func_71203_ab().func_152612_a(playerName);
        EnumPlayerData enumPlayerData = EnumPlayerData.values()[tabType];
        PlayerData playerdata = pl == null ? PlayerDataController.Instance.getDataFromUsername(playerName) : PlayerData.get((EntityPlayer)pl);
        if (enumPlayerData == EnumPlayerData.Magic) {
            playerdata.magicData.readToNBT(compound);
        }
        playerdata.save();
        if (pl != null) {
            SyncController.syncPlayer(pl);
        }
    }
}

