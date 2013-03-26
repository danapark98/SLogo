180 man hours spent
Start date: February 15, 2013 
End date: March 25, 2013
https://github.com/srwareham/SLogo
Sean, Yoshi: View
Ellango, Ryan, Scott: Model / Controller
Resources Consulted: stackoverflow.com, Google Images
(default package) Main.java to run the simulation.  This file can be modified to change the language or the title of the simulation

Program use: 
File-> New to open a new workspace
File-> Load to load a previously saved workspace
File-> Quit to close the program. Note: Only the last window closed (if multiple workspaces are open) should be done so with File->Quit
File-> Help to display html file documenting SLogo command usage
File -> Save Options-> Save to save the workspace to an output file
File->Save Options-> Autosave to enable workspace saving automatically
File->Save Options-> Manually save to disable workspace autosaving / mandate user action for saving (this is the default behavior)
File -> Quit to terminate all running processes related of the simulation
All necessary data files can be found in src/resources
Easter eggs: Type in “unicorn” to change the line color to a rainbow effect. Also try setsh 3 for added effect.

Known Bugs:
The Operating System level close button (ie red “x” in Windows or red circle in Mac) does not kill the process running our simulation- it only closes the view.  We were presented with the choice of this error or that all running workspaces would be terminated upon any of them being closed out.  Our quit button, however, successfully terminates the underlying process.
Line drawing for a distance past 6990 (ie fd 7000)  results in stack overflow from a recursive method
Defined functions do not always save properly to files.
Can only highlight one line at a time from command history for copy/paste
