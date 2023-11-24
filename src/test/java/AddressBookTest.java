import org.example.Main;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Random;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddressBookTest {
    private String randomName;
    private String randomSurname;
    private int randomPhone;
    private int randomMobilePhone;
    private String randomEmail;
    private String randomStreet;
    private int randomStreetNumber;
    private String randomTown;
    private int randomZipCode;

    private Random random;
    @Test
    void testMainWithExit() {
        // Arrange
        String input = "0\n";  // Simulate user entering '0' to exit
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Act
        try {
            Main.main(new String[]{});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Assert
        String expectedOutput = "Application terminating...";
        String actualOutput = outputStream.toString().trim();
        assertTrue(actualOutput.contains(expectedOutput),
                "Expected output:\n" + expectedOutput + "\nActual output:\n" + actualOutput);
    }

    @Test
    void testSearchExitContact() {
        // Arrange
        String input = "2\n 0\n 0\n";  // Simulate user entering contact information and then exiting
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Act
        try {
            Main.main(new String[]{});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Assert
        String expectedOutput = "Application terminating...";
        String actualOutput = outputStream.toString().trim();
        assertTrue(actualOutput.contains(expectedOutput),
                "Expected output:\n" + expectedOutput + "\nActual output:\n" + actualOutput);
        // Add more assertions based on your application's behavior
    }

    @Test
    void testPrintandExit() {
        // Arrange
        String searchInput = "3\n 0\n"; // Simulate searching for a user and then exiting

        InputStream originalSystemIn = System.in; // Store the original System.in
        System.setIn(new ByteArrayInputStream(searchInput.getBytes()));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Act
        try (Scanner scanner = new Scanner(System.in)) {
            Main.main(new String[]{});
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            // Restore the original System.in after the test
            System.setIn(originalSystemIn);
        }

        // Assert
        String expectedOutput = "Name:";
        assertTrue(outputStream.toString().trim().contains(expectedOutput));
    }
//    @Test
//    void testAddExit() {
//        // Arrange
//        String searchInput = "1\nsome\nname\n654\n211\ncheck@example.com\nsStreet\n62\ntrown\n12645\n0\n"; // Simulate searching for a user and then exiting
//
//        InputStream originalSystemIn = System.in; // Store the original System.in
//        System.setIn(new ByteArrayInputStream(searchInput.getBytes()));
//
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(outputStream));
//
//        // Act
//        try (Scanner scanner = new Scanner(System.in)) {
//            Main.main(new String[]{});
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        } finally {
//            // Restore the original System.in after the test
//            System.setIn(originalSystemIn);
//        }
//
//        // Assert
//        String expectedOutput = "Application terminating...";
//        assertTrue(outputStream.toString().trim().contains(expectedOutput));
//    }
    @Test
    void testAddExit() {
        // Arrange
//        Random random = new Random();
        randomName = generateRandomString();
        randomSurname = generateRandomString();
        randomPhone = generateRandomNumber();
        randomMobilePhone = generateRandomNumber();
        randomEmail = generateRandomString() + "@example.com";
        randomStreet = generateRandomString();
        randomStreetNumber = generateRandomNumber();
        randomTown = generateRandomString();
        randomZipCode = generateRandomNumber();

        String searchInput = String.format("1\n%s\n%s\n%d\n%d\n%s\n%s\n%d\n%s\n%d\n0\n",
                randomName, randomSurname, randomPhone, randomMobilePhone, randomEmail,
                randomStreet, randomStreetNumber, randomTown, randomZipCode);

        InputStream originalSystemIn = System.in; // Store the original System.in
        System.setIn(new ByteArrayInputStream(searchInput.getBytes()));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Act
        try (Scanner scanner = new Scanner(System.in)) {
            Main.main(new String[]{});
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            // Restore the original System.in after the test
            System.setIn(originalSystemIn);
        }

        // Assert
        String expectedOutput = "Application terminating...";
        assertTrue(outputStream.toString().trim().contains(expectedOutput));
    }

    private String generateRandomString()
    {
        int length = 8;
        Random random = new Random();
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder randomString = new StringBuilder();

        for (int i = 0; i < length; i++) {
            randomString.append(characters.charAt(random.nextInt(characters.length())));
        }

        return randomString.toString();
    }

    private int generateRandomNumber()
    {
        Random random = new Random();
        return random.nextInt(1000); // Adjust the range as needed
    }
    @Test
    void SearchNameAddExit()
    {
        String searchInput = String.format("2\n1\n%s\n%s\n0\n0\n",randomName,randomSurname);
        InputStream originalSystemIn = System.in; // Store the original System.in
        System.setIn(new ByteArrayInputStream(searchInput.getBytes()));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Act
        try (Scanner scanner = new Scanner(System.in)) {
            Main.main(new String[]{});
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            // Restore the original System.in after the test
            System.setIn(originalSystemIn);
        }

        // Assert
        String expectedOutput = "Application terminating...";
        assertTrue(outputStream.toString().trim().contains(expectedOutput));
    }
    @Test
    void SearchContactAddExit()
    {
        String searchInput = String.format("2\n1\n%d\n%d\n0\n0\n",randomPhone,randomMobilePhone);
        InputStream originalSystemIn = System.in; // Store the original System.in
        System.setIn(new ByteArrayInputStream(searchInput.getBytes()));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Act
        try (Scanner scanner = new Scanner(System.in)) {
            Main.main(new String[]{});
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            // Restore the original System.in after the test
            System.setIn(originalSystemIn);
        }

        // Assert
        String expectedOutput = "Application terminating...";
        assertTrue(outputStream.toString().trim().contains(expectedOutput));
    }

}
