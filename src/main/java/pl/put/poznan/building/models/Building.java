package pl.put.poznan.building.models;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents Building objects.
 * Each building object has id, name and a list of floors.
 * The class implements methods to calculate various properties of the building,
 * such as area, cube (volume), light power, energy consumption for heating.
 *
 * The class extends the {@code Localization} class and inherits its fields:
 * {@code id} and {@code name}.
 *
 * The class overrides methods from {@code Localization} class:
 * {@code getArea()}, {@code @getCube()}, {@code getLight()}, {@code getHeating()},
 * {@code calLight()}, {@code calHeating()}, {@code toString()}.
 *
 * @author s2lw
 * @since 1.0
 *
 */

public class Building extends Localization {

    /**
     * A dynamic list of floors of the building.
     *
     */
    private List<Floor> floors;

    /**
     * Constructor - to create building object.
     * @param id unique identifier of the building
     * @param name name of the building
     */
    public Building(String id, String name) {
        super(id, name); // wywołujemy konstruktor klasy nadrzędnej - Localization
        this.floors = new ArrayList<>(); // inicjalizujemy listę pienter jako dynamiczną listę
    }

    public List<Floor> getFloors() {
        return floors;
    }

    /**
     * Add new floor to the list of floors
     * @param floor floor object to be added
     */
    public void addFloor(Floor floor) {
        floors.add(floor);
    }

    /**
     * Remove specified floor object
     * @param floor - the floor object to be removed
     */
    public void removeFloor(Floor floor){ floors.remove(floor); } //żeby można było zmodyfikować piętro z poziomu PUT requesta

    /**
     * Calculates the area of the building - sum of area for all floors
     * @return returns calculated area of the building
     */
    @Override
    public float getArea() {
        float totalArea = 0.0f;
        for (Floor floor : floors) {
            totalArea += floor.getArea();
        }
        return totalArea;
    }

    /**
     * Calculates the cube of the building - as sum of cubes for all floors.
     * @return returns calculated cube
     */
    @Override
    public float getCube() {
        float totalVolume = 0.0f;
        for (Floor floor : floors) {
            totalVolume += floor.getCube();
        }
        return totalVolume;
    }

    /**
     * Calculates total light power for the building - as sum of light power for all floors.
     * @return the calculated light power
     */
    @Override
    public float getLight() {
        float totalLight = 0.0f;
        for (Floor floor : floors) {
            totalLight += floor.getLight();
        }
        return totalLight;
    }

    /**
     * Calculates the light power per surface area.
     * @return the calculated light power per surface area
     */
    @Override
    public float calLight() {
        float area = this.getArea();
        if (area == 0.0f) {
            return 0.0f;
        }
        return this.getLight() / area;
    }

    /**
     * The total energy needed for heating for the building - sum for all the floors.
     * @return the calculated energy.
     */

    @Override
    public float getHeating() {
        float totalHeating = 0.0f;
        for (Floor floor : floors) {
            totalHeating += floor.getHeating();
        }
        return  totalHeating;
    }

    /**
     * Calculates energy consumption for heating per unit volume.
     * @return the calculated energy consumption for heating per unit volume for the building.
     */
    @Override
    public float calHeating() {
        float cube = this.getCube();
        if (cube == 0.0f) {
            return 0.0f;
        }
        return this.getHeating() / cube;
    }

    /**
     * Creates a string representation of the building object.
     * @return the string representing the building object.
     */
    @Override
    public String toString() {
        return super.toString() + " Building{" + "floors=" + floors + '}';
    }
}
