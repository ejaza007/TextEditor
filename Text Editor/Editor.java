import java.util.Scanner;

/**
 * Takes in input using Scanner Class
 * Runs document class methods depending on input
 * 
 * @author Armaghan Ejaz
 * @version 13-03-2022
 */
public class Editor
{
    //instance variables 
    private static  String temp;

    /**
     * Main method for scanner class
     * uses if-else structure to run methods
     * Creates Buffer Structure using Gap Buffer or Linked List Buffer depending on 
     * User provides String
     *
     * @param String 
     * 
     */
    public static void main(String type) throws java.io.IOException {

        //main method for taking input from scanner 
        System.out.println("Welcome to SimpleEditArray");
        System.out.println("(Open a file)");

        Scanner s = new Scanner(System.in);
        Document doc=new Document(type);
        temp=s.next();
        System.out.println("? exampleFile.txt");

        //loop for scanner 
        while(!(temp.equals("q"))){

            //method opens file
            if(temp.equals("o")){
                Scanner s2=new Scanner(System.in);
                String temp_2=s2.nextLine();
                String name=temp_2;                

                System.out.println("=====================================");
                System.out.println("Loading file" + " " + name);
                doc.load_file(name);
                String printer=doc.toStringforPrint();

                System.out.println(printer);

                System.out.println("=====================================");

            }

            //method saves file
            else if(temp.equals("c")){
                doc.store_file();
            }

            //method saves new file
            else if(temp.equals("s")){
                Scanner s2=new Scanner(System.in);
                String temp_2=s2.nextLine();
                String name=temp_2;                
                doc.store_file(name);
            }

            //inserts text where cursor
            else if(temp.equals("e")){

                Scanner s2=new Scanner(System.in);
                String temp_2=s2.next();

                doc.insert_text(temp_2);

                System.out.println("=====================================");

            }

            //moves cursor to the right
            //moves cursor by inputted value
            else if(temp.equals("r")){
                Scanner s2=new Scanner(System.in);
                if(s2.hasNextInt()){
                    int number=s2.nextInt();
                    doc.cursor_right(number);
                }

                else{

                    doc.cursor_right();
                }

                System.out.println("=====================================");
            }

            //moves cursor to the left
            //moves cursor by inputted value
            else if(temp.equals("l")){

                Scanner s2=new Scanner(System.in);
                if(s2.hasNextInt()){
                    int number=s2.nextInt();
                    doc.cursor_left(number);
                }

                else{

                    doc.cursor_left();
                }
                System.out.println("=====================================");

            }

            //moves cursor to the down
            //moves cursor by inputted value
            else if(temp.equals("d")){

                Scanner s2=new Scanner(System.in);
                if(s2.hasNextInt()){
                    int number=s2.nextInt();
                    doc.cursor_down(number);
                }

                else{
                    doc.cursor_down();
                }
                System.out.println("=====================================");

            }

            //moves cursor to the up
            //moves cursor by inputted value
            else if(temp.equals("u")){
                Scanner s2=new Scanner(System.in);
                if(s2.hasNextInt()){
                    int number=s2.nextInt();
                    doc.cursor_up(number);
                }

                else{
                    doc.cursor_up();
                }
                System.out.println("=====================================");

            }

            //method inserts new line
            else if(temp.equals("ab")){
                doc.insert_line_below();
                System.out.println("=====================================");
            }

            //method inserts above line
            else if(temp.equals("aa")){
                doc.insert_line_above();
                System.out.println("=====================================");
            }

            //method deletes line
            else if(temp.equals("dl")){
                Scanner s2=new Scanner(System.in);
                if(s2.hasNextInt()){
                    int number=s2.nextInt();
                    doc.remove_line(number);
                }

                else{
                    doc.remove_line();
                    System.out.println("=====================================");
                }
                System.out.println("=====================================");
            }

            //creates a new document
            else if(temp.equals("cl")){
                doc=new Document(type);
            }

            Scanner scnr=new Scanner (System.in);
            temp=scnr.next();

        }
    }

    //method created for testing
    public void test_method(Document doc,String input){
        temp=input;
        if(temp.equals("r")){
            doc.cursor_right();
        }
        else if(temp.equals("l")){
            doc.cursor_left();
        }
        else if(temp.equals("d")){
            doc.cursor_down();
        }
        else if(temp.equals("u")){
            doc.cursor_up();

        }

        else if(temp.equals("ab")){
            doc.insert_line_below();

        }

        else if(temp.equals("aa")){
            doc.insert_line_above();

        }

        else if(temp.equals("dl")){
            doc.remove_line();

        }

    }

    //method created for testing input method
    public void test_method_e(Document doc,String input){

        doc.insert_text(input);
    }
}