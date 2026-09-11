/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.stats.Achievement
 *  net.minecraft.stats.StatBase
 *  net.minecraft.stats.StatList
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.ChatStyle
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.world.WorldSettings
 */
package noppes.npcs.scripted.entity;

import java.util.ArrayList;
import java.util.List;
import kamkeel.npcs.controllers.AttributeController;
import kamkeel.npcs.entity.EntityEnergyProjectile;
import kamkeel.npcs.network.PacketHandler;
import kamkeel.npcs.network.packets.data.AchievementPacket;
import kamkeel.npcs.network.packets.data.ChatAlertPacket;
import kamkeel.npcs.network.packets.data.gui.GuiClosePacket;
import kamkeel.npcs.network.packets.data.script.ScriptOverlayClosePacket;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatList;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.WorldSettings;
import noppes.npcs.CustomNpcsPermissions;
import noppes.npcs.NoppesStringUtils;
import noppes.npcs.NoppesUtilPlayer;
import noppes.npcs.NoppesUtilServer;
import noppes.npcs.api.IBlock;
import noppes.npcs.api.IContainer;
import noppes.npcs.api.IParticle;
import noppes.npcs.api.IPixelmonPlayerData;
import noppes.npcs.api.IPos;
import noppes.npcs.api.IScreenSize;
import noppes.npcs.api.ITimers;
import noppes.npcs.api.IWorld;
import noppes.npcs.api.entity.IEnergyProjectile;
import noppes.npcs.api.entity.IEntity;
import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.api.gui.ICustomGui;
import noppes.npcs.api.handler.IActionManager;
import noppes.npcs.api.handler.IOverlayHandler;
import noppes.npcs.api.handler.data.IAnimationData;
import noppes.npcs.api.handler.data.IDialog;
import noppes.npcs.api.handler.data.IMagicData;
import noppes.npcs.api.handler.data.IPlayerAttributes;
import noppes.npcs.api.handler.data.IQuest;
import noppes.npcs.api.handler.data.ISound;
import noppes.npcs.api.item.IItemStack;
import noppes.npcs.api.overlay.ICustomOverlay;
import noppes.npcs.compat.PixelmonHelper;
import noppes.npcs.config.ConfigScript;
import noppes.npcs.constants.EnumQuestType;
import noppes.npcs.containers.ContainerCustomGui;
import noppes.npcs.controllers.CustomGuiController;
import noppes.npcs.controllers.DialogController;
import noppes.npcs.controllers.PartyController;
import noppes.npcs.controllers.PlayerQuestController;
import noppes.npcs.controllers.QuestController;
import noppes.npcs.controllers.data.Dialog;
import noppes.npcs.controllers.data.Party;
import noppes.npcs.controllers.data.PlayerData;
import noppes.npcs.controllers.data.PlayerDialogData;
import noppes.npcs.controllers.data.PlayerQuestData;
import noppes.npcs.controllers.data.Quest;
import noppes.npcs.controllers.data.QuestData;
import noppes.npcs.entity.EntityDialogNpc;
import noppes.npcs.scripted.CustomNPCsException;
import noppes.npcs.scripted.NpcAPI;
import noppes.npcs.scripted.ScriptPixelmonPlayerData;
import noppes.npcs.scripted.ScriptSound;
import noppes.npcs.scripted.entity.ScriptDBCPlayer;
import noppes.npcs.scripted.entity.ScriptLivingBase;
import noppes.npcs.scripted.gui.ScriptGui;
import noppes.npcs.scripted.overlay.ScriptOverlay;
import noppes.npcs.util.ValueUtil;

public class ScriptPlayer<T extends EntityPlayerMP>
extends ScriptLivingBase<T>
implements IPlayer {
    public T player;
    private PlayerData data;

    public ScriptPlayer(T player) {
        super(player);
        this.player = player;
    }

    @Override
    public String getDisplayName() {
        return this.player.getDisplayName();
    }

    @Override
    public String getName() {
        return this.player.func_70005_c_();
    }

    @Override
    public void kick(String reason) {
        ((EntityPlayerMP)this.player).field_71135_a.func_147360_c(reason);
    }

    @Override
    public void spawnParticle(IParticle entityParticle) {
        entityParticle.spawnOnEntity(this);
    }

    @Override
    public void setPosition(double x, double y, double z) {
        NoppesUtilPlayer.teleportPlayer(this.player, x, y, z, ((EntityPlayerMP)this.player).field_71093_bK);
    }

    public void setPos(double x, double y, double z) {
        this.setPosition(x, y, z);
    }

    @Override
    public void setPosition(IPos pos) {
        this.setPosition(pos.getXD(), pos.getYD(), pos.getZD());
    }

    @Override
    public void setPos(IPos pos) {
        this.setPosition(pos);
    }

    @Override
    public void setPosition(double x, double y, double z, int dimensionId) {
        if (NpcAPI.Instance().getIWorld(dimensionId) == null) {
            return;
        }
        NoppesUtilPlayer.teleportPlayer(this.player, x, y, z, dimensionId);
    }

    public void setPos(double x, double y, double z, int dimensionId) {
        this.setPosition(x, y, z, dimensionId);
    }

    @Override
    public void setPosition(double x, double y, double z, IWorld world) {
        this.setPosition(x, y, z, world.getDimensionID());
    }

    public void setPos(double x, double y, double z, IWorld world) {
        this.setPos(x, y, z, world.getDimensionID());
    }

    @Override
    public void setPosition(IPos pos, int dimensionId) {
        this.setPosition(pos.getXD(), pos.getYD(), pos.getZD(), dimensionId);
    }

    public void setPos(IPos pos, int dimensionId) {
        this.setPosition(pos, dimensionId);
    }

    @Override
    public void setPosition(IPos pos, IWorld world) {
        this.setPosition(pos.getXD(), pos.getYD(), pos.getZD(), world.getDimensionID());
    }

    public void setPos(IPos pos, IWorld world) {
        this.setPosition(pos, world.getDimensionID());
    }

    @Override
    public void setDimension(int dimension) {
        this.setPosition(this.getPos(), dimension);
    }

    public void setDimension(IWorld world) {
        this.setDimension(world.getDimensionID());
    }

    @Override
    public int getHunger() {
        return this.player.func_71024_bL().func_75116_a();
    }

    @Override
    public void setHunger(int hunger) {
        int prevHunger = this.getHunger();
        if (hunger < 0) {
            hunger = 0;
        }
        this.player.func_71024_bL().func_75122_a(hunger - prevHunger, 0.0f);
    }

    @Override
    public float getSaturation() {
        return this.player.func_71024_bL().func_75115_e();
    }

    @Override
    public void setSaturation(float saturation) {
        float prevSaturation = this.getHunger();
        if (saturation < 0.0f) {
            saturation = 0.0f;
        }
        this.player.func_71024_bL().func_75122_a(0, saturation - prevSaturation);
    }

    @Override
    public void showDialog(IDialog dialog) {
        if (dialog != null) {
            this.showDialog(dialog.getId());
        }
    }

    @Override
    public boolean hasReadDialog(IDialog dialog) {
        if (dialog != null) {
            return this.hasReadDialog(dialog.getId());
        }
        return false;
    }

    @Override
    public void readDialog(IDialog dialog) {
        if (dialog != null) {
            this.readDialog(dialog.getId());
        }
    }

    @Override
    public void unreadDialog(IDialog dialog) {
        if (dialog != null) {
            this.unreadDialog(dialog.getId());
        }
    }

    @Override
    public void showDialog(int id) {
        Dialog dialog = (Dialog)DialogController.Instance.get(id);
        if (dialog == null) {
            return;
        }
        NoppesUtilServer.openDialog(this.player, new EntityDialogNpc(((EntityPlayerMP)this.player).field_70170_p), dialog, 0);
    }

    @Override
    public boolean hasReadDialog(int id) {
        PlayerDialogData data = PlayerData.get(this.player).dialogData;
        return data.dialogsRead.contains(id);
    }

    @Override
    public void readDialog(int id) {
        PlayerData playerData = PlayerData.get(this.player);
        playerData.dialogData.dialogsRead.add(id);
        playerData.updateClient = true;
    }

    @Override
    public void unreadDialog(int id) {
        PlayerData playerData = PlayerData.get(this.player);
        playerData.dialogData.dialogsRead.remove(id);
        playerData.updateClient = true;
    }

    @Override
    public boolean hasFinishedQuest(IQuest quest) {
        if (quest == null) {
            return false;
        }
        return this.hasFinishedQuest(quest.getId());
    }

    @Override
    public boolean hasActiveQuest(IQuest quest) {
        if (quest == null) {
            return false;
        }
        return this.hasActiveQuest(quest.getId());
    }

    @Override
    public void startQuest(IQuest quest) {
        if (quest == null) {
            return;
        }
        this.startQuest(quest.getId());
    }

    @Override
    public void finishQuest(IQuest quest) {
        if (quest == null) {
            return;
        }
        this.finishQuest(quest.getId());
    }

    @Override
    public void stopQuest(IQuest quest) {
        if (quest == null) {
            return;
        }
        this.stopQuest(quest.getId());
    }

    @Override
    public void removeQuest(IQuest quest) {
        if (quest == null) {
            return;
        }
        this.removeQuest(quest.getId());
    }

    @Override
    public boolean hasFinishedQuest(int id) {
        PlayerQuestData data = PlayerData.get(this.player).questData;
        return data.finishedQuests.containsKey(id);
    }

    @Override
    public boolean hasActiveQuest(int id) {
        PlayerQuestData data = PlayerData.get(this.player).questData;
        return data.activeQuests.containsKey(id);
    }

    @Override
    public void startQuest(int id) {
        Quest quest = QuestController.Instance.quests.get(id);
        if (quest == null) {
            return;
        }
        PlayerData data = PlayerData.get(this.player);
        if (data.questData.activeQuests.containsKey(id)) {
            return;
        }
        QuestData questdata = new QuestData(quest);
        data.questData.activeQuests.put(id, questdata);
        AchievementPacket.sendAchievement(this.player, false, "quest.newquest", quest.title);
        ChatAlertPacket.sendChatAlert(this.player, new Object[]{"quest.newquest", ": ", quest.title});
        data.updateClient = true;
    }

    @Override
    public void finishQuest(int id) {
        Quest quest = QuestController.Instance.quests.get(id);
        if (quest == null) {
            return;
        }
        PlayerData data = PlayerData.get(this.player);
        PlayerQuestController.setQuestFinishedUtil(this.player, quest, data.questData);
        data.updateClient = true;
    }

    @Override
    public void stopQuest(int id) {
        Quest quest = QuestController.Instance.quests.get(id);
        if (quest == null) {
            return;
        }
        PlayerData data = PlayerData.get(this.player);
        data.questData.activeQuests.remove(id);
        data.updateClient = true;
    }

    @Override
    public void removeQuest(int id) {
        Quest quest = QuestController.Instance.quests.get(id);
        if (quest == null) {
            return;
        }
        PlayerData data = PlayerData.get(this.player);
        data.questData.activeQuests.remove(id);
        data.questData.finishedQuests.remove(id);
        data.updateClient = true;
    }

    @Override
    public int getType() {
        return 1;
    }

    @Override
    public boolean typeOf(int type) {
        return type == 1 ? true : super.typeOf(type);
    }

    @Override
    public void addFactionPoints(int faction, int points) {
        PlayerData data = PlayerData.get(this.player);
        data.factionData.increasePoints(faction, points, (EntityPlayer)this.player);
    }

    @Override
    public void setFactionPoints(int faction, int points) {
        PlayerData data = PlayerData.get(this.player);
        data.factionData.increasePoints(faction, points - this.getFactionPoints(faction), (EntityPlayer)this.player);
    }

    @Override
    public int getFactionPoints(int faction) {
        PlayerData data = PlayerData.get(this.player);
        return data.factionData.getFactionPoints(faction);
    }

    @Override
    public void sendMessage(String message) {
        this.player.func_145747_a((IChatComponent)new ChatComponentTranslation(NoppesStringUtils.formatText(message, this.player), new Object[0]));
    }

    public void sendMessage(String message, String color, boolean bold, boolean italic, boolean underlined) {
        this.sendMessage(message, color, bold, italic, underlined, false, false);
    }

    public void sendMessage(String message, String color, boolean bold, boolean italic, boolean obfuscated, boolean strikethrough, boolean underlined) {
        EnumChatFormatting c = this.getColor(color);
        if (c == null) {
            return;
        }
        ChatComponentTranslation chat = new ChatComponentTranslation(NoppesStringUtils.formatText(message, this.player), new Object[0]);
        ChatStyle style = new ChatStyle();
        style.func_150238_a(c);
        style.func_150227_a(Boolean.valueOf(bold));
        style.func_150217_b(Boolean.valueOf(italic));
        style.func_150237_e(Boolean.valueOf(obfuscated));
        style.func_150225_c(Boolean.valueOf(strikethrough));
        style.func_150228_d(Boolean.valueOf(underlined));
        chat.func_150255_a(style);
        this.player.func_145747_a((IChatComponent)chat);
    }

    private EnumChatFormatting getColor(String color) {
        switch (color) {
            case "black": {
                return EnumChatFormatting.BLACK;
            }
            case "dark_blue": {
                return EnumChatFormatting.DARK_BLUE;
            }
            case "dark_green": {
                return EnumChatFormatting.DARK_GREEN;
            }
            case "dark_aqua": {
                return EnumChatFormatting.DARK_AQUA;
            }
            case "dark_red": {
                return EnumChatFormatting.DARK_RED;
            }
            case "dark_purple": {
                return EnumChatFormatting.DARK_PURPLE;
            }
            case "gold": {
                return EnumChatFormatting.GOLD;
            }
            case "gray": {
                return EnumChatFormatting.GRAY;
            }
            case "dark_gray": {
                return EnumChatFormatting.DARK_GRAY;
            }
            case "blue": {
                return EnumChatFormatting.BLUE;
            }
            case "green": {
                return EnumChatFormatting.GREEN;
            }
            case "aqua": {
                return EnumChatFormatting.AQUA;
            }
            case "red": {
                return EnumChatFormatting.RED;
            }
            case "light_purple": {
                return EnumChatFormatting.LIGHT_PURPLE;
            }
            case "yellow": {
                return EnumChatFormatting.YELLOW;
            }
            case "white": {
                return EnumChatFormatting.WHITE;
            }
        }
        return null;
    }

    @Override
    public int getMode() {
        return ((EntityPlayerMP)this.player).field_71134_c.func_73081_b().func_77148_a();
    }

    @Override
    public void setMode(int type) {
        this.player.func_71033_a(WorldSettings.func_77161_a((int)type));
    }

    @Override
    public IItemStack[] getInventory() {
        IItemStack[] items = new IItemStack[36];
        for (int i = 0; i < ((EntityPlayerMP)this.player).field_71071_by.field_70462_a.length; ++i) {
            ItemStack item = ((EntityPlayerMP)this.player).field_71071_by.field_70462_a[i];
            if (item == null) continue;
            items[i] = NpcAPI.Instance().getIItemStack(item);
        }
        return items;
    }

    public int inventoryItemCount(IItemStack item) {
        return this.inventoryItemCount(item, true, true);
    }

    @Override
    public int inventoryItemCount(IItemStack item, boolean ignoreNBT, boolean ignoreDamage) {
        int i = 0;
        for (ItemStack is : ((EntityPlayerMP)this.player).field_71071_by.field_70462_a) {
            if (is == null || !NoppesUtilPlayer.compareItems(is, item.getMCItemStack(), ignoreDamage, ignoreNBT)) continue;
            i += is.field_77994_a;
        }
        return i;
    }

    @Override
    public boolean removeItem(String id, int damage, int amount) {
        Item item = (Item)Item.field_150901_e.func_82594_a(id);
        if (item == null) {
            return false;
        }
        return this.removeItem(NpcAPI.Instance().getIItemStack(new ItemStack(item, 1, damage)), amount);
    }

    public boolean removeItem(IItemStack item, int amount) {
        return this.removeItem(item, amount, true, true);
    }

    @Override
    public boolean removeItem(IItemStack item, int amount, boolean ignoreNBT, boolean ignoreDamage) {
        int count = this.inventoryItemCount(item, ignoreNBT, ignoreDamage);
        if (amount > count) {
            return false;
        }
        if (count == amount) {
            this.removeAllItems(item, ignoreNBT, ignoreDamage);
        } else {
            for (int i = 0; i < ((EntityPlayerMP)this.player).field_71071_by.field_70462_a.length; ++i) {
                ItemStack is = ((EntityPlayerMP)this.player).field_71071_by.field_70462_a[i];
                if (is == null || !NoppesUtilPlayer.compareItems(is, item.getMCItemStack(), ignoreDamage, ignoreNBT)) continue;
                if (amount > is.field_77994_a) {
                    ((EntityPlayerMP)this.player).field_71071_by.field_70462_a[i] = null;
                    amount -= is.field_77994_a;
                    continue;
                }
                is.func_77979_a(amount);
                break;
            }
        }
        this.updatePlayerInventory();
        return true;
    }

    @Override
    public int removeAllItems(IItemStack item, boolean ignoreNBT, boolean ignoreDamage) {
        int removed = 0;
        for (int i = 0; i < ((EntityPlayerMP)this.player).field_71071_by.field_70462_a.length; ++i) {
            ItemStack is = ((EntityPlayerMP)this.player).field_71071_by.field_70462_a[i];
            if (is == null || !NoppesUtilPlayer.compareItems(is, item.getMCItemStack(), ignoreDamage, ignoreNBT)) continue;
            ((EntityPlayerMP)this.player).field_71071_by.field_70462_a[i] = null;
            ++removed;
        }
        return removed;
    }

    @Override
    public boolean giveItem(IItemStack item, int amount) {
        if (item != null && item.getMCItemStack() != null) {
            item.setStackSize(amount);
            boolean bool = ((EntityPlayerMP)this.player).field_71071_by.func_70441_a(item.getMCItemStack());
            this.updatePlayerInventory();
            return bool;
        }
        return false;
    }

    @Override
    public boolean giveItem(String id, int damage, int amount) {
        Item item = (Item)Item.field_150901_e.func_82594_a(id);
        if (item == null) {
            return false;
        }
        return ((EntityPlayerMP)this.player).field_71071_by.func_70441_a(new ItemStack(item, amount, damage));
    }

    @Override
    public void setSpawnpoint(int x, int y, int z) {
        x = ValueUtil.clamp(x, -30000000, 30000000);
        z = ValueUtil.clamp(z, -30000000, 30000000);
        y = ValueUtil.clamp(y, 0, 256);
        this.player.func_71063_a(new ChunkCoordinates(x, y, z), true);
    }

    @Override
    public void setSpawnpoint(IPos pos) {
        this.setSpawnpoint(pos.getX(), pos.getY(), pos.getZ());
    }

    @Override
    public void resetSpawnpoint() {
        this.player.func_71063_a(null, false);
    }

    @Override
    public void clearInventory() {
        int i;
        for (i = 0; i < ((EntityPlayerMP)this.player).field_71071_by.field_70462_a.length; ++i) {
            ((EntityPlayerMP)this.player).field_71071_by.field_70462_a[i] = null;
        }
        for (i = 0; i < ((EntityPlayerMP)this.player).field_71071_by.field_70460_b.length; ++i) {
            ((EntityPlayerMP)this.player).field_71071_by.field_70460_b[i] = null;
        }
    }

    @Override
    public void setRotation(float rotationYaw) {
        NoppesUtilPlayer.teleportPlayer(this.player, ((EntityPlayerMP)this.player).field_70165_t, ((EntityPlayerMP)this.player).field_70163_u, ((EntityPlayerMP)this.player).field_70161_v, rotationYaw, ((EntityPlayerMP)this.player).field_70125_A, ((EntityPlayerMP)this.player).field_71093_bK);
    }

    @Override
    public void setRotation(float rotationYaw, float rotationPitch) {
        NoppesUtilPlayer.teleportPlayer(this.player, ((EntityPlayerMP)this.player).field_70165_t, ((EntityPlayerMP)this.player).field_70163_u, ((EntityPlayerMP)this.player).field_70161_v, rotationYaw, rotationPitch, ((EntityPlayerMP)this.player).field_71093_bK);
    }

    @Override
    public float getRotation() {
        return ((EntityPlayerMP)this.entity).field_70177_z;
    }

    @Override
    public void swingHand() {
        NoppesUtilPlayer.swingPlayerArm(this.player);
    }

    @Override
    public void disableMouseInput(long time, int ... buttonIds) {
        NoppesUtilPlayer.disableMouseInput(this.player, time, buttonIds);
    }

    @Override
    public void stopUsingItem() {
        this.player.func_71034_by();
    }

    @Override
    public void clearItemInUse() {
        this.player.func_71041_bz();
    }

    @Override
    public void playSound(String name, float volume, float pitch) {
        this.player.func_85030_a(name, volume, pitch);
    }

    @Override
    public void playSound(int id, ISound sound) {
        NoppesUtilPlayer.playSoundTo(this.player, id, (ScriptSound)sound);
    }

    @Override
    public void playSound(ISound sound) {
        NoppesUtilPlayer.playSoundTo(this.player, (ScriptSound)sound);
    }

    @Override
    public void stopSound(int id) {
        NoppesUtilPlayer.stopSoundFor(this.player, id);
    }

    @Override
    public void pauseSounds() {
        NoppesUtilPlayer.pauseSoundsFor(this.player);
    }

    @Override
    public void continueSounds() {
        NoppesUtilPlayer.continueSoundsFor(this.player);
    }

    @Override
    public void stopSounds() {
        NoppesUtilPlayer.stopSoundsFor(this.player);
    }

    @Override
    public void mountEntity(Entity ridingEntity) {
        this.player.func_70078_a(ridingEntity);
    }

    @Override
    public IEntity dropOneItem(boolean dropStack) {
        return NpcAPI.Instance().getIEntity((Entity)this.player.func_71040_bB(dropStack));
    }

    @Override
    public boolean canHarvestBlock(IBlock block) {
        return this.player.func_146099_a(block.getMCBlock());
    }

    @Override
    public boolean interactWith(IEntity entity) {
        return this.player.func_70998_m(entity.getMCEntity());
    }

    @Override
    public boolean hasAchievement(String achievement) {
        StatBase statbase = StatList.func_151177_a((String)achievement);
        if (statbase == null || !(statbase instanceof Achievement)) {
            return false;
        }
        return this.player.func_147099_x().func_77443_a((Achievement)statbase);
    }

    @Override
    public boolean hasBukkitPermission(String permission) {
        return CustomNpcsPermissions.hasPermissionString(this.player, permission);
    }

    @Override
    public int getExpLevel() {
        return ((EntityPlayerMP)this.player).field_71068_ca;
    }

    @Override
    public void setExpLevel(int level) {
        ((EntityPlayerMP)this.player).field_71068_ca = level;
        this.player.func_82242_a(0);
    }

    @Override
    public IPixelmonPlayerData getPixelmonData() {
        if (!PixelmonHelper.Enabled) {
            return null;
        }
        return new ScriptPixelmonPlayerData((EntityPlayerMP)this.player);
    }

    @Override
    public ITimers getTimers() {
        return PlayerData.get(this.player).timers;
    }

    @Override
    public IScreenSize getScreenSize() {
        PlayerData data = PlayerData.get(this.player);
        return data.getScreenSize();
    }

    @Override
    public void updatePlayerInventory() {
        ((EntityPlayerMP)this.entity).field_71069_bz.func_75142_b();
        PlayerData playerData = PlayerData.get(this.player);
        PlayerQuestData questData = playerData.questData;
        Party playerParty = playerData.getPlayerParty();
        if (playerParty != null) {
            PartyController.Instance().checkQuestCompletion(playerParty, EnumQuestType.Item);
        }
        questData.checkQuestCompletion(playerData, EnumQuestType.Item);
    }

    @Deprecated
    public boolean checkGUIOpen() {
        NoppesUtilPlayer.isGUIOpen(this.player);
        PlayerData data = PlayerData.get(this.player);
        return data.getGUIOpen();
    }

    @Override
    @Deprecated
    public ScriptDBCPlayer<T> getDBCPlayer() {
        return this instanceof ScriptDBCPlayer ? (ScriptDBCPlayer)this : null;
    }

    @Override
    public boolean blocking() {
        return this.player.func_70632_aY();
    }

    @Override
    public PlayerData getData() {
        if (this.data == null) {
            this.data = PlayerData.get(this.player);
        }
        return this.data;
    }

    @Override
    public boolean isScriptingDev() {
        return ConfigScript.isScriptDev(this.player);
    }

    @Override
    public IQuest[] getActiveQuests() {
        PlayerQuestData data = (PlayerQuestData)this.getData().getQuestData();
        ArrayList<IQuest> quests = new ArrayList<IQuest>();
        for (int id : data.activeQuests.keySet()) {
            IQuest quest = QuestController.Instance.quests.get(id);
            if (quest == null) continue;
            quests.add(quest);
        }
        return quests.toArray(new IQuest[quests.size()]);
    }

    @Override
    public IContainer getOpenContainer() {
        return NpcAPI.Instance().getIContainer(((EntityPlayerMP)this.entity).field_71070_bA);
    }

    @Override
    public void showCustomGui(ICustomGui gui) {
        CustomGuiController.openGui(this, (ScriptGui)gui);
    }

    @Override
    public ICustomGui getCustomGui() {
        return ((EntityPlayerMP)this.entity).field_71070_bA instanceof ContainerCustomGui ? ((ContainerCustomGui)((EntityPlayerMP)this.entity).field_71070_bA).customGui : null;
    }

    @Override
    public void closeGui() {
        ((EntityPlayerMP)this.entity).func_71128_l();
        GuiClosePacket.closeGUI(this.player, -1, new NBTTagCompound());
    }

    @Override
    public void showCustomOverlay(ICustomOverlay overlay) {
        CustomGuiController.openOverlay(this, (ScriptOverlay)overlay);
    }

    @Override
    public void closeOverlay(int id) {
        PacketHandler.Instance.sendToPlayer(new ScriptOverlayClosePacket(id), (EntityPlayerMP)this.entity);
    }

    @Override
    public IOverlayHandler getOverlays() {
        return this.getData().skinOverlays;
    }

    @Override
    public IAnimationData getAnimationData() {
        return this.getData().animationData;
    }

    @Override
    public IQuest[] getFinishedQuests() {
        PlayerQuestData data = this.getData().questData;
        ArrayList<IQuest> quests = new ArrayList<IQuest>();
        for (int id : data.finishedQuests.keySet()) {
            IQuest quest = QuestController.Instance.quests.get(id);
            if (quest == null) continue;
            quests.add(quest);
        }
        return quests.toArray(new IQuest[quests.size()]);
    }

    @Override
    public void setConqueredEnd(boolean conqueredEnd) {
        ((EntityPlayerMP)this.player).field_71136_j = conqueredEnd;
    }

    @Override
    public boolean conqueredEnd() {
        return ((EntityPlayerMP)this.player).field_71136_j;
    }

    @Override
    public IMagicData getMagicData() {
        return this.getData().magicData;
    }

    @Override
    public IPlayerAttributes getAttributes() {
        return AttributeController.getTracker(this.player);
    }

    @Override
    public IActionManager getActionManager() {
        if (this.getData().actionManager.getName().isEmpty()) {
            this.data.actionManager.setName(this.player.getDisplayName());
        }
        return this.data.actionManager;
    }

    @Override
    public IPlayer[] getPartyMembers() {
        Party party = this.getData().getPlayerParty();
        if (party == null) {
            throw new CustomNPCsException("Player is not in party", new Object[0]);
        }
        ArrayList list = new ArrayList();
        for (int i = 0; i < party.getPlayerNamesList().size(); ++i) {
            IPlayer<?> player = NpcAPI.Instance().getPlayer(party.getPlayerNamesList().get(i));
            if (player == null) continue;
            list.add(player);
        }
        return list.toArray(new IPlayer[list.size()]);
    }

    @Override
    public long getCurrencyBalance() {
        return this.getData().tradeData.getBalance();
    }

    @Override
    public void setCurrencyBalance(long amount) {
        this.getData().tradeData.setBalance(amount);
    }

    @Override
    public boolean depositCurrency(long amount) {
        return this.getData().tradeData.deposit(amount);
    }

    @Override
    public boolean withdrawCurrency(long amount) {
        return this.getData().tradeData.withdraw(amount);
    }

    @Override
    public boolean canAffordCurrency(long amount) {
        return this.getData().tradeData.canAfford(amount);
    }

    @Override
    public boolean isUsingVaultCurrency() {
        return this.getData().tradeData.isUsingVault();
    }

    @Override
    public String getFormattedCurrencyBalance() {
        return this.getData().tradeData.formatBalance();
    }

    @Override
    public IEnergyProjectile[] getActiveEnergyProjectiles() {
        List<EntityEnergyProjectile> entities = EntityEnergyProjectile.getActiveProjectiles(this.player.func_145782_y());
        IEnergyProjectile[] result = new IEnergyProjectile[entities.size()];
        for (int i = 0; i < entities.size(); ++i) {
            result[i] = (IEnergyProjectile)NpcAPI.Instance().getIEntity(entities.get(i));
        }
        return result;
    }
}

