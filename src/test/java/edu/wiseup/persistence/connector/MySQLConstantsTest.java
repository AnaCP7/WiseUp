package edu.wiseup.persistence.connector;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MySQLConstantsTest {

    @Test
    public void testPasswdConstant() {
        String passwd = MySQLConstants.PASSWD;
        assertEquals("jdbc.mysql.passwd", passwd);
    }

    @Test
    public void testUserConstant() {
        String user = MySQLConstants.USER;
        assertEquals("jdbc.mysql.user", user);
    }

    @Test
    public void testDriverConstant() {
        String driver = MySQLConstants.DRIVER;
        assertEquals("jdbc.mysql.driver", driver);
    }



}
