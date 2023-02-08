# TextEditor
A Java text editor with a choice of two efficient data structures: gap buffer and linked list. This text editor offers basic features such as opening, writing to, and saving to files, and provides a fast and reliable way to manipulate and store text.

The text editor uses the Scanner class and runs on the BlueJ user interface

Run the Editor class, which contains the main, and enter "gap" to utilize the gap buffer data structure or enter "linked" to use a linked list data 
structure.

The Editor handles all file manipulation through the keyboard commands as follows:

o xxxxx - Open a file named “xxxxx”.

c - Save the current loaded data to the originating file.

s - Save the current loaded data to a new file. 

e - Edit the current-line, using the current cursor position. 

r - Move right one character. r x - Move right x characters. 

l - Move left one character. l x - Move left x characters. 

d - Move down one line, perserving the cursor position, do not adding any new lines.

d x - Move down x lines, perserving the cursor position, do not adding any new lines. 

u - Move up one line, perserving the cursor position, do not adding any new lines. 

u x - Move up one line, perserving the cursor position, do not adding any new lines. 

q - Exit the program, without saving any data. ab - Add a blank line below the current-line.

aa - Add a blank line above the current-line. dl - Delete the current-line. 

dl x - Delete the current-line, and x - 1 lines below. 

If there are only less than x lines available, only remove those lines. cl - Clear the currently loaded data from the program.

