/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 */
package noppes.npcs.scripted.entity;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import noppes.npcs.api.entity.IDBCPlayer;
import noppes.npcs.api.item.IItemStack;
import noppes.npcs.scripted.CustomNPCsException;
import noppes.npcs.scripted.NpcAPI;
import noppes.npcs.scripted.entity.ScriptPlayer;

public class ScriptDBCPlayer<T extends EntityPlayerMP>
extends ScriptPlayer<T>
implements IDBCPlayer {
    public T player;

    public ScriptDBCPlayer(T player) {
        super(player);
        this.player = player;
    }

    @Override
    public void setStat(String stat, int value) {
        switch (stat = stat.toLowerCase().trim()) {
            case "str": 
            case "strength": {
                this.player.getEntityData().func_74775_l("PlayerPersisted").func_74768_a("jrmcStrI", value);
                break;
            }
            case "dex": 
            case "dexterity": {
                this.player.getEntityData().func_74775_l("PlayerPersisted").func_74768_a("jrmcDexI", value);
                break;
            }
            case "con": 
            case "constitution": {
                this.player.getEntityData().func_74775_l("PlayerPersisted").func_74768_a("jrmcCnsI", value);
                break;
            }
            case "wil": 
            case "willpower": {
                this.player.getEntityData().func_74775_l("PlayerPersisted").func_74768_a("jrmcWilI", value);
                break;
            }
            case "mnd": 
            case "mind": {
                this.player.getEntityData().func_74775_l("PlayerPersisted").func_74768_a("jrmcIntI", value);
                break;
            }
            case "spi": 
            case "spirit": {
                this.player.getEntityData().func_74775_l("PlayerPersisted").func_74768_a("jrmcCncI", value);
            }
        }
    }

    @Override
    public int getStat(String stat) {
        switch (stat = stat.toLowerCase().trim()) {
            case "str": 
            case "strength": {
                return this.player.getEntityData().func_74775_l("PlayerPersisted").func_74762_e("jrmcStrI");
            }
            case "dex": 
            case "dexterity": {
                return this.player.getEntityData().func_74775_l("PlayerPersisted").func_74762_e("jrmcDexI");
            }
            case "con": 
            case "constitution": {
                return this.player.getEntityData().func_74775_l("PlayerPersisted").func_74762_e("jrmcCnsI");
            }
            case "wil": 
            case "willpower": {
                return this.player.getEntityData().func_74775_l("PlayerPersisted").func_74762_e("jrmcWilI");
            }
            case "mnd": 
            case "mind": {
                return this.player.getEntityData().func_74775_l("PlayerPersisted").func_74762_e("jrmcIntI");
            }
            case "spi": 
            case "spirit": {
                return this.player.getEntityData().func_74775_l("PlayerPersisted").func_74762_e("jrmcCncI");
            }
        }
        throw new CustomNPCsException("Invalid stat name: " + stat + "\nValid stat names are:\nstr, dex, con, wil, mnd, spi\nstrength, dexterity, constitution, willpower, mind, spirit", new Object[0]);
    }

    private double applyOperator(String method, double n1, double n2) {
        if (method.equals("+")) {
            n1 += n2;
        } else if (method.equals("-")) {
            n1 -= n2;
        } else if (method.equals("*")) {
            n1 *= n2;
        } else if (method.equals("/")) {
            n1 /= n2;
        } else if (method.equals("%")) {
            n1 %= n2;
        }
        return n1;
    }

    @Override
    public void addBonusAttribute(String stat, String bonusID, String operation, double attributeValue) {
        this.addBonusAttribute(stat, bonusID, operation, attributeValue, true);
    }

    @Override
    public void addBonusAttribute(String stat, String bonusID, String operation, double attributeValue, boolean endOfTheList) {
        this.bonusAttribute("add", stat, bonusID, operation, attributeValue, endOfTheList);
    }

    @Override
    public void addToBonusAttribute(String stat, String bonusID, String operation, double attributeValue) {
        this.bonusAttribute("addto", stat, bonusID, operation, attributeValue, true);
    }

    @Override
    public void setBonusAttribute(String stat, String bonusID, String operation, double attributeValue) {
        this.bonusAttribute("set", stat, bonusID, operation, attributeValue, true);
    }

    @Override
    public void getBonusAttribute(String stat, String bonusID) {
        this.bonusAttribute("get", stat, bonusID, "*", 1.0, true);
    }

    @Override
    public void removeBonusAttribute(String stat, String bonusID) {
        this.bonusAttribute("remove", stat, bonusID, "*", 1.0, true);
    }

    @Override
    public void clearBonusAttribute(String stat) {
        this.bonusAttribute("clear", stat, "", "*", 1.0, true);
    }

    @Override
    public String bonusAttribute(String action, String stat, String bonusID) {
        String[] actions = new String[]{"get", "remove", "clear"};
        boolean valid = false;
        for (String s : actions) {
            if (!s.equals(action.toLowerCase())) continue;
            valid = true;
        }
        if (!valid) {
            throw new CustomNPCsException("Action can be:  get/remove/clear", new Object[0]);
        }
        return this.bonusAttribute(action, stat, bonusID, "*", 1.0, false);
    }

    @Override
    public String bonusAttribute(String action, String stat, String bonusID, String operation, double attributeValue, boolean endOfTheList) {
        String[] actions = new String[]{"add", "addto", "set", "get", "remove", "clear"};
        String[] operations = new String[]{"+", "-", "*", "/", "%"};
        boolean valid = false;
        for (String string : actions) {
            if (!string.equals(action.toLowerCase())) continue;
            valid = true;
        }
        if (!valid) {
            throw new CustomNPCsException("Action can be:  add/addTo/set/get/remove/clear", new Object[0]);
        }
        if (!(action.equals("remove") || action.equals("get") || action.equals("clear"))) {
            valid = false;
            for (String string : operations) {
                if (!string.equals(operation)) continue;
                valid = true;
            }
            if (!valid) {
                throw new CustomNPCsException("Operation can be:  +  -  *  /  %", new Object[0]);
            }
        }
        stat = stat.toLowerCase().trim();
        switch (stat) {
            case "strength": {
                stat = "str";
                break;
            }
            case "dexterity": {
                stat = "dex";
                break;
            }
            case "constitution": {
                stat = "con";
                break;
            }
            case "willpower": {
                stat = "wil";
                break;
            }
            case "mind": {
                stat = "mnd";
                break;
            }
            case "spirit": {
                stat = "spi";
            }
        }
        if (!(stat.equals("str") || stat.equals("dex") || stat.equals("con") || stat.equals("wil") || stat.equals("mnd") || stat.equals("spi"))) {
            throw new CustomNPCsException("Invalid stat name: " + stat + "\nValid stat names are:\nstr, dex, con, wil, mnd, spi\nstrength, dexterity, constitution, willpower, mind, spirit", new Object[0]);
        }
        String bonusValueString = operation + attributeValue;
        String bonus = this.player.getEntityData().func_74775_l("PlayerPersisted").func_74779_i("jrmcAttrBonus" + stat);
        String[] bonuses = bonus.split("\\|");
        String[][] stringArray = new String[bonuses.length][2];
        if (bonuses.length > 0 && bonuses[0].length() > 0) {
            for (int i = 0; i < bonuses.length; ++i) {
                String[] bonusValue = bonuses[i].split("\\;");
                stringArray[i][0] = bonusValue[0];
                stringArray[i][1] = bonusValue[1];
            }
        }
        switch (action) {
            case "get": {
                if (this.player.getEntityData().func_74775_l("PlayerPersisted").func_74764_b("jrmcAttrBonus" + stat)) {
                    for (String[] s : stringArray) {
                        if (!s[0].equals(bonusID)) continue;
                        return s[1];
                    }
                }
                return "";
            }
            case "clear": {
                if (!this.player.getEntityData().func_74775_l("PlayerPersisted").func_74764_b("jrmcAttrBonus" + stat)) break;
                this.player.getEntityData().func_74775_l("PlayerPersisted").func_74778_a("jrmcAttrBonus" + stat, "");
                break;
            }
            case "remove": {
                boolean number;
                String newBonusString = "";
                if (!this.player.getEntityData().func_74775_l("PlayerPersisted").func_74764_b("jrmcAttrBonus" + stat)) break;
                int num = -1;
                boolean run = false;
                try {
                    num = Integer.parseInt(bonusID);
                    number = true;
                }
                catch (Exception var33) {
                    number = false;
                }
                for (int i = 0; i < bonuses.length; ++i) {
                    String[] bonusValue = bonuses[i].split("\\;");
                    stringArray[i][0] = bonusValue[0];
                    if ((!number || i != num) && (number || !stringArray[i][0].equals(bonusID))) continue;
                    bonuses[i] = "";
                    run = true;
                    break;
                }
                if (!run) break;
                String startString = "";
                for (int i = 0; i < bonuses.length; ++i) {
                    if (bonuses[i] == null || bonuses[i].length() <= 0) continue;
                    startString = startString + bonuses[i] + (bonuses.length - 1 == i ? "" : "|");
                }
                this.player.getEntityData().func_74775_l("PlayerPersisted").func_74778_a("jrmcAttrBonus" + stat, startString);
                break;
            }
            case "add": {
                boolean nbtFail = false;
                for (int id = 0; id < bonuses.length; ++id) {
                    String[] bonusValue = bonuses[id].split("\\;");
                    stringArray[id][0] = bonusValue[0];
                    if (!stringArray[id][0].equals(bonusID)) continue;
                    nbtFail = true;
                    break;
                }
                if (nbtFail) break;
                bonus = endOfTheList ? (bonus.length() == 0 ? bonusID + ";" + bonusValueString : bonus + (bonus.charAt(bonus.length() - 1) == '|' ? "" : "|") + bonusID + ";" + bonusValueString) : (bonus.length() == 0 ? bonusID + ";" + bonusValueString : bonusID + ";" + bonusValueString + "|" + bonus);
                this.player.getEntityData().func_74775_l("PlayerPersisted").func_74778_a("jrmcAttrBonus" + stat, bonus);
                break;
            }
            case "set": {
                int num = -1;
                boolean number = false;
                boolean run = false;
                try {
                    num = Integer.parseInt(bonusID);
                    number = true;
                }
                catch (Exception var34) {
                    number = false;
                }
                for (int startIndex = 0; startIndex < bonuses.length; ++startIndex) {
                    String[] bonusValue = bonuses[startIndex].split("\\;");
                    stringArray[startIndex][0] = bonusValue[0];
                    if ((!number || startIndex != num) && (number || !stringArray[startIndex][0].equals(bonusID))) continue;
                    String noNBTText = stringArray[startIndex][0] + ";" + bonusValueString;
                    bonuses[startIndex] = "";
                    run = true;
                    bonuses[startIndex] = noNBTText;
                    bonusValue = bonuses[startIndex].split("\\;");
                    stringArray[startIndex][0] = bonusValue[0];
                    stringArray[startIndex][1] = bonusValue[1];
                    break;
                }
                if (!run) break;
                String startString = "";
                for (int i = 0; i < bonuses.length; ++i) {
                    if (bonuses[i] == null || bonuses[i].length() <= 0) continue;
                    startString = startString + bonuses[i] + (bonuses.length - 1 == i ? "" : "|");
                }
                this.player.getEntityData().func_74775_l("PlayerPersisted").func_74778_a("jrmcAttrBonus" + stat, startString);
                break;
            }
            case "addto": {
                boolean number;
                boolean run = false;
                int id = -1;
                try {
                    id = Integer.parseInt(bonusID);
                    number = true;
                }
                catch (Exception var35) {
                    number = false;
                }
                for (int i = 0; i < bonuses.length; ++i) {
                    String data;
                    String[] bonusValue = bonuses[i].split("\\;");
                    stringArray[i][0] = bonusValue[0];
                    if ((!number || i != id) && (number || !stringArray[i][0].equals(bonusID))) continue;
                    if (stringArray[i][1].contains("nbt_") || stringArray[i][1].contains("NBT_") || bonusValueString.contains("nbt_") || bonusValueString.contains("NBT_")) break;
                    double value = Double.parseDouble(stringArray[i][1].substring(1));
                    double resultValue = this.applyOperator(operation, value, attributeValue);
                    String result = stringArray[i][1].substring(0, 1) + resultValue;
                    bonuses[i] = data = stringArray[i][0] + ";" + result;
                    bonusValue = bonuses[i].split("\\;");
                    stringArray[i][0] = bonusValue[0];
                    stringArray[i][1] = bonusValue[1];
                    run = true;
                    break;
                }
                if (!run) break;
                String startString = "";
                for (int i = 0; i < bonuses.length; ++i) {
                    if (bonuses[i] == null || bonuses[i].length() <= 0) continue;
                    startString = startString + bonuses[i] + (bonuses.length - 1 == i ? "" : "|");
                }
                this.player.getEntityData().func_74775_l("PlayerPersisted").func_74778_a("jrmcAttrBonus" + stat, startString);
                break;
            }
        }
        return "";
    }

    @Override
    public void setRelease(byte release) {
        this.player.getEntityData().func_74775_l("PlayerPersisted").func_74774_a("jrmcRelease", release);
    }

    @Override
    public byte getRelease() {
        return this.player.getEntityData().func_74775_l("PlayerPersisted").func_74771_c("jrmcRelease");
    }

    @Override
    public void setBody(int body) {
        this.player.getEntityData().func_74775_l("PlayerPersisted").func_74768_a("jrmcBdy", body);
    }

    @Override
    public int getBody() {
        return this.player.getEntityData().func_74775_l("PlayerPersisted").func_74762_e("jrmcBdy");
    }

    @Override
    public void setHP(int hp) {
        this.setBody(hp);
    }

    @Override
    public int getHP() {
        return this.getBody();
    }

    @Override
    public void setStamina(int stamina) {
        this.player.getEntityData().func_74775_l("PlayerPersisted").func_74768_a("jrmcStamina", stamina);
    }

    @Override
    public int getStamina() {
        return this.player.getEntityData().func_74775_l("PlayerPersisted").func_74762_e("jrmcStamina");
    }

    @Override
    public void setKi(int ki) {
        this.player.getEntityData().func_74775_l("PlayerPersisted").func_74768_a("jrmcEnrgy", ki);
    }

    @Override
    public int getKi() {
        return this.player.getEntityData().func_74775_l("PlayerPersisted").func_74762_e("jrmcEnrgy");
    }

    @Override
    public void setTP(int tp) {
        this.player.getEntityData().func_74775_l("PlayerPersisted").func_74768_a("jrmcTpint", tp);
    }

    @Override
    public int getTP() {
        return this.player.getEntityData().func_74775_l("PlayerPersisted").func_74762_e("jrmcTpint");
    }

    @Override
    public void setGravity(float gravity) {
        this.player.getEntityData().func_74775_l("PlayerPersisted").func_74776_a("jrmcGravForce", gravity);
    }

    @Override
    public float getGravity() {
        return this.player.getEntityData().func_74775_l("PlayerPersisted").func_74760_g("jrmcGravForce");
    }

    @Override
    public boolean isBlocking() {
        NBTTagCompound compound = new NBTTagCompound();
        this.player.func_70109_d(compound);
        return compound.func_74775_l("JRMCEP").func_74762_e("blocking") == 1;
    }

    @Override
    public void setHairCode(String hairCode) {
        NBTTagCompound compound = new NBTTagCompound();
        this.player.func_70109_d(compound);
        compound.func_74775_l("JRMCEP").func_74778_a("haircode", hairCode);
    }

    @Override
    public String getHairCode() {
        NBTTagCompound compound = new NBTTagCompound();
        this.player.func_70109_d(compound);
        return compound.func_74775_l("JRMCEP").func_74779_i("haircode");
    }

    @Override
    public void setExtraCode(String extraCode) {
        NBTTagCompound compound = new NBTTagCompound();
        this.player.func_70109_d(compound);
        compound.func_74775_l("JRMCEP").func_74778_a("extracode", extraCode);
    }

    @Override
    public String getExtraCode() {
        NBTTagCompound compound = new NBTTagCompound();
        this.player.func_70109_d(compound);
        return compound.func_74775_l("JRMCEP").func_74779_i("extracode");
    }

    @Override
    public void setItem(IItemStack itemStack, byte slot, boolean vanity) {
        NBTTagCompound compound = new NBTTagCompound();
        this.player.func_70109_d(compound);
        byte offset = (byte)(vanity ? 10 : 2);
        NBTTagList newList = new NBTTagList();
        NBTTagList list = compound.func_74775_l("JRMCEP").func_150295_c("dbcExtraInvTag", 10);
        for (int i = 0; i < list.func_74745_c(); ++i) {
            if (offset - list.func_150305_b(i).func_74771_c("Slot") == slot) continue;
            newList.func_74742_a((NBTBase)list.func_150305_b(i));
        }
        NBTTagCompound compoundItem = new NBTTagCompound();
        if (itemStack != null) {
            itemStack.getMCItemStack().func_77955_b(compoundItem);
        }
        compoundItem.func_74774_a("Slot", (byte)(offset - slot));
        newList.func_74742_a((NBTBase)compoundItem);
        compound.func_74775_l("JRMCEP").func_74782_a("dbcExtraInvTag", (NBTBase)newList);
        this.player.func_70020_e(compound);
    }

    @Override
    public IItemStack getItem(byte slot, boolean vanity) {
        NBTTagCompound compound = new NBTTagCompound();
        this.player.func_70109_d(compound);
        byte offset = (byte)(vanity ? 10 : 2);
        NBTTagList list = compound.func_74775_l("JRMCEP").func_150295_c("dbcExtraInvTag", 10);
        for (int i = 0; i < list.func_74745_c(); ++i) {
            if (offset - list.func_150305_b(i).func_74771_c("Slot") != slot) continue;
            return NpcAPI.Instance().getIItemStack(ItemStack.func_77949_a((NBTTagCompound)list.func_150305_b(i)));
        }
        return null;
    }

    @Override
    public IItemStack[] getInventory() {
        IItemStack[] baseInventory = super.getInventory();
        NBTTagCompound compound = new NBTTagCompound();
        this.player.func_70109_d(compound);
        NBTTagList extraList = compound.func_74775_l("JRMCEP").func_150295_c("dbcExtraInvTag", 10);
        IItemStack[] extraInventory = new IItemStack[extraList.func_74745_c()];
        for (int i = 0; i < extraList.func_74745_c(); ++i) {
            ItemStack itemStack = ItemStack.func_77949_a((NBTTagCompound)extraList.func_150305_b(i));
            extraInventory[i] = NpcAPI.Instance().getIItemStack(itemStack);
        }
        IItemStack[] fullInventory = new IItemStack[baseInventory.length + extraInventory.length];
        System.arraycopy(baseInventory, 0, fullInventory, 0, baseInventory.length);
        System.arraycopy(extraInventory, 0, fullInventory, baseInventory.length, extraInventory.length);
        return fullInventory;
    }

    @Override
    public void setForm(byte form) {
        if (form < 0) {
            return;
        }
        switch (this.getRace()) {
            case 0: 
            case 3: {
                if (form <= 3) break;
                return;
            }
            case 1: 
            case 2: {
                if (form <= 15) break;
                return;
            }
            case 4: {
                if (form <= 7) break;
                return;
            }
        }
        this.player.getEntityData().func_74775_l("PlayerPersisted").func_74774_a("jrmcState", form);
    }

    @Override
    public byte getForm() {
        return this.player.getEntityData().func_74775_l("PlayerPersisted").func_74771_c("jrmcState");
    }

    @Override
    public void setForm2(byte form2) {
        this.player.getEntityData().func_74775_l("PlayerPersisted").func_74774_a("jrmcState2", form2);
    }

    @Override
    public byte getForm2() {
        return this.player.getEntityData().func_74775_l("PlayerPersisted").func_74771_c("jrmcState2");
    }

    @Override
    public double getRacialFormMastery(byte form) {
        try {
            String formTree = this.getRacialTreeName();
            if (this.player.getEntityData().func_74775_l("PlayerPersisted").func_74764_b("jrmcFormMastery" + formTree)) {
                String[] formMasteries = this.player.getEntityData().func_74775_l("PlayerPersisted").func_74779_i("jrmcFormMastery" + formTree).split(";");
                String masteryString = formMasteries[form];
                return Double.parseDouble(masteryString.split(",")[1]);
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        return 0.0;
    }

    @Override
    public void setRacialFormMastery(byte form, double value) {
        try {
            String formTree = this.getRacialTreeName();
            if (this.player.getEntityData().func_74775_l("PlayerPersisted").func_74764_b("jrmcFormMastery" + formTree)) {
                String[] formMasteries = this.player.getEntityData().func_74775_l("PlayerPersisted").func_74779_i("jrmcFormMastery" + formTree).split(";");
                if (form >= formMasteries.length) {
                    return;
                }
                formMasteries[form] = formMasteries[form].split(",")[0] + "," + value;
                StringBuilder masteryString = new StringBuilder();
                for (int i = 0; i < formMasteries.length; ++i) {
                    masteryString.append(formMasteries[i]);
                    if (i >= formMasteries.length - 1) continue;
                    masteryString.append(';');
                }
                this.player.getEntityData().func_74775_l("PlayerPersisted").func_74778_a("jrmcFormMastery" + formTree, String.valueOf(masteryString));
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    @Override
    public void addRacialFormMastery(byte form, double value) {
        try {
            String formTree = this.getRacialTreeName();
            if (this.player.getEntityData().func_74775_l("PlayerPersisted").func_74764_b("jrmcFormMastery" + formTree)) {
                String[] formMasteries = this.player.getEntityData().func_74775_l("PlayerPersisted").func_74779_i("jrmcFormMastery" + formTree).split(";");
                if (form >= formMasteries.length) {
                    return;
                }
                double newMastery = Double.parseDouble(formMasteries[form].split(",")[1]) + value;
                formMasteries[form] = formMasteries[form].split(",")[0] + "," + newMastery;
                StringBuilder masteryString = new StringBuilder();
                for (int i = 0; i < formMasteries.length; ++i) {
                    masteryString.append(formMasteries[i]);
                    if (i >= formMasteries.length - 1) continue;
                    masteryString.append(';');
                }
                this.player.getEntityData().func_74775_l("PlayerPersisted").func_74778_a("jrmcFormMastery" + formTree, String.valueOf(masteryString));
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    private String getRacialTreeName() {
        switch (this.getRace()) {
            case 0: {
                return "Racial_Human";
            }
            case 1: {
                return "Racial_Saiyan";
            }
            case 2: {
                return "Racial_Half-Saiyan";
            }
            case 3: {
                return "Racial_Namekian";
            }
            case 4: {
                return "Racial_Arcosian";
            }
            case 5: {
                return "Racial_Majin";
            }
        }
        return "Racial_Human";
    }

    @Override
    public double getOtherFormMastery(String formName) {
        try {
            if (this.player.getEntityData().func_74775_l("PlayerPersisted").func_74764_b("jrmcFormMasteryNonRacial")) {
                String[] formMasteries;
                String masteryData = this.player.getEntityData().func_74775_l("PlayerPersisted").func_74779_i("jrmcFormMasteryNonRacial");
                if (!masteryData.contains(formName)) {
                    return 0.0;
                }
                for (String masteryString : formMasteries = masteryData.split(";")) {
                    if (!masteryString.split(",")[0].equals(formName)) continue;
                    return Double.parseDouble(masteryString.split(",")[1]);
                }
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        return 0.0;
    }

    @Override
    public void setOtherFormMastery(String formName, double value) {
        try {
            if (this.player.getEntityData().func_74775_l("PlayerPersisted").func_74764_b("jrmcFormMasteryNonRacial")) {
                String masteryData = this.player.getEntityData().func_74775_l("PlayerPersisted").func_74779_i("jrmcFormMasteryNonRacial");
                if (!masteryData.contains(formName)) {
                    return;
                }
                String[] formMasteries = masteryData.split(";");
                for (int i = 0; i < formMasteries.length; ++i) {
                    String name = formMasteries[i].split(",")[0];
                    if (!name.equals(formName)) continue;
                    formMasteries[i] = name + "," + value;
                }
                StringBuilder masteryString = new StringBuilder();
                for (int i = 0; i < formMasteries.length; ++i) {
                    masteryString.append(formMasteries[i]);
                    if (i >= formMasteries.length - 1) continue;
                    masteryString.append(';');
                }
                this.player.getEntityData().func_74775_l("PlayerPersisted").func_74778_a("jrmcFormMasteryNonRacial", String.valueOf(masteryString));
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    @Override
    public void addOtherFormMastery(String formName, double value) {
        try {
            if (this.player.getEntityData().func_74775_l("PlayerPersisted").func_74764_b("jrmcFormMasteryNonRacial")) {
                String masteryData = this.player.getEntityData().func_74775_l("PlayerPersisted").func_74779_i("jrmcFormMasteryNonRacial");
                if (!masteryData.contains(formName)) {
                    return;
                }
                String[] formMasteries = masteryData.split(";");
                for (int i = 0; i < formMasteries.length; ++i) {
                    String name = formMasteries[i].split(",")[0];
                    if (!name.equals(formName)) continue;
                    double newMastery = Double.parseDouble(formMasteries[i].split(",")[1]) + value;
                    formMasteries[i] = name + "," + newMastery;
                }
                StringBuilder masteryString = new StringBuilder();
                for (int i = 0; i < formMasteries.length; ++i) {
                    masteryString.append(formMasteries[i]);
                    if (i >= formMasteries.length - 1) continue;
                    masteryString.append(';');
                }
                this.player.getEntityData().func_74775_l("PlayerPersisted").func_74778_a("jrmcFormMasteryNonRacial", String.valueOf(masteryString));
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    @Override
    public void setPowerPoints(int points) {
        this.player.getEntityData().func_74775_l("PlayerPersisted").func_74768_a("jrmcArcRsrv", points);
    }

    @Override
    public int getPowerPoints() {
        return this.player.getEntityData().func_74775_l("PlayerPersisted").func_74762_e("jrmcArcRsrv");
    }

    @Override
    public void setAuraColor(int color) {
        this.player.getEntityData().func_74775_l("PlayerPersisted").func_74768_a("jrmcAuraColor", color);
    }

    @Override
    public int getAuraColor() {
        return this.player.getEntityData().func_74775_l("PlayerPersisted").func_74762_e("jrmcAuraColor");
    }

    @Override
    public void setFormLevel(int level) {
        this.player.getEntityData().func_74775_l("PlayerPersisted").func_74778_a("jrmcSSltX", "TR" + level);
    }

    @Override
    public int getFormLevel() {
        String formString = this.player.getEntityData().func_74775_l("PlayerPersisted").func_74779_i("jrmcSSltX");
        if (formString.length() < 1) {
            return 0;
        }
        StringBuilder digitString = new StringBuilder();
        for (char c : formString.toCharArray()) {
            if (!Character.isDigit(c)) continue;
            digitString.append(c);
        }
        if (digitString.length() < 1) {
            return 0;
        }
        return Integer.parseInt(digitString.toString());
    }

    @Override
    public void setSkills(String skills) {
        this.player.getEntityData().func_74775_l("PlayerPersisted").func_74778_a("jrmcSSlts", skills);
    }

    @Override
    public String getSkills() {
        return this.player.getEntityData().func_74775_l("PlayerPersisted").func_74779_i("jrmcSSlts");
    }

    @Override
    public void setJRMCSE(String statusEffects) {
        this.player.getEntityData().func_74775_l("PlayerPersisted").func_74778_a("jrmcStatusEff", statusEffects);
    }

    @Override
    public String getJRMCSE() {
        return this.player.getEntityData().func_74775_l("PlayerPersisted").func_74779_i("jrmcStatusEff");
    }

    @Override
    public byte getDBCClass() {
        return this.player.getEntityData().func_74775_l("PlayerPersisted").func_74771_c("jrmcClass");
    }

    @Override
    public void setDBCClass(byte dbcClass) {
        if (dbcClass < 0 || dbcClass > 2) {
            return;
        }
        this.player.getEntityData().func_74775_l("PlayerPersisted").func_74774_a("jrmcClass", dbcClass);
    }

    @Override
    public int getRace() {
        return this.player.getEntityData().func_74775_l("PlayerPersisted").func_74771_c("jrmcRace");
    }

    @Override
    public void setRace(byte race) {
        if (race < 0 || race > 5) {
            return;
        }
        this.player.getEntityData().func_74775_l("PlayerPersisted").func_74774_a("jrmcRace", race);
    }

    @Override
    public int getPowerType() {
        return this.player.getEntityData().func_74775_l("PlayerPersisted").func_74771_c("jrmcPwrtyp");
    }

    @Override
    public void setPowerType(byte powerType) {
        if (powerType < 0 || powerType > 1) {
            return;
        }
        this.player.getEntityData().func_74775_l("PlayerPersisted").func_74774_a("jrmcPwrtyp", powerType);
    }

    @Override
    public int getKillCount(String type) {
        type = type.toLowerCase().trim();
        int evilKills = this.player.getEntityData().func_74775_l("PlayerPersisted").func_74762_e("jrmcKillCountEvil");
        int goodKills = this.player.getEntityData().func_74775_l("PlayerPersisted").func_74762_e("jrmcKillCountGood");
        int neutKills = this.player.getEntityData().func_74775_l("PlayerPersisted").func_74762_e("jrmcKillCountNeut");
        switch (type) {
            case "evil": {
                return evilKills;
            }
            case "good": {
                return goodKills;
            }
            case "neutral": {
                return neutKills;
            }
            case "all": {
                return evilKills + goodKills + neutKills;
            }
        }
        throw new CustomNPCsException("Invalid kill type: " + type + "\nValid kill types are: evil, good, neutral, all", new Object[0]);
    }

    @Override
    public String getFusionString() {
        if (this.player.getEntityData().func_74775_l("PlayerPersisted").func_74764_b("jrmcFuzion")) {
            return this.player.getEntityData().func_74775_l("PlayerPersisted").func_74779_i("jrmcFuzion").trim();
        }
        return "";
    }
}

