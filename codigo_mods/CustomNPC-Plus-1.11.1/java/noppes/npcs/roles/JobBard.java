/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.roles;

import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.CustomItems;
import noppes.npcs.CustomNpcs;
import noppes.npcs.client.controllers.MusicController;
import noppes.npcs.constants.EnumBardInstrument;
import noppes.npcs.entity.EntityNPCInterface;
import noppes.npcs.roles.JobInterface;

public class JobBard
extends JobInterface {
    public int minRange = 2;
    public int maxRange = 64;
    public boolean isStreamer = true;
    public boolean hasOffRange = true;
    public String song = "";
    private EnumBardInstrument instrument = EnumBardInstrument.Banjo;

    public JobBard(EntityNPCInterface npc) {
        super(npc);
        if (CustomItems.banjo != null) {
            this.mainhand = new ItemStack(CustomItems.banjo);
            this.overrideOffHand = true;
            this.overrideMainHand = true;
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbttagcompound) {
        nbttagcompound.func_74778_a("BardSong", this.song);
        nbttagcompound.func_74768_a("BardMinRange", this.minRange);
        nbttagcompound.func_74768_a("BardMaxRange", this.maxRange);
        nbttagcompound.func_74768_a("BardInstrument", this.instrument.ordinal());
        nbttagcompound.func_74757_a("BardStreamer", this.isStreamer);
        nbttagcompound.func_74757_a("BardHasOff", this.hasOffRange);
        return nbttagcompound;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbttagcompound) {
        this.song = nbttagcompound.func_74779_i("BardSong");
        this.minRange = nbttagcompound.func_74762_e("BardMinRange");
        this.maxRange = nbttagcompound.func_74762_e("BardMaxRange");
        this.setInstrument(nbttagcompound.func_74762_e("BardInstrument"));
        this.isStreamer = nbttagcompound.func_74767_n("BardStreamer");
        this.hasOffRange = nbttagcompound.func_74767_n("BardHasOff");
    }

    public void setInstrument(int i) {
        if (CustomItems.banjo == null) {
            return;
        }
        this.instrument = EnumBardInstrument.values()[i];
        this.overrideOffHand = this.instrument != EnumBardInstrument.None;
        this.overrideMainHand = this.overrideOffHand;
        switch (this.instrument) {
            case None: {
                this.mainhand = null;
                this.offhand = null;
                break;
            }
            case Banjo: {
                this.mainhand = new ItemStack(CustomItems.banjo);
                this.offhand = null;
                break;
            }
            case Violin: {
                this.mainhand = new ItemStack(CustomItems.violin);
                this.offhand = new ItemStack(CustomItems.violinbow);
                break;
            }
            case Guitar: {
                this.mainhand = new ItemStack(CustomItems.guitar);
                this.offhand = null;
                break;
            }
            case Harp: {
                this.mainhand = new ItemStack(CustomItems.harp);
                this.offhand = null;
                break;
            }
            case FrenchHorn: {
                this.mainhand = new ItemStack(CustomItems.frenchHorn);
                this.offhand = null;
            }
        }
    }

    public EnumBardInstrument getInstrument() {
        return this.instrument;
    }

    public void onLivingUpdate() {
        List list;
        if (!this.npc.isRemote() || this.song.isEmpty()) {
            return;
        }
        if (!MusicController.Instance.isPlaying() && Minecraft.func_71410_x().field_71462_r == null) {
            if (!this.play()) {
                return;
            }
        } else if (MusicController.Instance.getEntity() != this.npc) {
            EntityPlayer player = CustomNpcs.proxy.getPlayer();
            double distanceToPlayer = this.npc.func_70032_d((Entity)player);
            double distanceToMusic = MusicController.Instance.getDistance();
            if (Math.ceil(distanceToPlayer) < Math.ceil(distanceToMusic) && !this.play()) {
                return;
            }
        } else if (this.hasOffRange && !(list = this.npc.field_70170_p.func_72872_a(EntityPlayer.class, this.npc.field_70121_D.func_72314_b((double)this.maxRange, (double)this.maxRange, (double)this.maxRange))).contains(CustomNpcs.proxy.getPlayer())) {
            MusicController.Instance.stopMusic();
        }
        if (MusicController.Instance.isPlaying(this.song)) {
            Minecraft.func_71410_x().field_147126_aw.field_147676_d = 12000;
        }
    }

    private boolean play() {
        List list = this.npc.field_70170_p.func_72872_a(EntityPlayer.class, this.npc.field_70121_D.func_72314_b((double)this.minRange, (double)this.minRange, (double)this.minRange));
        if (!list.contains(CustomNpcs.proxy.getPlayer())) {
            return false;
        }
        if (this.isStreamer) {
            MusicController.Instance.playMusicJukebox(this.song, (Entity)this.npc, this.hasOffRange ? this.maxRange : 0);
        } else {
            MusicController.Instance.playMusicBackground(this.song, (Entity)this.npc, this.hasOffRange ? this.maxRange : 0);
        }
        return true;
    }

    @Override
    public void killed() {
        this.delete();
    }

    @Override
    public void delete() {
        if (this.npc.field_70170_p.field_72995_K && this.hasOffRange && MusicController.Instance.isPlaying(this.song)) {
            MusicController.Instance.stopAllSounds();
        }
    }
}

