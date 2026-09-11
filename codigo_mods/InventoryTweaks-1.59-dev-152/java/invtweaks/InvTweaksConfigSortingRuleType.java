/*
 * Decompiled with CFR 0.152.
 */
package invtweaks;

public enum InvTweaksConfigSortingRuleType {
    RECTANGLE(1),
    ROW(2),
    COLUMN(3),
    SLOT(4);

    private int lowestPriority;
    private int highestPriority;

    private InvTweaksConfigSortingRuleType(int priorityLevel) {
        this.lowestPriority = priorityLevel * 1000000;
        this.highestPriority = (priorityLevel + 1) * 1000000 - 1;
    }

    public int getLowestPriority() {
        return this.lowestPriority;
    }

    public int getHighestPriority() {
        return this.highestPriority;
    }
}

