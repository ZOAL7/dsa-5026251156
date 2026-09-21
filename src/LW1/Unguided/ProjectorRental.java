package LW1.Unguided;

public class ProjectorRental extends Rental {
    public ProjectorRental(String id, int days) {
        super(id, days);
    }
    public int calculateCharge() {
      if (getDays() <= 3) {
         return getDays() * 45000;
        } else {
            return (3 * 45000) + ((getDays() - 3) * 20000);
    }
}
    public String label() {
      return "Projector"; 
    }
}
