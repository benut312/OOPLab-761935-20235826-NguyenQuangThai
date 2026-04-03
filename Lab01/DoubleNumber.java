import java.util.Scanner;
public class DoubleNumber{

	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap so thu nhat:");
        double num1 = sc.nextDouble();
        System.out.println("Nhap so thu hai :");
        double num2 = sc.nextDouble();
        System.out.println("num1 + num2 = " + (num1 + num2));
        System.out.println("num1 - num2 = "+ (num1 - num2));
        System.out.println("num1 * num2 = " + (num1 * num2));
        if(num2 != 0 ){
            System.out.println("num1 / num2 = " + (num1 /  num2));
        }else{ 
            System.out.println("Gia tri khong xac dinh");
        }
	}
}


