package es.ull.esit;

import es.ull.esit.builder.TransportBuilder;
import es.ull.esit.factories.CruiseShipFactory;
import es.ull.esit.factories.FreighterFactory;
import es.ull.esit.factories.OilTankerFactory;
import es.ull.esit.factories.TransportFactory;
import es.ull.esit.transports.Transport;
import es.ull.esit.utilities.CsvGenerator;
import es.ull.esit.utilities.LogFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConsoleMain {

    private static final Logger LOGGER = Logger.getLogger(ConsoleMain.class.getName());
    public static final String LINE_SEPARATOR = "----------------------";

    public static final int N_TRANSPORTS = 3;
    protected static final List<Transport> TRANSPORT_LIST = new ArrayList<>();
    public static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        ConsoleHandler handler = new ConsoleHandler();
        handler.setFormatter(new LogFormatter());
        LOGGER.addHandler(handler);
        LOGGER.setUseParentHandlers(false);

        defaultMenu();
    }

    public static void defaultMenu() {
        Scanner scanner = new Scanner(System.in);
        int input;

        do {
            LOGGER.info("Welcome");
            LOGGER.info(LINE_SEPARATOR);
            LOGGER.info("(1) Automatic Mode");
            LOGGER.info("(2) Manual Mode");
            LOGGER.info("(3) End");
            LOGGER.info(LINE_SEPARATOR);
            LOGGER.info("Please enter a number: ");

            input = scanner.nextInt();

            switch (input) {
                case 1:
                    automaticMode();
                    break;
                case 2:
                    manualMode();
                    break;
                case 3:
                    LOGGER.info("Bye");
                    break;
                default:
                    LOGGER.info("Invalid Option!");
                    break;
            }

        } while (input != 3);
    }

    private static void automaticMode() {
        generateRandomTransports(new CruiseShipFactory());
        generateRandomTransports(new FreighterFactory());
        generateRandomTransports(new OilTankerFactory());

        // Storing initial coordinates in CSV
        String fileName = "./transportsInput.csv";
        CsvGenerator.generateCsvFile(fileName, TRANSPORT_LIST);

        // Simulating transport tracking
        try {
            boolean iterate = true;

            while(iterate) {

                iterate = false;

                for(Transport t : TRANSPORT_LIST) {
                    LOGGER.info(t.toString());
                    if(t.move()) iterate = true;
                }

                LOGGER.info("\n");
                Thread.sleep(3000);
            }
        }
        catch (InterruptedException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
            Thread.currentThread().interrupt();
        }

        // Storing final coordinates in CSV
        String fileName1 = "./transportsOutput.csv";
        CsvGenerator.generateCsvFile(fileName1, TRANSPORT_LIST);
    }

    private static void manualMode() {
        int input;

        do {
            LOGGER.info("Manual Mode");
            LOGGER.info(LINE_SEPARATOR);
            LOGGER.info("(1) Create Ship");
            LOGGER.info("(2) Kill Ship");
            LOGGER.info("(3) Update Ship Position");
            LOGGER.info("(4) See Position Status");
            LOGGER.info("(5) Save Current Data to CSV");
            LOGGER.info("(6) End");
            LOGGER.info(LINE_SEPARATOR);
            LOGGER.info("Select desired operation: ");

            input = SCANNER.nextInt();

            switch (input) {
                case 1:
                    createShip();
                    break;
                case 2:
                    killShip();
                    break;
                case 3:
                    updateShip();
                    break;
                case 4:
                    showShipStatus();
                    break;
                case 5:
                    CsvGenerator.generateCsvFile("./manualOutput.csv", TRANSPORT_LIST);
                    break;
                case 6:
                    TRANSPORT_LIST.clear();
                    LOGGER.info("Exiting...");
                    break;
                default:
                    LOGGER.info("Invalid Option!");
                    break;
            }

        } while (input != 6);
    }

    private static void generateRandomTransports(TransportFactory factory) {
        for(int i = 0; i < N_TRANSPORTS; i++) {

            TransportBuilder builder = new TransportBuilder(factory);

            TRANSPORT_LIST.add(builder.getBuild());
        }
    }

    private static void createShip() {
        LOGGER.info("Please select ship type: ");
        LOGGER.info(LINE_SEPARATOR);
        LOGGER.info("(1) CruiseShip");
        LOGGER.info("(2) Freighter");
        LOGGER.info("(3) OilTanker");
        LOGGER.info(LINE_SEPARATOR);
        LOGGER.info("Select: ");

        TransportFactory factory;

        switch (SCANNER.nextInt()) {
            case 1:
                factory = new CruiseShipFactory();
                break;
            case 2:
                factory = new FreighterFactory();
                break;
            case 3:
                factory = new OilTankerFactory();
                break;
            default:
                throw new IllegalStateException("Unexpected value");
        }

        Transport transport = factory.createTransport();

        LOGGER.info(LINE_SEPARATOR);
        LOGGER.info("Enter initial coordinates: ");

        LOGGER.info("Latitude: ");
        double latitude = SCANNER.nextDouble();

        LOGGER.info("Longitude: ");
        double longitude = SCANNER.nextDouble();

        transport.setCurrentLocation(latitude, longitude);
        TRANSPORT_LIST.add(transport);

        LOGGER.info(LINE_SEPARATOR);
        LOGGER.info("Done!");
    }

    private static void killShip() {
        LOGGER.info("Please choose ship to delete: ");
        int input = getSelectedShip();

        TRANSPORT_LIST.remove(input);
        LOGGER.info("Removed!");
    }


    private static void showShipStatus() {
        LOGGER.info("Current Ship Locations");
        LOGGER.info(LINE_SEPARATOR);
        for(Transport t : TRANSPORT_LIST) {
            LOGGER.info(t.toString());
        }
        LOGGER.info(LINE_SEPARATOR);
    }

    private static void updateShip() {
        LOGGER.info("Please choose ship to update: ");
        int input = getSelectedShip();

        LOGGER.info(LINE_SEPARATOR);
        LOGGER.info("Enter new coordinates: ");

        LOGGER.info("Latitude: ");
        double latitude = SCANNER.nextDouble();

        LOGGER.info("Longitude: ");
        double longitude = SCANNER.nextDouble();

        TRANSPORT_LIST.get(input).setCurrentLocation(latitude, longitude);
        LOGGER.info(LINE_SEPARATOR);
        LOGGER.info("Removed!");
    }

    private static int getSelectedShip() {
        LOGGER.info(LINE_SEPARATOR);
        for(int i = 0; i < TRANSPORT_LIST.size(); i++) {
            LOGGER.info("(" + i + ") " + TRANSPORT_LIST.get(i).toString());
        }
        LOGGER.info(LINE_SEPARATOR);
        LOGGER.info("Select: ");

        return SCANNER.nextInt();
    }
}