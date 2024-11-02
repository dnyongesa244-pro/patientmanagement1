package patientmanagement1;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class FormatDate {
    public String format(Date date) {
        System.out.println("format started");
        String formatted = "";
        try {
            // Convert Date to LocalDate
            Instant instant = date.toInstant();
            LocalDate localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();

            // Use a built-in formatter for the desired output
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            formatted = localDate.format(outputFormatter);    
            System.out.println(formatted);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return formatted;
    }
}
