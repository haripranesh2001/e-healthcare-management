import java.sql.*;
import java.util.*;
public class Connection1
{
	Connection con=null;
	Connection getconnection() throws Exception
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcare","root","Mysql@2001.");
	    return con;
	}
	void Login() throws Exception
	{
		Statement stmt=con.createStatement();
		System.out.println("press 1 for Admin login");
		System.out.println("press 2 for patient login");
		System.out.println("press 3 for doctor login");
		Scanner in=new Scanner(System.in);
		int n=in.nextInt();
		if(n==1)
		{
			AdminLogin(stmt,in);
		}
		else if(n==2)
		{
			PatientLogin(stmt,in);
		}
		else if(n==3)
		{
			DoctorLogin(stmt,in);
		}else
		{
			System.out.println("Enter the correct numbers from above");
		}
	}
	void AdminLogin(Statement stmt,Scanner in) throws Exception
	{
		System.out.println("Enter userName:");
		String uName=in.next();
		System.out.println("Enter Password");
		String pass=in.next();
		ResultSet rs=stmt.executeQuery("Select * from admin_login where username='"+uName+"'and password='"+pass+"'");
		if(rs.next())
		{
			System.out.println("**********Login successfull**********");
			int d1=0;
			while(d1!=7)
			{
		
		System.out.println("1 for Doctor List");
		System.out.println("2 for patient List");
		System.out.println("3 for add new Doctor");
		System.out.println("4 for Remove a Doctor");
		System.out.println("5 for Appointment Details");
		System.out.println("6 for viewing all reports");
		System.out.println("7 for Log out");
	    d1=in.nextInt();
		switch(d1)
		{
		case 1:
			String sql="select * from doctors;";
			ResultSet rs1=stmt.executeQuery(sql);
			while(rs1.next())
			{
				System.out.println(rs1.getInt(1)+" "+rs1.getString(2)+" "+rs1.getString(3)+" "+rs1.getInt(4)+" "+rs1.getString(5));
			}
			System.out.println();
			break;
		case 2:
			String sql2="select * from patients;";
			ResultSet rs2=stmt.executeQuery(sql2);
			while(rs2.next())
			{
				System.out.println(rs2.getInt(1)+" "+rs2.getString(2)+" "+rs2.getString(3)+" "+rs2.getString(4));
			}
			System.out.println();
			break;
		case 3:
			System.out.println("Enter the doctor id,doctor name,contact number,entry charge and the doctor type");
			int id=in.nextInt();
			String nm=in.next();
			String ctnum=in.next();
			int ec=in.nextInt();
			String dcType=in.next();
			String sql3="insert into doctors value('"+id+"','"+nm+"','"+ctnum+"','"+ec+"','"+dcType+"',123);";
			int rs3=stmt.executeUpdate(sql3);
			System.out.println("A new doctor has been added");
			System.out.println();
			break;
		case 4:
			System.out.println("Enter the doctor name");
			String dc=in.next();
			String sql4="delete from doctors where doctor_name='"+dc+"'";
			int rss=stmt.executeUpdate(sql4);
			String str4="select * from doctors";
			ResultSet rs4=stmt.executeQuery(str4);
			while(rs4.next())
			{
				System.out.println(rs4.getInt(1)+" "+rs4.getString(2)+" "+rs4.getString(3)+" "+rs4.getInt(4)+" "+rs4.getString(5));
			}
			break;
		case 5:
			  System.out.println("Appointment details");
			  String str="Select * from appointments";
			  System.out.println();
			  ResultSet ad=stmt.executeQuery(str);
			  while(ad.next())
			  {
				  System.out.println(ad.getInt(1)+" "+ad.getString(2)+" "+ad.getInt(3)+" "+ad.getString(4)+" "+ad.getString(5));
			  }
			  System.out.println();
			  break;
		case 6:
			String qry1="select * from reports";
			 ResultSet ad1=stmt.executeQuery(qry1);
			  while(ad1.next())
			  {
				  System.out.println(ad1.getInt(1)+" "+ad1.getString(2)+" "+ad1.getInt(3)+" "+ad1.getString(4)+" "+ad1.getString(5));
			  }
			  System.out.println();
			  break;
		case 7:
			System.out.println("You are logged out of admin");
			System.out.println("Do you want to continue with the portal");
			String str0=in.next();
			if(str0.equals("yes"))
			{
				Login();
			}else
				System.out.println("Thank you for your time");
			break;
			
		}
		}
		}else
			System.out.println("Login unsuccessfull");
		
	}
	void PatientLogin(Statement stmt,Scanner in) throws Exception
	{
		Connection1 obj=new Connection1();
		System.out.println("Enter userId");
		int pId=in.nextInt();
		System.out.println("Enter Password");
		String pass=in.next();
		int pl=0;
		ResultSet rs=stmt.executeQuery("Select * from patients where patient_id='"+pId+"'and password='"+pass+"'");
		if(rs.next())
		{
			System.out.println("*************Login successfull************");
			while(pl!=4)
			{
			System.out.println("1 for viewing the doctors in hospital");
			System.out.println("2 for booking an appointment with a doctor");
			System.out.println("3 for viewing the report");
			System.out.println("4 for logout");
			 pl=in.nextInt();
			switch(pl)
			{
			case 1:
				String sql="select * from doctors;";
				ResultSet rs1=stmt.executeQuery(sql);
				while(rs1.next())
				{
					System.out.println(" Doctor Id:"+rs1.getInt(1)+" Doctor Name: "+rs1.getString(2)+" Doctor ph: "+rs1.getString(3)+" Consulting fee: "+rs1.getInt(4)+" Specialization: "+rs1.getString(5));
				}
				System.out.println();
				break;
			case 2:
				BookAppointment(pId,stmt,in);
				break;
			case 3:
				String str="select * from reports where patient_id='"+pId+"'";
				ResultSet rs2=stmt.executeQuery(str);
				while(rs2.next())
				{
					  System.out.println(rs2.getInt(1)+" "+rs2.getString(2)+" "+rs2.getInt(3)+" "+rs2.getString(4)+" "+rs2.getString(5));
				}
				System.out.println();
				break;
			case 4:
				System.out.println("You are logged out of patient id");
				System.out.println("Do you want to continue with the portal");
				String str0=in.next();
				if(str0.equals("yes"))
				{
					Login();
				}else
					System.out.println("Thank you for your time");
				break;
				
			}
			}
			
		}else
			System.out.println("Login unsuccessfull");
		
		}
	void BookAppointment(int pId,Statement stmt,Scanner in) throws Exception
	{
		//System.out.println("Enter the date of your preferred appointment");
		//String dte=in.next();
		String sql="select * from doctors;";
		ResultSet rs1=stmt.executeQuery(sql);
		while(rs1.next())
		{
			System.out.println(" Doctor Id:"+rs1.getInt(1)+" Doctor Name: "+rs1.getString(2)+" Doctor ph: "+rs1.getString(3)+" Consulting fee: "+rs1.getInt(4)+" Specialization: "+rs1.getString(5));
		}
		System.out.println();
		System.out.println("Select the doctor id for your preferred doctor");
		int did=in.nextInt();
		ResultSet app=stmt.executeQuery("select doctor_type from doctors where doctor_id='"+did+"'");  
		String doc=" ";
		if(app.next())
		{
		doc=app.getString(1);
		}
		System.out.println("Do you wish to proceed for consulting payment ....1 for yes & 2 for No");
		int vc=in.nextInt();
		if(vc==1)
		{
			System.out.println("Enter your UPI id after payment through our hospital management");
			String upi=in.next();
			System.out.println("Your appointment has been confirmed");
			System.out.println();
			String appt="insert into appointments values('"+pId+"',sysdate(),'"+did+"','"+doc+"','"+upi+"')";   // WORKING
			int f=stmt.executeUpdate(appt);
		}else
			System.out.println("Thank you for your valuable time,..Appointment is incomplete and so canceled.");
		
		
	}
	void DoctorLogin(Statement stmt,Scanner in) throws Exception
	{
		System.out.println("Enter doctor_id");
		int dcid=in.nextInt();
		System.out.println("Enter Password");
		String pass=in.next();
		ResultSet rs=stmt.executeQuery("Select * from doctors where doctor_id='"+dcid+"'and password='"+pass+"'");
		if(rs.next())
		{
			System.out.println("**************Login successfull***********");
			   int dl=0;
			   while(dl!=3)
			   {
			System.out.println("Select 1 for viewing appointments ");
			System.out.println("Select 2 for prescribing and add report to patients");
			System.out.println("Select 3 for logout");
			System.out.println();
		    dl=in.nextInt();
			String str="Select * from appointments where doctor_id='"+dcid+"' ";
			ResultSet dll=stmt.executeQuery(str);
			switch(dl)
			{
			case 1:
				while(dll.next())
				{
					System.out.println(dll.getInt(1)+" "+dll.getString(2)+" "+dll.getInt(3)+" "+dll.getString(4)+" "+dll.getString(5));
				}
				break;
			case 2:
				while(dll.next())
				{
					System.out.println(dll.getInt(1)+" "+dll.getString(2)+" "+dll.getInt(3)+" "+dll.getString(4)+" "+dll.getString(5));
				}
				System.out.println("Select the patient_id for which you have to give the report");
				int ptid=in.nextInt();
				String str1="select patient_problem from patients where patient_id='"+ptid+"'";
				ResultSet dl1=stmt.executeQuery(str1);
				String str2=" ";
				if(dl1.next())
				str2=dl1.getString(1);
				String str3="select doctor_name from doctors where doctor_id='"+dcid+"'";
				ResultSet dl2=stmt.executeQuery(str3);
				String str4="";
				if(dl2.next())
			    str4=dl2.getString(1);
				System.out.println("Give riview for the patient with"+str2);
				in.next();
				String riview=in.nextLine();
			    int rvw=stmt.executeUpdate("insert into reports values('"+ptid+"','"+str2+"','"+dcid+"','"+str4+"','"+riview+"')");
			    System.out.println("Report created");
			    System.out.println();
			    break;
			case 3:
				System.out.println("You are logged out of Doctor's Login");
				System.out.println("Do you want to continue with the portal");
				String str0=in.next();
				if(str0.equals("yes"))
				{
					Login();
				}else
					System.out.println("Thank you for your time");
				break;
				
			}}
		}else
			System.out.println("Login unsuccessfull");

	}
	public static void main(String args[]) throws Exception
	{
		System.out.println("Healthcare Management System");
		Connection1 connect=new Connection1();
		connect.getconnection();
		connect.Login();
	}
      
	


}
