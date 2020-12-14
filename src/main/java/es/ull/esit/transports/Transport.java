package es.ull.esit.transports;

/**
 *
 * @class Transport
 * @brief Abstract class to represent a transport
 *
 * @details This class provides common methods and attributes shared
 * between every transport.
 *
 */

public abstract class Transport {
    protected String type;          /**< Transport type. */
    protected Double latitude;      /**< Current latitude. */
    protected Double longitude;     /**< Current longitude. */

    /**
     * @brief Type Getter
     * @return String -> transport type
     */
    public String getType() { return type; }

    /**
     * @brief Latitude Getter
     * @return Double -> latitude value
     */
    public Double getLatitude() { return latitude; }

    /**
     * @brief Longitude Getter
     * @return Double -> longitude value
     */
    public Double getLongitude() { return longitude; }

    /**
     * @brief Coordinates Setter
     * @param latitude -> new latitude value
     * @param longitude -> new longitude value
     */
    public void setCoordinates(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
