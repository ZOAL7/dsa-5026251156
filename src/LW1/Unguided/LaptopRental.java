package LW1.Unguided;

public class LaptopRental extends Rental {
    public LaptopRental(String id, int days) {
        super(id, days);
    }
    public int calculateCharge() {
      return (getDays() * 40000) + 10000;
   }
   public String label() {
      return "Laptop";
   }
}
