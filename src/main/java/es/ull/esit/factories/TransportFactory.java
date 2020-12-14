package es.ull.esit.factories;

import es.ull.esit.transports.Transport;

public interface TransportFactory {
    Transport createTransport();
}
