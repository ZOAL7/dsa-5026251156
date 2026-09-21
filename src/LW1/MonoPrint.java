package LW1;
public class MonoPrint extends PrintJob {
   private static final int RATE_PER_PAGE = 500;

   public MonoPrint(String var1, int var2) {
      super(var1, var2);
   }

   public int calculateCharge() {
      return getPages() * 500;
   }

   public String label() {
      return "Mono";
   }
}
