import java.util.Scanner;

public class Calculator
{
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите выражение в арабских или римских числах: ");
        String input = scanner.nextLine();
        System.out.println(calc(input));
    }

    public static String calc(String input) throws Exception {
        int num1;
        int num2;
        String operation;
        String result;
        boolean isRoman;

        String[] operands = input.split("[+\\-*/]");//делим строку на символы по знаку

        if (operands.length != 2) throw new Exception();//если пользователь ввел больше 2 чисел, выдаем ошибку
        {
            operation = findOperation(input);//вызываем метод для определения действия
        }

        if (operation == null)
        {
            throw new Exception(); //если пользовательввел недопустимые знаки, выдаём ошибку
        }

        if (Roman.isRoman(operands[0]) && Roman.isRoman(operands[1]))//если оба числа римские
        {
            num1 = Roman.converterToArabic(operands[0]);//конвертируем первое число в арабское
            num2 = Roman.converterToArabic(operands[1]);//конвертируем второе число в арабское
            isRoman = true;
        } else if (!Roman.isRoman(operands[0]) && !Roman.isRoman(operands[1]))//если оба числа арабские
        {
            num1 = Integer.parseInt(operands[0]);//конвертируем первое число из строки в число (int)
            num2 = Integer.parseInt(operands[1]);//конвертируем второе число из строки в число (int)
            isRoman = false;
        } else//если одно римское, а другое арабское
        {
            throw new Exception();//выдаём ошибку
        }

        if (num1 > 10 || num2 > 10)//если какое-либо из чисел больше 10
        {
            throw new Exception();//выдаём ошибку
        }
        int arabic = calc(num1, num2, operation);
        if (isRoman)
        {
            if (arabic <= 0) //если римское число получилось меньше либо равно нулю
            {
                throw new Exception();//выдаём ошибку
            }
            result = Roman.converterToRoman(arabic); //конвертируем результат в римские цифры
        } else
        {
            result = String.valueOf(arabic);//Конвертируем арабское число в тип String
        }
        return result;
    }

    static String findOperation(String input)
    {
        if (input.contains("+")) return "+";
        else if (input.contains("-")) return "-";
        else if (input.contains("*")) return "*";
        else if (input.contains("/")) return "/";
        else return null;
    }

    static int calc(int a, int b, String operation)
    {

        return switch (operation) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            default -> a / b;
        };
    }

    static class Roman
    {
        static String[] romanNumbers = new String[]{"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI",
                "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV",
                "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI",
                "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII",
                "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII",
                "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV",
                "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV",
                "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII",
                "XCVIII", "XCIX", "C"};

        public static boolean isRoman(String val)
        {
            for (int i = 0; i < romanNumbers.length; i++)
            {
                if (val.equals(romanNumbers[i]))
                {
                    return true;
                }
            }
            return false;
        }
        public static int converterToArabic(String roman)
        {
            for (int i = 0; i < romanNumbers.length; i++)
            {
                if (roman.equals(romanNumbers[i]))
                {
                    return i;
                }
            }
            return -1;
        }

            public static String converterToRoman(int arabic)
            {
                return romanNumbers[arabic];
            }
    }
}