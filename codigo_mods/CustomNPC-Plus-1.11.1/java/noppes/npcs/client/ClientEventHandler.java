/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.EventPriority
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.AbstractClientPlayer
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.renderer.entity.RendererLivingEntity
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.client.event.MouseEvent
 *  net.minecraftforge.client.event.RenderGameOverlayEvent$ElementType
 *  net.minecraftforge.client.event.RenderGameOverlayEvent$Post
 *  net.minecraftforge.client.event.RenderHandEvent
 *  net.minecraftforge.client.event.RenderLivingEvent$Post
 *  net.minecraftforge.client.event.RenderLivingEvent$Pre
 *  net.minecraftforge.client.event.RenderPlayerEvent$Post
 *  net.minecraftforge.client.event.RenderPlayerEvent$Pre
 *  net.minecraftforge.client.event.RenderPlayerEvent$Specials$Post
 *  net.minecraftforge.client.event.RenderPlayerEvent$Specials$Pre
 *  net.minecraftforge.event.entity.player.ItemTooltipEvent
 */
package noppes.npcs.client;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import noppes.npcs.client.ClientAbilityState;
import noppes.npcs.client.ClientCacheHandler;
import noppes.npcs.client.KeyPressHandler;
import noppes.npcs.client.gui.customoverlay.OverlayCustom;
import noppes.npcs.client.gui.hud.ClientHudManager;
import noppes.npcs.client.gui.hud.CompassHudComponent;
import noppes.npcs.client.gui.hud.EnumHudComponent;
import noppes.npcs.client.gui.hud.QuestTrackingComponent;
import noppes.npcs.client.gui.hud.ability.AbilityHotbarComponent;
import noppes.npcs.client.gui.player.AuctionTooltipHandler;
import noppes.npcs.client.renderer.MarkRenderer;
import noppes.npcs.client.renderer.RenderCNPCPlayer;
import noppes.npcs.constants.EnumAnimationPart;
import noppes.npcs.controllers.data.FramePart;
import noppes.npcs.controllers.data.MarkData;
import noppes.npcs.controllers.data.PlayerData;
import noppes.npcs.entity.EntityNPCInterface;

public class ClientEventHandler {
    public static final RenderCNPCPlayer renderCNPCSelf = new RenderCNPCPlayer();
    public static final RenderCNPCPlayer renderCNPCPlayer = new RenderCNPCPlayer();
    public static HashMap<Integer, Long> disabledButtonTimes = new HashMap();
    public static float partialHandTicks;
    public static boolean firstPersonAnimation;
    public static ModelBiped firstPersonModel;
    public static final ResourceLocation steveTextures;
    public static final ResourceLocation fem;
    public static float partialRenderTick;
    public static RendererLivingEntity renderer;
    public static boolean renderingEntityInGUI;
    public static EntityNPCInterface renderingNpc;
    public static EntityPlayer renderingPlayer;
    public static HashMap<EnumAnimationPart, String[]> partNames;
    public static HashMap<Class<?>, Field[]> declaredFieldCache;
    private static final HashSet<Render> processedPlayerRenderers;
    public static final HashMap<ModelRenderer, FramePart> originalValues;
    public static ModelBase playerModel;
    private Class<?> renderPlayerJBRA;

    public ClientEventHandler() {
        try {
            this.renderPlayerJBRA = Class.forName("JinRyuu.JBRA.RenderPlayerJBRA");
        }
        catch (ClassNotFoundException e) {
            this.renderPlayerJBRA = null;
        }
    }

    @SubscribeEvent
    public void onItemTooltip(ItemTooltipEvent event) {
        AuctionTooltipHandler.onItemTooltip(event);
    }

    @SubscribeEvent
    public void onMouse(MouseEvent event) {
        AbilityHotbarComponent comp;
        if (event.button >= 0) {
            KeyPressHandler.trackMouseButton(event.button, event.buttonstate);
        }
        Minecraft mc = Minecraft.func_71410_x();
        if (mc.field_71462_r != null) {
            return;
        }
        if (mc.field_71441_e != null && mc.field_71439_g != null && ClientAbilityState.shouldLockRotation() && (event.dx != 0 || event.dy != 0) && event.button == -1 && event.dwheel == 0) {
            event.setCanceled(true);
            return;
        }
        if (event.dwheel != 0 && KeyPressHandler.isHudKeyHeld() && (comp = (AbilityHotbarComponent)ClientHudManager.getInstance().getHudComponents().get((Object)EnumHudComponent.AbilityHotbar)) != null && comp.hasAnyAbilities()) {
            if (event.dwheel > 0) {
                comp.onCyclePrev();
            } else {
                comp.onCycleNext();
            }
            event.setCanceled(true);
            return;
        }
        ArrayList<Integer> removeList = new ArrayList<Integer>();
        for (Map.Entry<Integer, Long> entry : disabledButtonTimes.entrySet()) {
            if (entry.getValue() > 0L) {
                if (entry.getKey() == event.button || entry.getKey() == -1) {
                    event.setCanceled(true);
                }
                disabledButtonTimes.put(entry.getKey(), entry.getValue() - 1L);
                continue;
            }
            removeList.add(entry.getKey());
        }
        Iterator<Map.Entry<Integer, Long>> iterator = removeList.iterator();
        while (iterator.hasNext()) {
            int i = (Integer)((Object)iterator.next());
            disabledButtonTimes.remove(i);
        }
    }

    @SubscribeEvent
    public void onOverlayRender(RenderGameOverlayEvent.Post event) {
        if (event.type == RenderGameOverlayEvent.ElementType.ALL) {
            for (OverlayCustom overlayCustom : ClientCacheHandler.customOverlays.values()) {
                overlayCustom.renderGameOverlay(event.partialTicks);
            }
            if (ClientHudManager.getInstance().getHudComponents().isEmpty()) {
                ClientHudManager.getInstance().registerHud(EnumHudComponent.QuestTracker, new QuestTrackingComponent(Minecraft.func_71410_x()));
                ClientHudManager.getInstance().registerHud(EnumHudComponent.QuestCompass, new CompassHudComponent(Minecraft.func_71410_x()));
                ClientHudManager.getInstance().registerHud(EnumHudComponent.AbilityHotbar, new AbilityHotbarComponent(Minecraft.func_71410_x()));
            }
            ClientHudManager.getInstance().renderAllHUDs(event.partialTicks);
        }
    }

    @SubscribeEvent
    public void onRenderEntity(RenderLivingEvent.Pre event) {
        if (event.entity instanceof EntityNPCInterface) {
            renderingPlayer = null;
            renderingNpc = (EntityNPCInterface)event.entity;
        }
        renderer = event.renderer;
        partialRenderTick = Minecraft.func_71410_x().field_71428_T.field_74281_c;
        this.setOriginalPlayerParts((Entity)event.entity);
    }

    @SubscribeEvent
    public void renderHand(RenderHandEvent event) {
        this.setOriginalPlayerParts((Entity)Minecraft.func_71410_x().field_71439_g);
    }

    private void setOriginalPlayerParts(Entity entity) {
        Render render;
        if (entity instanceof EntityClientPlayerMP && !processedPlayerRenderers.contains(render = RenderManager.field_78727_a.func_78715_a(entity.getClass()))) {
            processedPlayerRenderers.add(render);
            Collection<ModelRenderer> modelRenderers = ClientEventHandler.getAllModelRenderers(render);
            for (ModelRenderer modelRenderer : modelRenderers) {
                if (originalValues.containsKey(modelRenderer)) continue;
                FramePart part = new FramePart();
                part.pivot = new float[]{modelRenderer.field_78800_c, modelRenderer.field_78797_d, modelRenderer.field_78798_e};
                part.rotation = new float[]{modelRenderer.field_78795_f, modelRenderer.field_78796_g, modelRenderer.field_78808_h};
                originalValues.put(modelRenderer, part);
            }
        }
    }

    @SubscribeEvent
    public void onRenderEntity(RenderLivingEvent.Post event) {
        if (event.entity instanceof EntityNPCInterface) {
            MarkData markData = MarkData.get((EntityNPCInterface)event.entity);
            EntityClientPlayerMP player = Minecraft.func_71410_x().field_71439_g;
            if (PlayerData.get((EntityPlayer)player) != null) {
                for (MarkData.Mark m : markData.marks) {
                    if (m.getType() == 0 || !m.availability.isAvailable((EntityPlayer)player)) continue;
                    MarkRenderer.render(event.entity, event.x, event.y, event.z, m);
                    break;
                }
            }
        } else if (event.entity instanceof EntityPlayer) {
            for (Map.Entry<ModelRenderer, FramePart> entry : originalValues.entrySet()) {
                ModelRenderer renderer = entry.getKey();
                FramePart part = entry.getValue();
                renderer.field_78795_f = part.rotation[0];
                renderer.field_78796_g = part.rotation[1];
                renderer.field_78808_h = part.rotation[2];
                renderer.field_78800_c = part.pivot[0];
                renderer.field_78797_d = part.pivot[1];
                renderer.field_78798_e = part.pivot[2];
            }
            playerModel = null;
        }
        renderingNpc = null;
    }

    @SubscribeEvent
    public void onRenderPlayer(RenderPlayerEvent.Pre event) {
        renderingNpc = null;
        renderingPlayer = event.entityPlayer;
    }

    @SubscribeEvent(priority=EventPriority.LOWEST)
    public void pre(RenderPlayerEvent.Pre event) {
        if (!(event.entity instanceof AbstractClientPlayer)) {
            return;
        }
    }

    @SubscribeEvent
    public void onRenderPlayer(RenderPlayerEvent.Post event) {
        EntityPlayer player = event.entityPlayer;
        renderingPlayer = null;
        if (ClientEventHandler.hasOverlays(player)) {
            if (this.renderPlayerJBRA != null && this.renderPlayerJBRA.isInstance(event.renderer)) {
                return;
            }
            if (!(event.renderer instanceof RenderCNPCPlayer)) {
                ClientEventHandler.renderCNPCPlayer.field_77045_g = event.renderer.field_77045_g;
                ClientEventHandler.renderCNPCPlayer.field_77109_a = event.renderer.field_77109_a;
                ClientEventHandler.renderCNPCPlayer.field_77111_i = event.renderer.field_77111_i;
                ClientEventHandler.renderCNPCPlayer.field_77108_b = event.renderer.field_77108_b;
                ClientEventHandler.renderCNPCPlayer.tempRenderPartialTicks = event.partialRenderTick;
                double d0 = player.field_70142_S + (player.field_70165_t - player.field_70142_S) * (double)event.partialRenderTick - RenderManager.field_78725_b;
                double d1 = player.field_70137_T + (player.field_70163_u - player.field_70137_T) * (double)event.partialRenderTick - RenderManager.field_78726_c;
                double d2 = player.field_70136_U + (player.field_70161_v - player.field_70136_U) * (double)event.partialRenderTick - RenderManager.field_78723_d;
                float f1 = player.field_70126_B + (player.field_70177_z - player.field_70126_B) * event.partialRenderTick;
                if (Minecraft.func_71410_x().field_71439_g.equals((Object)player)) {
                    d0 = 0.0;
                    d1 = 0.0;
                    d2 = 0.0;
                }
                renderCNPCPlayer.func_76986_a((EntityLivingBase)player, d0, d1, d2, f1, event.partialRenderTick);
            }
        }
    }

    @SubscribeEvent
    public void cancelSpecials(RenderPlayerEvent.Specials.Pre event) {
        if (event.renderer instanceof RenderCNPCPlayer) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void tryRenderDBC(RenderPlayerEvent.Specials.Post event) {
        if (!ClientEventHandler.hasOverlays(event.entityPlayer)) {
            return;
        }
        if (this.renderPlayerJBRA == null || !this.renderPlayerJBRA.isInstance(event.renderer)) {
            return;
        }
        renderCNPCPlayer.renderDBCModel(event);
    }

    public static boolean hasOverlays(EntityPlayer player) {
        return ClientCacheHandler.skinOverlays.containsKey(player.func_110124_au()) && !ClientCacheHandler.skinOverlays.get(player.func_110124_au()).values().isEmpty();
    }

    public static Collection<ModelRenderer> getAllModelRenderers(Object object) {
        HashSet<ModelRenderer> modelRenderers = new HashSet<ModelRenderer>();
        HashSet<ModelBase> modelBases = ClientEventHandler.getAllModelBases(object);
        for (ModelBase modelBase : modelBases) {
            modelRenderers.addAll(ClientEventHandler.getModelRenderersFromModelBase(modelBase));
        }
        return modelRenderers;
    }

    private static HashSet<ModelBase> getAllModelBases(Object object) {
        HashSet<ModelBase> modelBases = new HashSet<ModelBase>();
        if (object == null) {
            return modelBases;
        }
        for (Class<?> clazz = object.getClass(); clazz != null; clazz = clazz.getSuperclass()) {
            Field[] fields;
            for (Field field : fields = clazz.getDeclaredFields()) {
                field.setAccessible(true);
                try {
                    Object value = field.get(object);
                    if (!(value instanceof ModelBase)) continue;
                    modelBases.add((ModelBase)value);
                }
                catch (IllegalAccessException illegalAccessException) {
                    // empty catch block
                }
            }
        }
        return modelBases;
    }

    private static HashSet<ModelRenderer> getModelRenderersFromModelBase(ModelBase modelBase) {
        HashSet<ModelRenderer> modelRenderers = new HashSet<ModelRenderer>();
        for (Class<?> clazz = modelBase.getClass(); clazz != null; clazz = clazz.getSuperclass()) {
            Field[] fields;
            for (Field field : fields = clazz.getDeclaredFields()) {
                field.setAccessible(true);
                try {
                    Object value = field.get(modelBase);
                    if (!(value instanceof ModelRenderer) || ClientEventHandler.getPlayerPartType((ModelRenderer)value) == null) continue;
                    modelRenderers.add((ModelRenderer)value);
                }
                catch (IllegalAccessException illegalAccessException) {
                    // empty catch block
                }
            }
        }
        return modelRenderers;
    }

    private static EnumAnimationPart getPlayerPartType(ModelRenderer renderer) {
        if (renderer.field_78810_s instanceof ModelBiped) {
            if (renderer == ((ModelBiped)renderer.field_78810_s).field_78116_c || renderer == ((ModelBiped)renderer.field_78810_s).field_78114_d) {
                return EnumAnimationPart.HEAD;
            }
            if (renderer == ((ModelBiped)renderer.field_78810_s).field_78115_e) {
                return EnumAnimationPart.BODY;
            }
            if (renderer == ((ModelBiped)renderer.field_78810_s).field_78112_f) {
                return EnumAnimationPart.RIGHT_ARM;
            }
            if (renderer == ((ModelBiped)renderer.field_78810_s).field_78113_g) {
                return EnumAnimationPart.LEFT_ARM;
            }
            if (renderer == ((ModelBiped)renderer.field_78810_s).field_78123_h) {
                return EnumAnimationPart.RIGHT_LEG;
            }
            if (renderer == ((ModelBiped)renderer.field_78810_s).field_78124_i) {
                return EnumAnimationPart.LEFT_LEG;
            }
        }
        try {
            Field[] declared;
            Class<?> ModelBipedBody = Class.forName("JinRyuu.JRMCore.entity.ModelBipedBody");
            ModelBase model = renderer.field_78810_s;
            if (!(model instanceof ModelBiped)) {
                return null;
            }
            if (declaredFieldCache.containsKey(ModelBipedBody)) {
                declared = declaredFieldCache.get(ModelBipedBody);
            } else {
                declared = ModelBipedBody.getDeclaredFields();
                declaredFieldCache.put(ModelBipedBody, declared);
            }
            Set<Map.Entry<EnumAnimationPart, String[]>> entrySet = partNames.entrySet();
            for (Field f : declared) {
                f.setAccessible(true);
                for (Map.Entry<EnumAnimationPart, String[]> entry : entrySet) {
                    String[] names;
                    for (String partName : names = entry.getValue()) {
                        try {
                            if (!partName.equals(f.getName()) || renderer != f.get(model)) continue;
                            return entry.getKey();
                        }
                        catch (IllegalAccessException illegalAccessException) {
                            // empty catch block
                        }
                    }
                }
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        return null;
    }

    static {
        steveTextures = new ResourceLocation("textures/entity/steve.png");
        fem = new ResourceLocation("jinryuufamilyc:fem.png");
        partNames = new HashMap();
        declaredFieldCache = new HashMap();
        processedPlayerRenderers = new HashSet();
        originalValues = new HashMap();
    }
}

