/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.config.GuiButtonExt
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.audio.ISound
 *  net.minecraft.client.audio.PositionedSoundRecord
 *  net.minecraft.client.audio.SoundHandler
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package riskyken.armourersWorkshop.client.gui.controls;

import cpw.mods.fml.client.config.GuiButtonExt;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import riskyken.armourersWorkshop.common.lib.LibSounds;

@SideOnly(value=Side.CLIENT)
public class GuiBookButton
extends GuiButtonExt {
    private static final ResourceLocation buttonSound = new ResourceLocation("armourersWorkshop".toLowerCase() + ":pageTurn");
    private final ResourceLocation texture;
    private final int srcX;
    private final int srcY;

    public GuiBookButton(int id, int xPos, int yPos, int srcX, int srcY, ResourceLocation texture) {
        super(id, xPos, yPos, 18, 10, "awdaw");
        this.srcX = srcX;
        this.srcY = srcY;
        this.texture = texture;
    }

    public void func_146112_a(Minecraft mc, int mouseX, int mouseY) {
        if (!this.field_146125_m) {
            return;
        }
        this.field_146123_n = mouseX >= this.field_146128_h && mouseY >= this.field_146129_i && mouseX < this.field_146128_h + this.field_146120_f && mouseY < this.field_146129_i + this.field_146121_g;
        int hoverState = this.func_146114_a(this.field_146123_n);
        int xOffset = 0;
        if (hoverState == 2) {
            xOffset = 23;
        }
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        mc.field_71446_o.func_110577_a(this.texture);
        this.func_73729_b(this.field_146128_h, this.field_146129_i, this.srcX + xOffset, this.srcY, this.field_146120_f, this.field_146121_g);
    }

    public void func_146113_a(SoundHandler soundHandler) {
        soundHandler.func_147682_a((ISound)PositionedSoundRecord.func_147674_a((ResourceLocation)new ResourceLocation(LibSounds.PAGE_TURN), (float)1.0f));
    }
}

