package control;

import instructions.IExecutable;
import instructions.Instruction;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

import simulation.Model;
import view.View;

public class Controller {

	Model myModel;
	View myView;

	public Controller(Model model, View view) {
		myModel = model;
		myView = view;
	}

	public IExecutable sendString(String s) {
		return Parser.generateInstruction(s);
	}

	public void saveState(FileWriter fw){
		try{
			BufferedWriter out = new BufferedWriter(fw);
			// TODO: write the variables that need to be saved
		}
		catch(Exception e){
			// print error
		}
	}

	public void loadState(File f) {
		try {
			Scanner input = new Scanner(f);
			while (input.hasNext()) {
				Scanner line = new Scanner(input.nextLine());
				// TODO: create variables
			}

			input.close();
		} catch (FileNotFoundException e) {
			// should not happen because File came from user selection
			e.printStackTrace();
		}

	}

	public void clear() {

	}

	public void runInstruction(IExecutable instr, Model myModel) {
		// myModel.getTurtle.perfromAction(instr);
	}

}
