/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package me.mycompany.sticky_rice_restaurant;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author WINDOWS
 */
public class DateUtil {
    
    
    private static final String PATTERN = "dd/MM/yyyy";

    public static Date parse(String input) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(PATTERN);
        return dateFormat.parse(input);
    }

    public static String format(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(PATTERN);
        return dateFormat.format(date);
    }
}
