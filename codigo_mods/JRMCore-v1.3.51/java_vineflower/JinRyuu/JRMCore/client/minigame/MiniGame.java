package JinRyuu.JRMCore.client.minigame;

import JinRyuu.JRMCore.JRMCoreClient;
import JinRyuu.JRMCore.JRMCoreGuiScreen;
import JinRyuu.JRMCore.JRMCoreH;

public abstract class MiniGame {
   public static final String training1 = JRMCoreH.tjjrmc + ":gui/training1.png";
   public static final String training1gui = JRMCoreH.tjjrmc + ":gui/training1gui.png";
   public static final String[] States = new String[]{"Loading", "Initializing", "Playing", "GameOver"};
   public static final int STATE_ZERO = 0;
   public static final int STATE_INIT = 1;
   public static final int STATE_GAME = 2;
   public static final int STATE_END = 3;
   public int state = 0;
   public int lvl = 0;
   public int score = 0;
   public int lives = 3;

   public JRMCoreGuiScreen getGUIInstance() {
      return JRMCoreGuiScreen.instance;
   }

   public abstract void gameReset();

   public abstract void generateStart();

   public abstract void chooseReward();

   public abstract void update(int var1, int var2);

   public void stateManager(int guiLeft, int guiTop) {
      if (!JRMCoreClient.mc.field_71439_g.field_70170_p.field_72995_K || !JRMCoreClient.mc.func_147113_T()) {
         switch (this.state) {
            case 0:
               this.gameReset();
               break;
            case 1:
               this.generateStart();
               break;
            case 2:
               this.update(guiLeft, guiTop);
               break;
            case 3:
               this.chooseReward();
         }
      }
   }
}
