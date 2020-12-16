package es.ull.esit.factories;

import es.ull.esit.transports.Freighter;
import es.ull.esit.transports.Transport;

import java.awt.geom.Point2D;

/**
 *  @class FreighterFactory
 *  @brief A concrete factory, which function is generate an Freighter
 *
 */

public class FreighterFactory implements TransportFactory {
    /**
     * @brief Overrided method capable to create a new Freighter
     * @return new Freighter
     */
    @Override
    public Transport createTransport() {
        return new Freighter();
    }
}
