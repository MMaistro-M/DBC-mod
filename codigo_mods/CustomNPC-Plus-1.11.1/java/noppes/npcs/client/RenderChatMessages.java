/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.util.IChatComponent
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client;

import java.util.Map;
import java.util.TreeMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.IChatComponent;
import noppes.npcs.IChatMessages;
import noppes.npcs.client.ClientProxy;
import noppes.npcs.client.TextBlockClient;
import noppes.npcs.config.ConfigClient;
import noppes.npcs.entity.EntityNPCInterface;
import org.lwjgl.opengl.GL11;

public class RenderChatMessages
implements IChatMessages {
    private Map<Long, TextBlockClient> messages = new TreeMap<Long, TextBlockClient>();
    private int boxLength = 46;
    private float scale = 0.5f;
    private String lastMessage = "";
    private long lastMessageTime = 0L;

    @Override
    public void addMessage(String message, EntityNPCInterface npc) {
        if (!ConfigClient.EnableChatBubbles) {
            return;
        }
        long time = System.currentTimeMillis();
        if (message.equals(this.lastMessage) && this.lastMessageTime + 5000L > time) {
            return;
        }
        TreeMap<Long, TextBlockClient> messages = new TreeMap<Long, TextBlockClient>(this.messages);
        messages.put(time, new TextBlockClient(message, this.boxLength * 4, true, new Object[]{Minecraft.func_71410_x().field_71439_g, npc}));
        if (messages.size() > 3) {
            messages.remove(messages.keySet().iterator().next());
        }
        this.messages = messages;
        this.lastMessage = message;
        this.lastMessageTime = time;
    }

    @Override
    public void renderMessages(double par3, double par5, double par7, float scale, boolean inRange) {
        Map<Long, TextBlockClient> messages = this.getMessages();
        if (messages.isEmpty()) {
            return;
        }
        if (inRange) {
            this.render(par3, par5, par7, scale, false);
        }
        this.render(par3, par5, par7, scale, true);
    }

    public void render(double par3, double par5, double par7, float textscale, boolean depth) {
        FontRenderer font = Minecraft.func_71410_x().field_71466_p;
        float var13 = 1.6f;
        float var14 = 0.016666668f * var13;
        GL11.glPushAttrib((int)1048575);
        GL11.glPushMatrix();
        int size = 0;
        for (TextBlockClient block : this.messages.values()) {
            size += block.lines.size();
        }
        int fontHeight = ConfigClient.ChatBubblesFontType ? ClientProxy.Font.height() : font.field_78288_b;
        int textYSize = (int)((float)(size * fontHeight) * this.scale);
        GL11.glTranslatef((float)((float)par3 + 0.0f), (float)((float)par5 + (float)textYSize * textscale * var14), (float)((float)par7));
        GL11.glScalef((float)textscale, (float)textscale, (float)textscale);
        GL11.glNormal3f((float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)(-RenderManager.field_78727_a.field_78735_i), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)RenderManager.field_78727_a.field_78732_j, (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glScalef((float)(-var14), (float)(-var14), (float)var14);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        RenderHelper.func_74518_a();
        GL11.glDisable((int)3008);
        GL11.glDepthMask((boolean)true);
        GL11.glEnable((int)3042);
        GL11.glDepthFunc((int)515);
        if (depth) {
            GL11.glEnable((int)2929);
        } else {
            GL11.glDisable((int)2929);
        }
        int black = depth ? -16777216 : 0x55000000;
        int white = depth ? -1140850689 : 0x44FFFFFF;
        OpenGlHelper.func_148821_a((int)770, (int)771, (int)1, (int)0);
        GL11.glDisable((int)3553);
        GL11.glEnable((int)2884);
        this.drawRect(-this.boxLength - 2, -2, this.boxLength + 2, textYSize + 1, white, 0.11);
        this.drawRect(-this.boxLength - 1, -3, this.boxLength + 1, -2, black, 0.1);
        this.drawRect(-this.boxLength - 1, textYSize + 2, -1, textYSize + 1, black, 0.1);
        this.drawRect(3, textYSize + 2, this.boxLength + 1, textYSize + 1, black, 0.1);
        this.drawRect(-this.boxLength - 3, -1, -this.boxLength - 2, textYSize, black, 0.1);
        this.drawRect(this.boxLength + 3, -1, this.boxLength + 2, textYSize, black, 0.1);
        this.drawRect(-this.boxLength - 2, -2, -this.boxLength - 1, -1, black, 0.1);
        this.drawRect(this.boxLength + 2, -2, this.boxLength + 1, -1, black, 0.1);
        this.drawRect(-this.boxLength - 2, textYSize + 1, -this.boxLength - 1, textYSize, black, 0.1);
        this.drawRect(this.boxLength + 2, textYSize + 1, this.boxLength + 1, textYSize, black, 0.1);
        this.drawRect(0, textYSize + 1, 3, textYSize + 4, white, 0.11);
        this.drawRect(-1, textYSize + 4, 1, textYSize + 5, white, 0.11);
        this.drawRect(-1, textYSize + 1, 0, textYSize + 4, black, 0.1);
        this.drawRect(3, textYSize + 1, 4, textYSize + 3, black, 0.1);
        this.drawRect(2, textYSize + 3, 3, textYSize + 4, black, 0.1);
        this.drawRect(1, textYSize + 4, 2, textYSize + 5, black, 0.1);
        this.drawRect(-2, textYSize + 4, -1, textYSize + 5, black, 0.1);
        this.drawRect(-2, textYSize + 5, 1, textYSize + 6, black, 0.1);
        GL11.glEnable((int)3553);
        GL11.glDepthMask((boolean)true);
        GL11.glScalef((float)this.scale, (float)this.scale, (float)this.scale);
        int index = 0;
        for (TextBlockClient block : this.messages.values()) {
            for (IChatComponent chat : block.lines) {
                String message = chat.func_150254_d();
                if (ConfigClient.ChatBubblesFontType) {
                    ClientProxy.Font.drawString(message, -ClientProxy.Font.width(message) / 2, index * fontHeight, black);
                } else {
                    font.func_78276_b(message, -font.func_78256_a(message) / 2, index * fontHeight, black);
                }
                ++index;
            }
            GL11.glDisable((int)2884);
        }
        GL11.glEnable((int)2896);
        GL11.glDisable((int)3042);
        GL11.glEnable((int)2929);
        GL11.glEnable((int)3008);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glPopMatrix();
        RenderHelper.func_74519_b();
        GL11.glPopAttrib();
    }

    private void drawRect(int par0, int par1, int par2, int par3, int par4, double par5) {
        int j1;
        if (par0 < par2) {
            j1 = par0;
            par0 = par2;
            par2 = j1;
        }
        if (par1 < par3) {
            j1 = par1;
            par1 = par3;
            par3 = j1;
        }
        float f = (float)(par4 >> 24 & 0xFF) / 255.0f;
        float f1 = (float)(par4 >> 16 & 0xFF) / 255.0f;
        float f2 = (float)(par4 >> 8 & 0xFF) / 255.0f;
        float f3 = (float)(par4 & 0xFF) / 255.0f;
        Tessellator tessellator = Tessellator.field_78398_a;
        GL11.glColor4f((float)f1, (float)f2, (float)f3, (float)f);
        tessellator.func_78382_b();
        tessellator.func_78377_a((double)par0, (double)par3, par5);
        tessellator.func_78377_a((double)par2, (double)par3, par5);
        tessellator.func_78377_a((double)par2, (double)par1, par5);
        tessellator.func_78377_a((double)par0, (double)par1, par5);
        tessellator.func_78381_a();
    }

    private Map<Long, TextBlockClient> getMessages() {
        TreeMap<Long, TextBlockClient> messages = new TreeMap<Long, TextBlockClient>();
        long time = System.currentTimeMillis();
        for (long timestamp : this.messages.keySet()) {
            if (time > timestamp + 10000L) continue;
            TextBlockClient message = this.messages.get(timestamp);
            messages.put(timestamp, message);
        }
        this.messages = messages;
        return messages;
    }
}

