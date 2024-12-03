//Фомина Мария Игоревна
//Лабораторная работа 42
/*Условие: Входной файл input1.html содержит текст, написанный на языке HTML.
В тесте находятся теги. В одной строке может быть несколько тегов. Теги находятся в угловых скобках, каждому открывающему тегу ставится в соответствие закрывающий тег. Например, пара тегов<b></b>.
Между тегами находится текст, причем теги не разрывают текст. Например, при поиске слова hello комбинация h<b><i>el</i>l</b>o должна быть найдена.
Гарантируется,что страница HTML является корректной, т.е. все символы "<" и ">" используются только в тегах, все теги записаны корректно.
Входной файл input2.in содержит список фрагментов текста, которые нужно найти в первом файле, записанных через разделители (точка с запятой). Может быть несколько строк.

Примечание: Ваша программа должна игнорировать различие между строчными и прописными буквами и для тегов и для искомого контекста.

Выходные данные:
1. В выходной файл output1.out вывести список всех тегов в порядке возрастания количества символов тега.
2. В выходной файл output2.out вывести номера строк (нумерация с 0) первого файла, в которых был найден искомый контекст в первый раз или -1 , если не был найден.
3. В выходной файл output3.out - список фрагментов второго файла, которые НЕ были найдены в первом файле.
*/
/*Идея алгоритма: Считываем файл html построчно в вектор,
 а слова из input2.in также в массив. С помощью регулярного выражения находим тэги и сортируем.
 Далее удаляем ихдля удобной работы в дальнейшем.
Стравнимаем строки файлов. Нужные данные записываем в ответ */
/*
* Тесты:
* input1
* <html>
<head>
  <title>HTMl страница cat</title>
</head>
<body>
<p align="center">
  <img src="../images/audi.jpg" width="250" height="221" alt="" />
</p>
My name is Maria
<div style="text-align:center">
  <img src="../images/mustang.jpg" width="250" height="153" alt="" /> lalalal
</div>
Red
</body>
</html>
* input 2
* cat; meat; red; pett;
* output1.out
* </p> <html> <head> <body> </div> <title> </head> </body> </html> </title> <p align="center"> <div style="text-align:center"> <img src="../images/audi.jpg" width="250" height="221" alt="" /> <img src="../images/mustang.jpg" width="250" height="153" alt="" />
 * output2.out
* 2 12
 * output3.out
 * meat pett */
import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class Main {
    public static void readFile(String Path, Vector<String> lines) {
        try {
            File file = new File(Path);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.err.println("Файл не найден: " + e.getMessage());
        }
    }
    public static void readWords(String Path, Vector<String> lines)
    {
        try {

            Scanner scanner = new Scanner(new File(Path));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] words = line.split(";");
                for (String word : words) {
                    lines.add(word.trim()); // Удаляем лишние пробелы
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static String[] find_tags(Vector<String> html_lines)
    {
        Vector<String> tags=new Vector<>();
        Pattern pattern=Pattern.compile("<(.*?)>");
        for(String line:html_lines) {
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                tags.add(matcher.group());
            }
        }
        String[] tagsArray = new String[tags.size()];
        tags.toArray(tagsArray);
        Arrays.sort(tagsArray, Comparator.comparingInt(String::length));
        return tagsArray;
    }
    public static Vector<String> removeTags(Vector<String> html_lines)
    {
        Vector<String> new_str=new Vector<>();
        Pattern pattern=Pattern.compile("<(.*?)>");
        for(String str: html_lines)
        {
            Matcher matcher= pattern.matcher(str);
            str=matcher.replaceAll("");
            new_str.add(str);
        }
        return new_str;
    }
    public static Vector<Integer> find_words(Vector<String> in_lines, Vector<String> new_str)
    {
        Vector<Integer> numbers=new Vector<>();
        boolean Flag=false;
        for(int i=0;i<in_lines.size();i++)
        {
            for(int j=0;j<new_str.size();j++)
            {
                if(new_str.get(j).contains(in_lines.get(i)))
                {
                    Flag=true;
                    numbers.add(j);
                }
            }
        }
        if(numbers.size()==0)
        {
            numbers.add(-1);
        }
        return numbers;
    }
    public static Vector<String> find_missing_words(Vector<String> in_lines, Vector<String> new_str)
    {
        Vector<String> miss_words=new Vector<>();
        boolean Flag=false;
        for(int i=0;i<in_lines.size();i++)
        {
            Flag=false;
            for(int j=0;j<new_str.size();j++)
            {
                if(new_str.get(j).contains(in_lines.get(i)))
                {
                    Flag=true;
                }
            }
            if(Flag==false)
            {
                miss_words.add(in_lines.get(i));
            }
        }

        return miss_words;
    }
    public static void writeToFile(String fileName, Vector<String> context) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for(String line: context){
                writer.write(line);
                writer.write(" ");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Данные успешно записаны в " + fileName);
    }
    public static void writeInt(String fileName, Vector<Integer> context) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for(Integer num: context){
                writer.write(Integer.toString(num));
                writer.write(" ");
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Данные успешно записаны в " + fileName);
    }
    public static void main(String[] args) {
        Vector<String> html_lines=new Vector<>();
        Vector<String> in_lines=new Vector<>();
        readFile("input1.html", html_lines);
        readWords("input2.in",in_lines);
        System.out.println("Содержимое HTML-файла:");
        for (String line : html_lines) {
            System.out.println(line);
        }
        String[] tags=find_tags(html_lines);
        for (int i = 0; i < tags.length; i++) {
            tags[i] = tags[i].toLowerCase();
        }
        //output1
        System.out.println("New: ");
        for(String t:tags)
        {
            System.out.print(t);
        }
        System.out.println(" ");
        Vector<String> new_str=removeTags(html_lines);
        for (int i = 0; i < new_str.size(); i++) {
            new_str.set(i,new_str.get(i).toLowerCase());
        }
        writeToFile("output1.out",new Vector<>(Arrays.asList(tags)));

        //output2
        Vector<Integer> numbers=find_words(in_lines, new_str);
        for(int n:numbers)
        {
            System.out.println(n);
        }
        writeInt("output2.out",numbers);

        //output3
        Vector<String> miss_word=find_missing_words(in_lines,new_str);
        for(String n:miss_word)
        {
            System.out.println(n);
        }
        writeToFile("output3.out",miss_word);
        // Доступ к строкам из .in файла
        System.out.println("\nСодержимое .in файла:");
        for (String line : in_lines) {
            System.out.println(line);
        }
    }
}