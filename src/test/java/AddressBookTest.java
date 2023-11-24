import org.example.Main;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Random;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddressBookTest {
    private static String randomName;
    private static String randomSurname;
    private static int randomPhone;
    private static int randomMobilePhone;
    private static String randomEmail;
    private static String randomStreet;
    private static int randomStreetNumber;
    private static String randomTown;
    private static int randomZipCode;

    private Random random;

    private PrintStream originalSystemOut;
    private ByteArrayOutputStream outputStream;
    private static final String OUTPUT_FILE = "test_output.txt";
    private static final String OUTPUT_FILE1 = "test_output1.txt";

     @BeforeAll
     static void setUp()
     {
        randomName = generateRandomString();
        randomSurname = generateRandomString();
        randomPhone = generateRandomNumber();
        randomMobilePhone = generateRandomNumber();
        randomEmail = generateRandomString() + "@example.com";
        randomStreet = generateRandomString();
        randomStreetNumber = generateRandomNumber();
        randomTown = generateRandomString();
        randomZipCode = generateRandomNumber();
    }
    @BeforeEach
    void setfileUp() {
        // Save the original System.out
        originalSystemOut = System.out;

        // Redirect System.out to a ByteArrayOutputStream
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }
    void tearDown() {
        // Restore the original System.out after each test
        System.setOut(originalSystemOut);

        // Save the captured output to a file
        try (FileWriter writer = new FileWriter(OUTPUT_FILE)) {
            writer.write(outputStream.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    void tearDown1() {
        // Restore the original System.out after each test
        System.setOut(originalSystemOut);

        // Save the captured output to a file
        try (FileWriter writer = new FileWriter(OUTPUT_FILE1)) {
            writer.write(outputStream.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
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
    @Test
    void testAddExit() {
        // Arrange
        String searchInput = String.format("1\n%s\n%s\n%d\n%d\n%s\n%s\n%d\n%s\n%d\n0\n",
                randomName, randomSurname, randomPhone, randomMobilePhone, randomEmail,
                randomStreet, randomStreetNumber, randomTown, randomZipCode);

        InputStream originalSystemIn = System.in; // Store the original System.in
        System.setIn(new ByteArrayInputStream(searchInput.getBytes()));
        System.out.println(randomName);
        System.out.println(randomSurname);
        tearDown();
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

    private static String generateRandomString()
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

    private static int generateRandomNumber()
    {
        Random random = new Random();
        return random.nextInt(1000); // Adjust the range as needed
    }
    @Test
    void SearchNameAddExit()
    {
        String searchInput = String.format("2\n1\n%s\n%s\n0\n0\n",randomName,randomSurname);
//        String searchInput = String.format("2\n1\nAzJSOfnw\nIPEgxQlh\n0\n0\n0\n");
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
//    @Test
//    void SearchContactAddExit()
//    {
//        String searchInput = String.format("2\n1\n%d\n%d\n0\n0\n",randomPhone,randomMobilePhone);
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
    void DeleteNameAndExit()
    {
        System.out.println(randomName);
        System.out.println(randomSurname);
//        tearDown1();
        String searchInput = String.format("5\n1\n%s\n%s\n0\n0\n0\n",randomName,randomSurname);
//        String searchInput = String.format("5\n1\nAzJSOfnw\nIPEgxQlh\n0\n0\n0\n");
        InputStream originalSystemIn = System.in; // Store the original System.in
        System.setIn(new ByteArrayInputStream(searchInput.getBytes()));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Act
        try {
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

        tearDown1();
    }
}
