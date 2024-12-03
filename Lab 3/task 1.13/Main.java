//Фомина Мария Игоревна
/*
* Условие: 13.	Задана строка  из слов.
* Слова в исходной строке разделяются некоторым множеством разделителей.
* Переставить в ней слова, состоящие только из цифр, так, чтобы они были упорядочены
*  по возрастанию суммы их цифр. При этом другие слова должны оставаться на месте.
* Слова в новой строке должны разделяться ровно одним пробелом
* Идея решения: Разделяем строку на слова, проверяем является ли слово числом.
*  Если слово число, то добавляем его в один массив, а его сумму с другой массив.
* Сортируем данные массивы. Слова так же собираем в массив.
* Из полученных массивов получаем готовую строку
* Тесты:
* №1
* Enter a string
!ddj:345:666:1000,rew.Masha
New string:  1000 345 666 ddj rew Masha
* №2
* Enter a string
Hello6, Maria;55;1234;2eee
New string:  55 1234 Hello6 Maria 2eee*/
import java.util.Scanner;
import java.util.Vector;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);
        String line;
        System.out.println("Enter a string");
        line=scanner.nextLine();
        StringTokenizer str=new StringTokenizer(line," .,?!:;");
        Vector<Integer> sums=new Vector<>();
        Vector<Integer> numbers=new Vector<>();
        Vector<String> words=new Vector<>();
        while(str.hasMoreTokens())
        {
            String token = str.nextToken();
            if(isNum(token))
            {
                int number = Integer.parseInt(token);
                int sum=SumOfNum(number);
                sums.add(sum);
                numbers.add(number);
            }
            else
            {
                words.add(token);
            }
        }
        Sort(sums,numbers);
        String Result="";
        for(int i=0; i<numbers.size();i++)
        {
            Result=Result+" "+Integer.toString(numbers.get(i));
        }
        for(int i=0;i<words.size();i++)
        {
            Result=Result+" "+words.get(i);
        }
        System.out.print("New string: ");
        System.out.println(Result);

    }
    static void Sort(Vector<Integer> arr1, Vector<Integer> arr2) {
        int n = arr1.size();
        boolean flag;
        for (int i = 0; i < n - 1; i++) {
            flag = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr1.get(j) > arr1.get(j + 1)) {
                    int temp = arr1.get(j);
                    arr1.set(j, arr1.get(j + 1));
                    arr1.set(j + 1, temp);
                    temp = arr2.get(j);
                    arr2.set(j, arr2.get(j + 1));
                    arr2.set(j + 1, temp);
                    flag = true;
                }
            }
            if (!flag) break;
        }
    }
    public static boolean isNum(String str)
    {
        Scanner scanner_n=new Scanner(str);
        boolean isNumeric = scanner_n.hasNextInt();
        scanner_n.close();
        return isNumeric;
    }
    public static int SumOfNum(int number)
    {
        int sum=0;
        while(number!=0)
        {
            sum+=number%10;
            number=number/10;
        }
        return sum;
    }
}