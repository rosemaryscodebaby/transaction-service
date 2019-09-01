package com.bingobucket.ticket.service;

import org.junit.Before;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/*
   Sample Correct Bingo Ticket Strip
*	15	22	34	*	60	69	*	*
9	*	*	37	45	52	*	*	89
*	18	*	*	*	56	65	75	83

10	20	*	33	50	*	*	*	81
*	*	28	39	*	57	64	71	*
4	*	*	32	*	*	66	79	82

*	*	26	31	*	*	68	78	87
6	*	25	35	*	53	*	*	86
8	17	*	*	49	*	70	*	90

5	13	27	38	41	*	*	*	*
1	12	24	*	*	*	61	80	*
*	*	23	*	48	59	62	*	84

*	*	21	40	46	54	*	*	85
2	14	29	*	43	*	*	74	*
7	16	*	*	44	*	67	72	*

*	11	*	*	42	58	63	77	*
3	*	30	36	*	55	*	73	*
*	19	*	*	47	51	*	76	88
 */
public abstract class TestDataProvider {

    protected List<Integer> list0, list1, list2, list3, list4, list5, list6, list7, list8;

    @Before
    public void setUp() {
        list0 = buildArr(-1, 9, -1, 10, -1, 4, -1, 6, 8, 5, 1, -1, -1, 2, 7, -1, 3, -1);
        list1 = buildArr(15, -1, 18, 20, -1, -1, -1, -1, 17, 13, 12, -1, -1, 14, 16, 11, -1, 19);
        list2 = buildArr(22, -1, -1, -1, 28, -1, 26, 25, -1, 27, 24, 23, 21, 29, -1, -1, 30, -1);
        list3 = buildArr(34, 37, -1, 33, 39, 32, 31, 35, -1, 38, -1, -1, 40, -1, -1, -1, 36, -1);
        list4 = buildArr(-1, 45, -1, 50, -1, -1, -1, -1, 49, 41, -1, 48, 46, 43, 44, 42, -1, 47);
        list5 = buildArr(60, 52, 56, -1, 57, -1, -1, 53, -1, -1, -1, 59, 54, -1, -1, 58, 55, 51);
        list6 = buildArr(69, -1, 65, -1, 64, 66, 68, -1, 70, -1, 61, 62, -1, -1, 67, 63, -1, -1);
        list7 = buildArr(-1, -1, 75, -1, 71, 79, 78, -1, -1, -1, 80, -1, -1, 74, 72, 77, 73, 76);
        list8 = buildArr(-1, 89, 83, 81, -1, 82, 87, 86, 90, -1, -1, 84, 85, -1, -1, -1, -1, 88);
    }

    protected Map<Integer, List<Integer>> buildTicketStrip() {
        Map<Integer, List<Integer>> data = new HashMap<>();
        data.put(0, list0);
        data.put(1, list1);
        data.put(2, list2);
        data.put(3, list3);
        data.put(4, list4);
        data.put(5, list5);
        data.put(6, list6);
        data.put(7, list7);
        data.put(8, list8);
        return data;
    }

    private List<Integer> buildArr(Integer... vals) {
        return Arrays.asList(vals);
    }

}
