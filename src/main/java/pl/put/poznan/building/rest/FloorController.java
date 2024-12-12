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
@RequestMapping("/floors")
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

    @GetMapping("/{id}")
    public List<Floor> getBuildingFloors(@PathVariable String id) {
        Building building = buildingInfo.findBuilding(id);
        return building.getFloors();
    }

    @GetMapping("/{id}/floor/{floorId}")
    public Floor getFloor(@PathVariable String id, @PathVariable String floorId) {
        Building building = buildingInfo.findBuilding(id);
        return buildingInfo.findFloor(building, id);
    }


}
