/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.ai.EntityAIBase
 */
package noppes.npcs.ai;

import java.util.List;
import net.minecraft.entity.ai.EntityAIBase;
import noppes.npcs.constants.AiMutex;
import noppes.npcs.entity.EntityNPCInterface;

public class EntityAIMovingPath
extends EntityAIBase {
    private EntityNPCInterface npc;
    private int[] pos;

    public EntityAIMovingPath(EntityNPCInterface par1EntityNPCInterface) {
        this.npc = par1EntityNPCInterface;
        this.func_75248_a(AiMutex.PASSIVE);
    }

    public boolean func_75250_a() {
        if (this.npc.isAttacking() || this.npc.isInteracting() || this.npc.func_70681_au().nextInt(40) != 0 && this.npc.ais.movingPause || !this.npc.func_70661_as().func_75500_f() || this.npc.isInteracting()) {
            return false;
        }
        List<int[]> list = this.npc.ais.getMovingPath();
        if (list.size() < 2) {
            return false;
        }
        this.npc.ais.incrementMovingPath();
        this.pos = this.npc.ais.getCurrentMovingPath();
        return true;
    }

    public boolean func_75253_b() {
        if (this.npc.isAttacking() || this.npc.isInteracting()) {
            this.npc.ais.decreaseMovingPath();
            return false;
        }
        return !this.npc.func_70661_as().func_75500_f();
    }

    public void func_75249_e() {
        this.npc.func_70661_as().func_75492_a((double)this.pos[0] + 0.5, (double)this.pos[1], (double)this.pos[2] + 0.5, 1.0);
    }
}

