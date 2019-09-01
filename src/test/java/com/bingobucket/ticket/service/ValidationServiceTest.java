package com.bingobucket.ticket.service;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class ValidationServiceTest {

    private List<Integer> columnData = new ArrayList<>(Arrays.asList(new Integer[]{0,0,0,9,1,4,5,6,8,0,0,3,0,10,2,0,7,0}));

    @Test
    public void testValidateColumnNoDuplicaets() {
        Set<Integer> actualSet = new HashSet<>();
        assertEquals(columnData.stream().filter(x -> x == 0 || actualSet.add(x)).collect(Collectors.toList()).size(), columnData.size());
    }


}