package es.ull.esit.factories;

import es.ull.esit.transports.OilTanker;
import es.ull.esit.transports.Transport;

public class OilTankerFactory implements TransportFactory {
    @Override
    public Transport createTransport() {
        return new OilTanker();
    }
}
