/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 */
package noppes.npcs.controllers.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import noppes.npcs.ICompatibilty;
import noppes.npcs.VersionCompatibility;
import noppes.npcs.api.handler.data.IAvailability;
import noppes.npcs.api.handler.data.IDialog;
import noppes.npcs.api.handler.data.IDialogCategory;
import noppes.npcs.api.handler.data.IDialogImage;
import noppes.npcs.api.handler.data.IDialogOption;
import noppes.npcs.api.handler.data.IQuest;
import noppes.npcs.config.ConfigMain;
import noppes.npcs.constants.EnumOptionType;
import noppes.npcs.controllers.DialogController;
import noppes.npcs.controllers.QuestController;
import noppes.npcs.controllers.data.Availability;
import noppes.npcs.controllers.data.DialogCategory;
import noppes.npcs.controllers.data.DialogColorData;
import noppes.npcs.controllers.data.DialogImage;
import noppes.npcs.controllers.data.DialogOption;
import noppes.npcs.controllers.data.FactionOptions;
import noppes.npcs.controllers.data.PlayerMail;
import noppes.npcs.controllers.data.Quest;
import noppes.npcs.scripted.CustomNPCsException;

public class Dialog
implements ICompatibilty,
IDialog {
    public int version = VersionCompatibility.ModRev;
    public int id = -1;
    public String title = "";
    public String text = "";
    public int quest = -1;
    public DialogCategory category;
    public DialogColorData colorData = new DialogColorData();
    public HashMap<Integer, DialogOption> options = new HashMap();
    public Availability availability = new Availability();
    public FactionOptions factionOptions = new FactionOptions();
    public String sound;
    public String command = "";
    public PlayerMail mail = new PlayerMail();
    public int color = 0xE0E0E0;
    public int titleColor = 0xE0E0E0;
    public boolean hideNPC = false;
    public boolean showWheel = false;
    public boolean disableEsc = false;
    public boolean darkenScreen = true;
    public boolean showOptionLine = true;
    public byte alignment = 0;
    public boolean renderGradual = false;
    public boolean showPreviousBlocks = true;
    public String textSound = "minecraft:random.wood_click";
    public float textPitch = 1.0f;
    public int textWidth = 300;
    public int textHeight = 400;
    public int titlePos;
    public int textOffsetX;
    public int textOffsetY;
    public int titleOffsetX;
    public int titleOffsetY;
    public int optionSpaceX;
    public int optionSpaceY;
    public int optionOffsetX;
    public int optionOffsetY;
    public float npcScale = 1.0f;
    public int npcOffsetX;
    public int npcOffsetY;
    public HashMap<Integer, IDialogImage> dialogImages = new HashMap();

    public boolean hasDialogs(EntityPlayer player) {
        for (DialogOption option : this.options.values()) {
            if (option == null || option.optionType != EnumOptionType.DialogOption || !option.hasDialog() || !option.isAvailable(player)) continue;
            return true;
        }
        return false;
    }

    public void readNBT(NBTTagCompound compound) {
        this.id = compound.func_74762_e("DialogId");
        this.readNBTPartial(compound);
    }

    public void readNBTPartial(NBTTagCompound compound) {
        this.version = compound.func_74762_e("ModRev");
        VersionCompatibility.CheckAvailabilityCompatibility(this, compound);
        this.title = compound.func_74779_i("DialogTitle");
        this.text = compound.func_74779_i("DialogText");
        this.quest = compound.func_74762_e("DialogQuest");
        this.sound = compound.func_74779_i("DialogSound");
        this.command = compound.func_74779_i("DialogCommand");
        this.mail.readNBT(compound.func_74775_l("DialogMail"));
        this.hideNPC = compound.func_74767_n("DialogHideNPC");
        this.showWheel = compound.func_74764_b("DialogShowWheel") ? compound.func_74767_n("DialogShowWheel") : true;
        this.disableEsc = compound.func_74767_n("DialogDisableEsc");
        this.darkenScreen = compound.func_74764_b("DialogDarkScreen") ? compound.func_74767_n("DialogDarkScreen") : true;
        this.alignment = compound.func_74771_c("DialogAlignment");
        NBTTagList options = compound.func_150295_c("Options", 10);
        HashMap<Integer, DialogOption> newoptions = new HashMap<Integer, DialogOption>();
        for (int iii = 0; iii < options.func_74745_c(); ++iii) {
            NBTTagCompound option = options.func_150305_b(iii);
            int opslot = option.func_74762_e("OptionSlot");
            DialogOption dia = new DialogOption();
            dia.readNBT(option.func_74775_l("Option"));
            newoptions.put(opslot, dia);
        }
        this.options = newoptions;
        NBTTagList images = compound.func_150295_c("Images", 10);
        HashMap<Integer, DialogImage> newImages = new HashMap<Integer, DialogImage>();
        for (int i = 0; i < images.func_74745_c(); ++i) {
            NBTTagCompound imageCompound = images.func_150305_b(i);
            int id = imageCompound.func_74762_e("ID");
            DialogImage image = new DialogImage(id);
            image.readNBT(imageCompound);
            newImages.put(id, image);
        }
        this.dialogImages = newImages;
        this.color = compound.func_74762_e("Color");
        this.titleColor = compound.func_74762_e("TitleColor");
        this.renderGradual = compound.func_74767_n("RenderGradual");
        this.showPreviousBlocks = compound.func_74767_n("PreviousBlocks");
        this.showOptionLine = compound.func_74767_n("ShowOptionLine");
        this.textSound = compound.func_74779_i("TextSound");
        this.textPitch = compound.func_74760_g("TextPitch");
        this.textWidth = compound.func_74762_e("TextWidth");
        this.textHeight = compound.func_74762_e("TextHeight");
        this.textOffsetX = compound.func_74762_e("TextOffsetX");
        this.textOffsetY = compound.func_74762_e("TextOffsetY");
        this.titlePos = compound.func_74762_e("TitlePos");
        this.titleOffsetX = compound.func_74762_e("TitleOffsetX");
        this.titleOffsetY = compound.func_74762_e("TitleOffsetY");
        this.optionOffsetX = compound.func_74762_e("OptionOffsetX");
        this.optionOffsetY = compound.func_74762_e("OptionOffsetY");
        this.optionSpaceX = compound.func_74762_e("OptionSpaceX");
        this.optionSpaceY = compound.func_74762_e("OptionSpaceY");
        this.npcScale = compound.func_74760_g("NPCScale");
        this.npcOffsetX = compound.func_74762_e("NPCOffsetX");
        this.npcOffsetY = compound.func_74762_e("NPCOffsetY");
        if (!compound.func_74764_b("PreviousBlocks")) {
            this.showPreviousBlocks = true;
        }
        if (!compound.func_74764_b("TextSound")) {
            this.textSound = "minecraft:random.wood_click";
        }
        if (!compound.func_74764_b("TextPitch")) {
            this.textPitch = 1.0f;
        }
        if (!compound.func_74764_b("TextWidth")) {
            this.textWidth = 300;
        }
        if (!compound.func_74764_b("TextHeight")) {
            this.textHeight = 400;
        }
        if (!compound.func_74764_b("ShowOptionLine")) {
            this.showOptionLine = true;
        }
        if (!compound.func_74764_b("NPCScale")) {
            this.npcScale = 1.0f;
        }
        if (!compound.func_74764_b("Color")) {
            this.color = 0xE0E0E0;
        }
        if (!compound.func_74764_b("TitleColor")) {
            this.titleColor = 0xE0E0E0;
        }
        this.availability.readFromNBT(compound);
        this.factionOptions.readFromNBT(compound);
        this.colorData.readFromNBT(compound);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.func_74768_a("DialogId", this.id);
        return this.writeToNBTPartial(compound);
    }

    public NBTTagCompound writeToNBTPartial(NBTTagCompound compound) {
        compound.func_74778_a("DialogTitle", this.title);
        compound.func_74778_a("DialogText", this.text);
        compound.func_74768_a("DialogQuest", this.quest);
        compound.func_74778_a("DialogCommand", this.command);
        compound.func_74782_a("DialogMail", (NBTBase)this.mail.writeNBT());
        compound.func_74757_a("DialogHideNPC", this.hideNPC);
        compound.func_74757_a("DialogShowWheel", this.showWheel);
        compound.func_74757_a("DialogDisableEsc", this.disableEsc);
        compound.func_74757_a("DialogDarkScreen", this.darkenScreen);
        compound.func_74774_a("DialogAlignment", this.alignment);
        if (this.sound != null && !this.sound.isEmpty()) {
            compound.func_74778_a("DialogSound", this.sound);
        }
        NBTTagList options = new NBTTagList();
        for (int opslot : this.options.keySet()) {
            NBTTagCompound listcompound = new NBTTagCompound();
            listcompound.func_74768_a("OptionSlot", opslot);
            listcompound.func_74782_a("Option", (NBTBase)this.options.get(opslot).writeNBT());
            options.func_74742_a((NBTBase)listcompound);
        }
        compound.func_74782_a("Options", (NBTBase)options);
        this.availability.writeToNBT(compound);
        this.factionOptions.writeToNBT(compound);
        this.colorData.writeToNBT(compound);
        compound.func_74768_a("ModRev", this.version);
        compound.func_74768_a("Color", this.color);
        compound.func_74768_a("TitleColor", this.titleColor);
        compound.func_74757_a("RenderGradual", this.renderGradual);
        compound.func_74757_a("PreviousBlocks", this.showPreviousBlocks);
        compound.func_74757_a("ShowOptionLine", this.showOptionLine);
        compound.func_74778_a("TextSound", this.textSound);
        compound.func_74776_a("TextPitch", this.textPitch);
        compound.func_74768_a("TextWidth", this.textWidth);
        compound.func_74768_a("TextHeight", this.textHeight);
        compound.func_74768_a("TextOffsetX", this.textOffsetX);
        compound.func_74768_a("TextOffsetY", this.textOffsetY);
        compound.func_74768_a("TitlePos", this.titlePos);
        compound.func_74768_a("TitleOffsetX", this.titleOffsetX);
        compound.func_74768_a("TitleOffsetY", this.titleOffsetY);
        compound.func_74768_a("OptionOffsetX", this.optionOffsetX);
        compound.func_74768_a("OptionOffsetY", this.optionOffsetY);
        compound.func_74768_a("OptionSpaceX", this.optionSpaceX);
        compound.func_74768_a("OptionSpaceY", this.optionSpaceY);
        compound.func_74776_a("NPCScale", this.npcScale);
        compound.func_74768_a("NPCOffsetX", this.npcOffsetX);
        compound.func_74768_a("NPCOffsetY", this.npcOffsetY);
        NBTTagList images = new NBTTagList();
        for (IDialogImage dialogImage : this.dialogImages.values()) {
            NBTTagCompound imageCompound = ((DialogImage)dialogImage).writeToNBT(new NBTTagCompound());
            images.func_74742_a((NBTBase)imageCompound);
        }
        compound.func_74782_a("Images", (NBTBase)images);
        return compound;
    }

    public boolean hasQuest() {
        return this.getQuest() != null;
    }

    public boolean hasOtherOptions() {
        for (DialogOption option : this.options.values()) {
            if (option == null || option.optionType == EnumOptionType.Disabled) continue;
            return true;
        }
        return false;
    }

    public Dialog copy(EntityPlayer player) {
        Dialog dialog = new Dialog();
        dialog.id = this.id;
        dialog.text = this.text;
        dialog.title = this.title;
        dialog.category = this.category;
        dialog.quest = this.quest;
        dialog.sound = this.sound;
        dialog.mail = this.mail;
        dialog.command = this.command;
        dialog.color = this.color;
        dialog.titleColor = this.titleColor;
        dialog.hideNPC = this.hideNPC;
        dialog.showWheel = this.showWheel;
        dialog.disableEsc = this.disableEsc;
        dialog.darkenScreen = this.darkenScreen;
        dialog.renderGradual = this.renderGradual;
        dialog.showPreviousBlocks = this.showPreviousBlocks;
        dialog.showOptionLine = this.showOptionLine;
        dialog.alignment = this.alignment;
        dialog.textSound = this.textSound;
        dialog.textPitch = this.textPitch;
        dialog.textWidth = this.textWidth;
        dialog.textHeight = this.textHeight;
        dialog.textOffsetX = this.textOffsetX;
        dialog.textOffsetY = this.textOffsetY;
        dialog.titlePos = this.titlePos;
        dialog.titleOffsetX = this.titleOffsetX;
        dialog.titleOffsetY = this.titleOffsetY;
        dialog.optionOffsetX = this.optionOffsetX;
        dialog.optionOffsetY = this.optionOffsetY;
        dialog.optionSpaceX = this.optionSpaceX;
        dialog.optionSpaceY = this.optionSpaceY;
        dialog.npcScale = this.npcScale;
        dialog.npcOffsetX = this.npcOffsetX;
        dialog.npcOffsetY = this.npcOffsetY;
        dialog.dialogImages = this.dialogImages;
        dialog.colorData = this.colorData;
        for (int slot : this.options.keySet()) {
            DialogOption option = this.options.get(slot);
            if (option.optionType == EnumOptionType.DialogOption && (!option.hasDialog() || !option.isAvailable(player))) continue;
            dialog.options.put(slot, option);
        }
        return dialog;
    }

    @Override
    public int getVersion() {
        return this.version;
    }

    @Override
    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.title;
    }

    @Override
    public List<IDialogOption> getOptions() {
        return new ArrayList<DialogOption>(this.options.values());
    }

    @Override
    public IDialogOption getOption(int slot) {
        IDialogOption option = (IDialogOption)((Object)this.options.get(slot));
        if (option == null) {
            throw new CustomNPCsException("There is no DialogOption for slot: " + slot, new Object[0]);
        }
        return option;
    }

    @Override
    public IAvailability getAvailability() {
        return this.availability;
    }

    @Override
    public IDialogCategory getCategory() {
        return this.category;
    }

    @Override
    public void save() {
        DialogController.Instance.saveDialog(this.category.id, this);
    }

    @Override
    public void setName(String name) {
        this.title = name;
    }

    @Override
    public String getText() {
        return this.text;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public void setQuest(IQuest quest) {
        if (quest == null) {
            this.quest = -1;
        } else {
            if (quest.getId() < 0) {
                throw new CustomNPCsException("Quest id is lower than 0", new Object[0]);
            }
            this.quest = quest.getId();
        }
    }

    @Override
    public Quest getQuest() {
        return QuestController.Instance == null ? null : QuestController.Instance.quests.get(this.quest);
    }

    @Override
    public String getCommand() {
        return this.command;
    }

    @Override
    public void setCommand(String command) {
        this.command = command;
    }

    @Override
    public void setDarkenScreen(boolean darkenScreen) {
        this.darkenScreen = darkenScreen;
    }

    @Override
    public boolean getDarkenScreen() {
        return this.darkenScreen;
    }

    @Override
    public void setDisableEsc(boolean disableEsc) {
        this.disableEsc = disableEsc;
    }

    @Override
    public boolean getDisableEsc() {
        return this.disableEsc;
    }

    @Override
    public void setShowWheel(boolean showWheel) {
        this.showWheel = showWheel;
    }

    @Override
    public boolean getShowWheel() {
        return this.showWheel;
    }

    @Override
    public void setHideNPC(boolean hideNPC) {
        this.hideNPC = hideNPC;
    }

    @Override
    public boolean getHideNPC() {
        return this.hideNPC;
    }

    @Override
    public void setSound(String sound) {
        this.sound = sound;
    }

    @Override
    public String getSound() {
        return this.sound;
    }

    @Override
    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public int getColor() {
        return this.color;
    }

    @Override
    public void setTitleColor(int titleColor) {
        this.titleColor = titleColor;
    }

    @Override
    public int getTitleColor() {
        return this.titleColor;
    }

    @Override
    public void renderGradual(boolean gradual) {
        this.renderGradual = gradual;
    }

    @Override
    public boolean renderGradual() {
        return this.renderGradual;
    }

    @Override
    public void showPreviousBlocks(boolean show) {
        this.showPreviousBlocks = show;
    }

    @Override
    public boolean showPreviousBlocks() {
        return this.showPreviousBlocks;
    }

    @Override
    public void showOptionLine(boolean show) {
        this.showOptionLine = show;
    }

    @Override
    public boolean showOptionLine() {
        return this.showOptionLine;
    }

    @Override
    public void setTextSound(String textSound) {
        this.textSound = textSound;
    }

    @Override
    public String getTextSound() {
        return this.textSound;
    }

    @Override
    public void setTextPitch(float textPitch) {
        this.textPitch = textPitch;
    }

    @Override
    public float getTextPitch() {
        return this.textPitch;
    }

    @Override
    public void setTitlePos(int pos) {
        this.titlePos = pos;
    }

    @Override
    public int getTitlePos() {
        return this.titlePos;
    }

    @Override
    public void setNPCScale(float scale) {
        this.npcScale = scale;
    }

    @Override
    public float getNpcScale() {
        return this.npcScale;
    }

    @Override
    public void setNpcOffset(int offsetX, int offsetY) {
        this.npcOffsetX = offsetX;
        this.npcOffsetY = offsetY;
    }

    @Override
    public int getNpcOffsetX() {
        return this.npcOffsetX;
    }

    @Override
    public int getNpcOffsetY() {
        return this.npcOffsetY;
    }

    @Override
    public void textWidthHeight(int textWidth, int textHeight) {
        this.textWidth = textWidth;
        this.textHeight = textHeight;
    }

    @Override
    public int getTextWidth() {
        return this.textWidth;
    }

    @Override
    public int setTextHeight() {
        return this.textHeight;
    }

    @Override
    public void setTextOffset(int offsetX, int offsetY) {
        this.textOffsetX = offsetX;
        this.textOffsetY = offsetY;
    }

    @Override
    public int getTextOffsetX() {
        return this.textOffsetX;
    }

    @Override
    public int getTextOffsetY() {
        return this.textOffsetY;
    }

    @Override
    public void setTitleOffset(int offsetX, int offsetY) {
        this.titleOffsetX = offsetX;
        this.titleOffsetY = offsetY;
    }

    @Override
    public int getTitleOffsetX() {
        return this.titleOffsetX;
    }

    @Override
    public int getTitleOffsetY() {
        return this.titleOffsetY;
    }

    @Override
    public void setOptionOffset(int offsetX, int offsetY) {
        this.optionOffsetX = offsetX;
        this.optionOffsetY = offsetY;
    }

    @Override
    public int getOptionOffsetX() {
        return this.optionOffsetX;
    }

    @Override
    public int getOptionOffsetY() {
        return this.optionOffsetY;
    }

    @Override
    public void setOptionSpacing(int spaceX, int spaceY) {
        this.optionSpaceX = spaceX;
        this.optionSpaceY = spaceY;
    }

    @Override
    public int getOptionSpaceX() {
        return this.optionSpaceX;
    }

    @Override
    public int getOptionSpaceY() {
        return this.optionSpaceY;
    }

    @Override
    public void addImage(int id, IDialogImage image) {
        if (this.dialogImages.size() >= ConfigMain.DialogImageLimit) {
            return;
        }
        ((DialogImage)image).id = id;
        this.dialogImages.put(id, image);
    }

    @Override
    public IDialogImage getImage(int id) {
        return this.dialogImages.get(id);
    }

    @Override
    public IDialogImage createImage() {
        return new DialogImage();
    }

    @Override
    public IDialogImage[] getImages() {
        return new ArrayList<IDialogImage>(this.dialogImages.values()).toArray(new IDialogImage[0]);
    }

    @Override
    public boolean hasImage(int id) {
        return this.dialogImages.containsKey(id);
    }

    @Override
    public void removeImage(int id) {
        this.dialogImages.remove(id);
    }

    @Override
    public void clearImages() {
        this.dialogImages.clear();
    }
}

