package es.ull.esit.builder;

import es.ull.esit.factories.TransportFactory;
import es.ull.esit.transports.Transport;
import es.ull.esit.utilities.WaterCoordinatesGenerator;

import java.awt.geom.Point2D;

/**
 *
 * @class TransportBuilder
 * @brief Class to encapsulate the creation of transport objects.
 *
 * @details This class receives a TransportFactory and creates the desired transport object.
 *
 */
public class TransportBuilder {
    private final Transport transport;  /**< Abstract transport to get built. */

    /**
     * @brief Class Constructor
     * @details Instantiates a concrete transport using a given factory
     * and calls the necessary setters to build the object.
     * @param factory -> concrete transport factory
     */
    public TransportBuilder(TransportFactory factory) {
        Point2D[] coordinates = WaterCoordinatesGenerator.getOceanCoordinates();

        assert coordinates != null;
        Point2D startLocation = coordinates[0];
        Point2D endLocation = coordinates[1];

        transport = factory.createTransport();

        transport.setStartLocation(startLocation.getX(), startLocation.getY());
        transport.setEndLocation(endLocation.getX(), endLocation.getY());
        transport.setPath();
    }

    /**
     * @brief Final object getter
     * @return Transport -> built transport
     */
    public Transport getBuild(){
        return transport;
    }
}
