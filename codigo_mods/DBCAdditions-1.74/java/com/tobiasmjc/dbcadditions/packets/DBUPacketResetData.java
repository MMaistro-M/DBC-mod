/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  io.netty.buffer.ByteBuf
 */
package com.tobiasmjc.dbcadditions.packets;

import com.tobiasmjc.dbcadditions.DBCAConfig;
import com.tobiasmjc.dbcadditions.data.ability.DBCAAbilities;
import com.tobiasmjc.dbcadditions.data.forms.DBCAForm;
import com.tobiasmjc.dbcadditions.data.forms.DBCAForms;
import com.tobiasmjc.dbcadditions.data.forms.FormItem;
import com.tobiasmjc.dbcadditions.data.forms.FormItemsDBA;
import com.tobiasmjc.dbcadditions.data.races.DBCARace;
import com.tobiasmjc.dbcadditions.data.races.DBCARaces;
import com.tobiasmjc.dbcadditions.data.skills.DBCASkill;
import com.tobiasmjc.dbcadditions.data.skills.DBCASkills;
import com.tobiasmjc.dbcadditions.packets.DBUMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class DBUPacketResetData
implements IMessage {
    public void fromBytes(ByteBuf buf) {
    }

    public void toBytes(ByteBuf buf) {
    }

    public static class Handler
    extends DBUMessageHandler<DBUPacketResetData> {
        @Override
        public void onClientSide(DBUPacketResetData message) {
            DBCAForms.FORMS = new LinkedHashSet<DBCAForm>();
            DBCASkills.SKILLS = new LinkedHashSet<DBCASkill>();
            FormItemsDBA.FormItems = new LinkedHashSet<FormItem>();
            DBCARaces.RACES = new HashSet<DBCARace>();
            for (DBCARace race : DBCARaces.HOST_RACES) {
                DBCARaces.RACES.add(race.copy());
            }
            DBCAConfig.HumanMaxRacial = DBCAConfig.HHumanMaxRacial;
            DBCAConfig.SaiyanMaxRacial = DBCAConfig.HSaiyanMaxRacial;
            DBCAConfig.NamekianMaxRacial = DBCAConfig.HNamekianMaxRacial;
            DBCAConfig.ArcosianMaxRacial = DBCAConfig.HArcosianMaxRacial;
            DBCAConfig.MajinMaxRacial = DBCAConfig.HMajinMaxRacial;
            DBCAConfig.SaiyanTPCosts = (int[])DBCAConfig.HSaiyanTPCosts.clone();
            DBCAConfig.ArcosianTPCosts = (int[])DBCAConfig.HArcosianTPCosts.clone();
            DBCAConfig.HumanTPCosts = (int[])DBCAConfig.HHumanTPCosts.clone();
            DBCAConfig.MajinTPCosts = (int[])DBCAConfig.HMajinTPCosts.clone();
            DBCAConfig.NamekianTPCosts = (int[])DBCAConfig.HNamekianTPCosts.clone();
            DBCAConfig.SaiyanMindCosts = (int[])DBCAConfig.HSaiyanMindCosts.clone();
            DBCAConfig.ArcosianMindCosts = (int[])DBCAConfig.HArcosianMindCosts.clone();
            DBCAConfig.HumanMindCosts = (int[])DBCAConfig.HHumanMindCosts.clone();
            DBCAConfig.MajinMindCosts = (int[])DBCAConfig.HMajinMindCosts.clone();
            DBCAConfig.NamekianMindCosts = (int[])DBCAConfig.HNamekianMindCosts.clone();
            DBCAAbilities.AbsorbingMap.clear();
        }
    }
}

