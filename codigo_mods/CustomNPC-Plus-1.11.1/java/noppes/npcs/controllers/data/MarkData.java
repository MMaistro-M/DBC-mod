/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.world.World
 *  net.minecraftforge.common.IExtendedEntityProperties
 */
package noppes.npcs.controllers.data;

import java.util.ArrayList;
import java.util.List;
import kamkeel.npcs.network.PacketHandler;
import kamkeel.npcs.network.packets.data.MarkDataPacket;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import noppes.npcs.api.entity.data.IMark;
import noppes.npcs.api.handler.data.IAvailability;
import noppes.npcs.controllers.data.Availability;
import noppes.npcs.entity.EntityNPCInterface;

public class MarkData
implements IExtendedEntityProperties {
    private static final String CNPCMARK = "cnpcmark";
    private EntityLivingBase entity;
    public List<Mark> marks = new ArrayList<Mark>();

    public void setNBT(NBTTagCompound compound) {
        ArrayList<Mark> marks = new ArrayList<Mark>();
        NBTTagList list = compound.func_150295_c("marks", 10);
        for (int i = 0; i < list.func_74745_c(); ++i) {
            NBTTagCompound c = list.func_150305_b(i);
            Mark m = new Mark();
            m.type = c.func_74762_e("type");
            m.color = c.func_74762_e("color");
            m.availability.readFromNBT(c.func_74775_l("availability"));
            marks.add(m);
        }
        this.marks = marks;
    }

    public NBTTagCompound getNBT() {
        NBTTagCompound compound = new NBTTagCompound();
        NBTTagList list = new NBTTagList();
        for (Mark m : this.marks) {
            NBTTagCompound c = new NBTTagCompound();
            c.func_74768_a("type", m.type);
            c.func_74768_a("color", m.color);
            c.func_74782_a("availability", (NBTBase)m.availability.writeToNBT(new NBTTagCompound()));
            list.func_74742_a((NBTBase)c);
        }
        compound.func_74782_a("marks", (NBTBase)list);
        return compound;
    }

    public static boolean has(EntityLivingBase entity) {
        return entity.getExtendedProperties(CNPCMARK) != null;
    }

    public static MarkData get(EntityNPCInterface npc) {
        if (!MarkData.has((EntityLivingBase)npc)) {
            npc.registerExtendedProperties(CNPCMARK, new MarkData());
        }
        MarkData data = (MarkData)npc.getExtendedProperties(CNPCMARK);
        if (data.entity == null) {
            data.entity = npc;
            data.setNBT(npc.getEntityData().func_74775_l(CNPCMARK));
        }
        return data;
    }

    public IMark addMark(int type) {
        Mark m = new Mark();
        m.type = type;
        this.marks.add(m);
        if (!this.entity.field_70170_p.field_72995_K) {
            this.syncClients();
        }
        return m;
    }

    public IMark addMark(int type, int color) {
        Mark m = new Mark();
        m.type = type;
        m.color = color;
        this.marks.add(m);
        if (!this.entity.field_70170_p.field_72995_K) {
            this.syncClients();
        }
        return m;
    }

    public void saveNBTData(NBTTagCompound compound) {
        if (!(this.entity instanceof EntityNPCInterface)) {
            return;
        }
        this.entity.getEntityData().func_74782_a(CNPCMARK, (NBTBase)this.getNBT());
    }

    public void loadNBTData(NBTTagCompound compound) {
    }

    public void init(Entity entity, World world) {
    }

    public void syncClients() {
        PacketHandler.Instance.sendToAll(new MarkDataPacket(this.entity.func_145782_y(), this.getNBT()));
    }

    public class Mark
    implements IMark {
        public int type = 0;
        public Availability availability = new Availability();
        public int color = 16772433;

        @Override
        public IAvailability getAvailability() {
            return this.availability;
        }

        @Override
        public int getColor() {
            return this.color;
        }

        @Override
        public void setColor(int color) {
            this.color = color;
        }

        @Override
        public int getType() {
            return this.type;
        }

        @Override
        public void setType(int type) {
            this.type = type;
        }

        @Override
        public void update() {
            MarkData.this.syncClients();
        }
    }
}

