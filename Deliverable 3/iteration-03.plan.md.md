# PRODUCT NAME: uCalendar


## Iteration 3

 * Start date: Mar 13, 2018
 * End date: Mar 29, 2018

## Process

#### Changes from previous iteration

We did not make any changes to our process from the previous iteration. However, one process decision that was successful for us was the use of a Trello board. The Trello board allowed us to see exactly every task that needed to be implemented at a high level. It also allowed us to assign these tasks to the right team member and see exactly what stage of development each task was. This allowed us to be successful throughout our project because everyone had at least a high level idea of all the functionality the program needed to have. The task board also allowed us to keep in mind any future ideas and not have to ask "what is there left to do?". A success metric we could use to assert that this decision was successful is a count of all the times in our group chat where someone had to ask for what they should work on next (this never happened).

#### Roles & responsibilities

* Nghiem Ly - Front end UI
* Alexander Cherkaski - Classes which hold events/courses/notes for a specific calendar/user, Calendar serializer, Dropbox API Integration
* Dicky Zeng - Calendar Helper Functions, Course Helper functions, UI functions
* Mang Yau - File sharing functionality, function to identify calendar time block conflicts
* Christopher Vander Heide - Calendar Helper Functions, function to identify calendar time block conflicts
* Prince Minhas - Event Class, Calendar Helper Functions
* Jordan Tram - Calendar Helper Functions, Functionality to find free time slots in calendar

#### Events

Mar 13 at BA3200 (In-person): In this meeting, we will review the code from the second iteration. We will also clarify any concerns from the second iteration, take note of all bugs/errors, and think of potential fixes. This will allow us to correct code from the previous iteration and head into the third and last iteration smoothly.

Mar 15 at Tutorial Room (In-person): This meeting will be used as a quickly weekly sync before implementing a majority of the code for this iteration. We will use this meeting to check on the progress of each individual's task and discuss all the functionalities that need to be implemented in this iteration. We will then update the Trello task board accordingly and delegate the tasks to members.

Mar 22 at Tutorial Room (In-person): This meeting will serve as a code review session to go over new implemented code from this current iteration. We will also go over and simulate user flow in the GUI in order to prepare for the demo video. We will make a list of any new found bugs/errors/concerns and think of possible fixes. 

Mar 27 (Online): This meeting will server as a coding session; we will polish the GUI to make user flow more intuitive. We will communicate over Skype as a team. We will then make a list of any final functionalities/features we think we have time to implement before the end of the iteration. 

Mar 29 at Tutorial Room (In-person): In this meeting, we will prepare for the final demo and address any last minute concerns.

#### Artifacts  

We will continue to use and update our Trello task board from the previous iteration in order to organize, schedule, and delegate our necessary tasks. Our current Trello task board has a list representing each stage of the software development cycle (i.e. "Testing" or "In Progress") and we can pin tasks to each list. We will move tasks along as they progress through the software development cycle. Each task will be colour coded based on priority (we will determine priority for each task as a group); red represents high priority, yellow represents medium priority, and blue represents low priority. Tasks get assigned to team members based on what that team member is responsible for (i.e. if we need to add a new button to the layout, the member(s) responsible for the GUI will have that task assigned to them). We will also take into consideration how many tasks in progress that team member has already.

In addition to our task board, we have a Facebook group chat as a means to get an immediate response for any questions/concerns we have. 

#### Git / GitHub workflow

Unlike the previous iteration, we plan to each work on our own forks of the repository in order to avoid conflicts. This will allow us to each indepedently work on and add to the code without worrying about what every other team member is working on. When we are ready to push new code to the repository, we will submit a pull-request from the fork to the main repository. We will then require that at least 2 separate members review the pull-request and when the second member has reviewed and approved the new code, he will merge it into the main master branch of the repository. This idea is tentative, as in past iterations we all worked directly to the main repository. In the past iterations when we worked directly on the main repository, we didn't have many merge conflicts to fix since we were all working on mostly isolated parts of the codebase, so this allowed us to work more efficiently. If we find that merge conflicts are minimal, we will continue to work the way we have in the past.

## Product

#### Goals and tasks

The biggest goal for this iteration is to have a working serializer/deserializer for users' calendars. This is important because we need the data on users' calendars to persist ("As a user, I want to be able to come back to the program and have my calendar be the same way as when I left it"). To achieve this goal, we will need to agree on how we wish to serialize the data (i.e. whether to use Java's built in serializable object) based on which way we think is the best and most straightforward to implement. 

The next biggest goal for this iteration is to have the Dropbox API integration to allow for sharing of notes between users ("As a user, I want to be able to store and share notes"). To achieve this goal, we will need to familiarize ourselves with how the Dropbox API works by reading the documentation provided. We will then need to agree on which functionalities we need from this Dropbox server (i.e. ability to delete notes, etc). 

Another goal we want to accomplish for this iteration is polishing off the UI to make it more appealing and intuitive to users ("As a user, I want to have the smoothest and most intuitive experience possible when using this app"). To accomplish this, we will need to draw inspiration from other appealing and intuitive apps and understand what makes them user-friendly.

#### Artifacts
   
We will produce interactive mock-ups of the calendar that show the implemented Dropbox functionality as well as serializer functionality. Having this artifact will give all team members a better understanding of how the new features work. It will also give us an idea of what changes we want to make to these features going forward. We will also show code segments of the new features (Dropbox and serializer). This will allow us to go over the code for the new features and review it as a team.