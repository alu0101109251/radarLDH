package es.ull.esit.factories;

import es.ull.esit.transports.Transport;

/**
 *
 * @class TransportFactory
 * @brief Interface to represent an Abstract Factory
 *
 * @details This class provides methods to be implemented by concrete factories.
 *
 */
public interface TransportFactory {

    /**
     * @brief Virtual method for creating transports
     * @return Transport
     */
    Transport createTransport();
}
