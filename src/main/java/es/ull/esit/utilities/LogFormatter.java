package es.ull.esit.utilities;

import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.SimpleFormatter;

/**
 *
 * @class LogFormatter
 * @brief Class to format LOGGER info output
 *
 * @details This class override format method to display only message in info logging output.
 *
 */
public final class LogFormatter extends SimpleFormatter {
    /**
     *
     * @param record -> log object to be formatted
     * @return String -> formatted log
     */
    @Override
    public String format(LogRecord record){
        if(record.getLevel() == Level.INFO) {
            return record.getMessage() + "\r\n";
        }
        else {
            return super.format(record);
        }
    }
}
