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

public class GuiModelBody
extends GuiModelInterface {
    private GuiScreen parent;
    private final String[] arrBody = new String[]{"gui.no", "gui.yes"};
    private final String[] arrBodywear = new String[]{"gui.no", "gui.yes", "Solid"};
    private final String[] arrWing = new String[]{"gui.no", "Player", "Type1", "Type2", "Type3", "Type4", "Type5", "Type6", "Type7", "Type8", "Type9", "Type10", "Type11", "Type12", "Type13", "Type14", "Type15"};
    private final String[] arrBreasts = new String[]{"gui.no", "Type1", "Type2", "Type3"};
    private final String[] arrParticles = new String[]{"gui.no", "Player", "Type1", "Type2", "Rainbow", "Type3", "Type4", "Type5", "Type6", "Type7"};
    private final String[] arrfins = new String[]{"gui.no", "Player", "Type1", "Type2", "Type3", "Type4", "Type5", "Type6"};
    private final String[] arrskirt = new String[]{"gui.no", "Player", "Type1"};

    public GuiModelBody(GuiScreen parent, EntityCustomNpc npc) {
        super(npc);
        this.parent = parent;
        this.xOffset = 60;
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        int y = this.guiTop + 20;
        this.addButton(new GuiNpcButton(30, this.guiLeft + 50, y += 22, 70, 20, this.arrBody, (int)this.playerdata.hideBody));
        this.addLabel(new GuiNpcLabel(30, "Hide", this.guiLeft, y + 5, 0xFFFFFF));
        if (this.npc.display.modelType == 1 || this.npc.display.modelType == 2) {
            this.addButton(new GuiNpcButton(8, this.guiLeft + 50, y += 22, 70, 20, this.arrBodywear, (int)this.playerdata.bodywear));
            this.addLabel(new GuiNpcLabel(8, "Bodywear", this.guiLeft, y + 5, 0xFFFFFF));
        }
        this.addButton(new GuiNpcButton(1, this.guiLeft + 50, y += 22, 70, 20, this.arrBreasts, (int)this.playerdata.breasts));
        this.addLabel(new GuiNpcLabel(1, "Breasts", this.guiLeft, y + 5, 0xFFFFFF));
        ModelPartData wing = this.playerdata.getPartData("wings");
        this.addButton(new GuiNpcButton(0, this.guiLeft + 50, y += 22, 70, 20, this.arrWing, wing == null ? 0 : wing.type + 1));
        this.addLabel(new GuiNpcLabel(0, "Wings", this.guiLeft, y + 5, 0xFFFFFF));
        if (wing != null) {
            this.addButton(new GuiNpcButton(11, this.guiLeft + 122, y, 40, 20, wing.getColor()));
        }
        ModelPartData particles = this.playerdata.getPartData("particles");
        this.addButton(new GuiNpcButton(2, this.guiLeft + 50, y += 22, 70, 20, this.arrParticles, this.getParticleIndex(particles)));
        this.addLabel(new GuiNpcLabel(2, "Particles", this.guiLeft, y + 5, 0xFFFFFF));
        if (particles != null && particles.type != 1) {
            this.addButton(new GuiNpcButton(12, this.guiLeft + 122, y, 40, 20, particles.getColor()));
        }
        ModelPartData fin = this.playerdata.getPartData("fin");
        this.addButton(new GuiNpcButton(3, this.guiLeft + 50, y += 22, 70, 20, this.arrfins, this.getFinIndex(fin)));
        this.addLabel(new GuiNpcLabel(3, "Fin", this.guiLeft, y + 5, 0xFFFFFF));
        if (fin != null) {
            this.addButton(new GuiNpcButton(13, this.guiLeft + 122, y, 40, 20, fin.getColor()));
        }
        ModelPartData skirt = this.playerdata.getPartData("skirt");
        this.addButton(new GuiNpcButton(4, this.guiLeft + 50, y += 22, 70, 20, this.arrskirt, this.getSkirtindex(skirt)));
        this.addLabel(new GuiNpcLabel(4, "Skirt", this.guiLeft, y + 5, 0xFFFFFF));
        if (skirt != null) {
            this.addButton(new GuiNpcButton(14, this.guiLeft + 122, y, 40, 20, skirt.getColor()));
        }
    }

    private int getFinIndex(ModelPartData fin) {
        if (fin == null) {
            return 0;
        }
        if (fin.type == 0) {
            if (fin.playerTexture) {
                return 1;
            }
            if (fin.texture.contains("1")) {
                return 2;
            }
            if (fin.texture.contains("2")) {
                return 3;
            }
            if (fin.texture.contains("3")) {
                return 4;
            }
            if (fin.texture.contains("4")) {
                return 5;
            }
            if (fin.texture.contains("5")) {
                return 6;
            }
            if (fin.texture.contains("6")) {
                return 7;
            }
        }
        return 0;
    }

    private int getSkirtindex(ModelPartData skirt) {
        if (skirt == null) {
            return 0;
        }
        if (skirt.type == 0) {
            if (skirt.playerTexture) {
                return 1;
            }
            if (skirt.texture.contains("1")) {
                return 2;
            }
            if (skirt.texture.contains("2")) {
                return 3;
            }
            if (skirt.texture.contains("3")) {
                return 4;
            }
            if (skirt.texture.contains("4")) {
                return 5;
            }
            if (skirt.texture.contains("5")) {
                return 6;
            }
            if (skirt.texture.contains("6")) {
                return 7;
            }
        }
        return 0;
    }

    private int getParticleIndex(ModelPartData particles) {
        if (particles == null) {
            return 0;
        }
        if (particles.type == 0) {
            if (particles.playerTexture) {
                return 1;
            }
            if (particles.texture.contains("1")) {
                return 2;
            }
            if (particles.texture.contains("2")) {
                return 3;
            }
            if (particles.texture.contains("3")) {
                return 5;
            }
            if (particles.texture.contains("4")) {
                return 6;
            }
            if (particles.texture.contains("5")) {
                return 7;
            }
            if (particles.texture.contains("6")) {
                return 8;
            }
            if (particles.texture.contains("7")) {
                return 9;
            }
        }
        if (particles.type == 1) {
            return 4;
        }
        return 0;
    }

    @Override
    protected void func_146284_a(GuiButton btn) {
        ModelPartData particles;
        super.func_146284_a(btn);
        GuiNpcButton button = (GuiNpcButton)btn;
        if (button.field_146127_k == 0) {
            if (button.getValue() == 0) {
                this.playerdata.removePart("wings");
            } else {
                ModelPartData data = this.playerdata.getOrCreatePart("wings");
                if (button.getValue() > 1) {
                    data.setTexture("wings/wing" + (button.getValue() - 1), button.getValue() - 1);
                }
            }
            this.func_73866_w_();
        }
        if (button.field_146127_k == 1) {
            this.playerdata.breasts = (byte)button.getValue();
        }
        if (button.field_146127_k == 2) {
            int value = button.getValue();
            if (value == 0) {
                this.playerdata.removePart("particles");
            } else {
                particles = this.playerdata.getOrCreatePart("particles");
                if (value == 1) {
                    particles.setTexture("", 0);
                }
                if (value == 2) {
                    particles.setTexture("particle/type1", 0);
                }
                if (value == 3) {
                    particles.setTexture("particle/type2", 0);
                }
                if (value == 4) {
                    particles.setTexture("", 1);
                }
                if (value == 5) {
                    particles.setTexture("particle/type3", 0);
                }
                if (value == 6) {
                    particles.setTexture("particle/type4", 0);
                }
                if (value == 7) {
                    particles.setTexture("particle/type5", 0);
                }
                if (value == 8) {
                    particles.setTexture("particle/type6", 0);
                }
                if (value == 9) {
                    particles.setTexture("particle/type7", 0);
                }
            }
            this.func_73866_w_();
        }
        if (button.field_146127_k == 3) {
            int value = button.getValue();
            if (value == 0) {
                this.playerdata.removePart("fin");
            } else {
                particles = this.playerdata.getOrCreatePart("fin");
                if (value == 1) {
                    particles.setTexture("", 0);
                }
                if (value == 2) {
                    particles.setTexture("fin/fin1", 0);
                }
                if (value == 3) {
                    particles.setTexture("fin/fin2", 0);
                }
                if (value == 4) {
                    particles.setTexture("fin/fin3", 0);
                }
                if (value == 5) {
                    particles.setTexture("fin/fin4", 0);
                }
                if (value == 6) {
                    particles.setTexture("fin/fin5", 0);
                }
                if (value == 7) {
                    particles.setTexture("fin/fin6", 0);
                }
            }
            this.func_73866_w_();
        }
        if (button.field_146127_k == 4) {
            int value = button.getValue();
            if (value == 0) {
                this.playerdata.removePart("skirt");
            } else {
                ModelPartData skirt = this.playerdata.getOrCreatePart("skirt");
                if (value == 1) {
                    skirt.setTexture("", 0);
                }
                if (value == 2) {
                    skirt.setTexture("skirt/skirt1", 0);
                }
            }
            this.func_73866_w_();
        }
        if (button.field_146127_k == 8) {
            this.playerdata.bodywear = (byte)button.getValue();
        }
        if (button.field_146127_k == 30) {
            this.playerdata.hideBody = (byte)button.getValue();
        }
        if (button.field_146127_k == 11) {
            this.field_146297_k.func_147108_a((GuiScreen)new GuiModelColor(this, this.playerdata.getPartData("wings"), this.npc));
        }
        if (button.field_146127_k == 12) {
            this.field_146297_k.func_147108_a((GuiScreen)new GuiModelColor(this, this.playerdata.getPartData("particles"), this.npc));
        }
        if (button.field_146127_k == 13) {
            this.field_146297_k.func_147108_a((GuiScreen)new GuiModelColor(this, this.playerdata.getPartData("fin"), this.npc));
        }
        if (button.field_146127_k == 14) {
            this.field_146297_k.func_147108_a((GuiScreen)new GuiModelColor(this, this.playerdata.getPartData("skirt"), this.npc));
        }
    }

    @Override
    public void close() {
        this.field_146297_k.func_147108_a(this.parent);
    }
}

