package application;



public class Adjacent implements Comparable<Adjacent> {
    private City City;
    private float distance; //kilometers

    public Adjacent(City c, float dis) {
        this.City = c;
        this.distance = dis;
    }

    public City getCity() {
        return City;
    }

    public void setCity(City City) {
        this.City = City;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "City=" + City + ", distance=" + distance;
    }

    @Override
    public int compareTo(Adjacent o) {
        return (int) Double.compare(this.distance, o.distance);
    }
}
