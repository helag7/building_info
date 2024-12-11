package pl.put.poznan.building.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
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
        logger.info("Trying to access building with id: {}", id);
        //Find the correct building by id:
        for (Building building: buildingStorage){
            if (building.getId().equals(id)){
                logger.info("Successfully accessed building with id: {}", id);
                return building;
            }
        }
        //Exception if building was not found:
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Building not found");
    }

    @GetMapping("/{id}/area")
    public float getBuildingArea(@PathVariable String id) {
        //Find the correct building by id:
        for (Building building: buildingStorage){
            if (building.getId().equals(id)){
                //Return the computed area of the building
                return building.getArea();
            }
        }
        //Exception if building was not found:
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Building not found");
    }

    @GetMapping("/{id}/cube")
    public float getBuildingCube(@PathVariable String id) {
        //Find the correct building by id:
        for (Building building: buildingStorage){
            if (building.getId().equals(id)){
                //Return the computed area of the building
                return building.getCube();
            }
        }
        //Exception if building was not found:
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Building not found");
    }

    @GetMapping("/{id}/heating")
    public float getBuildingHeating(@PathVariable String id) {
        //Find the correct building by id:
        for (Building building: buildingStorage){
            if (building.getId().equals(id)){
                //Return the computed area of the building
                return building.getHeating();
            }
        }
        //Exception if building was not found:
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Building not found");
    }

    @GetMapping("/{id}/light")
    public float getBuildingLight(@PathVariable String id) {
        //Find the correct building by id:
        for (Building building: buildingStorage){
            if (building.getId().equals(id)){
                //Return the computed area of the building
                return building.getLight();
            }
        }
        //Exception if building was not found:
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Building not found");
    }

    @GetMapping("/{id}/light-per-area")
    public float getBuildingLightPerArea(@PathVariable String id) {
        //Find the correct building by id:
        for (Building building: buildingStorage){
            if (building.getId().equals(id)){
                //Return the computed area of the building
                return building.calLight();
            }
        }
        //Exception if building was not found:
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Building not found");
    }
    @GetMapping("/{id}/heating-per-cube")
    public float getBuildingHeatingPerCube(@PathVariable String id) {
        //Find the correct building by id:
        for (Building building: buildingStorage){
            if (building.getId().equals(id)){
                //Return the computed area of the building
                return building.calHeating();
            }
        }
        //Exception if building was not found:
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Building not found");
    }

    //GET requesty dla konkretnego piętra:
    @GetMapping("/{id}/floors")
    public List<Floor> getBuildingFloors(@PathVariable String id) {
        //Find the correct building by id:
        for (Building building: buildingStorage){
            if (building.getId().equals(id)){
                //Return the computed area of the building
                return building.getFloors();
            }
        }
        //Exception if building was not found:
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Building not found");
    }

    @GetMapping("/{id}/floor/{floorId}")
    public Floor getFloor(@PathVariable String id, @PathVariable String floorId) {
        //Find the correct building by id:
        for (Building building: buildingStorage){
            if (building.getId().equals(id)){
                //Find the correct floor:
                for(Floor floor: building.getFloors()){
                    if(floor.getId().equals(floorId)){
                        return floor;
                    }
                }
            }
        }
        //Exception if building was not found:
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Building or floor not found");
    }

    @GetMapping("/{id}/floor/{floorId}/light-per-area")
    public float getFloorLightPerArea(@PathVariable String id, @PathVariable String floorId) {
        //Find the correct building by id:
        for (Building building: buildingStorage){
            if (building.getId().equals(id)){
                //Find the correct floor:
                for(Floor floor: building.getFloors()){
                    if(floor.getId().equals(floorId)){
                        return floor.calLight();
                    }
                }
            }
        }
        //Exception if building was not found:
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Building or floor not found");
    }

    @GetMapping("/{id}/floor/{floorId}/heating-per-cube")
    public float getFloorHeatingPerCube(@PathVariable String id, @PathVariable String floorId) {
        //Find the correct building by id:
        for (Building building: buildingStorage){
            if (building.getId().equals(id)){
                //Find the correct floor:
                for(Floor floor: building.getFloors()){
                    if(floor.getId().equals(floorId)){
                        return floor.calHeating();
                    }
                }
            }
        }
        //Exception if building was not found:
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Building or floor not found");
    }

    //GET requests for room with specified id:

    @GetMapping("/{id}/floor/{floorId}/room/{roomId}/light-per-area")
    public float getRoomLightPerArea(@PathVariable String id, @PathVariable String floorId, @PathVariable String roomId) {
        //Find the correct building by id:
        for (Building building: buildingStorage){
            if (building.getId().equals(id)){
                //Find the correct floor:
                for(Floor floor: building.getFloors()){
                    if(floor.getId().equals(floorId)){
                        for(Room room: floor.getRooms()){
                            if(room.getId().equals(roomId)){
                                return room.calLight();
                            }
                        }
                    }
                }
            }
        }
        //Exception if building was not found:
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Building, floor or room not found");
    }

    @GetMapping("/{id}/floor/{floorId}/room/{roomId}/heating-per-cube")
    public float getRoomHeatingPerCube(@PathVariable String id, @PathVariable String floorId, @PathVariable String roomId) {
        //Find the correct building by id:
        for (Building building: buildingStorage){
            if (building.getId().equals(id)){
                //Find the correct floor:
                for(Floor floor: building.getFloors()){
                    if(floor.getId().equals(floorId)){
                        for(Room room: floor.getRooms()){
                            if(room.getId().equals(roomId)){
                                return room.calHeating();
                            }
                        }
                    }
                }
            }
        }
        //Exception if building was not found:
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Building, floor or room not found");
    }
//
//
//
//
//
    @GetMapping("/{id}/floor/{floorId}/rooms")
    public List<Room> getFloorRooms(@PathVariable String id, @PathVariable String floorId) {
        //Find the correct building by id:
        for (Building building: buildingStorage){
            if (building.getId().equals(id)){
                //Find the correct floor:
                for(Floor floor: building.getFloors()){
                    if(floor.getId().equals(floorId)){
                        return floor.getRooms();
                    }
                }
            }
        }
        //Exception if building was not found:
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Building or floor not found.");
    }

    @GetMapping("/{id}/floor/{floorId}/room/{roomId}")
    public Room getRoom(@PathVariable String id, @PathVariable String floorId, @PathVariable String roomId) {
        //Find the correct building by id:
        for (Building building: buildingStorage){
            if (building.getId().equals(id)){
                //Find the correct floor:
                for(Floor floor: building.getFloors()){
                    if(floor.getId().equals(floorId)){
                        for(Room room: floor.getRooms()){
                            if(room.getId().equals(roomId)){
                                return room;
                            }
                        }
                    }
                }
            }
        }
        //Exception if building was not found:
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Building, floor or room not found");
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
