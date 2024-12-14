package pl.put.poznan.building.models;

import pl.put.poznan.building.models.Localization;
import pl.put.poznan.building.models.Room;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents Floor objects.
 * Each floor object has id, name and a list of Rooms.
 * The class implements methods to calculate various properties of the floor,
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
public class Floor extends Localization {
    /**
     * A dynamic list of rooms of the Floor.
     */
    private List<Room> rooms;

    /**
     * Constructor - to create floor object.
     * @param id unique (within the building) identifier of the floor
     * @param name name of the floor
     */
    public Floor(String id, String name) {
        super(id, name);
        this.rooms = new ArrayList<>();
    }

    public List<Room> getRooms() {
        return rooms;
    }

    /**
     * Add new room to the list of rooms
     * @param room room object to be added
     */
    public void addRoom(Room room) {
        rooms.add(room);
    }

    /**
     * Remove specified room object
     * @param room - the room object to be removed
     */
    public void removeRoom(Room room){rooms.remove(room);} //Do modyfikacji pokoju z poziomu PUT requesta

    /**
     * Calculates the area of the floor - sum of area for all rooms
     * @return returns calculated area of the floor
     */
    @Override
    public float getArea() {
        float totalArea = 0.0f;
        for (Room room : rooms) {
            totalArea += room.getArea();
        }
        return totalArea;
    }

    /**
     * Calculates the cube of the floor - as sum of cubes for all rooms.
     * @return returns calculated cube
     */
    @Override
    public float getCube() {
        float totalVolume = 0.0f;
        for (Room room : rooms) {
            totalVolume += room.getCube();
        }
        return totalVolume;
    }

    /**
     * Calculates total light power for the floor - as sum of light power for all rooms.
     * @return the calculated light power
     */
    @Override
    public float getLight() {
        float totalLight = 0.0f;
        for (Room room : rooms) {
            totalLight += room.getLight();
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
     * The total energy needed for heating for the floor - sum for all the rooms.
     * @return the calculated energy.
     */
    @Override
    public float getHeating() {
        float totalHeating = 0.0f;
        for (Room room : rooms) {
            totalHeating += room.getHeating();
        }
        return  totalHeating;
    }
    /**
     * Calculates energy consumption for heating per unit volume.
     * @return the calculated energy consumption for heating per unit volume for the floor.
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
     * Creates a string representation of the floor object.
     * @return the string representing the floor object.
     */
    @Override
    public String toString() {
        return super.toString() + " Floor{rooms=" + rooms + '}';
    }
}
