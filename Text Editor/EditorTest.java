
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

/**
 * The test class EditorTest.
 *
 * @author Armaghan Ejaz
 * @version 13-03-2022
 */
public class EditorTest
{
    /**
     * Default constructor for test class EditorTest
     */
    public EditorTest()
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
    * Tests the method for when user inputs 'r'
    * calls string of location of cursor to show that the cursor has moved
    *
    * 
    */

    @Test
    @DisplayName("Test method1.")
    public void test_method_r(){

        //creates objects for testing
        Editor test_editor=new Editor();
        Document doc=new Document("gap");

        //loads file 
        try
        {
            doc.load_file("file.txt");
        }
        catch (java.io.IOException ioe)
        {
            ioe.printStackTrace();
        }

        //calls method to be tested
        test_editor.test_method(doc,"r");

        //expected output
        String expOut="04_01";

        //tests to check if expected output is equal to test output 
        assertEquals(expOut,doc.curr_cursor_string());

        //new document to test for linked list buffer
        Document doc_2=new Document("linked");

        //loads file 
        try
        {
            doc_2.load_file("file.txt");
        }
        catch (java.io.IOException ioe)
        {
            ioe.printStackTrace();
        }

        //calls method to be tested
        test_editor.test_method(doc_2,"r");

        //expected output
        String expOut_2="04_01";
        //tests to check if expected output is equal to test output 
        assertEquals(expOut_2,doc_2.curr_cursor_string());
        
        //test for file with single line
        //loads file 
        try
        {
            doc.load_file("test.txt");
        }
        catch (java.io.IOException ioe)
        {
            ioe.printStackTrace();
        }
        
        //calls method to be tested
        test_editor.test_method(doc,"r");

        //expected output
        expOut="00_01";

        //tests to check if expected output is equal to test output 
        assertEquals(expOut,doc.curr_cursor_string());
        
       
        //test for file with single line
        //loads file 
        try
        {
            doc_2.load_file("test.txt");
        }
        catch (java.io.IOException ioe)
        {
            ioe.printStackTrace();
        }

        //calls method to be tested
        test_editor.test_method(doc_2,"r");

        //expected output
        expOut_2="00_01";
        //tests to check if expected output is equal to test output 
        assertEquals(expOut_2,doc_2.curr_cursor_string());
        

    }
    
    /**
    * Tests the method for when user inputs 'l'
    * calls string of location of cursor to show that the cursor has moved
    *
    * 
    */
    @Test
    @DisplayName("Test method2.")
    public void test_method_l(){
        //creates objects for testing
        Editor test_editor=new Editor();
        Document doc=new Document("gap");

        //loads file 
        try
        {
            doc.load_file("file.txt");
        }
        catch (java.io.IOException ioe)
        {
            ioe.printStackTrace();

        }

        //calls method to be tested
        //calls right cursor method to show cursor can move left
        test_editor.test_method(doc,"r");
        test_editor.test_method(doc,"r");
        test_editor.test_method(doc,"l");

        //expected and actual ouput
        doc.curr_cursor_string();
        String expOut="04_01";

        //tests if equal
        assertEquals(expOut,doc.curr_cursor_string());
        
        //test for file with single line
        //loads file 
        try
        {
            doc.load_file("test.txt");
        }
        catch (java.io.IOException ioe)
        {
            ioe.printStackTrace();

        }

        //calls method to be tested
        //calls right cursor method to show cursor can move left
        test_editor.test_method(doc,"r");
        test_editor.test_method(doc,"r");
        test_editor.test_method(doc,"l");

        //expected and actual ouput
        doc.curr_cursor_string();
        expOut="00_01";

        //tests if equal
        assertEquals(expOut,doc.curr_cursor_string());
        
        Document doc_2=new Document("linked");

        //loads file 
        try
        {
            doc_2.load_file("file.txt");
        }
        catch (java.io.IOException ioe)
        {
            ioe.printStackTrace();

        }

        //calls method to be tested
        //calls right cursor method to show cursor can move left
        test_editor.test_method(doc_2,"r");
        test_editor.test_method(doc_2,"r");
        test_editor.test_method(doc_2,"l");

        //expected and actual ouput
        doc_2.curr_cursor_string();
        String expOut_2="04_01";

        //tests if equal
        assertEquals(expOut_2,doc_2.curr_cursor_string());
        
        //test for file with single line
        //loads file 
        try
        {
            doc_2.load_file("test.txt");
        }
        catch (java.io.IOException ioe)
        {
            ioe.printStackTrace();

        }

        //calls method to be tested
        //calls right cursor method to show cursor can move left
        test_editor.test_method(doc_2,"r");
        test_editor.test_method(doc_2,"r");
        test_editor.test_method(doc_2,"l");

        //expected and actual ouput
        doc_2.curr_cursor_string();
        expOut_2="00_01";

        //tests if equal
        assertEquals(expOut_2,doc_2.curr_cursor_string());


        
    }

    /**
    * Tests the method for when user inputs 'u'
    * calls string of location of cursor to show that the cursor has moved
    *
    * 
    */

    @Test
    @DisplayName("Test method3.")
    public void test_method_u(){
        //creates objects for testing
        Editor test_editor=new Editor();
        Document doc=new Document("gap");

        //loads file
        try
        {
            doc.load_file("file.txt");
        }
        catch (java.io.IOException ioe)
        {
            ioe.printStackTrace();

        }

        //calls method to be tested
        test_editor.test_method(doc,"u");
        test_editor.test_method(doc,"u");

        //actual output and expected output
        String og_curs=doc.curr_cursor_string();
        String expOut="02_00";

        //tests if actual and expected outputs are equal
        assertEquals(expOut,doc.curr_cursor_string());
        
        
        //test for line with single line
        //loads file
        try
        {
            doc.load_file("test.txt");
        }
        catch (java.io.IOException ioe)
        {
            ioe.printStackTrace();

        }

        //calls method to be tested
        test_editor.test_method(doc,"u");
        test_editor.test_method(doc,"u");

        //actual output and expected output
        og_curs=doc.curr_cursor_string();
        expOut="00_00";

        //tests if actual and expected outputs are equal
        assertEquals(expOut,doc.curr_cursor_string());
        
        //repeat test for linked list buffer
        Document doc_2=new Document("linked");

        //loads file
        try
        {
            doc_2.load_file("file.txt");
        }
        catch (java.io.IOException ioe)
        {
            ioe.printStackTrace();

        }

        //calls method to be tested
        test_editor.test_method(doc_2,"u");
        test_editor.test_method(doc_2,"u");

        //actual output and expected output
        String og_curs_2=doc_2.curr_cursor_string();
        String expOut_2="02_00";

        //tests if actual and expected outputs are equal
        assertEquals(expOut_2,doc_2.curr_cursor_string());
        
        
        //test for line with single line
        //loads file
        try
        {
            doc_2.load_file("test.txt");
        }
        catch (java.io.IOException ioe)
        {
            ioe.printStackTrace();

        }

        //calls method to be tested
        test_editor.test_method(doc_2,"u");
        test_editor.test_method(doc_2,"u");

        //actual output and expected output
        og_curs_2=doc_2.curr_cursor_string();
        expOut_2="00_00";

        //tests if actual and expected outputs are equal
        assertEquals(expOut_2,doc_2.curr_cursor_string());

    }
    
    /**
    * Tests the method for when user inputs 'd'
    * calls string of location of cursor to show that the cursor has moved
    *
    * 
    */

    @Test
    @DisplayName("Test method4.")
    public void test_method_d(){
        //creates objects for testing
        Editor test_editor=new Editor();
        Document doc=new Document("gap");

        //loads file
        try
        {
            doc.load_file("file.txt");
        }
        catch (java.io.IOException ioe)
        {
            ioe.printStackTrace();

        }

        //calls method to be tested
        test_editor.test_method(doc,"d");
        test_editor.test_method(doc,"d");

        //actual output and expceted output
        String og_curs=doc.curr_cursor_string();
        String expOut="04_00";

        //tests if actual and expected outputs are equal
        assertEquals(expOut,doc.curr_cursor_string());
        
        //test for single line
        //loads file
        try
        {
            doc.load_file("test.txt");
        }
        catch (java.io.IOException ioe)
        {
            ioe.printStackTrace();

        }

        //calls method to be tested
        test_editor.test_method(doc,"d");
        test_editor.test_method(doc,"d");

        //actual output and expceted output
        og_curs=doc.curr_cursor_string();
        expOut="00_00";

        //tests if actual and expected outputs are equal
        assertEquals(expOut,doc.curr_cursor_string());
        
        //repeat tests for linked list buffer
        Document doc_2=new Document("linked");

        //loads file
        try
        {
            doc_2.load_file("file.txt");
        }
        catch (java.io.IOException ioe)
        {
            ioe.printStackTrace();

        }

        //calls method to be tested
        test_editor.test_method(doc_2,"d");
        test_editor.test_method(doc_2,"d");

        //actual output and expceted output
        String og_curs_2=doc_2.curr_cursor_string();
        String expOut_2="04_00";

        //tests if actual and expected outputs are equal
        assertEquals(expOut_2,doc_2.curr_cursor_string());
        
        
        //test for single line
        //loads file
        try
        {
            doc_2.load_file("test.txt");
        }
        catch (java.io.IOException ioe)
        {
            ioe.printStackTrace();

        }

        //calls method to be tested
        test_editor.test_method(doc_2,"d");
        test_editor.test_method(doc_2,"d");

        //actual output and expceted output
        og_curs_2=doc_2.curr_cursor_string();
        expOut_2="00_00";

        //tests if actual and expected outputs are equal
        assertEquals(expOut_2,doc_2.curr_cursor_string());

        
    }

    /**
    * Tests the method for when user inputs 'ab'
    * outputs string of the document to show that lines have been added below
    * outputs string of cursor to show that it has changed
    *
    * 
    */
    @Test
    @DisplayName("Test method5.")
    public void test_method_ab(){
        //creates objects for testing
        Editor test_editor=new Editor();
        Document doc=new Document("gap");

        //loads file
        try
        {
            doc.load_file("file.txt");
        }
        catch (java.io.IOException ioe)
        {
            ioe.printStackTrace();

        }

        //tests original output to show they are equal
        String og_string=doc.toStringDocument();
        String test_string="this|is|a|test|file";
        assertEquals(test_string,og_string);

        //shows cursor of original output
        assertEquals("04_00",doc.curr_cursor_string());

        //calls method to be tested
        test_editor.test_method(doc,"ab");

        //actual output and expected output
        String new_string=doc.toStringDocument();
        test_string="this|is|a|test|file|";

        //tests if actual and expected output are equal
        assertEquals(test_string,new_string);

        //tests change in cursor position
        assertEquals("05_00",doc.curr_cursor_string());
        
        //test for file with single line
        //loads file
        try
        {
            doc.load_file("test.txt");
        }
        catch (java.io.IOException ioe)
        {
            ioe.printStackTrace();

        }

        //tests original output to show they are equal
        og_string=doc.toStringDocument();
        test_string="test";
        assertEquals(test_string,og_string);

        //shows cursor of original output
        assertEquals("00_00",doc.curr_cursor_string());

        //calls method to be tested
        test_editor.test_method(doc,"ab");

        //actual output and expected output
        new_string=doc.toStringDocument();
        test_string="test|";

        //tests if actual and expected output are equal
        assertEquals(test_string,new_string);

        //tests change in cursor position
        assertEquals("01_00",doc.curr_cursor_string());
        
        
        

    }

    /**
    * Tests the method for when user inputs 'aa'
    * outputs string of the document to show that lines have been added above
    *
    * 
    */
    @Test
    @DisplayName("Test method6.")
    public void test_method_aa(){
        //creates objects for testing
        Editor test_editor=new Editor();
        Document doc=new Document("gap");

        //loads file
        try
        {
            doc.load_file("file.txt");
        }
        catch (java.io.IOException ioe)
        {
            ioe.printStackTrace();

        }

        //tests original output to show they are equal
        String og_string=doc.toStringDocument();
        String test_string="this|is|a|test|file";
        assertEquals(test_string,og_string);

        //calls method to be tested
        test_editor.test_method(doc,"aa");

        //actual output and expected output
        String new_string=doc.toStringDocument();
        test_string="this|is|a|test||file";

        //test if actual and expected output are equal
        assertEquals(test_string,new_string);

        //calls method and tests new expected output  
        test_editor.test_method(doc,"aa");
        new_string=doc.toStringDocument();
        assertEquals("this|is|a|test|||file",new_string);
        
        //test for file with single line
        //loads file
        try
        {
            doc.load_file("test.txt");
        }
        catch (java.io.IOException ioe)
        {
            ioe.printStackTrace();

        }

        //tests original output to show they are equal
        og_string=doc.toStringDocument();
        test_string="test";
        assertEquals(test_string,og_string);

        //calls method to be tested
        test_editor.test_method(doc,"aa");

        //actual output and expected output
        new_string=doc.toStringDocument();
        test_string="|test";

        //test if actual and expected output are equal
        assertEquals(test_string,new_string);

        //calls method and tests new expected output  
        test_editor.test_method(doc,"aa");
        new_string=doc.toStringDocument();
        assertEquals("||test",new_string);
        
        
       
        
        
    }

    /**
    * Tests the method for when user inputs 'dl'
    * outputs string of the document to show that lines have been deleted
    *
    * 
    */
    @Test
    @DisplayName("Test method7.")
    public void test_method_dl(){
        //creates objects for testing
        Editor test_editor=new Editor();
        Document doc=new Document("gap");

        //loads file
        try
        {
            doc.load_file("file.txt");
        }
        catch (java.io.IOException ioe)
        {
            ioe.printStackTrace();

        }
        
        //tests original output to show they are equal
        String og_string=doc.toStringDocument();
        String test_string="this|is|a|test|file";
        assertEquals(test_string,og_string);
        
        //calls method to be tested
        test_editor.test_method(doc,"dl");
        
        //actual output and expected output
        String new_string=doc.toStringDocument();
        test_string="this|is|a|test";

        //test if actual and expected output are equal
        assertEquals(test_string,new_string);

        //calls method to be tested
        test_editor.test_method(doc,"dl");
        
        //calls method and tests new expected output  
        new_string=doc.toStringDocument();
        test_string="this|is|a";
        assertEquals(test_string,new_string);
        
        //test for file with single line
        //loads file
        try
        {
            doc.load_file("test.txt");
        }
        catch (java.io.IOException ioe)
        {
            ioe.printStackTrace();

        }
        
        //tests original output to show they are equal
        og_string=doc.toStringDocument();
        test_string="test";
        assertEquals(test_string,og_string);
        
        //calls method to be tested
        test_editor.test_method(doc,"dl");
        
        //actual output and expected output
        new_string=doc.toStringDocument();
        test_string="";

        //test if actual and expected output are equal
        assertEquals(test_string,new_string);
        
        
         Document doc_2=new Document("linked");

        //loads file
        try
        {
            doc_2.load_file("file.txt");
        }
        catch (java.io.IOException ioe)
        {
            ioe.printStackTrace();

        }
        
        //tests original output to show they are equal
        String og_string_2=doc_2.toStringDocument();
        String test_string_2="this|is|a|test|file";
        assertEquals(test_string_2,og_string_2);
        
        //calls method to be tested
        test_editor.test_method(doc_2,"dl");
        
        //actual output and expected output
        String new_string_2=doc_2.toStringDocument();
        test_string_2="this|is|a|test";

        //test if actual and expected output are equal
        assertEquals(test_string_2,new_string_2);

        //calls method to be tested
        test_editor.test_method(doc_2,"dl");
        
        //calls method and tests new expected output  
        new_string_2=doc_2.toStringDocument();
        test_string_2="this|is|a";
        assertEquals(test_string_2,new_string_2);
        
         //test for file with single line
        //loads file
        try
        {
            doc_2.load_file("test.txt");
        }
        catch (java.io.IOException ioe)
        {
            ioe.printStackTrace();

        }
        
        //tests original output to show they are equal
        og_string_2=doc_2.toStringDocument();
        test_string_2="test";
        assertEquals(test_string_2,og_string_2);
        
        //calls method to be tested
        test_editor.test_method(doc_2,"dl");
        
        //actual output and expected output
        new_string_2=doc_2.toStringDocument();
        test_string_2="";

        //test if actual and expected output are equal
        assertEquals(test_string_2,new_string_2);
        

        

    }
    
    /**
    * Tests the method for when user inputs 'dl'
    * outputs string of the document to show that lines have been deleted
    *
    * 
    */
    @Test
    @DisplayName("Test method8.")
    public void test_method_e(){
        //creates objects for testing
        Editor test_editor=new Editor();
        Document doc=new Document("gap");

        //loads file
        try
        {
            doc.load_file("file.txt");
        }
        catch (java.io.IOException ioe)
        {
            ioe.printStackTrace();

        }

        //tests original output to show they are equal
        String og_string=doc.toStringDocument();
        String test_string="this|is|a|test|file";
        assertEquals(test_string,og_string);

        //calls method to be tested
        test_editor.test_method_e(doc,"insert");

        //actual output and expected output
        String new_string=doc.toStringDocument();
        test_string="this|is|a|test|insertfile";

        //test if actual and expected output are equal
        assertEquals(test_string,new_string);

        //calls method and tests new expected output  
        test_editor.test_method_e(doc,"add");
        new_string=doc.toStringDocument();
        test_string="this|is|a|test|insertaddfile";
        assertEquals(test_string,new_string);
        
        
        //loads file
        try
        {
            doc.load_file("test.txt");
        }
        catch (java.io.IOException ioe)
        {
            ioe.printStackTrace();

        }

        //tests original output to show they are equal
        og_string=doc.toStringDocument();
        test_string="test";
        assertEquals(test_string,og_string);

        //calls method to be tested
        test_editor.test_method_e(doc,"insert");

        //actual output and expected output
        new_string=doc.toStringDocument();
        test_string="inserttest";

        //test if actual and expected output are equal
        assertEquals(test_string,new_string);

        //calls method and tests new expected output  
        test_editor.test_method_e(doc,"add");
        new_string=doc.toStringDocument();
        test_string="insertaddtest";
        assertEquals(test_string,new_string);
        
        
        
        //creates objects for testing using linked list buffer
        Document doc_2=new Document("linked");

        //loads file
        try
        {
            doc_2.load_file("file.txt");
        }
        catch (java.io.IOException ioe)
        {
            ioe.printStackTrace();

        }

        //tests original output to show they are equal
        String og_string_2=doc_2.toStringDocument();
        String test_string_2="this|is|a|test|file";
        assertEquals(test_string_2,og_string_2);

        //calls method to be tested
        test_editor.test_method_e(doc_2,"insert");

        //actual output and expected output
        String new_string_2=doc_2.toStringDocument();
        test_string_2="this|is|a|test|insertfile";

        //test if actual and expected output are equal
        assertEquals(test_string_2,new_string_2);

        //calls method and tests new expected output  
        test_editor.test_method_e(doc_2,"add");
        new_string_2=doc_2.toStringDocument();
        test_string_2="this|is|a|test|addinsertfile";
        assertEquals(test_string_2,new_string_2);
        
        //test for file with single line using linked list buffer
        //loads file
        try
        {
            doc_2.load_file("test.txt");
        }
        catch (java.io.IOException ioe)
        {
            ioe.printStackTrace();

        }

        //tests original output to show they are equal
        og_string_2=doc_2.toStringDocument();
        test_string_2="test";
        assertEquals(test_string_2,og_string_2);

        //calls method to be tested
        test_editor.test_method_e(doc_2,"insert");

        //actual output and expected output
        new_string_2=doc_2.toStringDocument();
        test_string_2="inserttest";

        //test if actual and expected output are equal
        assertEquals(test_string_2,new_string_2);

        //calls method and tests new expected output  
        test_editor.test_method_e(doc_2,"add");
        new_string_2=doc_2.toStringDocument();
        test_string_2="addinserttest";
        assertEquals(test_string_2,new_string_2);
        
        
        

    }
}
