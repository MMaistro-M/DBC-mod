/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.ChunkCoordIntPair
 *  net.minecraftforge.common.ForgeChunkManager
 *  net.minecraftforge.common.ForgeChunkManager$Ticket
 */
package noppes.npcs.roles;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraftforge.common.ForgeChunkManager;
import noppes.npcs.controllers.ChunkController;
import noppes.npcs.entity.EntityNPCInterface;
import noppes.npcs.roles.JobInterface;

public class JobChunkLoader
extends JobInterface {
    private List<ChunkCoordIntPair> chunks = new ArrayList<ChunkCoordIntPair>();
    private int ticks = 20;
    private long playerLastSeen = 0L;

    public JobChunkLoader(EntityNPCInterface npc) {
        super(npc);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.func_74772_a("ChunkPlayerLastSeen", this.playerLastSeen);
        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        this.playerLastSeen = compound.func_74763_f("ChunkPlayerLastSeen");
    }

    @Override
    public boolean aiShouldExecute() {
        --this.ticks;
        if (this.ticks > 0) {
            return false;
        }
        this.ticks = 20;
        List players = this.npc.field_70170_p.func_72872_a(EntityPlayer.class, this.npc.field_70121_D.func_72314_b(48.0, 48.0, 48.0));
        if (!players.isEmpty()) {
            this.playerLastSeen = System.currentTimeMillis();
        }
        if (System.currentTimeMillis() > this.playerLastSeen + 600000L) {
            ChunkController.Instance.deleteNPC(this.npc);
            this.chunks.clear();
            return false;
        }
        ForgeChunkManager.Ticket ticket = ChunkController.Instance.getTicket(this.npc);
        if (ticket == null) {
            return false;
        }
        double x = this.npc.field_70165_t / 16.0;
        double z = this.npc.field_70161_v / 16.0;
        ArrayList<ChunkCoordIntPair> list = new ArrayList<ChunkCoordIntPair>();
        list.add(new ChunkCoordIntPair(MathHelper.func_76128_c((double)x), MathHelper.func_76128_c((double)z)));
        list.add(new ChunkCoordIntPair(MathHelper.func_76143_f((double)x), MathHelper.func_76143_f((double)z)));
        list.add(new ChunkCoordIntPair(MathHelper.func_76128_c((double)x), MathHelper.func_76143_f((double)z)));
        list.add(new ChunkCoordIntPair(MathHelper.func_76143_f((double)x), MathHelper.func_76128_c((double)z)));
        for (ChunkCoordIntPair chunk : list) {
            if (!this.chunks.contains(chunk)) {
                ForgeChunkManager.forceChunk((ForgeChunkManager.Ticket)ticket, (ChunkCoordIntPair)chunk);
                continue;
            }
            this.chunks.remove(chunk);
        }
        for (ChunkCoordIntPair chunk : this.chunks) {
            ForgeChunkManager.unforceChunk((ForgeChunkManager.Ticket)ticket, (ChunkCoordIntPair)chunk);
        }
        this.chunks = list;
        return false;
    }

    @Override
    public void reset() {
        ChunkController.Instance.deleteNPC(this.npc);
        this.chunks.clear();
        this.playerLastSeen = 0L;
    }

    @Override
    public void delete() {
    }
}

