/*Τρίμης Στυλιανός-Αθανάσιος 1564*/


package mypackage;

public class student {
	  private String name;
	  private String lastname;
	  private String id;
	  private String e_mail;
	  private int semester;
	  public student()
	  {
		  this.id="";
		  this.name="";
		  this.lastname="";
		  this.e_mail="";
		  this.semester=1;
	  }
	  public student(String i, String n, String ln, int s, String e)
	  {
		  this.id=i;
		  this.name=n;
		  this.lastname=ln;
		  this.e_mail=e;
		  this.semester=s;
	  }
	  public String getId()
	  {
		  return this.id;
	  }
	  public String getName()
	  {
		  return this.name;
	  }
	  public String getLastname()
	  {
		  return this.lastname;
	  }
	  public String getEmail()
	  {
		  return this.e_mail;
	  }
	  public int getSemester()
	  {
		  return this.semester;
	  }
	  public String to_str()
	  {
		  return this.id+"--"+this.name+"--"+this.lastname+"--"+this.e_mail+"--"+String.valueOf(this.semester);
	  
	  }
}
