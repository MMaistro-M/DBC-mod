package JinRyuu.JRMCore;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.MovementInput;

@SideOnly(Side.CLIENT)
public class MoveInputJRMC extends MovementInput {
   private GameSettings gameSettings;
   private static final String __OBFID = "CL_00000937";
   public float moveModifier = 1.0F;

   public MoveInputJRMC(GameSettings p_i1237_1_, float mm) {
      this.gameSettings = p_i1237_1_;
      this.moveModifier = mm;
   }

   public void func_78898_a() {
      this.field_78902_a = 0.0F;
      this.field_78900_b = 0.0F;
      if (this.gameSettings.field_74351_w.func_151470_d()) {
         this.field_78900_b++;
      }

      if (this.gameSettings.field_74368_y.func_151470_d()) {
         this.field_78900_b--;
      }

      if (this.gameSettings.field_74370_x.func_151470_d()) {
         this.field_78902_a++;
      }

      if (this.gameSettings.field_74366_z.func_151470_d()) {
         this.field_78902_a--;
      }

      this.field_78901_c = this.gameSettings.field_74314_A.func_151470_d();
      this.field_78899_d = this.gameSettings.field_74311_E.func_151470_d();
      if (this.field_78899_d) {
         this.field_78902_a = (float)(this.field_78902_a * 0.3);
         this.field_78900_b = (float)(this.field_78900_b * 0.3);
      }

      this.field_78902_a = this.field_78902_a * this.moveModifier;
      this.field_78900_b = this.field_78900_b * this.moveModifier;
   }
}
