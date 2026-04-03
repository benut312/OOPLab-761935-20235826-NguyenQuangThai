import java.util.Scanner;

public class DaysInMonth {

    public static int getMonthNumber(String monthInput) {
        if (monthInput == null) return -1;

        String m = monthInput.trim().toLowerCase();

        switch (m) {
            case "1":
            case "january":
                return 1;
            case "2":
            case "february":
      
                return 2;
            case "3":
            case "march":
        
                return 3;
            case "4":
            case "april":
           
                return 4;
            case "5":
            case "may":
                return 5;
            case "6":
            case "june":
        
                return 6;
            case "7":
            case "july":
           
                return 7;
            case "8":
            case "august":
     
                return 8;
            case "9":
            case "september":
                return 9;
            case "10":
            case "october":
     
                return 10;
            case "11":
            case "november":
                return 11;
            case "12":
            case "december":
           
                return 12;
            default:
                return -1;
        }
    }

    public static boolean isLeapYear(int year) {
        return (year % 400 == 0) || (year % 4 == 0 && year % 100 != 0);
    }

    public static int getDaysInMonth(int month, int year) {
        switch (month) {
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                return 31;
            case 4: case 6: case 9: case 11:
                return 30;
            case 2:
                return isLeapYear(year) ? 29 : 28;
            default:
                return -1;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int month;
        int year;
        while (true) {
            System.out.print("Enter month: ");
            String monthInput = scanner.nextLine();
            month = getMonthNumber(monthInput);

            if (month != -1) {
                break;
            }
            System.out.println("Invalid month. Try again");
        }

        while (true) {
            System.out.print("Enter year: ");
            if (scanner.hasNextInt()) {
                year = scanner.nextInt();
                if (year >= 0) {
                    break;
                }
            } else {
                scanner.next();
             System.out.println("Invalid year.Try again");
            }
        }

        int days = getDaysInMonth(month, year);
        System.out.println("Month " + month + " of year " + year + " has " + days + " days.");

        scanner.close();
    }
}
