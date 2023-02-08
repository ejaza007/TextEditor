/**
 * Constructor for GapBuffer using Linked List
 * Implements GapBuffer Interface
 * Creates nodes of characters and uses space as a cursor
 */

public class LinkedListBuffer implements GapBufferInterface{

    //instance variables
    private int gap_size;
    private int cursor_position;
    private Node head;

    /***
     * Creates Node class,
     * 
     * stores characters
     */

    class Node
    {
        //creating Node class variables
        char data;
        Node next;
        Node(char d)    {data = d; next = null; }
    }

    /***
     * Default constructor
     */

    LinkedListBuffer(){

    }

    /***
     * loads characters of the string into nodes
     * @param string
     * 
     */

    public void load_string(String str_value){
        //empties buffer
        empty_buffer();

        //sets head
        head=new Node(' ');
        Node curr=head;

        //loop adds each character from a string
        int i=0;
        while(i<str_value.length()){
            Node buffer=new Node(str_value.charAt(i));
            curr.next=buffer;
            curr=buffer;
            i++;

        }
        return;

    }

    /***
     * Utility method, will empty the current buffer, set cursor to zero,
     * make rest of array spaces.
     */
    public void empty_buffer() {

        //empties buffer
        //sets cursor position to 0
        cursor_position = 0;
        head=null;

    }

    /***
     * returns elements of buffer as a String
     * 
     * @return String
     */

    public String toString(){
        //sets variables

        String returner = "";
        String returner_2="";
        Node temp=head;
        Node temp_2=head;

        if(head==null){
            return "";
        }

        //runs loop for cursor at start
        //adds string and returns

        if(cursor_position()==0){
            Node current=temp.next;
            while(current!=null) {
                returner += current.data;
                current = current.next;

            }
            return returner;
        }

        //runs loop for cursor at end
        //adds string and returns
        if(cursor_position()==length()){

            while(temp.next!=null){
                returner += temp.data;
                temp = temp.next;                
            }

            return returner;
        }

        //adds loop if cursor in the middle
        //adds string till cursor, skips cursor, adds loop again
        int i=0;
        Node current=temp;
        while(i<cursor_position()){
            returner+=current.data;
            current=current.next;
            i++;
        }

        Node current_2=current.next;
        while(current_2!=null){
            returner_2+=current_2.data;
            current_2=current_2.next;

        }

        return returner+returner_2;
    }

    /***
     * returns length of the nodes
     * 
     * @return integer
     */

    public int length(){
        //runs loop 
        //increments counter till there is no node
        Node temp=head;
        int counter=0;
        if(temp!=null) {
            while(temp.next!=null) {
                temp=temp.next;
                counter++;
            }
            return counter;

        }
        return 0;
    }

    /***
     * returns location of the cursor
     * 
     * @return integer
     */

    public int cursor_position(){
        // returns cursor
        return cursor_position;
    }

    /***
     * moves cursor to the left
     * returns true/false based on if it can
     * 
     * @return True/False
     */

    public boolean cursor_left(){
        //sets temp variable
        Node temp=head;

        //returns false if cursor can not move left
        if (cursor_position()==0){
            return false;
        }

        //loops till cursor reaches cursor
        int i=0;
        while(i!=cursor_position()-1){
            i++;
            temp=temp.next;

        }

        //switches cursor with character to the left
        char temp_char=temp.data;
        temp.data=' ';
        temp.next.data=temp_char;
        cursor_position--;
        return true;

    }

    /***
     * moves cursor to the left
     * returns true/false based on if it can
     * 
     * @param character count
     * 
     * @return True/False
     */

    public boolean cursor_left(int char_count){
        //repeats cursor left for integer value
        if(cursor_position()<char_count){
            return false;
        }

        for (int i=0;i<char_count;i++){
            cursor_left();
        }
        return true;
    }

    /***
     * moves cursor to the left
     * returns true/false based on if it can
     * 
     * 
     * @return True/False
     */

    public boolean cursor_right(){  
        //sets temp variable
        Node temp=head;

        //returns false if cursor can not move right
        if(cursor_position()==length()){
            return false;
        }

        // if cursor at start switches position with character to the right
        if(cursor_position()==0){
            char temp_char=temp.next.data;
            temp.next.data=' ';
            temp.data=temp_char;
            cursor_position++;
            return true;
        }

        //loops till cursor reaches cursor
        int i=0;
        while(i!=cursor_position()){
            i++;
            temp=temp.next;

        }

        //switches cursor with character to the right
        char temp_char = temp.next.data;
        temp.next.data=' ';
        temp.data=temp_char;
        cursor_position++;

        return true;

    }

    /***
     * moves cursor to the right
     * returns true/false based on if it can
     * 
     * @param character count
     * 
     * @return True/False
     */

    public boolean cursor_right(int char_count){
        //repeats cursor right for integer value
        if(char_count<(length()-cursor_position())){
            for(int i=0;i<char_count;i++){
                cursor_right();
            }
            return true;
        }
        return false;
    }

    /***
     * moves cursor to the start of the line
     * returns true/false based on if it can
     * 
     * 
     * @return True/False
     */

    public boolean cursor_move_start_line(){

        //move cursorm left to the start of the line

        //returns false if cursor is already at start
        if(cursor_position()==0 || head==null){
            return false;
        }

        //loops till cursor reaches the start
        while(cursor_position()!=0){
            cursor_left();
            if(cursor_position==0){
                return true;
            }
        }

        return true;

    }

    /***
     * moves cursor to the end of the line
     * returns true/false based on if it can
     * 
     * 
     * @return True/False
     */

    public boolean cursor_move_end_line(){
        //move cursor right to the end of the line

        Node temp=head;

        //returns true if cursor is already at start
        if(cursor_position()==length()){
            return false;
        }

        //loops till cursor reaches the start
        while(temp!=null){
            temp=temp.next;
            cursor_right();

        }

        return true;
    }

    /***
     * moves cursor to the left and removes a node
     * returns true/false based on if it can
     * 
     * 
     * @return True/False
     */

    public boolean remove_char_toleft(){
        //moves cursor to the left and removes node

        Node temp=head;
        //returns false if cursor moves to the left
        if (cursor_position()==0){
            return false;
        }

        //loops till node reaches cursor 
        int i=0;
        while(i!=cursor_position()-1){
            i++;
            temp=temp.next;

        }

        //moves cursor left
        //removes node
        //decrements cursor position
        temp.data=' ';
        temp.next=temp.next.next;
        cursor_position--;
        return true;
    }

    /***
     * moves cursor to the left and removes a node
     * returns true/false based on if it can
     * 
     * @param character count
     * 
     * @return True/False
     */

    public boolean remove_char_toleft(int char_count){
        //returns false if cursor moves to the left

        if(cursor_position()<char_count){
            return false;
        }

        //loops character count for integer count
        for(int i=0;i<char_count;i++){
            remove_char_toleft();
        }
        return true;
    }

    /***
     * inserts character at cursor location
     * returns true/false based on if it can
     * 
     * @param character value
     * 
     * @return True/False
     */

    public boolean insert_text(char char_value){
        //if text can not be inserted returns false

        if (head==null){
            return false;
        }
        
        
        Node new_node=new Node(char_value);
        Node current=head;

        //loops to reach cursor position
        int i=0;
        while(i<cursor_position()){
            current=current.next;
            i++;
        }
        
        //points node to inserted text
        //inerted text points to the node next to the original node
        Node temp=current.next;
        current.next=new_node;

        new_node.next=temp;
        return true;
    }

    /***
     * inserts character at cursor location
     * returns true/false based on if it can
     * 
     * @param String value
     * 
     * @return True/False
     */

    public boolean insert_text(String str_value){
        
        //loops insert text for the length of the string
        //adds the last character of the string
        
        for(int i=0;i<str_value.length();i++){

            Node new_node=new Node(str_value.charAt(str_value.length()-1-i));
            Node current=head;

            int j=0;
            while(j<cursor_position()){
                current=current.next;
                j++;
            }

            Node temp=current.next;
            current.next=new_node;

            new_node.next=temp;
        }
        return true;
    }

}
