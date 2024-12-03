//Фомина Мария Игоревна
/*Усовие: 13.	В текстовом файле input.txt в первой строке записаны два слова,
разделенные пробелами, в остальных строках  - текст, слова в котором разделены знаками препинания.
Нужно найти эти слова в тексте и поменять местами строки, содержащие эти слова.
Если таких слов несколько – брать первые.
 Идея решения: Считываем с файла слова и затем создаем массив строк.
 Далее просматриваем все строки и запоминаем индекс строки,
 в котором впервый раз встречается одно из заданных слов.
 Для каждого слова своя переменная с индексом.
 Когда все строки просмотрены, смотрим значение индексов и если они различные и не -1,
 то меняем их местами. Записываем в новый файлю */
//
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String inputFile = "input.txt";
        StringBuilder firstWord = new StringBuilder();
        StringBuilder secondWord = new StringBuilder();
        StringBuffer[] lines = new StringBuffer[100];
        int count = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String first = reader.readLine();
            String[] words = first.split(" ");
            firstWord.append(words[0]);
            secondWord.append(words[1]);
            String line;
            while ((line = reader.readLine()) != null) {
                lines[count++] = new StringBuffer(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int index1 = -1;
        int index2 = -1;

        for (int i = 0; i <count; i++) {
            if (lines[i].toString().contains(firstWord)) {
                index1= i;
            }
            if (lines[i].toString().contains(secondWord)) {
                index2 = i;
            }
            if (index1 != -1 && index2!= -1) {
                break;
            }
        }

        if ((index1 != -1 && index2 != -1)||(index1!=index2)) {
            StringBuffer temp = lines[index1];
            lines[index1] = lines[index2];
            lines[index2] = temp;
        }
        if(index1==index2)
        {
            System.out.println("In the same string");
        }
        String outputFile="output.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            writer.newLine();
            for (int i = 0; i < count; i++) {
                writer.write(lines[i].toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}