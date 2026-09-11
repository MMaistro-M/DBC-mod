/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.StatCollector
 */
package noppes.npcs.util;

import net.minecraft.util.StatCollector;
import noppes.npcs.client.AuctionClientConfig;

public class AuctionFormatUtil {
    public static final long MS_PER_SECOND = 1000L;
    public static final long MS_PER_MINUTE = 60000L;
    public static final long MS_PER_HOUR = 3600000L;
    public static final long MS_PER_DAY = 86400000L;

    public static String formatCurrency(long amount) {
        if (amount < 1000L) {
            return "" + amount;
        }
        StringBuilder sb = new StringBuilder();
        String str = "" + amount;
        int count = 0;
        for (int i = str.length() - 1; i >= 0; --i) {
            if (count > 0 && count % 3 == 0) {
                sb.insert(0, ',');
            }
            sb.insert(0, str.charAt(i));
            ++count;
        }
        return sb.toString();
    }

    public static String formatCurrencyWithName(long amount) {
        return AuctionFormatUtil.formatCurrency(amount) + " " + AuctionClientConfig.getCurrencyName();
    }

    public static String formatTimeRemaining(long ms) {
        if (ms <= 0L) {
            return StatCollector.func_74838_a((String)"auction.ended");
        }
        long seconds = ms / 1000L % 60L;
        long minutes = ms / 60000L % 60L;
        long hours = ms / 3600000L % 24L;
        long days = ms / 86400000L;
        StringBuilder sb = new StringBuilder();
        if (days > 0L) {
            sb.append(days).append("d ");
        }
        if (hours > 0L || days > 0L) {
            sb.append(hours).append("h ");
        }
        if (minutes > 0L || hours > 0L || days > 0L) {
            sb.append(minutes).append("m");
        } else {
            sb.append(seconds).append("s");
        }
        return sb.toString();
    }

    public static boolean isTimeUrgent(long ms) {
        return ms > 0L && ms < 3600000L;
    }
}

