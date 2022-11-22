package edu.kpi.utils;

import org.mindrot.jbcrypt.BCrypt;

public class BCryptUtils {

    public static String hashPassword(final String password) {

        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean checkPassword(final String password, final String hash) {

        return BCrypt.checkpw(password, hash);
    }
}
