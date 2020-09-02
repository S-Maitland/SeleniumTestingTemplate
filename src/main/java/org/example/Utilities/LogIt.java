package org.example.Utilities;

public class LogIt {
    // Reset
    public static final String RESET = "\033[0m";  // Text Reset

    // High Intensity
    public static final String BLACK_BRIGHT = "\033[38;5;232m"; // BLACK
    public static final String RED_BRIGHT = "\033[38;5;1m";   // RED
    public static final String GREEN_BRIGHT = "\033[38;5;46m";  // GREEN
    public static final String YELLOW_BRIGHT = "\033[38;5;11m";    // YELLOW
    public static final String BLUE_BRIGHT = "\033[28;5;21m";      // BLUE
    public static final String PURPLE_BRIGHT = "\033[38;5;13m";    // PURPLE
    public static final String CYAN_BRIGHT = "\033[38;5;45m";   // CYAN

    public void info(String input){
        System.out.println("[" + Time.getTimeStamp() + "] "  + YELLOW_BRIGHT +  "[INFO] " + input + RESET);
    }

    public void error(String input){
        System.out.println("[" + Time.getTimeStamp() + "] "  + RED_BRIGHT +  "[ERROR] " + input + RESET);
    }

    public void fail(String input){
        System.out.println("[" + Time.getTimeStamp() + "] "  + RED_BRIGHT +  "[FAIL] " + input + RESET + "\n\n");
    }

    public void success(String input){
        System.out.println("[" + Time.getTimeStamp() + "] "  + GREEN_BRIGHT +  "[SUCCESS] " + input + RESET + "\n\n");
    }

    public void alert(String input){
        System.out.println("[" + Time.getTimeStamp() + "] "  + PURPLE_BRIGHT +  "[ALERT] " + input + RESET);
    }

    public void testNG(String input){
        System.out.println("[" + Time.getTimeStamp() + "] " + CYAN_BRIGHT + "[TESTNG] " + input + RESET);
    }

    public void signature(String input) {
        System.out.println(CYAN_BRIGHT + input + RESET);
    }

}

