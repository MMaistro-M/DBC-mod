/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.EnumChatFormatting
 */
package noppes.npcs.controllers.data;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import noppes.npcs.constants.EnumAuctionSort;

public class AuctionFilter {
    private String searchText = "";
    public EnumAuctionSort sortBy = EnumAuctionSort.NEWEST;
    private String normalizedSearch = "";

    public boolean hasSearchText() {
        return this.searchText != null && !this.searchText.trim().isEmpty();
    }

    public static String normalizeForSearch(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }
        String stripped = EnumChatFormatting.func_110646_a((String)input);
        return stripped != null ? stripped.toLowerCase().trim() : input.toLowerCase().trim();
    }

    public boolean matchesSearch(String itemName, String sellerName) {
        if (!this.hasSearchText()) {
            return true;
        }
        String normalizedItem = AuctionFilter.normalizeForSearch(itemName);
        String normalizedSeller = AuctionFilter.normalizeForSearch(sellerName);
        return normalizedItem.contains(this.normalizedSearch) || normalizedSeller.contains(this.normalizedSearch);
    }

    public boolean matchesSearch(String itemName) {
        return this.matchesSearch(itemName, "");
    }

    public boolean matchesSearchAdvanced(String itemName, String sellerName) {
        String[] words;
        if (!this.hasSearchText()) {
            return true;
        }
        String normalizedItem = AuctionFilter.normalizeForSearch(itemName);
        String normalizedSeller = AuctionFilter.normalizeForSearch(sellerName);
        for (String word : words = this.normalizedSearch.split("\\s+")) {
            if (word.isEmpty() || normalizedItem.contains(word) || normalizedSeller.contains(word)) continue;
            return false;
        }
        return true;
    }

    public boolean matchesSearchAdvanced(String itemName) {
        return this.matchesSearchAdvanced(itemName, "");
    }

    public void reset() {
        this.searchText = "";
        this.sortBy = EnumAuctionSort.NEWEST;
        this.normalizedSearch = "";
    }

    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.func_74778_a("SearchText", this.searchText != null ? this.searchText : "");
        compound.func_74768_a("SortBy", this.sortBy.ordinal());
        return compound;
    }

    public void readFromNBT(NBTTagCompound compound) {
        this.searchText = compound.func_74779_i("SearchText");
        this.normalizedSearch = AuctionFilter.normalizeForSearch(this.searchText);
        this.sortBy = EnumAuctionSort.fromOrdinal(compound.func_74762_e("SortBy"));
    }

    public static AuctionFilter fromNBT(NBTTagCompound compound) {
        AuctionFilter filter = new AuctionFilter();
        filter.readFromNBT(compound);
        return filter;
    }

    public AuctionFilter copy() {
        AuctionFilter copy = new AuctionFilter();
        copy.searchText = this.searchText;
        copy.normalizedSearch = this.normalizedSearch;
        copy.sortBy = this.sortBy;
        return copy;
    }

    public String getSearchText() {
        return this.searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
        this.normalizedSearch = AuctionFilter.normalizeForSearch(searchText);
    }

    public EnumAuctionSort getSortBy() {
        return this.sortBy;
    }

    public void setSortBy(EnumAuctionSort sortBy) {
        this.sortBy = sortBy;
    }
}

