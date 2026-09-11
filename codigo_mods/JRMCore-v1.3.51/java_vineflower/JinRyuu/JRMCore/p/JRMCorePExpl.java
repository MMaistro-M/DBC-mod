package JinRyuu.JRMCore.p;

import JinRyuu.JRMCore.JRMCoreClient;
import JinRyuu.JRMCore.JRMCoreConfig;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class JRMCorePExpl implements IMessage {
   public double explosionX;
   public double explosionY;
   public double explosionZ;
   public float explosionSize;
   public List chunkPositionRecords;
   public float playerVelocityX;
   public float playerVelocityY;
   public float playerVelocityZ;
   public boolean expGriOff;
   public double expDam;
   public Entity origin;
   public byte type;
   int plID;

   public JRMCorePExpl() {
   }

   public JRMCorePExpl(
      double explosionX,
      double explosionY,
      double explosionZ,
      float explosionSize,
      boolean expGriOff,
      double expDam,
      Entity origin,
      List chunkPositionRecords,
      float playerVelocityX,
      float playerVelocityY,
      float playerVelocityZ,
      byte type
   ) {
      this.explosionX = explosionX;
      this.explosionY = explosionY;
      this.explosionZ = explosionZ;
      this.explosionSize = explosionSize;
      this.expGriOff = expGriOff;
      this.expDam = expDam;
      this.origin = origin;
      this.chunkPositionRecords = chunkPositionRecords;
      this.playerVelocityX = playerVelocityX;
      this.playerVelocityY = playerVelocityY;
      this.playerVelocityZ = playerVelocityZ;
      this.type = type;
   }

   public void toBytes(ByteBuf buffer) {
      buffer.writeDouble(this.explosionX);
      buffer.writeDouble(this.explosionY);
      buffer.writeDouble(this.explosionZ);
      buffer.writeFloat(this.explosionSize);
      buffer.writeBoolean(JRMCoreConfig.expGriOff);
      buffer.writeDouble(this.expDam);
      buffer.writeInt(this.origin == null ? 0 : this.origin.func_145782_y());
      buffer.writeInt(this.chunkPositionRecords.size());
      int var2 = (int)this.explosionX;
      int var3 = (int)this.explosionY;
      int var4 = (int)this.explosionZ;
      Iterator var5 = this.chunkPositionRecords.iterator();
      buffer.writeFloat(this.playerVelocityX);
      buffer.writeFloat(this.playerVelocityY);
      buffer.writeFloat(this.playerVelocityZ);
      buffer.writeByte(this.type);
   }

   public void fromBytes(ByteBuf buffer) {
      this.explosionX = buffer.readDouble();
      this.explosionY = buffer.readDouble();
      this.explosionZ = buffer.readDouble();
      this.explosionSize = buffer.readFloat();
      this.expGriOff = buffer.readBoolean();
      this.expDam = buffer.readDouble();
      this.plID = buffer.readInt();
      int var2 = buffer.readInt();
      this.chunkPositionRecords = new ArrayList(var2);
      int var3 = (int)this.explosionX;
      int var4 = (int)this.explosionY;
      int var5 = (int)this.explosionZ;
      this.playerVelocityX = buffer.readFloat();
      this.playerVelocityY = buffer.readFloat();
      this.playerVelocityZ = buffer.readFloat();
      this.type = buffer.readByte();
   }

   public static class Handler extends BAmh<JRMCorePExpl> {
      public IMessage handleClientMessage(EntityPlayer p, JRMCorePExpl m, MessageContext ctx) {
         m.origin = p.field_70170_p.func_73045_a(m.plID);
         JRMCoreClient.phc
            .handleExpl(
               m.explosionX,
               m.explosionY,
               m.explosionZ,
               m.explosionSize,
               m.expGriOff,
               m.expDam,
               m.origin,
               m.chunkPositionRecords,
               m.playerVelocityX,
               m.playerVelocityY,
               m.playerVelocityZ,
               p,
               m.type
            );
         return null;
      }

      public IMessage handleServerMessage(EntityPlayer p, JRMCorePExpl m, MessageContext ctx) {
         return null;
      }
   }
}
