
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class BufferStructureTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class BufferStructureTest
{
    private BufferStructure bStruct;

    /**
     * Default constructor for test class BufferStructureTest
     */
    public BufferStructureTest()
    {
        bStruct = null;
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
        bStruct = new BufferStructure("gap");
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
        bStruct = null;
    }

    @Test
    public void test_add_line_at_start() {

        bStruct.load_line_at_start("abcd");
        assertEquals("abcd", bStruct.toString());

        bStruct.load_line_at_start("cde");
        assertEquals("cde|abcd", bStruct.toString());

        bStruct.load_line_at_start("zyzaksd");
        assertEquals("zyzaksd|cde|abcd", bStruct.toString());
    }

    @Test
    public void test_add_line_at_start_exceeding_array_size() {

        bStruct.load_line_at_start("abcd");
        assertEquals("abcd", bStruct.toString());

        bStruct.load_line_at_start("abcd");
        assertEquals("abcd|abcd", bStruct.toString());

        bStruct.load_line_at_start("abcd");
        assertEquals("abcd|abcd|abcd", bStruct.toString());

        bStruct.load_line_at_start("abcd");
        assertEquals("abcd|abcd|abcd|abcd", bStruct.toString());

        bStruct.load_line_at_start("abcd");
        assertEquals("abcd|abcd|abcd|abcd|abcd", bStruct.toString());

        bStruct.load_line_at_start("abcd");
        assertEquals("abcd|abcd|abcd|abcd|abcd|abcd", bStruct.toString());
    }

    @Test
    public void test_add_line_at_end() {

        bStruct.load_line_at_end("abcd");
        assertEquals("abcd", bStruct.toString());

        bStruct.load_line_at_end("cde");
        assertEquals("abcd|cde", bStruct.toString());

        bStruct.load_line_at_end("zyzaksd");
        assertEquals("abcd|cde|zyzaksd", bStruct.toString());
    }

    @Test
    public void test_add_line_at_end_exceeding_array_size() {

        bStruct.load_line_at_end("abcd");
        assertEquals("abcd", bStruct.toString());

        bStruct.load_line_at_end("abcd");
        assertEquals("abcd|abcd", bStruct.toString());

        bStruct.load_line_at_end("abcd");
        assertEquals("abcd|abcd|abcd", bStruct.toString());

        bStruct.load_line_at_end("abcd");
        assertEquals("abcd|abcd|abcd|abcd", bStruct.toString());

        bStruct.load_line_at_end("abcd");
        assertEquals("abcd|abcd|abcd|abcd|abcd", bStruct.toString());

        bStruct.load_line_at_end("abcd");
        assertEquals("abcd|abcd|abcd|abcd|abcd|abcd", bStruct.toString());
    }

    @Test
    public void test_add_line_at_start_end() {

        bStruct.load_line_at_start("abcd");
        assertEquals("abcd", bStruct.toString());

        bStruct.load_line_at_end("cde");
        assertEquals("abcd|cde", bStruct.toString());

        bStruct.load_line_at_start("zyzaksd");
        assertEquals("zyzaksd|abcd|cde", bStruct.toString());

        bStruct.load_line_at_end("9786987");
        assertEquals("zyzaksd|abcd|cde|9786987", bStruct.toString());
    }

    @Test
    public void test_cursor_move_in_square_text_block_one() {
        bStruct.load_line_at_end("012345");
        bStruct.load_line_at_end("012345");
        bStruct.load_line_at_end("012345");
        bStruct.load_line_at_end("012345");
        bStruct.load_line_at_end("012345");
        assertEquals("012345|012345|012345|012345|012345", bStruct.toString());

        assertEquals("04_00", bStruct.curr_cursor_string()); // walk the left edge
        assertTrue(bStruct.cursor_up());
        assertEquals("03_00", bStruct.curr_cursor_string());
        assertTrue(bStruct.cursor_up());
        assertEquals("02_00", bStruct.curr_cursor_string());
        assertTrue(bStruct.cursor_up());
        assertEquals("01_00", bStruct.curr_cursor_string());
        assertTrue(bStruct.cursor_up());
        assertEquals("00_00", bStruct.curr_cursor_string());

        assertFalse(bStruct.cursor_up());  // can not move further, return false

        assertTrue(bStruct.cursor_right());                  // walk the top edge
        assertEquals("00_01", bStruct.curr_cursor_string());
        assertTrue(bStruct.cursor_right());
        assertEquals("00_02", bStruct.curr_cursor_string());
        assertTrue(bStruct.cursor_right());
        assertEquals("00_03", bStruct.curr_cursor_string());
        assertTrue(bStruct.cursor_right());
        assertEquals("00_04", bStruct.curr_cursor_string());
        assertTrue(bStruct.cursor_right());
        assertEquals("00_05", bStruct.curr_cursor_string());
        assertTrue(bStruct.cursor_right());
        assertEquals("00_06", bStruct.curr_cursor_string());

        assertFalse(bStruct.cursor_right());  // can not move further, return false

        assertTrue(bStruct.cursor_down());                   // walk the right edge
        assertEquals("01_06", bStruct.curr_cursor_string());
        assertTrue(bStruct.cursor_down());
        assertEquals("02_06", bStruct.curr_cursor_string());
        assertTrue(bStruct.cursor_down());
        assertEquals("03_06", bStruct.curr_cursor_string());
        assertTrue(bStruct.cursor_down());
        assertEquals("04_06", bStruct.curr_cursor_string());

        assertFalse(bStruct.cursor_down());  // can not move further, return false

        assertTrue(bStruct.cursor_left());                   // walk the bottom edge
        assertEquals("04_05", bStruct.curr_cursor_string());
        assertTrue(bStruct.cursor_left());
        assertEquals("04_04", bStruct.curr_cursor_string());
        assertTrue(bStruct.cursor_left());
        assertEquals("04_03", bStruct.curr_cursor_string());
        assertTrue(bStruct.cursor_left());
        assertEquals("04_02", bStruct.curr_cursor_string());
        assertTrue(bStruct.cursor_left());
        assertEquals("04_01", bStruct.curr_cursor_string());
        assertTrue(bStruct.cursor_left());
        assertEquals("04_00", bStruct.curr_cursor_string());

        assertFalse(bStruct.cursor_left());  // can not move further, return false

    }

    @Test
    public void test_cursor_move_in_square_text_block_two() {
        bStruct.load_line_at_end("012345");
        bStruct.load_line_at_end("012345");
        bStruct.load_line_at_end("012345");
        bStruct.load_line_at_end("012345");
        bStruct.load_line_at_end("012345");
        assertEquals("012345|012345|012345|012345|012345", bStruct.toString());

        assertEquals("04_00", bStruct.curr_cursor_string()); // move to the next ring in
        assertTrue(bStruct.cursor_right());
        assertTrue(bStruct.cursor_up());
        assertEquals("03_01", bStruct.curr_cursor_string());

        assertTrue(bStruct.cursor_up());                     // walk the ring left edge
        assertEquals("02_01", bStruct.curr_cursor_string());
        assertTrue(bStruct.cursor_up());
        assertEquals("01_01", bStruct.curr_cursor_string());

        assertTrue(bStruct.cursor_right());                  // walk the ring top edge
        assertEquals("01_02", bStruct.curr_cursor_string());
        assertTrue(bStruct.cursor_right());
        assertEquals("01_03", bStruct.curr_cursor_string());
        assertTrue(bStruct.cursor_right());
        assertEquals("01_04", bStruct.curr_cursor_string());

        assertTrue(bStruct.cursor_down());                   // walk the ring right edge
        assertEquals("02_04", bStruct.curr_cursor_string());
        assertTrue(bStruct.cursor_down());
        assertEquals("03_04", bStruct.curr_cursor_string());

        assertTrue(bStruct.cursor_left());                   // walk the ring bottom edge
        assertEquals("03_03", bStruct.curr_cursor_string());
        assertTrue(bStruct.cursor_left());
        assertEquals("03_02", bStruct.curr_cursor_string());
        assertTrue(bStruct.cursor_left());
        assertEquals("03_01", bStruct.curr_cursor_string());
    }

    @Test
    public void test_cursor_move_in_square_text_block_three() {
        bStruct.load_line_at_end("01234");
        bStruct.load_line_at_end("01234");
        bStruct.load_line_at_end("01234");
        bStruct.load_line_at_end("01234");
        bStruct.load_line_at_end("01234");
        assertEquals("01234|01234|01234|01234|01234", bStruct.toString());

        assertEquals("04_00", bStruct.curr_cursor_string()); // walk the left edge
        assertTrue(bStruct.cursor_up(2));
        assertEquals("02_00", bStruct.curr_cursor_string());
        assertTrue(bStruct.cursor_up(2));
        assertEquals("00_00", bStruct.curr_cursor_string());

        assertTrue(bStruct.cursor_right(2));                  // walk the top edge
        assertEquals("00_02", bStruct.curr_cursor_string());
        assertTrue(bStruct.cursor_right(2));
        assertEquals("00_04", bStruct.curr_cursor_string());

        assertTrue(bStruct.cursor_down(2));                  // walk the right edge
        assertEquals("02_04", bStruct.curr_cursor_string());
        assertTrue(bStruct.cursor_down(2));
        assertEquals("04_04", bStruct.curr_cursor_string());

        assertTrue(bStruct.cursor_left(2));                  // walk the bottom edge
        assertEquals("04_02", bStruct.curr_cursor_string());
        assertTrue(bStruct.cursor_left(2));
        assertEquals("04_00", bStruct.curr_cursor_string());

    }

    @Test
    public void test_cursor_move_in_square_text_block_four() {
        bStruct.load_line_at_end("01234");
        bStruct.load_line_at_end("01234");
        bStruct.load_line_at_end("01234");
        bStruct.load_line_at_end("01234");
        bStruct.load_line_at_end("01234");
        assertEquals("01234|01234|01234|01234|01234", bStruct.toString());

        assertTrue(bStruct.cursor_up());                     // move to inner ring
        assertTrue(bStruct.cursor_right());

        assertEquals("03_01", bStruct.curr_cursor_string()); // inner ring corners
        assertTrue(bStruct.cursor_up(2));
        assertEquals("01_01", bStruct.curr_cursor_string());
        assertTrue(bStruct.cursor_right(2));
        assertEquals("01_03", bStruct.curr_cursor_string());
        assertTrue(bStruct.cursor_down(2));
        assertEquals("03_03", bStruct.curr_cursor_string());
        assertTrue(bStruct.cursor_left(2));
        assertEquals("03_01", bStruct.curr_cursor_string());
    }

    @Test void test_insert_line_start() {

        bStruct.load_line_at_start("01234");

        assertEquals("00_00", bStruct.curr_cursor_string());

        bStruct.load_line_at_start("abcde");

        assertEquals("00_00", bStruct.curr_cursor_string());

        bStruct.load_line_at_start("vwxyz");

        assertEquals("vwxyz|abcde|01234", bStruct.toString());

        assertEquals("00_00", bStruct.curr_cursor_string());

    }

    @Test void test_insert_line_end() {

        bStruct.load_line_at_end("01234");

        assertEquals("01234", bStruct.toString());

        assertEquals("00_00", bStruct.curr_cursor_string());

        bStruct.load_line_at_end("abcde");

        assertEquals("01234|abcde", bStruct.toString());

        assertEquals("01_00", bStruct.curr_cursor_string());

        bStruct.load_line_at_end("vwxyz");

        assertEquals("01234|abcde|vwxyz", bStruct.toString());

        assertEquals("02_00", bStruct.curr_cursor_string());

    }

    @Test
    void test_growth() {

        for(int cnt = 0; cnt < 6; cnt++) {                // using for loops to step
            bStruct.load_line_at_end("01234");            // through growth handling
        }

        assertEquals("05_00", bStruct.curr_cursor_string());
        assertEquals("01234|01234|01234|01234|01234|01234", bStruct.toString());

        for(int cnt = 0; cnt < 6; cnt++) {
            bStruct.load_line_at_start("abc");
        }

        assertEquals("00_00", bStruct.curr_cursor_string());
        assertEquals("abc|abc|abc|abc|abc|abc|01234|01234|01234|01234|01234|01234", bStruct.toString());

        for(int cnt = 0; cnt < 11; cnt++) {
            assertTrue(bStruct.cursor_down());
        }

        assertFalse(bStruct.cursor_down());
    }

    @Test
    void test_insert_line_position_emptyInsert() {

        bStruct.load_line_at_end("01234");
        bStruct.load_line_at_end("01234");
        bStruct.load_line_at_end("01234");
        bStruct.load_line_at_end("01234");
        bStruct.load_line_at_end("01234");

        assertEquals("04_00", bStruct.curr_cursor_string());
        assertEquals("01234|01234|01234|01234|01234", bStruct.toString());

        bStruct.load_line_at_position("abcde", 2);

        assertEquals("02_00", bStruct.curr_cursor_string());
        assertEquals("01234|01234|abcde|01234|01234|01234", bStruct.toString());

        bStruct.insert_empty_line_above();

        assertEquals("02_00", bStruct.curr_cursor_string());
        assertEquals("01234|01234||abcde|01234|01234|01234", bStruct.toString());

        bStruct.cursor_down();
        assertEquals("03_00", bStruct.curr_cursor_string());

        bStruct.insert_empty_line_below();

        assertEquals("04_00", bStruct.curr_cursor_string());
        assertEquals("01234|01234||abcde||01234|01234|01234", bStruct.toString());

    }

    @Test
    public void test_cursor_insert_char_square_text_block() {
        bStruct.load_line_at_start("01234");
        bStruct.load_line_at_start("01234");
        bStruct.load_line_at_start("01234");
        bStruct.load_line_at_start("01234");
        bStruct.load_line_at_start("01234");
        assertEquals("01234|01234|01234|01234|01234", bStruct.toString());

        assertTrue(bStruct.cursor_down());                     // move to inner ring
        assertTrue(bStruct.cursor_right());

        assertEquals("01_01", bStruct.curr_cursor_string());   // walk inner ring corners
        assertTrue(bStruct.cursor_down(2));
        assertEquals("03_01", bStruct.curr_cursor_string());
        assertTrue(bStruct.cursor_right(2));
        assertEquals("03_03", bStruct.curr_cursor_string());
        assertTrue(bStruct.cursor_up(2));
        assertEquals("01_03", bStruct.curr_cursor_string());
        assertTrue(bStruct.cursor_left(2));
        assertEquals("01_01", bStruct.curr_cursor_string());
    }

    @Test
    void test_cursor_move_with_nonregular_textblock() {
        bStruct.load_line_at_start("01234");
        bStruct.load_line_at_start("0123456");
        bStruct.load_line_at_start("012");
        bStruct.load_line_at_start("0123");
        bStruct.load_line_at_start("01");
        assertEquals("01|0123|012|0123456|01234", bStruct.toString());

        assertEquals("00_00", bStruct.curr_cursor_string());  // Wall edgest of irregular lines
        assertTrue(bStruct.cursor_down(3));
        assertEquals("03_00", bStruct.curr_cursor_string());

        assertTrue(bStruct.cursor_move_end_line());
        assertEquals("03_07", bStruct.curr_cursor_string());

        assertTrue(bStruct.cursor_down());
        assertEquals("04_05", bStruct.curr_cursor_string());

        assertTrue(bStruct.cursor_up());
        assertEquals("03_05", bStruct.curr_cursor_string());

        assertTrue(bStruct.cursor_up());
        assertEquals("02_03", bStruct.curr_cursor_string());

        assertTrue(bStruct.cursor_up());
        assertEquals("01_03", bStruct.curr_cursor_string());

        assertTrue(bStruct.cursor_up());
        assertEquals("00_02", bStruct.curr_cursor_string());
    }

    @Test
    void test_cursor_move_first_last_irregular_text() {

        bStruct.load_line_at_end("01");
        bStruct.load_line_at_end("01234");
        bStruct.load_line_at_end("0123");
        bStruct.load_line_at_end("0123456");
        bStruct.load_line_at_end("012");
        assertEquals("01|01234|0123|0123456|012", bStruct.toString());

        assertEquals("04_00", bStruct.curr_cursor_string());

        assertTrue(bStruct.cursor_move_first_line());
        assertEquals("00_00", bStruct.curr_cursor_string());

        assertTrue(bStruct.cursor_move_last_line());
        assertEquals("04_00", bStruct.curr_cursor_string());

        assertTrue(bStruct.cursor_move_first_line());
        assertEquals("00_00", bStruct.curr_cursor_string());

        assertTrue(bStruct.cursor_move_end_line());
        assertEquals("00_02", bStruct.curr_cursor_string());

        assertTrue(bStruct.cursor_move_last_line());
        assertEquals("04_02", bStruct.curr_cursor_string());
    }

    @Test
    void test_cursor_move_first_last_iregular_text() {

        bStruct.load_line_at_end("0123456");
        bStruct.load_line_at_end("0123456");
        bStruct.load_line_at_end("0123456");
        bStruct.load_line_at_end("0123456");
        bStruct.load_line_at_end("0123456");
        assertEquals("0123456|0123456|0123456|0123456|0123456", bStruct.toString());

        assertEquals("04_00", bStruct.curr_cursor_string());

        assertTrue(bStruct.cursor_move_first_line());
        assertEquals("00_00", bStruct.curr_cursor_string());

        assertTrue(bStruct.cursor_move_end_line());
        assertEquals("00_07", bStruct.curr_cursor_string());

        assertTrue(bStruct.cursor_move_last_line());
        assertEquals("04_07", bStruct.curr_cursor_string());
    }

    @Test
    void test_character_remove() {

        bStruct.load_line_at_end("01");      // testing with a perfectly 
        bStruct.load_line_at_end("01234");   // filled array
        bStruct.load_line_at_end("0123");
        bStruct.load_line_at_end("0123456");
        bStruct.load_line_at_end("012");
        assertEquals("01|01234|0123|0123456|012", bStruct.toString());

        assertEquals("04_00", bStruct.curr_cursor_string());

        assertTrue(bStruct.cursor_up(2));
        assertEquals("02_00", bStruct.curr_cursor_string());

        assertFalse(bStruct.remove_char_toleft());                  // no characters to the left return false
        assertEquals("01|01234|0123|0123456|012", bStruct.toString());

        assertTrue(bStruct.cursor_right(2));
        assertEquals("02_02", bStruct.curr_cursor_string());

        assertTrue(bStruct.remove_char_toleft());                   // remove char in middle of string
        assertEquals("01|01234|023|0123456|012", bStruct.toString());
        assertEquals("02_01", bStruct.curr_cursor_string());

        assertTrue(bStruct.cursor_right(2));
        assertEquals("02_03", bStruct.curr_cursor_string());

        assertTrue(bStruct.remove_char_toleft());                   // remove char at end of string
        assertEquals("01|01234|02|0123456|012", bStruct.toString());
        assertEquals("02_02", bStruct.curr_cursor_string());

        assertTrue(bStruct.remove_char_toleft(2));                  // remove the rest of the string
        assertEquals("01|01234||0123456|012", bStruct.toString());
        assertEquals("02_00", bStruct.curr_cursor_string());

        assertFalse(bStruct.remove_char_toleft());                  // test on empty string
        assertEquals("01|01234||0123456|012", bStruct.toString());
        assertEquals("02_00", bStruct.curr_cursor_string());

        // public boolean remove_char_toleft();
        // public boolean remove_char_toleft(int char_count);
    }

    @Test
    void test_line_remove() {

        bStruct.load_line_at_end("01");
        bStruct.load_line_at_end("01234");
        bStruct.load_line_at_end("0123");
        bStruct.load_line_at_end("0123456");
        bStruct.load_line_at_end("012");
        assertEquals("01|01234|0123|0123456|012", bStruct.toString());
        assertEquals("04_00", bStruct.curr_cursor_string());

        assertTrue(bStruct.cursor_up(2));                    // move to middle of array
        assertEquals("02_00", bStruct.curr_cursor_string());

        assertTrue(bStruct.remove_line());                   // remove line
        assertEquals("02_00", bStruct.curr_cursor_string());

        assertEquals("01|01234|0123456|012", bStruct.toString());

        assertTrue(bStruct.cursor_move_last_line());         // move to end of array
        assertEquals("03_00", bStruct.curr_cursor_string());

        assertTrue(bStruct.remove_line());                   // remove line
        assertEquals("02_00", bStruct.curr_cursor_string());

        assertEquals("01|01234|0123456", bStruct.toString());

        assertTrue(bStruct.cursor_move_first_line());        // move to front of array
        assertEquals("00_00", bStruct.curr_cursor_string());

        assertTrue(bStruct.remove_line());
        assertEquals("00_00", bStruct.curr_cursor_string());

        assertEquals("01234|0123456", bStruct.toString());

        assertTrue(bStruct.remove_line());                   // clear array
        assertEquals("00_00", bStruct.curr_cursor_string());

        assertEquals("0123456", bStruct.toString());

        assertTrue(bStruct.remove_line());
        assertEquals("", bStruct.toString());

        assertFalse(bStruct.remove_line());                  // remove from empty array

    }

    @Test
    void test_text_insert() {

        bStruct.load_line_at_end("01");
        bStruct.load_line_at_end("01234");
        bStruct.load_line_at_end("0123");
        bStruct.load_line_at_end("0123456");
        bStruct.load_line_at_end("012");
        assertEquals("01|01234|0123|0123456|012", bStruct.toString());
        assertEquals("04_00", bStruct.curr_cursor_string());

        assertTrue(bStruct.insert_text('a'));                           // manipulate last line in array
        assertEquals("01|01234|0123|0123456|a012", bStruct.toString());
        assertTrue(bStruct.cursor_move_start_line());
        assertTrue(bStruct.insert_text("xyz"));
        assertEquals("01|01234|0123|0123456|xyza012", bStruct.toString());

        assertTrue(bStruct.cursor_move_end_line());
        assertTrue(bStruct.insert_text('a'));
        assertEquals("01|01234|0123|0123456|xyza012a", bStruct.toString());
        assertTrue(bStruct.cursor_move_end_line());
        assertTrue(bStruct.insert_text("xyz"));
        assertEquals("01|01234|0123|0123456|xyza012axyz", bStruct.toString());

        assertTrue(bStruct.cursor_left(6));
        assertTrue(bStruct.insert_text('a'));
        assertEquals("01|01234|0123|0123456|xyza0a12axyz", bStruct.toString());
        assertTrue(bStruct.cursor_left());
        assertTrue(bStruct.insert_text("xyz"));
        assertEquals("01|01234|0123|0123456|xyza0xyza12axyz", bStruct.toString());

        assertTrue(bStruct.cursor_move_first_line());

        assertTrue(bStruct.cursor_move_start_line());
        assertTrue(bStruct.insert_text('a'));                           // manipulate first line in array
        assertEquals("a01|01234|0123|0123456|xyza0xyza12axyz", bStruct.toString());
        assertTrue(bStruct.cursor_move_start_line());
        assertTrue(bStruct.insert_text("xyz"));
        assertEquals("xyza01|01234|0123|0123456|xyza0xyza12axyz", bStruct.toString());

        assertTrue(bStruct.cursor_move_end_line());
        assertTrue(bStruct.insert_text('a'));
        assertEquals("xyza01a|01234|0123|0123456|xyza0xyza12axyz", bStruct.toString());
        assertTrue(bStruct.cursor_move_end_line());
        assertTrue(bStruct.insert_text("xyz"));
        assertEquals("xyza01axyz|01234|0123|0123456|xyza0xyza12axyz", bStruct.toString());

        assertTrue(bStruct.cursor_left(5));
        assertTrue(bStruct.insert_text('a'));
        assertEquals("xyza0a1axyz|01234|0123|0123456|xyza0xyza12axyz", bStruct.toString());
        assertTrue(bStruct.cursor_left());
        assertTrue(bStruct.insert_text("xyz"));
        assertEquals("xyza0xyza1axyz|01234|0123|0123456|xyza0xyza12axyz", bStruct.toString());

        assertTrue(bStruct.cursor_down(2));

        assertTrue(bStruct.cursor_move_start_line());
        assertTrue(bStruct.insert_text('a'));                           // manipulate 3rd line in array
        assertEquals("xyza0xyza1axyz|01234|a0123|0123456|xyza0xyza12axyz", bStruct.toString());
        assertTrue(bStruct.cursor_move_start_line());
        assertTrue(bStruct.insert_text("xyz"));
        assertEquals("xyza0xyza1axyz|01234|xyza0123|0123456|xyza0xyza12axyz", bStruct.toString());

        assertTrue(bStruct.cursor_move_end_line());
        assertTrue(bStruct.insert_text('a'));
        assertEquals("xyza0xyza1axyz|01234|xyza0123a|0123456|xyza0xyza12axyz", bStruct.toString());
        assertTrue(bStruct.cursor_move_end_line());
        assertTrue(bStruct.insert_text("xyz"));
        assertEquals("xyza0xyza1axyz|01234|xyza0123axyz|0123456|xyza0xyza12axyz", bStruct.toString());

        assertTrue(bStruct.cursor_left(5));
        assertTrue(bStruct.insert_text('a'));
        assertEquals("xyza0xyza1axyz|01234|xyza012a3axyz|0123456|xyza0xyza12axyz", bStruct.toString());
        assertTrue(bStruct.cursor_left());
        assertTrue(bStruct.insert_text("xyz"));
        assertEquals("xyza0xyza1axyz|01234|xyza012xyza3axyz|0123456|xyza0xyza12axyz", bStruct.toString());
    }

    @Test
    void test_misc_methods_added_during_dev() {

        bStruct.load_line_at_end("01");
        bStruct.load_line_at_end("01234");
        bStruct.load_line_at_end("0123");
        bStruct.load_line_at_end("0123456");
        bStruct.load_line_at_end("012");

        assertEquals("01",      bStruct.toStringLine(0));
        assertEquals("01234",   bStruct.toStringLine(1));
        assertEquals("0123",    bStruct.toStringLine(2));
        assertEquals("0123456", bStruct.toStringLine(3));
        assertEquals("012",     bStruct.toStringLine(4));

        assertEquals(2, bStruct.position_line_length(0));
        assertEquals(5, bStruct.position_line_length(1));
        assertEquals(4, bStruct.position_line_length(2));
        assertEquals(7, bStruct.position_line_length(3));
        assertEquals(3, bStruct.position_line_length(4));

        assertEquals("04_00", bStruct.curr_cursor_string());
        assertTrue(bStruct.cursor_up(2));
        assertEquals("02_00", bStruct.curr_cursor_string());
        assertEquals(4,       bStruct.curr_line_length());

    }
}
