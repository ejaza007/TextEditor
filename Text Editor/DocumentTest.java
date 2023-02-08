import java.io.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class DocumentTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class DocumentTest
{
    private Document doc;

    /**
     * Default constructor for test class DocumentTest
     */
    public DocumentTest()
    {
        doc = null;
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
        doc = new Document("gap");
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
        doc = null;
    }

    @Test
    public void test_load_document_from_file() {

        // load an existing file
        try {
            doc.load_file("files/test_file_00.txt");
        } catch (IOException e) {
            fail("files/test_file_00.txt does not exist, but should");
        }

        // verify the loaded information
        assertEquals("abcdef||klme|28932|",    doc.toStringDocument());
        assertEquals("files/test_file_00.txt", doc.current_file_name());

        assertEquals("abcdef", doc.toStringLine(0));           // test string and line length
        assertEquals("",       doc.toStringLine(1));
        assertEquals("klme",   doc.toStringLine(2));
        assertEquals("28932",  doc.toStringLine(3));
        assertEquals("",       doc.toStringLine(4));

        assertEquals(6, doc.line_length(0));
        assertEquals(0, doc.line_length(1));
        assertEquals(4, doc.line_length(2));
        assertEquals(5, doc.line_length(3));
        assertEquals(0, doc.line_length(4));

        assertEquals(5, doc.line_count());
    }

    @Test
    public void test_document_cursor_moves() {

        // load an existing file
        try {
            doc.load_file("files/test_file_02.txt");
        } catch (IOException e) {
            fail("files/test_file_02.txt does not exist, but should");
        }

        // verify the loaded information
        assertEquals("012345|012345|012345|012345|012345", doc.toStringDocument());
        assertEquals("files/test_file_02.txt",             doc.current_file_name());
        assertEquals("04_00", doc.curr_cursor_string());
        
        assertFalse(doc.cursor_down());                 // test the different cursor movements
        assertEquals("04_00", doc.curr_cursor_string());
        
        assertTrue(doc.cursor_up());
        assertEquals("03_00", doc.curr_cursor_string());
        
        assertTrue(doc.cursor_up(3));
        assertEquals("00_00", doc.curr_cursor_string());

        assertFalse(doc.cursor_up());
        assertEquals("00_00", doc.curr_cursor_string());
        
        assertTrue(doc.cursor_down(3));
        assertEquals("03_00", doc.curr_cursor_string());
        
        assertTrue(doc.cursor_move_first_line());
        assertEquals("00_00", doc.curr_cursor_string());
        
        assertTrue(doc.cursor_move_last_line());
        assertEquals("04_00", doc.curr_cursor_string());

        assertTrue(doc.cursor_up(2));
        assertEquals("02_00", doc.curr_cursor_string());

        assertFalse(doc.cursor_left());
        assertEquals("02_00", doc.curr_cursor_string());

        assertTrue(doc.cursor_right());
        assertEquals("02_01", doc.curr_cursor_string());

        assertTrue(doc.cursor_right(2));
        assertEquals("02_03", doc.curr_cursor_string());

        assertTrue(doc.cursor_move_end_line());
        assertEquals("02_06", doc.curr_cursor_string());

        assertFalse(doc.cursor_right());
        assertEquals("02_06", doc.curr_cursor_string());

        assertTrue(doc.cursor_move_start_line());
        assertEquals("02_00", doc.curr_cursor_string());
    }

    @Test
    public void test_document_insert_lines() {

        // load an existing file
        try {
            doc.load_file("files/test_file_02.txt");
        } catch (IOException e) {
            fail("files/test_file_02.txt does not exist, but should");
        }

        // verify the loaded information
        assertEquals("012345|012345|012345|012345|012345", doc.toStringDocument());
        assertEquals("files/test_file_02.txt",             doc.current_file_name());

        assertTrue(doc.cursor_up(3));                     // test the line movement and manipulations
        assertEquals("01_00", doc.curr_cursor_string());

        assertTrue(doc.insert_line_above());
        assertEquals("01_00", doc.curr_cursor_string());
        assertEquals("012345||012345|012345|012345|012345", doc.toStringDocument());

        assertTrue(doc.cursor_down());
        assertEquals("02_00", doc.curr_cursor_string());

        assertTrue(doc.insert_line_below());
        assertEquals("03_00", doc.curr_cursor_string());
        assertEquals("012345||012345||012345|012345|012345", doc.toStringDocument());
    }

    @Test
    public void test_document_remove_insert_methods() {

        // load an existing file
        try {
            doc.load_file("files/test_file_02.txt");
        } catch (IOException e) {
            fail("files/test_file_02.txt does not exist, but should");
        }

        // verify the loaded information
        assertEquals("012345|012345|012345|012345|012345", doc.toStringDocument());
        assertEquals("files/test_file_02.txt",             doc.current_file_name());

        assertTrue(doc.cursor_up(3));                       // the different string manipulations
        assertEquals("01_00", doc.curr_cursor_string());

        assertTrue(doc.remove_line());
        assertEquals("01_00", doc.curr_cursor_string());
        assertEquals("012345|012345|012345|012345", doc.toStringDocument());

        assertTrue(doc.cursor_right(4));
        assertEquals("01_04", doc.curr_cursor_string());
        assertTrue(doc.remove_char_toleft());
        assertEquals("012345|01245|012345|012345", doc.toStringDocument());

        assertEquals("01_03", doc.curr_cursor_string());
        assertTrue(doc.remove_char_toleft(2));
        assertEquals("012345|045|012345|012345", doc.toStringDocument());

        assertEquals("01_01", doc.curr_cursor_string());
        assertTrue(doc.insert_text('a'));
        assertEquals("012345|0a45|012345|012345", doc.toStringDocument());
        
        assertEquals("01_02", doc.curr_cursor_string());
        assertTrue(doc.insert_text("xyz"));
        assertEquals("012345|0axyz45|012345|012345", doc.toStringDocument());        // public boolean insert_text(String str_value){ 
        assertEquals("01_05", doc.curr_cursor_string());
    }

    
    @Test
    public void test_document_write_to_file() {

        // load an existing file
        try {
            doc.load_file("files/test_file_01.txt");
        } catch (IOException e) {
            fail("files/test_file_01.txt does not exist, but should");
        }

        // verify the loaded information
        assertEquals("abcdef||klme|28932|",    doc.toStringDocument());
        assertEquals("files/test_file_01.txt", doc.current_file_name());

        // store to different file
        try {
            doc.store_file("files/output_file_01.txt");
        } catch (IOException e) {
            fail(e);
        }
    
        assertEquals("files/output_file_01.txt", doc.current_file_name());
        
        // go check the file
    }

    
    @Test
    public void test_document_write_back_to_curr_file() {

        // load an existing file
        try {
            doc.load_file("files/test_file_writeback.txt");
        } catch (IOException e) {
            fail("files/test_file_writeback.txt does not exist, but should");
        }

        // verify the loaded information
        assertEquals("files/test_file_writeback.txt", doc.current_file_name());

        doc.remove_line();
        
        // store to different file
        try {
            doc.store_file();
        } catch (IOException e) {
            fail(e);
        }
    
        assertEquals("files/test_file_writeback.txt", doc.current_file_name());
        
        // go check the file
    }
}
