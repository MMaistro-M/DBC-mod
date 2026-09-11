/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.registry.IEntityAdditionalSpawnData
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.world.World
 */
package JinRyuu.DragonBC.common.Npcs;

import JinRyuu.DragonBC.common.DBCConfig;
import JinRyuu.DragonBC.common.Npcs.EntityDBC;
import JinRyuu.DragonBC.common.Npcs.EntitySaiyan01;
import JinRyuu.DragonBC.common.Npcs.EntitySaiyan02;
import JinRyuu.JRMCore.JRMCoreH;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import io.netty.buffer.ByteBuf;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityDBCEvil
extends EntityDBC
implements IEntityAdditionalSpawnData {
    private Entity spwner = null;
    protected Entity target = null;
    private int noSpwnr = DBCConfig.mdat;

    public EntityDBCEvil(World par1World) {
        super(par1World);
    }

    protected NBTTagCompound nbt(EntityPlayer p, String s) {
        NBTTagCompound nbt;
        if (s.contains("pres")) {
            if (!p.getEntityData().func_74764_b("PlayerPersisted")) {
                nbt = new NBTTagCompound();
                p.getEntityData().func_74782_a("PlayerPersisted", (NBTBase)nbt);
            } else {
                nbt = p.getEntityData().func_74775_l("PlayerPersisted");
            }
        } else {
            nbt = p.getEntityData();
        }
        return nbt;
    }

    public void setSpwner(Entity e) {
        this.spwner = e;
    }

    public Entity getSpwner() {
        return this.spwner;
    }

    public void settarget(Entity e) {
        this.target = e;
    }

    public Entity gettarget() {
        return this.target;
    }

    public void setETA(Entity par1Entity) {
        this.field_70789_a = par1Entity;
    }

    @Override
    protected Entity func_70782_k() {
        return this.target != null ? this.target : super.func_70782_k();
    }

    @Override
    public void func_70645_a(DamageSource par1DamageSource) {
        Entity var3 = par1DamageSource.func_76346_g();
        super.func_70645_a(par1DamageSource);
    }

    @Override
    public void func_70071_h_() {
        if (!(this instanceof EntitySaiyan01) && !(this instanceof EntitySaiyan02)) {
            double r = DBCConfig.mdal;
            if (this.spwner != null && r != 0.0) {
                AxisAlignedBB aabb = AxisAlignedBB.func_72330_a((double)(this.field_70165_t - r), (double)(this.field_70163_u - r), (double)(this.field_70161_v - r), (double)(this.field_70165_t + r), (double)(this.field_70163_u + r), (double)(this.field_70161_v + r));
                List list = this.field_70170_p.func_72872_a(EntityPlayer.class, aabb);
                boolean b = false;
                int j = 0;
                int sgid = JRMCoreH.getInt((EntityPlayer)this.spwner, "JRMCGID");
                for (int i = 0; i < list.size(); ++i) {
                    EntityPlayer entity2 = (EntityPlayer)list.get(i);
                    int ogid = JRMCoreH.getInt(entity2, "JRMCGID");
                    if (this.spwner.func_145782_y() != entity2.func_145782_y() && (sgid == 0 || sgid != ogid)) continue;
                    ++j;
                }
                if (this.func_145782_y() == this.spwner.func_145782_y()) {
                    ++j;
                }
                if (j == 0) {
                    --this.noSpwnr;
                    if (this.noSpwnr <= 0) {
                        this.func_70106_y();
                    }
                } else if (this.noSpwnr != DBCConfig.mdat) {
                    this.noSpwnr = DBCConfig.mdat;
                }
            }
            if (!this.field_70170_p.field_72995_K && this.spwner == null) {
                this.func_70106_y();
            }
        }
        super.func_70071_h_();
    }

    @Override
    public boolean func_70097_a(DamageSource par1DamageSource, float par2) {
        if (!super.func_70097_a(par1DamageSource, par2)) {
            return false;
        }
        if (this.spwner != null) {
            MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
            EntityPlayer spwnr = (EntityPlayer)this.spwner;
            Entity atckr = par1DamageSource.func_76346_g();
            if (atckr instanceof EntityPlayer) {
                int sgid = JRMCoreH.getInt(spwnr, "JRMCGID");
                if (sgid != 0) {
                    this.func_70784_b(atckr);
                    this.settarget(atckr);
                    EntityLivingBase entitylivingbase = this.func_70638_az();
                    if (entitylivingbase == null && this.func_70777_m() instanceof EntityLivingBase) {
                        entitylivingbase = (EntityLivingBase)this.func_70777_m();
                    }
                    if (entitylivingbase == null && par1DamageSource.func_76346_g() instanceof EntityLivingBase) {
                        entitylivingbase = (EntityLivingBase)par1DamageSource.func_76346_g();
                    }
                    return true;
                }
                if (spwnr.func_145782_y() == atckr.func_145782_y()) {
                    this.func_70784_b(atckr);
                    this.settarget(atckr);
                    EntityLivingBase entitylivingbase = this.func_70638_az();
                    if (entitylivingbase == null && this.func_70777_m() instanceof EntityLivingBase) {
                        entitylivingbase = (EntityLivingBase)this.func_70777_m();
                    }
                    if (entitylivingbase == null && par1DamageSource.func_76346_g() instanceof EntityLivingBase) {
                        entitylivingbase = (EntityLivingBase)par1DamageSource.func_76346_g();
                    }
                    return true;
                }
                return false;
            }
            return false;
        }
        EntityLivingBase entitylivingbase = this.func_70638_az();
        if (entitylivingbase == null && this.func_70777_m() instanceof EntityLivingBase) {
            entitylivingbase = (EntityLivingBase)this.func_70777_m();
        }
        if (entitylivingbase == null && par1DamageSource.func_76346_g() instanceof EntityLivingBase) {
            entitylivingbase = (EntityLivingBase)par1DamageSource.func_76346_g();
        }
        return true;
    }

    public void writeSpawnData(ByteBuf data) {
        data.writeInt(this.spwner == null ? 0 : this.spwner.func_145782_y());
        data.writeInt(this.target == null ? 0 : this.target.func_145782_y());
        data.writeInt(this.field_70789_a == null ? 0 : this.field_70789_a.func_145782_y());
    }

    public void readSpawnData(ByteBuf data) {
        int e1 = data.readInt();
        int e2 = data.readInt();
        int e3 = data.readInt();
        this.spwner = e1 == 0 ? this.spwner : this.field_70170_p.func_73045_a(e1);
        this.target = e2 == 0 ? this.target : this.field_70170_p.func_73045_a(e2);
        this.field_70789_a = e3 == 0 ? this.field_70789_a : this.field_70170_p.func_73045_a(e3);
    }
}

