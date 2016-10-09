package cz.kucicz.memsource;

import java.text.SimpleDateFormat;
import java.util.Date;

public class JSONDateParser {

    public static Date parse(String input) throws java.text.ParseException {

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz");

        //this is zero time so we need to add that TZ indicator for
        if (input.endsWith("Z")) {
            input = input.substring(0, input.length() - 1) + "GMT-00:00";
        } else {
            int inset = 5;

            String date = input.substring(0, input.length() - inset);
            String timezone = input.substring(input.length() - inset, input.length());
            //check timezone format
            if (!timezone.contains(":")) {
                timezone = timezone.substring(0, 3) + ":" + timezone.substring(3);
            }
            input = date + "GMT" + timezone;
        }

        return df.parse(input);
    }
}
