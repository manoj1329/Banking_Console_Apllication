package bankin;

import static java.lang.System.out;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Database {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
                 LocalDateTime now = LocalDateTime.now();
                 String type1="Credit";
                 String type2="Debit";
    void naccount(String accno,String accname,Double amount)
    {
    String anum=accno;
     String name=accname;
     Double balance=amount;
       try{
       Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank?useSSL=false","root","1998");
          String Query="insert into acdetails values('"+anum+"','"+name+"','"+balance+"')";
                 Statement st=con.createStatement();
                 st.executeUpdate(Query);
                 out.println("*************************************");
                 out.println("Account Created Succesfully "); 
                 System.out.println("******************************");
                 transaction(now,anum,name,type1,balance);
}
     catch(Exception e )
     {
         System.out.println("******************************");
         System.out.println("Account already Exists");
         System.out.println("******************************");
     }
}
       
void adeposit(String accno,Double damt)
    {
    String ano=accno;
     Double dbal=damt;
     String anum=accno;
     
     try{
            java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank?useSSL=false", "root", "1998");
            String Query="select * from  acdetails where anum=?";            
            PreparedStatement psm=conn.prepareStatement(Query);
            psm.setString(1,ano);
            ResultSet rs=psm.executeQuery();
            if(rs.next())
            {
                if(rs.getString(1).equals(ano))
                {
                Statement st=conn.createStatement();
                Double bal=rs.getDouble(3);
                String aname=rs.getString(2);
                String name=rs.getString(2);
                Double bald=bal+dbal;
                st.executeUpdate("update acdetails set balance='"+bald+"' where anum='"+ano+"'");
                System.out.println("******************************");
                out.println("Acount Holder Mr/Ms:\n"+aname);
                out.println("Deposit Succesful !\n");
                out.println("Available Balance:\n"+bald);
                System.out.println("******************************");
                Double balance=damt;
                transaction(now,anum,name,type1,balance);
                 conn.close();
                  }
                         
            }
             else
                {
                    System.out.println("******************************");
                    out.println("Account Not Found !\n\n");
                    System.out.println("******************************");
                }
     }
            
     catch(Exception e )
     {
         System.out.println(e);
     }
}
void awithdraw(String accno,Double wamt)
    {
    String ano=accno;
    String anum=accno;
     Double dbal=wamt;
     try{
            java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank?useSSL=false", "root", "1998");
            String Query="select * from  acdetails where anum=?";            
            PreparedStatement psm=conn.prepareStatement(Query);
            psm.setString(1,ano);
            ResultSet rs=psm.executeQuery();
            if(rs.next())
            {
                if(rs.getString(1).equals(ano) && rs.getDouble(3)>=500)
                {
                Statement st=conn.createStatement();
                Double bal=rs.getDouble(3);
                String aname=rs.getString(2);
                String name=rs.getString(2);
                Double bald=bal-dbal;
                if(bald<=500)
                      {
                      System.out.println("******************************");
                  out.println("Minimum Balance cannot be Withdrawn!") ;   
                  System.out.println("******************************");
                  }
              else
                  {
                st.executeUpdate("update acdetails set balance='"+bald+"' where anum='"+ano+"'");
                System.out.println("******************************");
                out.println("Acount Holder Mr/Ms:\n"+aname);
                out.println("Withdraw Succesful !\n");
                out.println("Available Balance:\n"+bald);
                System.out.println("******************************");
                Double balance=wamt;
                transaction(now,anum,name,type2,balance);
                 conn.close();
                  }
                }
                else
                {
                    System.out.println("******************************");
                    out.println("Error\tInsufficient Balance !");
                    System.out.println("******************************");
                }
                                        
            }
             else
                {
                    out.println("Account Not Found !\n\n");
                }
     }
            
     catch(Exception e )
     {
         System.out.println(e);
     }
}
void abalance(String accno)
    {
    String ano=accno;
     try{
            java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank?useSSL=false", "root", "1998");
            String Query="select * from  acdetails where anum=?";            
            PreparedStatement psm=conn.prepareStatement(Query);
            psm.setString(1,ano);
            ResultSet rs=psm.executeQuery();
            if(rs.next())
            {
                if(rs.getString(1).equals(ano))
                {
                Statement st=conn.createStatement();
                String ac=rs.getString(1);
                Double bal=rs.getDouble(3);
                String aname=rs.getString(2);
                System.out.println("******************************");
                 out.println("Acount Number \n"+ac);
                out.println("Acount Holder Mr/Ms:\n"+aname);
                out.println("Available Balance:\n"+bal);
                System.out.println("******************************");
                 conn.close();
                  }
                         
            }
             else
                {
                    System.out.println("******************************");
                    out.println("Account Not Found !\n\n");
                    System.out.println("******************************");
                }
     }
            
     catch(Exception e )
     {
         System.out.println(e);
     }
}
void atransfer(String accno,String banum,Double tamt)
    {
    String ano=accno;
    String anum=accno;
     String bnum=banum;
     Double tamo=tamt;    
     try{
            java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank?useSSL=false", "root", "1998");
            String Query="select * from  acdetails where anum=?";
            PreparedStatement psm=conn.prepareStatement(Query);
            psm.setString(1,ano);
            ResultSet rs=psm.executeQuery();
            if(rs.next())
            {
              if(rs.getString(1).equals(ano) && rs.getDouble(3)>=tamt)
                  
                     
              {
                  Statement st=conn.createStatement();
                  Double balance=tamt;
                  Double bal1=rs.getDouble(3);
                  String aname1=rs.getString(2);
                  String name=rs.getString(2);
                  Double bald1=bal1-tamo;
                  if(bald1<=500)
                      {
                      System.out.println("******************************");
                  out.println("Minimum Balance cannot be Transfered!") ;   
                  System.out.println("******************************");
                  }
              else
                  {
                  st.executeUpdate("update acdetails set balance='"+bald1+"' where anum='"+ano+"'");
                  transaction(now,anum,name,type2,balance);
                  out.println("Transfer Succesful !\n");
                  conn.close();
            java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank?useSSL=false", "root", "1998");
            String Query1="select * from  acdetails where anum=?";
            PreparedStatement psm1=con.prepareStatement(Query);
            psm1.setString(1,bnum);
            ResultSet rs1=psm1.executeQuery();;
            if(rs1.next())
            {
                String anum1=bnum;
                String name1=rs1.getString(2);
                Double balance1=tamt;
                Statement st1=con.createStatement();
               transaction1(now,anum1,name1,type1,balance1);
                Double bal2=rs1.getDouble(3);
                String aname2=rs1.getString(2);
                Double bald2=bal2+tamo;
                 System.out.println("******************************");
                 st1.executeUpdate("update acdetails set balance='"+bald2+"' where anum='"+bnum+"'");
                out.println("Amount Credited To Mr/Ms:\n"+aname2);
                out.println("Available Balance:\n"+bald1);
                System.out.println("******************************");
                 con.close();
                         
            }
                 
            else
            {
                System.out.println("******************************");
                System.out.println("Beneficiary Account Not Found !\n");
                System.out.println("******************************");
            }
              }
              }
              else
                {
                    System.out.println("******************************");
                    out.println("Transaction Failure !\n\n");
                    System.out.println("******************************");
                }
            
            }
             else
                {
                    System.out.println("******************************");
                    out.println("Account Not Found !\n\n");
                    System.out.println("******************************");
                }
            
            }
                
     catch(Exception e )
     {
         System.out.println(e);
     }
}

    private void transaction(LocalDateTime now, String anum, String name,String type, Double balance) {
        LocalDateTime d=now;
        String ano=anum;
        String n=name;
        String t=type;
        Double bal=balance;
        try{
       Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank?useSSL=false","root","1998");
          String Query="insert into transaction values('"+d+"','"+ano+"','"+n+"','"+t+"','"+bal+"')";
                 Statement st=con.createStatement();
                 st.executeUpdate(Query);
    }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    private void transaction1(LocalDateTime now, String anum, String name,String type, Double balance) {
        LocalDateTime d=now;
        String ano=anum;
        String n=name;
        String t=type;
        Double bal=balance;
        try{
       Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank?useSSL=false","root","1998");
          String Query="insert into transaction values('"+d+"','"+ano+"','"+n+"','"+t+"','"+bal+"')";
                 Statement st=con.createStatement();
                 st.executeUpdate(Query);
    }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    void mini(String ano) {
        String anum=ano;
        try{
       Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank?useSSL=false","root","1998");
          String Query="select * from transaction where anum=?";
          PreparedStatement ps=con.prepareStatement(Query);
          ps.setString(1,anum);
          ResultSet rs=ps.executeQuery();
            System.out.println("******************************");
              out.println("Account Number:"+anum);
          out.println("DATE\t\tTIME\t\t TYPE \t AMOUNT");
          out.println("-----------------------------------------------");
          while(rs.next())
          {
             
             rs.getString(2);
              String name=rs.getString(3);
            
              
              out.print(rs.getDate(1));
              out.print("\t");
              out.print(rs.getTime(1));
              out.print("\t");
             out.print(rs.getString(4));
             out.print("\t");
            out.println(rs.getString(5));
          }
          System.out.println("******************************");
    }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}