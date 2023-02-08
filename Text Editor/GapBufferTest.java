import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class GapBufferTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class GapBufferTest
{
    private GapBuffer gb;

    /**
     * Default constructor for test class GapBufferTest
     */
    public GapBufferTest()
    {
        gb = null;
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
        gb = new GapBuffer();
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
    }

    @Test
    public void test_load_and_toString() {
        gb.load_string("This is a string");
        assertEquals("This is a string", gb.toString());

        gb.load_string("T");
        assertEquals("T", gb.toString());

        gb.load_string("");
        assertEquals("", gb.toString());
    }

    @Test
    public void test_cursor_movement() {
        gb.load_string("1234567890");
        assertEquals("1234567890", gb.toString());

        assertTrue(gb.cursor_position() == 0);
        assertFalse(gb.cursor_left());          // test to see if cursor moves off buffer
        assertTrue(gb.cursor_position() == 0);

        assertTrue(gb.cursor_right());
        assertTrue(gb.cursor_position() == 1);
        assertEquals("1234567890", gb.toString());

        assertTrue(gb.cursor_right(3));
        assertTrue(gb.cursor_position() == 4);
        assertEquals("1234567890", gb.toString());

        assertTrue(gb.cursor_left());
        assertTrue(gb.cursor_position() == 3);
        assertEquals("1234567890", gb.toString());

        assertTrue(gb.cursor_left(3));
        assertTrue(gb.cursor_position() == 0);
        assertEquals("1234567890", gb.toString());

        assertTrue(gb.cursor_right(10));
        assertTrue(gb.cursor_position() == 10);
        assertEquals("1234567890", gb.toString());

        assertFalse(gb.cursor_right());
        assertTrue(gb.cursor_position() == 10);
        assertEquals("1234567890", gb.toString());

    }

    @Test
    public void test_char_insertion() {
        gb.load_string("1234567890");
        assertEquals("1234567890", gb.toString(), "The string are not displayed correctly.");

        assertTrue(gb.insert_text('a'));
        assertTrue(gb.cursor_position() == 1);
        assertEquals("a1234567890", gb.toString());

        assertTrue(gb.cursor_right());
        assertTrue(gb.insert_text('b'));
        assertTrue(gb.cursor_position() == 3);
        assertEquals("a1b234567890", gb.toString());

        assertTrue(gb.cursor_right(9));
        assertTrue(gb.insert_text('c'));
        assertTrue(gb.cursor_position() == 13);
        assertEquals("a1b234567890c", gb.toString());

        assertTrue(gb.cursor_left(2));
        assertTrue(gb.insert_text('D'));
        assertTrue(gb.cursor_position() == 12);
        assertEquals("a1b23456789D0c", gb.toString());
    }

    @Test
    public void test_text_insertion() {
        gb.load_string("1234567");
        assertEquals("1234567", gb.toString());

        assertTrue(gb.insert_text("abc"));
        assertTrue(gb.cursor_position() == 3);
        assertEquals("abc1234567", gb.toString());

        assertTrue(gb.cursor_right());
        assertTrue(gb.insert_text("def"));
        assertTrue(gb.cursor_position() == 7);
        assertEquals("abc1def234567", gb.toString());

        assertTrue(gb.cursor_right(6));
        assertTrue(gb.insert_text("ghi"));
        assertTrue(gb.cursor_position() == 16);
        assertEquals("abc1def234567ghi", gb.toString());

        assertTrue(gb.cursor_left(2));
        assertTrue(gb.insert_text("mno"));

        assertTrue(gb.cursor_position() == 17);
        assertEquals("abc1def234567gmnohi", gb.toString());

    }

    @Test
    public void test_positioning_methods() {
        gb.load_string("1234567890");              // load string, cursor defaults to 0
        assertTrue(gb.cursor_position() ==  0);
        assertTrue(gb.before_length()   ==  0);
        assertTrue(gb.after_length()    == 10);
        assertTrue(gb.length()          == 10);

        assertTrue(gb.cursor_right(5));            // move to middle of string
        assertTrue(gb.cursor_position() ==  5);
        assertTrue(gb.before_length()   ==  5);
        assertTrue(gb.after_length()    ==  5);
        assertTrue(gb.length()          == 10);

        assertTrue(gb.cursor_left());             // move one to left
        assertTrue(gb.cursor_position() ==  4);
        assertTrue(gb.before_length()   ==  4);
        assertTrue(gb.after_length()    ==  6);
        assertTrue(gb.length()          == 10);

        assertTrue(gb.cursor_right(2));            // move two to right
        assertTrue(gb.cursor_position() ==  6);
        assertTrue(gb.before_length()   ==  6);
        assertTrue(gb.after_length()    ==  4);
        assertTrue(gb.length()          == 10);

        assertTrue(gb.cursor_right(4));            // move to end of string
        assertTrue(gb.cursor_position() == 10);
        assertTrue(gb.before_length()   == 10);
        assertTrue(gb.after_length()    ==  0);
        assertTrue(gb.length()          == 10);

        assertTrue(gb.cursor_left(10));            // move to start of string
        assertTrue(gb.cursor_position() ==  0);
        assertTrue(gb.before_length()   ==  0);
        assertTrue(gb.after_length()    == 10);
        assertTrue(gb.length()          == 10);
    }

    @Test
    public void test_end_movements() {
        gb.load_string("1234567890");              // load string, cursor defaults to 0

        assertTrue(gb.cursor_right(5));            // move to middle of string
        assertTrue(gb.cursor_position() ==  5);
        assertTrue(gb.before_length()   ==  5);
        assertTrue(gb.after_length()    ==  5);
        assertTrue(gb.length()          == 10);

        assertTrue(gb.cursor_move_start_line());   // move to front of string
        assertTrue(gb.cursor_position() ==  0);
        assertTrue(gb.before_length()   ==  0);
        assertTrue(gb.after_length()    == 10);
        assertTrue(gb.length()          == 10);

        assertFalse(gb.cursor_left());             // try to go before string start

        assertTrue(gb.cursor_right(5));            // move to middle of string
        assertTrue(gb.cursor_position() ==  5);
        assertTrue(gb.before_length()   ==  5);
        assertTrue(gb.after_length()    ==  5);
        assertTrue(gb.length()          == 10);

        assertTrue(gb.cursor_move_end_line());     // move to end of string
        assertTrue(gb.cursor_position() == 10);
        assertTrue(gb.before_length()   == 10);
        assertTrue(gb.after_length()    ==  0);
        assertTrue(gb.length()          == 10);

        assertFalse(gb.cursor_right());            // try to go beyond string end
    }

    @Test
    public void test_character_removal() {
        gb.load_string("1234567890");              // load string, cursor defaults to 0
        assertTrue(gb.cursor_position() ==  0);

        assertTrue(gb.cursor_right(5));
        assertTrue(gb.cursor_position() ==  5);

        assertTrue(gb.remove_char_toleft());
        assertTrue(gb.cursor_position() ==  4);
        assertEquals("123467890", gb.toString());

        assertTrue(gb.remove_char_toleft(2));
        assertTrue(gb.cursor_position() ==  2);
        assertEquals("1267890", gb.toString());

        assertTrue(gb.cursor_move_end_line());     // move to end of string
        assertTrue(gb.cursor_position() ==  7);

        assertTrue(gb.remove_char_toleft());
        assertTrue(gb.cursor_position() ==  6);
        assertEquals("126789", gb.toString());

        assertTrue(gb.remove_char_toleft(3));
        assertTrue(gb.cursor_position() ==  3);
        assertEquals("126", gb.toString());

        assertTrue(gb.cursor_move_start_line());   // move to front of string
        assertTrue(gb.cursor_position() ==  0);
        assertFalse(gb.remove_char_toleft());
        assertFalse(gb.remove_char_toleft(10));
        assertEquals("126", gb.toString());
        assertTrue(gb.cursor_position() ==  0);
    }

    @Test
    public void test_buffer_growth() {
        gb.load_string("1234567890");              // load string, cursor gap buffer is default to 20
        assertEquals("1234567890", gb.toString());
        assertTrue(gb.cursor_position() ==  0);

        assertTrue(gb.insert_text("1234567890"));
        assertTrue(gb.cursor_position() ==  10);
        assertEquals("12345678901234567890", gb.toString());

        assertTrue(gb.insert_text("*"));
        assertTrue(gb.cursor_position() ==  11);
        assertEquals("1234567890*1234567890", gb.toString());

        gb.load_string("1234567890");              // load string, cursor gap buffer is default to 20
        assertEquals("1234567890", gb.toString());
        assertTrue(gb.cursor_position() ==  0);

        assertTrue(gb.cursor_right(3));
        assertTrue(gb.cursor_position() ==  3);
        assertTrue(gb.insert_text("1234567890"));
        assertTrue(gb.cursor_position() ==  13);
        assertEquals("12312345678904567890", gb.toString());

        assertTrue(gb.insert_text("*"));
        assertTrue(gb.cursor_position() ==  14);
        assertEquals("1231234567890*4567890", gb.toString());

        gb.load_string("");              // load string, cursor gap buffer is default to 20
        assertEquals("", gb.toString());
        assertTrue(gb.cursor_position() ==  0);

        assertTrue(gb.insert_text("aaaaa"));
        assertTrue(gb.cursor_position() ==  5);
        assertEquals("aaaaa", gb.toString());

        assertTrue(gb.insert_text("bbbbb"));
        assertTrue(gb.cursor_position() ==  10);
        assertEquals("aaaaabbbbb", gb.toString());

        assertTrue(gb.insert_text("ccccc"));
        assertTrue(gb.cursor_position() ==  15);
        assertEquals("aaaaabbbbbccccc", gb.toString());

        assertTrue(gb.insert_text("ddddd"));
        assertTrue(gb.cursor_position() ==  20);
        assertEquals("aaaaabbbbbcccccddddd", gb.toString());

        assertTrue(gb.insert_text("eeeee"));
        assertTrue(gb.cursor_position() ==  25);
        assertEquals("aaaaabbbbbcccccdddddeeeee", gb.toString());
    }

    @Test
    public void test_buffer_growth_with_alt_size() {
        gb = new GapBuffer(5);

        gb.load_string("123");              // load string, cursor gap buffer is default to 20
        assertEquals("123", gb.toString());
        assertTrue(gb.cursor_position() ==  0);

        assertTrue(gb.insert_text("123"));
        assertTrue(gb.cursor_position() ==  3);
        assertEquals("123123", gb.toString());

        assertTrue(gb.insert_text("*"));
        assertTrue(gb.cursor_position() ==  4);
        assertEquals("123*123", gb.toString());

        gb.load_string("123");              // load string, cursor gap buffer is default to 20
        assertEquals("123", gb.toString());
        assertTrue(gb.cursor_position() ==  0);

        assertTrue(gb.cursor_right(1));
        assertTrue(gb.cursor_position() ==  1);
        assertTrue(gb.insert_text("12"));
        assertTrue(gb.cursor_position() ==  3);
        assertEquals("11223", gb.toString());

        assertTrue(gb.insert_text("*"));
        assertTrue(gb.cursor_position() ==  4);
        assertEquals("112*23", gb.toString());

        gb.load_string("");              // load string, cursor gap buffer is default to 20
        assertEquals("", gb.toString());
        assertTrue(gb.cursor_position() ==  0);

        assertTrue(gb.insert_text("1"));
        assertTrue(gb.cursor_position() ==  1);
        assertEquals("1", gb.toString());

        assertTrue(gb.insert_text("2"));
        assertTrue(gb.cursor_position() ==  2);
        assertEquals("12", gb.toString());

        assertTrue(gb.insert_text("3"));
        assertTrue(gb.cursor_position() ==  3);
        assertEquals("123", gb.toString());

        assertTrue(gb.insert_text("4"));
        assertTrue(gb.cursor_position() ==  4);
        assertEquals("1234", gb.toString());

        assertTrue(gb.insert_text("5"));
        assertTrue(gb.cursor_position() ==  5);
        assertEquals("12345", gb.toString());

        assertTrue(gb.insert_text("6"));
        assertTrue(gb.cursor_position() ==  6);
        assertEquals("123456", gb.toString());

    }
}
