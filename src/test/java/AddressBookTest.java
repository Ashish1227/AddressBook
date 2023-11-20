import org.example.Main;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddressBookTest {

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
        String expectedOutput = "Name: Tejdeep";
        assertTrue(outputStream.toString().trim().contains(expectedOutput));
    }
    @Test
    void testAddExit() {
        // Arrange
        String searchInput = "1\nsome\nname\n654\n211\ncheck@example.com\nsStreet\n62\ntrown\n12645\n0\n"; // Simulate searching for a user and then exiting

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
