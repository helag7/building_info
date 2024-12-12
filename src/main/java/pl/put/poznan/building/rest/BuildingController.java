package pl.put.poznan.building.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.put.poznan.building.logic.BuildingInfo;
import pl.put.poznan.building.models.Building;
import pl.put.poznan.building.models.Floor;
import pl.put.poznan.building.models.Room;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/buildings") //to sprawia, że /buildings jest już od razu w każdym URLu w metodach tej klasy, więc nie trzeba go za każdym razem dopisywać.
public class BuildingController {

    private static final Logger logger = LoggerFactory.getLogger(BuildingController.class);

    //class BuildingInfo has methods to simplify searching for correct buildings
    private final BuildingInfo buildingInfo;

    @Autowired
    public BuildingController(BuildingInfo buildingInfo){
        this.buildingInfo = buildingInfo;
    }

    //POST requesty - do wprowadzania danych nowego budnyku/poziomu/pokoju
    @PostMapping("/addBuilding")
    public Building addBuilding(@RequestBody Building building){
        logger.info("Received request to add building: {}", building);
        buildingInfo.addBuilding(building);
        logger.info("Building added: {}", building);
        return building;
    }

    //GET requesty - do pobrania danych danego budynku/poziomu/pokoju

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



    //PUT requesty - dodają możliwość update'owania danych budynku/poziomu/pokoju
    //TODO - zmienic uwzgledniajac przeniesienie buildingStorage do klasy buildingInfo!
//    @PutMapping("{id}")
//    public Building updateBuilding(@PathVariable String id, @RequestBody Building updatedBuilding){
//        logger.info("Request to update building: {}", id);
//        //Find the building:
//        for (int i=0; i<buildingInfo.buildingStorage.size(); i++){
//            Building building = buildingInfo.buildingStorage.get(i);
//            if(building.getId().equals(id)){
//                buildingInfo.buildingStorage.set(i, updatedBuilding);
//                logger.info("Building {} updated.", id);
//                return updatedBuilding;
//            }
//
//        }
//        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Building not found.");
//    }
//
//    //Updatowanie poziomu - done
//    @PutMapping("{bid}/floor/{fid}")
//    public Floor updateFloor(@PathVariable String bid, @PathVariable String fid, @RequestBody Floor updatedFloor){
//        logger.info("Request to update floor {} of building {}", fid, bid);
//        //Find the building:
//        for(int i=0; i<buildingInfo.buildingStorage.size(); i++){
//            Building building = buildingInfo.buildingStorage.get(i);
//            for(int j=0; j<building.getFloors().size(); j++){
//                Floor floor = building.getFloors().get(j);
//                if(floor.getId().equals(fid)){
//                    //Change the floor to the updatedFloor
//                    building.removeFloor(floor);
//                    building.addFloor(updatedFloor);
//                    logger.info("Floor {} updated in building {}", fid, bid);
//                    return updatedFloor;
//                }
//            }
//        }
//        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Building or Floor not found");
//    }

    //Updatowanie pokoju - to be continued




}
