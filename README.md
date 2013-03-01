SLogo

View Team:
Sean: 7.3 Hours spent Mon Feb 25, Tuesday: 2.9
Yoshi: 5.2 Hours spent Mon Feb 25, Tuesday: 2.9

Turtle picture: http://sweetclipart.com/cute-colorable-turtle-697

How to add a new instruction:
1. Write class that extends Instruction (probably want to extend BaseInstruction): i.e. Class Loop extends BaseInstruction
2. add the ClassPath to instruction_index.txt in the resources package: i.e. instructions.Loop
3. add keywords to each of the properties files 
	example for keywords "loop" and "loopdeloop":
	
	Loop = loop,loopdeloop
	
4. Run and use your new instruction!