package es.ull.esit.factories;

import es.ull.esit.transports.CruiseShip;
import es.ull.esit.transports.Transport;

import java.awt.geom.Point2D;

/**
 *  @class CruiseShipFactory
 *  @brief A concrete factory, which function is generate an CruiseShip
 *
 */

public class CruiseShipFactory implements TransportFactory {
    /**
     * @brief Overrided method capable to create a new CruiseShip
     * @return new CruiseShip
     */
    @Override
    public Transport createTransport() {
        return new CruiseShip();
    }
}
