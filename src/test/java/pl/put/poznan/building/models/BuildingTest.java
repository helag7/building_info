package pl.put.poznan.building.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BuildingTest {
    private Building building;
    private Floor floor1;
    private Floor floor2;
    private Room room1;
    private Room room2;

    @BeforeEach
    void setUp() {
        // Inicjalizacja testowych obiektów
        building = new Building("building1", "Building 1");

        room1 = new Room("room1", "Room 1", 20.0f, 50.0f, 100.0f, 50.0f);
        room2 = new Room("room2", "Room 2", 30.0f, 70.0f, 150.0f, 70.0f);

        floor1 = new Floor("floor1", "Floor 1");
        floor1.addRoom(room1);

        floor2 = new Floor("floor2", "Floor 2");
        floor2.addRoom(room2);

        building.addFloor(floor1);
        building.addFloor(floor2);
    }

    @Test
    void testAddFloor() {
        Floor newFloor = new Floor("floor3", "Floor 3");
        building.addFloor(newFloor);

        List<Floor> floors = building.getFloors();
        assertEquals(3, floors.size());
        assertTrue(floors.contains(newFloor));
    }

    @Test
    void testRemoveFloor() {
        building.removeFloor(floor1);

        List<Floor> floors = building.getFloors();
        assertEquals(1, floors.size());
        assertFalse(floors.contains(floor1));
    }

    @Test
    void testGetArea() {
        float totalArea = building.getArea();
        assertEquals(50.0f, totalArea, 0.001f);
    }

    @Test
    void testGetCube() {
        float totalCube = building.getCube();
        assertEquals(120.0f, totalCube, 0.001f);
    }

    @Test
    void testGetLight() {
        float totalLight = building.getLight();
        assertEquals(120.0f, totalLight, 0.001f);
    }

    @Test
    void testCalLight() {
        float lightPerArea = building.calLight();
        assertEquals(2.4f, lightPerArea, 0.001f);
    }

    @Test
    void testGetHeating() {
        float totalHeating = building.getHeating();
        assertEquals(250.0f, totalHeating, 0.001f);
    }

    @Test
    void testCalHeating() {
        float heatingPerCube = building.calHeating();
        assertEquals(2.083f, heatingPerCube, 0.001f);
    }

    @Test
    void testToString() {
        String expected = "Localization{id='building1', name='Building 1'} Building{floors=[Localization{id='floor1', name='Floor 1'} Floor{rooms=[Localization{id='room1', name='Room 1'} Room{area=20.0, cube=50.0, heating=100.0, light=50.0}]}, Localization{id='floor2', name='Floor 2'} Floor{rooms=[Localization{id='room2', name='Room 2'} Room{area=30.0, cube=70.0, heating=150.0, light=70.0}]}]}";
        assertEquals(expected, building.toString());
    }
}