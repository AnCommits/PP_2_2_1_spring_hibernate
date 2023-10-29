package hiber.model;

public enum CarModel {
    ALFA_24HP("ALFA 24hp"),
    AUDI_TYPE_A("Audi Type A"),
    BENZ_VICTORIA("Benz Victoria"),
    ROBERTS_ELECTRIC("Roberts Electric");

    private final String model;

    CarModel(String model) {
        this.model = model;
    }

    public String getModel() {
        return model;
    }
}
