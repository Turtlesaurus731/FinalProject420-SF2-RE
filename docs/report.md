# Library Management System Report

## 1. Introduction
This project is a library management system done in Java. It allows users to borrow, return and search for items such as books in the library. 
Furthermore, Admin users can also generate reports and backup data Using CSV files.

## 2. Features
User Management:
The system supports 3 types of users. 
  --> Student, Teacher, Admin
Students can borrow up to 5 books, Teachers can borrow up to 10 items and Admin can't borrow, as their role lies in management.
All three classes (Student, Teacher and Admin) are subclasses of the abstract User class, inheriting key methods such as getBorrowLimit
and canBorrow which set the borrow limmitations mentioned earlier. Borrowed items are also stored in a ArrayLists for each user, 
which helps in tracking what books are borrowed and by who. While the User classes define the borrowing rules, 
the Library class is in charge of processing the borrow operations ( and should be done there ).
However, something to note is that the User class can curently bypass the Library restrictions (which is a flaw on my end)

Item Management:
The system suports 3 types of items.
  --> Book, DVD, Magazine
All three classes (Book, DVD, Magazine) are sublcasses of the abstract Item class. This allows all items to share attrivutes such as IDs,
titles, and status. Like previously mentioned, all Items have a status field that indicates wether it is IN_STORE, BORROWED, or LOST. 
In addition, multiple copies of the same item can exit in the library granted that they have different IDs. Since they are differentiated 
by ID, it is easier to track the status of each physical copy.

Search methods:
The system supports 3 type of search methods all from the Library class
 --> SearchByTitle, SearchByAuthor, searchByCreator
Search by Author only works for books while search for creator works for all 3 items with the help of polymorphism?. 
Both recursive and stream-based versions of the SearchByTitle and SearchByAuthor can be used (SearchCreator is stream-based). 
Stream searches like streamSearchByTitle(), streamSearchByAuthor() and searchCreator() uses .filter, while recursive searches like
recursiveSearchByAuthor and recursiveSearchByTitle recursively traverse the item list to locate matching objects. Since the library can contain
multiple copies of the same item (with different IDs), the search methods remove duplicate search results with the help of the getSearchKey()
method that is defined in the Item class. 

Borrow and return methods:
All borrowing and returning operations are done through the Library class. Before an Item is borrowed, the code checks whether the user exists
in the system, whether the item is available, whether the user has exceeded their borrowing limitations. After successfully borrowing, it's 
status is changed from IN_STORE to BORROWED and the item is added to the users borred items list. This of course works the other way round as well.
where if an item is returned, it's status is changed back to IN_STORE. The system will throw exceptions when passing invalid operations.

Admin features:
Like stated prior, the Admin class is responsible fpr system management rather than borrwing items. Admins can generate reports displaying
all items and their status. They can also mark objects by calling the markItemAsLost method that calls a method of the same name in Library to 
update the items status to LOST. In addition, the Admin can back up all the users and items into CSV files.

CSV loading and backup:
User and Item information can be backed up into CSV files using the backupUsersCSV() and backupItemsCSV() methods from the library class.
These two methods use FileWriter to write the relevant data into their respective files (items.csv and users.csv). The system also
allows the restoration of previously saved data using loadUsersCSV() and loadItemsCSV() methods. These methods use Scanner to read each line
from the CSV files and recreate the object based on the type. For example, with the book type, the system will create a new book object with 
the data from the CSV row. Furthermore, the system allows the user to input a custom path but will default to the paths defined by the constants
if input is empty or null. Exception hangling is also present (try-catch) during file operations and will throw detailed error messages when
needed. 

## 3. Some of the used Data Structures
The project uses a lot of Java collections that I have learned in class to organize and manage the library.

 --> List  
ArrayLists are used to store both users and items in the library. Lists are also used to store borrowed items for each user. 
Lists were chosen because they allow easy insertion, traversal, and searching of objects.

 --> Stack  
A Stack called borrowedStack is used to keep track of borrowed items. Whenever an item is borrowed, it is pushed onto the stack. 
This follows the LIFO (Last In First Out) principle that I learned in class.

 --> Queue  
A Queue called returnQueue is used to track returned items. Whenever an item is returned, it is added to the queue. 
This follows the FIFO (First In First Out) principle.

 --> Set  
A HashSet called lostItems is used to store all lost items. 
Sets prevent duplicate values from being stored, making them useful for tracking unique lost items.

 --> Map  
A HashMap called itemMap is used to associate item IDs with Item objects. 
This allows items to be quickly retrieved using their unique ID instead of searching through the entire item list.

## 4. Junit test
Unit testing was done using Junit jupiter API to test various methods of the system. Some notible tests include:
 
 --> Adding items and users
 
 --> Borrowying and returning items
 
 --> Stream and recursive search mothods (making sure there is no duplicates that pop up in search result for example)
 
 --> Search by creator

## 5. Class Diagram
The final version of the class diagram for deliverable 1 is V5 (the file is called ClassDiagramV5)
