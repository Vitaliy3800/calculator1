import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Calculator {
    public static void main(String[] args) throws Exception {

        Scanner s = new Scanner(System.in);
        System.out.println("Введите выражение: ");
        String expression = s.nextLine();
        System.out.println(parse(expression));
    }

    public static String parse(String expression) throws Exception {
        int a;
        int b;
        String op;
        String result;
        boolean roman;
        String[] operands = expression.split("[+ \\- / *]");
        if(operands.length != 2) {
            throw new Exception("Формат математической операции не удовлетворяет заданию - два операнда и один оператор");
        }
        op = operation(expression);

        if(Roman.roman(operands[0]) && Roman.roman(operands[1])){
            a = Roman.convertArabian(operands[0]);
            b = Roman.convertArabian(operands[1]);
            roman = true;
        } else if(!Roman.roman(operands[0]) && !Roman.roman(operands[1])) {
            a = Integer.parseInt(operands[0]);
            b = Integer.parseInt(operands[1]);
            roman = false;
        } else{
            throw new Exception("Числа должны быть в одном формате");
        }
        if(a > 10 || b > 10){
            throw new Exception("Числа должны быть от 1 до 10");
        }
        int arabian = calc(a, b, op);
        if(roman){
            if(arabian <= 0){
                throw new Exception("Римское число должно быть больше 0");
            }
            result = Roman.convertRoman(arabian);
        } else{
            result = String.valueOf(arabian);
        }
        return result;

    }

    static String operation(String expression){
        if(expression.contains("+")){
            return "+";
        } else if(expression.contains("-")){
            return "-";
        } else if(expression.contains("*")){
            return "*";
        }else if(expression.contains("/")){
            return "/";
        }else return null;
    }

    static int calc(int a, int b, String op){
        if(op.equals("+")){
            return a + b;
        } else if(op.equals("-")){
            return  a - b;
        } else if(op.equals("*")){
            return a * b;
        } else return a / b;
    }

    class Roman{
        static String[] array = new String[]{"0","I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII",
                "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII",
                "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI", "XLII",
                "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII",
                "LIX", "LX", "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII",
                "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI",
                "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"};

        public static int convertArabian(String roman){
            for(int i = 0; i < array.length; i++ ){
                if(roman.equals(array[i])){
                    return i;
                }
            }
            return -1;
        }

        public static String convertRoman(int arabian){
            return array[arabian];
        }

        public static boolean roman(String value){
            for(int i = 0; i < array.length; i++){
                if(value.equals(array[i])){
                    return true;
                }
            }
            return false;
        }

    }
}