/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.Logger
 */
package invtweaks;

import invtweaks.InvTweaks;
import invtweaks.InvTweaksConfigSortingRuleType;
import invtweaks.InvTweaksItemTree;
import java.awt.Point;
import org.apache.logging.log4j.Logger;

public class InvTweaksConfigSortingRule
implements Comparable<InvTweaksConfigSortingRule> {
    private static final Logger log = InvTweaks.log;
    private String constraint;
    private int[] preferredPositions;
    private String keyword;
    private InvTweaksConfigSortingRuleType type;
    private int priority;
    private int containerSize;
    private int containerRowSize;

    public InvTweaksConfigSortingRule(InvTweaksItemTree tree, String constraint, String keyword, int containerSize, int containerRowSize) {
        this.keyword = keyword;
        this.constraint = constraint;
        this.containerSize = containerSize;
        this.containerRowSize = containerRowSize;
        this.type = InvTweaksConfigSortingRule.getRuleType(constraint, containerRowSize);
        this.preferredPositions = this.getRulePreferredPositions(constraint);
        this.priority = this.type.getLowestPriority() + 100000 + tree.getKeywordDepth(keyword) * 1000 - tree.getKeywordOrder(keyword);
    }

    public InvTweaksConfigSortingRuleType getType() {
        return this.type;
    }

    public int[] getPreferredSlots() {
        return this.preferredPositions;
    }

    public String getKeyword() {
        return this.keyword;
    }

    public String getRawConstraint() {
        return this.constraint;
    }

    public int getPriority() {
        return this.priority;
    }

    @Override
    public int compareTo(InvTweaksConfigSortingRule o) {
        return this.getPriority() - o.getPriority();
    }

    public int[] getRulePreferredPositions(String constraint) {
        return InvTweaksConfigSortingRule.getRulePreferredPositions(constraint, this.containerSize, this.containerRowSize);
    }

    public static int[] getRulePreferredPositions(String constraint, int containerSize, int containerRowSize) {
        int[] result;
        block15: {
            int i;
            int containerColumnSize;
            block14: {
                String[] elements;
                result = null;
                containerColumnSize = containerSize / containerRowSize;
                if (constraint.length() < 5) break block14;
                boolean vertical = false;
                if (constraint.contains("v")) {
                    vertical = true;
                    constraint = constraint.replaceAll("v", "");
                }
                if ((elements = constraint.split("-")).length != 2) break block15;
                int[] slots1 = InvTweaksConfigSortingRule.getRulePreferredPositions(elements[0], containerSize, containerRowSize);
                int[] slots2 = InvTweaksConfigSortingRule.getRulePreferredPositions(elements[1], containerSize, containerRowSize);
                if (slots1.length != 1 || slots2.length != 1) break block15;
                int slot1 = slots1[0];
                int slot2 = slots2[0];
                Point point1 = new Point(slot1 % containerRowSize, slot1 / containerRowSize);
                Point point2 = new Point(slot2 % containerRowSize, slot2 / containerRowSize);
                result = new int[(Math.abs(point2.y - point1.y) + 1) * (Math.abs(point2.x - point1.x) + 1)];
                int resultIndex = 0;
                if (vertical) {
                    for (Point p : new Point[]{point1, point2}) {
                        int buffer = p.x;
                        p.x = p.y;
                        p.y = buffer;
                    }
                }
                int y = point1.y;
                while (point1.y < point2.y ? y <= point2.y : y >= point2.y) {
                    int x = point1.x;
                    while (point1.x < point2.x ? x <= point2.x : x >= point2.x) {
                        result[resultIndex++] = vertical ? InvTweaksConfigSortingRule.index(containerRowSize, x, y) : InvTweaksConfigSortingRule.index(containerRowSize, y, x);
                        x += point1.x < point2.x ? 1 : -1;
                    }
                    y += point1.y < point2.y ? 1 : -1;
                }
                if (!constraint.contains("r")) break block15;
                InvTweaksConfigSortingRule.reverseArray(result);
                break block15;
            }
            int column = -1;
            int row = -1;
            boolean reverse = false;
            for (i = 0; i < constraint.length(); ++i) {
                char c = constraint.charAt(i);
                if (c >= '1' && c - 49 <= containerRowSize) {
                    column = c - 49;
                    continue;
                }
                if (c >= 'a' && c - 97 <= containerColumnSize) {
                    row = c - 97;
                    continue;
                }
                if (c != 'r') continue;
                reverse = true;
            }
            if (column != -1 && row != -1) {
                result = new int[]{InvTweaksConfigSortingRule.index(containerRowSize, row, column)};
            } else if (row != -1) {
                result = new int[containerRowSize];
                for (i = 0; i < containerRowSize; ++i) {
                    result[i] = InvTweaksConfigSortingRule.index(containerRowSize, row, reverse ? containerRowSize - 1 - i : i);
                }
            } else {
                result = new int[containerColumnSize];
                for (i = 0; i < containerColumnSize; ++i) {
                    result[i] = InvTweaksConfigSortingRule.index(containerRowSize, reverse ? i : containerColumnSize - 1 - i, column);
                }
            }
        }
        return result;
    }

    public static InvTweaksConfigSortingRuleType getRuleType(String constraint, int rowSize) {
        InvTweaksConfigSortingRuleType result = InvTweaksConfigSortingRuleType.SLOT;
        if (constraint.length() == 1 || constraint.length() == 2 && constraint.contains("r")) {
            result = (constraint = constraint.replace("r", "")).charAt(0) - 49 <= rowSize && constraint.charAt(0) >= '1' ? InvTweaksConfigSortingRuleType.COLUMN : InvTweaksConfigSortingRuleType.ROW;
        } else if (constraint.length() > 4) {
            result = constraint.charAt(1) == constraint.charAt(4) ? InvTweaksConfigSortingRuleType.COLUMN : (constraint.charAt(0) == constraint.charAt(3) ? InvTweaksConfigSortingRuleType.ROW : InvTweaksConfigSortingRuleType.RECTANGLE);
        }
        return result;
    }

    public String toString() {
        return this.constraint + " " + this.keyword;
    }

    private static int index(int rowSize, int row, int column) {
        return row * rowSize + column;
    }

    private static void reverseArray(int[] data) {
        int left = 0;
        for (int right = data.length - 1; left < right; ++left, --right) {
            int temp = data[left];
            data[left] = data[right];
            data[right] = temp;
        }
    }
}

