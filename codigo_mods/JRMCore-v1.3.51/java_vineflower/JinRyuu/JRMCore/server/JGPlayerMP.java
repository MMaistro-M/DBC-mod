package JinRyuu.JRMCore.server;

import JinRyuu.JRMCore.JRMCoreH;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;

public class JGPlayerMP {
   public EntityPlayer player;
   private NBTTagCompound nbt;

   public JGPlayerMP(EntityPlayer player) {
      this.player = player;
   }

   public JGPlayerMP(EntityPlayerMP player) {
      this.player = player;
   }

   public NBTTagCompound getNBT() {
      return this.nbt;
   }

   public void setNBT(NBTTagCompound nbt) {
      this.nbt = nbt;
   }

   public NBTTagCompound connectBaseNBT() {
      return this.nbt = JRMCoreH.nbt(this.player, "pres");
   }

   public int[] getAttributes() {
      return JRMCoreH.PlyrAttrbts(this.player);
   }

   public String[] getSkills() {
      return JRMCoreH.PlyrSkills(this.player);
   }

   public String getSkillsX() {
      return this.nbt.func_74779_i("jrmcSSltX");
   }

   public String getSkillsY() {
      return this.nbt.func_74779_i("jrmcSSltY");
   }

   public int getLevel(int[] playerAttributes) {
      return JRMCoreH.getPlayerLevel(playerAttributes);
   }

   public byte getRace() {
      return this.nbt.func_74771_c("jrmcRace");
   }

   public byte getClassID() {
      return this.nbt.func_74771_c("jrmcClass");
   }

   public byte getPowerType() {
      return this.nbt.func_74771_c("jrmcPwrtyp");
   }

   public int getAlignment() {
      return this.nbt.func_74771_c("jrmcAlign");
   }

   public byte getState() {
      return this.nbt.func_74771_c("jrmcState");
   }

   public void setState(int value) {
      this.nbt.func_74774_a("jrmcState", (byte)value);
   }

   public byte getState2() {
      return this.nbt.func_74771_c("jrmcState2");
   }

   public void setState2(int value) {
      this.nbt.func_74774_a("jrmcState2", (byte)value);
   }

   public byte getRelease() {
      return this.nbt.func_74771_c("jrmcRelease");
   }

   public void setRelease(int value) {
      this.nbt.func_74774_a("jrmcRelease", (byte)value);
   }

   public int getReserve() {
      return this.nbt.func_74762_e("jrmcArcRsrv");
   }

   public void setReserve(int value) {
      this.nbt.func_74768_a("jrmcArcRsrv", value);
   }

   public String getAbsorption() {
      return this.nbt.func_74779_i("jrmcMajinAbsorptionData");
   }

   public void setAbsorption(String value) {
      this.nbt.func_74778_a("jrmcMajinAbsorptionData", value);
   }

   public int getTransformationMeter() {
      return this.nbt.func_74771_c("jrmcSaiRg");
   }

   public void setTransformationMeter(int value) {
      this.nbt.func_74774_a("jrmcSaiRg", (byte)value);
   }

   public void setStateAndTransformationMeter(int valueSt, int valueTr) {
      this.setState(valueSt);
      this.setTransformationMeter(valueTr);
   }

   public int getStat(byte race, byte classID, int attributeID, int stat, byte powerType, int attribute) {
      return this.getStat(race, classID, attributeID, stat, powerType, attribute, 0.0F);
   }

   public String getStatusEffects() {
      return this.nbt.func_74779_i("jrmcStatusEff");
   }

   public boolean hasStatusEffect(int statusEffectID, String statusEffects) {
      return JRMCoreH.StusEfcts(statusEffectID, statusEffects);
   }

   public String getPlayerSettings() {
      return this.nbt.func_74779_i("jrmcSettings");
   }

   public int getStat(byte race, byte classID, int attributeID, int stat, byte powerType, int[] playerAttributes) {
      return this.getStat(race, classID, attributeID, stat, powerType, playerAttributes[attributeID], 0.0F);
   }

   public int getStat(byte race, byte classID, int attributeID, int stat, byte powerType, int[] playerAttributes, float skillBonus) {
      return this.getStat(race, classID, attributeID, stat, powerType, playerAttributes[attributeID], skillBonus);
   }

   public int getStat(byte race, byte classID, int attributeID, int stat, byte powerType, int attribute, float skillBonus) {
      return JRMCoreH.stat(this.player, attributeID, powerType, stat, attribute, race, classID, skillBonus);
   }

   public int getHealth() {
      return JRMCoreH.getInt(this.player, "jrmcBdy");
   }

   public int getHealthMax(byte race, byte classID, byte powerType, int[] playerAttributes) {
      return this.getStat(race, classID, 2, 2, powerType, playerAttributes);
   }

   public int getHealthRegen(byte race, byte classID, byte powerType) {
      return this.getStat(race, classID, -1, 8, powerType, 100);
   }

   public int getEnergy() {
      return JRMCoreH.getInt(this.player, "jrmcEnrgy");
   }

   public int getEnergyMax(byte race, byte classID, byte powerType, int[] playerAttributes, float skillBonus) {
      return this.getStat(race, classID, 5, 5, powerType, playerAttributes, skillBonus);
   }

   public int getEnergyRegen(byte race, byte classID, byte powerType) {
      return this.getStat(race, classID, -1, 10, powerType, 100);
   }

   public int getStamina() {
      return JRMCoreH.getInt(this.player, "jrmcStamina");
   }

   public int getStaminaMax(byte race, byte classID, byte powerType, int[] playerAttributes) {
      return this.getStat(race, classID, 2, 3, powerType, playerAttributes);
   }

   public int getStaminaRegen(byte race, byte classID, byte powerType) {
      return this.getStat(race, classID, -1, 9, powerType, 100);
   }

   public int getEnergyPower() {
      return this.getEnergyPower(this.getRace(), this.getClassID(), this.getPowerType());
   }

   public int getEnergyPower(byte race, byte classID, byte powerType) {
      return this.getStat(race, classID, 3, 4, powerType, this.getAttribute(3), 0.0F);
   }

   public int getAttribute(int attributeID) {
      int powerType = this.getPowerType();
      int race = this.getRace();
      int state = this.getState();
      int state2 = this.getState2();
      int classID = this.getClassID();
      byte release = this.getRelease();
      int reserve = this.getReserve();
      String absorption = this.getAbsorption();
      int[] attributes = this.getAttributes();
      String skillsX = this.getSkillsX();
      String[] skills = this.getSkills();
      String statusEffects = this.getStatusEffects();
      boolean hasLegendary = this.hasStatusEffect(14, statusEffects);
      boolean hasMajin = this.hasStatusEffect(12, statusEffects);
      boolean hasKaioken = this.hasStatusEffect(5, statusEffects);
      boolean hasMystic = this.hasStatusEffect(13, statusEffects);
      boolean hasUltraInstinct = this.hasStatusEffect(19, statusEffects);
      boolean hasGodOfDestruction = this.hasStatusEffect(20, statusEffects);
      boolean hasFusion = this.hasStatusEffect(10, statusEffects) || this.hasStatusEffect(11, statusEffects);
      return JRMCoreH.getPlayerAttribute(
         this.player,
         attributes,
         attributeID,
         state,
         state2,
         race,
         skillsX,
         release,
         reserve,
         hasLegendary,
         hasMajin,
         hasKaioken,
         hasMystic,
         hasUltraInstinct,
         hasGodOfDestruction,
         powerType,
         skills,
         hasFusion,
         absorption
      );
   }
}
