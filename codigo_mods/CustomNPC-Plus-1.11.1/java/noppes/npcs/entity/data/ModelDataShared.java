/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.passive.EntityHorse
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 */
package noppes.npcs.entity.data;

import java.util.HashMap;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import noppes.npcs.api.entity.data.IModelData;
import noppes.npcs.api.entity.data.IModelRotate;
import noppes.npcs.api.entity.data.IModelScale;
import noppes.npcs.entity.data.ModelPartData;
import noppes.npcs.entity.data.ModelRotate;
import noppes.npcs.entity.data.ModelScale;
import noppes.npcs.util.ValueUtil;

public class ModelDataShared
implements IModelData {
    public ModelScale modelScale = new ModelScale();
    public boolean enableRotation = false;
    public ModelRotate rotation = new ModelRotate();
    public ModelPartData legParts = new ModelPartData();
    public Class<? extends EntityLivingBase> entityClass;
    public EntityLivingBase entity;
    public NBTTagCompound extra = new NBTTagCompound();
    private HashMap<String, ModelPartData> parts = new HashMap();
    public byte breasts = 0;
    public byte headwear = (byte)2;
    public byte bodywear = 0;
    public byte armwear = 0;
    public byte legwear = 0;
    public byte solidArmwear = 0;
    public byte solidLegwear = 0;
    public byte hideHead = 0;
    public byte hideBody = 0;
    public byte hideArms = 0;
    public byte hideLegs = 0;

    public NBTTagCompound writeToNBT() {
        NBTTagCompound compound = new NBTTagCompound();
        if (this.entityClass != null) {
            compound.func_74778_a("EntityClass", this.entityClass.getCanonicalName());
        }
        compound = this.modelScale.writeToNBT(compound);
        compound.func_74782_a("LegParts", (NBTBase)this.legParts.writeToNBT());
        compound.func_74774_a("Headwear", this.headwear);
        compound.func_74774_a("Bodywear", this.bodywear);
        compound.func_74774_a("Armwear", this.armwear);
        compound.func_74774_a("Legwear", this.legwear);
        compound.func_74774_a("SolidArmwear", this.solidArmwear);
        compound.func_74774_a("SolidLegwear", this.solidLegwear);
        compound.func_74774_a("hideHead", this.hideHead);
        compound.func_74774_a("hideBody", this.hideBody);
        compound.func_74774_a("hideArms", this.hideArms);
        compound.func_74774_a("hideLegs", this.hideLegs);
        compound.func_74757_a("EnableRotation", this.enableRotation);
        if (this.enableRotation) {
            compound.func_74782_a("ModelRotation", (NBTBase)this.rotation.writeToNBT());
        }
        compound.func_74774_a("Breasts", this.breasts);
        compound.func_74782_a("ExtraData", (NBTBase)this.extra);
        NBTTagList list = new NBTTagList();
        for (String name : this.parts.keySet()) {
            NBTTagCompound item = this.parts.get(name).writeToNBT();
            item.func_74778_a("PartName", name);
            list.func_74742_a((NBTBase)item);
        }
        compound.func_74782_a("Parts", (NBTBase)list);
        return compound;
    }

    public void readFromNBT(NBTTagCompound compound) {
        this.setEntity(compound.func_74779_i("EntityClass"));
        this.modelScale.readFromNBT(compound);
        this.legParts.readFromNBT(compound.func_74775_l("LegParts"));
        this.headwear = compound.func_74771_c("Headwear");
        this.bodywear = compound.func_74771_c("Bodywear");
        this.armwear = compound.func_74771_c("Armwear");
        this.legwear = compound.func_74771_c("Legwear");
        this.solidArmwear = compound.func_74771_c("SolidArmwear");
        this.solidLegwear = compound.func_74771_c("SolidLegwear");
        this.hideHead = compound.func_74771_c("hideHead");
        this.hideBody = compound.func_74771_c("hideBody");
        this.hideArms = compound.func_74771_c("hideArms");
        this.hideLegs = compound.func_74771_c("hideLegs");
        this.enableRotation = compound.func_74767_n("EnableRotation");
        if (this.enableRotation) {
            this.rotation.readFromNBT(compound.func_74775_l("ModelRotation"));
        }
        this.breasts = compound.func_74771_c("Breasts");
        this.extra = compound.func_74775_l("ExtraData");
        HashMap<String, ModelPartData> parts = new HashMap<String, ModelPartData>();
        NBTTagList list = compound.func_150295_c("Parts", 10);
        for (int i = 0; i < list.func_74745_c(); ++i) {
            NBTTagCompound item = list.func_150305_b(i);
            ModelPartData part = new ModelPartData();
            part.readFromNBT(item);
            parts.put(item.func_74779_i("PartName"), part);
        }
        this.parts = parts;
    }

    public void setEntityClass(Class<? extends EntityLivingBase> entityClass) {
        this.entityClass = entityClass;
        this.entity = null;
        this.extra = new NBTTagCompound();
        if (entityClass == EntityHorse.class) {
            this.extra.func_74768_a("Type", -1);
        }
    }

    public Class<? extends EntityLivingBase> getEntityClass() {
        return this.entityClass;
    }

    public float offsetY() {
        if (this.entity == null) {
            return -this.getBodyY();
        }
        return this.entity.field_70131_O - 1.8f;
    }

    public void clearEntity() {
        this.entity = null;
    }

    public ModelPartData getPartData(String type) {
        return this.parts.get(type);
    }

    public void removePart(String type) {
        this.parts.remove(type);
    }

    public ModelPartData getOrCreatePart(String type) {
        ModelPartData part = this.parts.get(type);
        if (part == null) {
            part = new ModelPartData();
            this.parts.put(type, part);
        }
        return part;
    }

    public float getBodyY() {
        if (this.legParts.type == 3) {
            return (0.9f - this.modelScale.body.scaleY) * 0.75f + this.getLegsY();
        }
        if (this.legParts.type == 3) {
            return (0.5f - this.modelScale.body.scaleY) * 0.75f + this.getLegsY();
        }
        return (1.0f - this.modelScale.body.scaleY) * 0.75f + this.getLegsY();
    }

    public float getLegsY() {
        if (this.legParts.type == 3) {
            return 0.87f - this.modelScale.legs.scaleY;
        }
        return (1.0f - this.modelScale.legs.scaleY) * 0.75f;
    }

    @Override
    public void headWear(byte config) {
        this.headwear = ValueUtil.clamp(config, (byte)0, (byte)2);
    }

    @Override
    public byte headWear() {
        return this.headwear;
    }

    @Override
    public void bodyWear(byte config) {
        this.bodywear = ValueUtil.clamp(config, (byte)0, (byte)2);
    }

    @Override
    public byte bodyWear() {
        return this.bodywear;
    }

    @Override
    public void rightArmWear(byte config) {
        if ((config = ValueUtil.clamp(config, (byte)0, (byte)2)) == 1 || config == 2) {
            if (this.armwear == 0) {
                this.armwear = (byte)2;
            } else if (this.armwear == 3) {
                this.armwear = 1;
            }
        } else if (this.armwear == 1) {
            this.armwear = (byte)3;
        } else if (this.armwear == 2) {
            this.armwear = 0;
        }
        if (config == 1) {
            this.solidArmwear = 0;
        } else if (config == 2) {
            if (this.solidArmwear == 0) {
                this.solidArmwear = (byte)2;
            } else if (this.solidArmwear == 3) {
                this.solidArmwear = 1;
            }
        }
    }

    @Override
    public byte rightArmWear() {
        if (this.armwear == 0 || this.armwear == 3) {
            return 0;
        }
        if (this.solidArmwear == 1 || this.solidArmwear == 2) {
            return 2;
        }
        return 1;
    }

    @Override
    public void leftArmWear(byte config) {
        if ((config = ValueUtil.clamp(config, (byte)0, (byte)2)) == 1 || config == 2) {
            if (this.armwear == 0) {
                this.armwear = (byte)3;
            } else if (this.armwear == 2) {
                this.armwear = 1;
            }
        } else if (this.armwear == 1) {
            this.armwear = (byte)2;
        } else if (this.armwear == 3) {
            this.armwear = 0;
        }
        if (config == 1) {
            this.solidArmwear = 0;
        } else if (config == 2) {
            if (this.solidArmwear == 0) {
                this.solidArmwear = (byte)3;
            } else if (this.solidArmwear == 2) {
                this.solidArmwear = 1;
            }
        }
    }

    @Override
    public byte leftArmWear() {
        if (this.armwear == 0 || this.armwear == 2) {
            return 0;
        }
        if (this.solidArmwear == 1 || this.solidArmwear == 3) {
            return 2;
        }
        return 1;
    }

    @Override
    public void rightLegWear(byte config) {
        if ((config = ValueUtil.clamp(config, (byte)0, (byte)2)) == 1 || config == 2) {
            if (this.legwear == 0) {
                this.legwear = (byte)2;
            } else if (this.legwear == 3) {
                this.legwear = 1;
            }
        } else if (this.legwear == 1) {
            this.legwear = (byte)3;
        } else if (this.legwear == 2) {
            this.legwear = 0;
        }
        if (config == 1) {
            this.solidLegwear = 0;
        } else if (config == 2) {
            if (this.solidLegwear == 0) {
                this.solidLegwear = (byte)2;
            } else if (this.solidLegwear == 3) {
                this.solidLegwear = 1;
            }
        }
    }

    @Override
    public byte rightLegWear() {
        if (this.legwear == 0 || this.legwear == 3) {
            return 0;
        }
        if (this.solidLegwear == 1 || this.solidLegwear == 2) {
            return 2;
        }
        return 1;
    }

    @Override
    public void leftLegWear(byte config) {
        if ((config = ValueUtil.clamp(config, (byte)0, (byte)2)) == 1 || config == 2) {
            if (this.legwear == 0) {
                this.legwear = (byte)3;
            } else if (this.legwear == 2) {
                this.legwear = 1;
            }
        } else if (this.legwear == 1) {
            this.legwear = (byte)2;
        } else if (this.legwear == 3) {
            this.legwear = 0;
        }
        if (config == 1) {
            this.solidLegwear = 0;
        } else if (config == 2) {
            if (this.solidLegwear == 0) {
                this.solidLegwear = (byte)3;
            } else if (this.solidLegwear == 2) {
                this.solidLegwear = 1;
            }
        }
    }

    @Override
    public byte leftLegWear() {
        if (this.legwear == 0 || this.legwear == 2) {
            return 0;
        }
        if (this.solidLegwear == 1 || this.solidLegwear == 3) {
            return 2;
        }
        return 1;
    }

    @Override
    public void hidePart(int part, byte hide) {
        hide = (part = ValueUtil.clamp(part, 0, 3)) > 1 ? ValueUtil.clamp(hide, (byte)0, (byte)3) : ValueUtil.clamp(hide, (byte)0, (byte)1);
        switch (part) {
            case 0: {
                this.hideHead = hide;
                break;
            }
            case 1: {
                this.hideBody = hide;
                break;
            }
            case 2: {
                this.hideArms = hide;
                break;
            }
            case 3: {
                this.hideLegs = hide;
            }
        }
    }

    @Override
    public int hidden(int part) {
        part = ValueUtil.clamp(part, 0, 3);
        switch (part) {
            case 0: {
                return this.hideHead;
            }
            case 1: {
                return this.hideBody;
            }
            case 2: {
                return this.hideArms;
            }
            case 3: {
                return this.hideLegs;
            }
        }
        return 0;
    }

    @Override
    public void enableRotation(boolean enableRotation) {
        this.enableRotation = enableRotation;
    }

    @Override
    public boolean enableRotation() {
        return this.enableRotation;
    }

    @Override
    public IModelRotate getRotation() {
        return this.rotation;
    }

    @Override
    public IModelScale getScale() {
        return this.modelScale;
    }

    @Override
    public void setEntity(String string) {
        this.entityClass = null;
        this.entity = null;
        try {
            Class<?> cls = Class.forName(string);
            if (EntityLivingBase.class.isAssignableFrom(cls)) {
                this.entityClass = cls.asSubclass(EntityLivingBase.class);
            }
        }
        catch (ClassNotFoundException classNotFoundException) {
            // empty catch block
        }
    }

    @Override
    public String getEntity() {
        return this.entityClass == null ? null : this.entityClass.getName();
    }
}

