package com.itxie.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class PolishNotation {
    public static void main(String[] args) {

        /**
         * 完成将一个中缀表达式转成后缀表达式的功能
         * 说明：
         * 1.1+((2+3)*4)-5 ==> 1 2 3 + 4 * + 5 -
         * 2.因为直接对str操作，不方便，因此先将“1+((2+3)*4)-5” ==》中缀表达式对应的list
         * 即“1+((2+3)*4)-5”=> ArrayList[1,+,(,(,2,+,3,),*,4,),-,5]
         */

        String expression = "1+((2+3)*4)-5";
        List<String> infixExpressionList = toInfixExpressionList(expression);//[1, +, (, (, 2, +, 3, ), *, 4, ), -, 5]
        System.out.println("中缀表达式对应的List：" + infixExpressionList);
        List<String> parseSuffixExpressionList = parseSuffixExpressionList(infixExpressionList);
        System.out.println("后缀表达式对应的List：" + parseSuffixExpressionList);
        System.out.println("expression = " + calculate(parseSuffixExpressionList));

        /*
        //先定义逆波兰表达式
        //(30+8)*6-6+9/6 ---> 30 8 + 6 * 6 - 9 6 / +
        String suffixExpression = "30 8 + 6 * 6 - 9 6 / +";//223
        //将表达式放进ArrayList

        List<String> list = getListString(suffixExpression);
        System.out.println("List = " + list);

        int res = calculate(list);
        System.out.println("运算结果" + res);
        */
    }

    //即ArrayList[1,+,(,(,2,+,3,),*,4,),-,5] ==》ArrayList [1,2,3,+,4,*,+,5,-]
    //方法：将得到的中缀表达式对应的List=》后缀表达式对应的List
    public static List<String> parseSuffixExpressionList(List<String> ls) {
        //定义两个栈
        Stack<String> s1 = new Stack<>();//符号栈
        //说明：因为 s2 这个栈，在整个转换过程中，没有 pop 操作，而且后面我们还需要逆序输出
        //因此比较麻烦，这里我们就不用 Stack<String> 直接使用 List<String> s2
        //Stack<String> s2 = new Stack<String>(); // 储存中间结果的栈 s2
        List<String> s2 = new ArrayList<String>(); // 储存中间结果的 Lists2

        //遍历ls
        for (String item : ls) {
            //如果是一个数，加入s2
            if (item.matches("\\d+")) {
                s2.add(item);
            } else if (item.equals("(")) {
                s1.push(item);
            } else if (item.equals(")")) {
                //如果是右括号“)”，则依次弹出 s1 栈顶的运算符，并压入 s2，直到遇到左括号为止，此时将这一对括号丢弃
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop();//将(弹出栈
            } else {
                //当 item 的优先级小于等于 s1 栈顶运算符, 将 s1 栈顶的运算符弹出并加入到 s2 中，再次转到(4.1) 与 s1 中新的栈顶运算符相比较
                //问题：我们缺少一个比较优先级高低的方法
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)) {
                    s2.add(s1.pop());
                }
                //还需要item压入栈
                s1.push(item);
            }
        }

        //将s1剩余运算符依次弹出并加入s2
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }
        return s2;//注意因为是存放到 List, 因此按顺序输出就是对应的后缀表达式对应的 List
    }

    //将中缀表达式转成对应的List
    public static List<String> toInfixExpressionList(String s) {
        //定义一个List，存放中缀表达式对应的内容
        List<String> ls = new ArrayList<>();
        int i = 0;//指针，用于遍历中缀表达式字符串
        String str;//对多位数的拼接
        char c;//每遍历一个字符，存到c
        do {
            //如果c是一个非数字，需要加到ls
            if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {
                ls.add("" + c);
                i++;
            } else {
                //如果是一个数，需要考虑多位数
                str = "";
                while (i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57) {
                    str += c;//拼接
                    i++;
                }
                ls.add(str);
            }
        } while (i < s.length());
        return ls;
    }

    //将一个逆波兰表达式，依次将数据和运算符放入到ArrayList中
    public static List<String> getListString(String suffixExpression) {
        //分割表达式
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<>();
        for (String ele : split) {
            list.add(ele);
        }
        return list;
    }

    //完成对逆波兰表达式的计算

    /**
     * 1)从左至右扫描，将 3 和 4 压入堆栈；
     * 2)遇到+运算符，因此弹出 4 和 3（4 为栈顶元素，3 为次顶元素），计算出 3+4 的值，得 7，再将 7 入栈；
     * 3)将 5 入栈；
     * 4)接下来是×运算符，因此弹出 5 和 7，计算出 7×5=35，将 35 入栈；
     * 5)将 6 入栈；
     * 6)最后是-运算符，计算出 35-6 的值，即 29，由此得出最终结果
     */
    public static int calculate(List<String> ls) {
        //创建栈，只需要一个
        Stack<String> stack = new Stack<>();
        //遍历ls
        for (String item : ls) {
            //使用正则表达式取出数
            if (item.matches("\\d+")) {
                //入栈
                stack.push(item);
            } else {
                //pop出两个数，并运算，再入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("-")) {
                    res = num1 - num2;
                } else if (item.equals("*")) {
                    res = num1 * num2;
                } else if (item.equals("/")) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("运算符有误");
                }
                //res入栈
                stack.push("" + res);
            }
        }
        //最后留在stack中数据是运算结果
        return Integer.parseInt(stack.pop());
    }
}

//返回一个运算符对应优先级
class Operation {
    private static final int ADD = 1;
    private static final int SUB = 1;
    private static final int MUL = 2;
    private static final int DIV = 2;

    //返回对应优先级数字
    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("不存在该运算符");
        }
        return result;
    }
}