package es.ull.esit;

import es.ull.esit.builder.TransportBuilder;
import es.ull.esit.factories.CruiseShipFactory;
import es.ull.esit.factories.FreighterFactory;
import es.ull.esit.factories.OilTankerFactory;
import es.ull.esit.factories.TransportFactory;
import es.ull.esit.transports.CruiseShip;
import es.ull.esit.transports.Transport;
import es.ull.esit.utilities.CsvGenerator;
import es.ull.esit.utilities.WaterCoordinatesGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.function.Executable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Radar Application Testing")
public class RadarTest {

    @DisplayName("Testing Transports")
    @Nested
    class TransportTest {

        private Transport cruise;

        @BeforeEach
        void setUp() {
            cruise = new CruiseShip();
            cruise.setStartLocation(28.453323, -16.029710);
            cruise.setEndLocation(30.5678, -24.0485);
            cruise.setPath();
        }

        @Test
        @DisplayName("Class Getters")
        void testTransportGetters() {
            assertAll("Verify all attributes are set correctly",
                    () -> assertEquals("CruiseShip", cruise.getType(), "Transport Type"),
                    () -> assertEquals(28.453323, cruise.getLatitude(), "Transport Latitude"),
                    () -> assertEquals(-16.029710, cruise.getLongitude(), "Transport Longitude")
            );
        }

        @Test
        @DisplayName("Class Methods")
        void testTransportMethods() {
            String expectedToString = "Transport{type='CruiseShip', currentLocation=Point2D.Double[28.453323, -16.02971]}";
            assertAll("Verify we can update a transport location",
                    () -> assertEquals(expectedToString, cruise.toString(), "Transport toString"),
                    () -> assertTrue(cruise.move(), "Moving transport"),
                    () -> {
                        for(int i = 0; i < 10000; i++)
                            cruise.move();
                        assertFalse(cruise.move(), "Transport shouldn't move if end reached");
                    }
            );
        }
    }

    @DisplayName("Testing Factories")
    @Nested
    class FactoryTest {

        private Transport freighter;
        private Transport cruiseShip;
        private Transport oilTanker;

        @BeforeEach
        void setUp() {
            TransportFactory freighterFactory = new FreighterFactory();
            TransportFactory cruiseShipFactory = new CruiseShipFactory();
            TransportFactory oilTankerFactory = new OilTankerFactory();

            freighter = freighterFactory.createTransport();
            cruiseShip = cruiseShipFactory.createTransport();
            oilTanker = oilTankerFactory.createTransport();
        }

        @Test
        @DisplayName("Factory Method")
        void testFactoryMethod() {
            assertAll("Verify we can construct transports with factory",
                    () -> assertEquals("Freighter", freighter.getType(), "Transport Type"),
                    () -> assertEquals("CruiseShip", cruiseShip.getType(), "Transport Type"),
                    () -> assertEquals("OilTanker", oilTanker.getType(), "Transport Type"),
                    () -> assertThrows(NullPointerException.class, ()-> freighter.getLongitude(), "Factory does not set coordinates")
            );
        }
    }

    @DisplayName("Testing Builders")
    @Nested
    class BuilderTest {

        private Transport oilTanker;

        @BeforeEach
        void setUp() {
            TransportBuilder builder = new TransportBuilder(new OilTankerFactory());
            oilTanker = builder.getBuild();
        }

        @Test
        @DisplayName("Factory Method")
        void testFactoryMethod() {
            assertAll("Verify the transport is constructed with the factory",
                    () -> assertEquals("OilTanker", oilTanker.getType(), "Transport Type"),
                    () -> assertTrue(oilTanker.move(), "Builder should set coordinates")
            );
        }
    }

    @DisplayName("Testing Utilities")
    @Nested
    class UtilitiesTest {

        @Test
        @DisplayName("Water Coordinates")
        void testWaterCoordinatesGenerator() {
            assertDoesNotThrow((Executable) WaterCoordinatesGenerator::getOceanCoordinates, "Verify we can obtain water coordinates from JSON");
        }

        @Test
        @DisplayName("CSV Generator")
        void testCsvGenerator() {
            List<Transport> testList = new ArrayList<>();
            testList.add(new TransportBuilder(new OilTankerFactory()).getBuild());
            assertAll("Verify the CSV generator works properly",
                    () -> assertDoesNotThrow(() -> CsvGenerator.generateCsvFile("dummy.csv", testList), "Verify we save a CSV file"),
                    () -> assertThrows(NullPointerException.class, ()-> CsvGenerator.generateCsvFile(null, testList), "Testing invalid filename")
            );
        }
    }
}
