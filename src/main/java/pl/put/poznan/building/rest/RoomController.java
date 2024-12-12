package pl.put.poznan.building.rest;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.building.logic.BuildingInfo;
import pl.put.poznan.building.models.Building;
import pl.put.poznan.building.models.Floor;
import pl.put.poznan.building.models.Room;

import java.util.List;

@RestController
@RequestMapping("/buildings")
public class RoomController {

    private static final Logger logger = LoggerFactory.getLogger(RoomController.class);

    private final BuildingInfo buildingInfo;

    //Bean injection:
    @Autowired
    public RoomController(BuildingInfo buildingInfo){
        this.buildingInfo = buildingInfo;
    }

    @PostMapping("{bid}/floor/{fid}/addRoom")
    public Room addRoom(@PathVariable String bid, @PathVariable String fid, @RequestBody Room room){
        logger.info("Received request to add building to building {} floor {}.", bid, fid);
        Building building = buildingInfo.findBuilding(bid);
        Floor floor = buildingInfo.findFloor(building, fid);
        floor.addRoom(room);
        logger.info("Room added successfully: {}", room);
        return room;
    }

    @GetMapping("/{id}/floor/{floorId}/rooms")
    public List<Room> getFloorRooms(@PathVariable String id, @PathVariable String floorId) {
        Building building = buildingInfo.findBuilding(id);
        Floor floor = buildingInfo.findFloor(building, floorId);
        return floor.getRooms();
    }

    @GetMapping("/{id}/floor/{floorId}/room/{roomId}")
    public Room getRoom(@PathVariable String id, @PathVariable String floorId, @PathVariable String roomId) {
        Building building = buildingInfo.findBuilding(id);
        Floor floor = buildingInfo.findFloor(building, floorId);
        return buildingInfo.findRoom(id, floorId, roomId);
    }
    @GetMapping("/{id}/floor/{floorId}/room/{roomId}/area")
    public float getRoomArea(@PathVariable String id, @PathVariable String floorId, @PathVariable String roomId) {
        Building building = buildingInfo.findBuilding(id);
        Floor floor = buildingInfo.findFloor(building, floorId);
        Room room =  buildingInfo.findRoom(id, floorId, roomId);
        return room.getArea();
    }
    @GetMapping("/{id}/floor/{floorId}/room/{roomId}/cube")
    public float getRoomCube(@PathVariable String id, @PathVariable String floorId, @PathVariable String roomId) {
        Building building = buildingInfo.findBuilding(id);
        Floor floor = buildingInfo.findFloor(building, floorId);
        Room room =  buildingInfo.findRoom(id, floorId, roomId);
        return room.getCube();
    }
    @GetMapping("/{id}/floor/{floorId}/room/{roomId}/heating")
    public float getRoomHeating(@PathVariable String id, @PathVariable String floorId, @PathVariable String roomId) {
        Building building = buildingInfo.findBuilding(id);
        Floor floor = buildingInfo.findFloor(building, floorId);
        Room room =  buildingInfo.findRoom(id, floorId, roomId);
        return room.getHeating();
    }
    @GetMapping("/{id}/floor/{floorId}/room/{roomId}/light")
    public float getRoomLight(@PathVariable String id, @PathVariable String floorId, @PathVariable String roomId) {
        Building building = buildingInfo.findBuilding(id);
        Floor floor = buildingInfo.findFloor(building, floorId);
        Room room =  buildingInfo.findRoom(id, floorId, roomId);
        return room.getLight();
    }

    @GetMapping("/{id}/floor/{floorId}/room/{roomId}/light-per-area")
    public float getRoomLightPerArea(@PathVariable String id, @PathVariable String floorId, @PathVariable String roomId) {
        Building building = buildingInfo.findBuilding(id);
        Floor floor = buildingInfo.findFloor(building, floorId);
        Room room = buildingInfo.findRoom(id, floorId, roomId);
        return room.calLight();
    }

    @GetMapping("/{id}/floor/{floorId}/room/{roomId}/heating-per-cube")
    public float getRoomHeatingPerCube(@PathVariable String id, @PathVariable String floorId, @PathVariable String roomId) {
        Building building = buildingInfo.findBuilding(id);
        Floor floor = buildingInfo.findFloor(building, floorId);
        Room room = buildingInfo.findRoom(id, floorId, roomId);
        return room.calHeating();
    }

    @PutMapping("{bid}/floor/{fid}/room/{rid}/update")
    public Room updateRoom(@PathVariable String bid, @PathVariable String fid, @PathVariable String rid, @RequestBody Room updatedRoom){
        return buildingInfo.updateRoom(bid, fid, updatedRoom, rid);
    }

}
