package JinRyuu.JRMCore.i;

import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.JRMCoreKeyHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiCustomPlayerInventory extends GuiContainer {
   private float xSize_lo;
   private float ySize_lo;
   private static final ResourceLocation iconLocation = new ResourceLocation("jinryuumodscore:gui/ci.png");
   private final InventoryCustomPlayer inventory;

   public GuiCustomPlayerInventory(EntityPlayer player, InventoryPlayer inventoryPlayer, InventoryCustomPlayer inventoryCustom) {
      super(new ContainerCustomPlayer(player, inventoryPlayer, inventoryCustom));
      this.inventory = inventoryCustom;
   }

   protected void func_73869_a(char c, int keyCode) {
      super.func_73869_a(c, keyCode);
      if (keyCode == JRMCoreKeyHandler.Sagasys.func_151463_i()) {
         this.field_146297_k.field_71439_g.func_71053_j();
      }
   }

   public void func_73863_a(int mouseX, int mouseY, float f) {
      super.func_73863_a(mouseX, mouseY, f);
      this.xSize_lo = mouseX;
      this.ySize_lo = mouseY;
   }

   protected void func_146979_b(int mouseX, int mouseY) {
      String s = this.inventory.func_145818_k_()
         ? JRMCoreH.trl("jrmc", this.inventory.func_145825_b())
         : I18n.func_135052_a(this.inventory.func_145825_b(), new Object[0]);
      this.field_146289_q.func_78276_b(s, 82, 12, 4210752);
      this.field_146289_q.func_78276_b(JRMCoreH.trl("jrmc", "WeightSlot"), 100, 66, 4210752);
      this.field_146289_q.func_78276_b(JRMCoreH.trl("jrmc", "BodySlot"), 100, 48, 4210752);
      this.field_146289_q.func_78276_b(JRMCoreH.trl("jrmc", "HeadSlot"), 100, 30, 4210752);
   }

   protected void func_146976_a(float f, int mouseX, int mouseY) {
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      this.field_146297_k.func_110434_K().func_110577_a(iconLocation);
      this.func_73729_b(this.field_147003_i, this.field_147009_r, 0, 0, this.field_146999_f, this.field_147000_g);
      this.drawEntityOnScreen(
         this.field_147003_i + 51,
         this.field_147009_r + 75,
         30,
         this.field_147003_i + 51 - this.xSize_lo,
         this.field_147009_r + 25 - this.ySize_lo,
         this.field_146297_k.field_71439_g
      );
   }

   private void drawEntityOnScreen(int i, int j, int k, float f, float g, EntityClientPlayerMP thePlayer) {
      GuiInventory.func_147046_a(
         this.field_147003_i + 51,
         this.field_147009_r + 75,
         30,
         this.field_147003_i + 51 - this.xSize_lo,
         this.field_147009_r + 25 - this.ySize_lo,
         this.field_146297_k.field_71439_g
      );
   }
}
