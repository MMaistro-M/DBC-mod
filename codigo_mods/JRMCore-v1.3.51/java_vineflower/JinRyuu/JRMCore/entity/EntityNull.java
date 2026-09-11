package JinRyuu.JRMCore.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLiving;
import net.minecraft.world.World;

@SideOnly(Side.CLIENT)
public class EntityNull extends EntityLiving {
   public EntityNull(World w) {
      super(w);
   }
}
