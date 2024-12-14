package pl.put.poznan.building.models;


/**
 * This class represents Room objects.
 * Each room object has: id, name, area, cube, heating and light.
 *
 * The class implements methods to calculate various properties of the room,
 * such as light power per area or heating per unit volume.
 *
 * The class extends the {@code Localization} class and inherits its fields:
 * {@code id} and {@code name}.
 *
 * The class overrides methods from {@code Localization} class:
 * {@code getArea()}, {@code @getCube()}, {@code getLight()}, {@code getHeating()},
 * {@code calLight()}, {@code calHeating()}, {@code toString()}.
 *
 * @author s2lw
 * @since 1.0
 *
 */
public class Room extends Localization {
    /**
     * This field stores the area of the room [m^2]
     */
    private float area;
    /**
     * This field stores cube (volume) of the room [m^3]
     */
    private float cube;
    /**
     * This field stores energy consumption for heating in the room (kWh)
     */
    private float heating;
    /**
     * This field stores the total light power required in a room (lm)
     */
    private float light;

    /**
     * Constructor to create Room object.
     * @param id unique (within the floor) room identifier
     * @param name name of the room
     * @param area area of the room
     * @param cube volume of the room
     * @param heating energy consumption needed for heating of the room
     * @param light total light power required in a room
     */
    public Room(String id, String name, float area, float cube, float heating, float light) {
        super(id, name);
        this.area = area;
        this.cube = cube;
        this.heating = heating;
        this.light = light;
    }

    public float getArea() {
        return area;
    }

    public void setArea(float area) {
        this.area = area;
    }

    public float getCube() {
        return cube;
    }

    public void setCube(float cube) {
        this.cube = cube;
    }

    @Override
    public float getHeating() {
        return heating;
    }

    public void setHeating(float heating) {
        this.heating = heating;
    }

    @Override
    public float getLight() {
        return light;
    }

    public void setLight(float light) {
        this.light = light;
    }
    /**
     * The total energy needed for heating for the room.
     * @return the calculated energy.
     */
    @Override
    public float calLight() {
        if (area != 0.0f) {
            return light / area;
        } else {
            return 0.0f;
        }
    }
    /**
     * Calculates energy consumption for heating per unit volume.
     * @return the calculated energy consumption for heating per unit volume for the room.
     */
    @Override
    public float calHeating() {
        if (cube != 0.0f) {
            return heating / cube;
        } else {
            return 0.0f;
        }
    }
    /**
     * Creates a string representation of the room object.
     * @return the string representing the room object.
     */
    @Override
    public String toString() {
        return super.toString() + " Room{area=" + area +
                ", cube=" + cube +
                ", heating=" + heating +
                ", light=" + light + '}';
    }
}
