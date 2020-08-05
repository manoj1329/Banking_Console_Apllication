package bankin;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Scanner;
public class Banking {
String accno;
String accname;
Double amount;
Double damt;
Database db=new Database();
String  tot,t3;
int i,j,c=0,acno,d=0,a=1,p=1;
Scanner sc=new Scanner(System.in);
Banking()
{
c=0;
}
void newacc()
{
out.println("Enter the Account Number");
accno=sc.next();
System.out.println("Enter the Account Holder Name");
accname=sc.next();
System.out.println("Enter Minimum Deposit Amount:");
amount=sc.nextDouble();
c++;
if(amount>=500)
{
db.naccount(accno, accname, amount);
}
else
{
    System.out.println("Account Cannot Be Created Deposit Minimum Balance 500\\-");
}

}
void deposit()
{
System.out.println("Enter the Account Number");
accno=sc.next();
System.out.println("Enter the Deposit Amount");
damt=sc.nextDouble();
db.adeposit(accno, damt);
return;
}
void withdraw(){
    Double wamt;
    System.out.println("Enter the Account Number");
accno=sc.next();
    System.out.println("Enter Withdraw amount:");
    wamt=sc.nextDouble();
    db.awithdraw(accno,wamt);
}
void balance()
{
    System.out.println("Enter Account Number");
    accno=sc.next();
    db.abalance(accno);
}
void transfer()
{
    String banum;
    System.out.println("Enter Account Number:");
    accno=sc.next();
    System.out.println("Enter Beneficiary Account Number:");
    banum=sc.next();
    System.out.println("Enter Amount to be Tranfered");
    Double tamt=sc.nextDouble();
    db.atransfer(accno,banum,tamt);
}
void ministatement()
{
    System.out.println("Enter Account Number");
    String ano=sc.next();
    db.mini(ano);
}
}





