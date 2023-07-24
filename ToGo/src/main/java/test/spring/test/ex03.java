package test.spring.test;

public class ex03 {
    public static void main(String[] args) {
        for(int y=1; y<=4;y++) {
            int listCount = (int) (Math.random() * 3 + 2);
            System.out.println("listCount="+listCount);
            for (int n = 0; n < listCount; n++) {
                int random = (int) (Math.random() * 100);
                System.out.println("random="+random);
            }

        }
    }
}
