package pl.put.poznan.building.models;

import java.util.ArrayList;
import java.util.List;

public class Building extends Localization {
    private List<Floor> floors;

    public Building(String id, String name) {
        super(id, name); // wywołujemy konstruktor klasy nadrzędnej - Localization
        this.floors = new ArrayList<>(); // inicjalizujemy listę pienter jako dynamiczną listę
    }

    public List<Floor> getFloors() {
        return floors;
    }

    public void addFloor(Floor floor) {
        floors.add(floor);
    }
    public void removeFloor(Floor floor){ floors.remove(floor); } //żeby można było zmodyfikować piętro z poziomu PUT requesta

    @Override
    public float getArea() {
        float totalArea = 0.0f;
        for (Floor floor : floors) {
            totalArea += floor.getArea();
        }
        return totalArea;
    }

    @Override
    public float getCube() {
        float totalVolume = 0.0f;
        for (Floor floor : floors) {
            totalVolume += floor.getCube();
        }
        return totalVolume;
    }

    @Override
    public float getLight() {
        float totalLight = 0.0f;
        for (Floor floor : floors) {
            totalLight += floor.getLight();
        }
        return totalLight;
    }

    @Override
    public float calLight() {
        float area = this.getArea();
        if (area == 0.0f) {
            return 0.0f;
        }
        return this.getLight() / area;
    }



    @Override
    public float getHeating() {
        float totalHeating = 0.0f;
        for (Floor floor : floors) {
            totalHeating += floor.getHeating();
        }
        return  totalHeating;
    }
    @Override
    public float calHeating() {
        float cube = this.getCube();
        if (cube == 0.0f) {
            return 0.0f;
        }
        return this.getHeating() / cube;
    }


    @Override
    public String toString() {
        return super.toString() + " Building{" + "floors=" + floors + '}';
    }
}
