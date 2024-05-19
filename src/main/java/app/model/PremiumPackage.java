package app.model;

import java.util.ArrayList;

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

    public static ArrayList<String> getPackageNames() {
        ArrayList<String> result = new ArrayList<>();
        for (PremiumPackage premiumPackage : PremiumPackage.values()) {
            result.add(premiumPackage.name().substring(1) + " days | " + premiumPackage.getValue() + "$");
        }
        return result;
    }

    public static PremiumPackage getPackageByName(String description) {
        for (PremiumPackage premiumPackage : PremiumPackage.values()) {
            if (description.contains(premiumPackage.name().substring(1) + " days")) {
                return premiumPackage;
            }
        }
        return null;
    }
}
