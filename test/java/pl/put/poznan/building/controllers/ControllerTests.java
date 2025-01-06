package pl.put.poznan.building.controllers;

import org.junit.jupiter.api.Test;
import pl.put.poznan.building.logic.BuildingInfo;
import pl.put.poznan.building.models.Building;
import pl.put.poznan.building.models.Floor;
import pl.put.poznan.building.models.Room;
import pl.put.poznan.building.rest.FloorController;
import pl.put.poznan.building.rest.RoomController;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ControllerTests {

    @Test
    public void testAddFloor() {
        BuildingInfo mockBuildingInfo = mock(BuildingInfo.class);
        Building mockBuilding = mock(Building.class);
        FloorController controller = new FloorController(mockBuildingInfo);

        Floor floor = new Floor("floorId", "floorName");
        when(mockBuildingInfo.findBuilding("1")).thenReturn(mockBuilding);

        controller.addFloor("1", floor);

        verify(mockBuildingInfo).findBuilding("1");
        verify(mockBuilding).addFloor(floor);
    }

    @Test
    public void testGetBuildingFloors() {
        BuildingInfo mockBuildingInfo = mock(BuildingInfo.class);
        Building mockBuilding = mock(Building.class);
        FloorController controller = new FloorController(mockBuildingInfo);

        List<Floor> mockFloors = List.of(new Floor("floorId", "floorName"));
        when(mockBuildingInfo.findBuilding("1")).thenReturn(mockBuilding);
        when(mockBuilding.getFloors()).thenReturn(mockFloors);

        List<Floor> result = controller.getBuildingFloors("1");

        assertEquals(mockFloors, result);
        verify(mockBuildingInfo).findBuilding("1");
    }

    @Test
    public void testGetFloor() {
        BuildingInfo mockBuildingInfo = mock(BuildingInfo.class);
        FloorController controller = new FloorController(mockBuildingInfo);

        Floor mockFloor = new Floor("floorId", "floorName");
        when(mockBuildingInfo.findFloor("1", "2")).thenReturn(mockFloor);

        Floor result = controller.getFloor("1", "2");

        assertEquals(mockFloor, result);
        verify(mockBuildingInfo).findFloor("1", "2");
    }

    @Test
    public void testGetFloorArea() {
        BuildingInfo mockBuildingInfo = mock(BuildingInfo.class);
        FloorController controller = new FloorController(mockBuildingInfo);

        Floor mockFloor = mock(Floor.class);
        when(mockBuildingInfo.findFloor("1", "2")).thenReturn(mockFloor);
        when(mockFloor.getArea()).thenReturn(50.0f);

        float result = controller.getFloorArea("1", "2");

        assertEquals(50.0f, result);
        verify(mockBuildingInfo).findFloor("1", "2");
        verify(mockFloor).getArea();
    }

    @Test
    public void testAddRoom() {
        BuildingInfo mockBuildingInfo = mock(BuildingInfo.class);
        RoomController controller = new RoomController(mockBuildingInfo);

        Floor mockFloor = mock(Floor.class);
        Room room = new Room("roomId", "roomName", 20.5f, 50.0f, 15.0f, 10.0f);
        when(mockBuildingInfo.findFloor("1", "2")).thenReturn(mockFloor);

        controller.addRoom("1", "2", room);

        verify(mockBuildingInfo).findFloor("1", "2");
        verify(mockFloor).addRoom(room);
    }

    @Test
    public void testGetRoom() {
        BuildingInfo mockBuildingInfo = mock(BuildingInfo.class);
        RoomController controller = new RoomController(mockBuildingInfo);

        Room mockRoom = new Room("roomId", "roomName", 20.5f, 50.0f, 15.0f, 10.0f);
        when(mockBuildingInfo.findRoom("1", "2", "3")).thenReturn(mockRoom);

        Room result = controller.getRoom("1", "2", "3");

        assertEquals(mockRoom, result);
        verify(mockBuildingInfo).findRoom("1", "2", "3");
    }

    @Test
    public void testGetRoomArea() {
        BuildingInfo mockBuildingInfo = mock(BuildingInfo.class);
        RoomController controller = new RoomController(mockBuildingInfo);

        Room mockRoom = mock(Room.class);
        when(mockBuildingInfo.findRoom("1", "2", "3")).thenReturn(mockRoom);
        when(mockRoom.getArea()).thenReturn(30.0f);

        float result = controller.getRoomArea("1", "2", "3");

        assertEquals(30.0f, result);
        verify(mockBuildingInfo).findRoom("1", "2", "3");
        verify(mockRoom).getArea();
    }

    @Test
    public void testUpdateFloor() {
        BuildingInfo mockBuildingInfo = mock(BuildingInfo.class);
        FloorController controller = new FloorController(mockBuildingInfo);

        Floor mockFloor = new Floor("floorId", "floorName");
        when(mockBuildingInfo.updateFloor("1", mockFloor, "2")).thenReturn(mockFloor);

        Floor result = controller.updateFloor("1", "2", mockFloor);

        assertEquals(mockFloor, result);
        verify(mockBuildingInfo).updateFloor("1", mockFloor, "2");
    }

    @Test
    public void testUpdateRoom() {
        BuildingInfo mockBuildingInfo = mock(BuildingInfo.class);
        RoomController controller = new RoomController(mockBuildingInfo);

        Room mockRoom = new Room("roomId", "roomName", 20.5f, 50.0f, 15.0f, 10.0f);
        when(mockBuildingInfo.updateRoom("1", "2", mockRoom, "3")).thenReturn(mockRoom);

        Room result = controller.updateRoom("1", "2", "3", mockRoom);

        assertEquals(mockRoom, result);
        verify(mockBuildingInfo).updateRoom("1", "2", mockRoom, "3");
    }

    @Test
    public void testGetRoomLight() {
        BuildingInfo mockBuildingInfo = mock(BuildingInfo.class);
        RoomController controller = new RoomController(mockBuildingInfo);

        Room mockRoom = mock(Room.class);
        when(mockBuildingInfo.findRoom("1", "2", "3")).thenReturn(mockRoom);
        when(mockRoom.getLight()).thenReturn(100.0f);

        float result = controller.getRoomLight("1", "2", "3");

        assertEquals(100.0f, result);
        verify(mockBuildingInfo).findRoom("1", "2", "3");
        verify(mockRoom).getLight();
    }
}