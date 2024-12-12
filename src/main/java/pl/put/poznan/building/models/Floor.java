package pl.put.poznan.building.models;

import pl.put.poznan.building.models.Localization;
import pl.put.poznan.building.models.Room;

import java.util.ArrayList;
import java.util.List;

public class Floor extends Localization {
    private List<Room> rooms;

    public Floor(String id, String name) {
        super(id, name);
        this.rooms = new ArrayList<>();
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }
    public void removeRoom(Room room){rooms.remove(room);} //Do modyfikacji pokoju z poziomu PUT requesta

    @Override
    public float getArea() {
        float totalArea = 0.0f;
        for (Room room : rooms) {
            totalArea += room.getArea();
        }
        return totalArea;
    }

    @Override
    public float getCube() {
        float totalVolume = 0.0f;
        for (Room room : rooms) {
            totalVolume += room.getCube();
        }
        return totalVolume;
    }

    @Override
    public float getLight() {
        float totalLight = 0.0f;
        for (Room room : rooms) {
            totalLight += room.getLight();
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
        for (Room room : rooms) {
            totalHeating += room.getHeating();
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
        return super.toString() + " Floor{rooms=" + rooms + '}';
    }
}
