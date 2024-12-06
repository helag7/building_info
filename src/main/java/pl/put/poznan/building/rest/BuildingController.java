package pl.put.poznan.building.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.put.poznan.building.models.Building;
import pl.put.poznan.building.models.Floor;
import pl.put.poznan.building.models.Room;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/buildings")
public class BuildingController {

    private static final Logger logger = LoggerFactory.getLogger(BuildingController.class);

    //Storage for buildings:
    private final List<Building> buildingStorage = new ArrayList<>();

    @PostMapping(consumes = "application/json", produces="application/json")
    public Building addBuilding(@RequestBody Building building){
        logger.info("Received request to add building: {}", building);

        buildingStorage.add(building);
        logger.info("Building added: {}", building);

        return building;
    }

    @GetMapping("/{id}")
    public Building getBuilding(@PathVariable String id) {
        logger.info("Trying to access building with id: {}", id);

        for (Building building: buildingStorage){
            if (building.getId().equals(id)){
                logger.info("Successfully accessed building with id: {}", id);
                return building;
            }
        }
        //Exception if building was not found:
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Building not found");
    }



}
