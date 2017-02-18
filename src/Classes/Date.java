package Classes;

public class Date {
    private int day = -1;
    private int month = -1;
    private int upperLimit = 31;
    private String[] months;
    private int[] separatedMonths;
    private int year = -1;
    private int[][] map;

    public Date(int day, int month, int year) {
        init();
        setYear(year);
        setMonth(month);
        setDay(day);
    }

    public Date(String date) {
        init();
        isValid(date);
        setDate(date);
    }

    Date(String month, int day, int year) {
        init();
        setYear(year);
        setMonth(findMonth(month));
        setDay(day);
    }

    Date(int day, int year) {
        setYear(year);
        init(year);
        separateDay(day);
    }

    private void separateDay(int day) {
        if (day > 0 && day < 31) {
            setMonth(1);
            setDay(day);
        } else {
            for (int i = separatedMonths.length - 1; i >= 0; i--) {
                if (day >= separatedMonths[i]) {
                    setMonth(i);
                    break;
                }
            }
            setDay(day - separatedMonths[month]);
        }
    }

    private int findMonth(String month){
        for(int i = 0; i<months.length; i++)
            if(months[i].equals(month))
               return i+1;
        return -1;
    }

    private void init() {
        map = new int[][]{{1, 3, 5, 7, 8, 10, 12}, {4, 6, 9, 11}, {2}};
        months = new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    }

    private void init(int year) {
        int temp = year % 4 == 0 ? 29 : 28;
        separatedMonths = new int[]{0, 31, temp, temp + 31, temp + 61, temp + 92, temp + 122, temp + 153, temp + 184, temp + 214, temp + 245, temp + 275, temp + 306};
        init();
    }

    private void setDate(String date) {
        setDay(Integer.parseInt(date.substring(0, 2)));
        setMonth(Integer.parseInt(date.substring(3, 5)));
        setYear(Integer.parseInt(date.substring(6, date.length())));
    }

    private static void isValid(String date) {
        String regex = "\\d{2}/\\d{2}/\\d{4}";
        if (!date.matches(regex))
            throw new NullPointerException("Invalid date input");
    }

    private int getDayLimit() {
        int limit = 31;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < map[i].length; j++)
                if (month == map[i][j])
                    limit = i;
        if (limit == 0)
            limit = 31;
        else if (limit == 1)
            limit = 30;
        else if (limit == 2) {
            if (year % 4 == 0)
                limit = 29;
            else
                limit = 28;
        }
        return limit;
    }

    public String toString() {
        return String.format("%02d/%02d/%04d", day, month, year);
    }

    public String toString(String statement) {
        String string = "";
        boolean isDay = true;
        boolean isMonth = true;
        boolean isYear = true;

        for (int i = 0; i < statement.length(); i++)
            if (statement.charAt(i) == 'D' && isDay) {
                string += day + "/";
                isDay = false;
            } else if (statement.charAt(i) == 'M' && isMonth) {
                if (i < statement.length() - 1 && statement.charAt(i + 1) == 'M') {
                    string += months[month - 1] + "/";
                    i++;
                } else
                    string += month + "/";
                isMonth = false;
            } else if (statement.charAt(i) == 'Y' && isYear) {
                string += year + "/";
                isYear = false;
            }
        return string.substring(0,string.length() - 1);
    }

    private void setDay(int day) {
        if (month != -1)
            upperLimit = getDayLimit();
        if (day > 0 && day <= upperLimit)
            this.day = day;
        else
            throw new NullPointerException("Day is out of a range");
    }

    private void setMonth(int month) {
        if (month > 0 && month < 13)
            this.month = month;
        else
            throw new NullPointerException("Month is out of a range");
    }

    private void setYear(int year) {
        if (year > 0)
            this.year = year;
        else
            throw new NullPointerException("Year is out of a range");
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public String getMonthName(){
        return months[month - 1];
    }

    public int getYear() {
        return year;
    }
}
