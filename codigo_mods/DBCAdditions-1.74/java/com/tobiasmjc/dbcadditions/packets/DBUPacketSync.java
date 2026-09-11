/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.ByteBufUtils
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 */
package com.tobiasmjc.dbcadditions.packets;

import com.tobiasmjc.dbcadditions.DBCAConfig;
import com.tobiasmjc.dbcadditions.data.forms.DBCAForm;
import com.tobiasmjc.dbcadditions.data.forms.DBCAForms;
import com.tobiasmjc.dbcadditions.data.forms.FormItem;
import com.tobiasmjc.dbcadditions.data.forms.FormItemsDBA;
import com.tobiasmjc.dbcadditions.data.races.DBCARace;
import com.tobiasmjc.dbcadditions.data.races.DBCARaces;
import com.tobiasmjc.dbcadditions.data.skills.DBCASkill;
import com.tobiasmjc.dbcadditions.data.skills.DBCASkills;
import com.tobiasmjc.dbcadditions.packets.DBUMessageHandler;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class DBUPacketSync
implements IMessage {
    private NBTTagCompound data;
    private byte type;

    public DBUPacketSync() {
    }

    public DBUPacketSync(byte type, NBTTagCompound data) {
        this.type = type;
        this.data = data;
    }

    public void toBytes(ByteBuf buffer) {
        buffer.writeByte((int)this.type);
        ByteBufUtils.writeTag((ByteBuf)buffer, (NBTTagCompound)this.data);
    }

    public void fromBytes(ByteBuf buffer) {
        this.type = buffer.readByte();
        this.data = ByteBufUtils.readTag((ByteBuf)buffer);
    }

    public static class Handler
    extends DBUMessageHandler<DBUPacketSync> {
        @Override
        public void onClientSide(DBUPacketSync message) {
            if (message.type == 0) {
                NBTTagList list = (NBTTagList)message.data.func_74781_a("Forms");
                for (int i = 0; i < list.func_74745_c(); ++i) {
                    NBTTagCompound formData = list.func_150305_b(i);
                    FormItem form = FormItem.read(formData);
                    FormItemsDBA.FormItems.add(form);
                }
            } else if (message.type == 1) {
                NBTTagList list = (NBTTagList)message.data.func_74781_a("Forms");
                for (int i = 0; i < list.func_74745_c(); ++i) {
                    NBTTagCompound formData = list.func_150305_b(i);
                    DBCAForm form = DBCAForm.read(formData);
                    DBCAForms.FORMS.add(form);
                }
            } else if (message.type == 2) {
                NBTTagList list = (NBTTagList)message.data.func_74781_a("Skills");
                for (int i = 0; i < list.func_74745_c(); ++i) {
                    NBTTagCompound skillData = list.func_150305_b(i);
                    DBCASkill skill = DBCASkill.read(skillData);
                    DBCASkills.SKILLS.add(skill);
                }
            } else if (message.type == 3) {
                NBTTagList list = (NBTTagList)message.data.func_74781_a("Races");
                for (int i = 0; i < list.func_74745_c(); ++i) {
                    NBTTagCompound raceData = list.func_150305_b(i);
                    byte id = raceData.func_74771_c("ID");
                    int[] tpCosts = raceData.func_74759_k("TPCosts");
                    int[] mindCosts = raceData.func_74759_k("MindCosts");
                    DBCARace race = DBCARaces.getRace(id);
                    race.setBaseMultiplier(raceData.func_74769_h("Multiplier"));
                    race.setTPCosts(tpCosts);
                    race.setMindCosts(mindCosts);
                }
            } else if (message.type == 4) {
                NBTTagCompound data = message.data;
                DBCAConfig.SaiyanMaxRacial = data.func_74771_c("SaiyanRacial");
                DBCAConfig.HumanMaxRacial = data.func_74771_c("HumanRacial");
                DBCAConfig.ArcosianMaxRacial = data.func_74771_c("ArcosianRacial");
                DBCAConfig.NamekianMaxRacial = data.func_74771_c("NamekianRacial");
                DBCAConfig.MajinMaxRacial = data.func_74771_c("MajinRacial");
                DBCAConfig.SaiyanTPCosts = data.func_74759_k("SaiyanTPCosts");
                DBCAConfig.HumanTPCosts = data.func_74759_k("HumanTPCosts");
                DBCAConfig.ArcosianTPCosts = data.func_74759_k("ArcosianTPCosts");
                DBCAConfig.NamekianTPCosts = data.func_74759_k("NamekianTPCosts");
                DBCAConfig.MajinTPCosts = data.func_74759_k("MajinTPCosts");
                DBCAConfig.SaiyanMindCosts = data.func_74759_k("SaiyanMindCosts");
                DBCAConfig.HumanMindCosts = data.func_74759_k("HumanMindCosts");
                DBCAConfig.ArcosianMindCosts = data.func_74759_k("ArcosianMindCosts");
                DBCAConfig.NamekianMindCosts = data.func_74759_k("NamekianMindCosts");
                DBCAConfig.MajinMindCosts = data.func_74759_k("MajinMindCosts");
                DBCAConfig.PotaraMinLevel = data.func_74762_e("PotaraMinLevel");
                DBCASkills.Beast.setEnabled(DBCAForms.Beast.isEnabled());
                DBCASkills.NamekianPotential.setEnabled(DBCAForms.NamekianPotential.isEnabled() || DBCAForms.Orange.isEnabled());
                DBCASkills.ArcosianPotential.setEnabled(DBCAForms.Black.isEnabled());
            }
        }
    }
}

