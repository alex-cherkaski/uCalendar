# PRODUCT NAME: TBA

#### Q1: What are you planning to build?
We are planning to build a desktop application for University of Toronto students that allows them to share information (e.g. notes) with each other and also organize study sessions and other events amongst their classmates. The problem we are trying to solve is as follows: as University of Toronto students, we need an easy and efficient way to have direct access with people in our classes, in order to share resources and receive assistance from each other. In our application, students are able to organize notes for specific classes, exchange notes, and plan meeting times, as well as make additional plans on the in app calendar interface.

#### Q2: Who are your target users?
The target user base are students of the University of Toronto. The application is for students that want to find and connect with other students in their classes, organize and exchange notes, and plan study groups. This application centralizes all of these functions in one simple application instead of multiple different applications with extra features that may not be useful to them. This allows for efficient organization and planning.

#### Q3: Why would your users choose your product? What are they using today to solve their problem/need?
Users would choose our product because it allows them to organize in a centralized manner. They can build their calendar, setup and check for possible meeting times with other students, and organize and exchange notes. Other apps like Facebook Messenger and WhatsApp give students the ability to the same but with more limited functionality, and features that are not meant to directly facilitate the needs of students. Their main aim is to help users communicate through a chat, but is limited in allowing people to compare schedules, does not allow them to formulate schedules in a unified place, and is not designed to effectively store and read notes in an organized manor. Everything that is done in the chat is done in a linear fashion which introduces difficulties in maintained a specific desired structure of notes. For example course notes posted in a chat several months ago may be difficult to find. Our application will allow the uploaded documents to be organized in a custom way (date/time being one of them). The ability to quickly compare schedules and form new meeting plans in the same app in one calendar is a big asset to flexibility and productivity. Our app allows users to easily organize class notes in the calendar under specific dates and classes. The ability to organize and share notes in such a fluid manner is simply not present in existing applications that user use. 

### Highlights
Our application will be a desktop application instead of a mobile application or a web application. The application allows us to manage our schedule and view notes that the student has taken or received from others. The team agreed that a desktop experience would be most beneficial as editing and viewing experiences are more restricted on a mobile device. Our next decision on managing uploads and downloads is dependant on how we simulate/integrate a server that can host our files for students to access. A server is more accessible on a desktop platform which led us to consider options such as a localhost server just for presentation purposes or purchase an actual server. As a team, we decided that we should integrate a server through other means and landed on the alternative of GitHub or Google Drive. There is a Google Drive API that allows us to implement file retrieval and submissions for our application. Another function of our application is to allow a student to view their schedules along with their notes specific to lecture slot. Similar to Google Calendar where one can import schedules, our team decided to import schedules directly from our ACORN schedules (given as .ics format).

*listing alternatives*

How to integrate a server into the application - github/purchase a server/localhost server

Managing uploads and downloads - server/local file system

How to import ACORN schedules - Google Calendar

We debated implementing the solution as either a mobile app, a desktop app, or a web app and at the end, we settled for a desktop solution that would connect to a server. 
