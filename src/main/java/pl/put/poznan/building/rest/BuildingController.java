package pl.put.poznan.building.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.building.logic.BuildingInfo;
import pl.put.poznan.building.models.Building;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//This class contains all REST methods to interact with buildings
@RestController
@RequestMapping("/buildings")
public class BuildingController {

    private static final Logger logger = LoggerFactory.getLogger(BuildingController.class);

    //class BuildingInfo has methods to simplify searching for correct buildings
    private final BuildingInfo buildingInfo;

    @Autowired
    public BuildingController(BuildingInfo buildingInfo){
        this.buildingInfo = buildingInfo;
    }

    @PostMapping("/addBuilding")
    public Building addBuilding(@RequestBody Building building){
        logger.info("Received request to add building: {}", building);
        buildingInfo.addBuilding(building);
        logger.info("Building added: {}", building);
        return building;
    }

    @GetMapping("/{id}")
    public Building getBuilding(@PathVariable String id) {
        return buildingInfo.findBuilding(id);
    }

    @GetMapping("/{id}/area")
    public float getBuildingArea(@PathVariable String id) {
        Building building = buildingInfo.findBuilding(id);
        return building.getArea();
    }

    @GetMapping("/{id}/cube")
    public float getBuildingCube(@PathVariable String id) {
        Building building = buildingInfo.findBuilding(id);
        return building.getCube();
    }

    @GetMapping("/{id}/heating")
    public float getBuildingHeating(@PathVariable String id) {
        Building building = buildingInfo.findBuilding(id);
        return building.getHeating();
    }

    @GetMapping("/{id}/light")
    public float getBuildingLight(@PathVariable String id) {
        Building building = buildingInfo.findBuilding(id);
        return building.getLight();
    }

    @GetMapping("/{id}/light-per-area")
    public float getBuildingLightPerArea(@PathVariable String id) {
        Building building = buildingInfo.findBuilding(id);
        return building.calLight();
    }
    @GetMapping("/{id}/heating-per-cube")
    public float getBuildingHeatingPerCube(@PathVariable String id) {
        Building building = buildingInfo.findBuilding(id);
        return building.calHeating();
    }


    @PutMapping("{id}/update")
    public Building updateBuilding(@PathVariable String id, @RequestBody Building updatedBuilding){
        return buildingInfo.updateBuilding(updatedBuilding, id);
    }
}
