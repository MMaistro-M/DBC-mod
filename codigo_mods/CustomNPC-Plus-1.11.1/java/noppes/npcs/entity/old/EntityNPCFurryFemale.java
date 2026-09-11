/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.World
 */
package noppes.npcs.entity.old;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import noppes.npcs.entity.EntityCustomNpc;
import noppes.npcs.entity.EntityNPCInterface;
import noppes.npcs.entity.data.ModelData;
import noppes.npcs.entity.data.ModelPartData;

public class EntityNPCFurryFemale
extends EntityNPCInterface {
    public EntityNPCFurryFemale(World world) {
        super(world);
        this.scaleZ = 0.9075f;
        this.scaleY = 0.9075f;
        this.scaleX = 0.9075f;
        this.display.texture = "customnpcs:textures/entity/furryfemale/WolfBlack.png";
    }

    @Override
    public void func_70071_h_() {
        this.field_70128_L = true;
        if (!this.field_70170_p.field_72995_K) {
            NBTTagCompound compound = new NBTTagCompound();
            this.func_70109_d(compound);
            EntityCustomNpc npc = new EntityCustomNpc(this.field_70170_p);
            npc.func_70020_e(compound);
            ModelData data = npc.modelData;
            data.breasts = (byte)2;
            data.modelScale.head.setScale(0.95f, 0.95f);
            data.modelScale.legs.setScale(0.92f, 0.92f);
            data.modelScale.arms.setScale(0.8f, 0.92f);
            data.modelScale.body.setScale(0.92f, 0.92f);
            ModelPartData hair = data.getOrCreatePart("ears");
            hair.playerTexture = true;
            ModelPartData snout = data.getOrCreatePart("snout");
            snout.playerTexture = true;
            snout.type = 1;
            ModelPartData tail = data.getOrCreatePart("tail");
            tail.playerTexture = true;
            this.field_70170_p.func_72838_d((Entity)npc);
        }
        super.func_70071_h_();
    }
}

