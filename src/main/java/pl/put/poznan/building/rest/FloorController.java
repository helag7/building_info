package pl.put.poznan.building.rest;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.put.poznan.building.models.Building;
import pl.put.poznan.building.models.Floor;
import pl.put.poznan.building.logic.BuildingInfo;

import java.util.List;

@RestController
@RequestMapping("/buildings")
public class FloorController {

    private static final Logger logger = LoggerFactory.getLogger(FloorController.class);

    private final BuildingInfo buildingInfo;

    //Bean injection:
    @Autowired
    public FloorController(BuildingInfo buildingInfo){
        this.buildingInfo = buildingInfo;
    }


    @PostMapping("/{id}/addFloor")
    public Floor addFloor(@PathVariable String id, @RequestBody Floor floor){
        logger.info("Received request to add floor to building {}.", id);
        Building building = buildingInfo.findBuilding(id);
        building.addFloor(floor);
        logger.info("Successfully added floor {}.", floor);
        return floor;
    }

    @GetMapping("/{id}/floors")
    public List<Floor> getBuildingFloors(@PathVariable String id) {
        Building building = buildingInfo.findBuilding(id);
        return building.getFloors();
    }

    @GetMapping("/{id}/floor/{floorId}")
    public Floor getFloor(@PathVariable String id, @PathVariable String floorId) {
        Building building = buildingInfo.findBuilding(id);
        return buildingInfo.findFloor(building, floorId);
    }

    @GetMapping("/{id}/floor/{floorId}/area")
    public float getFloorArea(@PathVariable String id, @PathVariable String floorId){
        Building building = buildingInfo.findBuilding(id);
        Floor floor = buildingInfo.findFloor(building, floorId);
        return floor.getArea();
    }

    @GetMapping("/{id}/floor/{floorId}/cube")
    public float getFloorCube(@PathVariable String id, @PathVariable String floorId){
        Building building = buildingInfo.findBuilding(id);
        Floor floor = buildingInfo.findFloor(building, floorId);
        return floor.getCube();
    }
    @GetMapping("/{id}/floor/{floorId}/heating")
    public float getFloorHeating(@PathVariable String id, @PathVariable String floorId){
        Building building = buildingInfo.findBuilding(id);
        Floor floor = buildingInfo.findFloor(building, floorId);
        return floor.getHeating();
    }
    @GetMapping("/{id}/floor/{floorId}/light")
    public float getFloorLight(@PathVariable String id, @PathVariable String floorId){
        Building building = buildingInfo.findBuilding(id);
        Floor floor = buildingInfo.findFloor(building, floorId);
        return floor.getLight();
    }

    @GetMapping("/{id}/floor/{floorId}/light-per-area")
    public float getFloorLightPerArea(@PathVariable String id, @PathVariable String floorId) {
        Building building = buildingInfo.findBuilding(id);
        Floor floor = buildingInfo.findFloor(building, floorId);
        return floor.calLight();
    }

    @GetMapping("/{id}/floor/{floorId}/heating-per-cube")
    public float getFloorHeatingPerCube(@PathVariable String id, @PathVariable String floorId) {
        Building building = buildingInfo.findBuilding(id);
        Floor floor = buildingInfo.findFloor(building, floorId);
        return floor.calHeating();
    }

    @PutMapping("{bid}/floor/{fid}/update")
    public Floor updateFloor(@PathVariable String bid, @PathVariable String fid, @RequestBody Floor updatedFloor){
        return buildingInfo.updateFloor(bid, updatedFloor, fid);
    }


}
