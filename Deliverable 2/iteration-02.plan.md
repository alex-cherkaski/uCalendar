# PRODUCT NAME: uCalendar


## Iteration 02

 * Start date: Feb 21, 2018
 * End date: Mar 8, 2018
 

## Process

#### Roles & responsibilities

Describe the different roles on the team and the responsibilities associated

* Nghiem Ly - Front end UI
* Alexander Cherkaski - ACORN .ics parser, CourseBuilder, Classes which hold events/courses/notes for a specific calendar/user
* Dicky Zeng - Calendar Helper Functions, Course Helper functions, Event class additional features
* Mang Yau - File sharing functionality, function to identify calendar time block conflicts
* Christopher Vander Heide - Calendar Helper Functions, function to identify calendar time block conflicts
* Prince Minhas - Event Class, Calendar Helper Functions
* Jordan Tram - Calendar Helper Functions, Functionality to find free time slots in calendar

#### Events

Feb 27 at BA3200 (In-person): 
Meeting to work on iteration document and discuss about issues and ideas
Discuss the current progress of each individual’s tasks
Review each other’s code to ensure that there would be no upcoming conflict as we continue
Come up with a way to reformat functions between classes and create a utility class to reduce redundancy
Have a detailed discussion about how the physical layout of the GUI (front-end) should look like (e.g. popup windows, graphics transitions). This will create more clarity on the code that needs to be written for both the front-end and back end.

Mar 1 tutorial (In-person):
Start thinking and talking about how the video is going to be made. How much of the GUI should we ideally have done before we start the video? How are we going to split our time between talking about the back-end and the front-end and our design plans for the future?
Talk about the progress that we have made between the last meeting and this meeting. 
Talk to other group members about the changes that they would like to make going forward.
Conduct a code review on all the new features that have been implemented since the last review, so that every group member has a complete understanding of the relationship between classes and data elements.

Mar 8 tutorial (In-person):
Begin the process of recording the video. This is most likely going to be done by speaking into a microphone while showing the GUI on the screen in parallel.
Discuss any bugs that need to be addressed and fixed immediately before recording the video so that the product can be accurately represented.
Continue to discuss the progress that we have made individually and also collectively in meeting our goals in terms of timeline and functionality.
Talk about design and implementation issues that have been dynamically raised, and were not planned for.

Mar 8 Coding Session (Online Event): 
Begin coding that google drive portion of the application. The feature that allows us to store information (i.e. class notes) in a particular structure and allows us to retrieve it when desired by the user.
Have the coding session done online while everyone is connected using skype or another voice chat application.
Since no one in the team particularly has experience with google drive it will important to do start coding together, so that we can answer questions and/or reservations that another person may have.

#### Artifacts

We will be using a Trello task board to organize and delegate our tasks (available at https://trello.com/b/M8U3j738). Each list in the task board will represent a certain stage of the software development cycle. We will move each task between stages as we progress. On the task board, we will colour code the tasks based on priority (red = high priority, yellow = medium priority, blue = low priority). We will assess the complexity of each of the tasks and give appropriate due dates. Based on this assessment, we will assign tasks to team members as equally as possible. Additionally, tasks will be assigned based on what each member has been working on (i.e. the member(s) in charge of the Calendar class will be assigned a majority of the tasks related to Calendar functions).
We will also be using social media websites like Facebook to facilitate more detailed conversations. These are especially useful in scenarios like these where something unexpected could always happen, and require immediate priority/attention. Whether it is someone getting stuck on a function or not having enough time to complete their part. There are always challenges that arise while trying to complete tasks (i.e. coding and design), so Facebook will allow us to constantly be on top of any problem that any team member may have. Within the larger goals that we have set up on Trello, there will definitely be smaller goals that can be derived from them. Facebook will also be useful for these scenarios in terms of handling the priority and distribution of workload of these smaller challenges.

#### Git / GitHub workflow

Our Git workflow was to simply commit to the master branch. Each group member is largely responsible for isolated classes and features so risk of conflict was minimal. When group members complete major functionality components of their classes other group members conducted code reviews and add additional functionality as needed. Such an approach allows us to work in a much more dynamic matter and assist one another as needed.
Despite being assigned different tasks, conflicts may still very well arise. However due to our constant communication on Facebook and organization plan (showed in Trello), we are always aware/cognitive of the specific conflict before it actually takes place. This allows us to easily collectively decide on how we would like to solve the problem, and makes the Git workflow very effortless and efficient. This process is also assisted by constantly review the commits of everyone group member. 
In the next iteration as the amount of interconnected work increases even more, we will all be working on our own forks of the project. We will then submit pull requests from the fork to the master branch upon getting a certain functionality/feature working and require at least 2 other members to review and approve the code before merging it to the master branch. Specifically, the first member to review and approve the code will leave a comment (saying they approve the changes), and after the second member reviews and approves the code, they will have the responsibility of merging it to the master branch. We are choosing to use this workflow in the next iteration because with all the basic features implemented, we will now be tying more of the backend to the frontend and using/working with each other’s work.


## Product

#### Goals and tasks

The first and most important goal we would like to have done for this iteration is a have a basic GUI/front end working with multiple views to navigate through. More specifically, we want at least a displayable calendar where we can navigate through different weeks and add different elements to the calendar such as courses and events. This will give us enough to have a minimum viable product to display for the video.  For this to be done, we need to have implemented in the backend: a calendar class which can hold events and courses and perform various actions on the calendar (i.e. add new events and courses); and event and course classes which hold info about the specified event/course (such as the time it takes place and the duration). On the front end, we will need to have buttons to allow a user to import their .ics files, add new events, add new courses and we need buttons to shift through weeks on the calendar. We also need to decide on an appealing and intuitive layout of all the UI elements in order to simplify use.

An equally important goal we would like to have for this iteration is a parser which will be used to parse the info from an .ics file. In terms of a user story, this goal could be illustrated as follows: “As a user, I would like the ability to import existing schedules onto this application and add to it.” ACORN allows UofT students to export their ACORN schedules in .ics format. We want to be able to take those files and allow users to import them into the application to display their courses (along with other events they add in the app) on the calendar. To do this task, we need to understand the contents of the .ics file so we can grab the important parts needed (such as course name, time, etc) to be passed onto other functions of the backend. 

A less important goal we have for this iteration is to have functions which can identify conflicts and find free time slots both within a calendar and between two calendars. This will be useful when we begin populating calendars with more and more events, so we can choose how to warn and display conflicting events. Also, if we know the free time slots in a calendar, we can make recommendations on times to plan events between multiple students. To complete this task, we will need to maintain a list of all events and courses for every calendar. Each list element will contain the event/course name and the time slot it takes up. We will need to implement these lists and the data structures that the list elements themselves take on. 

Another goal we have is to implement all the minimum features to allow for note-sharing. This goal may not be met this iteration, but to get this done, we need to have classes for a single note as well as a collection of notes. We will also need to have timestamps on these notes to allow for convenient sorting. Additionally, we will need to decide on how we will store these notes of various users. Initially, we decided to integrate a server such as Google Drive (using the Google Drive API) and have the notes be uploaded to their servers. 

#### Artifacts

* Interactive mock-ups of the calendar that demonstrates the behaviour and functionality of importing calendar files, adding and removing items on the calendar as well as notes
  * The purpose of this artifact is to clarify and decide on what features should be kept or added before the backend coding begins.
  * We also need this to create a MVP, and video demonstration purposes.
  * It can also be used to better explain the functionality/features that we want to implement going forward.
* Code segments for implemented features that allow basic functionality of the UI
  * The purpose of this artifact is to present how these features will be implemented and to point out any mistakes and hindsights in the logic of the code.
* Statistics about usage of the features that most calendar apps have
  * The purpose of this artifact is to demonstrate that we have implemented the most desirable and useful features while disregarding gimmicky features.
