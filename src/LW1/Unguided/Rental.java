package LW1.Unguided;

import LW1.unguided.Chargeable;

public abstract class Rental implements Chargeable {
    private final String id;
    private final int days;

    protected Rental(String id, int days) {
        if (days <= 0) {
            throw new IllegalArgumentException("days must be positive");
        }
        this.id = id;
        this.days = days;
    }
    public String getId() {
        return this.id;
    }
    public int getDays() {
        return this.days;
    }
    
    public abstract int calculateCharge();

    public int calculateCharge(int units) {
        if (units <= 0) {
            throw new IllegalArgumentException("units must be positive");
        } else {
            return units * this.calculateCharge();
        }
    }

    public String label() {
      return "Print";
   }

    public String summary() {
        String id = this.id;
        return id + " | " + this.label() + " | " + this.calculateCharge();
    }
}