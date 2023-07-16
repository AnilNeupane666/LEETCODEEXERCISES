package Stack.src;

import java.util.Stack;

public class ValidParentheses {
    public static void main(String[] args) {
        isValid("()");
        isValid("()[]{}");
}

    private static boolean isValid(String s) {  
        if (s.length()%2 != 0){
            return false;
        }
        Stack<Character> stack = new Stack<Character>();
        for ( char character : s.toCharArray()){
            if ( character == '('){
                stack.push(')');
            } else if ( character == '['){
                stack.push(']');
            } else if ( character == '{'){
                stack.push('}');
            } else if ( stack.isEmpty() || stack.pop() != character){
                return false;
            }
        }
        return stack.isEmpty();
    }
    //The basic idea is to push the right parentheses ')', ']', or '}' into the stack each time when we encounter left ones.
    // And if a right bracket appears in the string, we need check if the stack is empty and also whether the top element is the same with that right bracket. 
    // If not, the string is not a valid one. At last, we also need check if the stack is empty.
}