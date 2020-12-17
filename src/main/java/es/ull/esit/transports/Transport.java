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

    @Override
    public String toString() {
        return "Transport{" +
                "type='" + type + '\'' +
                ", currentLocation=" + currentLocation +
                '}';
    }

    /**
     * @brief Returns number of path's positions
     * @return int -> path size
     */
    public int pathSize() {
        return path.size();
    }

    /**
     * @brief currentPathIndex Getter
     * @return int -> currentPathIndex value
     */
    public int getCurrentPathIndex() {
        return currentPathIndex;
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

        int xI = Math.round((float) startLocation.getX());
        int yI = Math.round((float) startLocation.getY());

        int xF = Math.round((float) endLocation.getX());
        int yF = Math.round((float) endLocation.getY());

        // Calculate straight line equation
        float m = (float) (yF - yI) / (float) (xF - xI);
        float c = yI - m*xI;

        assignLine(xI, yI, xF, yF, m, c);
    }

    /**
     * @brief Auxiliary method capable to assign a straight line points
     * @param xI -> int value of x-coordinate
     * @param yI -> int value of y-coordinate
     * @param xF -> float value of x-coordinate
     * @param yF -> float value of y-coordinate
     * @param m  -> float value from slope of the line
     * @param c  -> float value that represents the interception with y-axys
     */
    private void assignLine(int xI, int yI, int xF, int yF, float m, float c) {
        // Determine dependent variable
        int xDelta = Math.abs(xF - xI);
        int yDelta = Math.abs(yF - yI);

        // Assign straight line points
        if(xDelta >= yDelta) {
            // y = mx + c

            for(int x = Math.min(xI, xF), j = 0; x <= Math.max(xI, xF); x++, j++) {
                int y = Math.round(m*x + c);
                path.add(new Point2D.Double(x, y));
            }
        }
        else {
            // x = (y - c) / m

            for(int y = Math.min(yI, yF), j = 0; y <= Math.max(yI, yF); y++, j++) {
                int x = Math.round((y - c) / m);
                path.add(new Point2D.Double(x, y));
            }
        }
        path.add(endLocation);
    }
}