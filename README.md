SLogo
<hr>
View Team:<br>
Sean: 7.3 Hours spent Mon Feb 25, Tuesday: 2.9 <br>
Yoshi: 5.2 Hours spent Mon Feb 25, Tuesday: 2.9, Saturday: 3.5h<br>
<br>
Turtle picture: http://sweetclipart.com/cute-colorable-turtle-697


How to add a new instruction:
<br>1. Write class that extends Instruction (probably want to extend BaseInstruction): i.e. Class Loop extends BaseInstruction
<br>2. add the ClassPath to instruction_index.txt in the resources package: i.e. instructions.Loop
<br>3. add keywords to each of the properties files 
<br>	example for keywords "loop" and "loopdeloop":
<br>	
<br>	Loop = loop,loopdeloop
<br>	
<br> 4. Run and use your new instruction


Methods all views should implement:
makeFileMenu()
makeConsole()
makeHistory()

Methods our view Implements:
we want it to be structured in a Border fashion.  Each method will call JComponent makers.
This way the user can decide how the view should be structured.  Certian elements must appear on-screen

