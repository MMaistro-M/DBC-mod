package JinRyuu.JRMCore;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import net.minecraft.world.World;
import net.minecraft.world.storage.ISaveHandler;
import net.minecraft.world.storage.SaveHandler;

public class JRMCoreSafeUtil {
   public static BufferedReader getReader(File file) throws UnsupportedEncodingException, FileNotFoundException {
      return new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
   }

   public static File getWorldSaveDir(World world) {
      ISaveHandler isavehandler = world.func_72860_G();
      return isavehandler instanceof SaveHandler ? ((SaveHandler)isavehandler).func_75765_b() : null;
   }

   public static BufferedWriter getWriter(File file) throws UnsupportedEncodingException, FileNotFoundException {
      return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF8"));
   }
}
