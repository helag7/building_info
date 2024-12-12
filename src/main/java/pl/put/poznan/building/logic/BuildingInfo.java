package pl.put.poznan.building.logic;


import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import pl.put.poznan.building.models.Building;
import pl.put.poznan.building.models.Floor;
import pl.put.poznan.building.models.Room;

import java.util.ArrayList;
import java.util.List;

//This file contains some logic that the BuildingController uses.
public class BuildingInfo {

    //Storage for buildings: (z tego co pamiętam to mówił, że nie trzeba bazy danych, więc to powinno wystarczyć xD)
    private final List<Building> buildingStorage = new ArrayList<>();

    public Building findBuilding(String id){
        for(Building building: buildingStorage){
            if(building.getId().equals(id)){
                return building;
            }
        }
        //Exception if building was not found:
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Building not found");
    }

    public Floor findFloor(Building building, String id){
        for(Floor floor: building.getFloors()){
            if(floor.getId().equals(id)){
                return floor;
            }
        }
        //Exception if floor was not found:
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Floor not found");
    }
    public Room findRoom(Floor floor, String id){
        for(Room room: floor.getRooms()){
            if(room.getId().equals(id)){
                return room;
            }
        }
        //Exception if room was not found:
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Room not found");
    }

    public void addBuilding(Building building){
        buildingStorage.add(building);
    }
}
