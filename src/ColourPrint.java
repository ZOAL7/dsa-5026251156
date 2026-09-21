public class ColourPrint extends PrintJob {
   private static final int FULL_RATE_PAGES = 10;
   private static final int FULL_RATE = 1500;
   private static final int REDUCED_RATE = 1000;
   private static final int SETUP_FEE = 2000;

   public ColourPrint(String var1, int var2) {
      super(var1, var2);
   }

   public int calculateCharge() {
      int var1 = this.getPages();
      int var2 = Math.min(var1, 10);
      int var3 = var1 - var2;
      return var2 * 1500 + var3 * 1000 + 2000;
   }

   public String label() {
      return "Colour";
   }
}
