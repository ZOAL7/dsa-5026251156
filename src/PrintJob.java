public abstract class PrintJob implements Chargeable {
   private final String id;
   private final int pages;

   protected PrintJob(String var1, int var2) {
      if (var2 <= 0) {
         throw new IllegalArgumentException("pages must be positive");
      } else {
         this.id = var1;
         this.pages = var2;
      }
   }

   public String getId() {
      return this.id;
   }

   public int getPages() {
      return this.pages;
   }

   public abstract int calculateCharge();

   public int calculateCharge(int var1) {
      if (var1 <= 0) {
         throw new IllegalArgumentException("copies must be positive");
      } else {
         return var1 * this.calculateCharge();
      }
   }

   public String label() {
      return "Print";
   }

   public String summary() {
      String var10000 = this.id;
      return var10000 + " | " + this.label() + " | " + this.calculateCharge();
   }
}
