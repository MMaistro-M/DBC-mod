/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 */
package noppes.npcs.client.gui.model;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import noppes.npcs.client.gui.model.GuiModelColor;
import noppes.npcs.client.gui.util.GuiModelInterface;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.entity.EntityCustomNpc;
import noppes.npcs.entity.data.ModelPartData;

public class GuiModelHead
extends GuiModelInterface {
    private GuiScreen parent;
    private final String[] arrHead = new String[]{"gui.no", "gui.yes"};
    private final String[] arrHeadwear = new String[]{"gui.no", "gui.yes", "Solid"};
    private final String[] arrHair = new String[]{"gui.no", "Player", "Long", "Thin", "Stylish", "Ponytail"};
    private final String[] arrBeard = new String[]{"gui.no", "Player", "Standard", "Viking", "Long", "Short"};
    private final String[] arrMohawk = new String[]{"gui.no", "Type1", "Type2", "Type3"};
    private final String[] arrSnout = new String[]{"gui.no", "Player Small", "Player Medium", "Player Large", "Player Bunny", "Small1", "Medium1", "Large1", "Bunny1", "Beak1"};
    private final String[] arrEars = new String[]{"gui.no", "Player", "Player Bunny", "Bunny", "Type1"};
    private final String[] arrHorns = new String[]{"gui.no", "Player Bull", "Player Antlers", "Player AntennasB", "Player AntennasF", "Bull", "Antlers", "AntennasB", "AntennasF"};

    public GuiModelHead(GuiScreen parent, EntityCustomNpc npc) {
        super(npc);
        this.parent = parent;
        this.xOffset = 60;
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        int y = this.guiTop + 20;
        this.addButton(new GuiNpcButton(30, this.guiLeft + 50, y += 22, 70, 20, this.arrHead, (int)this.playerdata.hideHead));
        this.addLabel(new GuiNpcLabel(30, "Hide", this.guiLeft, y + 5, 0xFFFFFF));
        this.addButton(new GuiNpcButton(0, this.guiLeft + 50, y += 22, 70, 20, this.arrHeadwear, (int)this.playerdata.headwear));
        this.addLabel(new GuiNpcLabel(0, "Headwear", this.guiLeft, y + 5, 0xFFFFFF));
        ModelPartData hair = this.playerdata.getPartData("hair");
        this.addButton(new GuiNpcButton(1, this.guiLeft + 50, y += 22, 70, 20, this.arrHair, hair == null ? 0 : hair.type + 1));
        this.addLabel(new GuiNpcLabel(1, "Hair", this.guiLeft, y + 5, 0xFFFFFF));
        if (hair != null) {
            this.addButton(new GuiNpcButton(11, this.guiLeft + 122, y, 40, 20, hair.getColor()));
        }
        ModelPartData mohawk = this.playerdata.getPartData("mohawk");
        this.addButton(new GuiNpcButton(2, this.guiLeft + 50, y += 22, 70, 20, this.arrMohawk, (int)(mohawk == null ? (byte)0 : mohawk.type)));
        this.addLabel(new GuiNpcLabel(2, "Mohawk", this.guiLeft, y + 5, 0xFFFFFF));
        if (mohawk != null) {
            this.addButton(new GuiNpcButton(12, this.guiLeft + 122, y, 40, 20, mohawk.getColor()));
        }
        ModelPartData beard = this.playerdata.getPartData("beard");
        this.addButton(new GuiNpcButton(3, this.guiLeft + 50, y += 22, 70, 20, this.arrBeard, beard == null ? 0 : beard.type + 1));
        this.addLabel(new GuiNpcLabel(3, "Beard", this.guiLeft, y + 5, 0xFFFFFF));
        if (beard != null) {
            this.addButton(new GuiNpcButton(13, this.guiLeft + 122, y, 40, 20, beard.getColor()));
        }
        ModelPartData snout = this.playerdata.getPartData("snout");
        this.addButton(new GuiNpcButton(4, this.guiLeft + 50, y += 22, 70, 20, this.arrSnout, snout == null ? 0 : snout.type + (snout.playerTexture ? 1 : 5)));
        this.addLabel(new GuiNpcLabel(4, "Snout", this.guiLeft, y + 5, 0xFFFFFF));
        if (snout != null) {
            this.addButton(new GuiNpcButton(14, this.guiLeft + 122, y, 40, 20, snout.getColor()));
        }
        ModelPartData ears = this.playerdata.getPartData("ears");
        this.addButton(new GuiNpcButton(5, this.guiLeft + 50, y += 22, 70, 20, this.arrEars, this.getEars(ears)));
        this.addLabel(new GuiNpcLabel(5, "Ears", this.guiLeft, y + 5, 0xFFFFFF));
        if (ears != null) {
            this.addButton(new GuiNpcButton(15, this.guiLeft + 122, y, 40, 20, ears.getColor()));
        }
        ModelPartData horns = this.playerdata.getPartData("horns");
        this.addButton(new GuiNpcButton(6, this.guiLeft + 50, y += 22, 70, 20, this.arrHorns, this.getHorns(horns)));
        this.addLabel(new GuiNpcLabel(6, "Horns", this.guiLeft, y + 5, 0xFFFFFF));
        if (horns != null) {
            this.addButton(new GuiNpcButton(16, this.guiLeft + 122, y, 40, 20, horns.getColor()));
        }
    }

    private int getEars(ModelPartData data) {
        if (data == null) {
            return 0;
        }
        if (data.playerTexture && data.type == 0) {
            return 1;
        }
        if (data.playerTexture && data.type == 1) {
            return 2;
        }
        if (data.type == 0) {
            return 4;
        }
        if (data.type == 1) {
            return 3;
        }
        return 0;
    }

    private int getHorns(ModelPartData data) {
        if (data == null) {
            return 0;
        }
        if (data.playerTexture) {
            return data.type + 1;
        }
        return data.type + 5;
    }

    @Override
    protected void func_146284_a(GuiButton btn) {
        ModelPartData data;
        super.func_146284_a(btn);
        GuiNpcButton button = (GuiNpcButton)btn;
        if (button.field_146127_k == 0) {
            this.playerdata.headwear = (byte)button.getValue();
        }
        if (button.field_146127_k == 30) {
            this.playerdata.hideHead = (byte)button.getValue();
        }
        if (button.field_146127_k == 1) {
            if (button.getValue() == 0) {
                this.playerdata.removePart("hair");
            } else {
                data = this.playerdata.getOrCreatePart("hair");
                if (button.getValue() > 1) {
                    data.setTexture("hair/hair" + (button.getValue() - 1), button.getValue() - 1);
                }
            }
            this.func_73866_w_();
        }
        if (button.field_146127_k == 2) {
            if (button.getValue() == 0) {
                this.playerdata.removePart("mohawk");
            } else {
                data = this.playerdata.getOrCreatePart("mohawk");
                if (button.getValue() > 0) {
                    data.setTexture("hair/mohawk" + button.getValue(), button.getValue());
                }
            }
            this.func_73866_w_();
        }
        if (button.field_146127_k == 3) {
            if (button.getValue() == 0) {
                this.playerdata.removePart("beard");
            } else {
                data = this.playerdata.getOrCreatePart("beard");
                if (button.getValue() > 1) {
                    data.setTexture("beard/beard" + (button.getValue() - 1), button.getValue() - 1);
                }
            }
            this.func_73866_w_();
        }
        if (button.field_146127_k == 4) {
            if (button.getValue() == 0) {
                this.playerdata.removePart("snout");
            } else if (button.getValue() < 5) {
                data = this.playerdata.getOrCreatePart("snout");
                data.type = (byte)(button.getValue() - 1);
            } else {
                data = this.playerdata.getOrCreatePart("snout");
                int type = 0;
                if (button.field_146126_j.startsWith("Medium")) {
                    type = 1;
                }
                if (button.field_146126_j.startsWith("Large")) {
                    type = 2;
                }
                if (button.field_146126_j.startsWith("Bunny")) {
                    type = 3;
                }
                if (button.field_146126_j.startsWith("Beak")) {
                    type = 4;
                }
                data.setTexture("snout/" + button.field_146126_j.toLowerCase(), type);
            }
            this.func_73866_w_();
        }
        if (button.field_146127_k == 5) {
            int value = button.getValue();
            if (value == 0) {
                this.playerdata.removePart("ears");
            } else {
                ModelPartData data2 = this.playerdata.getOrCreatePart("ears");
                if (value == 1) {
                    data2.setTexture("", 0);
                }
                if (value == 2) {
                    data2.setTexture("", 1);
                }
                if (value == 3) {
                    data2.setTexture("ears/bunny1", 1);
                }
                if (value == 4) {
                    data2.setTexture("ears/type1", 0);
                }
            }
            this.func_73866_w_();
        }
        if (button.field_146127_k == 6) {
            int value = button.getValue();
            if (value == 0) {
                this.playerdata.removePart("horns");
            } else {
                ModelPartData data3 = this.playerdata.getOrCreatePart("horns");
                if (value <= 4) {
                    data3.setTexture("", value - 1);
                }
                if (value == 5) {
                    data3.setTexture("horns/bull", 0);
                }
                if (value == 6) {
                    data3.setTexture("horns/antlers", 1);
                }
                if (value == 7) {
                    data3.setTexture("horns/antennas", 2);
                }
                if (value == 8) {
                    data3.setTexture("horns/antennas", 3);
                }
            }
            this.func_73866_w_();
        }
        if (button.field_146127_k == 11) {
            this.field_146297_k.func_147108_a((GuiScreen)new GuiModelColor(this, this.playerdata.getPartData("hair"), this.npc));
        }
        if (button.field_146127_k == 12) {
            this.field_146297_k.func_147108_a((GuiScreen)new GuiModelColor(this, this.playerdata.getPartData("mohawk"), this.npc));
        }
        if (button.field_146127_k == 13) {
            this.field_146297_k.func_147108_a((GuiScreen)new GuiModelColor(this, this.playerdata.getPartData("beard"), this.npc));
        }
        if (button.field_146127_k == 14) {
            this.field_146297_k.func_147108_a((GuiScreen)new GuiModelColor(this, this.playerdata.getPartData("snout"), this.npc));
        }
        if (button.field_146127_k == 15) {
            this.field_146297_k.func_147108_a((GuiScreen)new GuiModelColor(this, this.playerdata.getPartData("ears"), this.npc));
        }
        if (button.field_146127_k == 16) {
            this.field_146297_k.func_147108_a((GuiScreen)new GuiModelColor(this, this.playerdata.getPartData("horns"), this.npc));
        }
    }

    @Override
    public void close() {
        this.field_146297_k.func_147108_a(this.parent);
    }
}

