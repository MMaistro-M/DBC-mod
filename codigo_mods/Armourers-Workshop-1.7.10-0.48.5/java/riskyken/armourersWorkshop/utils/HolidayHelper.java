/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.StatCollector
 */
package riskyken.armourersWorkshop.utils;

import java.util.ArrayList;
import java.util.Calendar;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.StatCollector;
import riskyken.armourersWorkshop.common.config.ConfigHandler;
import riskyken.armourersWorkshop.common.items.ModItems;
import riskyken.armourersWorkshop.common.wardrobe.ExPropsPlayerSkinData;

public final class HolidayHelper {
    private static ArrayList<Holiday> holidayList;
    public static final Holiday halloween;
    public static final Holiday halloween_season;
    public static final Holiday christmas;
    public static final Holiday christmas_season;
    public static final Holiday valentins;
    public static final Holiday newYears;
    public static final Holiday ponytailDay;
    public static final Holiday aprilFools;

    public static ArrayList<Holiday> getActiveHolidays() {
        ArrayList<Holiday> activeList = new ArrayList<Holiday>();
        for (int i = 0; i < holidayList.size(); ++i) {
            if (!holidayList.get(i).isHolidayActive()) continue;
            activeList.add(holidayList.get(i));
        }
        return activeList;
    }

    public static int getYear() {
        return Calendar.getInstance().get(1);
    }

    public static void giftPlayer(EntityPlayerMP player) {
        if (christmas_season.isHolidayActive()) {
            ExPropsPlayerSkinData playerData = ExPropsPlayerSkinData.get((EntityPlayer)player);
            if (playerData.lastXmasYear < HolidayHelper.getYear()) {
                ItemStack giftSack = new ItemStack(ModItems.equipmentSkinTemplate, 1, 1000);
                if (!player.field_71071_by.func_70441_a(giftSack)) {
                    player.func_146105_b((IChatComponent)new ChatComponentText(StatCollector.func_74838_a((String)"chat.armourersworkshop:inventoryGiftFail")));
                } else {
                    playerData.lastXmasYear = HolidayHelper.getYear();
                }
            }
        }
    }

    static {
        halloween = new Holiday(31, 9, 0, 24);
        halloween_season = new Holiday(24, 9, 8, 0);
        christmas = new Holiday(25, 11, 0, 24);
        christmas_season = new Holiday(1, 11, 31, 0);
        valentins = new Holiday(14, 1, 1, 0);
        newYears = new Holiday(1, 0, 1, 0);
        ponytailDay = new Holiday(7, 6, 1, 0);
        aprilFools = new Holiday(1, 3, 1, 0);
        holidayList = new ArrayList();
        holidayList.add(halloween);
        holidayList.add(halloween_season);
        holidayList.add(christmas);
        holidayList.add(christmas_season);
        holidayList.add(valentins);
        holidayList.add(newYears);
        holidayList.add(ponytailDay);
        holidayList.add(aprilFools);
    }

    public static class Holiday {
        private final Calendar startDate = Calendar.getInstance();
        private final Calendar endDate;

        public Holiday(int dayOfMonth, int month, int lengthInDays, int lengthInHours) {
            this.startDate.set(12, 0);
            this.startDate.set(11, 0);
            this.startDate.set(2, month);
            this.startDate.set(5, dayOfMonth);
            this.endDate = (Calendar)this.startDate.clone();
            this.endDate.add(11, lengthInDays * 24 + lengthInHours);
        }

        public boolean isHolidayActive() {
            Calendar current = Calendar.getInstance();
            return ConfigHandler.enableHolidayEvents && current.after(this.startDate) & current.before(this.endDate);
        }

        public String toString() {
            return "Holiday [startDate=" + this.startDate + ", endDate=" + this.endDate + "]";
        }
    }
}

