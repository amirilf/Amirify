package app.model;

public enum PremiumPackage {
    D30(5), D60(9), D180(14);

    private double value;

    private PremiumPackage(double value) {
        this.value = value;
    }

    public double getValue() {
        return this.value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
