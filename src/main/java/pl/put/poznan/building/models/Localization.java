package pl.put.poznan.building.models;

/**
 * Represents an abstract template for defining various types of locations, such as rooms, floors, or buildings.
 *
 * Classes extending the {@code Localization} abstract class are required to define a unique id
 * and a name, which describe the location. These fields are inherited through the constructor and are
 * accessible through their respective methods.
 *
 * The following abstract methods must be implemented by subclasses to calculate specific properties of the location:
 * <ul>
 *      <li>{@link #getArea()} - Returns the area of the location.</li>
 *      <li>{@link #getCube()} - Returns the total volume of the location.</li>
 *      <li>{@link #getLight()} - Returns the total light power required for the location.</li>
 *      <li>{@link #calLight()} - Calculates light power per surface area.</li>
 *      <li>{@link #getHeating()} - Returns the total energy needed for heating for the location.</li>
 *      <li>{@link #calHeating()} - Calculates energy needed for heating per unit volume.</li>
 *
 * </ul>
 *
 * Additionally, this class overrides the {@link #toString()} method to provide
 * readable string representation of location's basic details.
 *
 *
 * @author s2lw
 * @version 1.1
 * @since 1.0
 *
 */

public abstract class Localization {
    /**
     * Unique identifier of the location.
     */
    private final String id;
    /**
     * Name of the location.
     */
    private String name;

    /**
     * Constructor - to create localization object.
     * @param id unique identifier of the location
     * @param name name of the location
     */
    public Localization(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Calculates the area of the location.
     * @return the total area of the location.
     */
    public abstract float getArea();

    /**
     * Calculates the volume of the location.
     * @return the total volume of the location.
     */
    public abstract float getCube();
    /**
     * Calculates total light power needed for the location.
     * @return the total light power in watts.
     */
    public abstract float getLight();
    /**
     * Calculates light power needed by the location per surface area.
     * @return the light power per square meter
     */
    public abstract float calLight();
    /**
     * Calculates the total energy needed for heating the location.
     * @return the total energy required for heating in kilowatt-hours
     */
    public abstract float getHeating();
    /**
     * Calculates the energy needed for heating of the location per unit volume.
     * @return the heating energy per cubic meter
     */
    public abstract float calHeating();
    /**
     * Overrides toString() method, to provide readable information about location.
     * @return the information about the location.
     */
    @Override // Override, ponieważ domyślne toString() jest mało przydatne
    public String toString() {
        return "Localization{id='" + id + "', name='" + name + "'}";
    }
}
