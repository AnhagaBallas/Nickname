import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    static volatile AtomicInteger count1 = new AtomicInteger(0);
    static volatile AtomicInteger count2 = new AtomicInteger(0);
    static volatile AtomicInteger count3 = new AtomicInteger(0);

    public static void main(String[] args) {
        Random random = new Random();
        String[] texts = new String[100_000];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));
        }
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < texts.length; i++) {
                if (texts[i].length() == 3) {
                    if (texts[i].equals(reverse(texts[i]))) {
                        if (checkChar(texts[i])) {
                            if (isAlphabaticOrder(texts[i])) {
                                count1.getAndIncrement();
                            }

                        }
                    }

                }
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < texts.length; i++) {
                if (texts[i].length() == 4) {
                    if (texts[i].equals(reverse(texts[i]))) {
                        if (checkChar(texts[i])) {
                            if (isAlphabaticOrder(texts[i])) {
                                count2.getAndIncrement();
                            }

                        }
                    }

                }
            }
        });
        Thread thread3 = new Thread(() -> {
            for (int i = 0; i < texts.length; i++) {
                if (texts[i].length() == 5) {
                    if (texts[i].equals(reverse(texts[i]))) {
                        if (checkChar(texts[i])) {
                            if (isAlphabaticOrder(texts[i])) {
                                count3.getAndIncrement();
                            }

                        }
                    }

                }
            }
        });
        thread1.start();
        thread2.start();
        thread3.start();
        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Красивых слов с длиной 3: " + count1 + " шт");
        System.out.println("Красивых слов с длиной 4: " + count2 + " шт");
        System.out.println("Красивых слов с длиной 5: " + count3 + " шт");


    }


    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }

    public static String reverse(String text) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            result.append(text.charAt((text.length() - 1) - i));
        }
        return result.toString();
    }

    public static boolean checkChar(String text) {
        Boolean result = true;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(0) == text.charAt(i)) {
                continue;
            } else {
                result = false;
            }
        }

        return result;
    }

    public static boolean isAlphabaticOrder(String s) {
        int n = s.length();
        char c[] = new char[n];
        for (int i = 0; i < n; i++) {
            c[i] = s.charAt(i);
        }
        Arrays.sort(c);
        for (int i = 0; i < n; i++)
            if (c[i] != s.charAt(i))
                return false;

        return true;
    }
}

