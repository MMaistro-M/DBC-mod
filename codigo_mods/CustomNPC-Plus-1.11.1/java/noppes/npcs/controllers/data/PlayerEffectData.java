/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 */
package noppes.npcs.controllers.data;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import noppes.npcs.controllers.data.EffectKey;
import noppes.npcs.controllers.data.PlayerData;
import noppes.npcs.controllers.data.PlayerEffect;

public class PlayerEffectData {
    private final PlayerData parent;
    private ConcurrentHashMap<EffectKey, PlayerEffect> effects = new ConcurrentHashMap();

    public PlayerEffectData(PlayerData playerData) {
        this.parent = playerData;
    }

    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        NBTTagList list = new NBTTagList();
        for (Map.Entry<EffectKey, PlayerEffect> entry : this.effects.entrySet()) {
            NBTTagCompound effectCompound = new NBTTagCompound();
            effectCompound.func_74768_a("id", entry.getKey().getId());
            effectCompound.func_74768_a("index", entry.getKey().getIndex());
            effectCompound.func_74768_a("duration", entry.getValue().duration);
            effectCompound.func_74774_a("level", entry.getValue().level);
            list.func_74742_a((NBTBase)effectCompound);
        }
        compound.func_74782_a("Effects", (NBTBase)list);
        return compound;
    }

    public void readFromNBT(NBTTagCompound compound) {
        HashSet<EffectKey> newKeys = new HashSet<EffectKey>();
        if (compound.func_74764_b("Effects")) {
            NBTTagList list = compound.func_150295_c("Effects", 10);
            for (int i = 0; i < list.func_74745_c(); ++i) {
                NBTTagCompound effectCompound = list.func_150305_b(i);
                int id = effectCompound.func_74762_e("id");
                int index = effectCompound.func_74762_e("index");
                int duration = effectCompound.func_74762_e("duration");
                byte level = effectCompound.func_74771_c("level");
                EffectKey key = new EffectKey(id, index);
                newKeys.add(key);
                PlayerEffect effect = this.effects.get(key);
                if (effect != null) {
                    effect.duration = duration;
                    effect.level = level;
                    continue;
                }
                effect = new PlayerEffect(id, duration, level, index);
                this.effects.put(key, effect);
            }
        }
        Iterator it = ((ConcurrentHashMap.KeySetView)this.effects.keySet()).iterator();
        while (it.hasNext()) {
            EffectKey key = (EffectKey)it.next();
            if (newKeys.contains(key)) continue;
            it.remove();
        }
    }

    public ConcurrentHashMap<EffectKey, PlayerEffect> getEffects() {
        return this.effects;
    }

    public void setEffects(ConcurrentHashMap<EffectKey, PlayerEffect> effects) {
        this.effects = effects;
    }

    public PlayerEffect getPlayerEffect(int id, int index) {
        return this.effects.get(new EffectKey(id, index));
    }

    public PlayerEffect getPlayerEffect(int id) {
        return this.getPlayerEffect(id, 0);
    }

    public boolean hasPlayerEffect(int id, int index) {
        return this.effects.containsKey(new EffectKey(id, index));
    }

    public boolean hasPlayerEffect(int id) {
        return this.hasPlayerEffect(id, 0);
    }
}

