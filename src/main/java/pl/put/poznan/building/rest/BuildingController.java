package pl.put.poznan.building.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pl.put.poznan.building.models.Building;
import pl.put.poznan.building.models.Floor;
import pl.put.poznan.building.models.Room;



import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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



}
