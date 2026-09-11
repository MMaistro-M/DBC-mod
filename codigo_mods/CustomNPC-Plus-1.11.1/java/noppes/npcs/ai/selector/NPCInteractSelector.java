/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.IEntitySelector
 *  net.minecraft.entity.Entity
 */
package noppes.npcs.ai.selector;

import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import noppes.npcs.entity.EntityNPCInterface;

public class NPCInteractSelector
implements IEntitySelector {
    private EntityNPCInterface npc;

    public NPCInteractSelector(EntityNPCInterface npc) {
        this.npc = npc;
    }

    public boolean func_82704_a(Entity entity) {
        if (entity == this.npc || !(entity instanceof EntityNPCInterface) || !this.npc.func_70089_S()) {
            return false;
        }
        EntityNPCInterface selected = (EntityNPCInterface)entity;
        return !selected.isAttacking() && !this.npc.getFaction().isAggressiveToNpc(selected) && this.npc.ais.stopAndInteract;
    }
}

