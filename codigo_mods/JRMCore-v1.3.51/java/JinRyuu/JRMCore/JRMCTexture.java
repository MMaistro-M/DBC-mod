package JinRyuu.JRMCore;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.resources.IResourceManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SideOnly(Side.CLIENT)
public class JRMCTexture extends AbstractTexture {
   private static final Logger logger = LogManager.getLogger();
   protected final String textureLocation;

   public JRMCTexture(String p_i1275_1_) {
      this.textureLocation = p_i1275_1_;
   }

   public void func_110551_a(IResourceManager p_110551_1_) throws IOException {
      this.func_147631_c();
      BufferedImage bufferedimage = ImageIO.read(new URL(this.textureLocation));
      boolean flag = false;
      boolean flag1 = false;
      TextureUtil.func_110989_a(this.func_110552_b(), bufferedimage, flag, flag1);
   }
}
