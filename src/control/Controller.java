package control;

import java.io.File;
import java.io.FileWriter;

import simulation.Model;

public class Controller {
	
	Model myModel;
	View myView;
	
	public Controller(Model model, View view){
		myModel = model;
		myView = view;
	}
	
	public Instruction sendString(String s){
		return Parser.generateInstruction(s);
	}
	public void saveState(FileWriter fw){
		
	}
	public void loadState(File f){
		
	}
	
	public void clear(){
		
	}
	
	public void runInstruction(Instruction instr, Model myModel){
		//myModel.getTurtle.perfromAction(instr);
	}
	
}
