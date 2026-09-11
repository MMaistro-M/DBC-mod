/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.audio.ISound
 *  net.minecraft.client.audio.SoundHandler
 */
package noppes.npcs.client.controllers;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.SoundHandler;
import noppes.npcs.client.controllers.ScriptClientSound;

public class ScriptSoundController {
    public HashMap<Integer, ScriptClientSound> sounds = new HashMap();
    public static ScriptSoundController Instance;

    public ScriptSoundController() {
        Instance = this;
    }

    public void onUpdate() {
        SoundHandler soundHandler = Minecraft.func_71410_x().func_147118_V();
        Iterator<Map.Entry<Integer, ScriptClientSound>> iterator = this.sounds.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, ScriptClientSound> entry = iterator.next();
            ScriptClientSound sound = entry.getValue();
            if (soundHandler.func_147692_c((ISound)sound) || sound.func_147657_c() || sound.paused) continue;
            iterator.remove();
        }
    }

    public void playSound(int id, ScriptClientSound sound) {
        SoundHandler soundHandler = Minecraft.func_71410_x().func_147118_V();
        if (this.sounds.containsKey(id)) {
            soundHandler.func_147683_b((ISound)sound);
            this.sounds.get(id).stopSound();
        }
        this.sounds.put(id, sound);
        soundHandler.func_147682_a((ISound)sound);
    }

    public void playSound(ScriptClientSound sound) {
        SoundHandler soundHandler = Minecraft.func_71410_x().func_147118_V();
        soundHandler.func_147682_a((ISound)sound);
    }

    public void stopSound(int id) {
        SoundHandler soundHandler = Minecraft.func_71410_x().func_147118_V();
        if (this.sounds.containsKey(id)) {
            soundHandler.func_147683_b((ISound)this.sounds.get(id));
            this.sounds.get(id).stopSound();
            this.sounds.remove(id);
        }
    }

    public void pauseAllSounds() {
        SoundHandler soundHandler = Minecraft.func_71410_x().func_147118_V();
        soundHandler.func_147689_b();
        for (ScriptClientSound sound : this.sounds.values()) {
            sound.paused = true;
        }
    }

    public void continueAllSounds() {
        SoundHandler soundHandler = Minecraft.func_71410_x().func_147118_V();
        soundHandler.func_147687_e();
        for (ScriptClientSound sound : this.sounds.values()) {
            sound.paused = false;
        }
    }

    public void stopAllSounds() {
        SoundHandler soundHandler = Minecraft.func_71410_x().func_147118_V();
        soundHandler.func_147690_c();
        this.sounds.clear();
    }
}

