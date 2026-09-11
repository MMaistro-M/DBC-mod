/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.audio.ISound
 *  net.minecraft.client.audio.PositionedSoundRecord
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package JinRyuu.JRMCore.client.minigame;

import JinRyuu.JRMCore.JRMCoreClient;
import JinRyuu.JRMCore.JRMCoreGuiButtonsMG0;
import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.client.config.jrmc.JGConfigClientSettings;
import JinRyuu.JRMCore.client.minigame.MiniGame;
import JinRyuu.JRMCore.server.config.core.JGConfigMiniGameConcentration;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class MiniGameConcentration
extends MiniGame {
    public static final int XYPOS = 0;
    public static final int TYPE = 1;
    public static final int VALUE = 2;
    public static final int REMOVED_TIMER = 3;
    public static final int IS_REMOVED = 4;
    public static final int ENTITY_BAD = 0;
    public static final int ENTITY_GOOD = 1;
    public static final int ENTITY_VIRUS = 2;
    public HashMap<Integer, Object[]> energyBalls = new HashMap();
    public ArrayList<Integer> removeList = new ArrayList();
    public int comboCounter = 0;
    public String temporaryMessage = "";
    public int tempMessageVisibility = 0;
    private Instant colorAnimation = Instant.now();
    private Instant textureAnimation = Instant.now();
    private Instant movementTimer = Instant.now();
    private float animationTimer = 0.0f;
    private int animationID = 0;
    private Instant comboTimer;
    private Instant TPusedLimitCheckTimer;
    private boolean update = false;

    @Override
    public void gameReset() {
        this.lvl = 0;
        this.score = 0;
        this.lives = 3;
        this.energyBalls.clear();
        this.state = 1;
        this.comboTimer = null;
        this.TPusedLimitCheckTimer = null;
    }

    @Override
    public void generateStart() {
        this.generateEntities(0);
        this.state = 2;
    }

    public void generateNextLevel() {
        this.energyBalls.clear();
        this.generateEntities(this.lvl);
    }

    @Override
    public void chooseReward() {
    }

    @Override
    public void stateManager(int guiLeft, int guiTop) {
        long timeElapsedCombo;
        if (this.comboTimer != null && (float)(timeElapsedCombo = Duration.between(this.comboTimer, Instant.now()).toMillis() / 1000L) >= JGConfigMiniGameConcentration.ComboTimer) {
            if (this.tempMessageVisibility > 0) {
                --this.tempMessageVisibility;
            }
            this.comboTimer = null;
        }
        if (this.tempMessageVisibility <= 0 && this.temporaryMessage.length() > 0) {
            this.temporaryMessage = "";
        }
        if (this.state != 0 && (this.TPusedLimitCheckTimer == null || Duration.between(this.TPusedLimitCheckTimer, Instant.now()).toMillis() / 1000L >= 10L)) {
            this.TPusedLimitCheckTimer = Instant.now();
            JRMCoreH.quad(10, 1, -1, 0);
        }
        if (JGConfigMiniGameConcentration.RandomMovementSpeed > 0 && this.update) {
            Random r = new Random();
            for (int id : this.energyBalls.keySet()) {
                int y;
                int timer;
                int randomMovementValue;
                Object[] object = this.energyBalls.get(id);
                int[] xypos = (int[])object[0];
                int x = xypos[0] + r.nextInt(1 + (randomMovementValue = (int)((float)JGConfigMiniGameConcentration.RandomMovementSpeed * ((timer = (int)(Duration.between(this.movementTimer, Instant.now()).toMillis() / 10L)) > 3 ? (float)timer / 3.0f : 1.0f))) * 2) - randomMovementValue;
                if (x < 5) {
                    x = 5;
                }
                if (x > 144) {
                    x = 144;
                }
                if ((y = xypos[1] + r.nextInt(1 + randomMovementValue * 2) - randomMovementValue) < 5) {
                    y = 5;
                }
                if (y > 144) {
                    y = 144;
                }
                this.energyBalls.put(id, new Object[]{new int[]{x, y}, object[1], object[2], object[3], object[4]});
            }
            this.update = false;
        }
        super.stateManager(guiLeft, guiTop);
    }

    @Override
    public void update(int guiLeft, int guiTop) {
        if (this.tempMessageVisibility <= 0) {
            this.comboCounter = 0;
            this.comboTimer = null;
        }
        for (int key : this.energyBalls.keySet()) {
            Object[] object = this.energyBalls.get(key);
            int[] xypos = (int[])object[0];
            this.entityUpdate(key, guiLeft + xypos[0] - 5, guiTop + xypos[1] - 5, (Integer)object[1], (Integer)object[2], (Instant)object[3], (Boolean)object[4]);
            if (!((Boolean)object[4]).booleanValue() || Duration.between((Instant)object[3], Instant.now()).toMillis() <= 200L) continue;
            this.removeList.add(key);
        }
        for (int remove : this.removeList) {
            this.energyBalls.remove(remove);
        }
        this.removeList.clear();
        boolean levelup = true;
        for (int key : this.energyBalls.keySet()) {
            Object[] object = this.energyBalls.get(key);
            if ((Integer)object[1] != 1) continue;
            levelup = false;
        }
        if (levelup) {
            ++this.lvl;
            this.generateNextLevel();
        }
        if (this.lives <= 0 || this.score < 0) {
            this.state = 3;
        }
    }

    public void generateEntities(int lvl) {
        this.colorAnimation = Instant.now();
        this.textureAnimation = Instant.now();
        if (JGConfigMiniGameConcentration.RandomMovementSpeed > 0) {
            this.movementTimer = Instant.now();
        }
        this.animationTimer = 0.0f;
        this.animationID = 0;
        Random r = new Random();
        for (int i = 0; i < 10 + lvl; ++i) {
            this.energyBalls.put(r.nextInt(1000000), new Object[]{new int[]{5 + r.nextInt(140), 5 + r.nextInt(140)}, r.nextInt(3), r.nextInt(10), null, false});
        }
    }

    public void entityUpdate(int id, int ex, int ey, int type, int value, Instant timer, boolean removed) {
        long timeElapsed3;
        float cos = 1.0f;
        if (JGConfigClientSettings.concentrationAnimatedColorOn) {
            long timeElapsed = Duration.between(this.colorAnimation, Instant.now()).toMillis();
            cos = MathHelper.func_76134_b((float)((float)timeElapsed * 0.001f)) * 0.2f;
            if (cos < 0.0f) {
                cos *= -1.0f;
            }
            cos += 0.8f;
        }
        if (JGConfigClientSettings.concentrationAnimatedTexturesOn) {
            if (this.textureAnimation == null) {
                this.textureAnimation = Instant.now();
            } else {
                long timeElapsed2 = Duration.between(this.textureAnimation, Instant.now()).toMillis() / 10L;
                if (timeElapsed2 > 10L) {
                    ++this.animationID;
                    this.textureAnimation = Instant.now();
                    if (this.animationID > 5) {
                        this.animationID = 0;
                    }
                }
            }
        } else if (this.textureAnimation != null) {
            this.textureAnimation = null;
            this.animationID = 0;
        }
        if (JGConfigMiniGameConcentration.RandomMovementSpeed > 0 && (timeElapsed3 = Duration.between(this.movementTimer, Instant.now()).toMillis() / 10L) > 3L) {
            this.update = true;
            this.movementTimer = Instant.now();
        }
        if (JRMCoreGuiButtonsMG0.clicked && this.getGUIInstance().x - 10 < ex && this.getGUIInstance().x > ex && this.getGUIInstance().y - 10 < ey && this.getGUIInstance().y > ey && !removed) {
            switch (type) {
                case 0: {
                    this.score -= value * (this.comboCounter + 1) * (this.lvl + 1);
                    break;
                }
                case 1: {
                    ++this.comboCounter;
                    if (this.comboCounter > 0) {
                        this.tempMessageVisibility = 1;
                        this.comboTimer = Instant.now();
                        if (this.comboCounter > 1) {
                            this.temporaryMessage = this.comboCounter + "x combo";
                        }
                    }
                    this.score += value * this.comboCounter;
                    break;
                }
                case 2: {
                    this.score -= value * (this.comboCounter + 1) * (this.lvl + 1);
                    --this.lives;
                    break;
                }
            }
            float rand = JRMCoreClient.mc.field_71439_g.field_70170_p.field_73012_v.nextFloat() * 0.1f + 0.9f;
            if (type != 1) {
                JRMCoreClient.mc.func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_147674_a((ResourceLocation)new ResourceLocation("jinryuumodscore:MINIGAME.blast_bad"), (float)(rand /= (float)(type == 0 ? 2 : (type == 2 ? 3 : 1)))));
            }
            JRMCoreClient.mc.func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_147674_a((ResourceLocation)new ResourceLocation("jinryuumodscore:MINIGAME.blast"), (float)rand));
            Object[] object = this.energyBalls.get(id);
            this.energyBalls.put(id, new Object[]{object[0], object[1], object[2], Instant.now(), true});
        }
        float scale = 1.0f;
        if (removed) {
            float timerScale = (float)Duration.between(timer, Instant.now()).toMillis() / 200.0f;
            if (timerScale > 1.0f) {
                timerScale = 1.0f;
            }
            if (timerScale < 0.0f) {
                timerScale = 0.0f;
            }
            scale = 1.0f + timerScale;
        }
        GL11.glPushMatrix();
        int x = ex - 2;
        int y = ey - 2;
        GL11.glTranslatef((float)x, (float)y, (float)0.0f);
        if (scale != 1.0f) {
            GL11.glTranslatef((float)((-scale * 14.0f + 14.0f) / 2.0f), (float)((-scale * 14.0f + 14.0f) / 2.0f), (float)0.0f);
        }
        GL11.glScalef((float)scale, (float)scale, (float)scale);
        if (JGConfigClientSettings.concentrationAnimatedColorOn) {
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)(cos * (1.0f - (scale - 1.0f))));
        } else {
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)(1.0f - (scale - 1.0f)));
        }
        this.getGUIInstance().func_73729_b(0, 0, 242, 214 + type * 14, 14, 14);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslatef((float)(x + 2), (float)(y + 2), (float)0.0f);
        if (scale != 1.0f) {
            GL11.glTranslatef((float)((-scale * 10.0f + 10.0f) / 2.0f), (float)((-scale * 10.0f + 10.0f) / 2.0f), (float)0.0f);
        }
        GL11.glScalef((float)scale, (float)scale, (float)scale);
        if (JGConfigClientSettings.concentrationAnimatedColorOn) {
            GL11.glColor4f((float)cos, (float)cos, (float)cos, (float)(1.0f - (scale - 1.0f)));
        } else {
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)(1.0f - (scale - 1.0f)));
        }
        this.getGUIInstance().func_73729_b(0, 0, this.animationID * 10, 226 + type * 10, 10, 10);
        GL11.glPopMatrix();
    }
}

