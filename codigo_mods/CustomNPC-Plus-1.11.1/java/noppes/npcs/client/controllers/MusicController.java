/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.audio.ISound
 *  net.minecraft.client.audio.ISound$AttenuationType
 *  net.minecraft.client.audio.SoundHandler
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.ResourceLocation
 */
package noppes.npcs.client.controllers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import noppes.npcs.client.controllers.ScriptClientSound;

public class MusicController {
    public static MusicController Instance;
    private final HashMap<String, ScriptClientSound> sounds = new HashMap();
    private int playDelay;
    public ScriptClientSound playingSound;
    private Entity entity;
    private int offRange;

    public MusicController() {
        Instance = this;
    }

    public void onUpdate() {
        if (this.playingSound != null && !Minecraft.func_71410_x().func_147118_V().func_147692_c((ISound)this.playingSound)) {
            Entity playingEntity = this.playingSound.getEntity();
            String sound = this.playingSound.sound;
            this.playingSound.stopSound();
            this.stopMusic();
            if (playingEntity == Minecraft.func_71410_x().field_71439_g) {
                this.playMusicBackground(sound, this.entity, this.offRange);
            } else {
                this.playMusicJukebox(sound, this.entity, this.offRange);
            }
        }
        SoundHandler soundHandler = Minecraft.func_71410_x().func_147118_V();
        HashSet<String> keys = new HashSet<String>();
        for (Map.Entry<String, ScriptClientSound> entry : this.sounds.entrySet()) {
            ScriptClientSound sound = entry.getValue();
            if (soundHandler.func_147692_c((ISound)sound) || sound.func_147657_c() || sound.paused) continue;
            keys.add(entry.getKey());
        }
        for (String key : keys) {
            this.sounds.remove(key);
        }
        if (this.playDelay > 0) {
            --this.playDelay;
        }
    }

    public void stopMusic() {
        SoundHandler soundHandler = Minecraft.func_71410_x().func_147118_V();
        if (this.playingSound != null) {
            if (soundHandler != null) {
                try {
                    soundHandler.func_147683_b((ISound)this.playingSound);
                }
                catch (Exception exception) {
                    // empty catch block
                }
            }
            this.playingSound.stopSound();
            this.sounds.remove(this.playingSound.sound);
            this.playingSound = null;
            this.entity = null;
            this.playDelay = 20;
        } else if (soundHandler != null) {
            try {
                soundHandler.func_147690_c();
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
    }

    public void playMusicJukebox(String music, Entity entity, int offRange) {
        if (this.playDelay > 0) {
            return;
        }
        this.playDelay = 20;
        if (this.isPlaying(music)) {
            return;
        }
        this.stopMusic();
        ScriptClientSound clientSound = new ScriptClientSound(music);
        clientSound.setEntity(entity);
        clientSound.setVolume(Math.max(1.0f, (float)offRange / 16.0f));
        clientSound.setAttenuationType(ISound.AttenuationType.LINEAR);
        clientSound.setRepeat(true);
        this.playingSound = clientSound;
        this.entity = entity;
        this.offRange = offRange;
        this.sounds.put(clientSound.sound, clientSound);
        try {
            SoundHandler soundHandler = Minecraft.func_71410_x().func_147118_V();
            soundHandler.func_147682_a((ISound)clientSound);
        }
        catch (Exception e) {
            this.stopAllSounds();
        }
    }

    public void playMusicBackground(String music, Entity entity, int offRange) {
        if (this.playDelay > 0) {
            return;
        }
        this.playDelay = 20;
        if (this.isPlaying(music)) {
            return;
        }
        this.stopMusic();
        ScriptClientSound clientSound = new ScriptClientSound(music);
        clientSound.setEntity((Entity)Minecraft.func_71410_x().field_71439_g);
        clientSound.setRepeat(true);
        this.playingSound = clientSound;
        this.entity = entity;
        this.offRange = offRange;
        this.sounds.put(clientSound.sound, clientSound);
        try {
            SoundHandler soundHandler = Minecraft.func_71410_x().func_147118_V();
            soundHandler.func_147682_a((ISound)clientSound);
        }
        catch (Exception e) {
            this.stopAllSounds();
        }
    }

    public void playSound(String music, float x, float y, float z) {
        if (this.playDelay > 0) {
            return;
        }
        this.playDelay = 20;
        ScriptClientSound clientSound = new ScriptClientSound(music);
        clientSound.setPos(x, y, z);
        clientSound.setVolume(1.0f);
        clientSound.setAttenuationType(ISound.AttenuationType.NONE);
        clientSound.setRepeat(false);
        try {
            SoundHandler soundHandler = Minecraft.func_71410_x().func_147118_V();
            soundHandler.func_147682_a((ISound)clientSound);
        }
        catch (Exception e) {
            this.stopAllSounds();
            return;
        }
        this.sounds.put(clientSound.sound, clientSound);
    }

    public boolean isPlaying(String music) {
        if (this.sounds.containsKey(music)) {
            return true;
        }
        if (this.playingSound != null && this.playingSound.sound.equals(music)) {
            ResourceLocation resource = new ResourceLocation(music);
            if (!this.playingSound.func_147650_b().equals((Object)resource)) {
                return false;
            }
            return Minecraft.func_71410_x().func_147118_V().func_147692_c((ISound)this.playingSound);
        }
        return false;
    }

    public boolean isPlaying() {
        return this.playingSound != null;
    }

    public Entity getEntity() {
        return this.entity;
    }

    public int getOffRange() {
        return this.offRange;
    }

    public double getDistance() {
        EntityClientPlayerMP player = Minecraft.func_71410_x().field_71439_g;
        if (this.entity != null && this.entity.field_71093_bK == player.field_71093_bK) {
            return player.func_70032_d(this.entity);
        }
        return Double.MAX_VALUE;
    }

    public void stopAllSounds() {
        this.stopMusic();
        for (ScriptClientSound sound : this.sounds.values()) {
            sound.stopSound();
        }
        this.sounds.clear();
    }

    public void stopSound(String soundKey) {
        if (soundKey == null || soundKey.isEmpty()) {
            return;
        }
        ScriptClientSound sound = this.sounds.remove(soundKey);
        if (sound != null) {
            SoundHandler soundHandler = Minecraft.func_71410_x().func_147118_V();
            if (soundHandler != null) {
                try {
                    soundHandler.func_147683_b((ISound)sound);
                }
                catch (Exception exception) {
                    // empty catch block
                }
            }
            sound.stopSound();
        }
    }
}

