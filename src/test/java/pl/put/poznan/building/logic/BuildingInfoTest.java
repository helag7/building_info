package pl.put.poznan.building.logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;
import pl.put.poznan.building.models.Building;
import pl.put.poznan.building.models.Floor;
import pl.put.poznan.building.models.Room;

import static org.junit.jupiter.api.Assertions.*;

class BuildingInfoTest {

    private BuildingInfo buildingInfo;

    @BeforeEach
    void setUp() {
        buildingInfo = new BuildingInfo();

        Room room1 = new Room("room1", "Room 1", 20.0f, 50.0f, 10.0f, 5.0f);
        Room room2 = new Room("room2", "Room 2", 30.0f, 70.0f, 15.0f, 7.0f);

        Floor floor1 = new Floor("floor1", "Floor 1");
        floor1.addRoom(room1);
        floor1.addRoom(room2);

        Building building1 = new Building("building1", "Building 1");
        building1.addFloor(floor1);

        buildingInfo.addBuilding(building1);
    }

    @Test
    void findBuilding_buildingExists_returnsId() {
        Building building = buildingInfo.findBuilding("building1");
        assertNotNull(building);
        assertEquals("building1", building.getId());
    }

    @Test
    void findBuilding_buildingDoesNotExist_throwsException() {
        assertThrows(ResponseStatusException.class, () -> buildingInfo.findBuilding("fakeId"));
    }

    @Test
    void findFloor_floorExists_returnsId() {
        Floor floor = buildingInfo.findFloor("building1", "floor1");
        assertNotNull(floor);
        assertEquals("floor1", floor.getId());
    }

    @Test
    void findFloor_floorDoesNotExist_throwsException() {
        assertThrows(ResponseStatusException.class, () -> buildingInfo.findFloor("building1", "nonexistent"));
    }

    @Test
    void findRoom_roomExists_returnsId() {
        Room room = buildingInfo.findRoom("building1", "floor1", "room1");
        assertNotNull(room);
        assertEquals("room1", room.getId());
    }

    @Test
    void findRoom_roomDoesNotExist_throwsException() {
        assertThrows(ResponseStatusException.class, () -> buildingInfo.findRoom("building1", "floor1", "nonexistent"));
    }

    @Test
    void addBuilding_shouldAddBuilding_returnsId() {
        Building newBuilding = new Building("building2", "Building 2");
        buildingInfo.addBuilding(newBuilding);

        Building retrievedBuilding = buildingInfo.findBuilding("building2");
        assertNotNull(retrievedBuilding);
        assertEquals("building2", retrievedBuilding.getId());
    }

    @Test
    void updateBuilding_buildingExists_returnsId() {
        Building updatedBuilding = new Building("building1", "Updated Building 1");
        Building result = buildingInfo.updateBuilding(updatedBuilding, "building1");

        assertEquals(updatedBuilding, result);
        assertEquals("building1", result.getId());
    }

    @Test
    void updateBuilding_buildingDoesNotExist_throwsException() {
        Building updatedBuilding = new Building("fakeBuilding", "Fake Building");
        assertThrows(ResponseStatusException.class, () -> buildingInfo.updateBuilding(updatedBuilding, "fakeBuilding"));
    }

    @Test
    void updateFloor_floorExists_returnsId() {
        Floor updatedFloor = new Floor("floor1", "Updated Floor 1");
        updatedFloor.addRoom(new Room("room3", "Room 3", 40.0f, 100.0f, 20.0f, 10.0f));
        Floor result = buildingInfo.updateFloor("building1", updatedFloor, "floor1");

        assertEquals(updatedFloor, result);
        assertEquals("floor1", result.getId());
    }

    @Test
    void updateRoom_roomExists_returnsId() {
        Room updatedRoom = new Room("room1", "Updated Room 1", 50.0f, 120.0f, 25.0f, 12.0f);
        Room result = buildingInfo.updateRoom("building1", "floor1", updatedRoom, "room1");

        assertEquals(updatedRoom, result);
        assertEquals("room1", result.getId());
    }
}