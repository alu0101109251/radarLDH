package es.ull.esit.transports;

import java.awt.geom.Point2D;
import java.util.ArrayList;

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

    private static final double N_INCREMENT = 0.05;  /**< Constant to define location increment. */

    protected String type;              /**< Concrete transport type. */

    protected Point2D startLocation;    /**< Initial path transport coordinates location. */
    protected Point2D endLocation;      /**< Final path transport coordinates location. */
    protected Point2D currentLocation;  /**< Current transport coordinates location. */

    protected ArrayList<Point2D> path = new ArrayList<>();  /**< Point locations which define the transport path. */
    protected int currentPathIndex;                         /**< Index to surf on the attribute path. */

    /**
     * @brief Type Getter
     * @return String -> transport type
     */
    public String getType() { return type; }

    /**
     * @brief Latitude Getter
     * @return Double -> latitude value
     */
    public Double getLatitude() { return currentLocation.getX(); }

    /**
     * @brief Longitude Getter
     * @return Double -> longitude value
     */
    public Double getLongitude() { return currentLocation.getY(); }

    /**
     * @brief This method will be able to update the current transport position in the map
     * @return boolean -> returns true if the transport updated its location, false otherwise.
     */
    public boolean move() {
        currentPathIndex++;
        if(currentPathIndex < path.size()) {
            currentLocation.setLocation(path.get(currentPathIndex));
            return true;
        }
        return false;
    }

    /**
     * @brief Generates a string representation of the object
     * @return String -> formatted string
     */
    @Override
    public String toString() {
        return "Transport{" +
                "type='" + type + '\'' +
                ", currentLocation=" + currentLocation +
                '}';
    }

    /**
     * @brief startLocation setter
     * @param latitude -> first latitude location
     * @param longitude -> current longitude location
     */
    public void setStartLocation(double latitude, double longitude) {
        this.startLocation = new Point2D.Double(latitude, longitude);
        setCurrentLocation(latitude, longitude);
        currentPathIndex = 0;
    }

    /**
     * @brief endLocation setter
     * @param latitude -> end latitude location
     * @param longitude -> end longitude location
     */
    public void setEndLocation(double latitude, double longitude) {
        this.endLocation = new Point2D.Double(latitude, longitude);
    }

    /**
     * @brief Current coordinates setter
     * @param latitude -> current latitude location
     * @param longitude -> current longitude location
     */
    public void setCurrentLocation(double latitude, double longitude) {
        this.currentLocation = new Point2D.Double(latitude, longitude);
    }

    /**
     * @brief Calculates the transport path
     * @details Given start and end location, this method calculates the straight line equation
     * between them and store all the points in the path array.
     */
    public void setPath() {
        path.add(startLocation);

        double xI = startLocation.getX();
        double yI = startLocation.getY();

        double xF = endLocation.getX();
        double yF = endLocation.getY();

        // Calculate straight line equation
        double m = (yF - yI) / (xF - xI);
        double c = yI - m*xI;

        // y = mx + c
        double increment = (xF<xI? -N_INCREMENT:N_INCREMENT);

        for(double x = xI; (x>xF?(x - xF > increment): (x -xF < increment)); x+= increment) {
            double y = m * x + c;
            path.add(new Point2D.Double(x, y));
        }
        path.add(endLocation);
    }
}