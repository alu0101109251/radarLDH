package es.ull.esit.factories;

import es.ull.esit.transports.Freighter;
import es.ull.esit.transports.Transport;

/**
 *  @class FreighterFactory
 *  @brief A concrete factory, which function is generate a Freighter
 *
 */

public class FreighterFactory implements TransportFactory {
    /**
     * @brief Override method capable to create a new Freighter
     * @return new Freighter
     */
    @Override
    public Transport createTransport() {
        return new Freighter();
    }
}
