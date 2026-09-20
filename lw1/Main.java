import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
   public Main() {
   }

   public static void main(String[] var0) {
      ArrayList var1 = new ArrayList();

      try {
         Scanner var2 = new Scanner(new File("jobs.txt"));

         try {
            while(var2.hasNext()) {
               String var3 = var2.next();
               String var4 = var2.next();
               int var5 = var2.nextInt();
               if (var3.equals("MONO")) {
                  var1.add(new MonoPrint(var4, var5));
               } else {
                  if (!var3.equals("COLOUR")) {
                     throw new IllegalArgumentException("Unknown job type: " + var3);
                  }

                  var1.add(new ColourPrint(var4, var5));
               }
            }
         } catch (Throwable var7) {
            try {
               var2.close();
            } catch (Throwable var6) {
               var7.addSuppressed(var6);
            }

            throw var7;
         }

         var2.close();
      } catch (FileNotFoundException var8) {
         System.err.println("Cannot find jobs.txt in the working directory.");
         return;
      }

      for(PrintJob var10 : var1) {
         System.out.println(var10.summary());
      }

   }
}