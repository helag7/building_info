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

/**
 * This class is a REST controller that handles HTTP requests for managing rooms.
 * It provides endpoints for adding, retrieving, updating and calculating properties of rooms.
 *
 * @author helag
 * @version 1.1
 * @since 1.0
 */
@RestController
@RequestMapping("/buildings")
public class RoomController {

    private static final Logger logger = LoggerFactory.getLogger(RoomController.class);

    private final BuildingInfo buildingInfo;

    /**
     * Constructor for the room controller.
     *
     * @param buildingInfo an instance of {@link BuildingInfo} that contains methods
     *                     for managing and retrieving room data.
     */
    @Autowired
    public RoomController(BuildingInfo buildingInfo){
        this.buildingInfo = buildingInfo;
    }
    /**
     * Handles a POST request to add a new room to a specified floor of the specified building.
     *
     * @param bid is the identifier of the building where the room will be added, specified in the request path.
     * @param fid is the identifier of the floor where the room will be added, specified in the request path.
     * @param room the {@link Room} object provided in the request body to be added to the floor of the building.
     * @return the {@link Floor} object that was added to the building.
     */
    @PostMapping("{bid}/floor/{fid}/addRoom")
    public Room addRoom(@PathVariable String bid, @PathVariable String fid, @RequestBody Room room){
        Floor floor = buildingInfo.findFloor(bid, fid);
        floor.addRoom(room);
        return room;
    }
    /**
     * Handles a GET request to retrieve all rooms for a specified floor of the specified building.
     *
     * @param id is the identifier of the building where rooms are to be added, specified in the request path.
     * @param floorId is the identifier of the floor where rooms are to be added, specified in the request path.
     * @return a list of {@link Room} objects for the specified floor of the specified building.
     */
    @GetMapping("/{id}/floor/{floorId}/rooms")
    public List<Room> getFloorRooms(@PathVariable String id, @PathVariable String floorId) {
        Floor floor = buildingInfo.findFloor(id, floorId);
        return floor.getRooms();
    }
    /**
     * Handles a GET request to retrieve a specific room from a floor of a building by its id.
     *
     * @param id is the identifier of the building specified in the request path.
     * @param floorId is the identifier of the floor within the specified building.
     * @param roomId is the identifier of the room within the specified floor.
     * @return  the {@link Room} object with the specified ID, if it exists.
     * Throws an exception if the room or floor or building is not found.
     */
    @GetMapping("/{id}/floor/{floorId}/room/{roomId}")
    public Room getRoom(@PathVariable String id, @PathVariable String floorId, @PathVariable String roomId) {
        Floor floor = buildingInfo.findFloor(id, floorId);
        return buildingInfo.findRoom(id, floorId, roomId);
    }
    /**
     * Handles a GET request to retrieve area of the specific room from a floor by its id.
     *
     * @param id is the identifier of the building specified in the request path.
     * @param floorId is the identifier of the floor within the specified building.
     * @param roomId is the identifier of the room within the specified floor.
     * @return  the area of the room as a floating-point value.
     * Throws an exception if the room or floor or building is not found.
     */
    @GetMapping("/{id}/floor/{floorId}/room/{roomId}/area")
    public float getRoomArea(@PathVariable String id, @PathVariable String floorId, @PathVariable String roomId) {
        Floor floor = buildingInfo.findFloor(id, floorId);
        Room room =  buildingInfo.findRoom(id, floorId, roomId);
        return room.getArea();
    }
    /**
     * Handles a GET request to retrieve volume of the specific room from a floor by its id.
     *
     * @param id is the identifier of the building specified in the request path.
     * @param floorId is the identifier of the floor within the specified building.
     * @param roomId is the identifier of the room within the specified floor.
     * @return  the volume of the room as a floating-point value.
     * Throws an exception if the room or floor or building is not found.
     */
    @GetMapping("/{id}/floor/{floorId}/room/{roomId}/cube")
    public float getRoomCube(@PathVariable String id, @PathVariable String floorId, @PathVariable String roomId) {
        Floor floor = buildingInfo.findFloor(id, floorId);
        Room room =  buildingInfo.findRoom(id, floorId, roomId);
        return room.getCube();
    }
    /**
     * Handles a GET request to retrieve total energy needed for heating for the specific room from a floor by its id.
     *
     * @param id is the identifier of the building specified in the request path.
     * @param floorId is the identifier of the floor within the specified building.
     * @param roomId is the identifier of the room within the specified floor.
     * @return  the total energy needed for heating of the room as a floating-point value.
     * Throws an exception if the room or floor or building is not found.
     */
    @GetMapping("/{id}/floor/{floorId}/room/{roomId}/heating")
    public float getRoomHeating(@PathVariable String id, @PathVariable String floorId, @PathVariable String roomId) {
        Floor floor = buildingInfo.findFloor(id, floorId);
        Room room =  buildingInfo.findRoom(id, floorId, roomId);
        return room.getHeating();
    }
    /**
     * Handles a GET request to retrieve total light power for the specific room from a floor by its id.
     *
     * @param id is the identifier of the building specified in the request path.
     * @param floorId is the identifier of the floor within the specified building.
     * @param roomId is the identifier of the room within the specified floor.
     * @return  the total light power of the room as a floating-point value.
     * Throws an exception if the room or floor or building is not found.
     */
    @GetMapping("/{id}/floor/{floorId}/room/{roomId}/light")
    public float getRoomLight(@PathVariable String id, @PathVariable String floorId, @PathVariable String roomId) {
        Floor floor = buildingInfo.findFloor(id, floorId);
        Room room =  buildingInfo.findRoom(id, floorId, roomId);
        return room.getLight();
    }
    /**
     * Handles a GET request to retrieve the light power per area for the specific room from a floor by its id.
     *
     * @param id is the identifier of the building specified in the request path.
     * @param floorId is the identifier of the floor within the specified building.
     * @param roomId is the identifier of the room within the specified floor.
     * @return  the light power per area for the room as a floating-point value.
     * Throws an exception if the room or floor or building is not found.
     */

    @GetMapping("/{id}/floor/{floorId}/room/{roomId}/light-per-area")
    public float getRoomLightPerArea(@PathVariable String id, @PathVariable String floorId, @PathVariable String roomId) {
        Floor floor = buildingInfo.findFloor(id, floorId);
        Room room = buildingInfo.findRoom(id, floorId, roomId);
        return room.calLight();
    }
    /**
     * Handles a GET request to retrieve the heating energy per unit volume for the specific room from a floor by its id.
     *
     * @param id is the identifier of the building specified in the request path.
     * @param floorId is the identifier of the floor within the specified building.
     * @param roomId is the identifier of the room within the specified floor.
     * @return  the heating energy per unit volume for the room as a floating-point value.
     * Throws an exception if the room or floor or building is not found.
     */
    @GetMapping("/{id}/floor/{floorId}/room/{roomId}/heating-per-cube")
    public float getRoomHeatingPerCube(@PathVariable String id, @PathVariable String floorId, @PathVariable String roomId) {
        Floor floor = buildingInfo.findFloor(id, floorId);
        Room room = buildingInfo.findRoom(id, floorId, roomId);
        return room.calHeating();
    }

    /**
     * Handles a PUT request to update the information about the specific room from a floor of a building by its id.
     *
     * @param bid is the identifier of the building specified in the request path.
     * @param fid is the identifier of the floor within the specified building.
     * @param rid is the identifier of the room within the specified floor.
     * @param updatedRoom the {@link Room} object with updated information.
     * @return the updated {@link Room} object.
     *      Throws an exception if the building or floor or room with specified id is not found.
     */

    @PutMapping("{bid}/floor/{fid}/room/{rid}/update")
    public Room updateRoom(@PathVariable String bid, @PathVariable String fid, @PathVariable String rid, @RequestBody Room updatedRoom){
        return buildingInfo.updateRoom(bid, fid, updatedRoom, rid);
    }

}
