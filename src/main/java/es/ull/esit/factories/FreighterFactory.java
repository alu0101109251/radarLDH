package es.ull.esit.factories;

import es.ull.esit.products.Freighter;
import es.ull.esit.products.Transport;

public class FreighterFactory implements TransportFactory {

    @Override
    public Transport createTransport() {
        return new Freighter();
    }
}
