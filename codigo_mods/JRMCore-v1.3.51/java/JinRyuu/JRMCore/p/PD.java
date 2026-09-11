package JinRyuu.JRMCore.p;

import JinRyuu.JRMCore.p.DBC.DBCPascend;
import JinRyuu.JRMCore.p.DBC.DBCPascendsound;
import JinRyuu.JRMCore.p.DBC.DBCPchargepart;
import JinRyuu.JRMCore.p.DBC.DBCPchargesound;
import JinRyuu.JRMCore.p.DBC.DBCPdescend;
import JinRyuu.JRMCore.p.DBC.DBCPdescendsound;
import JinRyuu.JRMCore.p.DBC.DBCPdri;
import JinRyuu.JRMCore.p.DBC.DBCPduo;
import JinRyuu.JRMCore.p.DBC.DBCPenergy;
import JinRyuu.JRMCore.p.DBC.DBCPscouter1;
import JinRyuu.JRMCore.p.DBC.DBCPscouter2;
import JinRyuu.JRMCore.p.DBC.DBCPscouter3;
import JinRyuu.JRMCore.p.DBC.DBCPscouter4;
import JinRyuu.JRMCore.p.DBC.DBCPspacepod1;
import JinRyuu.JRMCore.p.DBC.DBCPtick;
import JinRyuu.JRMCore.p.DBC.DBCPwish;
import JinRyuu.JRMCore.p.NC.NCPData;
import JinRyuu.JRMCore.p.NC.NCPData2;
import JinRyuu.JRMCore.p.NC.NCPDou;
import JinRyuu.JRMCore.p.NC.NCPchargesound;
import JinRyuu.JRMCore.p.NC.NCPjumpsound;
import JinRyuu.JRMCore.p.YC.JYearsCP;
import JinRyuu.JRMCore.p.YC.JYearsCPData;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class PD {
   private static byte packetId = 0;
   private static final SimpleNetworkWrapper dispatcher = NetworkRegistry.INSTANCE.newSimpleChannel("jinryuujrmcore");
   public static Class[] rp = new Class[]{
      JRMCorePData.class,
      JRMCorePAttck.class,
      JRMCorePChar.class,
      JRMCorePCost.class,
      JRMCorePExpl.class,
      JRMCorePFall.class,
      JRMCorePQuad.class,
      JRMCorePQuadI.class,
      JRMCorePRls.class,
      JRMCorePStats.class,
      JRMCorePStats2.class,
      JRMCorePStats3.class,
      JRMCorePStats3b.class,
      JRMCorePTech.class,
      JRMCorePTick.class,
      JRMCorePTri.class,
      JRMCorePTrib.class,
      JRMCorePUpgrade.class,
      SyncPlayerPropsMessage.class,
      OpenGuiMessage.class,
      JRMCorePData2.class,
      JRMCorePData3.class
   };
   public static Class[] rph = new Class[]{
      JRMCorePData.Handler.class,
      JRMCorePAttck.Handler.class,
      JRMCorePChar.Handler.class,
      JRMCorePCost.Handler.class,
      JRMCorePExpl.Handler.class,
      JRMCorePFall.Handler.class,
      JRMCorePQuad.Handler.class,
      JRMCorePQuadI.Handler.class,
      JRMCorePRls.Handler.class,
      JRMCorePStats.Handler.class,
      JRMCorePStats2.Handler.class,
      JRMCorePStats3.Handler.class,
      JRMCorePStats3b.Handler.class,
      JRMCorePTech.Handler.class,
      JRMCorePTick.Handler.class,
      JRMCorePTri.Handler.class,
      JRMCorePTrib.Handler.class,
      JRMCorePUpgrade.Handler.class,
      SyncPlayerPropsMessage.Handler.class,
      OpenGuiMessage.Handler.class,
      JRMCorePData2.Handler.class,
      JRMCorePData3.Handler.class
   };
   public static Class[] rpDBC = new Class[]{
      DBCPascend.class,
      DBCPascendsound.class,
      DBCPchargepart.class,
      DBCPchargesound.class,
      DBCPdescend.class,
      DBCPdescendsound.class,
      DBCPdri.class,
      DBCPenergy.class,
      DBCPduo.class,
      DBCPscouter1.class,
      DBCPscouter2.class,
      DBCPscouter3.class,
      DBCPscouter4.class,
      DBCPspacepod1.class,
      DBCPtick.class,
      DBCPwish.class
   };
   public static Class[] rphDBC = new Class[]{
      DBCPascend.Handler.class,
      DBCPascendsound.Handler.class,
      DBCPchargepart.Handler.class,
      DBCPchargesound.Handler.class,
      DBCPdescend.Handler.class,
      DBCPdescendsound.Handler.class,
      DBCPdri.Handler.class,
      DBCPenergy.Handler.class,
      DBCPduo.Handler.class,
      DBCPscouter1.Handler.class,
      DBCPscouter2.Handler.class,
      DBCPscouter3.Handler.class,
      DBCPscouter4.Handler.class,
      DBCPspacepod1.Handler.class,
      DBCPtick.Handler.class,
      DBCPwish.Handler.class
   };

   public static void JYCregPac() {
      registerBiMessage(JYearsCP.Handler.class, JYearsCP.class);
      registerBiMessage(JYearsCPData.Handler.class, JYearsCPData.class);
   }

   public static void JFCregPac() {
      registerBiMessage(FamilyCP.Handler.class, FamilyCP.class);
   }

   public static void DBCregPac() {
      for (int i = 0; i < rpDBC.length; i++) {
         registerBiMessage(rphDBC[i], rpDBC[i]);
      }
   }

   public static void NCregPac() {
      registerBiMessage(NCPchargesound.Handler.class, NCPchargesound.class);
      registerBiMessage(NCPData.Handler.class, NCPData.class);
      registerBiMessage(NCPData2.Handler.class, NCPData2.class);
      registerBiMessage(NCPDou.Handler.class, NCPDou.class);
      registerBiMessage(NCPjumpsound.Handler.class, NCPjumpsound.class);
   }

   public static final void registerPackets() {
      JYCregPac();
      JFCregPac();
      DBCregPac();
      NCregPac();

      for (int i = 0; i < rp.length; i++) {
         registerMessage(rph[i], rp[i]);
      }
   }

   private static final <REQ extends IMessage, REPLY extends IMessage> void registerMessage(
      Class<? extends IMessageHandler<REQ, REPLY>> handlerClass, Class<REQ> messageClass, Side side
   ) {
      dispatcher.registerMessage(handlerClass, messageClass, packetId++, side);
   }

   private static final <REQ extends IMessage, REPLY extends IMessage> void registerBiMessage(
      Class<? extends IMessageHandler<REQ, REPLY>> handlerClass, Class<REQ> messageClass
   ) {
      dispatcher.registerMessage(handlerClass, messageClass, packetId, Side.CLIENT);
      dispatcher.registerMessage(handlerClass, messageClass, packetId++, Side.SERVER);
   }

   private static final <REQ extends IMessage> void registerMessage(Class<? extends Amh<REQ>> handlerClass, Class<REQ> messageClass) {
      if (CAmh.class.isAssignableFrom(handlerClass)) {
         registerMessage(handlerClass, messageClass, Side.CLIENT);
      } else if (SAmh.class.isAssignableFrom(handlerClass)) {
         registerMessage(handlerClass, messageClass, Side.SERVER);
      } else {
         if (!BAmh.class.isAssignableFrom(handlerClass)) {
            throw new IllegalArgumentException("Cannot determine on which Side(s) to register " + handlerClass.getName() + " - unrecognized handler class!");
         }

         registerBiMessage(handlerClass, messageClass);
      }
   }

   public static final void sendToAll(IMessage message) {
      dispatcher.sendToAll(message);
   }

   public static final void sendTo(IMessage message, EntityPlayerMP player) {
      dispatcher.sendTo(message, player);
   }

   public static final void sendToAllAround(IMessage message, TargetPoint point) {
      dispatcher.sendToAllAround(message, point);
   }

   public static final void sendToAllAround(IMessage message, int dimension, double x, double y, double z, double range) {
      sendToAllAround(message, new TargetPoint(dimension, x, y, z, range));
   }

   public static final void sendToAllAround(IMessage message, EntityPlayer player, double range) {
      sendToAllAround(message, player.field_70170_p.field_73011_w.field_76574_g, player.field_70165_t, player.field_70163_u, player.field_70161_v, range);
   }

   public static final void sendToDimension(IMessage message, int dimensionId) {
      dispatcher.sendToDimension(message, dimensionId);
   }

   public static final void sendToServer(IMessage message) {
      dispatcher.sendToServer(message);
   }
}
