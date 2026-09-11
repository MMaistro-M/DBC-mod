/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.handler.data;

import java.util.List;
import noppes.npcs.api.handler.data.IAvailability;
import noppes.npcs.api.handler.data.IDialogCategory;
import noppes.npcs.api.handler.data.IDialogImage;
import noppes.npcs.api.handler.data.IDialogOption;
import noppes.npcs.api.handler.data.IQuest;

public interface IDialog {
    public int getId();

    public String getName();

    public void setName(String var1);

    public String getText();

    public void setText(String var1);

    public IQuest getQuest();

    public void setQuest(IQuest var1);

    public String getCommand();

    public void setCommand(String var1);

    public List<IDialogOption> getOptions();

    public IDialogOption getOption(int var1);

    public IAvailability getAvailability();

    public IDialogCategory getCategory();

    public void setDarkenScreen(boolean var1);

    public boolean getDarkenScreen();

    public void setDisableEsc(boolean var1);

    public boolean getDisableEsc();

    public void setShowWheel(boolean var1);

    public boolean getShowWheel();

    public void setHideNPC(boolean var1);

    public boolean getHideNPC();

    public void setSound(String var1);

    public String getSound();

    public void save();

    public void setColor(int var1);

    public int getColor();

    public void setTitleColor(int var1);

    public int getTitleColor();

    public void renderGradual(boolean var1);

    public boolean renderGradual();

    public void showPreviousBlocks(boolean var1);

    public boolean showPreviousBlocks();

    public void showOptionLine(boolean var1);

    public boolean showOptionLine();

    public void setTextSound(String var1);

    public String getTextSound();

    public void setTextPitch(float var1);

    public float getTextPitch();

    public void setTitlePos(int var1);

    public int getTitlePos();

    public void setNPCScale(float var1);

    public float getNpcScale();

    public void setNpcOffset(int var1, int var2);

    public int getNpcOffsetX();

    public int getNpcOffsetY();

    public void textWidthHeight(int var1, int var2);

    public int getTextWidth();

    public int setTextHeight();

    public void setTextOffset(int var1, int var2);

    public int getTextOffsetX();

    public int getTextOffsetY();

    public void setTitleOffset(int var1, int var2);

    public int getTitleOffsetX();

    public int getTitleOffsetY();

    public void setOptionOffset(int var1, int var2);

    public int getOptionOffsetX();

    public int getOptionOffsetY();

    public void setOptionSpacing(int var1, int var2);

    public int getOptionSpaceX();

    public int getOptionSpaceY();

    public void addImage(int var1, IDialogImage var2);

    public IDialogImage getImage(int var1);

    public IDialogImage createImage();

    public IDialogImage[] getImages();

    public boolean hasImage(int var1);

    public void removeImage(int var1);

    public void clearImages();
}

