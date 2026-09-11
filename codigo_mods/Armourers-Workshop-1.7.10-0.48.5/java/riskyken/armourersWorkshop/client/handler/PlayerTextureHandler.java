/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.minecraft.MinecraftProfileTexture$Type
 *  cpw.mods.fml.common.eventhandler.EventPriority
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.AbstractClientPlayer
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.profiler.Profiler
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.client.event.RenderPlayerEvent$Post
 *  net.minecraftforge.client.event.RenderPlayerEvent$Pre
 *  net.minecraftforge.common.MinecraftForge
 */
package riskyken.armourersWorkshop.client.handler;

import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.HashMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.profiler.Profiler;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.MinecraftForge;
import riskyken.armourersWorkshop.api.common.skin.data.ISkinDye;
import riskyken.armourersWorkshop.client.render.EntityTextureInfo;
import riskyken.armourersWorkshop.client.render.MannequinFakePlayer;
import riskyken.armourersWorkshop.client.render.SkinModelRenderer;
import riskyken.armourersWorkshop.common.data.PlayerPointer;
import riskyken.armourersWorkshop.common.skin.data.Skin;
import riskyken.armourersWorkshop.common.skin.type.SkinTypeRegistry;
import riskyken.armourersWorkshop.common.wardrobe.EquipmentWardrobeData;
import riskyken.armourersWorkshop.common.wardrobe.ExtraColours;
import riskyken.armourersWorkshop.proxies.ClientProxy;

@SideOnly(value=Side.CLIENT)
public class PlayerTextureHandler {
    public static PlayerTextureHandler INSTANCE;
    private HashMap<PlayerPointer, EntityTextureInfo> playerTextureMap = new HashMap();
    private final Profiler profiler;
    private boolean useTexturePainting;

    public PlayerTextureHandler() {
        MinecraftForge.EVENT_BUS.register((Object)this);
        this.profiler = Minecraft.func_71410_x().field_71424_I;
    }

    @SubscribeEvent(priority=EventPriority.LOW)
    public void onRender(RenderPlayerEvent.Pre event) {
        this.useTexturePainting = ClientProxy.useTexturePainting();
        if (!this.useTexturePainting) {
            return;
        }
        if (!(event.entityPlayer instanceof AbstractClientPlayer)) {
            return;
        }
        AbstractClientPlayer player = (AbstractClientPlayer)event.entityPlayer;
        if (player instanceof MannequinFakePlayer) {
            return;
        }
        if (player.func_146103_bH() == null) {
            return;
        }
        PlayerPointer playerPointer = new PlayerPointer((EntityPlayer)player);
        EquipmentWardrobeData ewd = ClientProxy.equipmentWardrobeHandler.getEquipmentWardrobeData(playerPointer);
        if (ewd == null) {
            return;
        }
        this.profiler.func_76320_a("textureBuild");
        if (this.playerTextureMap.containsKey(playerPointer)) {
            EntityTextureInfo textureInfo = this.playerTextureMap.get(playerPointer);
            textureInfo.updateTexture(player.func_110306_p());
            textureInfo.updateHairColour(ewd.getExtraColours().getColour(ExtraColours.ExtraColourType.HAIR));
            textureInfo.updateSkinColour(ewd.getExtraColours().getColour(ExtraColours.ExtraColourType.SKIN));
            Skin[] skins = new Skin[50];
            for (int skinIndex = 0; skinIndex < 10; ++skinIndex) {
                skins[0 + skinIndex * 5] = SkinModelRenderer.INSTANCE.getPlayerCustomArmour((Entity)player, SkinTypeRegistry.skinHead, skinIndex);
                skins[1 + skinIndex * 5] = SkinModelRenderer.INSTANCE.getPlayerCustomArmour((Entity)player, SkinTypeRegistry.skinChest, skinIndex);
                skins[2 + skinIndex * 5] = SkinModelRenderer.INSTANCE.getPlayerCustomArmour((Entity)player, SkinTypeRegistry.skinLegs, skinIndex);
                skins[3 + skinIndex * 5] = SkinModelRenderer.INSTANCE.getPlayerCustomArmour((Entity)player, SkinTypeRegistry.skinFeet, skinIndex);
                skins[4 + skinIndex * 5] = SkinModelRenderer.INSTANCE.getPlayerCustomArmour((Entity)player, SkinTypeRegistry.skinOutfit, skinIndex);
            }
            ISkinDye[] dyes = new ISkinDye[50];
            for (int skinIndex = 0; skinIndex < 10; ++skinIndex) {
                dyes[0 + skinIndex * 5] = SkinModelRenderer.INSTANCE.getPlayerDyeData((Entity)player, SkinTypeRegistry.skinHead, skinIndex);
                dyes[1 + skinIndex * 5] = SkinModelRenderer.INSTANCE.getPlayerDyeData((Entity)player, SkinTypeRegistry.skinChest, skinIndex);
                dyes[2 + skinIndex * 5] = SkinModelRenderer.INSTANCE.getPlayerDyeData((Entity)player, SkinTypeRegistry.skinLegs, skinIndex);
                dyes[3 + skinIndex * 5] = SkinModelRenderer.INSTANCE.getPlayerDyeData((Entity)player, SkinTypeRegistry.skinFeet, skinIndex);
                dyes[4 + skinIndex * 5] = SkinModelRenderer.INSTANCE.getPlayerDyeData((Entity)player, SkinTypeRegistry.skinOutfit, skinIndex);
            }
            textureInfo.updateSkins(skins);
            textureInfo.updateDyes(dyes);
            ResourceLocation replacmentTexture = textureInfo.preRender();
            player.func_152121_a(MinecraftProfileTexture.Type.SKIN, replacmentTexture);
        }
        this.profiler.func_76319_b();
    }

    @SubscribeEvent(priority=EventPriority.HIGH)
    public void onRender(RenderPlayerEvent.Post event) {
        if (!this.useTexturePainting) {
            return;
        }
        if (!(event.entityPlayer instanceof AbstractClientPlayer)) {
            return;
        }
        AbstractClientPlayer player = (AbstractClientPlayer)event.entityPlayer;
        if (player instanceof MannequinFakePlayer) {
            return;
        }
        if (player.func_146103_bH() == null) {
            return;
        }
        PlayerPointer playerPointer = new PlayerPointer((EntityPlayer)player);
        EquipmentWardrobeData ewd = ClientProxy.equipmentWardrobeHandler.getEquipmentWardrobeData(playerPointer);
        if (ewd == null) {
            return;
        }
        this.profiler.func_76320_a("textureReset");
        if (this.playerTextureMap.containsKey(playerPointer)) {
            EntityTextureInfo textureInfo = this.playerTextureMap.get(playerPointer);
            ResourceLocation replacmentTexture = textureInfo.postRender();
            player.func_152121_a(MinecraftProfileTexture.Type.SKIN, replacmentTexture);
        } else {
            this.playerTextureMap.put(playerPointer, new EntityTextureInfo());
        }
        this.profiler.func_76319_b();
    }
}

