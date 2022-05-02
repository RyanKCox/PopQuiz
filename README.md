# PopQuiz
![image](https://user-images.githubusercontent.com/99361226/166301203-8d996c83-0103-4ba5-8867-c8bfbecf3bff.png)


## Project Description

Pop!Quiz is a quiz building and self study app. Users can create their own quizzes or download them from the server. Created quizzes can be given weblinks to tutorials and documentation on the subject. All quizzes generate flash cards based on the questions they contain. Once the user has taken the quiz, they can review the correct answers and scores are saved o the user's profile.

## Technologies Used

Kotlin
Jetpack Compose
HostNav Navigation
MVVM Design Pattern
REST API
RoomDB
DataStore
AlarmManager
Dagger - Hilt

##Features

* Account Creation
* Profile
* Locally stored Quizzes
* Notifications
* Flash Cards
* Searchable Database
* Resource weblinks

## Getting Started

1. Download the project at https://github.com/RyanKCox/PopQuiz.git
2. Import into Android Studio
3. Build and run the project
4. Register for an account
5. Upload your toys and begin trading

## Usage

First time users will register a new account using an email, password, and name. Once registered users can log in and have the option to stay signed in. Users are then taken to the Saved Quizzes screen. Here all locally saved quizzes will be displayed. A Navigation Drawer is used to navigate the app. Search Quizzes displays a list of quizzes available to users from the server. When clicked, an option to download the quiz is given. The Create a quiz option in the navigation drawer will take the user through the process of quiz creation, filling out a title, descriptions, topics the quiz covers, resource links to external study guides, and finally question creation. There are three types of questions: True or False, Single Answer, and Multiple Answer. When at the Saved Quizzes screen, users can search for quizzes by title and upon selecting a quiz, are taken to the Quiz Overview. The Quiz Overview screen displays information about the quiz, the external link to resources, and buttons for editting the quiz, taking the quiz, and flashcards generated for the quiz. After taking a quiz, users are able to review their answers. Finally, in the Profile Screen, users will see a list of the quizzes they have taken along with user statistics such as average score, most taken quiz, and how many quizzes they have taken.

## ScreenShots
![Profile](https://user-images.githubusercontent.com/99361226/166309232-e1b87b3b-2ed2-4075-9b18-066769eba170.jpg)
![QuizOverview](https://user-images.githubusercontent.com/99361226/166309476-630f54d0-9783-4d84-824e-a92e7f47c813.jpg)
![FlashCards](https://user-images.githubusercontent.com/99361226/166309591-5965f896-1f10-4356-8008-819010b7af26.jpg)
![Quiz](https://user-images.githubusercontent.com/99361226/166309688-62bdd873-ec5a-4af3-8c4c-e8f576ca817d.jpg)

## Contributors

* Ryan Cox (<https://github.com/RyanKCox>)
* Jonathan Castaneda (<https://github.com/jonathancastaneda-revature>) 
* Sarah Korrin Butler (<https://github.com/sarahkorrin>)
* Evan Jones (<https://github.com/Evy724>)
* Jacob Edens (<https://github.com/jacobedens>)
