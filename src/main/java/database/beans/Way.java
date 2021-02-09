package database.beans;

import beans.City;

public class Way {

    private City originCity;

    private  City distanceCity;

    private Float distance;

    public City getOriginCity() {
        return originCity;
    }

    public void setOriginCity(City originCity) {
        this.originCity = originCity;
    }

    public City getDistanceCity() {
        return distanceCity;
    }

    public void setDistanceCity(City distanceCity) {
        this.distanceCity = distanceCity;
    }

    public Float getDistance() {
        return distance;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }

    public Way(City originCity, City distanceCity, Float distance) {
        this.originCity = originCity;
        this.distanceCity = distanceCity;
        this.distance = distance;
    }

    public Way(City originCity, City distanceCity) {
        this.originCity = originCity;
        this.distanceCity = distanceCity;
    }

    @Override
    public String toString(){
        return originCity.getName() + " -> " + distanceCity.getName() + " :" + distance;
    }
}
