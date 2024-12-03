//Фомина Мария Игоревна
//Л
/*Условие: Для выполнения заданий использовать регулярные выражения.
Каждое задание реализовать в отдельном методе.
Строку получать из текстового файла input.txt.
Результат работы методов записывать в выходной текстовый файл output.txt.

    1. Из заданной строки исключить символы, расположенные внутри круглых скобок. Сами скобки тоже должны быть исключены.

    2. Из заданной строки удалить из каждой группы идущих подряд цифр, в которой более двух цифр, все цифры, начиная с третьей.

    3. Из заданной строки удалить из каждой группы идущих подряд цифр все незначащие нули.
Идея решения: Были созданы три фиункции, каждая из которых изменяет строку соответственно собственному регулярному выражению.
Для удаления были использованы функции класса Matcher: ReplaceALL, appendReplacement, append Tail
Для наглядности результат работы каждой функции выводится в отдельную строку
Пример:
*input.txt: My name 2205 is (Masha) 15 Nastya (Ilya) 33333333333 0324 00513;
output.txt:
Строка без символов в скобках и самих скобок:
My name 2205 is  15 Nastya  33333333333 0324 00513;
Строка без с удаленными цифрами(начиная с третьей)
My name 22 is (Masha) 15 Nastya (Ilya) 33 03 00;
Строка без незначащих нулей:
My name 2205 is (Masha) 15 Nastya (Ilya) 33333333333 324 513;
*
* */


import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static String Parentheses(String line)
    {
        String result="";
        Pattern pattern= Pattern.compile("\\((.*?)\\)");
        Matcher matcher= pattern.matcher(line);
        result=matcher.replaceAll("");
        return result;
    }
    public static String Numbers(String line)
    {
        Pattern pattern = Pattern.compile("(\\d{2})(\\d{1,})");
        Matcher matcher = pattern.matcher(line);
        StringBuffer result = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(result, matcher.group(1));
        }
        matcher.appendTail(result); // Добавляем оставшуюся часть строки

        return result.toString();
    }
    public static String Nulls(String line)
    {
        Pattern pattern = Pattern.compile("(0{0,})(\\d{1,})");
        Matcher matcher = pattern.matcher(line);
        StringBuffer result = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(result, matcher.group(2));
        }
        matcher.appendTail(result); // Добавляем оставшуюся часть строки

        return result.toString();
    }
    public static void main(String[] args) {


        try(FileReader reader= new FileReader("input.txt"))
        {
            String line;
            Scanner scanner= new Scanner(reader);
            line=scanner.nextLine();
            System.out.print(line);
            String string1, string2, string3;
            string1=Parentheses(line);
            string2=Numbers(line);
            string3=Nulls(line);
            try(FileWriter writer=new FileWriter("output.txt"))
            {
                writer.write("Строка без символов в скобках и самих скобок:\n");
                writer.write(string1);
                writer.write("\n");
                writer.write("Строка без с удаленными цифрами(начиная с третьей)\n");
                writer.write(string2);
                writer.write("\n");
                writer.write("Строка без незначащих нулей:\n");
                writer.write(string3);
                writer.write("\n");

            }
        }
        catch(IOException ex)
        {
            System.out.print(ex.getMessage());
        }

    }
    }