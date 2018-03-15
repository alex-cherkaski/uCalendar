 * IF YOU ARE SEEING A RED EXCLAMATION MARK ON THE PROJECT FOLDER ICON:
 * The build path got messed up. Probably because you cloned someone else's changes and hence their build path.
 * To fix this, right click on 'JRE System Library', go to 'Build Path', and click on 'Configure Build Path...'.
 * You will see for .jar file icons with red X's on them. 
 * Select all four of them and click 'Remove'. Then click 'Apply'.
 * Click 'Add External JARS...' and locate the jars in the '...\project-team-20\DropboxJars' folder.
 * Add all four of them and click 'Apply'.
 * Click 'Apply and Close'.
 * Done!
 
 Note: I tried just adding the jars to the project folder to avoid this crap, but build paths still get broken.
 The issue their is that eclipse caches (somewhere) all project related data and does not overwrite them when deleting and re-importing
 the .jar files. This only results in even worst errors and breaks compilation and build paths even more. For the sake of finishing
 this project on time, lets just stick to the steps I outline above.