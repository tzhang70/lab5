package lab5_2019;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        int programCounter = 0;
        List<String> program = new ArrayList<String>();
        Stack<String> stack = new Stack<String>(); // TODO string probably not best

        program.add("GOTO start<<1>>");
        program.add("LABEL Read");
        program.add("LINE -1");
        program.add("FUNCTION Read -1 -1");
        program.add("READ");
        program.add("RETURN ");
        program.add("LABEL Write");
        program.add("LINE -1");
        program.add("FUNCTION Write -1 -1");
        program.add("FORMAL dummyFormal 0");
        program.add("LOAD 0 dummyFormal");
        program.add("WRITE");
        program.add("RETURN ");
        program.add("LABEL start<<1>>");
        program.add("LINE 1");
        program.add("FUNCTION main 1 4");
        program.add("LIT 0 i");
        program.add("LIT 0 j");
        program.add("LINE 2");
        program.add("LOAD 0 i");
        program.add("LOAD 1 j");
        program.add("BOP +");
        program.add("LIT 7");
        program.add("BOP +");
        program.add("STORE 0 i");
        program.add("LINE 3");
        program.add("LOAD 0 i");
        program.add("ARGS 1");
        program.add("CALL Write");
        program.add("STORE 1 j");
        program.add("POP 2");
        program.add("HALT");

        while(!program.get(programCounter).equals("HALT")){
            System.out.println(program.get(programCounter));
            programCounter++;
        }

        // TODO run the program and print every stack after each step
    }
}
