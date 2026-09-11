package JinRyuu.JRMCore.entity;

import JinRyuu.JRMCore.JRMCoreGuiScreen;
import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.manager.AttributeArray;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Color;
import java.time.Duration;
import java.time.Instant;
import java.util.Random;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

@SideOnly(Side.CLIENT)
public class EntityAuraFlat extends EntityLiving {
   private final float LIFE = 0.5F;
   public AttributeArray array;
   public AttributeArray transparency;
   private Instant spawnTime;
   private float age;
   private boolean directionX;
   private boolean lrX;
   private boolean lrZ;
   private float startY;
   public Color aura;
   private byte auraID;
   private ResourceLocation texture;

   public EntityAuraFlat(World w) {
      super(w);
      Random r = new Random();
      float ARRAY_SPEED = 2.0F;
      this.transparency = new AttributeArray(0.0F, 0.0F, 0.125F, 0.7F, 0.25F, 0.0F);
      Color base = new Color(191, 215, 255);
      Color kk = new Color(235, 65, 35);
      Color ssj = new Color(255, 237, 117);
      Color god = new Color(250, 36, 25);
      Color blue = new Color(25, 205, 250);
      float combo = JRMCoreGuiScreen.instance.miniGameAirBoxing.comboCounter;
      this.aura = combo >= 200.0F ? blue : (combo >= 150.0F ? god : (combo >= 100.0F ? ssj : (combo >= 50.0F ? kk : base)));
      this.auraID = combo >= 150.0F ? 4 : (byte)(r.nextInt(3) + 1);
      this.texture = new ResourceLocation(JRMCoreH.tjjrmc + ":textures/auras/auraflat" + this.auraID + ".png");
      float auraSpeed = combo >= 150.0F ? 0.35F : 1.0F;
      this.array = new AttributeArray(0.0F, 0.0F, 0.125F, 1.0F * auraSpeed, 0.25F, 2.0F * auraSpeed);
      this.spawnTime = Instant.now();
      this.age = 0.0F;
      boolean xz = r.nextBoolean();
      this.lrX = r.nextBoolean();
      this.lrZ = r.nextBoolean();
      float value = (r.nextInt(1000) / 100.0F - 5.0F) / 4.0F;
      float limit = 1.25F;
      this.field_70165_t = (xz ? value : 1.25F) * (this.lrX ? -1 : 1);
      this.startY = -(r.nextInt(1000) / 100.0F - 5.0F) / 3.0F + 2.0F;
      this.field_70161_v = (!xz ? value : 1.25F) * (this.lrZ ? -1 : 1);
      this.directionX = xz;
   }

   public void update() {
      this.age = this.age + (float)Duration.between(this.spawnTime, Instant.now()).toMillis() / 1000.0F;
      this.array.update(this.age);
      this.transparency.update(this.age);
      this.spawnTime = Instant.now();
      if (this.age >= 0.25F) {
         this.func_70106_y();
      }

      this.field_70163_u = this.startY - this.array.lastValue;
      if (!this.directionX) {
         this.field_70165_t = this.array.lastValue * (this.lrX ? -1 : 1);
      } else {
         this.field_70161_v = this.array.lastValue * (this.lrZ ? -1 : 1);
      }
   }

   public ResourceLocation getTexture() {
      return this.texture;
   }
}
