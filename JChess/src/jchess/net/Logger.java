package jchess.net;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public abstract class Logger {
    public static void log(String message){
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
        System.out.printf("[%s] %s\n", timeStamp, message);
    }
}
