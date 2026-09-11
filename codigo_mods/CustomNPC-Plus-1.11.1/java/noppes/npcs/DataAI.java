/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.MathHelper
 */
package noppes.npcs;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import noppes.npcs.NBTTags;
import noppes.npcs.constants.EnumAnimation;
import noppes.npcs.constants.EnumCombatPolicy;
import noppes.npcs.constants.EnumMovingType;
import noppes.npcs.constants.EnumNavType;
import noppes.npcs.constants.EnumStandingType;
import noppes.npcs.entity.EntityNPCInterface;

public class DataAI {
    private final EntityNPCInterface npc;
    public int onAttack = 0;
    public int doorInteract = 2;
    public int findShelter = 2;
    public int distanceToMelee = 4;
    public int canFireIndirect = 0;
    public boolean canSwim = true;
    public boolean reactsToFire = false;
    public boolean avoidsWater = false;
    public boolean avoidsSun = false;
    public boolean returnToStart = true;
    public boolean directLOS = true;
    public int leapType = 0;
    public boolean canSprint = false;
    public boolean stopAndInteract = true;
    public EnumNavType tacticalVariant = EnumNavType.Default;
    public EnumCombatPolicy combatPolicy = EnumCombatPolicy.Flip;
    public int useRangeMelee = 0;
    public int tacticalRadius = 8;
    public int tacticalChance = 5;
    public int movementType = 0;
    public double flySpeed = 1.0;
    public double flyGravity = 0.0;
    public boolean hasFlyLimit = true;
    public int flyHeightLimit = 2;
    public EnumAnimation animationType = EnumAnimation.NONE;
    public EnumStandingType standingType = EnumStandingType.RotateBody;
    public EnumMovingType movingType = EnumMovingType.Standing;
    public boolean npcInteracting = true;
    public int orientation = 0;
    public float bodyOffsetX = 5.0f;
    public float bodyOffsetY = 5.0f;
    public float bodyOffsetZ = 5.0f;
    public int walkingRange = 10;
    private int moveSpeed = 5;
    private List<int[]> movingPath = new ArrayList<int[]>();
    public int[] startPos;
    public int movingPos = 0;
    public int movingPattern = 0;
    public boolean movingPause = true;

    public DataAI(EntityNPCInterface npc) {
        this.npc = npc;
    }

    public void readToNBT(NBTTagCompound compound) {
        this.canSwim = compound.func_74767_n("CanSwim");
        this.reactsToFire = compound.func_74767_n("ReactsToFire");
        this.avoidsWater = compound.func_74767_n("AvoidsWater");
        this.avoidsSun = compound.func_74767_n("AvoidsSun");
        this.returnToStart = compound.func_74767_n("ReturnToStart");
        this.onAttack = compound.func_74762_e("OnAttack");
        this.doorInteract = compound.func_74762_e("DoorInteract");
        this.findShelter = compound.func_74762_e("FindShelter");
        this.directLOS = compound.func_74767_n("DirectLOS");
        this.leapType = compound.func_74762_e("LeapType");
        this.canSprint = compound.func_74767_n("CanSprint");
        this.canFireIndirect = compound.func_74762_e("FireIndirect");
        this.useRangeMelee = compound.func_74762_e("RangeAndMelee");
        this.distanceToMelee = compound.func_74762_e("DistanceToMelee");
        this.tacticalRadius = compound.func_74762_e("TacticalRadius");
        this.tacticalChance = compound.func_74762_e("TacticalChance");
        this.movingPause = compound.func_74767_n("MovingPause");
        this.npcInteracting = compound.func_74767_n("npcInteracting");
        this.stopAndInteract = compound.func_74767_n("stopAndInteract");
        this.animationType = EnumAnimation.values()[compound.func_74762_e("MoveState") % EnumAnimation.values().length];
        this.standingType = EnumStandingType.values()[compound.func_74762_e("StandingState") % EnumStandingType.values().length];
        this.movingType = EnumMovingType.values()[compound.func_74762_e("MovingState") % EnumMovingType.values().length];
        this.tacticalVariant = EnumNavType.values()[compound.func_74762_e("TacticalVariant") % EnumNavType.values().length];
        this.combatPolicy = EnumCombatPolicy.values()[compound.func_74762_e("CombatPolicy") % EnumCombatPolicy.values().length];
        this.orientation = compound.func_74762_e("Orientation");
        this.bodyOffsetY = compound.func_74760_g("PositionOffsetY");
        this.bodyOffsetZ = compound.func_74760_g("PositionOffsetZ");
        this.bodyOffsetX = compound.func_74760_g("PositionOffsetX");
        this.walkingRange = compound.func_74762_e("WalkingRange");
        this.setWalkingSpeed(compound.func_74762_e("MoveSpeed"));
        this.setMovingPath(NBTTags.getIntegerArraySet(compound.func_150295_c("MovingPathNew", 10)));
        this.movingPos = compound.func_74762_e("MovingPos");
        this.movingPattern = compound.func_74762_e("MovingPatern");
        this.movementType = compound.func_74762_e("MovingType");
        this.flySpeed = !compound.func_74764_b("FlySpeed") ? 1.0 : compound.func_74769_h("FlySpeed");
        this.flyGravity = !compound.func_74764_b("FlyGravity") ? 0.0 : compound.func_74769_h("FlyGravity");
        this.hasFlyLimit = !compound.func_74764_b("HasFlyLimit") ? false : compound.func_74767_n("HasFlyLimit");
        this.flyHeightLimit = !compound.func_74764_b("FlyHeightLimit") ? 5 : compound.func_74762_e("FlyHeightLimit");
        this.startPos = compound.func_74759_k("StartPosNew");
        if (this.startPos == null || this.startPos.length != 3) {
            this.startPos = new int[]{MathHelper.func_76128_c((double)this.npc.field_70165_t), MathHelper.func_76128_c((double)this.npc.field_70163_u), MathHelper.func_76128_c((double)this.npc.field_70161_v)};
        }
    }

    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.func_74757_a("CanSwim", this.canSwim);
        compound.func_74757_a("ReactsToFire", this.reactsToFire);
        compound.func_74757_a("AvoidsWater", this.avoidsWater);
        compound.func_74757_a("AvoidsSun", this.avoidsSun);
        compound.func_74757_a("ReturnToStart", this.returnToStart);
        compound.func_74768_a("OnAttack", this.onAttack);
        compound.func_74768_a("DoorInteract", this.doorInteract);
        compound.func_74768_a("FindShelter", this.findShelter);
        compound.func_74757_a("DirectLOS", this.directLOS);
        compound.func_74768_a("LeapType", this.leapType);
        compound.func_74757_a("CanSprint", this.canSprint);
        compound.func_74768_a("FireIndirect", this.canFireIndirect);
        compound.func_74768_a("RangeAndMelee", this.useRangeMelee);
        compound.func_74768_a("DistanceToMelee", this.distanceToMelee);
        compound.func_74768_a("TacticalRadius", this.tacticalRadius);
        compound.func_74768_a("TacticalChance", this.tacticalChance);
        compound.func_74757_a("MovingPause", this.movingPause);
        compound.func_74757_a("npcInteracting", this.npcInteracting);
        compound.func_74757_a("stopAndInteract", this.stopAndInteract);
        compound.func_74768_a("MoveState", this.animationType.ordinal());
        compound.func_74768_a("StandingState", this.standingType.ordinal());
        compound.func_74768_a("MovingState", this.movingType.ordinal());
        compound.func_74768_a("TacticalVariant", this.tacticalVariant.ordinal());
        compound.func_74768_a("CombatPolicy", this.combatPolicy.ordinal());
        compound.func_74768_a("Orientation", this.orientation);
        compound.func_74776_a("PositionOffsetX", this.bodyOffsetX);
        compound.func_74776_a("PositionOffsetY", this.bodyOffsetY);
        compound.func_74776_a("PositionOffsetZ", this.bodyOffsetZ);
        compound.func_74768_a("WalkingRange", this.walkingRange);
        compound.func_74768_a("MoveSpeed", this.moveSpeed);
        compound.func_74782_a("MovingPathNew", (NBTBase)NBTTags.nbtIntegerArraySet(this.movingPath));
        compound.func_74768_a("MovingPos", this.movingPos);
        compound.func_74768_a("MovingPatern", this.movingPattern);
        compound.func_74768_a("MovingType", this.movementType);
        compound.func_74780_a("FlySpeed", this.flySpeed);
        compound.func_74780_a("FlyGravity", this.flyGravity);
        compound.func_74757_a("HasFlyLimit", this.hasFlyLimit);
        compound.func_74768_a("FlyHeightLimit", this.flyHeightLimit);
        this.npc.setAvoidWater(this.avoidsWater);
        compound.func_74783_a("StartPosNew", this.npc.getStartPos());
        return compound;
    }

    public List<int[]> getMovingPath() {
        if (this.movingPath.isEmpty() && this.startPos != null) {
            this.movingPath.add(this.startPos);
        }
        return this.movingPath;
    }

    public void setMovingPath(List<int[]> list) {
        this.movingPath = list;
        if (!this.movingPath.isEmpty()) {
            this.startPos = this.movingPath.get(0);
        }
    }

    public int[] getCurrentMovingPath() {
        List<int[]> list = this.getMovingPath();
        if (list.size() == 1) {
            this.movingPos = 0;
        } else if (this.movingPos >= list.size()) {
            if (this.movingPattern == 0) {
                this.movingPos = 0;
            } else {
                int size = list.size() * 2 - 2;
                if (this.movingPos >= size) {
                    this.movingPos = 0;
                } else if (this.movingPos >= list.size()) {
                    return list.get(list.size() - this.movingPos % list.size() - 2);
                }
            }
        }
        return list.get(this.movingPos);
    }

    public void incrementMovingPath() {
        List<int[]> list = this.getMovingPath();
        if (list.size() == 1) {
            this.movingPos = 0;
        } else if (this.movingPattern == 0) {
            ++this.movingPos;
            this.movingPos %= list.size();
        } else if (this.movingPattern == 1) {
            ++this.movingPos;
            int size = list.size() * 2 - 2;
            this.movingPos %= size;
        }
    }

    public void decreaseMovingPath() {
        List<int[]> list = this.getMovingPath();
        if (list.size() == 1) {
            this.movingPos = 0;
        } else if (this.movingPattern == 0) {
            --this.movingPos;
            if (this.movingPos < 0) {
                this.movingPos += list.size();
            }
        } else if (this.movingPattern == 1) {
            --this.movingPos;
            if (this.movingPos < 0) {
                int size = list.size() * 2 - 2;
                this.movingPos += size;
            }
        }
    }

    public double getDistanceSqToPathPoint() {
        int[] pos = this.getCurrentMovingPath();
        return this.npc.func_70092_e((double)pos[0] + 0.5, pos[1], (double)pos[2] + 0.5);
    }

    public void setWalkingSpeed(double speed) {
        this.moveSpeed = (int)speed;
        this.npc.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a((double)this.npc.getSpeed());
    }

    public int getWalkingSpeed() {
        return this.moveSpeed;
    }
}

