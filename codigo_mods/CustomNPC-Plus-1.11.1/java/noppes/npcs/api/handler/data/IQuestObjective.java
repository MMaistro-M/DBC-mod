/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.handler.data;

public interface IQuestObjective {
    public int getProgress();

    public void setProgress(int var1);

    public void setPlayerProgress(String var1, int var2);

    public int getMaxProgress();

    public boolean isCompleted();

    public String getText();

    public String getAdditionalText();
}

