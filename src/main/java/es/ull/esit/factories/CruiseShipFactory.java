package es.ull.esit.factories;

import es.ull.esit.transports.CruiseShip;
import es.ull.esit.transports.Transport;

/**
 *  @class CruiseShipFactory
 *  @brief A concrete factory, which function is generate a CruiseShip
 *
 */

public class CruiseShipFactory implements TransportFactory {
    /**
     * @brief Override method capable to create a new CruiseShip
     * @return new CruiseShip
     */
    @Override
    public Transport createTransport() {
        return new CruiseShip();
    }
}
