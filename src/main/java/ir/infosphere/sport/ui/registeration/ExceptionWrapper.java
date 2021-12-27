package ir.infosphere.sport.ui.registeration;

public class ExceptionWrapper extends Exception{
    public static String showMessage(Exception e) {

        if (e instanceof NameException) {
            return "";
        } else {
            return "";
        }
    }
}
