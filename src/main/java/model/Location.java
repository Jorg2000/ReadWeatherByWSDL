package model;

/**
 * POJO class for location
 */
public class Location {
    private String place;
    private String latitude;
    private String longitude;

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "Place='" + place + '\'' +
                "Latitude='" + latitude + '\'' +
                "Longitude='" + longitude + '\'';
    }
}
