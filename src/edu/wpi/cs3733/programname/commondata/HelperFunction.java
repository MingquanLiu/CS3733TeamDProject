package edu.wpi.cs3733.programname.commondata;

public class HelperFunction {
    public static String convertFloor(int floor){
        String mString = "";
        switch (floor){
            case 3:
                mString = "3";
                break;
            case 2:
                mString = "2";
                break;
            case 1:
                mString = "1";
                break;
            case 0:
                mString = "G";
                break;
            case -1:
                mString = "L1";
                break;
            case -2:
                mString = "L2";
                break;
        }
        return mString;
    }
}
