package hust.soict.dsai.garbage;

public class ConcatenationInLoops {
    public static void main(String[] args) {
        int iterations = 100000;

        long start = System.currentTimeMillis();
        String result = "";
        for (int i = 0; i < iterations; i++) {
            result += "a";
        }
        System.out.println("String + operator: " + (System.currentTimeMillis() - start) + " ms");

        start = System.currentTimeMillis();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < iterations; i++) {
            stringBuffer.append("a");
        }
        System.out.println("StringBuffer: " + (System.currentTimeMillis() - start) + " ms");

        start = System.currentTimeMillis();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < iterations; i++) {
            stringBuilder.append("a");
        }
        System.out.println("StringBuilder: " + (System.currentTimeMillis() - start) + " ms");
    }
}
