import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class project1 {
    private static final String alphabetRus = "абвгдеёжзийклмнопрстуфхцчшщьыъэюя., :-!?";

    public static void main(String[] args) {
        // "C:\\Users\\sus\\JavaRush\\project.txt" - путь к файлу с исходным текстом "хочу стать программистом"
        System.out.println("Выберите режим: 1 - использовать заданный криптографический ключ; 2 - brute force");
        Scanner scanner = new Scanner(System.in);
        int mode = scanner.nextInt();
        if (mode != 1 && mode != 2) {
            while (true) {
                System.out.println("Нужно ввести 1 или 2, попробуйте еще раз!");
                mode = scanner.nextInt();
                if (mode == 1 || mode == 2) {
                    break;
                }
            }
        }
        if (mode == 1) {
            System.out.println("Укажите путь к файлу:");
            Scanner scanner1 = new Scanner(System.in);
            String path1 = scanner1.next();
            Path path = Path.of(path1);
            try {
                List<String> list = Files.readAllLines(path);
                for (String str : list) {
                    String line = str;
                    Path pathNew = Files.createFile(Path.of("C:\\Users\\sus\\JavaRush\\project" + "_cipher" + ".txt"));
                    System.out.println("Путь к файлу с зашифрованным текстом: " + pathNew); //чтобы указать в консоли для расшифровки
                    String lineReady = encryptText(line);
                    writeInFile(pathNew, lineReady);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Укажите путь к файлу, текст в котором нужно расшифровать: ");
            String path2 = scanner1.next();
            decryptTextwithKey(path2);


        } else if (mode == 2) {
            System.out.println("Укажите путь к файлу:");
            Scanner scanner1 = new Scanner(System.in);
            String path1 = scanner1.next();
            Path path = Path.of(path1);
            try {
                List<String> list = Files.readAllLines(path);
                for (String str : list) {
                    String line = str;
                    Path pathNew = Files.createFile(Path.of("C:\\Users\\sus\\JavaRush\\project" + "_cipher" + ".txt"));
                    System.out.println("Путь к файлу с зашифрованным текстом: " + pathNew); //чтобы указать в консоли для расшифровки
                    String lineReady = encryptText(line);
                    writeInFile(pathNew, lineReady);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Укажите путь к файлу, текст в котором нужно расшифровать: ");
            String path2 = scanner1.next();
            decryptTextBF(path2);
        }
    }

    public static void writeInFile(Path path, String text) throws FileNotFoundException {
        File file = new File(String.valueOf(path));
        PrintWriter pw = new PrintWriter(file);
        pw.println(text);
        pw.close();
    }

    private static String encryptText(String textForCipher) {
        System.out.println("Укажите криптографический ключ для шифрования:");
        Scanner scanner = new Scanner(System.in);
        int key = scanner.nextInt();
        String finalText = "";
        for (int i = 0; i < textForCipher.length(); i++) {
            String text2 = Character.toString(textForCipher.charAt(i));
            if (alphabetRus.indexOf(text2) == -1) {
                continue;
            }
            String text3 = Character.toString(alphabetRus.charAt((alphabetRus.indexOf(text2) + key)));

            finalText = finalText + text3;
        }
        return finalText;
    }

    public static void decryptTextwithKey(String textForDecipher) {
        Scanner scanner = new Scanner(System.in);
        String path1 = String.valueOf(textForDecipher);
        Path path = Path.of(path1);
        try {
            List<String> list = Files.readAllLines(path);
            for (String str : list) {
                String line = str;
                System.out.println("Укажите криптографический ключ для расшифровки:");
                int key = scanner.nextInt();
                String cText = "";
                for (int j = 0; j < line.length(); j++) {
                    int charIndex = alphabetRus.indexOf(line.charAt(j));
                    int newIndex = (charIndex - key) % 41;
                    if (newIndex < 0) {
                        newIndex = alphabetRus.length() + newIndex;
                    }
                    String ciperChar = Character.toString(alphabetRus.charAt(newIndex));
                    cText = cText + ciperChar;
                }
                System.out.println(cText);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void decryptTextBF(String textForDecipher) {
        System.out.println("Начинаем подбор ключа: ");
        String path1 = String.valueOf(textForDecipher);
        Path path = Path.of(path1);
        try {
            List<String> list = Files.readAllLines(path);
            for (String str : list) {
                String line = str;
                for (int i = 1; i < alphabetRus.length() - 1; i++) {
                    String cText = "";
                    for (int j = 0; j < line.length(); j++) {
                        int charIndex = alphabetRus.indexOf(line.charAt(j));
                        int newIndex = (charIndex - i) % 41;
                        if (newIndex < 0) {
                            newIndex = alphabetRus.length() + newIndex;
                        }
                        String ciperChar = Character.toString(alphabetRus.charAt(newIndex));
                        cText = cText + ciperChar;
                    }
                    System.out.println(cText);
                    String check = " ";
                    String check1 = Character.toString(cText.charAt(4));
                    String check2 = Character.toString(cText.charAt(10));
                    if (check1.equals(check) && check2.equals(check)) {
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

