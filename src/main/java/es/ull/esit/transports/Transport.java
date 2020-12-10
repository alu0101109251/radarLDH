package es.ull.esit.transports;

public abstract class Transport {
    protected String type;
    protected Double latitude;
    protected Double longitude;

    public String getType() { return type; }
    public Double getLatitude() { return latitude; }
    public Double getLongitude() { return longitude; }

    public void setCoordinates(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
