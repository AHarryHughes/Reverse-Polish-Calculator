package com.aharryhughes;


import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class ReversePolishCalc {

    // You'll need a variable here to keep track of the top of the stack

    // The array of the input string split up
    private String[] tokens;

    // The stack
    private String[] stack;

    public double calculate(String input) {

        // 1. Use the String split method to split the string into tokens at the commas
        tokens = input.split(",");

        // 2. Allocate a stack as big as the number of tokens
        stack = new String[input.length()];

        // 3. write the algorithm
        for(int i = 0; i < tokens.length; ++i) {
            // calls to push() and pop() and do the math here
            if(tokens[i].equals("+") || tokens[i].equals("-") || tokens[i].equals("/") || tokens[i].equals("*")){
                ScriptEngineManager mgr = new ScriptEngineManager();
                ScriptEngine engine = mgr.getEngineByName("JavaScript");
                String firstPop = ((Double)this.pop()).toString();
                String secondPop = ((Double)this.pop()).toString();
                String equation = secondPop+tokens[i]+firstPop;
                try {
                    this.push((Double) engine.eval(equation));
                } catch (ScriptException e) {
                    e.printStackTrace();
                }
            }
            else{
                this.push(tokens[i]);
            }
        }

        // 4. return the result
        return pop();
    }

    private void push(String number) {
        // push on the stack
        System.out.println("pushing: "+number);
        for(int i = 0; i < 3; i++){
            if(stack[i] == null){
                stack[i] = number;
                System.out.println("2: "+stack[2]+" | 1: "+stack[1]+" | 0: "+stack[0]);
                break;
            }
        }
    }

    private void push(double d) {
        // change the double to a string and then push it on the stack
        System.out.println("pushing: "+d);
        for(int i = 0; i < 3; i++){
            if(stack[i] == null){
                stack[i] = ((Double)d).toString();
                System.out.println("2: "+stack[2]+" | 1: "+stack[1]+" | 0: "+stack[0]);
                break;
            }
        }
    }

    private double pop() {
        // remove the string from the top of the stack and convert it to a double and return it
        for(int i = 2; i >= 0; i--){
            if(stack[i] != null){
                System.out.println("popping: "+stack[i]);
                double temporary = Double.parseDouble(stack[i]);
                stack[i] = null;
                System.out.println("2: "+stack[2]+" | 1: "+stack[1]+" | 0: "+stack[0]);
                return temporary;
            }
        }
        return -.1;
    }
}
