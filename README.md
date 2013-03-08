SLogo
<hr>
View Team:<br>
Sean: 7.3 Hours spent Mon Feb 25, Tuesday: 2.9, Monday 4 <br>
Yoshi: 5.2 Hours spent Mon Feb 25, Tuesday: 2.9, Saturday: 3.5h, Sunday 2h, Monday 4<br>
<br>
Turtle picture: http://sweetclipart.com/cute-colorable-turtle-697


<b>How to add a new instruction:</b>

<li> Write class that extends Instruction (probably want to extend BaseInstruction): i.e. Class Loop extends BaseInstruction </li>
<li> add the ClassPath to instruction_index.txt in the resources package: i.e. instructions.Loop </li>
<li>. add keywords to each of the properties files 
example for keywords "loop" and "loopdeloop": 
<br>
<i>Loop = loop,loopdeloop</i> </li>
	
<li> Run and use your new instruction </li>
</ol>
View Heirarchy:
  The View class is an abstract class that serves to define what all Views will need to implement to work with out framework.
  The implemented methods are done as such because we feel they should be immutable across all various view implementations
  The ViewFileMenu class contains instructions for forming a file menu bar for modularity's sake
  

Methods our view Implements:
we want it to be structured in a Border fashion.  Each method will call JComponent makers.
This way the user can decide how the view should be structured.  Certian elements must appear on-screen

API Changes:
Added a stop method in canvas. Is not used but could be seen as useful.
Changed the main method call so that only a new View is instantiated.  This way each view is responsible for its 
own model / control
changed Controller.saveState to take an OutputStream to be much more flexible
changed Controller.loadstate to take an InputStream to be much more flexible
renamed Parser.runInstruction to the more accurate name createRunInstruction (creation not synonymous wihth execution)
removed Parser.loadWorkSpace as its implementation is only needed in the View
removed Parser.saveWorkSpace as its implementation is only needed in the View
added ElapsedTime as a parameter for Model.update.  Necessary addition for successful updating
removed Model.addLine as this was not the appropriate place for this functionality 
removed Model.displayMessage as this feature only needed to be implemented in the View
added Model.setEnvironment(Environment environment) to allow model specific environments
added addInstruction(String keywokrd, BaseInstruction instruction) to facilitate queues of instructions
added parameter ElapsedTime to Turtle.update  was necessary for its feature
added Location startingLocation to Turtle.java.  Used to reset it back to home location
added Location getLocationOnCanvas() vital for calculating angles/ distances from locations
added View.setCanvas() to allow the view to make a canvas that can subsequently make the model and controller

Known Errors: 
  The current implementation of the New File Menu option is completely developed. If the parent
  Workspace that openes a new window is closed, all of its child windows will be termindated
  There are several TODO:'s that have yet to be implemented.
    Some of which have had intermediate implementations put in place, but the TODO:'s remain for the reminder that we need to fix them
