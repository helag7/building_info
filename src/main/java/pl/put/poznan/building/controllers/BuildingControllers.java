package pl.put.poznan.building.controllers;

import pl.put.poznan.building.models.Building;
import pl.put.poznan.building.models.Floor;
import pl.put.poznan.building.models.Room;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BuildingControllers {

    @GetMapping("/building/{id}")
    public Building getBuilding(@PathVariable String id) {
        return createBuilding(id);
    }

    @GetMapping("/building/{id}/area")
    public float getBuildingArea(@PathVariable String id) {
        Building building = createBuilding(id);
        return building.getArea();
    }

    @GetMapping("/building/{id}/cube")
    public float getBuildingCube(@PathVariable String id) {
        Building building = createBuilding(id);
        return building.getCube();
    }

    @GetMapping("/building/{id}/heating")
    public float getBuildingHeating(@PathVariable String id) {
        Building building = createBuilding(id);
        return building.getHeating();
    }

    @GetMapping("/building/{id}/light")
    public float getBuildingLight(@PathVariable String id) {
        Building building = createBuilding(id);
        return building.getLight();
    }

    @GetMapping("/building/{id}/light-per-area")
    public float getBuildingLightPerArea(@PathVariable String id) {
        Building building = createBuilding(id);
        return building.calLight();
    }

    @GetMapping("/building/{id}/heating-per-cube")
    public float getBuildingHeatingPerCube(@PathVariable String id) {
        Building building = createBuilding(id);
        return building.calHeating();
    }

    @GetMapping("/building/{id}/floors")
    public List<Floor> getBuildingFloors(@PathVariable String id) {
        Building building = createBuilding(id);
        return building.getFloors();
    }

    @GetMapping("/building/{id}/floor/{floorId}")
    public Floor getFloor(@PathVariable String id, @PathVariable String floorId) {
        Building building = createBuilding(id);
        return building.getFloors().stream()
                .filter(floor -> floor.getId().equals(floorId))
                .findFirst()
                .orElse(null);
    }

    @GetMapping("/building/{id}/floor/{floorId}/rooms")
    public List<Room> getFloorRooms(@PathVariable String id, @PathVariable String floorId) {
        Floor floor = getFloor(id, floorId);
        return (floor != null) ? floor.getRooms() : new ArrayList<>();
    }

    @GetMapping("/building/{id}/floor/{floorId}/room/{roomId}")
    public Room getRoom(@PathVariable String id, @PathVariable String floorId, @PathVariable String roomId) {
        Floor floor = getFloor(id, floorId);
        if (floor != null) {
            return floor.getRooms().stream()
                    .filter(room -> room.getId().equals(roomId))
                    .findFirst()
                    .orElse(null);
        }
        return null;
    }

    private Building createBuilding(String id) {
        // Tworzymy budynek i jego elementy (piętra i pokoje)
        Building building = new Building(id, "Example Building");

        Floor floor1 = new Floor("1", "Ground Floor");
        floor1.addRoom(new Room("101", "Room 101", 25.0f, 60.0f, 500.0f, 150.0f));
        floor1.addRoom(new Room("102", "Room 102", 30.0f, 70.0f, 550.0f, 160.0f));

        Floor floor2 = new Floor("2", "First Floor");
        floor2.addRoom(new Room("201", "Room 201", 28.0f, 65.0f, 520.0f, 155.0f));
        floor2.addRoom(new Room("202", "Room 202", 35.0f, 80.0f, 600.0f, 170.0f));

        building.addFloor(floor1);
        building.addFloor(floor2);

        return building;
    }
}
