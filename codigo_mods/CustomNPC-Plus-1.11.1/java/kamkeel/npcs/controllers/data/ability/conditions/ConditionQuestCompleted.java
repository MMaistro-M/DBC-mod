/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.StatCollector
 */
package kamkeel.npcs.controllers.data.ability.conditions;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import kamkeel.npcs.controllers.data.ability.conditions.AbilityCondition;
import kamkeel.npcs.controllers.data.ability.enums.UserType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;
import noppes.npcs.client.gui.builder.FieldDef;
import noppes.npcs.client.gui.select.GuiQuestSelection;
import noppes.npcs.controllers.QuestController;
import noppes.npcs.controllers.data.PlayerData;
import noppes.npcs.controllers.data.Quest;

public class ConditionQuestCompleted
extends AbilityCondition {
    private int questId = -1;

    public ConditionQuestCompleted() {
        this.typeId = "condition.cnpc.quest_completed";
        this.name = "condition.quest_completed";
        this.userType = UserType.PLAYER_ONLY;
    }

    @Override
    protected boolean checkEntity(EntityLivingBase entity) {
        if (!(entity instanceof EntityPlayer)) {
            return false;
        }
        if (this.questId < 0) {
            return false;
        }
        EntityPlayer player = (EntityPlayer)entity;
        PlayerData data = PlayerData.get(player);
        if (data == null || data.questData == null) {
            return false;
        }
        return data.questData.hasFinishedQuest(this.questId);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void getConditionDefinitions(List<FieldDef> defs) {
        defs.add(FieldDef.subGuiField("condition.select_quest", () -> new GuiQuestSelection(this.questId), gui -> {
            GuiQuestSelection sel = gui;
            if (sel.selectedQuest != null) {
                this.questId = sel.selectedQuest.id;
            }
        }).buttonLabel(() -> {
            if (this.questId < 0) {
                return "None";
            }
            Quest q = QuestController.Instance.quests.get(this.questId);
            return q != null ? q.title : "ID:" + this.questId;
        }).clearable(() -> {
            this.questId = -1;
        }));
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public String getConditionSummary() {
        String filterLabel = StatCollector.func_74838_a((String)this.getFilter().toString());
        String questName = "None";
        if (this.questId >= 0) {
            Quest q = QuestController.Instance.quests.get(this.questId);
            questName = q != null ? q.title : "ID:" + this.questId;
        }
        return "[" + filterLabel + "] Quest: " + questName;
    }

    @Override
    public void writeTypeNBT(NBTTagCompound nbt) {
        nbt.func_74768_a("questId", this.questId);
    }

    @Override
    public void readTypeNBT(NBTTagCompound nbt) {
        this.questId = nbt.func_74762_e("questId");
    }

    @Override
    public boolean isConfigured() {
        return this.questId >= 0;
    }

    public int getQuestId() {
        return this.questId;
    }

    public void setQuestId(int questId) {
        this.questId = questId;
    }
}

