package pl.put.poznan.building.logic;


import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import pl.put.poznan.building.models.Building;
import pl.put.poznan.building.models.Floor;
import pl.put.poznan.building.models.Room;

import java.util.ArrayList;
import java.util.List;

/*
This class provides some functionality that controllers use, especially:
find building/floor/room by id, update building/floor/room by id

 */
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

    public Floor findFloor(String bid, String fid){
        Building building = findBuilding(bid);
        for(Floor floor: building.getFloors()){
            if(floor.getId().equals(fid)){
                return floor;
            }
        }
        //Exception if floor was not found:
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Floor not found");
    }
    public Room findRoom(String bid, String fid, String rid){
        Floor floor = findFloor(bid, fid);
        for(Room room: floor.getRooms()){
            if(room.getId().equals(rid)){
                return room;
            }
        }
        //Exception if room was not found:
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Room not found");
    }

    public void addBuilding(Building building){
        buildingStorage.add(building);
    }

    public Building updateBuilding(Building updatedBuilding, String id){
        for(int i=0; i<buildingStorage.size(); i++){
            Building building = buildingStorage.get(i);
            if(building.getId().equals(id)){
                buildingStorage.set(i, updatedBuilding);
                return updatedBuilding;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Building not found.");
    }

    public Floor updateFloor(String bid, Floor updatedFloor, String fid){
        Building building = findBuilding(bid);
        Floor floor = findFloor(bid, fid);
        building.removeFloor(floor);
        building.addFloor(updatedFloor);
        return updatedFloor;
    }

    public Room updateRoom(String bid, String fid, Room updatedRoom, String rid){
        Floor floor = findFloor(bid, fid);
        Room room = findRoom(bid, fid, rid);
        floor.removeRoom(room);
        floor.addRoom(updatedRoom);
        return updatedRoom;
    }
}
