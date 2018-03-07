package utilities;

import tuple.Tuple;

public class Test {
  public static void main(String[] args) {
    System.out.println(CalendarFunctions.timeStringToFloat("6:30"));
    System.out.println((int) 6.5);
    System.out.println(CalendarFunctions.timeFloatToString((float) 6.5));
    
    Tuple<String> time = new Tuple<>("00:00", "23:30", "Mon");
    System.out.println(CalendarFunctions.splitInterval(time, "01:00", "5:30"));
  }
}
