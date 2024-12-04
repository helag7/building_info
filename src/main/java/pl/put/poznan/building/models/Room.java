package pl.put.poznan.building.models;

public class Room extends Localization {
    private float area;
    private float cube;
    private float heating;
    private float light;

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

    @Override
    public float calLight() {
        if (area != 0.0f) {
            return light / area;
        } else {
            return 0.0f;
        }
    }

    @Override
    public float calHeating() {
        if (cube != 0.0f) {
            return heating / cube;
        } else {
            return 0.0f;
        }
    }

    @Override
    public String toString() {
        return super.toString() + " Room{area=" + area +
                ", cube=" + cube +
                ", heating=" + heating +
                ", light=" + light + '}';
    }
}
