package pl.put.poznan.building.rest;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.building.models.Building;
import pl.put.poznan.building.models.Floor;
import pl.put.poznan.building.logic.BuildingInfo;

import java.util.List;

/**
 * This class is a REST controller that handles HTTP requests for managing floors.
 * It provides endpoints for adding, retrieving, updating and calculating properties of floors.
 *
 * @author helag
 * @version 1.1
 * @since 1.0
 */
@RestController
@RequestMapping("/buildings")
public class FloorController {

    private static final Logger logger = LoggerFactory.getLogger(FloorController.class);

    private final BuildingInfo buildingInfo;

    /**
     * Constructor for the floor controller.
     *
     * @param buildingInfo an instance of {@link BuildingInfo} that contains methods
     *                     for managing and retrieving floor data.
     */
    @Autowired
    public FloorController(BuildingInfo buildingInfo){
        this.buildingInfo = buildingInfo;
    }

    /**
     * Handles a POST request to add a new floor to a specified building.
     *
     * @param id is the identifier of the building where the floor will be added, specified in the request path.
     * @param floor the {@link Floor} object provided in the request body to be added to the building.
     * @return the {@link Floor} object that was added to the building.
     */

    @PostMapping("/{id}/addFloor")
    public Floor addFloor(@PathVariable String id, @RequestBody Floor floor){
        logger.info("Received request to add floor to building {}.", id);
        Building building = buildingInfo.findBuilding(id);
        building.addFloor(floor);
        logger.info("Successfully added floor {}.", floor);
        return floor;
    }

    /**
     * Handles a GET request to retrieve all floors for a specified building
     *
     * @param id is the identifier of the building where floors are to be added, specified in the request path.
     * @return a list of {@link Floor} objects for the specified building.
     */
    @GetMapping("/{id}/floors")
    public List<Floor> getBuildingFloors(@PathVariable String id) {
        Building building = buildingInfo.findBuilding(id);
        return building.getFloors();
    }

    /**
     * Handles a GET request to retrieve a specific floor from a building by its id.
     *
     * @param id is the identifier of the building specified in the request path.
     * @param floorId is the identifier of the floor within the specified building.
     * @return  the {@link Floor} object with the specified ID, if it exists.
     * Throws an exception if the floor or building is not found.
     */
    @GetMapping("/{id}/floor/{floorId}")
    public Floor getFloor(@PathVariable String id, @PathVariable String floorId) {
        return buildingInfo.findFloor(id, floorId);
    }

    /**
     * Handles a GET request to retrieve area of the specific floor from a building by its id.
     *
     * @param id is the identifier of the building specified in the request path.
     * @param floorId is the identifier of the floor within the specified building.
     * @return  the area of the floor as a floating-point value.
     * Throws an exception if the floor or building is not found.
     */

    @GetMapping("/{id}/floor/{floorId}/area")
    public float getFloorArea(@PathVariable String id, @PathVariable String floorId){
        Floor floor = buildingInfo.findFloor(id, floorId);
        return floor.getArea();
    }
    /**
     * Handles a GET request to retrieve the volume of the specific floor from a building by its id.
     *
     * @param id is the identifier of the building specified in the request path.
     * @param floorId is the identifier of the floor within the specified building.
     * @return  the volume of the floor as a floating-point value.
     * Throws an exception if the floor or building is not found.
     */

    @GetMapping("/{id}/floor/{floorId}/cube")
    public float getFloorCube(@PathVariable String id, @PathVariable String floorId){
        Floor floor = buildingInfo.findFloor(id, floorId);
        return floor.getCube();
    }
    /**
     * Handles a GET request to retrieve the total energy needed for heating for the specific floor from a building by its id.
     *
     * @param id is the identifier of the building specified in the request path.
     * @param floorId is the identifier of the floor within the specified building.
     * @return  the total energy needed for heating of the floor as a floating-point value.
     * Throws an exception if the floor or building is not found.
     */
    @GetMapping("/{id}/floor/{floorId}/heating")
    public float getFloorHeating(@PathVariable String id, @PathVariable String floorId){
        Floor floor = buildingInfo.findFloor(id, floorId);
        return floor.getHeating();
    }
    /**
     * Handles a GET request to retrieve the total light power for the specific floor from a building by its id.
     *
     * @param id is the identifier of the building specified in the request path.
     * @param floorId is the identifier of the floor within the specified building.
     * @return  the total light power of the floor as a floating-point value.
     * Throws an exception if the floor or building is not found.
     */
    @GetMapping("/{id}/floor/{floorId}/light")
    public float getFloorLight(@PathVariable String id, @PathVariable String floorId){
        Floor floor = buildingInfo.findFloor(id, floorId);
        return floor.getLight();
    }
    /**
     * Handles a GET request to retrieve the light power per area for the specific floor from a building by its id.
     *
     * @param id is the identifier of the building specified in the request path.
     * @param floorId is the identifier of the floor within the specified building.
     * @return  the light power per area of the floor as a floating-point value.
     * Throws an exception if the floor or building is not found.
     */

    @GetMapping("/{id}/floor/{floorId}/light-per-area")
    public float getFloorLightPerArea(@PathVariable String id, @PathVariable String floorId) {
        Floor floor = buildingInfo.findFloor(id, floorId);
        return floor.calLight();
    }
    /**
     * Handles a GET request to retrieve the heating energy per unit volume for the specific floor from a building by its id.
     *
     * @param id is the identifier of the building specified in the request path.
     * @param floorId is the identifier of the floor within the specified building.
     * @return  the heating energy per unit volume of the floor as a floating-point value.
     * Throws an exception if the floor or building is not found.
     */

    @GetMapping("/{id}/floor/{floorId}/heating-per-cube")
    public float getFloorHeatingPerCube(@PathVariable String id, @PathVariable String floorId) {
        Floor floor = buildingInfo.findFloor(id, floorId);
        return floor.calHeating();
    }

    /**
     * Handles a PUT request to update the information about the specific floor from a building by its id.
     *
     * @param bid is the identifier of the building specified in the request path.
     * @param fid is the identifier of the floor within the specified building.
     * @param updatedFloor the {@link Floor} object with updated information.
     * @return the updated {@link Floor} object.
     *      Throws an exception if the building or floor with specified id is not found.
     */

    @PutMapping("{bid}/floor/{fid}/update")
    public Floor updateFloor(@PathVariable String bid, @PathVariable String fid, @RequestBody Floor updatedFloor){
        return buildingInfo.updateFloor(bid, updatedFloor, fid);
    }
}
