SLogo

View Team:
Sean: 7.3 Hours spent Mon Feb 25, Tuesday: 2.9
Yoshi: 5.2 Hours spent Mon Feb 25, Tuesday: 2.9

Turtle picture: http://sweetclipart.com/cute-colorable-turtle-697

<<<<<<< HEAD
How to add a new instruction:
1. Write class that extends Instruction (probably want to extend BaseInstruction): i.e. Class Loop extends BaseInstruction
2. add the ClassPath to instruction_index.txt in the resources package: i.e. instructions.Loop
3. add keywords to each of the properties files 
	example for keywords "loop" and "loopdeloop":
	
	Loop = loop,loopdeloop
	
4. Run and use your new instruction!
=======

Methods all views should implement:
makeFileMenu()
makeConsole()
makeHistory()

Methods our view Implements:
we want it to be structured in a Border fashion.  Each method will call JComponent makers.
This way the user can decide how the view should be structured.  Certian elements must appear on-screen
>>>>>>> 4cb52f1547a47f0295d5e3fae6a46dfa3d9191ce
