package JinRyuu.JRMCore.entity;

import JinRyuu.JRMCore.manager.AttributeArray;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class ModelNPCTraining extends JGModel {
   private final int ANIMATION_STAND = 0;
   private final int ANIMATION_LEFT_PUNCH = 1;
   private final int ANIMATION_RIGHT_PUNCH = 2;
   private final int ANIMATION_LEFT_KICK = 3;
   private final int ANIMATION_RIGHT_KICK = 4;
   private final int ANIMATION_HURT = 5;
   private final int ANIMATION_VICTORY = 6;
   private final int ANIMATION_DEFEAT = 7;
   public ModelRenderer head;
   public ModelRenderer body;
   public ModelRenderer rightarm;
   public ModelRenderer leftarm;
   public ModelRenderer rightleg;
   public ModelRenderer leftleg;
   public ModelRenderer rightarm2 = new ModelRenderer(this, 40, 26);
   public ModelRenderer leftarm2;
   public ModelRenderer rightleg2;
   public ModelRenderer leftleg2;

   public ModelNPCTraining() {
      super(64, 64);
      this.rightarm2.func_78793_a(0.0F, 4.0F, 0.0F);
      this.rightarm2.func_78790_a(-3.0F, 0.0F, -2.0F, 4, 6, 4, 0.0F);
      this.leftarm = new ModelRenderer(this, 32, 36);
      this.leftarm.func_78793_a(5.0F, 2.0F, 0.0F);
      this.leftarm.func_78790_a(-1.0F, -2.0F, -2.0F, 4, 6, 4, 0.0F);
      this.leftleg2 = new ModelRenderer(this, 16, 46);
      this.leftleg2.func_78793_a(0.0F, 6.0F, 0.0F);
      this.leftleg2.func_78790_a(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.0F);
      this.rightarm = new ModelRenderer(this, 40, 16);
      this.rightarm.func_78793_a(-5.0F, 2.0F, 0.0F);
      this.rightarm.func_78790_a(-3.0F, -2.0F, -2.0F, 4, 6, 4, 0.0F);
      this.body = new ModelRenderer(this, 16, 16);
      this.body.func_78793_a(0.0F, 0.0F, 0.0F);
      this.body.func_78790_a(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F);
      this.rightleg = new ModelRenderer(this, 0, 16);
      this.rightleg.func_78793_a(-1.9F, 12.0F, 0.0F);
      this.rightleg.func_78790_a(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.0F);
      this.rightleg2 = new ModelRenderer(this, 0, 26);
      this.rightleg2.func_78793_a(0.0F, 6.0F, 0.0F);
      this.rightleg2.func_78790_a(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.0F);
      this.leftleg = new ModelRenderer(this, 16, 36);
      this.leftleg.func_78793_a(1.9F, 12.0F, 0.0F);
      this.leftleg.func_78790_a(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.0F);
      this.leftarm2 = new ModelRenderer(this, 32, 46);
      this.leftarm2.func_78793_a(0.0F, 4.0F, 0.0F);
      this.leftarm2.func_78790_a(-1.0F, 0.0F, -2.0F, 4, 6, 4, 0.0F);
      this.head = new ModelRenderer(this, 0, 0);
      this.head.func_78793_a(0.0F, 0.0F, 0.0F);
      this.head.func_78790_a(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
      this.leftarm.func_78792_a(this.leftarm2);
      this.rightarm.func_78792_a(this.rightarm2);
      this.rightleg.func_78792_a(this.rightleg2);
      this.leftleg.func_78792_a(this.leftleg2);
      this.body.func_78792_a(this.head);
      this.body.func_78792_a(this.leftarm);
      this.body.func_78792_a(this.rightarm);
      this.body.func_78792_a(this.leftleg);
      this.body.func_78792_a(this.rightleg);
   }

   @Override
   public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
      this.updateJump();
      GL11.glPushMatrix();
      float legL = this.leftleg.field_78795_f;
      if (legL > 0.75F) {
         legL = 0.75F;
      }

      float legL2 = this.leftleg2.field_78795_f;
      float legR = this.rightleg.field_78795_f;
      if (legR > 0.75F) {
         legR = 0.75F;
      }

      float legR2 = this.rightleg.field_78795_f;
      float leg = legL < legR ? legL : legR;
      float leg2 = legL < legR ? legL2 : legR2;
      float F1 = leg * -0.25F + leg2 * (leg2 > 1.0F ? 0.01F : -0.08F);
      GL11.glTranslatef(0.0F, F1 - (this.jumping && this.jumpPos != null ? this.jumpPos.lastValue : 0.0F), 0.0F);
      this.body.func_78785_a(f5);
      GL11.glPopMatrix();
   }

   @Override
   public void resetAnimation() {
      this.setRotateAngle(this.head, 0.0F, 0.0F, 0.0F);
      this.setRotateAngle(this.body, 0.0F, 0.0F, 0.0F);
      this.setRotateAngle(this.leftarm, 0.0F, 0.0F, 0.0F);
      this.setRotateAngle(this.leftarm2, 0.0F, 0.0F, 0.0F);
      this.setRotateAngle(this.rightarm, 0.0F, 0.0F, 0.0F);
      this.setRotateAngle(this.rightarm2, 0.0F, 0.0F, 0.0F);
      this.setRotateAngle(this.leftleg, 0.0F, 0.0F, 0.0F);
      this.setRotateAngle(this.leftleg2, 0.0F, 0.0F, 0.0F);
      this.setRotateAngle(this.rightleg, 0.0F, 0.0F, 0.0F);
      this.setRotateAngle(this.rightleg2, 0.0F, 0.0F, 0.0F);
   }

   @Override
   public void setupAnimation() {
      switch (this.animationID) {
         case 0: {
            float FRAME0 = 0.0F;
            float FRAME1 = 0.8F;
            this.addAttribute(
               this.leftarm,
               new AttributeArray(FRAME0, 0.0F, FRAME1, -0.8F),
               new AttributeArray(FRAME0, 0.0F, FRAME1, -0.1F),
               new AttributeArray(FRAME0, 0.0F, FRAME1, -0.1F)
            );
            this.addAttribute(this.leftarm2, new AttributeArray(FRAME0, 0.0F, FRAME1, -1.0F), new AttributeArray(FRAME0, 0.0F, FRAME1, 0.001F), null);
            this.addAttribute(
               this.rightarm,
               new AttributeArray(FRAME0, 0.0F, FRAME1, -0.8F),
               new AttributeArray(FRAME0, 0.0F, FRAME1, 0.1F),
               new AttributeArray(FRAME0, 0.0F, FRAME1, 0.1F)
            );
            this.addAttribute(this.rightarm2, new AttributeArray(FRAME0, 0.0F, FRAME1, -1.0F), new AttributeArray(FRAME0, 0.0F, FRAME1, 0.001F), null);
            this.addAttribute(this.leftleg, new AttributeArray(FRAME0, 0.0F, FRAME1, -1.0F), new AttributeArray(FRAME0, 0.0F, FRAME1, -0.5F), null);
            this.addAttribute(this.leftleg2, new AttributeArray(FRAME0, 0.0F, FRAME1, 1.0F), new AttributeArray(FRAME0, 0.0F, FRAME1, 0.001F), null);
            this.addAttribute(this.rightleg, new AttributeArray(FRAME0, 0.0F, FRAME1, -1.0F), new AttributeArray(FRAME0, 0.0F, FRAME1, 0.8F), null);
            this.addAttribute(this.rightleg2, new AttributeArray(FRAME0, 0.0F, FRAME1, 1.0F), new AttributeArray(FRAME0, 0.0F, FRAME1, 0.001F), null);
            return;
         }
         case 1: {
            float FRAME0 = 0.0F;
            float FRAME1 = 0.05F;
            float FRAME2 = 0.3F;
            this.addAttribute(this.body, null, new AttributeArray(FRAME0, 0.0F, FRAME1, 0.2F, FRAME2, 0.0F), null);
            this.addAttribute(
               this.leftarm,
               new AttributeArray(FRAME0, -0.8F, FRAME1, -1.6F, FRAME2, -0.8F),
               new AttributeArray(FRAME0, -0.1F, FRAME1, -0.2F, FRAME2, -0.1F),
               new AttributeArray(FRAME0, -0.1F)
            );
            this.addAttribute(this.leftarm2, new AttributeArray(FRAME0, -1.0F, FRAME1, 0.0F, FRAME2, -1.0F), new AttributeArray(FRAME0, 0.001F), null);
            this.addAttribute(
               this.rightarm,
               new AttributeArray(FRAME0, -0.8F, FRAME1, -0.3F, FRAME2, -0.8F),
               new AttributeArray(FRAME0, 0.1F, FRAME1, 0.2F, FRAME2, 0.1F),
               new AttributeArray(FRAME0, 0.1F, FRAME1, 0.2F, FRAME2, 0.1F)
            );
            this.addAttribute(this.rightarm2, new AttributeArray(FRAME0, -1.0F), new AttributeArray(FRAME0, 0.001F), null);
            this.addAttribute(this.leftleg, new AttributeArray(FRAME0, -1.0F), new AttributeArray(FRAME0, -0.5F, FRAME1, -0.7F, FRAME2, -0.5F), null);
            this.addAttribute(this.leftleg2, new AttributeArray(FRAME0, 1.0F), new AttributeArray(FRAME0, 0.001F), null);
            this.addAttribute(this.rightleg, new AttributeArray(FRAME0, -1.0F), new AttributeArray(FRAME0, 0.8F, FRAME1, 0.6F, FRAME2, 0.8F), null);
            this.addAttribute(this.rightleg2, new AttributeArray(FRAME0, 1.0F), new AttributeArray(FRAME0, 0.001F), null);
            return;
         }
         case 2: {
            float FRAME0 = 0.0F;
            float FRAME1 = 0.05F;
            float FRAME2 = 0.3F;
            this.addAttribute(this.body, null, new AttributeArray(FRAME0, 0.0F, FRAME1, -0.2F, FRAME2, 0.0F), null);
            this.addAttribute(
               this.leftarm,
               new AttributeArray(FRAME0, -0.8F, FRAME1, -0.3F, FRAME2, -0.8F),
               new AttributeArray(FRAME0, -0.1F, FRAME1, -0.2F, FRAME2, -0.1F),
               new AttributeArray(FRAME0, -0.1F, FRAME1, -0.1F, FRAME2, -0.1F)
            );
            this.addAttribute(this.leftarm2, new AttributeArray(FRAME0, -1.0F), new AttributeArray(FRAME0, 0.001F), null);
            this.addAttribute(
               this.rightarm,
               new AttributeArray(FRAME0, -0.8F, FRAME1, -1.6F, FRAME2, -0.8F),
               new AttributeArray(FRAME0, 0.1F, FRAME1, 0.2F, FRAME2, 0.1F),
               new AttributeArray(FRAME0, 0.1F, FRAME1, 0.2F, FRAME2, 0.1F)
            );
            this.addAttribute(this.rightarm2, new AttributeArray(FRAME0, -1.0F, FRAME1, 0.0F, FRAME2, -1.0F), new AttributeArray(FRAME0, 0.001F), null);
            this.addAttribute(this.leftleg, new AttributeArray(FRAME0, -1.0F), new AttributeArray(FRAME0, -0.5F, FRAME1, -0.3F, FRAME2, -0.5F), null);
            this.addAttribute(this.leftleg2, new AttributeArray(FRAME0, 1.0F), new AttributeArray(FRAME0, 0.001F), null);
            this.addAttribute(this.rightleg, new AttributeArray(FRAME0, -1.0F), new AttributeArray(FRAME0, 0.8F, FRAME1, 1.0F, FRAME2, 0.8F), null);
            this.addAttribute(this.rightleg2, new AttributeArray(FRAME0, 1.0F), new AttributeArray(FRAME0, 0.001F), null);
            return;
         }
         case 3: {
            float FRAME0 = 0.0F;
            float FRAME1 = 0.05F;
            float FRAME2 = 0.25F;
            float FRAME3 = 0.3F;
            this.addAttribute(
               this.body,
               null,
               new AttributeArray(FRAME0, 0.0F, FRAME1, 0.05F, FRAME2, 0.02F, FRAME3, 0.0F),
               new AttributeArray(FRAME0, 0.0F, FRAME1, -0.4F, FRAME2, -0.3F, FRAME3, 0.0F)
            );
            this.addAttribute(
               this.leftarm,
               new AttributeArray(FRAME0, -0.8F, FRAME1, -0.2F, FRAME2, -0.3F, FRAME3, -0.8F),
               new AttributeArray(FRAME0, -0.1F),
               new AttributeArray(FRAME0, -0.1F, FRAME1, -1.3F, FRAME2, -1.0F, FRAME3, -0.1F)
            );
            this.addAttribute(this.leftarm2, new AttributeArray(FRAME0, -1.0F), new AttributeArray(FRAME0, 0.001F), null);
            this.addAttribute(
               this.rightarm,
               new AttributeArray(FRAME0, -0.8F, FRAME1, -0.2F, FRAME2, -0.3F, FRAME3, -0.8F),
               new AttributeArray(FRAME0, 0.1F),
               new AttributeArray(FRAME0, 0.1F, FRAME1, 0.5F, FRAME2, 0.4F, FRAME3, 0.1F)
            );
            this.addAttribute(
               this.rightarm2, new AttributeArray(FRAME0, -1.0F, FRAME1, -1.5F, FRAME2, -1.4F, FRAME3, -1.0F), new AttributeArray(FRAME0, 0.001F), null
            );
            this.addAttribute(
               this.leftleg,
               new AttributeArray(FRAME0, -1.0F, FRAME1, -1.3F, FRAME2, -1.2F, FRAME3, -1.0F),
               new AttributeArray(FRAME0, -0.5F, FRAME1, -0.3F, FRAME2, -0.4F, FRAME3, -0.5F),
               new AttributeArray(FRAME0, 0.0F, FRAME1, -1.3F, FRAME2, -1.0F, FRAME3, 0.0F)
            );
            this.addAttribute(
               this.leftleg2, new AttributeArray(FRAME0, 1.0F, FRAME1, 0.0F, FRAME2, 0.1F, FRAME3, 1.0F), new AttributeArray(FRAME0, 0.001F), null
            );
            this.addAttribute(
               this.rightleg,
               new AttributeArray(FRAME0, -1.0F, FRAME1, 0.2F, FRAME2, 0.3F, FRAME3, -1.0F),
               new AttributeArray(FRAME0, 0.8F, FRAME1, 0.6F, FRAME2, 0.7F, FRAME3, 0.8F),
               new AttributeArray(FRAME0, 0.0F, FRAME1, 0.3F, FRAME2, 0.2F, FRAME3, 0.0F)
            );
            this.addAttribute(
               this.rightleg2, new AttributeArray(FRAME0, 1.0F, FRAME1, 0.0F, FRAME2, 0.1F, FRAME3, 1.0F), new AttributeArray(FRAME0, 0.001F), null
            );
            return;
         }
         case 4: {
            float FRAME0 = 0.0F;
            float FRAME1 = 0.05F;
            float FRAME2 = 0.25F;
            float FRAME3 = 0.3F;
            this.addAttribute(
               this.body,
               null,
               new AttributeArray(FRAME0, 0.0F, FRAME1, -0.5F, FRAME2, -0.48F, FRAME3, 0.0F),
               new AttributeArray(FRAME0, 0.0F, FRAME1, 0.4F, FRAME2, 0.3F, FRAME3, 0.0F)
            );
            this.addAttribute(
               this.leftarm,
               new AttributeArray(FRAME0, -0.8F, FRAME1, -0.2F, FRAME2, -0.3F, FRAME3, -0.8F),
               new AttributeArray(FRAME0, -0.1F),
               new AttributeArray(FRAME0, -0.1F, FRAME1, -0.5F, FRAME2, -0.4F, FRAME3, -0.1F)
            );
            this.addAttribute(
               this.leftarm2, new AttributeArray(FRAME0, -1.0F, FRAME1, -1.5F, FRAME2, -1.4F, FRAME3, -1.0F), new AttributeArray(FRAME0, 0.001F), null
            );
            this.addAttribute(
               this.rightarm,
               new AttributeArray(FRAME0, -0.8F, FRAME1, -0.2F, FRAME2, -0.3F, FRAME3, -0.8F),
               new AttributeArray(FRAME0, 0.1F),
               new AttributeArray(FRAME0, 0.1F, FRAME1, 1.3F, FRAME2, 1.0F, FRAME3, 0.1F)
            );
            this.addAttribute(this.rightarm2, new AttributeArray(FRAME0, -1.0F), new AttributeArray(FRAME0, 0.001F), null);
            this.addAttribute(
               this.leftleg,
               new AttributeArray(FRAME0, -1.0F, FRAME1, -0.2F, FRAME2, -0.3F, FRAME3, -1.0F),
               new AttributeArray(FRAME0, -0.5F, FRAME1, 0.6F, FRAME2, 0.7F, FRAME3, -0.5F),
               new AttributeArray(FRAME0, 0.0F, FRAME1, -0.8F, FRAME2, -0.7F, FRAME3, 0.0F)
            );
            this.addAttribute(
               this.leftleg2, new AttributeArray(FRAME0, 1.0F, FRAME1, 0.0F, FRAME2, 0.1F, FRAME3, 1.0F), new AttributeArray(FRAME0, 0.001F), null
            );
            this.addAttribute(
               this.rightleg,
               new AttributeArray(FRAME0, -1.0F, FRAME1, -1.3F, FRAME2, -1.2F, FRAME3, -1.0F),
               new AttributeArray(FRAME0, 0.8F, FRAME1, 0.3F, FRAME2, 0.4F, FRAME3, 0.8F),
               new AttributeArray(FRAME0, 0.0F, FRAME1, 1.3F, FRAME2, 1.0F, FRAME3, 0.0F)
            );
            this.addAttribute(
               this.rightleg2, new AttributeArray(FRAME0, 1.0F, FRAME1, 0.0F, FRAME2, 0.1F, FRAME3, 1.0F), new AttributeArray(FRAME0, 0.001F), null
            );
            return;
         }
         case 5: {
            float FRAME0 = 0.0F;
            float FRAME1 = 0.05F;
            float FRAME2 = 0.3F;
            this.addAttribute(this.head, new AttributeArray(FRAME0, 0.0F, FRAME1, -0.1F, FRAME2, 0.0F), null, null);
            this.addAttribute(
               this.leftarm,
               new AttributeArray(FRAME0, -0.8F, FRAME1, -0.6F, FRAME2, -0.8F),
               new AttributeArray(FRAME0, -0.1F),
               new AttributeArray(FRAME0, -0.1F, FRAME1, -0.3F, FRAME2, -0.1F)
            );
            this.addAttribute(this.leftarm2, new AttributeArray(FRAME0, -1.0F, FRAME1, -0.5F, FRAME2, -1.0F), new AttributeArray(FRAME0, 0.001F), null);
            this.addAttribute(
               this.rightarm,
               new AttributeArray(FRAME0, -0.8F, FRAME1, -0.6F, FRAME2, -0.8F),
               new AttributeArray(FRAME0, 0.1F),
               new AttributeArray(FRAME0, 0.1F, FRAME1, 0.3F, FRAME2, 0.1F)
            );
            this.addAttribute(this.rightarm2, new AttributeArray(FRAME0, -1.0F, FRAME1, -0.5F, FRAME2, -1.0F), new AttributeArray(FRAME0, 0.001F), null);
            this.addAttribute(this.leftleg, new AttributeArray(FRAME0, -1.0F), new AttributeArray(FRAME0, -0.5F), null);
            this.addAttribute(this.leftleg2, new AttributeArray(FRAME0, 1.0F), new AttributeArray(FRAME0, 0.001F), null);
            this.addAttribute(this.rightleg, new AttributeArray(FRAME0, -1.0F), new AttributeArray(FRAME0, 0.8F), null);
            this.addAttribute(this.rightleg2, new AttributeArray(FRAME0, 1.0F), new AttributeArray(FRAME0, 0.001F), null);
            return;
         }
         case 6: {
            float FRAME0 = 0.0F;
            float FRAME1 = 0.3F;
            float FRAME2 = 0.5F;
            float FRAME3 = 0.7F;
            this.addAttribute(this.body, null, new AttributeArray(FRAME0, 0.0F), new AttributeArray(FRAME0, 0.0F, FRAME1, -0.06F, FRAME2, -0.06F, FRAME3, 0.0F));
            this.addAttribute(
               this.leftarm,
               new AttributeArray(FRAME0, -0.8F, FRAME1, -2.7F, FRAME2, -2.7F, FRAME3, -0.1F),
               new AttributeArray(FRAME0, -0.1F),
               new AttributeArray(FRAME0, -0.1F, FRAME1, 0.2F, FRAME2, 0.2F, FRAME3, -0.1F)
            );
            this.addAttribute(
               this.leftarm2, new AttributeArray(FRAME0, -1.0F, FRAME1, -0.1F, FRAME2, -0.1F, FRAME3, -0.9F), new AttributeArray(FRAME0, 0.001F), null
            );
            this.addAttribute(
               this.rightarm,
               new AttributeArray(FRAME0, -0.8F, FRAME1, -0.6F, FRAME2, -0.6F, FRAME3, -0.1F),
               new AttributeArray(FRAME0, 0.1F, FRAME1, 0.2F, FRAME2, 0.2F, FRAME3, 0.1F),
               new AttributeArray(FRAME0, 0.1F, FRAME1, 0.2F, FRAME2, 0.2F, FRAME3, 0.1F)
            );
            this.addAttribute(this.rightarm2, new AttributeArray(FRAME0, -1.0F, FRAME2, -1.0F, FRAME3, -0.9F), new AttributeArray(FRAME0, 0.001F), null);
            this.addAttribute(
               this.leftleg,
               new AttributeArray(FRAME0, -1.0F, FRAME1, 0.0F, FRAME2, 0.0F, FRAME3, 0.1F),
               new AttributeArray(FRAME0, 0.5F, FRAME1, 0.0F, FRAME2, 0.0F, FRAME3, 0.1F),
               new AttributeArray(FRAME0, 0.001F, FRAME1, 0.06F, FRAME2, 0.06F, FRAME3, -0.1F)
            );
            this.addAttribute(
               this.leftleg2,
               new AttributeArray(FRAME0, 1.0F, FRAME1, 0.0F, FRAME2, 0.0F, FRAME3, 0.0F),
               new AttributeArray(FRAME0, 0.001F, FRAME1, 0.0F, FRAME2, 0.0F, FRAME3, 0.001F),
               null
            );
            this.addAttribute(
               this.rightleg,
               new AttributeArray(FRAME0, -1.0F, FRAME1, -1.05F, FRAME2, -1.05F, FRAME3, 0.1F),
               new AttributeArray(FRAME0, 0.8F, FRAME1, 0.4F, FRAME2, 0.4F, FRAME3, 0.1F),
               new AttributeArray(FRAME0, 0.0F, FRAME2, 0.0F, FRAME3, 0.1F)
            );
            this.addAttribute(
               this.rightleg2, new AttributeArray(FRAME0, 1.0F, FRAME1, 1.05F, FRAME2, 1.05F, FRAME3, 0.0F), new AttributeArray(FRAME0, 0.001F), null
            );
            this.addJump(new AttributeArray(FRAME0, 0.0F, FRAME1, 1.0F, FRAME2, 0.9F, FRAME3, 0.0F));
            return;
         }
         case 7: {
            float FRAME0 = 0.0F;
            float FRAME1 = 0.3F;
            float FRAME2 = 0.5F;
            float FRAME3 = 0.7F;
            this.addAttribute(
               this.head,
               new AttributeArray(FRAME0, 0.0F, FRAME1, 0.06F),
               new AttributeArray(FRAME0, 0.0F, FRAME1, -0.5F, FRAME1 * 2.0F, 0.0F, FRAME1 * 3.0F, 0.5F, FRAME1 * 4.0F, 0.0F),
               null
            );
            this.addAttribute(this.body, new AttributeArray(FRAME0, 0.0F, FRAME1, 0.06F), null, null);
            this.addAttribute(
               this.leftarm, new AttributeArray(FRAME0, -0.8F, FRAME1, -0.15F), new AttributeArray(FRAME0, -0.1F), new AttributeArray(FRAME0, -0.1F)
            );
            this.addAttribute(this.leftarm2, new AttributeArray(FRAME0, -1.0F, FRAME1, -0.05F), new AttributeArray(FRAME0, 0.001F), null);
            this.addAttribute(
               this.rightarm, new AttributeArray(FRAME0, -0.8F, FRAME1, -0.15F), new AttributeArray(FRAME0, 0.1F), new AttributeArray(FRAME0, 0.1F)
            );
            this.addAttribute(this.rightarm2, new AttributeArray(FRAME0, -1.0F, FRAME1, -0.05F), new AttributeArray(FRAME0, 0.001F), null);
            this.addAttribute(
               this.leftleg,
               new AttributeArray(FRAME0, -1.0F, FRAME1, -0.3F),
               new AttributeArray(FRAME0, 0.5F, FRAME1, 0.0F),
               new AttributeArray(FRAME0, 0.001F, FRAME1, -0.06F)
            );
            this.addAttribute(this.leftleg2, new AttributeArray(FRAME0, 1.0F, FRAME1, 0.3F), new AttributeArray(FRAME0, 0.001F, FRAME1, 0.0F), null);
            this.addAttribute(this.rightleg, new AttributeArray(FRAME0, -1.0F, FRAME1, -0.3F), new AttributeArray(FRAME0, 0.8F, FRAME1, 0.4F), null);
            this.addAttribute(this.rightleg2, new AttributeArray(FRAME0, 1.0F, FRAME1, 0.25F), new AttributeArray(FRAME0, 0.001F), null);
            return;
         }
      }
   }
}
