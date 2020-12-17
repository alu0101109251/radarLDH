package es.ull.esit.factories;

import es.ull.esit.transports.OilTanker;
import es.ull.esit.transports.Transport;

/**
 *  @class OilTankerFactory
 *  @brief A concrete factory, which function is generate an OilTanker
 *
 */

public class OilTankerFactory implements TransportFactory {
    /**
     * @brief Override method capable to create a new OilTanker
     * @return new OilTanker
     */
    @Override
    public Transport createTransport() {
        return new OilTanker();
    }
}
