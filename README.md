SLogo

<b>Team 2:<b>
<ul>
<li> Ryan Fishel (ref13@duke.edu) 
<li> Ellango Jothimurugesan (ej48@duke.edu) 
<li> Scott Valentine (sdv4@duke.edu)
<li> Sean Wareham (srw22@duke.edu) 
<li> Elder Yoshida (emy2@duke.edu)
<li> Mentor: Volodymyr Zavidovych (vz4@duke.edu)
<li> Repository: https://github.com/srwareham/SLogo
</ul>
<b>Start date:</b> February 15, 2013

<hr>

<b>View Team:</b>
<ul>
<li>Sean: 7.3 Hours spent Mon Feb 25, Tuesday: 2.9, Monday 4 <br>
<li>Yoshi: 5.2 Hours spent Mon Feb 25, Tuesday: 2.9, Saturday: 3.5h, Sunday 2h, Monday 4<br>
</ul>
<b>Model/Controller Team:</b>
<br>
<ul>
<li>Ellango:
<li>Ryan: 1.5 hours Sunday 2/24, 4 hours Monday 2/25, 3 hours Tuesday 2/26, 
1 hour Wednesday 2/27, 3 hours Thursday 2/28, 4 hours Saturday 3/2,
6.5 hours Sunday 3/3, 2 hours Monday 3/4, 2 hour Thursday 3/7
<li>Scott: 
</ul>
<hr>
<b>Resources</b>
<br>
Turtle picture: http://sweetclipart.com/cute-colorable-turtle-697
<hr>

<b>How to add a new instruction:</b>

How to add a new instruction:
<ol>
<li> Write class that extends BaseInstruction: i.e. Class Loop extends BaseInstruction
<li> Add the ClassPath to instruction_index.txt in the resources package: i.e. instructions.Loop
<li> Add keywords to each of the properties files 
<br>  example for keywords "loop" and "loopdeloop":	
<br>	Loop = loop,loopdeloop	
<br>
<li> Run and use your new instruction
</ol>

<b>View Hierarchy:</b>
  The View class is an abstract class that serves to define what all Views will need to implement to work with out framework.
  The implemented methods are done as such because we feel they should be immutable across all various view implementations
  The ViewFileMenu class contains instructions for forming a file menu bar for modularity's sake
  <hr>
  
<b>Methods our view Implements:</b>
we want it to be structured in a Border fashion.  Each method will call JComponent makers.
This way the user can decide how the view should be structured.  Certain elements must appear on-screen
<hr>

<b>API Changes:</b>
Added a stop method in canvas. Is not used but could be seen as useful.
Changed the main method call so that only a new View is instantiated.  This way each view is responsible for its 
own model / control
changed Controller.saveState to take an OutputStream to be much more flexible
changed Controller.loadstate to take an InputStream to be much more flexible
renamed Parser.runInstruction to the more accurate name createRunInstruction (creation not synonymous with execution)
removed Parser.loadWorkSpace as its implementation is only needed in the View
removed Parser.saveWorkSpace as its implementation is only needed in the View
added ElapsedTime as a parameter for Model.update.  Necessary addition for successful updating
removed Model.addLine as this was not the appropriate place for this functionality 
removed Model.displayMessage as this feature only needed to be implemented in the View
added Model.setEnvironment(Environment environment) to allow model specific environments
added addInstruction(String keyword, BaseInstruction instruction) to facilitate queues of instructions
added parameter ElapsedTime to Turtle.update  was necessary for its feature
added Location startingLocation to Turtle.java.  Used to reset it back to home location
added Location getLocationOnCanvas() vital for calculating angles/ distances from locations
added View.setCanvas() to allow the view to make a canvas that can subsequently make the model and controller
added View.getResources() so that all classes can access the language versioning
<hr>

<b>Known Errors:</b>
  The current implementation of the New File Menu option is completely developed. If the parent
  Workspace that opens a new window is closed, all of its child windows will be terminated
  There are several TODO:'s that have yet to be implemented.
    Some of which have had intermediate implementations put in place, but the TODO:'s remain for the reminder that we need to fix them

<hr>
<b>Explanations:</b>
<ul>
	<li> Constant Instruction:<br>
	This class makes instructions that are just constants.  When we parse, everything has a list of instructions
	so we need to make our constants instructions in order to parse them.
	<li>Copy: <br>
	<li>nextOperand loops: <br>
	<li>BaseInstruction.ListIterator: <br>
	<li>BaseInstruction.getNumberOfArguments:<br>
	This gets the number of arguments for a certain instruction.  This is used so that we know
	 how many arguments to read given the instruction that we just read when we are parsing.
	Turtle.recursiveLineCreation: This solves the issue of how to draw lines when the turtle wraps around the screen.
	InstructionMapFactory: This class loads all of the instructions into the map in the Environment using reflection.
</ul>	
