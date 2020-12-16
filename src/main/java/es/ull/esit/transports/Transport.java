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
    protected String type;              /**< Transport type. */

    protected Point2D startLocation;    /**< Start location of the transport path. */
    protected Point2D endLocation;
    protected Point2D currentLocation;

    protected ArrayList<Point2D> path = new ArrayList<>();

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

    public void move() {
        if(currentPathIndex < path.size()) {
            currentLocation.setLocation(path.get(currentPathIndex++));
        }
    }

    public void setStartLocation(double latitude, double longitude) {
        this.startLocation = new Point2D.Double(latitude, longitude);
        setCurrentLocation(latitude, longitude);
        currentPathIndex = 0;
    }

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
