# User Guide

## 1. Running the Program
1. Open the project in IntelliJ IDEA
2. Run the `Main` class
3. The console will display the system output and demonstrate the library system's features

## 2. Adding Users and Items
The library system works with 3 different type of users and 3 different type of Items

### Users: 
  --> Student
  
  --> Teacher
  
  --> Admin

### Items:
  --> Book
  
  --> DVD
  
  --> Magazine

Steps:
1. Initialize the users and add them to library
2. Initalize the items and add them to library

  <img width="1004" height="659" alt="Users and Items screenshot" src="https://github.com/user-attachments/assets/81af82ae-db74-431d-97b7-804ca416f4ec" />

## 3. Borrowing an Item
Users can borrow items if they are available and within their borrowing limits.
(Student = At most 5 books, Teacher = At most 10 items, Admin = Cannot borrow)

Steps:
1. Select a user
2. Select an item
3. Call borrowItem()

<img width="1057" height="323" alt="Borrow screenshot" src="https://github.com/user-attachments/assets/d1d94e0d-7a0b-47cb-8b77-382f237fe920" />

## 4. Returning an Item
Borrowed items can be returned back to the library

Steps:
1. Select borrowed item
2. Call returnItem()

<img width="1047" height="345" alt="Return screenshot" src="https://github.com/user-attachments/assets/35119b35-4f4e-40e3-8c28-fa141b62ba58" />

## 5. Searching for Items
The library system supports 3 search methods:

  --> Search by title
  
  --> Search by author
  
  --> Search by creator

<img width="1056" height="693" alt="Search screenshot" src="https://github.com/user-attachments/assets/1d92b9ff-0d7a-498b-8868-925e1cc88961" />

## 6. Admin Feature: Generate system report
<img width="813" height="900" alt="Generate report screenshot" src="https://github.com/user-attachments/assets/ae6e3ddb-c59e-41da-b97a-f61f4032d432" />

## 7. Admin Feature: Mark item as lost
<img width="1103" height="601" alt="Lost screenshot" src="https://github.com/user-attachments/assets/b11ea36b-6dcd-4e8e-8293-f37bf62b9896" />

## 8. Admin Feature: Back up data to CSV
  
  admin.backupData(library);

## 9. CSV
The system can load previously saved data from CSV files when the program starts.
<img width="1079" height="1230" alt="CSV screenshot" src="https://github.com/user-attachments/assets/42636b16-4c39-4a4f-8864-25dc463fbce1" />
