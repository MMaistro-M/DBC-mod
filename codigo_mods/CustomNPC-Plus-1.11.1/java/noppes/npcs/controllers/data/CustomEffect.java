/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.Event
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.controllers.data;

import cpw.mods.fml.common.eventhandler.Event;
import java.util.HashSet;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.EventHooks;
import noppes.npcs.NoppesUtilServer;
import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.api.handler.data.ICustomEffect;
import noppes.npcs.constants.EnumScriptType;
import noppes.npcs.controllers.CustomEffectController;
import noppes.npcs.controllers.TagController;
import noppes.npcs.controllers.data.EffectScript;
import noppes.npcs.controllers.data.PlayerEffect;
import noppes.npcs.scripted.event.player.PlayerEvent;

public class CustomEffect
implements ICustomEffect {
    public int id = -1;
    public String name = "";
    public boolean lossOnDeath = true;
    public int length = 30;
    public int everyXTick = 20;
    public String icon = "";
    public int iconX = 0;
    public int iconY = 0;
    public String menuName = "\u00a7aNEW EFFECT";
    public int width = 16;
    public int height = 16;
    public boolean animated = false;
    public int frameCount = 1;
    public int frametime = 2;
    public int index = 0;
    public HashSet<UUID> tagUUIDs = new HashSet();

    public CustomEffect() {
    }

    public CustomEffect(int id) {
        this.id = id;
    }

    public CustomEffect(int id, String name) {
        this(id);
        this.name = name;
    }

    @Override
    public int getID() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setMenuName(String name) {
        if (name != null && !name.isEmpty()) {
            this.menuName = name.replaceAll("&", "\u00a7");
        }
    }

    @Override
    public String getMenuName() {
        return this.menuName;
    }

    @Override
    public void setName(String name) {
        if (name != null && !name.isEmpty()) {
            this.name = name;
        }
    }

    @Override
    public String getIcon() {
        return this.icon;
    }

    @Override
    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public int getEveryXTick() {
        return this.everyXTick;
    }

    @Override
    public void setEveryXTick(int everyXTick) {
        int remainder;
        if (everyXTick < 10) {
            everyXTick = 10;
        }
        everyXTick = (remainder = everyXTick % 10) >= 5 ? (everyXTick += 10 - remainder) : (everyXTick -= remainder);
        this.everyXTick = everyXTick;
    }

    @Override
    public int getIconX() {
        return this.iconX;
    }

    @Override
    public void setIconX(int iconX) {
        this.iconX = iconX;
    }

    @Override
    public int getIconY() {
        return this.iconY;
    }

    @Override
    public void setIconY(int iconY) {
        this.iconY = iconY;
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    @Override
    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public boolean isAnimated() {
        return this.animated;
    }

    @Override
    public void setAnimated(boolean animated) {
        this.animated = animated;
    }

    @Override
    public int getFrameCount() {
        return this.frameCount;
    }

    @Override
    public void setFrameCount(int frameCount) {
        this.frameCount = Math.max(1, frameCount);
    }

    @Override
    public int getFrameTime() {
        return this.frametime;
    }

    @Override
    public void setFrameTime(int frametime) {
        this.frametime = Math.max(1, frametime);
    }

    @Override
    public boolean isLossOnDeath() {
        return this.lossOnDeath;
    }

    @Override
    public void setLossOnDeath(boolean lossOnDeath) {
        this.lossOnDeath = lossOnDeath;
    }

    @Override
    public ICustomEffect save() {
        return CustomEffectController.getInstance().saveEffect(this);
    }

    @Override
    public void setID(int id) {
        this.id = id;
    }

    @Override
    public int getIndex() {
        return this.index;
    }

    public void onAdded(EntityPlayer player, PlayerEffect playerEffect) {
        IPlayer iPlayer = NoppesUtilServer.getIPlayer(player);
        if (playerEffect.index == 0) {
            PlayerEvent.EffectEvent.Added event = new PlayerEvent.EffectEvent.Added(iPlayer, playerEffect);
            EffectScript script = this.getScriptHandler();
            if (script == null) {
                return;
            }
            script.callScript(EnumScriptType.ON_EFFECT_ADD, (Event)event);
        }
        EventHooks.onEffectAdded(iPlayer, playerEffect);
    }

    public void onTick(EntityPlayer player, PlayerEffect playerEffect) {
        IPlayer iPlayer = NoppesUtilServer.getIPlayer(player);
        if (playerEffect.index == 0) {
            PlayerEvent.EffectEvent.Ticked event = new PlayerEvent.EffectEvent.Ticked(iPlayer, playerEffect);
            EffectScript script = this.getScriptHandler();
            if (script == null) {
                return;
            }
            script.callScript(EnumScriptType.ON_EFFECT_TICK, (Event)event);
        }
        EventHooks.onEffectTick(iPlayer, playerEffect);
    }

    public void onRemoved(EntityPlayer player, PlayerEffect playerEffect, PlayerEvent.EffectEvent.ExpirationType type) {
        IPlayer iPlayer = NoppesUtilServer.getIPlayer(player);
        if (playerEffect.index == 0) {
            PlayerEvent.EffectEvent.Removed event = new PlayerEvent.EffectEvent.Removed(iPlayer, playerEffect, type);
            EffectScript script = this.getScriptHandler();
            if (script == null) {
                return;
            }
            script.callScript(EnumScriptType.ON_EFFECT_REMOVE, (Event)event);
        }
        EventHooks.onEffectRemove(iPlayer, playerEffect, type);
    }

    public NBTTagCompound writeToNBT(boolean saveScripts) {
        NBTTagCompound compound = new NBTTagCompound();
        compound.func_74768_a("ID", this.id);
        compound.func_74778_a("name", this.name);
        compound.func_74778_a("menuName", this.menuName);
        compound.func_74768_a("length", this.length);
        compound.func_74768_a("everyXTick", this.everyXTick);
        compound.func_74768_a("iconX", this.iconX);
        compound.func_74768_a("iconY", this.iconY);
        compound.func_74768_a("iconWidth", this.width);
        compound.func_74768_a("iconHeight", this.height);
        compound.func_74778_a("icon", this.icon);
        compound.func_74757_a("lossOnDeath", this.lossOnDeath);
        compound.func_74757_a("iconAnimated", this.animated);
        compound.func_74768_a("iconFrameCount", this.frameCount);
        compound.func_74768_a("iconFrameTime", this.frametime);
        TagController.writeTagUUIDs(compound, "TagUUIDs", this.tagUUIDs);
        if (saveScripts) {
            NBTTagCompound scriptData = new NBTTagCompound();
            EffectScript handler = this.getScriptHandler();
            if (handler != null) {
                handler.writeToNBT(scriptData);
            }
            compound.func_74782_a("ScriptData", (NBTBase)scriptData);
        }
        return compound;
    }

    public void readFromNBT(NBTTagCompound compound) {
        this.id = compound.func_74764_b("ID") ? compound.func_74762_e("ID") : CustomEffectController.Instance.getUnusedId();
        this.name = compound.func_74779_i("name");
        this.menuName = compound.func_150297_b("menuName", 8) ? compound.func_74779_i("menuName") : this.name;
        this.length = compound.func_74762_e("length");
        this.everyXTick = compound.func_74762_e("everyXTick");
        this.iconX = compound.func_74762_e("iconX");
        this.iconY = compound.func_74762_e("iconY");
        this.width = compound.func_150297_b("iconWidth", 3) ? compound.func_74762_e("iconWidth") : 16;
        this.height = compound.func_150297_b("iconHeight", 3) ? compound.func_74762_e("iconHeight") : 16;
        this.icon = compound.func_74779_i("icon");
        this.lossOnDeath = compound.func_74767_n("lossOnDeath");
        if (compound.func_74764_b("iconAnimated")) {
            this.animated = compound.func_74767_n("iconAnimated");
        }
        if (compound.func_74764_b("iconFrameCount")) {
            this.frameCount = Math.max(1, compound.func_74762_e("iconFrameCount"));
        }
        if (compound.func_74764_b("iconFrameTime")) {
            this.frametime = Math.max(1, compound.func_74762_e("iconFrameTime"));
        }
        this.tagUUIDs = TagController.readTagUUIDs(compound, "TagUUIDs");
        if (compound.func_150297_b("ScriptData", 10)) {
            EffectScript handler = new EffectScript();
            handler.readFromNBT(compound.func_74775_l("ScriptData"));
            this.setScriptHandler(handler);
        }
    }

    public CustomEffect cloneEffect() {
        CustomEffect newEffect = new CustomEffect();
        newEffect.readFromNBT(this.writeToNBT(true));
        newEffect.id = -1;
        return newEffect;
    }

    public EffectScript getScriptHandler() {
        return CustomEffectController.getInstance().customEffectScriptHandlers.get(this.id);
    }

    public void setScriptHandler(EffectScript handler) {
        CustomEffectController.getInstance().customEffectScriptHandlers.put(this.id, handler);
    }

    public EffectScript getOrCreateScriptHandler() {
        EffectScript data = this.getScriptHandler();
        if (data == null) {
            data = new EffectScript();
            this.setScriptHandler(data);
        }
        return data;
    }

    public void runEffect(EntityPlayer player, PlayerEffect playerEffect) {
        if (player.field_70173_aa % this.everyXTick == 0) {
            this.onTick(player, playerEffect);
        }
    }
}

