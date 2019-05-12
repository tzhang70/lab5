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


        //run the program and print every stack after each step

        while(!program.get(programCounter).equals("HALT")){
            String str = program.get(programCounter);
            boolean stackChanged =false;

            String[] temp = str.split(" ");
            switch (temp[0]){
                case "GOTO" :
                    while(!(program.get(programCounter+1).contains(temp[1])))
                    {
                        programCounter++;
                    }
                    stackChanged=true;
                    break;

                case "LIT" :
                    if(temp.length == 3 )
                    {
                        stack.push(temp[1] + "" + temp[2]);
                    }
                    if(temp.length ==2 ) 
                    {
                        stack.push(temp[1]);
                    }
                    programCounter++;
                    stackChanged=true;
                    break;

                case "LOAD" :
                    String toLoad = "";
                    for(String i:stack)
                    {
                        if(i.contains(temp[2]))
                        {
                            toLoad = i.substring(0,1);
                        }
                    }
                    if(!(toLoad.equals("")))
                    {
                        stack.push(toLoad);
                    }
                    programCounter++;
                    stackChanged=true;
                    break;

                case "STORE" :
                    String toStore = "";
                    int whereToStore = 0;
                    for(int i=0; i<stack.size(); i++)
                    {
                        if(stack.get(i).contains(temp[2]))
                        {
                            toStore = stack.get(stack.size()-1) +""+temp[2];
                            whereToStore = i;
                        }
                    }
                    if(!(toStore.equals("")))
                    {
                        stack.set(whereToStore,toStore);
                        stack.pop();
                    }
                    programCounter++;
                    stackChanged=true;
                    break;

                case "BOP" :
                    int one = Integer.parseInt(stack.get(stack.size()-1).substring(0,1));
                    int two = Integer.parseInt(stack.get(stack.size()-2).substring(0,1));
                    int three = one + two;
                    stack.pop();
                    stack.pop();
                    stack.push(Integer.toString(three));

                    programCounter++;
                    stackChanged=true;
                    break;

                case "POP" :
                    for(int i=0;i<Integer.parseInt(temp[1]);i++)
                    {
                        stack.pop();
                    }
                    programCounter++;
                    stackChanged=true;
                    break;

                case "CALL" :
                    if(temp[1].equals("Write"))
                    {
                        System.out.println(stack.toString() + " ~From \"CALL Write\"");
                    }
                    programCounter++;
                    stackChanged=false;
                    break;

                default:
                    programCounter++;
                    stackChanged=false;
                    break;
            }

            if(stackChanged) 
            {
                System.out.println(stack.toString());
            }
        }

    }

}
