package Logging;

import org.apache.log4j.MDC;



public class Mdc {
    public static void main(String[] args) {
        // Before Request
        MDC.put("userID", "John");

        // While Processing the Request
        // log4j/log4j2 configuration file to include userID in logging
        System.out.println(MDC.get("userID"));


        // After Request
        MDC.clear();
    }
}
