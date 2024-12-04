package pl.put.poznan.building.models;

public abstract class Localization {
    private final String id;
    private String name;

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
    public abstract float getArea();

    public abstract float getCube();
    public abstract float getLight();
    public abstract float calLight();
    public abstract float getHeating();

    public abstract float calHeating();

    @Override // Override, ponieważ domyślne toString() jest mało przydatne
    public String toString() {
        return "Localization{id='" + id + "', name='" + name + "'}";
    }
}
