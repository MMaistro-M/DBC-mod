/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.client;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.nbt.NBTTagCompound;

@SideOnly(value=Side.CLIENT)
public class AuctionClientConfig {
    private static volatile long listingFee = 0L;
    private static String currencyName = "Coins";
    private static int auctionDurationHours = 24;
    private static volatile double minBidIncrement = 0.05;
    private static int maxActiveListings = 8;
    private static int claimExpirationDays = 20;
    private static boolean auctionEnabled = false;

    public static void readFromNBT(NBTTagCompound compound) {
        if (compound == null) {
            return;
        }
        auctionEnabled = compound.func_74767_n("AuctionEnabled");
        listingFee = compound.func_74763_f("ListingFee");
        currencyName = compound.func_74779_i("CurrencyName");
        auctionDurationHours = compound.func_74762_e("AuctionDurationHours");
        minBidIncrement = compound.func_74769_h("MinBidIncrement");
        maxActiveListings = compound.func_74762_e("MaxActiveListings");
        claimExpirationDays = compound.func_74762_e("ClaimExpirationDays");
        if (currencyName.isEmpty()) {
            currencyName = "Coins";
        }
    }

    public static void reset() {
        listingFee = 0L;
        currencyName = "Coins";
        auctionDurationHours = 24;
        minBidIncrement = 0.05;
        maxActiveListings = 8;
        claimExpirationDays = 20;
        auctionEnabled = false;
    }

    public static boolean isAuctionEnabled() {
        return auctionEnabled;
    }

    public static long getListingFee() {
        return listingFee;
    }

    public static String getCurrencyName() {
        return currencyName;
    }

    public static int getAuctionDurationHours() {
        return auctionDurationHours;
    }

    public static double getMinBidIncrement() {
        return minBidIncrement;
    }

    public static int getMaxActiveListings() {
        return maxActiveListings;
    }

    public static int getClaimExpirationDays() {
        return claimExpirationDays;
    }
}

