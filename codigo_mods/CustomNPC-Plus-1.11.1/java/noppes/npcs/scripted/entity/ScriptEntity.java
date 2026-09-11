/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.monster.EntityMob
 *  net.minecraft.entity.passive.EntityAnimal
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTBase$NBTPrimitive
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagIntArray
 *  net.minecraft.nbt.NBTTagString
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.WorldServer
 */
package noppes.npcs.scripted.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kamkeel.npcs.entity.EntityAbilityBeam;
import kamkeel.npcs.entity.EntityAbilityDisc;
import kamkeel.npcs.entity.EntityAbilityLaser;
import kamkeel.npcs.entity.EntityAbilityOrb;
import kamkeel.npcs.entity.EntityEnergyProjectile;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagIntArray;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.WorldServer;
import noppes.npcs.NoppesUtilServer;
import noppes.npcs.api.INbt;
import noppes.npcs.api.IParticle;
import noppes.npcs.api.IPos;
import noppes.npcs.api.IWorld;
import noppes.npcs.api.entity.IEntity;
import noppes.npcs.api.item.IItemStack;
import noppes.npcs.controllers.ServerCloneController;
import noppes.npcs.entity.EntityNPCInterface;
import noppes.npcs.scripted.CustomNPCsException;
import noppes.npcs.scripted.NpcAPI;
import noppes.npcs.scripted.ScriptParticle;

public class ScriptEntity<T extends Entity>
implements IEntity {
    protected T entity;
    private Map<String, Object> tempData = new HashMap<String, Object>();

    public ScriptEntity(T entity) {
        this.entity = entity;
    }

    @Deprecated
    public void spawnParticle(String directory, int HEXColor, int amount, int maxAge, double x, double y, double z, double motionX, double motionY, double motionZ, float gravity, float scale1, float scale2, float scaleRate, int scaleRateStart, float alpha1, float alpha2, float alphaRate, int alphaRateStart, float rotation1, float rotation2, float rotationRate, int rotationRateStart, float rotationX1, float rotationX2, float rotationXRate, int rotationXRateStart, float rotationY1, float rotationY2, float rotationYRate, int rotationYRateStart, float rotationZ1, float rotationZ2, float rotationZRate, int rotationZRateStart) {
        int entityID = this.entity.func_145782_y();
        ScriptParticle particle = new ScriptParticle(directory);
        particle.HEXColor = HEXColor;
        particle.HEXColor2 = HEXColor;
        particle.HEXColorRate = 0.0f;
        particle.HEXColorStart = 0;
        particle.amount = amount;
        particle.maxAge = maxAge;
        particle.x = x;
        particle.y = y;
        particle.z = z;
        particle.motionX = motionX;
        particle.motionY = motionY;
        particle.motionZ = motionZ;
        particle.gravity = gravity;
        particle.scaleX1 = particle.scaleY1 = scale1;
        particle.scaleX2 = particle.scaleY2 = scale2;
        particle.scaleXRate = particle.scaleYRate = scaleRate;
        particle.scaleXRateStart = particle.scaleYRateStart = scaleRateStart;
        particle.alpha1 = alpha1;
        particle.alpha2 = alpha2;
        particle.alphaRate = alphaRate;
        particle.alphaRateStart = alphaRateStart;
        particle.rotationX1 = rotationX1;
        particle.rotationX2 = rotationX2;
        particle.rotationXRate = rotationXRate;
        particle.rotationXRateStart = rotationXRateStart;
        particle.rotationY1 = rotationY1;
        particle.rotationY2 = rotationY2;
        particle.rotationYRate = rotationYRate;
        particle.rotationYRateStart = rotationYRateStart;
        particle.rotationZ1 = rotationZ1;
        particle.rotationZ2 = rotationZ2;
        particle.rotationZRate = rotationZRate;
        particle.rotationZRateStart = rotationZRateStart;
        NBTTagCompound compound = particle.writeToNBT();
        compound.func_74768_a("EntityID", entityID);
        NoppesUtilServer.spawnScriptedParticle(compound, this.getWorld().getDimensionID());
    }

    @Deprecated
    public void spawnParticle(String directory, int HEXColor, int amount, int maxAge, double x, double y, double z, double motionX, double motionY, double motionZ, float gravity, float scale1, float scale2, float scaleRate, int scaleRateStart, float alpha1, float alpha2, float alphaRate, int alphaRateStart) {
        int entityID = this.entity.func_145782_y();
        ScriptParticle particle = new ScriptParticle(directory);
        particle.HEXColor = HEXColor;
        particle.HEXColor2 = HEXColor;
        particle.HEXColorRate = 0.0f;
        particle.HEXColorStart = 0;
        particle.amount = amount;
        particle.maxAge = maxAge;
        particle.x = x;
        particle.y = y;
        particle.z = z;
        particle.motionX = motionX;
        particle.motionY = motionY;
        particle.motionZ = motionZ;
        particle.gravity = gravity;
        particle.scaleX1 = particle.scaleY1 = scale1;
        particle.scaleX2 = particle.scaleY2 = scale2;
        particle.scaleXRate = particle.scaleYRate = scaleRate;
        particle.scaleXRateStart = particle.scaleYRateStart = scaleRateStart;
        particle.alpha1 = alpha1;
        particle.alpha2 = alpha2;
        particle.alphaRate = alphaRate;
        particle.alphaRateStart = alphaRateStart;
        NBTTagCompound compound = particle.writeToNBT();
        compound.func_74768_a("EntityID", entityID);
        NoppesUtilServer.spawnScriptedParticle(compound, this.getWorld().getDimensionID());
    }

    @Deprecated
    public void spawnParticle(String directory, int HEXColor, int amount, int maxAge, double x, double y, double z, double motionX, double motionY, double motionZ, float gravity, float scale1, float scale2, float scaleRate, int scaleRateStart) {
        int entityID = this.entity.func_145782_y();
        ScriptParticle particle = new ScriptParticle(directory);
        particle.HEXColor = HEXColor;
        particle.HEXColor2 = HEXColor;
        particle.HEXColorRate = 0.0f;
        particle.HEXColorStart = 0;
        particle.amount = amount;
        particle.maxAge = maxAge;
        particle.x = x;
        particle.y = y;
        particle.z = z;
        particle.motionX = motionX;
        particle.motionY = motionY;
        particle.motionZ = motionZ;
        particle.gravity = gravity;
        particle.scaleX1 = particle.scaleY1 = scale1;
        particle.scaleX2 = particle.scaleY2 = scale2;
        particle.scaleXRate = particle.scaleYRate = scaleRate;
        particle.scaleXRateStart = particle.scaleYRateStart = scaleRateStart;
        NBTTagCompound compound = particle.writeToNBT();
        compound.func_74768_a("EntityID", entityID);
        NoppesUtilServer.spawnScriptedParticle(compound, this.getWorld().getDimensionID());
    }

    @Deprecated
    public void spawnParticle(String directory, int HEXColor, int amount, int maxAge, double x, double y, double z, double motionX, double motionY, double motionZ, float gravity) {
        int entityID = this.entity.func_145782_y();
        ScriptParticle particle = new ScriptParticle(directory);
        particle.HEXColor = HEXColor;
        particle.HEXColor2 = HEXColor;
        particle.HEXColorRate = 0.0f;
        particle.HEXColorStart = 0;
        particle.amount = amount;
        particle.maxAge = maxAge;
        particle.x = x;
        particle.y = y;
        particle.z = z;
        particle.motionX = motionX;
        particle.motionY = motionY;
        particle.motionZ = motionZ;
        particle.gravity = gravity;
        NBTTagCompound compound = particle.writeToNBT();
        compound.func_74768_a("EntityID", entityID);
        NoppesUtilServer.spawnScriptedParticle(compound, this.getWorld().getDimensionID());
    }

    @Deprecated
    public void spawnParticle(String directory, int HEXColor, int amount, int maxAge, double x, double y, double z) {
        int entityID = this.entity.func_145782_y();
        ScriptParticle particle = new ScriptParticle(directory);
        particle.HEXColor = HEXColor;
        particle.HEXColor2 = HEXColor;
        particle.HEXColorRate = 0.0f;
        particle.HEXColorStart = 0;
        particle.amount = amount;
        particle.maxAge = maxAge;
        particle.x = x;
        particle.y = y;
        particle.z = z;
        NBTTagCompound compound = particle.writeToNBT();
        compound.func_74768_a("EntityID", entityID);
        NoppesUtilServer.spawnScriptedParticle(compound, this.getWorld().getDimensionID());
    }

    @Deprecated
    public void spawnParticle(String directory, int HEXColor, int amount, int maxAge) {
        int entityID = this.entity.func_145782_y();
        ScriptParticle particle = new ScriptParticle(directory);
        particle.HEXColor = HEXColor;
        particle.HEXColor2 = HEXColor;
        particle.HEXColorRate = 0.0f;
        particle.HEXColorStart = 0;
        particle.amount = amount;
        particle.maxAge = maxAge;
        NBTTagCompound compound = particle.writeToNBT();
        compound.func_74768_a("EntityID", entityID);
        NoppesUtilServer.spawnScriptedParticle(compound, this.getWorld().getDimensionID());
    }

    @Override
    public void spawnParticle(IParticle entityParticle) {
        entityParticle.spawn(this);
    }

    @Override
    public int getEntityId() {
        return this.entity.func_145782_y();
    }

    @Override
    public String getUniqueID() {
        return this.entity.func_110124_au().toString();
    }

    @Override
    public double getYOffset() {
        return this.entity.func_70033_W();
    }

    @Override
    public double getWidth() {
        return ((Entity)this.entity).field_70130_N;
    }

    @Override
    public double getHeight() {
        return ((Entity)this.entity).field_70131_O;
    }

    @Override
    public double getX() {
        return ((Entity)this.entity).field_70165_t;
    }

    @Override
    public void setX(double x) {
        this.entity.func_70107_b(x, ((Entity)this.entity).field_70163_u, ((Entity)this.entity).field_70161_v);
    }

    @Override
    public double getY() {
        return ((Entity)this.entity).field_70163_u;
    }

    @Override
    public void setY(double y) {
        this.entity.func_70107_b(((Entity)this.entity).field_70165_t, y, ((Entity)this.entity).field_70161_v);
    }

    @Override
    public double getZ() {
        return ((Entity)this.entity).field_70161_v;
    }

    @Override
    public void setZ(double z) {
        this.entity.func_70107_b(((Entity)this.entity).field_70165_t, ((Entity)this.entity).field_70163_u, z);
    }

    @Override
    public double getMotionX() {
        return ((Entity)this.entity).field_70159_w;
    }

    @Override
    public void setMotionX(double x) {
        ((Entity)this.entity).field_70159_w = x;
        ((Entity)this.entity).field_70133_I = true;
    }

    @Override
    public double getMotionY() {
        return ((Entity)this.entity).field_70181_x;
    }

    @Override
    public void setMotionY(double y) {
        ((Entity)this.entity).field_70181_x = y;
        ((Entity)this.entity).field_70133_I = true;
    }

    @Override
    public double getMotionZ() {
        return ((Entity)this.entity).field_70179_y;
    }

    @Override
    public void setMotionZ(double z) {
        ((Entity)this.entity).field_70179_y = z;
        ((Entity)this.entity).field_70133_I = true;
    }

    @Override
    public void setMotion(double x, double y, double z) {
        this.setMotionX(x);
        this.setMotionY(y);
        this.setMotionZ(z);
    }

    @Override
    public void setMotion(IPos pos) {
        this.setMotion(pos.getXD(), pos.getYD(), pos.getZD());
    }

    @Override
    public IPos getMotion() {
        return NpcAPI.Instance().getIPos(((Entity)this.entity).field_70159_w, ((Entity)this.entity).field_70181_x, ((Entity)this.entity).field_70179_y);
    }

    @Override
    public boolean isAirborne() {
        return ((Entity)this.entity).field_70160_al;
    }

    @Override
    public int getBlockX() {
        return MathHelper.func_76128_c((double)((Entity)this.entity).field_70165_t);
    }

    @Override
    public int getBlockY() {
        return MathHelper.func_76128_c((double)((Entity)this.entity).field_70163_u);
    }

    @Override
    public int getBlockZ() {
        return MathHelper.func_76128_c((double)((Entity)this.entity).field_70161_v);
    }

    @Override
    public void setPosition(double x, double y, double z) {
        this.entity.func_70107_b(x, y, z);
    }

    public IPos getPos() {
        return NpcAPI.Instance().getIPos(((Entity)this.entity).field_70165_t, ((Entity)this.entity).field_70163_u, ((Entity)this.entity).field_70161_v);
    }

    @Override
    public IPos getPosition() {
        return this.getPos();
    }

    public void setPos(IPos pos) {
        this.entity.func_70107_b(pos.getXD(), pos.getYD(), pos.getZD());
    }

    @Override
    public void setPosition(IPos pos) {
        this.setPos(pos);
    }

    @Override
    public int getDimension() {
        return ((Entity)this.entity).field_71093_bK;
    }

    @Override
    public void setDimension(int dimensionId) {
        IWorld world = NpcAPI.Instance().getIWorld(dimensionId);
        if (world != null) {
            WorldServer mcWorld = world.getMCWorld();
            if (((Entity)this.entity).field_70153_n != null) {
                ((Entity)this.entity).field_70153_n.func_70078_a((Entity)null);
            }
            if (((Entity)this.entity).field_70154_o != null) {
                this.entity.func_70078_a((Entity)null);
            }
            WorldServer prevWorld = (WorldServer)((Entity)this.entity).field_70170_p;
            prevWorld.func_72828_b(new ArrayList<T>(Collections.singleton(this.entity)));
            prevWorld.func_82742_i();
            prevWorld.func_72847_b(this.entity);
            ((Entity)this.entity).field_70170_p = mcWorld;
            ((Entity)this.entity).field_71093_bK = dimensionId;
            mcWorld.func_72868_a(new ArrayList<T>(Collections.singleton(this.entity)));
            mcWorld.func_82742_i();
            mcWorld.func_72866_a(this.entity, false);
            if (this.entity instanceof EntityNPCInterface) {
                ((EntityNPCInterface)((Object)this.entity)).updateClient();
            }
            mcWorld.func_72838_d(this.entity);
            ((Entity)this.entity).field_70133_I = true;
        }
    }

    @Override
    public IEntity[] getCollidingEntities() {
        boolean isPl = this.entity instanceof EntityPlayer;
        AxisAlignedBB axisalignedbb = ((Entity)this.entity).field_70154_o != null && ((Entity)this.entity).field_70154_o.func_70089_S() ? ((Entity)this.entity).field_70121_D.func_111270_a(((Entity)this.entity).field_70154_o.field_70121_D).func_72314_b(isPl ? 0.0 : 1.0, 0.0, isPl ? 0.0 : 1.0) : ((Entity)this.entity).field_70121_D.func_72314_b(isPl ? 0.0 : 1.0, isPl ? 0.0 : 0.5, isPl ? 0.0 : 1.0);
        List entities = ((Entity)this.entity).field_70170_p.func_72839_b(this.entity, axisalignedbb);
        ArrayList list = new ArrayList();
        for (Entity living : entities) {
            if (!living.func_70089_S()) continue;
            list.add(NpcAPI.Instance().getIEntity(living));
        }
        return list.toArray(new IEntity[list.size()]);
    }

    @Override
    public IEntity[] getSurroundingEntities(int range) {
        List entities = ((Entity)this.entity).field_70170_p.func_72872_a(Entity.class, ((Entity)this.entity).field_70121_D.func_72314_b((double)range, (double)range, (double)range));
        ArrayList list = new ArrayList();
        for (Entity living : entities) {
            if (living == this.entity) continue;
            list.add(NpcAPI.Instance().getIEntity(living));
        }
        return list.toArray(new IEntity[list.size()]);
    }

    @Override
    public IEntity[] getSurroundingEntities(int range, int type) {
        Class cls = Entity.class;
        if (type == 5) {
            cls = EntityLivingBase.class;
        } else if (type == 1) {
            cls = EntityPlayer.class;
        } else if (type == 4) {
            cls = EntityAnimal.class;
        } else if (type == 3) {
            cls = EntityMob.class;
        } else if (type == 2) {
            cls = EntityNPCInterface.class;
        } else if (type == 13) {
            cls = EntityEnergyProjectile.class;
        } else if (type == 14) {
            cls = EntityAbilityOrb.class;
        } else if (type == 15) {
            cls = EntityAbilityBeam.class;
        } else if (type == 16) {
            cls = EntityAbilityDisc.class;
        } else if (type == 17) {
            cls = EntityAbilityLaser.class;
        }
        List entities = ((Entity)this.entity).field_70170_p.func_72872_a(cls, ((Entity)this.entity).field_70121_D.func_72314_b((double)range, (double)range, (double)range));
        ArrayList list = new ArrayList();
        for (Entity living : entities) {
            if (living == this.entity) continue;
            list.add(NpcAPI.Instance().getIEntity(living));
        }
        return list.toArray(new IEntity[list.size()]);
    }

    @Override
    public boolean isAlive() {
        return this.entity.func_70089_S();
    }

    @Override
    public Object getTempData(String key) {
        return this.tempData.get(key);
    }

    public void copyTempData(IEntity<?> scriptEntity) {
        if (scriptEntity != null) {
            this.tempData = ((ScriptEntity)scriptEntity).tempData;
        }
    }

    @Override
    public void setTempData(String key, Object value) {
        this.tempData.put(key, value);
    }

    @Override
    public boolean hasTempData(String key) {
        return this.tempData.containsKey(key);
    }

    @Override
    public void removeTempData(String key) {
        this.tempData.remove(key);
    }

    @Override
    public void clearTempData() {
        this.tempData.clear();
    }

    @Override
    public String[] getTempDataKeys() {
        return this.tempData.keySet().toArray(new String[0]);
    }

    @Override
    public Object getStoredData(String key) {
        NBTTagCompound compound = this.getStoredCompound();
        if (!compound.func_74764_b(key)) {
            return null;
        }
        NBTBase base = compound.func_74781_a(key);
        if (base instanceof NBTBase.NBTPrimitive) {
            return ((NBTBase.NBTPrimitive)base).func_150286_g();
        }
        if (base instanceof NBTTagIntArray) {
            return ((NBTTagIntArray)base).func_150302_c();
        }
        return ((NBTTagString)base).func_150285_a_();
    }

    @Override
    public void setStoredData(String key, Object value) {
        NBTTagCompound compound = this.getStoredCompound();
        if (value instanceof Number) {
            compound.func_74780_a(key, ((Number)value).doubleValue());
        } else if (value instanceof String) {
            compound.func_74778_a(key, (String)value);
        }
        this.saveStoredCompound(compound);
    }

    public void setStoredData(String key, int[] array) {
        NBTTagCompound compound = this.getStoredCompound();
        compound.func_74783_a(key, array);
        this.saveStoredCompound(compound);
    }

    @Override
    public boolean hasStoredData(String key) {
        return this.getStoredCompound().func_74764_b(key);
    }

    @Override
    public void removeStoredData(String key) {
        NBTTagCompound compound = this.getStoredCompound();
        compound.func_82580_o(key);
        this.saveStoredCompound(compound);
    }

    @Override
    public void clearStoredData() {
        this.entity.getEntityData().func_82580_o("CNPCStoredData");
    }

    @Override
    public String[] getStoredDataKeys() {
        NBTTagCompound compound = this.getStoredCompound();
        Set strings = compound.func_150296_c();
        ArrayList<String> stringList = new ArrayList<String>();
        for (Object o : strings) {
            stringList.add((String)o);
        }
        return stringList.toArray(new String[0]);
    }

    private NBTTagCompound getStoredCompound() {
        NBTTagCompound compound = this.entity.getEntityData().func_74775_l("CNPCStoredData");
        if (compound == null) {
            compound = new NBTTagCompound();
            this.entity.getEntityData().func_74782_a("CNPCStoredData", (NBTBase)compound);
        }
        return compound;
    }

    private void saveStoredCompound(NBTTagCompound compound) {
        this.entity.getEntityData().func_74782_a("CNPCStoredData", (NBTBase)compound);
    }

    @Override
    public long getAge() {
        return ((Entity)this.entity).field_70173_aa;
    }

    @Override
    public void despawn() {
        ((Entity)this.entity).field_70128_L = true;
    }

    @Override
    public boolean inWater() {
        return this.entity.func_70055_a(Material.field_151586_h);
    }

    @Override
    public boolean inLava() {
        return this.entity.func_70055_a(Material.field_151587_i);
    }

    @Override
    public boolean inFire() {
        return this.entity.func_70055_a(Material.field_151581_o);
    }

    @Override
    public boolean isBurning() {
        return this.entity.func_70027_ad();
    }

    @Override
    public void setBurning(int ticks) {
        this.entity.func_70015_d(ticks);
    }

    @Override
    public void extinguish() {
        this.entity.func_70066_B();
    }

    @Override
    public String getTypeName() {
        return EntityList.func_75621_b(this.entity);
    }

    public void playSound(String name, float volume, float pitch) {
        this.entity.func_85030_a(name, volume, pitch);
    }

    @Override
    public void dropItem(IItemStack item) {
        this.entity.func_70099_a(item.getMCItemStack(), 0.0f);
    }

    @Override
    public IEntity getRider() {
        return NpcAPI.Instance().getIEntity(((Entity)this.entity).field_70153_n);
    }

    @Override
    public void setRider(IEntity entity) {
        if (entity != null) {
            entity.getMCEntity().func_70078_a(this.entity);
        } else if (((Entity)this.entity).field_70153_n != null) {
            ((Entity)this.entity).field_70153_n.func_70078_a(null);
        }
    }

    @Override
    public IEntity getMount() {
        return NpcAPI.Instance().getIEntity(((Entity)this.entity).field_70154_o);
    }

    @Override
    public void setMount(IEntity entity) {
        if (entity == null) {
            this.entity.func_70078_a(null);
        } else {
            this.entity.func_70078_a(entity.getMCEntity());
        }
    }

    @Override
    public int getType() {
        return 0;
    }

    @Override
    public boolean typeOf(int type) {
        return type == 0;
    }

    @Override
    public void setRotation(float rotation) {
        ((Entity)this.entity).field_70177_z = rotation;
    }

    @Override
    public void setRotation(float rotationYaw, float rotationPitch) {
        ((Entity)this.entity).field_70177_z = rotationYaw;
        ((Entity)this.entity).field_70125_A = rotationPitch;
    }

    @Override
    public float getRotation() {
        return ((Entity)this.entity).field_70177_z;
    }

    @Override
    public void setPitch(float pitch) {
        ((Entity)this.entity).field_70125_A = pitch;
    }

    @Override
    public float getPitch() {
        return ((Entity)this.entity).field_70125_A;
    }

    @Override
    public void knockback(int power, float direction) {
        float v = direction * (float)Math.PI / 180.0f;
        this.entity.func_70024_g((double)(-MathHelper.func_76126_a((float)v) * (float)power), (double)power, (double)(MathHelper.func_76134_b((float)v) * (float)power));
        ((Entity)this.entity).field_70133_I = true;
    }

    @Override
    public void knockback(double xpower, double ypower, double zpower, float direction) {
        float v = direction * (float)Math.PI / 180.0f;
        this.entity.func_70024_g((double)(-MathHelper.func_76126_a((float)v)) * xpower, ypower, (double)MathHelper.func_76134_b((float)v) * zpower);
        ((Entity)this.entity).field_70133_I = true;
    }

    @Override
    public void knockback(IPos pos, float direction) {
        this.knockback(pos.getXD(), pos.getYD(), pos.getZD(), direction);
    }

    @Override
    public void setImmune(int ticks) {
        ((Entity)this.entity).field_70172_ad = ticks;
    }

    @Override
    public void setInvisible(boolean invisible) {
        this.entity.func_82142_c(invisible);
    }

    @Override
    public void setSneaking(boolean sneaking) {
        this.entity.func_70095_a(sneaking);
    }

    @Override
    public void setSprinting(boolean sprinting) {
        this.entity.func_70031_b(sprinting);
    }

    @Override
    public boolean hasCollided() {
        return ((Entity)this.entity).field_70132_H;
    }

    @Override
    public boolean hasCollidedVertically() {
        return ((Entity)this.entity).field_70124_G;
    }

    @Override
    public boolean hasCollidedHorizontally() {
        return ((Entity)this.entity).field_70123_F;
    }

    @Override
    public boolean capturesDrops() {
        return ((Entity)this.entity).captureDrops;
    }

    @Override
    public void setCapturesDrops(boolean capture) {
        ((Entity)this.entity).captureDrops = capture;
    }

    public void setCapturedDrops(IEntity[] capturedDrops) {
        ((Entity)this.entity).capturedDrops.clear();
        for (IEntity iEntity : capturedDrops) {
            if (!(iEntity.getMCEntity() instanceof EntityItem)) continue;
            ((Entity)this.entity).capturedDrops.add((EntityItem)iEntity.getMCEntity());
        }
    }

    @Override
    public IEntity<?>[] getCapturedDrops() {
        ArrayList iEntityList = new ArrayList();
        for (EntityItem entityItem : ((Entity)this.entity).capturedDrops) {
            iEntityList.add(NpcAPI.Instance().getIEntity((Entity)entityItem));
        }
        return iEntityList.toArray(new IEntity[0]);
    }

    @Override
    public boolean isSneaking() {
        return this.entity.func_70093_af();
    }

    @Override
    public boolean isSprinting() {
        return this.entity.func_70051_ag();
    }

    @Override
    public T getMCEntity() {
        return this.entity;
    }

    @Override
    public INbt getNbt() {
        return NpcAPI.Instance().getINbt(this.entity.getEntityData());
    }

    @Override
    public INbt getAllNbt() {
        NBTTagCompound compound = new NBTTagCompound();
        this.entity.func_70109_d(compound);
        return NpcAPI.Instance().getINbt(compound);
    }

    @Override
    public void setNbt(INbt nbt) {
        this.entity.func_70020_e(nbt.getMCNBT());
    }

    @Override
    public INbt getNbtOptional() {
        NBTTagCompound compound = new NBTTagCompound();
        return this.entity.func_70039_c(compound) ? NpcAPI.Instance().getINbt(compound) : null;
    }

    @Override
    public void storeAsClone(int tab, String name) {
        NBTTagCompound compound = new NBTTagCompound();
        if (!this.entity.func_70039_c(compound)) {
            throw new CustomNPCsException("Cannot store dead entities", new Object[0]);
        }
        ServerCloneController.Instance.addClone(compound, name, tab);
    }

    @Override
    public IWorld getWorld() {
        return NpcAPI.Instance().getIWorld(((Entity)this.entity).field_70170_p);
    }

    public boolean equals(Object object) {
        return object instanceof IEntity && ((IEntity)object).getMCEntity().equals(this.entity);
    }

    @Override
    public void updateEntity() {
        IWorld world = NpcAPI.Instance().getIWorld(((Entity)this.entity).field_70170_p);
        ((Entity)this.entity).field_71093_bK = world.getDimensionID();
    }
}

