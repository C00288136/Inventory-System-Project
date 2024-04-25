package loginInterface;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class loginLogicTest {

    @Test
    void authenticateUser() {

        String username = "admin";
        String password = loginLogic.hashPassword("admin");

        String expectedOutput = loginLogic.authenticateUser(username,password);

        assertEquals(expectedOutput,username);
    }

    @Test
    void hashPassword() {

        String password = "admin";
        String hashedPassword = loginLogic.hashPassword(password);

        String expectedHash = "21232f297a57a5a743894a0e4a801fc3";

        assertEquals(expectedHash,hashedPassword);
    }
}