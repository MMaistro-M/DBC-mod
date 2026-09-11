/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.item.ItemStack
 *  net.minecraftforge.common.util.ForgeDirection
 *  org.lwjgl.input.Mouse
 *  org.lwjgl.opengl.GL11
 */
package riskyken.armourersWorkshop.client.gui.miniarmourer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Color;
import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import riskyken.armourersWorkshop.api.common.IRectangle3D;
import riskyken.armourersWorkshop.api.common.skin.cubes.ICubeColour;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinPartType;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinType;
import riskyken.armourersWorkshop.client.gui.miniarmourer.GuiMiniArmourerHelper;
import riskyken.armourersWorkshop.client.model.bake.FaceRenderer;
import riskyken.armourersWorkshop.client.render.IRenderBuffer;
import riskyken.armourersWorkshop.client.render.ModRenderHelper;
import riskyken.armourersWorkshop.client.render.RenderBridge;
import riskyken.armourersWorkshop.client.render.SkinPartRenderData;
import riskyken.armourersWorkshop.client.render.SkinPartRenderer;
import riskyken.armourersWorkshop.client.skin.cache.ClientSkinCache;
import riskyken.armourersWorkshop.common.data.MiniCube;
import riskyken.armourersWorkshop.common.skin.cubes.CubeColour;
import riskyken.armourersWorkshop.common.skin.cubes.CubeRegistry;
import riskyken.armourersWorkshop.common.skin.data.Skin;
import riskyken.armourersWorkshop.common.skin.data.SkinPart;
import riskyken.armourersWorkshop.common.skin.data.SkinPointer;
import riskyken.armourersWorkshop.common.tileentities.TileEntityMiniArmourer;
import riskyken.armourersWorkshop.utils.SkinNBTHelper;

@SideOnly(value=Side.CLIENT)
public class GuiMiniArmourerBuildingModel {
    private final Minecraft mc;
    private final GuiScreen parent;
    private TileEntityMiniArmourer tileEntity;
    private ArrayList<MiniCube> cubes;
    ArrayList<MiniCube> renderCubes = new ArrayList();
    private int mouseLeftDownId = -1;
    private int mouseRightDownId = -1;
    public float zoom = 150.0f;
    private float rotation = 0.0f;
    private float pitch = 0.0f;
    private boolean mouseLeftIsDown = false;
    private boolean mouseRightIsDown = false;
    private boolean mouseCenterIsDown = false;
    private int mouseRightDownPosX = 0;
    private int mouseRightDownPosY = 0;
    private int lastMousePosX = 0;
    private int lastMousePosY = 0;
    private int fakeCubeRenders = 0;
    private int hoverCubeId = 0;
    public ISkinType currentSkinType;
    public ISkinPartType currentSkinPartType;
    public ItemStack stack;
    public SkinPointer skinPointer;

    public GuiMiniArmourerBuildingModel(GuiScreen parent, Minecraft mc, TileEntityMiniArmourer tileEntity) {
        this.parent = parent;
        this.mc = mc;
        this.tileEntity = tileEntity;
        this.rotation = 45.0f;
        this.pitch = 45.0f;
        this.cubes = new ArrayList();
    }

    public void drawScreen(int mouseX, int mouseY) {
        if (this.stack != null) {
            this.skinPointer = SkinNBTHelper.getSkinPointerFromStack(this.stack);
        }
        this.renderFakeCubes(mouseX, mouseY);
        GL11.glClear((int)16640);
        GuiScreen.func_73734_a((int)0, (int)0, (int)this.parent.field_146294_l, (int)this.parent.field_146295_m, (int)-16777216);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.renderModels(mouseX, mouseY);
        this.lastMousePosX = mouseX;
        this.lastMousePosY = mouseY;
    }

    private void renderFakeCubes(int mouseX, int mouseY) {
        int cubeFace;
        int cubeId;
        ArrayList<SkinPart> skinParts = this.tileEntity.getSkinParts();
        for (int i = 0; i < skinParts.size(); ++i) {
        }
        float scale = 0.0625f;
        GL11.glPushMatrix();
        GL11.glTranslatef((float)(this.parent.field_146294_l / 2), (float)(this.parent.field_146295_m / 2), (float)500.0f);
        GL11.glScalef((float)(-this.zoom), (float)this.zoom, (float)this.zoom);
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)this.pitch, (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glRotatef((float)this.rotation, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glTranslated((double)0.0, (double)(1.0f * scale), (double)0.0);
        GL11.glScalef((float)-1.0f, (float)-1.0f, (float)1.0f);
        this.drawBuildingCubes(true);
        Color c = GuiMiniArmourerHelper.getColourAtPos(Mouse.getX(), Mouse.getY());
        this.hoverCubeId = GuiMiniArmourerHelper.getIdFromColour(c);
        if (Mouse.isButtonDown((int)0)) {
            if (!this.mouseLeftIsDown) {
                this.mouseLeftIsDown = true;
                this.mouseLeftDownId = this.hoverCubeId;
            }
        } else if (this.mouseLeftIsDown) {
            this.mouseLeftIsDown = false;
            if (this.mouseLeftDownId != 0 & this.hoverCubeId == this.mouseLeftDownId) {
                cubeId = (int)Math.ceil((double)this.mouseLeftDownId / 6.0);
                cubeFace = cubeId * 6 - this.mouseLeftDownId;
                this.cubeClicked(cubeId, cubeFace, 0);
                this.mouseLeftDownId = 0;
            }
        }
        if (Mouse.isButtonDown((int)1)) {
            if (!this.mouseRightIsDown) {
                this.mouseRightIsDown = true;
                this.mouseRightDownPosX = mouseX;
                this.mouseRightDownPosY = mouseY;
                this.mouseRightDownId = this.hoverCubeId;
            } else {
                this.rotation += (float)(this.lastMousePosX - mouseX);
                this.pitch -= (float)(this.lastMousePosY - mouseY);
            }
        } else if (this.mouseRightIsDown) {
            this.mouseRightIsDown = false;
            if (this.mouseRightDownId != 0 & this.hoverCubeId == this.mouseRightDownId) {
                cubeId = (int)Math.ceil((double)this.mouseRightDownId / 6.0);
                cubeFace = cubeId * 6 - this.mouseRightDownId;
                this.cubeClicked(cubeId, cubeFace, 1);
                this.mouseRightDownId = 0;
            }
        }
        GL11.glPopMatrix();
    }

    private void renderModels(int mouseX, int mouseY) {
        Skin skin;
        float scale = 0.0625f;
        GL11.glPushMatrix();
        GL11.glTranslatef((float)(this.parent.field_146294_l / 2), (float)(this.parent.field_146295_m / 2), (float)500.0f);
        GL11.glScalef((float)(-this.zoom), (float)this.zoom, (float)this.zoom);
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)this.pitch, (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glRotatef((float)this.rotation, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glTranslated((double)0.0, (double)(1.0f * scale), (double)0.0);
        GL11.glScalef((float)-1.0f, (float)-1.0f, (float)1.0f);
        if (this.hoverCubeId != 0 && this.renderCubes != null) {
            int cubeId = (int)Math.ceil((double)this.hoverCubeId / 6.0);
            int cubeFace = cubeId * 6 - this.hoverCubeId;
            if (cubeId - 1 < this.renderCubes.size() & cubeId - 1 >= 0) {
                MiniCube tarCube = this.renderCubes.get(cubeId - 1);
                MiniCube newCube = new MiniCube(CubeRegistry.INSTANCE.getCubeFormId((byte)0));
                ForgeDirection dir = GuiMiniArmourerHelper.getDirectionForCubeFace(cubeFace);
                newCube.setX((byte)(tarCube.getX() + dir.offsetX));
                newCube.setY((byte)(tarCube.getY() + dir.offsetY));
                newCube.setZ((byte)(tarCube.getZ() + dir.offsetZ));
                GL11.glEnable((int)3042);
                GL11.glBlendFunc((int)770, (int)771);
                GL11.glDisable((int)3553);
                IRenderBuffer buff = RenderBridge.INSTANCE;
                buff.startDrawingQuads();
                this.renderArmourBlock((byte)newCube.getX(), (byte)newCube.getY(), (byte)newCube.getZ(), newCube.getColour(), scale, true);
                buff.draw();
                GL11.glEnable((int)3553);
                GL11.glDisable((int)3042);
            }
        }
        this.drawBuildingCubes(false);
        GL11.glScalef((float)-1.0f, (float)-1.0f, (float)1.0f);
        RenderHelper.func_74519_b();
        this.mc.field_71446_o.func_110577_a(this.mc.field_71439_g.func_110306_p());
        if (this.skinPointer != null && (skin = ClientSkinCache.INSTANCE.getSkin(this.skinPointer)) != null) {
            for (int i = 0; i < skin.getParts().size(); ++i) {
                SkinPart part = skin.getParts().get(i);
                if (part.getPartType() != this.currentSkinPartType) continue;
                SkinPartRenderData renderData = new SkinPartRenderData(part, 0.0625f, this.skinPointer.getSkinDye(), null, 0.0, true, false, false, null);
                SkinPartRenderer.INSTANCE.renderPart(renderData);
            }
        }
        if (this.currentSkinPartType != null) {
            GL11.glTranslated((double)0.0, (double)((float)(-this.currentSkinPartType.getBuildingSpace().getY()) * scale), (double)0.0);
        }
        GL11.glPopMatrix();
    }

    private void renderArmourBlock(byte x, byte y, byte z, ICubeColour colour, float scale, boolean b) {
        for (int i = 0; i < 6; ++i) {
            FaceRenderer.renderFace(x, y, z, colour.getRed(i), colour.getGreen(i), colour.getBlue(i), (byte)-1, (byte)i, false, (byte)1);
        }
    }

    private void cubeClicked(int cubeId, int cubeFace, int button) {
        if (this.renderCubes != null && cubeId - 1 < this.renderCubes.size() & cubeId - 1 >= 0) {
            MiniCube tarCube = this.renderCubes.get(cubeId - 1);
            if (button == 0) {
                MiniCube newCube = new MiniCube(CubeRegistry.INSTANCE.getCubeFormId((byte)0));
                newCube.setColour(-1);
                ForgeDirection dir = GuiMiniArmourerHelper.getDirectionForCubeFace(cubeFace);
                newCube.setX((byte)(tarCube.getX() + dir.offsetX));
                newCube.setY((byte)(tarCube.getY() + dir.offsetY));
                newCube.setZ((byte)(tarCube.getZ() + dir.offsetZ));
                this.cubes.add(newCube);
            }
            if (button == 1) {
                for (int i = 0; i < this.cubes.size(); ++i) {
                    MiniCube mc = this.cubes.get(i);
                    if (mc.getX() != tarCube.getX() || mc.getY() != tarCube.getY() || mc.getZ() != tarCube.getZ()) continue;
                    this.cubes.remove(i);
                    return;
                }
            }
        }
    }

    private void drawBuildingCubes(boolean fake) {
        if (this.cubes == null) {
            // empty if block
        }
        this.renderCubes.clear();
        this.fakeCubeRenders = 0;
        GL11.glDisable((int)3553);
        if (fake) {
            GL11.glDisable((int)2896);
            IRectangle3D guideSpace = this.currentSkinPartType.getGuideSpace();
            for (int ix = 0; ix < guideSpace.getWidth(); ++ix) {
                for (int iy = 0; iy < guideSpace.getHeight(); ++iy) {
                    for (int iz = 0; iz < guideSpace.getDepth(); ++iz) {
                        byte x = (byte)(ix + guideSpace.getX());
                        byte y = (byte)(iy + guideSpace.getY());
                        byte z = (byte)(iz + guideSpace.getZ());
                        MiniCube cube = new MiniCube(CubeRegistry.INSTANCE.getCubeFormId((byte)0));
                        cube.setX(x);
                        cube.setY(y);
                        cube.setZ(z);
                        this.renderCubes.add(cube);
                    }
                }
            }
            this.fakeCubeRenders = this.renderCubes.size();
        }
        float scale = 0.0625f;
        int colourId = 1;
        this.renderCubes.addAll(this.cubes);
        IRenderBuffer buff = RenderBridge.INSTANCE;
        buff.startDrawingQuads();
        for (int i = 0; i < this.renderCubes.size(); ++i) {
            MiniCube cube = this.renderCubes.get(i);
            if (cube != null) {
                if (cube.isGlowing() & !fake) {
                    GL11.glDisable((int)2896);
                    ModRenderHelper.disableLighting();
                }
                ICubeColour colour = new CubeColour();
                if (fake) {
                    colour.setColour(GuiMiniArmourerHelper.getColourFromId(colourId).getRGB(), 0);
                    colour.setColour(GuiMiniArmourerHelper.getColourFromId(colourId + 1).getRGB(), 1);
                    colour.setColour(GuiMiniArmourerHelper.getColourFromId(colourId + 2).getRGB(), 2);
                    colour.setColour(GuiMiniArmourerHelper.getColourFromId(colourId + 3).getRGB(), 3);
                    colour.setColour(GuiMiniArmourerHelper.getColourFromId(colourId + 4).getRGB(), 4);
                    colour.setColour(GuiMiniArmourerHelper.getColourFromId(colourId + 5).getRGB(), 5);
                } else {
                    colour = cube.getCubeColour();
                }
                this.renderArmourBlock((byte)cube.getX(), (byte)cube.getY(), (byte)cube.getZ(), colour, scale, false);
                if (cube.isGlowing() & !fake) {
                    ModRenderHelper.enableLighting();
                    GL11.glEnable((int)2896);
                }
            }
            colourId += 6;
        }
        buff.draw();
        if (fake) {
            GL11.glEnable((int)2896);
        }
        GL11.glEnable((int)3553);
    }
}

