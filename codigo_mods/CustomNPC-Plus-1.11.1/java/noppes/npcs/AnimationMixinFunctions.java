/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.client.renderer.ItemRenderer
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.client.renderer.texture.TextureUtil
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.client.ForgeHooksClient
 *  net.minecraftforge.client.IItemRenderer
 *  net.minecraftforge.client.IItemRenderer$ItemRenderType
 *  net.minecraftforge.client.MinecraftForgeClient
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;
import kamkeel.npcs.addon.DBCAddon;
import kamkeel.npcs.addon.client.DBCClient;
import kamkeel.npcs.addon.client.DBCClientAnimations;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import noppes.npcs.client.ClientCacheHandler;
import noppes.npcs.client.ClientEventHandler;
import noppes.npcs.constants.EnumAnimationPart;
import noppes.npcs.controllers.data.Animation;
import noppes.npcs.controllers.data.AnimationData;
import noppes.npcs.controllers.data.Frame;
import noppes.npcs.controllers.data.FramePart;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public class AnimationMixinFunctions {
    /*
     * Enabled aggressive block sorting
     */
    public static boolean applyValues(ModelRenderer modelRenderer) {
        if (ClientEventHandler.renderingPlayer == null && ClientEventHandler.renderingNpc == null) {
            return false;
        }
        if (DBCAddon.IsAvailable()) {
            if (ClientEventHandler.renderingPlayer != null) {
                return DBCClient.Instance.applyRenderModel(modelRenderer);
            }
            if (DBCClient.Instance.applyRenderModel(modelRenderer)) {
                return false;
            }
        }
        if (ClientEventHandler.renderingPlayer == null) {
            if (!ClientEventHandler.renderingNpc.display.animationData.isActive()) return false;
            AnimationData animData = ClientEventHandler.renderingNpc.display.animationData;
            EnumAnimationPart partType = AnimationMixinFunctions.getPartType(modelRenderer);
            if (partType == null) return false;
            if (animData == null) return false;
            Frame frame = (Frame)animData.animation.currentFrame();
            if (!frame.frameParts.containsKey((Object)partType)) return false;
            FramePart part = frame.frameParts.get((Object)partType);
            part.interpolateOffset();
            part.interpolateAngles();
            modelRenderer.field_78800_c += part.prevPivots[0];
            modelRenderer.field_78797_d += part.prevPivots[1];
            modelRenderer.field_78798_e += part.prevPivots[2];
            modelRenderer.field_78795_f = part.prevRotations[0];
            modelRenderer.field_78796_g = part.prevRotations[1];
            modelRenderer.field_78808_h = part.prevRotations[2];
            return true;
        }
        ClientEventHandler.playerModel = modelRenderer.field_78810_s;
        if (!ClientCacheHandler.playerAnimations.containsKey(ClientEventHandler.renderingPlayer.func_110124_au())) return false;
        AnimationData animData = ClientCacheHandler.playerAnimations.get(ClientEventHandler.renderingPlayer.func_110124_au());
        EnumAnimationPart mainPartType = AnimationMixinFunctions.getPlayerPartType(modelRenderer);
        EnumAnimationPart partType = mainPartType != null ? mainPartType : AnimationMixinFunctions.pivotEqualPart(modelRenderer);
        if (partType == null) return false;
        if (animData == null) return false;
        if (animData.animation == null) return false;
        if (!animData.isActive()) return false;
        if (!ClientEventHandler.originalValues.containsKey(modelRenderer)) {
            FramePart part = new FramePart();
            part.pivot = new float[]{modelRenderer.field_78800_c, modelRenderer.field_78797_d, modelRenderer.field_78798_e};
            part.rotation = new float[]{modelRenderer.field_78795_f, modelRenderer.field_78796_g, modelRenderer.field_78808_h};
            ClientEventHandler.originalValues.put(modelRenderer, part);
        }
        FramePart originalPart = ClientEventHandler.originalValues.get(modelRenderer);
        Frame frame = (Frame)animData.animation.currentFrame();
        if (frame == null) return false;
        if (!frame.frameParts.containsKey((Object)partType)) return false;
        FramePart part = frame.frameParts.get((Object)partType);
        if (partType == mainPartType) {
            part.interpolateAngles();
            part.interpolateOffset();
            modelRenderer.field_78800_c = originalPart.pivot[0] + part.prevPivots[0];
            modelRenderer.field_78797_d = originalPart.pivot[1] + part.prevPivots[1];
            modelRenderer.field_78798_e = originalPart.pivot[2] + part.prevPivots[2];
            modelRenderer.field_78795_f = part.prevRotations[0];
            modelRenderer.field_78796_g = part.prevRotations[1];
            modelRenderer.field_78808_h = part.prevRotations[2];
            return false;
        }
        modelRenderer.field_78808_h += part.prevRotations[2];
        return true;
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
        return AnimationMixinFunctions.getPartType(renderer);
    }

    private static EnumAnimationPart pivotEqualPart(ModelRenderer renderer) {
        if (renderer.field_78810_s instanceof ModelBiped) {
            ModelRenderer head = ((ModelBiped)renderer.field_78810_s).field_78116_c;
            ModelRenderer body = ((ModelBiped)renderer.field_78810_s).field_78115_e;
            ModelRenderer larm = ((ModelBiped)renderer.field_78810_s).field_78113_g;
            ModelRenderer rarm = ((ModelBiped)renderer.field_78810_s).field_78112_f;
            ModelRenderer lleg = ((ModelBiped)renderer.field_78810_s).field_78124_i;
            ModelRenderer rleg = ((ModelBiped)renderer.field_78810_s).field_78123_h;
            if (AnimationMixinFunctions.pivotsEqual(renderer, head)) {
                return EnumAnimationPart.HEAD;
            }
            if (AnimationMixinFunctions.pivotsEqual(renderer, body)) {
                return EnumAnimationPart.BODY;
            }
            if (AnimationMixinFunctions.pivotsEqual(renderer, rarm)) {
                return EnumAnimationPart.RIGHT_ARM;
            }
            if (AnimationMixinFunctions.pivotsEqual(renderer, larm)) {
                return EnumAnimationPart.LEFT_ARM;
            }
            if (AnimationMixinFunctions.pivotsEqual(renderer, rleg)) {
                return EnumAnimationPart.RIGHT_LEG;
            }
            if (AnimationMixinFunctions.pivotsEqual(renderer, lleg)) {
                return EnumAnimationPart.LEFT_LEG;
            }
        }
        return null;
    }

    private static boolean pivotsEqual(ModelRenderer m1, ModelRenderer m2) {
        return m1.field_78800_c == m2.field_78800_c && m1.field_78797_d == m2.field_78797_d && m1.field_78798_e == m2.field_78798_e;
    }

    private static EnumAnimationPart getPartType(ModelRenderer renderer) {
        ModelBase model = renderer.field_78810_s;
        Set<Map.Entry<EnumAnimationPart, String[]>> entrySet = ClientEventHandler.partNames.entrySet();
        for (Class<?> RenderClass = renderer.field_78810_s.getClass(); RenderClass != Object.class; RenderClass = RenderClass.getSuperclass()) {
            Field[] declared;
            if (ClientEventHandler.declaredFieldCache.containsKey(RenderClass)) {
                declared = ClientEventHandler.declaredFieldCache.get(RenderClass);
            } else {
                declared = RenderClass.getDeclaredFields();
                ClientEventHandler.declaredFieldCache.put(RenderClass, declared);
            }
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
        return null;
    }

    public static void playerFullModel_head(Entity entity, CallbackInfo callbackInfo) {
        if (!ClientCacheHandler.playerAnimations.containsKey(entity.func_110124_au())) {
            return;
        }
        AnimationData animData = ClientCacheHandler.playerAnimations.get(entity.func_110124_au());
        if (animData == null || !animData.isActive()) {
            return;
        }
        if (entity instanceof EntityLivingBase) {
            EntityLivingBase living = (EntityLivingBase)entity;
            living.field_70761_aq = living.field_70177_z;
            living.field_70760_ar = living.field_70126_B;
        }
        if (!DBCAddon.IsAvailable()) {
            Frame frame = (Frame)animData.animation.currentFrame();
            if (frame.frameParts.containsKey((Object)EnumAnimationPart.FULL_MODEL)) {
                FramePart part = frame.frameParts.get((Object)EnumAnimationPart.FULL_MODEL);
                part.interpolateOffset();
                part.interpolateAngles();
                float pi = 57.295776f;
                GL11.glTranslatef((float)part.prevPivots[0], (float)(-part.prevPivots[1]), (float)part.prevPivots[2]);
                GL11.glRotatef((float)(part.prevRotations[0] * pi), (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)(part.prevRotations[1] * pi), (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glRotatef((float)(part.prevRotations[2] * pi), (float)0.0f, (float)0.0f, (float)1.0f);
            }
        }
    }

    public static boolean mixin_renderFirstPersonAnimation(float partialRenderTick, EntityPlayer player, ModelBiped model, RenderBlocks renderBlocksIr, ResourceLocation resItemGlint) {
        int i;
        Frame frame;
        if (DBCAddon.IsAvailable()) {
            return DBCClient.Instance.firstPersonAnimation(partialRenderTick, player, model, renderBlocksIr, resItemGlint);
        }
        AnimationData animationData = ClientCacheHandler.playerAnimations.get(player.func_110124_au());
        if (animationData != null && animationData.isActive()) {
            Frame frame2 = (Frame)animationData.animation.currentFrame();
            if (frame2.frameParts.containsKey((Object)EnumAnimationPart.FULL_MODEL)) {
                FramePart part = frame2.frameParts.get((Object)EnumAnimationPart.FULL_MODEL);
                part.interpolateOffset();
                part.interpolateAngles();
            }
        }
        ModelRenderer[] parts = new ModelRenderer[]{model.field_78112_f, model.field_78113_g, model.field_78123_h, model.field_78124_i};
        EnumAnimationPart[] enumParts = new EnumAnimationPart[]{EnumAnimationPart.RIGHT_ARM, EnumAnimationPart.LEFT_ARM, EnumAnimationPart.RIGHT_LEG, EnumAnimationPart.LEFT_LEG};
        if (animationData != null && animationData.isActive()) {
            Animation animation = (Animation)animationData.getAnimation();
            frame = (Frame)animation.currentFrame();
            for (i = 0; i < parts.length; ++i) {
                ModelRenderer part = parts[i];
                EnumAnimationPart enumPart = enumParts[i];
                FramePart originalPart = ClientEventHandler.originalValues.get(part);
                if (originalPart == null || !frame.frameParts.containsKey((Object)enumPart)) continue;
                FramePart animatedPart = frame.frameParts.get((Object)enumPart);
                part.field_78800_c = originalPart.pivot[0] + animatedPart.prevPivots[0];
                part.field_78797_d = originalPart.pivot[1] + animatedPart.prevPivots[1];
                part.field_78798_e = originalPart.pivot[2] + animatedPart.prevPivots[2];
                part.field_78795_f = animatedPart.prevRotations[0];
                part.field_78796_g = animatedPart.prevRotations[1];
                part.field_78808_h = animatedPart.prevRotations[2];
            }
        } else {
            return false;
        }
        if (animationData != null && animationData.isActive() && frame.frameParts.containsKey((Object)EnumAnimationPart.FULL_MODEL)) {
            FramePart part = frame.frameParts.get((Object)EnumAnimationPart.FULL_MODEL);
            float pi = 57.295776f;
            GL11.glRotatef((float)(-part.prevRotations[1] * pi), (float)0.0f, (float)1.0f, (float)0.0f);
        }
        EntityClientPlayerMP entityclientplayermp = Minecraft.func_71410_x().field_71439_g;
        RenderHelper.func_74519_b();
        i = Minecraft.func_71410_x().field_71441_e.func_72802_i(MathHelper.func_76128_c((double)entityclientplayermp.field_70165_t), MathHelper.func_76128_c((double)entityclientplayermp.field_70163_u), MathHelper.func_76128_c((double)entityclientplayermp.field_70161_v), 0);
        int j = i % 65536;
        int k = i / 65536;
        OpenGlHelper.func_77475_a((int)OpenGlHelper.field_77476_b, (float)j, (float)k);
        float f3 = entityclientplayermp.field_71164_i + (entityclientplayermp.field_71155_g - entityclientplayermp.field_71164_i) * partialRenderTick;
        float f4 = entityclientplayermp.field_71163_h + (entityclientplayermp.field_71154_f - entityclientplayermp.field_71163_h) * partialRenderTick;
        GL11.glRotatef((float)((entityclientplayermp.field_70125_A - f3) * 0.1f), (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glRotatef((float)((entityclientplayermp.field_70177_z - f4) * 0.1f), (float)0.0f, (float)1.0f, (float)0.0f);
        try {
            DBCClientAnimations.doDBCRender(player);
        }
        catch (Exception e) {
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            Minecraft.func_71410_x().func_110434_K().func_110577_a(entityclientplayermp.func_110306_p());
            AnimationMixinFunctions.renderLimbs();
        }
        ItemRenderer itemRenderer = Minecraft.func_71410_x().field_71460_t.field_78516_c;
        ItemStack itemstack = itemRenderer.field_78453_b;
        if (frame.frameParts.containsKey((Object)EnumAnimationPart.RIGHT_ARM) && itemstack != null) {
            float f12;
            GL11.glPushMatrix();
            if (player.field_71104_cf != null) {
                itemstack = new ItemStack(Items.field_151055_y);
            }
            float p_78785_1_ = 0.0625f;
            GL11.glTranslatef((float)0.1f, (float)-0.20000002f, (float)-0.3f);
            GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glTranslatef((float)(model.field_78112_f.field_78800_c * p_78785_1_), (float)(model.field_78112_f.field_78797_d * p_78785_1_), (float)(model.field_78112_f.field_78798_e * p_78785_1_));
            GL11.glRotatef((float)((float)Math.toDegrees(model.field_78112_f.field_78808_h)), (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glRotatef((float)((float)Math.toDegrees(model.field_78112_f.field_78796_g)), (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glRotatef((float)((float)Math.toDegrees(model.field_78112_f.field_78795_f)), (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glTranslatef((float)-0.1f, (float)0.6f, (float)0.0f);
            GL11.glRotatef((float)255.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glRotatef((float)45.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glRotatef((float)80.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            float f6 = 0.6666667f;
            GL11.glScalef((float)f6, (float)f6, (float)f6);
            if (itemstack.func_77973_b().func_77623_v()) {
                for (k = 0; k < itemstack.func_77973_b().getRenderPasses(itemstack.func_77960_j()); ++k) {
                    i = itemstack.func_77973_b().func_82790_a(itemstack, k);
                    f12 = (float)(i >> 16 & 0xFF) / 255.0f;
                    f3 = (float)(i >> 8 & 0xFF) / 255.0f;
                    f4 = (float)(i & 0xFF) / 255.0f;
                    GL11.glColor4f((float)f12, (float)f3, (float)f4, (float)1.0f);
                    AnimationMixinFunctions.mixin_renderItem((EntityLivingBase)player, itemstack, k, IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON, renderBlocksIr, resItemGlint);
                }
            } else {
                k = itemstack.func_77973_b().func_82790_a(itemstack, 0);
                float f11 = (float)(k >> 16 & 0xFF) / 255.0f;
                f12 = (float)(k >> 8 & 0xFF) / 255.0f;
                f3 = (float)(k & 0xFF) / 255.0f;
                GL11.glColor4f((float)f11, (float)f12, (float)f3, (float)1.0f);
                AnimationMixinFunctions.mixin_renderItem((EntityLivingBase)player, itemstack, 0, IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON, renderBlocksIr, resItemGlint);
            }
            GL11.glPopMatrix();
        }
        for (int p = 0; p < parts.length; ++p) {
            ModelRenderer part = parts[p];
            EnumAnimationPart enumPart = enumParts[p];
            FramePart originalPart = ClientEventHandler.originalValues.get(part);
            if (originalPart == null || !frame.frameParts.containsKey((Object)enumPart)) continue;
            part.field_78800_c = originalPart.pivot[0];
            part.field_78797_d = originalPart.pivot[1];
            part.field_78798_e = originalPart.pivot[2];
            part.field_78795_f = originalPart.rotation[0];
            part.field_78796_g = originalPart.rotation[1];
            part.field_78808_h = originalPart.rotation[2];
        }
        return true;
    }

    private static void mixin_renderItem(EntityLivingBase p_78443_1_, ItemStack p_78443_2_, int p_78443_3_, IItemRenderer.ItemRenderType type, RenderBlocks renderBlocksIr, ResourceLocation resItemGlint) {
        IItemRenderer customRenderer;
        GL11.glPushMatrix();
        TextureManager texturemanager = Minecraft.func_71410_x().func_110434_K();
        Item item = p_78443_2_.func_77973_b();
        Block block = Block.func_149634_a((Item)item);
        if (p_78443_2_ != null && block != null && block.func_149701_w() != 0) {
            GL11.glEnable((int)3042);
            GL11.glEnable((int)2884);
            OpenGlHelper.func_148821_a((int)770, (int)771, (int)1, (int)0);
        }
        if ((customRenderer = MinecraftForgeClient.getItemRenderer((ItemStack)p_78443_2_, (IItemRenderer.ItemRenderType)type)) != null) {
            texturemanager.func_110577_a(texturemanager.func_130087_a(p_78443_2_.func_94608_d()));
            ForgeHooksClient.renderEquippedItem((IItemRenderer.ItemRenderType)type, (IItemRenderer)customRenderer, (RenderBlocks)renderBlocksIr, (EntityLivingBase)p_78443_1_, (ItemStack)p_78443_2_);
        } else if (p_78443_2_.func_94608_d() == 0 && item instanceof ItemBlock && RenderBlocks.func_147739_a((int)block.func_149645_b())) {
            texturemanager.func_110577_a(texturemanager.func_130087_a(0));
            GL11.glTranslatef((float)0.0f, (float)0.2f, (float)-0.2f);
            if (p_78443_2_ != null && block != null && block.func_149701_w() != 0) {
                GL11.glDepthMask((boolean)false);
                renderBlocksIr.func_147800_a(block, p_78443_2_.func_77960_j(), 1.0f);
                GL11.glDepthMask((boolean)true);
            } else {
                renderBlocksIr.func_147800_a(block, p_78443_2_.func_77960_j(), 1.0f);
            }
        } else {
            IIcon iicon = p_78443_1_.func_70620_b(p_78443_2_, p_78443_3_);
            if (iicon == null) {
                GL11.glPopMatrix();
                return;
            }
            texturemanager.func_110577_a(texturemanager.func_130087_a(p_78443_2_.func_94608_d()));
            TextureUtil.func_152777_a((boolean)false, (boolean)false, (float)1.0f);
            Tessellator tessellator = Tessellator.field_78398_a;
            float f = iicon.func_94209_e();
            float f1 = iicon.func_94212_f();
            float f2 = iicon.func_94206_g();
            float f3 = iicon.func_94210_h();
            float f4 = 0.0f;
            float f5 = 0.3f;
            GL11.glEnable((int)32826);
            GL11.glTranslatef((float)(-f4), (float)(-f5), (float)0.0f);
            float f6 = 1.5f;
            GL11.glScalef((float)f6, (float)f6, (float)f6);
            GL11.glRotatef((float)50.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glRotatef((float)335.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glTranslatef((float)-0.9375f, (float)-0.0625f, (float)0.0f);
            ItemRenderer.func_78439_a((Tessellator)tessellator, (float)f1, (float)f2, (float)f, (float)f3, (int)iicon.func_94211_a(), (int)iicon.func_94216_b(), (float)0.0625f);
            if (p_78443_2_.hasEffect(p_78443_3_)) {
                GL11.glDepthFunc((int)514);
                GL11.glDisable((int)2896);
                texturemanager.func_110577_a(resItemGlint);
                GL11.glEnable((int)3042);
                OpenGlHelper.func_148821_a((int)768, (int)1, (int)1, (int)0);
                float f7 = 0.76f;
                GL11.glColor4f((float)(0.5f * f7), (float)(0.25f * f7), (float)(0.8f * f7), (float)1.0f);
                GL11.glMatrixMode((int)5890);
                GL11.glPushMatrix();
                float f8 = 0.125f;
                GL11.glScalef((float)f8, (float)f8, (float)f8);
                float f9 = (float)(Minecraft.func_71386_F() % 3000L) / 3000.0f * 8.0f;
                GL11.glTranslatef((float)f9, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)-50.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                ItemRenderer.func_78439_a((Tessellator)tessellator, (float)0.0f, (float)0.0f, (float)1.0f, (float)1.0f, (int)256, (int)256, (float)0.0625f);
                GL11.glPopMatrix();
                GL11.glPushMatrix();
                GL11.glScalef((float)f8, (float)f8, (float)f8);
                f9 = (float)(Minecraft.func_71386_F() % 4873L) / 4873.0f * 8.0f;
                GL11.glTranslatef((float)(-f9), (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)10.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                ItemRenderer.func_78439_a((Tessellator)tessellator, (float)0.0f, (float)0.0f, (float)1.0f, (float)1.0f, (int)256, (int)256, (float)0.0625f);
                GL11.glPopMatrix();
                GL11.glMatrixMode((int)5888);
                GL11.glDisable((int)3042);
                GL11.glEnable((int)2896);
                GL11.glDepthFunc((int)515);
            }
            GL11.glDisable((int)32826);
            texturemanager.func_110577_a(texturemanager.func_130087_a(p_78443_2_.func_94608_d()));
            TextureUtil.func_147945_b();
        }
        GL11.glPopMatrix();
    }

    public static void renderLimbs() {
        AnimationData animationData = ClientCacheHandler.playerAnimations.get(Minecraft.func_71410_x().field_71439_g.func_110124_au());
        if (animationData != null && animationData.isActive() && animationData.getAnimation() != null && animationData.getAnimation().currentFrame() != null) {
            Animation animation = (Animation)animationData.getAnimation();
            Frame frame = (Frame)animation.currentFrame();
            ModelBiped model = ClientEventHandler.firstPersonModel;
            if (frame.frameParts.containsKey((Object)EnumAnimationPart.RIGHT_ARM)) {
                GL11.glPushMatrix();
                GL11.glTranslatef((float)0.1f, (float)-0.20000002f, (float)-0.3f);
                GL11.glEnable((int)32826);
                GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glRotatef((float)0.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                model.field_78112_f.func_78785_a(0.0625f);
                GL11.glPopMatrix();
            }
            if (frame.frameParts.containsKey((Object)EnumAnimationPart.LEFT_ARM)) {
                GL11.glPushMatrix();
                GL11.glTranslatef((float)-0.1f, (float)-0.20000002f, (float)-0.3f);
                GL11.glEnable((int)32826);
                GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glRotatef((float)0.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                model.field_78113_g.func_78785_a(0.0625f);
                GL11.glPopMatrix();
            }
            if (frame.frameParts.containsKey((Object)EnumAnimationPart.RIGHT_LEG)) {
                GL11.glPushMatrix();
                GL11.glTranslatef((float)0.025f, (float)0.0f, (float)-0.4f);
                GL11.glEnable((int)32826);
                GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glRotatef((float)0.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                model.field_78123_h.func_78785_a(0.0625f);
                GL11.glPopMatrix();
            }
            if (frame.frameParts.containsKey((Object)EnumAnimationPart.LEFT_LEG)) {
                GL11.glPushMatrix();
                GL11.glTranslatef((float)-0.025f, (float)0.0f, (float)-0.4f);
                GL11.glEnable((int)32826);
                GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glRotatef((float)0.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                model.field_78124_i.func_78785_a(0.0625f);
                GL11.glPopMatrix();
            }
        }
    }
}

