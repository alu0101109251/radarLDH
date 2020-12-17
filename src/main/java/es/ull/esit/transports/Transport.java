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
    /**
     * @brief This atribute represents the kind of the transport
     */
    protected String type;
    /**
     * @brief Storages in a 2D coordinates about where was created the
     * transport in the map
     */
    protected Point2D startLocation;
    /**
     * @brief Storages in a 2D coordinates about where will have arrive
     * the transport in the map just finishing its path.
     */
    protected Point2D endLocation;
    /**
     * @brief Storages in a 2D coordinates about in what part of the map
     * is the transport
     */
    protected Point2D currentLocation;
    /**
     * @brief Storages in an essential 2D coordinates set, due to,
     * all the points of path will be included here.
     */
    protected ArrayList<Point2D> path = new ArrayList<>();
    /**
     * @brief Index to surf on the attribute path_
     * all the points of path will be included here.
     */
    protected int currentPathIndex;
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
     * @brief This method will be able to refresh the position
     * transport in the map
     */
    public void move() {
        currentPathIndex++;
        if(currentPathIndex < path.size()) {
            currentLocation.setLocation(path.get(currentPathIndex));
        }
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
     * @brief Method capable to assign a straight line points
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

