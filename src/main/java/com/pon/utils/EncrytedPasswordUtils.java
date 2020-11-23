/**
 * 
 */
package com.pon.utils;

/**
 * @author Sanjeev Kumar
 * @Date   Dec 5, 2018
 * @Time   3:12:05 AM
 */
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncrytedPasswordUtils {
 
    // Encryte Password with BCryptPasswordEncoder
    public static String encrytePassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
 
    public static void main(String[] args) {
        String password = "123";
        String encrytedPassword = encrytePassword(password);
 
        System.out.println("Encryted Password: " + encrytedPassword);
    }
 
}
