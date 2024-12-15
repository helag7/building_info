/**
 * A class representing a simple smiley face.
 */
public class Smile {

    /**
     * The type of smile, e.g., ":)" or ":D".
     */
    private String smileType;

    /**
     * Creates a Smile with the given type.
     * 
     * @param smileType the type of smile
     */
    public Smile(String smileType) {
        this.smileType = smileType;
    }

    /**
     * Returns the smile type.
     * 
     * @return the smile type
     */
    public String getSmileType() {
        return smileType;
    }
}
