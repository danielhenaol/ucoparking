package co.edu.uco.ucoparking.domain.model;

public class Vehicle {

    private String plate;

    public Vehicle(String plate) {
        setPlate(plate);
    }

    public String getPlate() {
        return plate;
    }

    private void setPlate(String plate) {
        if (plate == null || plate.trim().isEmpty()) {
            throw new IllegalArgumentException("La placa del vehículo es obligatoria.");
        }

        this.plate = plate.trim().toUpperCase();
    }
}