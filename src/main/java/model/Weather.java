package model;


/**
 * POJO class for weather
 */
public class Weather {

    private Location location;
    private String time;
    private String wind;
    private String visibility;
    private String temperature;
    private String dewPoint;
    private String relativeHumidity;
    private String pressure;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getDewPoint() {
        return dewPoint;
    }

    public void setDewPoint(String dewPoint) {
        this.dewPoint = dewPoint;
    }

    public String getRelativeHumidity() {
        return relativeHumidity;
    }

    public void setRelativeHumidity(String relativeHumidity) {
        this.relativeHumidity = relativeHumidity;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    @Override
    public String toString() {
        return
                "Location='" + location + "\n" +
                        "Time='" + time + "\n" +
                        "Wind='" + wind + "\n" +
                        "Visibility='" + visibility + "\n" +
                        "Temperature='" + temperature + "\n" +
                        "DewPoint='" + dewPoint + "\n" +
                        "RelativeHumidity='" + relativeHumidity + "\n" +
                        "Pressure='" + pressure + "\n";
    }
}
