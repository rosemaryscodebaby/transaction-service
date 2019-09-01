package com.bingobucket.ticket.configuration;

public class Constants {
    public static final int BLANKS_PER_COLUMN = 8;
    public static final int BLANKS_PER_ROW = 4;
    public static final String[] COLUMN_INDEXES =  "0,1,2,3,4,5,6,7,8".split(",");
    public static final int COLUMN_LENGTH_OF_EACH_TICKET = 3;
    public static final int COMBINED_COLUMN_LENGTH = 18;
    public static final int NUMBERS_PER_COLUMN = COMBINED_COLUMN_LENGTH - BLANKS_PER_COLUMN;
    public static final int ROW_LENGTH_OF_EACH_TICKET = 9;
    public static final int TOTAL_NUMBER_OF_TICKETS = 6;

}
