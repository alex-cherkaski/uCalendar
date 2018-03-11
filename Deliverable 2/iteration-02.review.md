# PRODUCT NAME: uCalendar


## Iteration 02 - Review & Retrospect

 * When: Mar 8, 2018
 * Where: LM (Lash Miller Laboratories) 155

## Process - Reflection

#### Decisions that turned out well

* Trello task board (linked in the plan.md)
  * Having a task board allowed us to be more organized and know exactly what stage each feature/part of the product was at at a particular point in time. 
  * Also allowed us to see what features were still needed to be implemented, so members would always know what they could be working on (no one ever had nothing to work on or didn’t know what they could be working on)
* Working directly on the repository (as opposed to on separate forks)
  * Since we were working on parts that didn’t overlap with each other we didn’t have to worry too much about conflicts.
  * Allowed us to work efficiently because we did not need to have pull requests be reviewed and approved.
However, we do realize that as the codebase grows, emphasis needs to be placed on regular code reviews and conflict control management.

#### Decisions that did not turn out as well as we hoped

* Task delegation
  * The way we delegated our tasks in the iteration could have been done differently.
  * For this iteration, we only had one person mainly working on the front end; this proved to be problematic as the front end was a main emphasis for this iteration and any time we needed a new front end feature, that person was usually tasked with implementing it (because everyone else did not understand enough of the UI code clearly).
* Code review process
  * Without pull requests, no one technically had to review the code before it entered the main branch.
  * As a result, members weren’t familiar enough with other aspects of the codebase that they didn’t implement and often times had to ask the writer of the code how it worked.
  * In the next iteration, we will work on separate forks and use pull requests (as described in plan.md) and make sure documentation is present throughout the code.


#### Planned changes

Going forward, we are planning to all work on our own forks (as specified in plan.md) instead of committing changes directly to the master branch. We decided to make this change because moving forward, we will be working a lot more with parts of code implemented by other members. This creates a greater potential for conflicts within the code. Working on our own forks will allow for easier conflict management. Also, it will force us to have separate members review every change before it becomes a part of the master branch. This is crucial because as our codebase grows and grows with more and more functionality/features, we need to start making sure things are as correct and efficient as possible. 

We are also planning to have weekly meetings in addition to the tutorial meeting hour. Before, these meetings were tentative. Now, since we have a lot of different features and classes implemented and will continue to add more, we need to make sure we have regular meetings to discuss the direction of the project and make sure everyone is clear on every aspect of the codebase. With this meeting in addition to the weekly tutorial meeting, we think it will be easier to keep everyone coordinated and organized.


## Product - Review

#### Goals and/or tasks that were met/completed:

(ARTIFACTS TO BE ADDED)

We met our main goal of having a basic front end with multiple navigable views working. We have a working displayable calendar with the ability to move through different weeks and add events (https://i.imgur.com/RZf6ErZ.png, https://i.imgur.com/CkMPhSp.png, https://i.imgur.com/U1JCm0S.png). However, we noticed when creating the video and simulating user flow, we ran into some bugs. For example, we noticed events don’t get added to the calendar if there are no notes added beforehand. Also, if you scroll through enough, a deleted event may re-appear in the calendar. We have composed a document listing the bugs we encountered and need to address for the next iteration.

We had also planned to implemented the ability to parse an .ics file to allow users to import their ACORN calendars. We managed to successfully implement this; we managed to figure out the format of the .ics file and grab important info such as the course name and times (https://i.imgur.com/h5oTRcL.png). We then pass this info into a list of courses that can be displayed on the calendar. 

The goal of having functions to identify conflicts and free time slots both within a calendar and between two calendars was mostly met. We have a function which identifies conflicts in a calendar, a function that identifies conflicts between two calendars, and a function which finds the free time slots within a single calendar (https://i.imgur.com/Fo946Z9.png, https://i.imgur.com/ObQ3MAw.png). However, we have not yet implemented a function to find all free time slots between two calendars. This was because we decided to focus our attention on a minimum viable product for this iteration, and this function was not needed for this iteration. 

#### Goals and/or tasks that were planned but not met/completed:

The only goal from the iteration plan that we did not meet yet was the note-sharing functionality. This goal was not met yet because for this iteration, we decided to focus on the calendar portion of the application and the note-sharing functionality would be the main focus of the next iteration. Also, we still have not finalized which server we are going to use to store the notes; originally we planned to use Google Drive, but after some further research, we discovered that the Dropbox API may be a lot easier to work with. Therefore, we need to do more research on the server we plan to use in the next iteration.


## Meeting Highlights

Our first main insight going into the next iteration is that we need to put more of an emphasis on code documentation. Too often members of the group would not be able to figure out how others’ code worked and had to ask. We feel that with better documentation guiding readers through how the code works, we would reduce the need to ask other members as much. 

Additionally, on the next iteration, we want to put a greater focus on code reviews. This would also help eliminate the problem described above, as members would familiarize themselves better with more parts of the codebase. As stated in the plan.md, we will work on separate forks and at least 2 members will need to review and approve changes before they get committed to the master branch via a pull request. 

The final thing we want to focus on for the next iteration is how we will implement the note-sharing part of the application. We need to all research on different server APIs (Google Drive vs Dropbox) and agree on which to use. We then need to all familiarize ourselves we how the API works and collectively work to implement this major part of our application. 
