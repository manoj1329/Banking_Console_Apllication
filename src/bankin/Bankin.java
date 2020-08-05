package bankin;
import java.util.Scanner;
public class Bankin {
public static void main(String[] args) {    
Banking b=new Banking();
int x,y;
Scanner sc=new Scanner(System.in);
do
{
System.out.println("******************************");
System.out.println("WELCOME");
System.out.println("******************************");
System.out.println("1.Create New Account");
System.out.println("2.Existing Account");
System.out.println("3.Exit");
System.out.println("******************************");
System.out.println("Enter the your choice");
x=sc.nextInt();
switch(x)
{
case 1:b.newacc();
break;
case 2:
do
{
    System.out.println("******************************");
System.out.println("1.Deposit Cash");
System.out.println("2.Withdraw Cash");
System.out.println("3.Check Balance");
System.out.println("4.Mini Statement");
System.out.println("5.Money Transfer");
System.out.println("6.Log Out");
System.out.println("Enter the your choice");
System.out.println("******************************");
y=sc.nextInt();
switch(y)
{
case 1:b.deposit();
break;
    
case 2:b.withdraw();
break;
    
case 3:b.balance();
break;

case 4:b.ministatement();
break;
    

case 5:b.transfer();
break;

case 6:
break;
default :System.out.println("SORRY INVALID CHOICE");
}
}while (y !=6);

break;

case 3:System.exit(0);
break;

default :System.out.println("SORRY TRY AGAIN");
}
}while(x !=3);

}
}





