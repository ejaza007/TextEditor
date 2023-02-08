
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

/**
 * The test class LinkedListBufferTest.
 *
 * @author  Armaghan Ejaz
 * @version 13-03-2022
 */
public class LinkedListBufferTest
{
    /**
     * Default constructor for test class LinkedListBufferTest
     */
    public LinkedListBufferTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
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

    /**
    * test created to test load_string and toString methods
    *
    * adds nodes using the load_string method
    * uses toString to test if loaded as expected
    * 
    */

    @Test
    @DisplayName("Test method1.")
    public void test_meth_load_string_toString (){
        LinkedListBuffer testBuffer=new LinkedListBuffer();

        //test for cursor at the start of string
        testBuffer.load_string("test");

        assertEquals("test",testBuffer.toString());
        
        //case for cursor in the middle
        testBuffer.load_string("this is a test");
        assertEquals("this is a test",testBuffer.toString());
        testBuffer.cursor_right();
        testBuffer.cursor_right();
        testBuffer.cursor_left();
        
        assertEquals("this is a test",testBuffer.toString());
        
        //test for cursor at end of string
        testBuffer.cursor_move_end_line();
        assertEquals("this is a test",testBuffer.toString());
    }
    
    /**
    * test created to test all the cursor movement methods
    *
    * loads strings and moves cursor along the string 
    * tests if cursor movement is as expected using boolean
    * tests cursor location using cursor position
    * 
    */
   
    @Test
    @DisplayName("Test method2.")
    public void test_meth_cursor_methods (){

        LinkedListBuffer testBuffer=new LinkedListBuffer();

        testBuffer.load_string("test");
        
        //cases for left
        assertEquals(false,testBuffer.cursor_left());
        assertEquals(0,testBuffer.cursor_position());
        
        //cases for right
        testBuffer.cursor_right();
        assertEquals(true,testBuffer.cursor_left());
        testBuffer.cursor_right();
        assertEquals(1,testBuffer.cursor_position());
        
        //cases for right using parameter
        assertEquals(true,testBuffer.cursor_right(2));
        assertEquals(false,testBuffer.cursor_right(1));

        //case for moving to the start of the line
        assertEquals(true,testBuffer.cursor_move_start_line());
        assertEquals(0,testBuffer.cursor_position());

        //cases for moving to the end of the line
        assertEquals(true,testBuffer.cursor_move_end_line());
        assertEquals(4,testBuffer.cursor_position());
        assertEquals(false,testBuffer.cursor_move_end_line());

        //cases for cursor left using parameter
        assertEquals(true,testBuffer.cursor_left(2));
        assertEquals(2,testBuffer.cursor_position());

        

    }

    /**
    * test created to test the remove_char methods
    *
    * loads strings and uses remove_char to remove the characters
    * tests using toString to see what string has been removed
    * tests using cursor position to show movement of cursor
    * 
    */
   
    @Test
    @DisplayName("Test method3.")
    public void test_meth_remove_char (){

        LinkedListBuffer testBuffer=new LinkedListBuffer();
        
        
        testBuffer.load_string("test");
        
        //case for removing when cursor is at end
        testBuffer.cursor_move_end_line();
        assertEquals(4,testBuffer.cursor_position());
        assertEquals("test",testBuffer.toString());
        

        testBuffer.remove_char_toleft();
        assertEquals(3,testBuffer.cursor_position());
        assertEquals("tes",testBuffer.toString());

        //case for when cursor is not at the ends
        testBuffer.load_string("test");
        testBuffer.cursor_right();
        testBuffer.remove_char_toleft();
        assertEquals("est",testBuffer.toString());
        
        //case for removing using parameter
        testBuffer.load_string("test");
        testBuffer.cursor_move_end_line();
        testBuffer.remove_char_toleft(3);
        assertEquals("t",testBuffer.toString());
        
        //test for false case
        testBuffer.load_string("test");
        assertEquals(false,testBuffer.remove_char_toleft());

    }
    
    /**
    * test created to test the insert_text method
    *
    * loads strings and uses insert_text to add nodes
    * tests using toString to see what string has been added
    * 
    */
   
    @Test
    @DisplayName("Test method4.")
    public void test_meth_insert_text (){
        
        LinkedListBuffer testBuffer =new LinkedListBuffer();
        testBuffer.load_string("test");
        
        //case for cursor at the start
        testBuffer.insert_text('a');
        assertEquals("atest",testBuffer.toString());
        
    
        testBuffer.cursor_move_start_line();
        testBuffer.insert_text('b');
        assertEquals("batest",testBuffer.toString());
        
        //case for cursor at the end
        testBuffer.cursor_move_end_line();
        testBuffer.insert_text('c');
        assertEquals("batestc",testBuffer.toString());
        
        //case for cursor at neither ends
        testBuffer.cursor_left(2);
        testBuffer.insert_text('d');
        assertEquals("batedstc",testBuffer.toString());
        
        //case for inserting string
        testBuffer.load_string("this is a test");
        testBuffer.insert_text("insert");
        
        assertEquals("insertthis is a test",testBuffer.toString());
        
        testBuffer.cursor_move_end_line();
        testBuffer.insert_text("abc");
        assertEquals("insertthis is a testabc",testBuffer.toString());
        
        
        
        
        
        
    }
    
}
