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
import noppes.npcs.client.gui.util.GuiButtonBiDirectional;
import noppes.npcs.client.gui.util.GuiModelInterface;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.entity.EntityCustomNpc;
import noppes.npcs.entity.data.ModelPartData;

public class GuiModelLegs
extends GuiModelInterface {
    private GuiScreen parent;
    private final String[] arrLeg = new String[]{"gui.no", "Both", "Right", "Left"};
    private final String[] arrLegwear = new String[]{"gui.no", "Both", "Left", "Right"};
    private final String[] arrSolidLegwear = new String[]{"gui.no", "Both", "Left", "Right"};
    private final String[] arrLegs = new String[]{"gui.no", "Player", "Player Naga", "Spider", "Horse", "Naga", "Mermaid", "Mermaid 2", "Digitigrade"};
    private final String[] arrTail = new String[]{"gui.no", "Player", "Player Dragon", "Cat", "Wolf", "Horse", "Dragon", "Squirrel", "Fin", "Rodent", "Feather", "Fox", "Monkey"};

    public GuiModelLegs(GuiScreen parent, EntityCustomNpc npc) {
        super(npc);
        this.parent = parent;
        this.xOffset = 60;
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        int y = this.guiTop + 20;
        this.addButton(new GuiNpcButton(30, this.guiLeft + 50, y += 22, 70, 20, this.arrLeg, (int)this.playerdata.hideLegs));
        this.addLabel(new GuiNpcLabel(30, "Hide", this.guiLeft, y + 5, 0xFFFFFF));
        if (this.npc.display.modelType == 1 || this.npc.display.modelType == 2) {
            this.addButton(new GuiNpcButton(8, this.guiLeft + 50, y += 22, 70, 20, this.arrLegwear, (int)this.playerdata.legwear));
            this.addLabel(new GuiNpcLabel(8, "Legwear", this.guiLeft, y + 5, 0xFFFFFF));
            this.addButton(new GuiNpcButton(9, this.guiLeft + 50, y += 22, 70, 20, this.arrSolidLegwear, (int)this.playerdata.solidLegwear));
            this.addLabel(new GuiNpcLabel(9, "Solid", this.guiLeft, y + 5, 0xFFFFFF));
        }
        this.addButton(new GuiNpcButton(1, this.guiLeft + 50, y += 22, 70, 20, this.arrLegs, this.getLegIndex(this.playerdata.legParts)));
        this.addLabel(new GuiNpcLabel(1, "Legs", this.guiLeft, y + 5, 0xFFFFFF));
        if (this.playerdata.legParts.type > 0) {
            this.addButton(new GuiNpcButton(11, this.guiLeft + 122, y, 40, 20, this.playerdata.legParts.getColor()));
        }
        ModelPartData tail = this.playerdata.getPartData("tail");
        this.addButton(new GuiNpcButton(2, this.guiLeft + 50, y += 22, 70, 20, this.arrTail, this.getTailIndex(tail)));
        this.addLabel(new GuiNpcLabel(2, "Tail", this.guiLeft, y + 5, 0xFFFFFF));
        if (tail != null) {
            this.addButton(new GuiNpcButton(12, this.guiLeft + 122, y, 40, 20, tail.getColor()));
        }
        if (tail != null && tail.type == 8) {
            this.addLabel(new GuiNpcLabel(22, "Pattern", this.guiLeft, (y += 22) + 5, 0xFFFFFF));
            this.addButton(new GuiButtonBiDirectional(22, this.guiLeft + 50, y, 100, 20, new String[]{"Normal", "Wrapped", "Large"}, (int)tail.pattern));
        }
    }

    private int getLegIndex(ModelPartData data) {
        if (!data.playerTexture && data.type == 1) {
            return 5;
        }
        if (data.type == 4) {
            return 6;
        }
        if (data.type == 5) {
            return 8;
        }
        return data.type + 1;
    }

    private int getTailIndex(ModelPartData data) {
        if (data == null) {
            return 0;
        }
        if (data.playerTexture && data.type == 0) {
            return 1;
        }
        if (data.type == 0 && data.texture.contains("tail1")) {
            return 3;
        }
        if (data.type == 0 && data.texture.contains("tail2")) {
            return 4;
        }
        if (data.playerTexture && data.type == 1) {
            return 2;
        }
        if (data.type == 1) {
            return 6;
        }
        if (data.type == 2) {
            return 5;
        }
        if (data.type == 3) {
            return 7;
        }
        if (data.type == 4) {
            return 8;
        }
        if (data.type == 5) {
            return 9;
        }
        if (data.type == 6) {
            return 10;
        }
        if (data.type == 7) {
            return 11;
        }
        if (data.type == 8) {
            return 12;
        }
        return 0;
    }

    @Override
    protected void func_146284_a(GuiButton btn) {
        super.func_146284_a(btn);
        GuiNpcButton button = (GuiNpcButton)btn;
        if (button.field_146127_k == 8) {
            this.playerdata.legwear = (byte)button.getValue();
        }
        if (button.field_146127_k == 9) {
            this.playerdata.solidLegwear = (byte)button.getValue();
        }
        if (button.field_146127_k == 30) {
            this.playerdata.hideLegs = (byte)button.getValue();
        }
        if (button.field_146127_k == 1) {
            ModelPartData data = this.playerdata.legParts;
            int value = button.getValue() - 1;
            if (value < 1) {
                data.color = 0xFFFFFF;
            }
            if (value < 2) {
                data.setTexture("", value);
            }
            if (value == 2) {
                data.setTexture("legs/spider1", 2);
            }
            if (value == 3) {
                data.setTexture("legs/horse1", 3);
            }
            if (value == 4) {
                if (this.npc.display.modelType == 1 || this.npc.display.modelType == 2) {
                    data.setTexture("legs/naga2", 1);
                } else {
                    data.setTexture("legs/naga1", 1);
                }
            }
            if (value == 5) {
                data.setTexture("legs/mermaid1", 4);
            }
            if (value == 6) {
                data.setTexture("legs/mermaid1", 6);
            }
            if (value == 7) {
                data.setTexture("", 5);
            }
            this.func_73866_w_();
        }
        if (button.field_146127_k == 2) {
            int value = button.getValue();
            if (value == 0) {
                this.playerdata.removePart("tail");
            } else {
                ModelPartData data = this.playerdata.getOrCreatePart("tail");
                if (value == 1) {
                    // empty if block
                }
                data.setTexture("", 0);
                if (value == 2) {
                    data.setTexture("", 1);
                }
                if (value == 3) {
                    data.setTexture("tail/tail1", 0);
                }
                if (value == 4) {
                    data.setTexture("tail/tail2", 0);
                }
                if (value == 5) {
                    data.setTexture("tail/horse1", 2);
                }
                if (value == 6) {
                    data.setTexture("tail/dragon1", 1);
                }
                if (value == 7) {
                    data.setTexture("tail/squirrel1", 3);
                }
                if (value == 8) {
                    data.setTexture("tail/fin1", 4);
                }
                if (value == 9) {
                    data.setTexture("tail/rodent1", 5);
                }
                if (value == 10) {
                    data.setTexture("tail/feather1", 6);
                }
                if (value == 11) {
                    data.setTexture("tail/fox1", 7);
                }
                if (value == 12) {
                    data.setTexture("tail/monkey1", 8);
                }
                data.pattern = 0;
            }
            this.func_73866_w_();
        }
        if (button.field_146127_k == 22) {
            int value = button.getValue();
            ModelPartData data = this.playerdata.getOrCreatePart("tail");
            data.pattern = (byte)value;
        }
        if (button.field_146127_k == 11) {
            this.field_146297_k.func_147108_a((GuiScreen)new GuiModelColor(this, this.playerdata.legParts, this.npc));
        }
        if (button.field_146127_k == 12) {
            this.field_146297_k.func_147108_a((GuiScreen)new GuiModelColor(this, this.playerdata.getPartData("tail"), this.npc));
        }
    }

    @Override
    public void close() {
        this.field_146297_k.func_147108_a(this.parent);
    }
}

