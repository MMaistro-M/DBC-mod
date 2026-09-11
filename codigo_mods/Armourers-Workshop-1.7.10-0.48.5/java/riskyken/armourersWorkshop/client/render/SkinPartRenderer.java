/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package riskyken.armourersWorkshop.client.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import riskyken.armourersWorkshop.client.handler.ModClientFMLEventHandler;
import riskyken.armourersWorkshop.client.model.SkinModel;
import riskyken.armourersWorkshop.client.model.bake.ColouredFace;
import riskyken.armourersWorkshop.client.render.IRenderBuffer;
import riskyken.armourersWorkshop.client.render.ModRenderHelper;
import riskyken.armourersWorkshop.client.render.RenderBridge;
import riskyken.armourersWorkshop.client.render.SkinPartRenderData;
import riskyken.armourersWorkshop.client.skin.ClientSkinPartData;
import riskyken.armourersWorkshop.common.config.ConfigHandlerClient;
import riskyken.armourersWorkshop.proxies.ClientProxy;

@SideOnly(value=Side.CLIENT)
public class SkinPartRenderer
extends ModelBase {
    private static final ResourceLocation texture = new ResourceLocation("armourersWorkshop".toLowerCase(), "textures/armour/cube.png");
    public static final SkinPartRenderer INSTANCE = new SkinPartRenderer();
    private final Minecraft mc = Minecraft.func_71410_x();

    public void renderPart(SkinPartRenderData renderData) {
        ++ModClientFMLEventHandler.skinRendersThisTick;
        ClientSkinPartData cspd = renderData.getSkinPart().getClientSkinPartData();
        SkinModel skinModel = cspd.getModelForDye(renderData);
        boolean multipassSkinRendering = ClientProxy.useMultipassSkinRendering();
        for (int i = 0; i < skinModel.displayList.length; ++i) {
            if (!skinModel.haveList[i] || skinModel.displayList[i].isCompiled()) continue;
            skinModel.displayList[i].begin();
            this.renderVertexList(cspd.vertexLists[i], renderData, cspd);
            skinModel.displayList[i].end();
            skinModel.setLoaded();
        }
        if (ClientProxy.useSafeTextureRender()) {
            this.mc.field_71446_o.func_110577_a(texture);
        } else {
            GL11.glDisable((int)3553);
        }
        int startIndex = 0;
        int endIndex = 0;
        int loadingLod = skinModel.getLoadingLod();
        int lod = renderData.getLod();
        if (!renderData.isDoLodLoading()) {
            loadingLod = 0;
        }
        if (loadingLod > lod) {
            lod = loadingLod;
        }
        if (lod != 0) {
            startIndex = multipassSkinRendering ? lod * 4 : lod * 2;
        }
        endIndex = multipassSkinRendering ? startIndex + 4 : startIndex + 2;
        int listCount = skinModel.displayList.length;
        for (int i = startIndex; i < endIndex; ++i) {
            if (!(i >= startIndex & i < endIndex)) continue;
            boolean glowing = false;
            if (i % 2 == 1) {
                glowing = true;
            }
            if (!(i >= 0 & i < skinModel.displayList.length) || !skinModel.haveList[i] || !skinModel.displayList[i].isCompiled()) continue;
            if (glowing) {
                GL11.glPushAttrib((int)8192);
                GL11.glDisable((int)2896);
                ModRenderHelper.disableLighting();
            }
            if (ConfigHandlerClient.wireframeRender) {
                GL11.glPolygonMode((int)1032, (int)6913);
                GL11.glLineWidth((float)1.0f);
            }
            skinModel.displayList[i].render();
            if (ConfigHandlerClient.wireframeRender) {
                GL11.glPolygonMode((int)1032, (int)6914);
            }
            if (!glowing) continue;
            ModRenderHelper.enableLighting();
            GL11.glEnable((int)2896);
            GL11.glPopAttrib();
        }
        if (!ClientProxy.useSafeTextureRender()) {
            GL11.glEnable((int)3553);
        }
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
    }

    private void renderVertexList(ArrayList<ColouredFace> vertexList, SkinPartRenderData renderData, ClientSkinPartData cspd) {
        new RenderBridge();
        IRenderBuffer renderBuffer = RenderBridge.INSTANCE;
        renderBuffer.startDrawingQuads();
        for (int i = 0; i < vertexList.size(); ++i) {
            ColouredFace cVert = vertexList.get(i);
            cVert.renderVertex(renderBuffer, renderData, cspd, ClientProxy.useSafeTextureRender());
        }
        renderBuffer.draw();
    }
}

