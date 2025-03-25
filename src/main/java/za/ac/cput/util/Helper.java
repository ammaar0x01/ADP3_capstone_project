package za.ac.cput.util;

public class Helper {
    public static boolean nullOrEmpty(String value) {


        if(value.isEmpty() ||value ==null){
            return true;
        }

        return false;
    }
}
