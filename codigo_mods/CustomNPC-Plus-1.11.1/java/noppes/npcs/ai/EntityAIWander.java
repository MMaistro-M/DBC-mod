/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.IEntitySelector
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.Vec3
 */
package noppes.npcs.ai;

import java.util.Iterator;
import java.util.List;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import noppes.npcs.ai.RandomPositionGeneratorAlt;
import noppes.npcs.ai.selector.NPCInteractSelector;
import noppes.npcs.config.ConfigMain;
import noppes.npcs.constants.AiMutex;
import noppes.npcs.controllers.data.Line;
import noppes.npcs.entity.EntityNPCInterface;

public class EntityAIWander
extends EntityAIBase {
    private EntityNPCInterface entity;
    public final IEntitySelector selector;
    private double xPosition;
    private double yPosition;
    private double zPosition;
    private EntityNPCInterface nearbyNPC;

    public EntityAIWander(EntityNPCInterface npc) {
        this.entity = npc;
        this.func_75248_a(AiMutex.PASSIVE);
        this.selector = new NPCInteractSelector(npc);
    }

    public boolean func_75250_a() {
        if (this.entity.func_70654_ax() >= 100 || !this.entity.func_70661_as().func_75500_f() || this.entity.isInteracting() || this.entity.func_70681_au().nextInt(80) != 0) {
            return false;
        }
        if (this.entity.ais.npcInteracting && this.entity.func_70681_au().nextInt(4) == 1) {
            this.nearbyNPC = this.getNearbyNPC();
        }
        if (this.nearbyNPC != null) {
            this.xPosition = MathHelper.func_76128_c((double)this.nearbyNPC.field_70165_t);
            this.yPosition = MathHelper.func_76128_c((double)this.nearbyNPC.field_70163_u);
            this.zPosition = MathHelper.func_76128_c((double)this.nearbyNPC.field_70161_v);
            this.nearbyNPC.addInteract((EntityLivingBase)this.entity);
        } else {
            Vec3 vec = this.getVec();
            if (vec == null) {
                return false;
            }
            this.xPosition = vec.field_72450_a;
            this.yPosition = vec.field_72448_b;
            if (this.entity.canFly()) {
                this.yPosition = this.entity.getStartYPos() + (double)this.entity.func_70681_au().nextFloat() * 0.75 * (double)this.entity.ais.walkingRange;
            }
            this.zPosition = vec.field_72449_c;
        }
        return true;
    }

    public void func_75246_d() {
        if (this.nearbyNPC != null) {
            this.nearbyNPC.func_70661_as().func_75499_g();
        }
    }

    private EntityNPCInterface getNearbyNPC() {
        List list = this.entity.field_70170_p.func_94576_a((Entity)this.entity, this.entity.field_70121_D.func_72314_b((double)this.entity.ais.walkingRange, this.entity.ais.walkingRange > 7 ? 7.0 : (double)this.entity.ais.walkingRange, (double)this.entity.ais.walkingRange), this.selector);
        Iterator ita = list.iterator();
        while (ita.hasNext()) {
            EntityNPCInterface npc = (EntityNPCInterface)((Object)ita.next());
            if (npc.ais.stopAndInteract && !npc.isAttacking() && npc.func_70089_S() && !this.entity.faction.isAggressiveToNpc(npc)) continue;
            ita.remove();
        }
        if (list.isEmpty()) {
            return null;
        }
        return (EntityNPCInterface)((Object)list.get(this.entity.func_70681_au().nextInt(list.size())));
    }

    private Vec3 getVec() {
        if (this.entity.ais.walkingRange > 0) {
            double distance = this.entity.func_70092_e(this.entity.getStartXPos(), this.entity.getStartYPos(), this.entity.getStartZPos());
            int range = (int)MathHelper.func_76133_a((double)((double)(this.entity.ais.walkingRange * this.entity.ais.walkingRange) - distance));
            if (range > ConfigMain.NpcNavRange) {
                range = ConfigMain.NpcNavRange;
            }
            if (range < 3) {
                range = this.entity.ais.walkingRange;
                if (range > ConfigMain.NpcNavRange) {
                    range = ConfigMain.NpcNavRange;
                }
                Vec3 start = Vec3.func_72443_a((double)this.entity.getStartXPos(), (double)this.entity.getStartYPos(), (double)this.entity.getStartZPos());
                return RandomPositionGeneratorAlt.findRandomTargetBlockTowards(this.entity, range / 2, range / 2 > 7 ? 7 : range / 2, start);
            }
            return RandomPositionGeneratorAlt.findRandomTarget(this.entity, range, range / 2 > 7 ? 7 : range / 2);
        }
        return RandomPositionGeneratorAlt.findRandomTarget(this.entity, ConfigMain.NpcNavRange, 7);
    }

    public boolean func_75253_b() {
        if (this.nearbyNPC != null && (!this.selector.func_82704_a((Entity)this.nearbyNPC) || this.entity.func_70068_e((Entity)this.nearbyNPC) < (double)this.entity.field_70130_N * 1.5)) {
            return false;
        }
        return !this.entity.func_70661_as().func_75500_f() && this.entity.func_70089_S() && !this.entity.isInteracting();
    }

    public void func_75249_e() {
        this.entity.func_70661_as().func_75492_a(this.xPosition, this.yPosition, this.zPosition, 1.0);
    }

    public void func_75251_c() {
        if (this.nearbyNPC != null && this.entity.func_70068_e((Entity)this.nearbyNPC) < 12.0) {
            Line line = new Line(".........");
            line.hideText = true;
            if (this.entity.func_70681_au().nextBoolean()) {
                this.entity.saySurrounding(line);
            } else {
                this.nearbyNPC.saySurrounding(line);
            }
            this.entity.addInteract((EntityLivingBase)this.nearbyNPC);
            this.nearbyNPC.addInteract((EntityLivingBase)this.entity);
        }
        this.nearbyNPC = null;
    }
}

