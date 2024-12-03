// Фомина Мария Игоревна
//Лаб4 JAVA Обработка классов.
/*Условие:
* Модифицировать условие задачи учитывая свои пожелания
Возможно  авторское условие задачи
Использовать контейнеры:
 Vector, ArrayList, LinkedList, HashSet, TreeSet, HashMap, TreeMap.)

1) Задача "контакты"
а) разработать класс Контакт, определяющий запись в электронной книге мобильного
телефона и содержащий по меньшей мере следующие поля:
- *Наименование (имя человека или организации)
- *Номер телефона мобильного
- Номер телефона рабочего
- Номер телефона (домашнего)
- Адрес электронной почты
- Адрес веб-страницы
- Адрес

Обязательными является поля помеченные *, остальные поля могут быть пустыми

б) класс Контакт должен реализовать:
-интерфейс Comparable и Comparator с возможностью выбора одного из полей для сравнения
-интерфейс Iterator - индексатор по всем полям объекта Контакт
-метод для сохранения значений всех полей в строке текста (переопределить toString())
-конструктор или метод для инициализации объекта из строки текста

в) Для тестирования класса Контакт, создать консольное приложение позволяющее
создать небольшой массив контактов и напечатать отсортированными по
выбранному полю.
*/
/*Идея решения: Реализован класс Contact, используя Comparator разработаны два вида сортировок.
Так как только два поля являются обязательными дла заподнения. Заполнения массива возможно тремя вариантами: через set, метод fromString,
а при наличии только 2 обязательных полей через конструктор. Для просмотра вывода массива с помощью цикла for был использован Iterator,
 так как он позволяет грамотно проходить по всем полям класса. При выводе неявно вызывается метод toString(), который помогает желаемым образом выводить данные */
/*Тест:
* Сортируем по имени:
Alice, 9234567892, 8765432112,  не указан, alice@example.com,  не указан,  не указан
Alice, 1234567898,  не указан,  не указан,  не указан,  не указан,  не указан
Charlie, 5555555552,  не указан,  не указан,  не указан,  не указан,  не указан
Maria, 3756409822, 1234777731, 3334777723, mmm@gmail.com, m.com, Bogdanovicha 55
Кob, 9876543212,  не указан,  не указан,  не указан,  не указан,  не указан
Сортировка по мобильному номеру:
Alice, 1234567898,  не указан,  не указан,  не указан,  не указан,  не указан
Maria, 3756409822, 1234777731, 3334777723, mmm@gmail.com, m.com, Bogdanovicha 55
Charlie, 5555555552,  не указан,  не указан,  не указан,  не указан,  не указан
Alice, 9234567892, 8765432112,  не указан, alice@example.com,  не указан,  не указан
Кob, 9876543212,  не указан,  не указан,  не указан,  не указан,  не указан*/
import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class Main {
    private static final Pattern PHONE= Pattern.compile("\\d{10}");
    private static final Pattern EMAIL= Pattern.compile("^[\\w-\\.]+@[\\w-]+\\.[a-z]{2,4}$");
    public static class Contact implements Comparable<Contact>, Iterable<String> {
        private String name;
        private String mobileNumber;
        private String workNumber;
        private String homeNumber;
        private String email;
        private String website;
        private String address;

        public Contact(String name, String mobileNumber) {
            this.name = name;
            setMobileNumber(mobileNumber);
        }
        public void setMobileNumber(String mobileNumber) {
            if (!isValidPhone(mobileNumber)) {
                throw new IllegalArgumentException("Номер мобильного телефона должен состоять из 10 цифр.");
            }
            this.mobileNumber = mobileNumber;
        }
        public void setWorkNumber(String workNumber) {
            this.workNumber = workNumber;
        }
        public void setHomeNumber(String homeNumber) {
            this.homeNumber = homeNumber;
        }
        public void setEmail(String email) {
            if (!isValidEmail(email)) {
                throw new IllegalArgumentException("Некорректный адрес электронной почты.");
            }
            this.email = email;
        }
        public void setWebsite(String website) {
            this.website = website;
        }
        public void setAddress(String address) {
            this.address = address;
        }
        private boolean isValidPhone(String phone) {
            return PHONE.matcher(phone).matches();
        }
        private boolean isValidEmail(String email) {
            return EMAIL.matcher(email).matches();
        }
        public int compareTo(Contact other) {
            return this.name.compareTo(other.name);
        }
        public static Comparator<Contact> compareByMobileNumber() {
            return Comparator.comparing(contact -> contact.mobileNumber);
        }
        public Iterator<String> iterator() {
            return new Iterator<String>() {
                private int index = 0;
                private final String[] fields = {name, mobileNumber, workNumber, homeNumber, email, website, address};
                public boolean hasNext() {
                    return index < fields.length;
                }
                public String next() {
                    return fields[index++];
                }
            };
        }
        public String toString() {
            return String.join(", ", name, mobileNumber,
                    (workNumber != null && !workNumber.isEmpty()) ? workNumber : "не указан",
                    (homeNumber != null && !homeNumber.isEmpty()) ? homeNumber : "не указан",
                    (email != null && !email.isEmpty()) ? email : "не указан",
                    (website != null && !website.isEmpty()) ? website : "не указан",
                    (address != null && !address.isEmpty()) ? address : "не указан");
        }
        public static Contact fromString(String str) {
            String[] parts = str.split(",");
            if (parts.length < 2) {
                throw new IllegalArgumentException("Недостаточно данных для создания контакта.");
            }
            Contact contact = new Contact(parts[0], parts[1]);
            if (parts.length > 2) contact.setWorkNumber(parts[2]);
            if (parts.length > 3) contact.setHomeNumber(parts[3]);
            if (parts.length > 4) contact.setEmail(parts[4]);
            if (parts.length > 5) contact.setWebsite(parts[5]);
            if (parts.length > 6) contact.setAddress(parts[6]);
            return contact;
        }
    }
    private static void writeContactsToFile(ArrayList<Contact> contacts, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Contact contact : contacts) {
                writer.write(contact.toString());
                writer.newLine();
            }
            System.out.println("Контакты успешно записаны в файл " + filename);
        } catch (IOException e) {
            System.err.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }
    private static ArrayList<Contact> readContactsFromFile(String filename) {
        ArrayList<Contact> contacts = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    contacts.add(Contact.fromString(line));
                } catch (IllegalArgumentException e) {
                    System.err.println("Ошибка при добавлении контакта: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }
        return contacts;
    }

    public static void main(String[] args) {
        ArrayList<Contact> contacts = new ArrayList<>();
        contacts= readContactsFromFile("input.txt");
        System.out.println("Сортируем по имени:");
        Collections.sort(contacts);
        for (Contact contact : contacts) {
            System.out.println(contact); // Вызывает contact.toString() автоматически
        }
        System.out.println("Сортировка по мобильному номеру:");
        Collections.sort(contacts, Contact.compareByMobileNumber());
        for (Contact contact : contacts) {
            System.out.println(contact); // Вызывает contact.toString() автоматически
        }
        writeContactsToFile(contacts, "output_contacts.txt");
    }
}