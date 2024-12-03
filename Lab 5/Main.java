//Фомина Мария Игоревна
//Лабораторная работа №5
/*Постановка задачи
Необходимо прочитать данные, обработать их и записать выходные данные в заданном формате.

Входные данные
Входной файл input содержит данные в формате CSV.
Каждая запись в файле расположена на новой строке.
Разделителем между полями одной записи является символ точка с запятой (';').
Если значения какого-то поля записи нет, то разделить все равно присутствует.
Обязательными для заполнения являются только те поля, по которым строятся запросы для выборки данных.

Формат входных данных
Имеется список компаний.
Каждый элемент списка содержит следующие поля:
   Наименование (name)
    Краткое наименование (shortTitle)
    Дата актуализации (dateUpdate)
    Адрес (address)
    Дата основания (dateFoundation)
    Численность сотрудников (countEmployees)
    Аудитор (auditor)
    Телефон (phone)
    Email (email)
    Отрасль (branch)
    Вид деятельности (activity)
    Адрес страницы в Интернет (internetAddress/link)

Выходные данные
1. Прочитать данные из входного файла.
2. Выбрать данные по запросу.
3. Записать полученные данные в два файла (в XML-формате и JSON).

Запросы
1. Найти компанию по краткому наименованию.
2. Выбрать компании по отрасли.
3. Выбрать компании по виду деятельности.
4. Выбрать компании по дате основания в определенном промежутке (с и по).
5. Выбрать компании по численности сотрудников в определенном промежутке (с и по).

Примечания
1. Ваша программа должна игнорировать различие между строчными и прописными буквами.
2. Необходимо вести статистику работы приложения в файле logfile.txt. Данные должны накапливаться. Формат данных: дата и время запуска приложения; текст запроса.
*/
/*Идея алгоритма: Выполняется поиск по запросу, в файл logfile.txt заносится время начало поимка и информация о том, сколько обьектов по каждому критерию найдено.
 Результаты поиска записываются в файлы с расширением .json .xml,
 также дублируется в консоль. Запрос тоже вводится с консоли*/
/* Консоль(аналогично в .json .xml):
* Список компаний:
Company: flowers with love f_l flowers shop 23.12.20 5
Company: coffe for everyone c_f_e drinks cafe 20.05.18 10
Company: Blue jeans b_l cloth shop 12.03.19 15
Company: dress history d_h cloth museum 23.05.21 20
Введите короткое название компании:
f_l
Результат поиска по короткому имени компании:
Company: flowers with love f_l flowers shop 23.12.20 5
Введите отрасль:
cloth
Результат поиска по отрасли:
Company: Blue jeans b_l cloth shop 12.03.19 15
Company: dress history d_h cloth museum 23.05.21 20
Введите вид деятельности:
shop
Результат поиска по виду деятельности:
Company: flowers with love f_l flowers shop 23.12.20 5
Company: Blue jeans b_l cloth shop 12.03.19 15
Введите дату начала промежутка (в формате dd.MM.yy): 12.12.16
Введите дату конца промежутка (в формате dd.MM.yy): 12.12.20
Результат поиска по дате основания:
Company: coffe for everyone c_f_e drinks cafe 20.05.18 10
Company: Blue jeans b_l cloth shop 12.03.19 15
Введите минимум людей :
10
Введите максимум людей :
16
Результат поиска по количеству людей:
Company: coffe for everyone c_f_e drinks cafe 20.05.18 10
Company: Blue jeans b_l cloth shop 12.03.19 15
* logfile.txt:
* Время запуска: 12.11.24
Найдено: 1 с коротким именем: f_l
Найдено: 2 из отрасли: f_l
Найдено: 2 по виду деятельности: f_l
Найдено: 2 по промежутку даты: [2016-12-12, 2020-12-12]
Найдено: 2 компаний с количеством работников от 10 до 16*/
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;


public class Main {
    public static class Companies
    {
        private String name;
        private String short_name;
        private String branch;
        private String activity;
        private String date_begin;
        private String number_emp;

        public  Companies(String name, String short_name, String branch, String activity, String date_begin, String number_emp)
        {
            this.name=name;
            this.short_name=short_name;
            this.branch=branch;
            this.activity=activity;
            this.date_begin=date_begin;
            this.number_emp=number_emp;
        }
        public String GetN()
        {
            return name;
        }
        public String GetShn()
        {
            return short_name;
        }
        public String GetB()
        {
            return branch;
        }
        public String GetAct()
        {
            return activity;
        }
        public String GetDB()
        {
            return date_begin;
        }
        public String GetNE()
        {
            return number_emp;
        }
        @Override
        public String toString()
        {
            return "Company: "+this.name+" "+this.short_name+" "+this.branch+" "+this.activity+" "+this.date_begin+" "+this.number_emp;
        }
    }
    public static void main(String[] args)
    {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy");
        String formattedNow = now.format(formatter);
        List<Companies> companies = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File("input.csv"));
             Scanner in=new Scanner(System.in);
             FileWriter w_txt=new FileWriter("logfile.txt");
             FileWriter w_json=new FileWriter("result.json");
             FileWriter w_xml=new FileWriter("result.xml")) {
            while (scanner.hasNextLine()) {
                String fields[] = scanner.nextLine().split(";");
                Companies company = new Companies(fields[0], fields[1], fields[2], fields[3], fields[4], fields[5]);
                companies.add(company);
            }
            System.out.println("Список компаний: ");
            for (Companies company : companies) {
                System.out.println(company);
            }
            w_txt.write("Время запуска: "+formattedNow+"\n");
            //Короткое имя
            System.out.println("Введите короткое название компании: ");
            String shortn=in.nextLine().toLowerCase();
            w_json.write("Результат поиска по короткому имени компании:");
            w_json.write(System.lineSeparator());
            w_xml.write("Результат поиска по короткому имени компании:");
            w_xml.write(System.lineSeparator());
            System.out.println("Результат поиска по короткому имени компании:");
            int count=0;
            for(Companies c:companies)
            {
                if(c.GetShn().toLowerCase().compareTo(shortn)==0)
                {
                    w_json.write(c.toString());
                    w_json.write(System.lineSeparator());
                    w_xml.write(c.toString());
                    w_xml.write(System.lineSeparator());
                    System.out.println(c.toString());
                    count++;
                }
            }
            if(count==0)
            {
                w_json.write("Не найдено");
                w_json.write(System.lineSeparator());
                w_xml.write("Не найдено");
                w_xml.write(System.lineSeparator());
                System.out.println("Не найдено");
                w_txt.write("Не найдено с коротким именем: " + shortn + "\n");
            }
            else {
                w_txt.write("Найдено: " + count + " с коротким именем: " + shortn + "\n");
            }
            count=0;
            //Отрасль
            System.out.println("Введите отрасль: ");
            String br=in.nextLine().toLowerCase();
            w_json.write("Результат поиска по отрасли: ");
            w_json.write(System.lineSeparator());
            w_xml.write("Результат поиска по отрасли:");
            w_xml.write(System.lineSeparator());
            System.out.println("Результат поиска по отрасли:");
            for(Companies c:companies)
            {
                if(c.GetB().toLowerCase().compareTo(br)==0)
                {
                    w_json.write(c.toString());
                    w_json.write(System.lineSeparator());
                    w_xml.write(c.toString());
                    w_xml.write(System.lineSeparator());
                    System.out.println(c.toString());
                    count++;
                }
            }
            if(count==0)
            {
                w_json.write("Не найдено");
                w_json.write(System.lineSeparator());
                w_xml.write("Не найдено");
                w_xml.write(System.lineSeparator());
                System.out.println("Не найдено");
                w_txt.write("Не найдено компаний из отрасли: " + shortn + "\n");
            }
            else {
                w_txt.write("Найдено: " + count + " из отрасли: " + shortn + "\n");
            }
            count=0;
            //Вид деятельности
            System.out.println("Введите вид деятельности: ");
            String act=in.nextLine().toLowerCase();
            w_json.write("Результат поиска по виду деятельности: ");
            w_json.write(System.lineSeparator());
            w_xml.write("Результат поиска по виду деятельности:");
            w_xml.write(System.lineSeparator());
            System.out.println("Результат поиска по виду деятельности:");
            for(Companies c:companies)
            {
                if(c.GetAct().toLowerCase().compareTo(act)==0)
                {
                    w_json.write(c.toString());
                    w_json.write(System.lineSeparator());
                    w_xml.write(c.toString());
                    w_xml.write(System.lineSeparator());
                    System.out.println(c.toString());
                    count++;
                }
            }
            if(count==0)
            {
                w_json.write("Не найдено");
                w_json.write(System.lineSeparator());
                w_xml.write("Не найдено");
                w_xml.write(System.lineSeparator());
                System.out.println("Не найдено");
                w_txt.write("Не найдено по виду деятельности: "+shortn+"\n");
            }
            else {
                w_txt.write("Найдено: " + count + " по виду деятельности: " + shortn + "\n");
            }
            count=0;
            //Год основания
            System.out.print("Введите дату начала промежутка (в формате dd.MM.yy): ");
            LocalDate startDate = LocalDate.parse(in.nextLine(), formatter);
            System.out.print("Введите дату конца промежутка (в формате dd.MM.yy): ");
            LocalDate endDate = LocalDate.parse(in.nextLine(), formatter);
            w_json.write("Результат поиска по дате основания: ");
            w_json.write(System.lineSeparator());
            w_xml.write("Результат поискапо дате основания:");
            w_xml.write(System.lineSeparator());
            System.out.println("Результат поиска по дате основания:");
            for(Companies c:companies)
            {
                String date=c.GetDB();
                LocalDate objectDate = LocalDate.parse(date, formatter);
                if ((objectDate.isEqual(startDate) || objectDate.isAfter(startDate)) &&
                        (objectDate.isEqual(endDate) || objectDate.isBefore(endDate)))
                {
                    w_json.write(c.toString());
                    w_json.write(System.lineSeparator());
                    w_xml.write(c.toString());
                    w_xml.write(System.lineSeparator());
                    System.out.println(c.toString());
                    count++;
                }
            }
            if(count==0)
            {
                w_json.write("Не найдено");
                w_json.write(System.lineSeparator());
                w_xml.write("Не найдено");
                w_xml.write(System.lineSeparator());
                System.out.println("Не найдено");
                w_txt.write("Не найдено компаний по промежутка даты основания компании [" + startDate+", "+endDate+"]" + "\n");
            }
            else {
                w_txt.write("Найдено: " + count + " по промежутку даты: [" + startDate+", "+endDate+"]"+ "\n");
            }
            count=0;
            //Количество людей
            System.out.println("Введите минимум людей : ");
            int min=in.nextInt();
            System.out.println("Введите максимум людей : ");
            int max=in.nextInt();
            w_json.write("Результат поиска по количеству людей: ");
            w_json.write(System.lineSeparator());
            w_xml.write("Результат поиска по количеству людей:");
            w_xml.write(System.lineSeparator());
            System.out.println("Результат поиска по количеству людей:");
            for(Companies c:companies)
            {
                int number=Integer.parseInt(c.GetNE());
                if(number<=max && number>=min)
                {
                    w_json.write(c.toString());
                    w_json.write(System.lineSeparator());
                    w_xml.write(c.toString());
                    w_xml.write(System.lineSeparator());
                    System.out.println(c.toString());
                    count++;
                }
            }
            if(count==0)
            {
                w_json.write("Не найдено");
                w_json.write(System.lineSeparator());
                w_xml.write("Не найдено");
                w_xml.write(System.lineSeparator());
                System.out.println("Не найдено");
                w_txt.write("Не найдено компаний с количеством работников от " + min + " до " + max + "\n");
            }
            else {
                w_txt.write("Найдено: " + count + " компаний с количеством работников от " + min + " до " + max + "\n");
            }
            count=0;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}