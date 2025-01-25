package pl.put.poznan.building.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.building.logic.BuildingInfo;
import pl.put.poznan.building.models.Building;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class is a REST controller that handles HTTP requests for managing buildings.
 * It provides endpoints for adding, retrieving, updating and calculating properties of buildings.
 *
 * @author helag
 * @version 1.1
 * @since 1.0
 */


@RestController
@RequestMapping("/buildings")
public class BuildingController {

    private static final Logger logger = LoggerFactory.getLogger(BuildingController.class);

    //class BuildingInfo has methods to simplify searching for correct buildings
    private final BuildingInfo buildingInfo;

    /**
     * Constructor for the building controller.
     *
     * @param buildingInfo an instance of {@link BuildingInfo} that contains methods
     *                     for managing and retrieving building data.
     */
    @Autowired
    public BuildingController(BuildingInfo buildingInfo){
        this.buildingInfo = buildingInfo;
    }

    /**
     * Handles a POST request to add a new building.
     *
     * @param building the {@link Building} object provided in the request body to be added.
     * @return the {@link Building} object that was added.
     */
    @PostMapping("/addBuilding")
    public Building addBuilding(@RequestBody Building building){
        logger.info("Received request to add building: {}", building);
        buildingInfo.addBuilding(building);
        logger.info("Building added: {}", building);
        return building;
    }

    /**
     * Handles a GET request to retrieve a building with the specified id.
     *
     * @param id is the identifier of the building specified in the request path.
     * @return  the {@link Building} object with the specified ID, if it exists.
     * Throws an exception if the building is not found.
     */
    @GetMapping("/{id}")
    public Building getBuilding(@PathVariable String id) {
        return buildingInfo.findBuilding(id);
    }
    /**
     * Handles a GET request to retrieve the area of the building with the specified id.
     *
     * @param id is the identifier of the building specified in the request path.
     * @return  the area of the building as a floating-point value.
     *      Throws an exception if the building with specified id is not found.
     */
    @GetMapping("/{id}/area")
    public float getBuildingArea(@PathVariable String id) {
        Building building = buildingInfo.findBuilding(id);
        return building.getArea();
    }
    /**
     * Handles a GET request to retrieve the volume of the building with the specified id.
     *
     * @param id is the identifier of the building specified in the request path.
     * @return  the volume of the building as a floating-point value.
     *      Throws an exception if the building with specified id is not found.
     */
    @GetMapping("/{id}/cube")
    public float getBuildingCube(@PathVariable String id) {
        Building building = buildingInfo.findBuilding(id);
        return building.getCube();
    }
    /**
     * Handles a GET request to retrieve the total energy needed for heating of the building with the specified id.
     *
     * @param id is the identifier of the building specified in the request path.
     * @return  the total heating energy of the building as a floating-point value.
     *      Throws an exception if the building with specified id is not found.
     */
    @GetMapping("/{id}/heating")
    public float getBuildingHeating(@PathVariable String id) {
        Building building = buildingInfo.findBuilding(id);
        return building.getHeating();
    }
    /**
     * Handles a GET request to retrieve the total light of the building with the specified id.
     *
     * @param id is the identifier of the building specified in the request path.
     * @return  the total light of the building as a floating-point value.
     *      Throws an exception if the building with specified id is not found.
     */
    @GetMapping("/{id}/light")
    public float getBuildingLight(@PathVariable String id) {
        Building building = buildingInfo.findBuilding(id);
        return building.getLight();
    }
    /**
     * Handles a GET request to retrieve the light power per area of the building with the specified id.
     *
     * @param id is the identifier of the building specified in the request path.
     * @return  the light power by area of the building as a floating-point value.
     *      Throws an exception if the building with specified id is not found.
     */
    @GetMapping("/{id}/light-per-area")
    public float getBuildingLightPerArea(@PathVariable String id) {
        Building building = buildingInfo.findBuilding(id);
        return building.calLight();
    }
    /**
     * Handles a GET request to retrieve the heating energy per unit volume of the building with the specified id.
     *
     * @param id is the identifier of the building specified in the request path.
     * @return  the heating energy by unit volume of the building as a floating-point value.
     *      Throws an exception if the building with specified id is not found.
     */
    @GetMapping("/{id}/heating-per-cube")
    public float getBuildingHeatingPerCube(@PathVariable String id) {
        Building building = buildingInfo.findBuilding(id);
        return building.calHeating();
    }

    /**
     * Handles a PUT request to update the information about building with specified id.
     *
     * @param id is the identifier of the building specified in the request path.
     * @param updatedBuilding the {@link Building} object with updated information.
     * @return the updated {@link Building} object.
     *      Throws an exception if the building with specified id is not found.
     */

    @PutMapping("{id}/update")
    public Building updateBuilding(@PathVariable String id, @RequestBody Building updatedBuilding){
        return buildingInfo.updateBuilding(updatedBuilding, id);
    }
}
