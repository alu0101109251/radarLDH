package es.ull.esit;

import es.ull.esit.factories.CruiseShipFactory;
import es.ull.esit.factories.FreighterFactory;
import es.ull.esit.factories.OilTankerFactory;
import es.ull.esit.factories.TransportFactory;
import es.ull.esit.products.Transport;
import es.ull.esit.utilities.CsvGenerator;

import java.io.IOException;
import java.util.ArrayList;

public class RadarMain {

    public static final int N_TRANSPORTS = 5;
    private final ArrayList<Transport> transports = new ArrayList<>();

    public RadarMain() {
        generateRandomTransports(new CruiseShipFactory());
        generateRandomTransports(new FreighterFactory());
        generateRandomTransports(new OilTankerFactory());
    }

    public ArrayList<Transport> getTransports() {
        return transports;
    }

    // TODO :
    // https://stackoverflow.com/questions/25264684/how-to-generate-random-latitude-and-longitude-in-a-city-using-java

    private void generateRandomTransports(TransportFactory factory) {
        for(int i = 0; i < N_TRANSPORTS; i++) {
            double latitude = (Math.random() * 180.0) - 90.0;
            double longitude = (Math.random() * 360.0) - 180.0;

            Transport t = factory.createTransport();
            t.setCoordinates(latitude, longitude);

            transports.add(t);
        }
    }

    public static void main(String[] args) {
        RadarMain radarMain = new RadarMain();
        CsvGenerator.generateCsvFile("./src/main/java/es/ull/esit/utilities/transports.csv", radarMain.getTransports());
    }
}
