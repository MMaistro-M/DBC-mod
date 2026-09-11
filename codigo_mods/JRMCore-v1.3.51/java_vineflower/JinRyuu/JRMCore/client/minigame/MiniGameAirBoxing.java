package JinRyuu.JRMCore.client.minigame;

import JinRyuu.JRMCore.JRMCoreClient;
import JinRyuu.JRMCore.JRMCoreGuiScreen;
import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.client.config.jrmc.JGConfigClientSettings;
import JinRyuu.JRMCore.server.config.core.JGConfigMiniGameAirBoxing;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class MiniGameAirBoxing extends MiniGame {
   public final boolean DEBUG = false;
   public static final int KEY_FORWARD = 0;
   public static final int KEY_LEFT = 1;
   public static final int KEY_BACK = 2;
   public static final int KEY_RIGHT = 3;
   public static final int ANIMATION_HURT = 5;
   public static final int ANIMATION_VICTORY = 6;
   public static final int ANIMATION_DEFEAT = 7;
   private final int KEY_WIDTH = 17;
   private final int KEY_HEIGHT = 21;
   private ArrayList<MiniGameAirBoxing.KeyData> keys = new ArrayList<>();
   private boolean wasKeyPressed;
   private boolean wasKeyChecked;
   private int lastKeyPressed;
   public int comboCounter = 0;
   public Instant lastUpdate;
   public long lastUpdateMillis = 0L;
   public long spawnTimer = 0L;
   public float gameModelAge;
   public boolean gameModelAnimationChanged;
   public int gameModelAnimationID;
   private final byte RESULT_SUCCESS = 0;
   private final byte RESULT_FAIL = 1;
   private byte lastKeyResult;
   private Instant lastKeyResultTime;
   public ArrayList<Integer> airboxingResults = new ArrayList<>();
   public ArrayList<Instant> airboxingResultsTime = new ArrayList<>();
   public boolean endAnimation;
   public boolean endAnimationStart;
   private Instant endTimer;
   private Instant TPusedLimitCheckTimer;
   private int endTimerMillis = 0;
   private Instant modeTimer;
   public int currentMode = 0;
   private int modeLength = 0;
   private static final float[] mR = new float[]{1.0F, 0.6F, 0.6F, 1.0F};
   private static final float[] mG = new float[]{0.6F, 0.6F, 1.0F, 1.0F};
   private static final float[] mB = new float[]{0.6F, 1.0F, 0.6F, 1.0F};
   private float r = 1.0F;
   private float g = 1.0F;
   private float b = 1.0F;

   @Override
   public void gameReset() {
      this.keys.clear();
      this.airboxingResults.clear();
      this.airboxingResultsTime.clear();
      this.lvl = 0;
      this.score = 0;
      this.comboCounter = 0;
      this.wasKeyPressed = false;
      this.wasKeyChecked = false;
      this.lastKeyPressed = -1;
      this.state = 1;
      this.lastUpdate = Instant.now();
      this.lastUpdateMillis = 0L;
      this.spawnTimer = 0L;
      this.playAnimation(0);
      this.lastKeyResult = -1;
      this.lastKeyResultTime = null;
      this.endAnimation = true;
      this.endAnimationStart = false;
      this.endTimer = null;
      this.endTimerMillis = 0;
      this.TPusedLimitCheckTimer = null;
      this.modeTimer = Instant.now();
      this.currentMode = (int)(Math.random() * 4.0);
      this.modeLength = (int)(Math.random() * 5.0) + 5;
      this.lives = JGConfigMiniGameAirBoxing.StartLife;
   }

   @Override
   public void generateStart() {
      if (Duration.between(this.lastUpdate, Instant.now()).toMillis() / 1000L >= 1L) {
         this.lastUpdate = Instant.now();
         this.state = 2;
      }
   }

   @Override
   public void chooseReward() {
   }

   @Override
   public void stateManager(int guiLeft, int guiTop) {
      if (this.state != 0 && (this.TPusedLimitCheckTimer == null || Duration.between(this.TPusedLimitCheckTimer, Instant.now()).toMillis() / 1000L >= 10L)) {
         this.TPusedLimitCheckTimer = Instant.now();
         JRMCoreH.quad(10, 2, -1, 0);
      }

      super.stateManager(guiLeft, guiTop);
   }

   @Override
   public void update(int guiLeft, int guiTop) {
      if (JGConfigClientSettings.airboxingAnimatedColorOn) {
         this.r = this.addDifference(this.r, mR[this.currentMode]);
         this.g = this.addDifference(this.g, mG[this.currentMode]);
         this.b = this.addDifference(this.b, mB[this.currentMode]);
      } else {
         this.r = 1.0F;
         this.g = 1.0F;
         this.b = 1.0F;
      }

      this.updateTimers();
      if (this.lives > 0 && !this.endAnimationStart) {
         String length = this.score + "";
         this.lvl = length.length();
         if (this.lvl > 100) {
            this.lvl = 100;
         }

         this.spawnKey();
         this.isKeyPressed();

         for (int i = this.keys.size() - 1; i >= 0; i--) {
            float speed = JGConfigMiniGameAirBoxing.KeySpeed[this.keys.get(i).mode];
            MiniGameAirBoxing.KeyData xProgress = this.keys.get(i);
            xProgress.xProgress = xProgress.xProgress - speed * (float)this.lastUpdateMillis;
            float xProgressx = this.keys.get(i).xProgress;
            if (xProgressx <= -134.0F) {
               if (!this.keys.get(i).catched) {
                  this.lastKeyResultTime = null;
                  if (!this.keys.get(i).missed) {
                     this.lives = this.lives - JGConfigMiniGameAirBoxing.KeyLifeTaken[this.keys.get(i).mode];
                     this.lastKeyResult = 1;
                     this.comboCounter = 0;
                     this.playAnimation(5);
                     this.airboxingResults.add(1);
                     this.airboxingResultsTime.add(Instant.now());
                  }
               }

               this.keys.remove(i);
            }
         }

         int MIN = -105;
         int MAX = -122;
         if (!this.wasKeyChecked && this.keys.size() > 0 && this.wasKeyPressed) {
            for (MiniGameAirBoxing.KeyData key : this.keys) {
               if (!key.catched) {
                  if (this.lastKeyPressed == key.id && key.xProgress <= -105.0F && key.xProgress > -122.0F) {
                     if (this.lastKeyPressed == key.id && key.xProgress <= -105.0F && key.xProgress > -122.0F && !key.missed) {
                        this.comboCounter++;
                        this.score = this.score + (this.comboCounter + 1) * (this.lvl + 1);
                        key.catched = true;
                        this.playAnimation(key.id + 1);
                        this.lastKeyResult = 0;
                        this.lastKeyResultTime = null;
                        this.airboxingResults.add(0);
                        this.airboxingResultsTime.add(Instant.now());
                        break;
                     }
                  } else {
                     key.missed = true;
                     this.lives = this.lives - JGConfigMiniGameAirBoxing.KeyLifeTaken[key.mode];
                     this.comboCounter = 0;
                     this.playAnimation(5);
                     this.lastKeyResult = 1;
                     this.lastKeyResultTime = null;
                     this.airboxingResults.add(1);
                     this.airboxingResultsTime.add(Instant.now());
                  }
               }
            }

            this.wasKeyChecked = true;
         }

         this.draw(guiLeft, guiTop);
      } else {
         if (this.endAnimation) {
            if (this.endTimer != null) {
               this.endTimerMillis = (int)(this.endTimerMillis + Duration.between(this.endTimer, Instant.now()).toMillis());
               if (this.endTimerMillis >= 1000) {
                  this.playAnimation(this.lives <= 0 ? 7 : 6);
                  this.endAnimation = false;
                  this.state = 3;
                  this.endAnimationStart = false;
               }
            }

            this.endTimer = Instant.now();
         }
      }
   }

   private void updateTimers() {
      this.lastUpdateMillis = Duration.between(this.lastUpdate, Instant.now()).toMillis();
      this.lastUpdate = Instant.now();
      this.spawnTimer = this.spawnTimer + this.lastUpdateMillis;
      if (Duration.between(this.modeTimer, Instant.now()).toMillis() / 1000L > this.modeLength) {
         int lastMode = this.currentMode;

         for (byte maxWhile = 0; this.currentMode == lastMode && maxWhile < 100; maxWhile++) {
            this.currentMode = (int)(Math.random() * 4.0);
            this.modeLength = (int)(Math.random() * 5.0) + 5;
         }

         this.modeTimer = Instant.now();
      }
   }

   private void spawnKey() {
      if ((float)this.spawnTimer / 1000.0F >= JGConfigMiniGameAirBoxing.KeySpawnSpeed[this.currentMode]) {
         this.spawnTimer = 0L;
         int id = (int)(Math.random() * JGConfigMiniGameAirBoxing.KeyTypeIDs[this.currentMode].length);
         int keyID = JGConfigMiniGameAirBoxing.KeyTypeIDs[this.currentMode][id];
         this.keys.add(new MiniGameAirBoxing.KeyData(this.currentMode, keyID, 0.0F));
      }
   }

   private float addDifference(float n, float n2) {
      if (n != n2) {
         boolean nSmaller = n < n2;
         float diff = (nSmaller ? n - n2 : n2 - n) / 10.0F;
         diff *= (float)Duration.between(this.lastUpdate, Instant.now()).toMillis() / 3.0F;
         boolean negativeDiff = diff < 0.0F;
         if (negativeDiff) {
            diff *= -1.0F;
         }

         if (diff < 0.05) {
            diff = 0.05F;
         }

         if (negativeDiff) {
            diff *= -1.0F;
         }

         n += diff * (nSmaller ? -1 : 1);
         if (nSmaller ? n > n2 : n < n2) {
            n = n2;
         }
      }

      return n;
   }

   private void draw(int guiLeft, int guiTop) {
      for (int i = 0; i < 4; i++) {
         int[] xPos = new int[]{19, 0, 17, 34};
         int[] yPos = new int[]{-22, 0, 0, 0};
         int ex = guiLeft + 70 + i + xPos[i];
         int ey = guiTop + 120 + yPos[i];
         this.drawKey(i, ex, ey, this.lastKeyPressed == i ? -68 : 0, 0);
      }

      GL11.glPushMatrix();
      GL11.glColor3f(this.r, this.g, this.b);
      int ex = guiLeft + 80 + 23;
      int var14 = guiTop + 2;
      this.getGUIInstance().func_73729_b(ex, var14, 23, 202, 122, 22);
      GL11.glPopMatrix();

      for (MiniGameAirBoxing.KeyData key : this.keys) {
         int id = key.id;
         float xProgress = key.xProgress;
         ex = (int)(guiLeft + 203 + xProgress);
         var14 = guiTop + 1;
         float color = key.missed ? 0.5F : (key.catched ? 0.5F : 1.0F);
         float color2 = key.missed ? 1.0F : 0.5F;
         GL11.glPushMatrix();
         GL11.glColor3f(color2, color, color);
         this.drawKey(id, ex, var14, -68, 0);
         GL11.glPopMatrix();
      }

      ex = guiLeft + 80;
      var14 = guiTop;
      GL11.glPushMatrix();
      GL11.glTranslatef(ex, var14, 0.0F);
      if (this.lastKeyResult != -1) {
         float timer = 0.0F;
         if (this.lastKeyResultTime == null) {
            this.lastKeyResultTime = Instant.now();
         } else {
            timer = (float)Duration.between(this.lastKeyResultTime, Instant.now()).toMillis() / 1000.0F;
            timer *= 5.0F;
            if (timer > 1.0F) {
               timer = 1.0F;
            }

            if (timer < 0.0F) {
               timer = 0.0F;
            }

            if (timer >= 2.0F) {
               this.lastKeyResult = -1;
               this.lastKeyResultTime = null;
            }
         }

         if (JGConfigClientSettings.airboxingAnimatedColorOn) {
            GL11.glColor4f(
               1.0F + (this.lastKeyResult == 0 ? -timer / 2.0F : 0.0F),
               1.0F + (this.lastKeyResult == 0 ? 0.0F : -timer / 2.0F),
               1.0F + (this.lastKeyResult == 0 ? 0.0F : -timer / 2.0F),
               1.0F
            );
         } else {
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         }

         float scale = 1.0F + timer / 5.0F;
         GL11.glTranslatef((-scale * 23.0F + 23.0F) / 2.0F, (-scale * 26.0F + 26.0F) / 2.0F, 0.0F);
         GL11.glScalef(scale, scale, scale);
      }

      this.getGUIInstance().func_73729_b(0, 0, 0, 200, 23, 26);
      GL11.glPopMatrix();
      int length = this.airboxingResults.size() - 1;

      for (int i = length; i >= 0; i--) {
         int result = this.airboxingResults.get(i);
         boolean fail = result == 1;
         String text = fail ? "Miss" : "Hit!";
         int timePassed = (int)(Duration.between(this.airboxingResultsTime.get(i), Instant.now()).toMillis() / 100L);
         this.getGUIInstance();
         this.getGUIInstance();
         JRMCoreGuiScreen.drawStringWithBorder(
            JRMCoreGuiScreen.mc.field_71466_p, text, guiLeft + 80, guiTop + 30 + timePassed, JRMCoreH.techNCCol[fail ? 0 : 4]
         );
         if (timePassed > 30) {
            this.airboxingResults.remove(i);
            this.airboxingResultsTime.remove(i);
         }
      }
   }

   private void drawKey(int id, int x, int y, int u, int v) {
      int[] uPos = new int[]{0, 17, 34, 51};
      this.getGUIInstance().func_73729_b(x, y, 174 + uPos[id] + u, 235 + v, 17, 21);
   }

   private void drawKey(int id, int x, int y) {
      this.drawKey(id, x, y, 0, 0);
   }

   private void isKeyPressed() {
      int key = this.lastKeyPressed;
      this.lastKeyPressed = -1;
      if (GameSettings.func_100015_a(JRMCoreClient.mc.field_71474_y.field_74351_w)) {
         this.lastKeyPressed = 0;
      } else if (GameSettings.func_100015_a(JRMCoreClient.mc.field_71474_y.field_74370_x)) {
         this.lastKeyPressed = 1;
      } else if (GameSettings.func_100015_a(JRMCoreClient.mc.field_71474_y.field_74368_y)) {
         this.lastKeyPressed = 2;
      } else if (GameSettings.func_100015_a(JRMCoreClient.mc.field_71474_y.field_74366_z)) {
         this.lastKeyPressed = 3;
      }

      this.wasKeyPressed = this.lastKeyPressed != -1;
      if (this.lastKeyPressed != key) {
         this.wasKeyChecked = false;
      }
   }

   public void playAnimation(int animationID) {
      this.gameModelAge = 0.0F;
      this.gameModelAnimationChanged = true;
      this.gameModelAnimationID = animationID;
      if (animationID > 0) {
         if (animationID <= 4) {
            int id = (int)(Math.random() * 3.0) + 1;
            JRMCoreClient.mc
               .func_147118_V()
               .func_147682_a(
                  PositionedSoundRecord.func_147674_a(
                     new ResourceLocation("jinryuudragonbc:DBC4.punch" + id),
                     JRMCoreClient.mc.field_71439_g.field_70170_p.field_73012_v.nextFloat() * 0.4F + 0.8F
                  )
               );
         } else if (animationID == 5) {
            int id = (int)(Math.random() * 2.0) + 1;
            JRMCoreClient.mc
               .func_147118_V()
               .func_147682_a(
                  PositionedSoundRecord.func_147674_a(
                     new ResourceLocation("jinryuudragonbc:DBC4.block" + id),
                     JRMCoreClient.mc.field_71439_g.field_70170_p.field_73012_v.nextFloat() * 0.4F + 0.8F
                  )
               );
         }
      }
   }

   class KeyData {
      private int mode;
      private int id;
      private float xProgress;
      private boolean missed;
      private boolean catched;

      public KeyData(int mode, int id, float xProgress) {
         this.mode = mode;
         this.id = id;
         this.xProgress = xProgress;
         this.missed = false;
         this.catched = false;
      }
   }
}
