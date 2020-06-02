public class Person{
	private final String fname; // cant be edited so private and final
	private final String lname;
	Address address;
	String phnno;

  	public Person(String fname,String lname,String phnno,Address address) {
    	this.fname=fname;
    	this.lname=lname;
      this.phnno=phnno;
    	this.address=address;
    	}

  	public String getfname(){
  		return this.fname;
  	}

  	public String getlname(){
  		return this.lname;
  	}

  	public String getphnno(){
  		return phnno;
  	}

  	public void setphnno(String phnno){
  		this.phnno = phnno;
  	}

    public Address getaddress(){
      return this.address;
    }

    public void setaddress(Address address){
      this.address = address;
    }

}
