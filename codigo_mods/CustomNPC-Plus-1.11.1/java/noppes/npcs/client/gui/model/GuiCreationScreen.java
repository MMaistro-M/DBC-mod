/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.renderer.entity.RendererLivingEntity
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagByte
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.World
 */
package noppes.npcs.client.gui.model;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kamkeel.npcs.addon.client.GeckoAddonClient;
import kamkeel.npcs.network.PacketClient;
import kamkeel.npcs.network.packets.request.npc.ModelDataSavePacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.entity.NPCRendererHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import noppes.npcs.client.gui.model.GuiEntitySelection;
import noppes.npcs.client.gui.model.GuiModelArms;
import noppes.npcs.client.gui.model.GuiModelBody;
import noppes.npcs.client.gui.model.GuiModelHead;
import noppes.npcs.client.gui.model.GuiModelLegs;
import noppes.npcs.client.gui.model.GuiModelRotate;
import noppes.npcs.client.gui.model.GuiModelScale;
import noppes.npcs.client.gui.model.GuiPresetSave;
import noppes.npcs.client.gui.model.GuiPresetSelection;
import noppes.npcs.client.gui.util.GuiCustomScroll;
import noppes.npcs.client.gui.util.GuiModelInterface;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.ICustomScrollListener;
import noppes.npcs.compat.PixelmonHelper;
import noppes.npcs.entity.EntityCustomNpc;
import noppes.npcs.entity.EntityFakeLiving;
import noppes.npcs.entity.EntityNPCGolem;
import noppes.npcs.entity.EntityNPCInterface;
import noppes.npcs.entity.EntityNpcCrystal;
import noppes.npcs.entity.EntityNpcDragon;
import noppes.npcs.entity.EntityNpcPony;
import noppes.npcs.entity.EntityNpcSlime;

public class GuiCreationScreen
extends GuiModelInterface
implements ICustomScrollListener {
    public HashMap<String, Class<? extends EntityLivingBase>> data = new HashMap();
    private List<String> list;
    private final String[] ignoredTags = new String[]{"CanBreakDoors", "Bred", "PlayerCreated", "Tame", "HasReproduced"};
    private GuiNpcButton prev;
    private GuiNpcButton next;
    private GuiScreen parent;
    private HashMap<Integer, String> mapped = new HashMap();

    public GuiCreationScreen(GuiScreen parent, EntityCustomNpc npc) {
        super(npc);
        this.parent = parent;
        Map mapping = EntityList.field_75625_b;
        for (Object name : mapping.keySet()) {
            Class c = (Class)mapping.get(name);
            try {
                if (EntityCustomNpc.class.isAssignableFrom(c) || !EntityLiving.class.isAssignableFrom(c) || c.getConstructor(World.class) == null || Modifier.isAbstract(c.getModifiers()) || !(RenderManager.field_78727_a.func_78715_a(c) instanceof RendererLivingEntity)) continue;
                if (EntityNpcSlime.class.isAssignableFrom(c)) {
                    this.data.put("CustomNPC Slime", c.asSubclass(EntityLivingBase.class));
                    continue;
                }
                if (EntityNpcDragon.class.isAssignableFrom(c)) {
                    this.data.put("CustomNPC Dragon", c.asSubclass(EntityLivingBase.class));
                    continue;
                }
                if (EntityNpcPony.class.isAssignableFrom(c)) {
                    this.data.put("CustomNPC Pony", c.asSubclass(EntityLivingBase.class));
                    continue;
                }
                if (EntityNPCGolem.class.isAssignableFrom(c)) {
                    this.data.put("CustomNPC Golem", c.asSubclass(EntityLivingBase.class));
                    continue;
                }
                if (EntityNpcCrystal.class.isAssignableFrom(c)) {
                    this.data.put("CustomNPC Crystal", c.asSubclass(EntityLivingBase.class));
                    continue;
                }
                String className = name.toString();
                if (className.equals("npcgecko.CustomModelEntity")) {
                    this.data.put("CustomNPC GeoModel", c.asSubclass(EntityLivingBase.class));
                    continue;
                }
                this.data.put(name.toString(), c.asSubclass(EntityLivingBase.class));
            }
            catch (SecurityException e) {
                e.printStackTrace();
            }
            catch (NoSuchMethodException noSuchMethodException) {}
        }
        this.list = new ArrayList<String>(this.data.keySet());
        Collections.sort(this.list, String.CASE_INSENSITIVE_ORDER);
    }

    @Override
    public void func_73866_w_() {
        EntityLivingBase entity = this.playerdata.getEntity(this.npc);
        this.xOffset = entity == null ? 0 : 50;
        super.func_73866_w_();
        String title = "CustomNPC";
        if (entity != null) {
            title = (String)EntityList.field_75626_c.get(this.playerdata.getEntityClass());
        }
        this.addButton(new GuiNpcButton(1, this.guiLeft + 140, this.guiTop, 100, 20, title));
        this.prev = new GuiNpcButton(0, this.guiLeft + 118, this.guiTop, 20, 20, "<");
        this.addButton(this.prev);
        this.next = new GuiNpcButton(2, this.guiLeft + 242, this.guiTop, 20, 20, ">");
        this.addButton(this.next);
        this.prev.field_146124_l = this.getCurrentEntityIndex() >= 0;
        boolean bl = this.next.field_146124_l = this.getCurrentEntityIndex() < this.list.size() - 1;
        if (entity == null) {
            this.showPlayerButtons();
        } else if (PixelmonHelper.isPixelmon((Entity)entity)) {
            this.showPixelmonMenu(entity);
        } else {
            this.showEntityButtons(entity);
        }
    }

    private void showPlayerButtons() {
        int y = this.guiTop;
        this.addButton(new GuiNpcButton(9, this.guiLeft + 4, y += 22, 96, 20, "model.rotate"));
        this.addButton(new GuiNpcButton(8, this.guiLeft + 4, y += 22, 96, 20, "model.scale"));
        this.addButton(new GuiNpcButton(4, this.guiLeft + 50, y += 22, 50, 20, "selectServer.edit"));
        this.addLabel(new GuiNpcLabel(1, "Head", this.guiLeft, y + 5, 0xFFFFFF));
        this.addButton(new GuiNpcButton(5, this.guiLeft + 50, y += 22, 50, 20, "selectServer.edit"));
        this.addLabel(new GuiNpcLabel(2, "Body", this.guiLeft, y + 5, 0xFFFFFF));
        this.addButton(new GuiNpcButton(6, this.guiLeft + 50, y += 22, 50, 20, "selectServer.edit"));
        this.addLabel(new GuiNpcLabel(3, "Arms", this.guiLeft, y + 5, 0xFFFFFF));
        this.addButton(new GuiNpcButton(7, this.guiLeft + 50, y += 22, 50, 20, "selectServer.edit"));
        this.addLabel(new GuiNpcLabel(4, "Legs", this.guiLeft, y + 5, 0xFFFFFF));
        this.addButton(new GuiNpcButton(250, this.guiLeft + 50, y += 22, 50, 20, new String[]{"Default", "Steve64", "Alex"}, this.npc.display.modelType));
        this.addLabel(new GuiNpcLabel(250, "Model", this.guiLeft, y + 5, 0xFFFFFF));
        this.addButton(new GuiNpcButton(44, this.guiLeft + 310, this.guiTop + 14, 80, 20, "Save Model"));
        this.addButton(new GuiNpcButton(45, this.guiLeft + 310, this.guiTop + 36, 80, 20, "Load Model"));
    }

    private void showPixelmonMenu(EntityLivingBase entity) {
        GuiCustomScroll scroll = new GuiCustomScroll(this, 0);
        scroll.setSize(120, 200);
        scroll.guiLeft = this.guiLeft;
        scroll.guiTop = this.guiTop + 20;
        this.addScroll(scroll);
        scroll.setList(PixelmonHelper.getPixelmonList());
        scroll.setSelected(PixelmonHelper.getName(entity));
        Minecraft.func_71410_x().field_71439_g.func_71165_d(PixelmonHelper.getName(entity));
    }

    private void showEntityButtons(EntityLivingBase entity) {
        this.mapped.clear();
        if (entity instanceof EntityNPCInterface) {
            return;
        }
        int y = this.guiTop + 20;
        NBTTagCompound compound = this.getExtras(entity);
        Set keys = compound.func_150296_c();
        int i = 0;
        for (String name : keys) {
            byte b;
            if (this.isIgnored(name)) continue;
            NBTBase base = compound.func_74781_a(name);
            if (name.equals("Age")) {
                this.addLabel(new GuiNpcLabel(0, "Child", this.guiLeft, y + 5 + ++i * 22, 0xFFFFFF));
                this.addButton(new GuiNpcButton(30, this.guiLeft + 80, y + i * 22, 50, 20, new String[]{"gui.no", "gui.yes"}, entity.func_70631_g_() ? 1 : 0));
                continue;
            }
            if (base.func_74732_a() != 1 || (b = ((NBTTagByte)base).func_150290_f()) != 0 && b != 1) continue;
            if (this.playerdata.extra.func_74764_b(name)) {
                b = this.playerdata.extra.func_74771_c(name);
            }
            this.addLabel(new GuiNpcLabel(100 + ++i, name, this.guiLeft, y + 5 + i * 22, 0xFFFFFF));
            this.addButton(new GuiNpcButton(100 + i, this.guiLeft + 80, y + i * 22, 50, 20, new String[]{"gui.no", "gui.yes"}, (int)b));
            this.mapped.put(i, name);
        }
        if (EntityList.func_75621_b((Entity)entity).equals("doggystyle.Dog")) {
            int breed = 0;
            try {
                Method method = entity.getClass().getMethod("getBreedID", new Class[0]);
                breed = (Integer)method.invoke((Object)entity, new Object[0]);
            }
            catch (Exception exception) {
                // empty catch block
            }
            this.addLabel(new GuiNpcLabel(201, "Breed", this.guiLeft, y + 5 + ++i * 22, 0xFFFFFF));
            this.addButton(new GuiNpcButton(201, this.guiLeft + 80, y + i * 22, 50, 20, new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16"}, breed));
        }
        GeckoAddonClient.Instance.showGeckoButtons(this, entity);
    }

    private boolean isIgnored(String tag) {
        for (String s : this.ignoredTags) {
            if (!s.equals(tag)) continue;
            return true;
        }
        return false;
    }

    private NBTTagCompound getExtras(EntityLivingBase entity) {
        NBTTagCompound fake = new NBTTagCompound();
        new EntityFakeLiving(entity.field_70170_p).func_70014_b(fake);
        NBTTagCompound compound = new NBTTagCompound();
        try {
            entity.func_70014_b(compound);
        }
        catch (Exception exception) {
            // empty catch block
        }
        Set keys = fake.func_150296_c();
        for (String name : keys) {
            compound.func_82580_o(name);
        }
        return compound;
    }

    private int getCurrentEntityIndex() {
        return this.list.indexOf(EntityList.field_75626_c.get(this.playerdata.getEntityClass()));
    }

    @Override
    protected void func_146284_a(GuiButton btn) {
        String name;
        int index;
        super.func_146284_a(btn);
        GuiNpcButton button = (GuiNpcButton)btn;
        if (button.field_146127_k == 0) {
            index = this.getCurrentEntityIndex();
            if (!this.prev.field_146124_l) {
                return;
            }
            --index;
            try {
                if (index < 0) {
                    this.playerdata.setEntityClass(null);
                    this.npc.display.texture = "customnpcs:textures/entity/humanmale/Steve.png";
                } else {
                    this.playerdata.setEntityClass(this.data.get(this.list.get(index)));
                    EntityLivingBase entity = this.playerdata.getEntity(this.npc);
                    if (entity != null) {
                        RendererLivingEntity render = (RendererLivingEntity)RenderManager.field_78727_a.func_78713_a((Entity)entity);
                        this.npc.display.texture = NPCRendererHelper.getTexture(render, (Entity)entity);
                    }
                }
                this.npc.display.skinOverlayData.overlayList.remove(0);
                this.npc.textureLocation = null;
                this.npc.updateHitbox();
            }
            catch (Exception ex) {
                this.npc.display.texture = "customnpcs:textures/entity/humanmale/Steve.png";
            }
            this.func_73866_w_();
        }
        if (button.field_146127_k == 2) {
            index = this.getCurrentEntityIndex();
            if (!this.next.field_146124_l) {
                return;
            }
            this.playerdata.setEntityClass(this.data.get(this.list.get(++index)));
            this.updateTexture();
            this.func_73866_w_();
        }
        if (button.field_146127_k == 1) {
            this.field_146297_k.func_147108_a((GuiScreen)new GuiEntitySelection(this, this.playerdata, this.npc));
        }
        if (button.field_146127_k == 4) {
            this.field_146297_k.func_147108_a((GuiScreen)new GuiModelHead(this, this.npc));
        }
        if (button.field_146127_k == 5) {
            this.field_146297_k.func_147108_a((GuiScreen)new GuiModelBody(this, this.npc));
        }
        if (button.field_146127_k == 6) {
            this.field_146297_k.func_147108_a((GuiScreen)new GuiModelArms(this, this.npc));
        }
        if (button.field_146127_k == 7) {
            this.field_146297_k.func_147108_a((GuiScreen)new GuiModelLegs(this, this.npc));
        }
        if (button.field_146127_k == 250) {
            if (this.npc.display.modelType == 0) {
                if (this.npc.display.skinType == 2) {
                    this.npc.display.skinType = (byte)3;
                }
                this.npc.display.modelType = 1;
            } else if (this.npc.display.modelType == 1) {
                this.npc.display.modelType = 2;
            } else {
                if (this.npc.display.skinType == 3) {
                    this.npc.display.skinType = (byte)2;
                }
                this.npc.display.modelType = 0;
            }
            this.npc.textureLocation = null;
        }
        if (button.field_146127_k == 8) {
            this.field_146297_k.func_147108_a((GuiScreen)new GuiModelScale(this, this.playerdata, this.npc));
        }
        if (button.field_146127_k == 9) {
            this.field_146297_k.func_147108_a((GuiScreen)new GuiModelRotate(this, this.playerdata, this.npc));
        }
        if (button.field_146127_k == 30) {
            this.playerdata.extra.func_74768_a("Age", button.getValue() == 1 ? -24000 : 0);
            this.playerdata.clearEntity();
        }
        if (button.field_146127_k == 44) {
            this.field_146297_k.func_147108_a((GuiScreen)new GuiPresetSave(this, this.playerdata));
        }
        if (button.field_146127_k == 45) {
            this.field_146297_k.func_147108_a((GuiScreen)new GuiPresetSelection(this, this.playerdata));
        }
        if (button.field_146127_k >= 100 && button.field_146127_k < 200 && (name = this.mapped.get(button.field_146127_k - 100)) != null) {
            this.playerdata.extra.func_74757_a(name, button.getValue() == 1);
            this.playerdata.clearEntity();
        }
        if (button.field_146127_k == 201) {
            try {
                EntityLivingBase entity = this.playerdata.getEntity(this.npc);
                Method method = entity.getClass().getMethod("setBreedID", Integer.TYPE);
                method.invoke((Object)entity, button.getValue());
                NBTTagCompound comp = new NBTTagCompound();
                entity.func_70014_b(comp);
                this.playerdata.extra.func_74778_a("EntityData21", comp.func_74779_i("EntityData21"));
                this.playerdata.clearEntity();
                this.updateTexture();
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
        GeckoAddonClient.Instance.geckoGuiCreationScreenActionPerformed(this, button);
    }

    private void updateTexture() {
        try {
            EntityLivingBase entity = this.playerdata.getEntity(this.npc);
            if (entity != null && entity.equals((Object)this.npc.modelData.entity)) {
                return;
            }
            if (entity instanceof EntityNpcDragon) {
                this.npc.display.texture = "customnpcs:textures/entity/dragon/BlackDragon.png";
            } else if (entity instanceof EntityNpcSlime) {
                this.npc.display.texture = "customnpcs:textures/entity/slime/Slime.png";
            } else if (entity instanceof EntityNPCGolem) {
                this.npc.display.texture = "customnpcs:textures/entity/golem/Iron Golem.png";
            } else if (entity instanceof EntityNpcPony) {
                this.npc.display.texture = "customnpcs:textures/entity/ponies/MineLP Derpy Hooves.png";
            } else if (entity instanceof EntityNpcCrystal) {
                this.npc.display.texture = "customnpcs:textures/entity/crystal/EnderCrystal.png";
            } else if (entity != null) {
                RendererLivingEntity render = (RendererLivingEntity)RenderManager.field_78727_a.func_78713_a((Entity)entity);
                this.npc.display.texture = NPCRendererHelper.getTexture(render, (Entity)entity);
            } else {
                this.npc.display.texture = "customnpcs:textures/entity/humanmale/Steve.png";
            }
            this.npc.display.skinOverlayData.overlayList.remove(0);
            this.npc.textureLocation = null;
            this.npc.updateHitbox();
        }
        catch (Exception ex) {
            this.npc.display.texture = "customnpcs:textures/entity/humanmale/Steve.png";
        }
    }

    @Override
    public void close() {
        PacketClient.sendClient(new ModelDataSavePacket(this.playerdata.writeToNBT()));
        this.displayGuiScreen(this.parent);
    }

    @Override
    public void customScrollClicked(int i, int j, int k, GuiCustomScroll scroll) {
        this.playerdata.clearEntity();
        this.playerdata.extra.func_74778_a("Name", scroll.getSelected());
        this.updateTexture();
    }
}

