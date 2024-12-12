package pl.put.poznan.building.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.put.poznan.building.logic.BuildingInfo;
import pl.put.poznan.building.models.Building;
import pl.put.poznan.building.models.Floor; //maybe not needed here?
import pl.put.poznan.building.models.Room; //maybe not needed here?


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/buildings") //to sprawia, że /buildings jest już od razu w każdym URLu w metodach tej klasy, więc nie trzeba go za każdym razem dopisywać.
public class BuildingController {

    private static final Logger logger = LoggerFactory.getLogger(BuildingController.class);
    //Storage for buildings: (z tego co pamiętam to mówił, że nie trzeba bazy danych, więc to powinno wystarczyć xD)
    private final List<Building> buildingStorage = new ArrayList<>();
    //Debug purpose only

    //class BuildingInfo has methods to simplify searching for correct buildings
    private final BuildingInfo buildingInfo = new BuildingInfo();

    //POST requesty - do wprowadzania danych nowego budnyku/poziomu/pokoju
    @PostMapping("/addBuilding")
    public Building addBuilding(@RequestBody Building building){
        logger.info("Received request to add building: {}", building);

        buildingStorage.add(building);
        logger.info("Building added: {}", building);

        return building; //json format - automatically
    }


    @PostMapping("/{id}/addFloor")
    public Floor addFloor(@PathVariable String id, @RequestBody Floor floor){
        logger.info("Received request to add floor to building {}.", id);

        //Find the right building we're modyfing:
        for (Building building: buildingStorage){
            if (building.getId().equals(id)){
                building.addFloor(floor);
                logger.info("Successfully added floor to building with id: {}", id);
                return floor;
            }
        }
        //Exception if building was not found:
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Building not found");

    }

    @PostMapping("{bid}/floor/{fid}/addRoom")
    public Room addRoom(@PathVariable String bid, @PathVariable String fid, @RequestBody Room room){
        logger.info("Received request to add building to building {} floor {}.", bid, fid);
        //Find the right building we're modyfing:
        for (Building building: buildingStorage){
            if (building.getId().equals(bid)){
                //Find the floor we're modyfing:
                for(Floor floor: building.getFloors()){
                    if (floor.getId().equals(fid)){
                        floor.addRoom(room);
                        logger.info("Room added to floor {} building {}", fid, bid);
                    }
                }
                return room;
            }
        }
        //Exception if building was not found:
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Building not found");


    }


    //GET requesty - do pobrania danych danego budynku/poziomu/pokoju

    //GET requesty dla danych całego budynku
    @GetMapping("/{id}")
    public Building getBuilding(@PathVariable String id) {
        return buildingInfo.findBuilding(buildingStorage, id);
    }

    @GetMapping("/{id}/area")
    public float getBuildingArea(@PathVariable String id) {
        Building building = buildingInfo.findBuilding(buildingStorage, id);
        return building.getArea();
    }

    @GetMapping("/{id}/cube")
    public float getBuildingCube(@PathVariable String id) {
        Building building = buildingInfo.findBuilding(buildingStorage, id);
        return building.getCube();
    }

    @GetMapping("/{id}/heating")
    public float getBuildingHeating(@PathVariable String id) {
        Building building = buildingInfo.findBuilding(buildingStorage, id);
        return building.getHeating();
    }

    @GetMapping("/{id}/light")
    public float getBuildingLight(@PathVariable String id) {
        Building building = buildingInfo.findBuilding(buildingStorage, id);
        return building.getLight();
    }

    @GetMapping("/{id}/light-per-area")
    public float getBuildingLightPerArea(@PathVariable String id) {
        Building building = buildingInfo.findBuilding(buildingStorage, id);
        return building.calLight();
    }
    @GetMapping("/{id}/heating-per-cube")
    public float getBuildingHeatingPerCube(@PathVariable String id) {
        Building building = buildingInfo.findBuilding(buildingStorage, id);
        return building.calHeating();
    }

    @GetMapping("/{id}/floors")
    public List<Floor> getBuildingFloors(@PathVariable String id) {
        Building building = buildingInfo.findBuilding(buildingStorage, id);
        return building.getFloors();
    }

    //GET requesty dla konkretnego piętra:
    //TODO: zmienic tak zeby uzywaly metod z klasy buildingInfo
    @GetMapping("/{id}/floor/{floorId}")
    public Floor getFloor(@PathVariable String id, @PathVariable String floorId) {
        Building building = buildingInfo.findBuilding(buildingStorage, id);
        return buildingInfo.findFloor(building, id);
    }

    @GetMapping("/{id}/floor/{floorId}/area")
    public float getFloorArea(@PathVariable String id, @PathVariable String floorId){
        Building building = buildingInfo.findBuilding(buildingStorage, id);
        Floor floor = buildingInfo.findFloor(building, floorId);
        return floor.getArea();
    }

    @GetMapping("/{id}/floor/{floorId}/cube")
    public float getFloorCube(@PathVariable String id, @PathVariable String floorId){
        Building building = buildingInfo.findBuilding(buildingStorage, id);
        Floor floor = buildingInfo.findFloor(building, floorId);
        return floor.getCube();
    }
    @GetMapping("/{id}/floor/{floorId}/heating")
    public float getFloorHeating(@PathVariable String id, @PathVariable String floorId){
        Building building = buildingInfo.findBuilding(buildingStorage, id);
        Floor floor = buildingInfo.findFloor(building, floorId);
        return floor.getHeating();
    }
    @GetMapping("/{id}/floor/{floorId}/light")
    public float getFloorLight(@PathVariable String id, @PathVariable String floorId){
        Building building = buildingInfo.findBuilding(buildingStorage, id);
        Floor floor = buildingInfo.findFloor(building, floorId);
        return floor.getLight();
    }

    @GetMapping("/{id}/floor/{floorId}/light-per-area")
    public float getFloorLightPerArea(@PathVariable String id, @PathVariable String floorId) {
        Building building = buildingInfo.findBuilding(buildingStorage, id);
        Floor floor = buildingInfo.findFloor(building, floorId);
        return floor.calLight();
    }

    @GetMapping("/{id}/floor/{floorId}/heating-per-cube")
    public float getFloorHeatingPerCube(@PathVariable String id, @PathVariable String floorId) {
        Building building = buildingInfo.findBuilding(buildingStorage, id);
        Floor floor = buildingInfo.findFloor(building, floorId);
        return floor.calHeating();
    }

    @GetMapping("/{id}/floor/{floorId}/rooms")
    public List<Room> getFloorRooms(@PathVariable String id, @PathVariable String floorId) {
        Building building = buildingInfo.findBuilding(buildingStorage, id);
        Floor floor = buildingInfo.findFloor(building, floorId);
        return floor.getRooms();
    }
    //GET requests for room with specified id:
    @GetMapping("/{id}/floor/{floorId}/room/{roomId}")
    public Room getRoom(@PathVariable String id, @PathVariable String floorId, @PathVariable String roomId) {
        Building building = buildingInfo.findBuilding(buildingStorage, id);
        Floor floor = buildingInfo.findFloor(building, floorId);
        return buildingInfo.findRoom(floor, roomId);
    }
    @GetMapping("/{id}/floor/{floorId}/room/{roomId}/area")
    public float getRoomArea(@PathVariable String id, @PathVariable String floorId, @PathVariable String roomId) {
        Building building = buildingInfo.findBuilding(buildingStorage, id);
        Floor floor = buildingInfo.findFloor(building, floorId);
        Room room =  buildingInfo.findRoom(floor, roomId);
        return room.getArea();
    }
    @GetMapping("/{id}/floor/{floorId}/room/{roomId}/cube")
    public float getRoomCube(@PathVariable String id, @PathVariable String floorId, @PathVariable String roomId) {
        Building building = buildingInfo.findBuilding(buildingStorage, id);
        Floor floor = buildingInfo.findFloor(building, floorId);
        Room room =  buildingInfo.findRoom(floor, roomId);
        return room.getCube();
    }
    @GetMapping("/{id}/floor/{floorId}/room/{roomId}/heating")
    public float getRoomHeating(@PathVariable String id, @PathVariable String floorId, @PathVariable String roomId) {
        Building building = buildingInfo.findBuilding(buildingStorage, id);
        Floor floor = buildingInfo.findFloor(building, floorId);
        Room room =  buildingInfo.findRoom(floor, roomId);
        return room.getHeating();
    }
    @GetMapping("/{id}/floor/{floorId}/room/{roomId}/light")
    public float getRoomLight(@PathVariable String id, @PathVariable String floorId, @PathVariable String roomId) {
        Building building = buildingInfo.findBuilding(buildingStorage, id);
        Floor floor = buildingInfo.findFloor(building, floorId);
        Room room =  buildingInfo.findRoom(floor, roomId);
        return room.getLight();
    }

    @GetMapping("/{id}/floor/{floorId}/room/{roomId}/light-per-area")
    public float getRoomLightPerArea(@PathVariable String id, @PathVariable String floorId, @PathVariable String roomId) {
        Building building = buildingInfo.findBuilding(buildingStorage, id);
        Floor floor = buildingInfo.findFloor(building, floorId);
        Room room = buildingInfo.findRoom(floor, roomId);
        return room.calLight();
    }

    @GetMapping("/{id}/floor/{floorId}/room/{roomId}/heating-per-cube")
    public float getRoomHeatingPerCube(@PathVariable String id, @PathVariable String floorId, @PathVariable String roomId) {
        Building building = buildingInfo.findBuilding(buildingStorage, id);
        Floor floor = buildingInfo.findFloor(building, floorId);
        Room room = buildingInfo.findRoom(floor, roomId);
        return room.calHeating();
    }

    //PUT requesty - dodają możliwość update'owania danych budynku/poziomu/pokoju
    @PutMapping("{id}")
    public Building updateBuilding(@PathVariable String id, @RequestBody Building updatedBuilding){
        logger.info("Request to update building: {}", id);
        //Find the building:
        for (int i=0; i<buildingStorage.size(); i++){
            Building building = buildingStorage.get(i);
            if(building.getId().equals(id)){
                buildingStorage.set(i, updatedBuilding);
                logger.info("Building {} updated.", id);
                return updatedBuilding;
            }

        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Building not found.");
    }

    //Updatowanie poziomu - done
    @PutMapping("{bid}/floor/{fid}")
    public Floor updateFloor(@PathVariable String bid, @PathVariable String fid, @RequestBody Floor updatedFloor){
        logger.info("Request to update floor {} of building {}", fid, bid);
        //Find the building:
        for(int i=0; i<buildingStorage.size(); i++){
            Building building = buildingStorage.get(i);
            for(int j=0; j<building.getFloors().size(); j++){
                Floor floor = building.getFloors().get(j);
                if(floor.getId().equals(fid)){
                    //Change the floor to the updatedFloor
                    building.removeFloor(floor);
                    building.addFloor(updatedFloor);
                    logger.info("Floor {} updated in building {}", fid, bid);
                    return updatedFloor;
                }
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Building or Floor not found");
    }

    //Updatowanie pokoju - to be continued




}
