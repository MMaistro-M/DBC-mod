/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.AbstractClientPlayer
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.client.renderer.entity.RenderPlayer
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraftforge.client.event.RenderPlayerEvent$Post
 *  net.minecraftforge.client.event.RenderPlayerEvent$Pre
 *  net.minecraftforge.client.event.RenderPlayerEvent$Specials$Post
 *  net.minecraftforge.common.MinecraftForge
 *  org.lwjgl.opengl.GL11
 */
package riskyken.armourersWorkshop.client.render;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Color;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;
import java.util.WeakHashMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.MinecraftForge;
import org.lwjgl.opengl.GL11;
import riskyken.armourersWorkshop.api.common.skin.IEntityEquipment;
import riskyken.armourersWorkshop.api.common.skin.data.ISkinDye;
import riskyken.armourersWorkshop.api.common.skin.data.ISkinPointer;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinType;
import riskyken.armourersWorkshop.client.model.ModelRendererAttachment;
import riskyken.armourersWorkshop.client.model.skin.AbstractModelSkin;
import riskyken.armourersWorkshop.client.model.skin.ModelSkinBow;
import riskyken.armourersWorkshop.client.model.skin.ModelSkinChest;
import riskyken.armourersWorkshop.client.model.skin.ModelSkinFeet;
import riskyken.armourersWorkshop.client.model.skin.ModelSkinHead;
import riskyken.armourersWorkshop.client.model.skin.ModelSkinLegs;
import riskyken.armourersWorkshop.client.model.skin.ModelSkinOutfit;
import riskyken.armourersWorkshop.client.model.skin.ModelSkinSkirt;
import riskyken.armourersWorkshop.client.model.skin.ModelSkinSword;
import riskyken.armourersWorkshop.client.model.skin.ModelSkinWings;
import riskyken.armourersWorkshop.client.skin.cache.ClientSkinCache;
import riskyken.armourersWorkshop.common.config.ConfigHandlerClient;
import riskyken.armourersWorkshop.common.data.PlayerPointer;
import riskyken.armourersWorkshop.common.skin.data.Skin;
import riskyken.armourersWorkshop.common.skin.data.SkinPointer;
import riskyken.armourersWorkshop.common.skin.data.SkinProperties;
import riskyken.armourersWorkshop.common.skin.type.SkinTypeRegistry;
import riskyken.armourersWorkshop.common.wardrobe.EntityEquipmentData;
import riskyken.armourersWorkshop.common.wardrobe.EquipmentWardrobeData;
import riskyken.armourersWorkshop.common.wardrobe.ExtraColours;
import riskyken.armourersWorkshop.proxies.ClientProxy;
import riskyken.armourersWorkshop.utils.ModLogger;
import riskyken.armourersWorkshop.utils.SkinNBTHelper;

@SideOnly(value=Side.CLIENT)
public final class SkinModelRenderer {
    public static SkinModelRenderer INSTANCE;
    private final HashMap<PlayerPointer, EntityEquipmentData> playerEquipmentMap;
    private final Set<ModelBiped> attachedBipedSet;
    public final ModelSkinHead customHead = new ModelSkinHead();
    public final ModelSkinChest customChest = new ModelSkinChest();
    public final ModelSkinLegs customLegs = new ModelSkinLegs();
    public final ModelSkinFeet customFeet = new ModelSkinFeet();
    public final ModelSkinWings customWings = new ModelSkinWings();
    public final ModelSkinSkirt customSkirt = new ModelSkinSkirt();
    public final ModelSkinSword customSword = new ModelSkinSword();
    public final ModelSkinBow customBow = new ModelSkinBow();
    public final ModelSkinOutfit customOutfit = new ModelSkinOutfit();
    public EntityPlayer targetPlayer = null;

    public static void init() {
        INSTANCE = new SkinModelRenderer();
    }

    private SkinModelRenderer() {
        MinecraftForge.EVENT_BUS.register((Object)this);
        this.playerEquipmentMap = new HashMap();
        this.attachedBipedSet = Collections.newSetFromMap(new WeakHashMap());
    }

    public Skin getPlayerCustomArmour(Entity entity, ISkinType skinType, int slotIndex) {
        int slot;
        ItemStack armourStack;
        if (!(entity instanceof AbstractClientPlayer)) {
            return null;
        }
        AbstractClientPlayer player = (AbstractClientPlayer)entity;
        EntityEquipmentData equipmentData = this.playerEquipmentMap.get(new PlayerPointer((EntityPlayer)player));
        if (skinType.getVanillaArmourSlotId() >= 0 && skinType.getVanillaArmourSlotId() < 4 && slotIndex == 0 && SkinNBTHelper.stackHasSkinData(armourStack = player.func_82169_q(slot = 3 - skinType.getVanillaArmourSlotId()))) {
            SkinPointer sp = SkinNBTHelper.getSkinPointerFromStack(armourStack);
            return this.getCustomArmourItemData(sp);
        }
        if (equipmentData == null) {
            return null;
        }
        if (!equipmentData.haveEquipment(skinType, slotIndex)) {
            return null;
        }
        ISkinPointer skinPointer = equipmentData.getSkinPointer(skinType, slotIndex);
        return this.getCustomArmourItemData(skinPointer);
    }

    public ISkinDye getPlayerDyeData(Entity entity, ISkinType skinType, int slotIndex) {
        int slot;
        ItemStack armourStack;
        if (!(entity instanceof AbstractClientPlayer)) {
            return null;
        }
        AbstractClientPlayer player = (AbstractClientPlayer)entity;
        EntityEquipmentData equipmentData = this.playerEquipmentMap.get(new PlayerPointer((EntityPlayer)player));
        if (skinType.getVanillaArmourSlotId() >= 0 && skinType.getVanillaArmourSlotId() < 4 && slotIndex == 0 && SkinNBTHelper.stackHasSkinData(armourStack = player.func_82169_q(slot = 3 - skinType.getVanillaArmourSlotId()))) {
            SkinPointer sp = SkinNBTHelper.getSkinPointerFromStack(armourStack);
            return sp.getSkinDye();
        }
        if (equipmentData == null) {
            return null;
        }
        if (!equipmentData.haveEquipment(skinType, slotIndex)) {
            return null;
        }
        ISkinDye skinDye = equipmentData.getSkinPointer(skinType, slotIndex).getSkinDye();
        return skinDye;
    }

    public byte[] getPlayerExtraColours(Entity entity) {
        if (!(entity instanceof AbstractClientPlayer)) {
            return null;
        }
        AbstractClientPlayer player = (AbstractClientPlayer)entity;
        EntityEquipmentData equipmentData = this.playerEquipmentMap.get(new PlayerPointer((EntityPlayer)player));
        return null;
    }

    public IEntityEquipment getPlayerCustomEquipmentData(Entity entity) {
        if (!(entity instanceof AbstractClientPlayer)) {
            return null;
        }
        AbstractClientPlayer player = (AbstractClientPlayer)entity;
        EntityEquipmentData equipmentData = this.playerEquipmentMap.get(new PlayerPointer((EntityPlayer)player));
        return equipmentData;
    }

    public int getSkinDataMapSize() {
        return this.playerEquipmentMap.size();
    }

    public Skin getCustomArmourItemData(ISkinPointer skinPointer) {
        return ClientSkinCache.INSTANCE.getSkin(skinPointer);
    }

    public void addEquipmentData(PlayerPointer playerPointer, EntityEquipmentData equipmentData) {
        if (this.playerEquipmentMap.containsKey(playerPointer)) {
            this.playerEquipmentMap.remove(playerPointer);
        }
        this.playerEquipmentMap.put(playerPointer, equipmentData);
    }

    public void removeEquipmentData(PlayerPointer playerPointer) {
        if (this.playerEquipmentMap.containsKey(playerPointer)) {
            this.playerEquipmentMap.remove(playerPointer);
        }
    }

    private boolean isPlayerWearingSkirt(PlayerPointer playerPointer) {
        EntityEquipmentData equipmentData = this.playerEquipmentMap.get(playerPointer);
        if (equipmentData != null) {
            for (int i = 0; i < 10; ++i) {
                Skin skin;
                ISkinPointer skinPointer = equipmentData.getSkinPointer(SkinTypeRegistry.skinLegs, i);
                if (skinPointer != null && (skin = ClientSkinCache.INSTANCE.getSkin(skinPointer)) != null && SkinProperties.PROP_MODEL_LEGS_LIMIT_LIMBS.getValue(skin.getProperties()).booleanValue()) {
                    return true;
                }
                skinPointer = equipmentData.getSkinPointer(SkinTypeRegistry.skinOutfit, i);
                if (skinPointer == null || (skin = ClientSkinCache.INSTANCE.getSkin(skinPointer)) == null || !SkinProperties.PROP_MODEL_LEGS_LIMIT_LIMBS.getValue(skin.getProperties()).booleanValue()) continue;
                return true;
            }
        }
        return false;
    }

    public boolean playerHasCustomHead(EntityPlayer player) {
        EntityEquipmentData equipmentData = this.playerEquipmentMap.get(new PlayerPointer(player));
        if (equipmentData != null) {
            for (int i = 0; i < 10; ++i) {
                Skin skin;
                ISkinPointer sp = equipmentData.getSkinPointer(SkinTypeRegistry.skinHead, i);
                if (sp == null || (skin = ClientSkinCache.INSTANCE.getSkin(sp, false)) == null) continue;
                if (SkinProperties.PROP_MODEL_OVERRIDE_HEAD.getValue(skin.getProperties()).booleanValue()) {
                    return true;
                }
                if (!SkinProperties.PROP_MODEL_HIDE_OVERLAY_HEAD.getValue(skin.getProperties()).booleanValue()) continue;
                return true;
            }
        }
        return false;
    }

    @SubscribeEvent
    public void onRender(RenderPlayerEvent.Pre event) {
        EntityPlayer player;
        this.targetPlayer = player = event.entityPlayer;
        if (ClientProxy.getSkinRenderType() == ClientProxy.SkinRenderType.MODEL_ATTACHMENT) {
            this.attachModelsToBiped(event.renderer.field_77109_a, event.renderer);
        }
        if (player.func_146103_bH() == null) {
            return;
        }
        PlayerPointer playerPointer = new PlayerPointer(player);
        ISkinType[] playerSkinTypes = new ISkinType[]{SkinTypeRegistry.skinHead, SkinTypeRegistry.skinChest, SkinTypeRegistry.skinLegs, SkinTypeRegistry.skinFeet, SkinTypeRegistry.skinOutfit};
        boolean hideHead = false;
        boolean hideHeadOverlay = false;
        EntityEquipmentData equipmentData = this.playerEquipmentMap.get(new PlayerPointer(player));
        if (equipmentData != null) {
            for (int i = 0; i < 10; ++i) {
                for (ISkinType skinType : playerSkinTypes) {
                    Skin skin;
                    ISkinPointer skinPointer = equipmentData.getSkinPointer(skinType, i);
                    if (skinPointer == null || (skin = ClientSkinCache.INSTANCE.getSkin(skinPointer, false)) == null) continue;
                    if (SkinProperties.PROP_MODEL_HIDE_OVERLAY_HEAD.getValue(skin.getProperties()).booleanValue()) {
                        hideHeadOverlay = true;
                    }
                    if (!SkinProperties.PROP_MODEL_OVERRIDE_HEAD.getValue(skin.getProperties()).booleanValue()) continue;
                    hideHead = true;
                    hideHeadOverlay = true;
                }
            }
        }
        RenderPlayer renderer = event.renderer;
        renderer.field_77109_a.field_78116_c.field_78807_k = false;
        if (hideHead) {
            renderer.field_77109_a.field_78114_d.field_78807_k = true;
        }
        if (hideHeadOverlay || hideHead) {
            renderer.field_77109_a.field_78114_d.field_78807_k = true;
        }
        if (this.isPlayerWearingSkirt(playerPointer) && player.field_70721_aZ > 0.25f) {
            player.field_70721_aZ = 0.25f;
            player.field_70722_aY = 0.25f;
        }
    }

    private void attachModelsToBiped(ModelBiped modelBiped, RenderPlayer renderPlayer) {
        if (this.attachedBipedSet.contains(modelBiped)) {
            return;
        }
        this.attachedBipedSet.add(modelBiped);
        modelBiped.field_78116_c.func_78792_a((ModelRenderer)new ModelRendererAttachment(modelBiped, SkinTypeRegistry.skinHead, SkinTypeRegistry.INSTANCE.getSkinPartFromRegistryName("armourers:head.base")));
        modelBiped.field_78115_e.func_78792_a((ModelRenderer)new ModelRendererAttachment(modelBiped, SkinTypeRegistry.skinChest, SkinTypeRegistry.INSTANCE.getSkinPartFromRegistryName("armourers:chest.base")));
        modelBiped.field_78113_g.func_78792_a((ModelRenderer)new ModelRendererAttachment(modelBiped, SkinTypeRegistry.skinChest, SkinTypeRegistry.INSTANCE.getSkinPartFromRegistryName("armourers:chest.leftArm")));
        modelBiped.field_78112_f.func_78792_a((ModelRenderer)new ModelRendererAttachment(modelBiped, SkinTypeRegistry.skinChest, SkinTypeRegistry.INSTANCE.getSkinPartFromRegistryName("armourers:chest.rightArm")));
        modelBiped.field_78124_i.func_78792_a((ModelRenderer)new ModelRendererAttachment(modelBiped, SkinTypeRegistry.skinLegs, SkinTypeRegistry.INSTANCE.getSkinPartFromRegistryName("armourers:legs.leftLeg")));
        modelBiped.field_78123_h.func_78792_a((ModelRenderer)new ModelRendererAttachment(modelBiped, SkinTypeRegistry.skinLegs, SkinTypeRegistry.INSTANCE.getSkinPartFromRegistryName("armourers:legs.rightLeg")));
        modelBiped.field_78115_e.func_78792_a((ModelRenderer)new ModelRendererAttachment(modelBiped, SkinTypeRegistry.skinLegs, SkinTypeRegistry.INSTANCE.getSkinPartFromRegistryName("armourers:legs.skirt")));
        modelBiped.field_78124_i.func_78792_a((ModelRenderer)new ModelRendererAttachment(modelBiped, SkinTypeRegistry.skinFeet, SkinTypeRegistry.INSTANCE.getSkinPartFromRegistryName("armourers:feet.leftFoot")));
        modelBiped.field_78123_h.func_78792_a((ModelRenderer)new ModelRendererAttachment(modelBiped, SkinTypeRegistry.skinFeet, SkinTypeRegistry.INSTANCE.getSkinPartFromRegistryName("armourers:feet.rightFoot")));
        modelBiped.field_78115_e.func_78792_a((ModelRenderer)new ModelRendererAttachment(modelBiped, SkinTypeRegistry.skinWings, SkinTypeRegistry.INSTANCE.getSkinPartFromRegistryName("armourers:wings.leftWing")));
        modelBiped.field_78115_e.func_78792_a((ModelRenderer)new ModelRendererAttachment(modelBiped, SkinTypeRegistry.skinWings, SkinTypeRegistry.INSTANCE.getSkinPartFromRegistryName("armourers:wings.rightWing")));
        modelBiped.field_78116_c.func_78792_a((ModelRenderer)new ModelRendererAttachment(modelBiped, SkinTypeRegistry.skinOutfit, SkinTypeRegistry.INSTANCE.getSkinPartFromRegistryName("armourers:head.base")));
        modelBiped.field_78115_e.func_78792_a((ModelRenderer)new ModelRendererAttachment(modelBiped, SkinTypeRegistry.skinOutfit, SkinTypeRegistry.INSTANCE.getSkinPartFromRegistryName("armourers:chest.base")));
        modelBiped.field_78113_g.func_78792_a((ModelRenderer)new ModelRendererAttachment(modelBiped, SkinTypeRegistry.skinOutfit, SkinTypeRegistry.INSTANCE.getSkinPartFromRegistryName("armourers:chest.leftArm")));
        modelBiped.field_78112_f.func_78792_a((ModelRenderer)new ModelRendererAttachment(modelBiped, SkinTypeRegistry.skinOutfit, SkinTypeRegistry.INSTANCE.getSkinPartFromRegistryName("armourers:chest.rightArm")));
        modelBiped.field_78124_i.func_78792_a((ModelRenderer)new ModelRendererAttachment(modelBiped, SkinTypeRegistry.skinOutfit, SkinTypeRegistry.INSTANCE.getSkinPartFromRegistryName("armourers:legs.leftLeg")));
        modelBiped.field_78123_h.func_78792_a((ModelRenderer)new ModelRendererAttachment(modelBiped, SkinTypeRegistry.skinOutfit, SkinTypeRegistry.INSTANCE.getSkinPartFromRegistryName("armourers:legs.rightLeg")));
        modelBiped.field_78115_e.func_78792_a((ModelRenderer)new ModelRendererAttachment(modelBiped, SkinTypeRegistry.skinOutfit, SkinTypeRegistry.INSTANCE.getSkinPartFromRegistryName("armourers:legs.skirt")));
        modelBiped.field_78124_i.func_78792_a((ModelRenderer)new ModelRendererAttachment(modelBiped, SkinTypeRegistry.skinOutfit, SkinTypeRegistry.INSTANCE.getSkinPartFromRegistryName("armourers:feet.leftFoot")));
        modelBiped.field_78123_h.func_78792_a((ModelRenderer)new ModelRendererAttachment(modelBiped, SkinTypeRegistry.skinOutfit, SkinTypeRegistry.INSTANCE.getSkinPartFromRegistryName("armourers:feet.rightFoot")));
        modelBiped.field_78115_e.func_78792_a((ModelRenderer)new ModelRendererAttachment(modelBiped, SkinTypeRegistry.skinOutfit, SkinTypeRegistry.INSTANCE.getSkinPartFromRegistryName("armourers:wings.leftWing")));
        modelBiped.field_78115_e.func_78792_a((ModelRenderer)new ModelRendererAttachment(modelBiped, SkinTypeRegistry.skinOutfit, SkinTypeRegistry.INSTANCE.getSkinPartFromRegistryName("armourers:wings.rightWing")));
        ModLogger.log(String.format("Added model render attachment to %s", modelBiped.toString()));
        ModLogger.log(String.format("Using player renderer %s", renderPlayer.toString()));
    }

    @SubscribeEvent
    public void onRender(RenderPlayerEvent.Post event) {
        RenderPlayer renderer = event.renderer;
        renderer.field_77109_a.field_78114_d.field_78807_k = false;
        this.targetPlayer = null;
    }

    @SubscribeEvent
    public void onRenderSpecialsPost(RenderPlayerEvent.Specials.Post event) {
        if (ClientProxy.getSkinRenderType() != ClientProxy.SkinRenderType.RENDER_EVENT) {
            return;
        }
        EntityPlayer player = event.entityPlayer;
        RenderPlayer render = event.renderer;
        if (player.func_146103_bH() == null) {
            return;
        }
        PlayerPointer playerPointer = new PlayerPointer(player);
        if (!this.playerEquipmentMap.containsKey(playerPointer)) {
            return;
        }
        double distance = Minecraft.func_71410_x().field_71439_g.func_70011_f(player.field_70165_t, player.field_70163_u, player.field_70161_v);
        if (distance > (double)ConfigHandlerClient.maxSkinRenderDistance) {
            return;
        }
        EquipmentWardrobeData ewd = ClientProxy.equipmentWardrobeHandler.getEquipmentWardrobeData(new PlayerPointer(player));
        byte[] extraColours = null;
        if (ewd != null) {
            Color skinColour = new Color(ewd.getExtraColours().getColour(ExtraColours.ExtraColourType.SKIN));
            Color hairColour = new Color(ewd.getExtraColours().getColour(ExtraColours.ExtraColourType.HAIR));
            extraColours = new byte[]{(byte)skinColour.getRed(), (byte)skinColour.getGreen(), (byte)skinColour.getBlue(), (byte)hairColour.getRed(), (byte)hairColour.getGreen(), (byte)hairColour.getBlue()};
        }
        GL11.glPushMatrix();
        GL11.glEnable((int)2884);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glEnable((int)3042);
        for (int slot = 0; slot < 4; ++slot) {
            for (int skinIndex = 0; skinIndex < 10; ++skinIndex) {
                ISkinDye dye;
                Skin data;
                if (slot == SkinTypeRegistry.skinHead.getVanillaArmourSlotId()) {
                    data = this.getPlayerCustomArmour((Entity)player, SkinTypeRegistry.skinHead, skinIndex);
                    dye = this.getPlayerDyeData((Entity)player, SkinTypeRegistry.skinHead, skinIndex);
                    if (data != null) {
                        this.customHead.render((Entity)player, render.field_77109_a, data, false, dye, extraColours, false, distance, true);
                    }
                }
                if (slot == SkinTypeRegistry.skinChest.getVanillaArmourSlotId()) {
                    data = this.getPlayerCustomArmour((Entity)player, SkinTypeRegistry.skinChest, skinIndex);
                    dye = this.getPlayerDyeData((Entity)player, SkinTypeRegistry.skinChest, skinIndex);
                    if (data != null) {
                        this.customChest.render((Entity)player, render.field_77109_a, data, false, dye, extraColours, false, distance, true);
                    }
                }
                if (slot == SkinTypeRegistry.skinLegs.getVanillaArmourSlotId()) {
                    data = this.getPlayerCustomArmour((Entity)player, SkinTypeRegistry.skinLegs, skinIndex);
                    dye = this.getPlayerDyeData((Entity)player, SkinTypeRegistry.skinLegs, skinIndex);
                    if (data != null) {
                        this.customLegs.render((Entity)player, render.field_77109_a, data, false, dye, extraColours, false, distance, true);
                    }
                }
                if (slot == SkinTypeRegistry.oldSkinSkirt.getVanillaArmourSlotId()) {
                    data = this.getPlayerCustomArmour((Entity)player, SkinTypeRegistry.oldSkinSkirt, skinIndex);
                    dye = this.getPlayerDyeData((Entity)player, SkinTypeRegistry.oldSkinSkirt, skinIndex);
                    if (data != null) {
                        this.customSkirt.render((Entity)player, render.field_77109_a, data, false, dye, extraColours, false, distance, true);
                    }
                }
                if (slot == SkinTypeRegistry.skinFeet.getVanillaArmourSlotId()) {
                    data = this.getPlayerCustomArmour((Entity)player, SkinTypeRegistry.skinFeet, skinIndex);
                    dye = this.getPlayerDyeData((Entity)player, SkinTypeRegistry.skinFeet, skinIndex);
                    if (data != null) {
                        this.customFeet.render((Entity)player, render.field_77109_a, data, false, dye, extraColours, false, distance, true);
                    }
                }
                if (slot != 0) continue;
                data = this.getPlayerCustomArmour((Entity)player, SkinTypeRegistry.skinWings, skinIndex);
                dye = this.getPlayerDyeData((Entity)player, SkinTypeRegistry.skinWings, skinIndex);
                if (data != null) {
                    this.customWings.render((Entity)player, render.field_77109_a, data, false, dye, extraColours, false, distance, true);
                }
                data = this.getPlayerCustomArmour((Entity)player, SkinTypeRegistry.skinOutfit, skinIndex);
                dye = this.getPlayerDyeData((Entity)player, SkinTypeRegistry.skinOutfit, skinIndex);
                if (data == null) continue;
                this.customOutfit.render((Entity)player, render.field_77109_a, data, false, dye, extraColours, false, distance, true);
            }
        }
        GL11.glDisable((int)3042);
        GL11.glDisable((int)2884);
        GL11.glPopMatrix();
    }

    public AbstractModelSkin getModelForEquipmentType(ISkinType skinType) {
        if (skinType == SkinTypeRegistry.skinHead) {
            return this.customHead;
        }
        if (skinType == SkinTypeRegistry.skinChest) {
            return this.customChest;
        }
        if (skinType == SkinTypeRegistry.skinLegs) {
            return this.customLegs;
        }
        if (skinType == SkinTypeRegistry.oldSkinSkirt) {
            return this.customSkirt;
        }
        if (skinType == SkinTypeRegistry.skinFeet) {
            return this.customFeet;
        }
        if (skinType == SkinTypeRegistry.skinSword) {
            return this.customSword;
        }
        if (skinType == SkinTypeRegistry.skinBow) {
            return this.customBow;
        }
        if (skinType == SkinTypeRegistry.skinWings) {
            return this.customWings;
        }
        if (skinType == SkinTypeRegistry.skinOutfit) {
            return this.customOutfit;
        }
        return null;
    }

    public boolean renderEquipmentPartFromStack(Entity entity, ItemStack stack, ModelBiped modelBiped, byte[] extraColours, double distance, boolean doLodLoading) {
        SkinPointer skinPointer = SkinNBTHelper.getSkinPointerFromStack(stack);
        if (skinPointer == null) {
            return false;
        }
        Skin data = this.getCustomArmourItemData(skinPointer);
        return this.renderEquipmentPart(entity, modelBiped, data, skinPointer.getSkinDye(), extraColours, distance, doLodLoading);
    }

    public boolean renderEquipmentPartFromStack(ItemStack stack, ModelBiped modelBiped, byte[] extraColours, double distance, boolean doLodLoading) {
        SkinPointer skinPointer = SkinNBTHelper.getSkinPointerFromStack(stack);
        if (skinPointer == null) {
            return false;
        }
        Skin data = this.getCustomArmourItemData(skinPointer);
        return this.renderEquipmentPart(null, modelBiped, data, skinPointer.getSkinDye(), extraColours, distance, doLodLoading);
    }

    public boolean renderEquipmentPartFromSkinPointer(ISkinPointer skinPointer, float limb1, float limb2, float limb3, float headY, float headX) {
        Skin data = this.getCustomArmourItemData(skinPointer);
        return this.renderEquipmentPartRotated(null, data, limb1, limb2, limb3, headY, headX);
    }

    public boolean renderEquipmentPart(Entity entity, ModelBiped modelBiped, Skin data, ISkinDye skinDye, byte[] extraColours, double distance, boolean doLodLoading) {
        if (data == null) {
            return false;
        }
        AbstractModelSkin model = this.getModelForEquipmentType(data.getSkinType());
        if (model == null) {
            return false;
        }
        GL11.glEnable((int)2884);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glEnable((int)3042);
        model.render(entity, modelBiped, data, false, skinDye, extraColours, false, distance, doLodLoading);
        GL11.glDisable((int)3042);
        GL11.glDisable((int)2884);
        return true;
    }

    private boolean renderEquipmentPartRotated(Entity entity, Skin data, float limb1, float limb2, float limb3, float headY, float headX) {
        if (data == null) {
            return false;
        }
        AbstractModelSkin model = this.getModelForEquipmentType(data.getSkinType());
        if (model == null) {
            return false;
        }
        model.render(entity, data, limb1, limb2, limb3, headY, headX);
        return true;
    }
}

