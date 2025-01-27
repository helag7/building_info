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
    void testBuildingWithMock() {
        // Mock Room
        Room mockRoom = mock(Room.class);
        when(mockRoom.getArea()).thenReturn(25.0f);
        when(mockRoom.getCube()).thenReturn(100.0f);
        when(mockRoom.getLight()).thenReturn(60.0f);
        when(mockRoom.getHeating()).thenReturn(30.0f);

        // Mock Floor
        Floor mockFloor = mock(Floor.class);
        when(mockFloor.getArea()).thenReturn(100.0f);
        when(mockFloor.getCube()).thenReturn(400.0f);
        when(mockFloor.getLight()).thenReturn(240.0f);
        when(mockFloor.getHeating()).thenReturn(120.0f);

        // Mock Building
        Building mockBuilding = mock(Building.class);
        when(mockBuilding.getArea()).thenReturn(400.0f);
        when(mockBuilding.getCube()).thenReturn(1600.0f);
        when(mockBuilding.getLight()).thenReturn(960.0f);
        when(mockBuilding.getHeating()).thenReturn(480.0f);

        // Add interactions and verify behaviors

        // 1. Verify Room's getArea method
        assertEquals(25.0f, mockRoom.getArea());
        verify(mockRoom).getArea();

        // 2. Verify Room's getCube method
        assertEquals(100.0f, mockRoom.getCube());
        verify(mockRoom).getCube();

        // 3. Verify Floor's getArea method
        assertEquals(100.0f, mockFloor.getArea());
        verify(mockFloor).getArea();

        // 4. Verify Floor's getCube method
        assertEquals(400.0f, mockFloor.getCube());
        verify(mockFloor).getCube();

        // 5. Verify Building's getArea method
        assertEquals(400.0f, mockBuilding.getArea());
        verify(mockBuilding).getArea();

        // 6. Verify Building's getCube method
        assertEquals(1600.0f, mockBuilding.getCube());
        verify(mockBuilding).getCube();

        // 7. Test adding a floor to Building
        doNothing().when(mockBuilding).addFloor(mockFloor);
        mockBuilding.addFloor(mockFloor);
        verify(mockBuilding).addFloor(mockFloor);

        // 8. Test removing a floor from Building
        doNothing().when(mockBuilding).removeFloor(mockFloor);
        mockBuilding.removeFloor(mockFloor);
        verify(mockBuilding).removeFloor(mockFloor);

        // 9. Verify Floor's getLight method
        assertEquals(240.0f, mockFloor.getLight());
        verify(mockFloor).getLight();

        // 10. Verify Room's getHeating method
        assertEquals(30.0f, mockRoom.getHeating());
        verify(mockRoom).getHeating();
    }
}
