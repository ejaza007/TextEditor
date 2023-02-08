import java.io.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class DocumentIOTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class DocumentIOTest
{
    private DocumentIO dio;

    /**
     * Default constructor for test class DocumentIOTest
     */
    public DocumentIOTest()
    {
        dio = null;
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
        dio = new DocumentIO();
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
        dio = null;
    }

    @Test
    public void test_load_document_from_missing_file() {

        // load a file that does not exist
        try {
            dio.open_file("files/missing.txt");
            fail("files/missingtxt exists, but should not");
        } catch (IOException e) {
            // system appropriately fails
        }
    }

    @Test
    public void test_file_read() {

        try {

            assertTrue(dio.set_io_mode("input"));
            dio.open_file("files/test_file_22.txt");

            assertEquals("files/test_file_22.txt", dio.curr_file_name());

            assertTrue(dio.has_more_lines());
            assertEquals("012345", dio.read_line());
            assertTrue(dio.has_more_lines());
            assertEquals("012345", dio.read_line());
            assertTrue(dio.has_more_lines());
            assertEquals("012345", dio.read_line());
            assertTrue(dio.has_more_lines());
            assertEquals("012345", dio.read_line());
            assertTrue(dio.has_more_lines());
            assertEquals("012345", dio.read_line());
            assertFalse(dio.has_more_lines());

            dio.close_file();

        } catch (IOException e) {
            fail("File manipulation should not fail on valid file.\n" + e);
        }
    }

    @Test
    public void test_file_write() {
        try {

            assertTrue(dio.set_io_mode("output"));
            dio.open_file("files/test_file_21.txt");

            assertEquals("files/test_file_21.txt", dio.curr_file_name());

            assertFalse(dio.has_more_lines());
            assertEquals(null, dio.read_line());

            dio.write("hello");
            dio.write_line("hello");
            dio.write_line("newline hello");

            dio.close_file();

        } catch (IOException e) {
            fail("File manipulation should not fail.\n" + e);
        }

    }

    @Test
    public void test_file_write_to_read_modechange() {
        try {

            assertTrue(dio.set_io_mode("output"));
            dio.open_file("files/test_file_20.txt");

            assertEquals("files/test_file_20.txt", dio.curr_file_name());

            dio.write("hello");
            dio.write_line("hello");
            dio.write_line("newline hello");

            assertTrue(dio.set_io_mode("input"));    // should automatically close writer
            dio.open_file("files/test_file_22.txt");

            assertEquals("files/test_file_22.txt", dio.curr_file_name());

            assertTrue(dio.has_more_lines());        // should work as expected
            assertEquals("012345", dio.read_line());
            assertTrue(dio.has_more_lines());
            assertEquals("012345", dio.read_line());
            assertTrue(dio.has_more_lines());
            assertEquals("012345", dio.read_line());
            assertTrue(dio.has_more_lines());
            assertEquals("012345", dio.read_line());
            assertTrue(dio.has_more_lines());
            assertEquals("012345", dio.read_line());
            assertFalse(dio.has_more_lines());

            dio.close_file();

        } catch (IOException e) {
            fail("File manipulation should not fail.\n" + e);
        }
    }
}
