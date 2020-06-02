public class Address{
	String address;
	String city;
	String state;
	int zip;

	public Address(String address,String city,String state,int zip) {
  	this.address=address;
  	this.city=city;
  	this.state=state; 
  	this.zip=zip;
  	}

  	public String getaddress(){
  		return address;
  	}

  	public void setaddress(String address){
  		this.address = address;
  	}

  	public String getcity(){
  		return city;
  	}

  	public void setcity(String city){
  		this.city = city;
  	}

  	public String getstate(){
  		return state;
  	}

  	public void setstate(String state){
  		this.state = state;
  	}

  	public int getzip(){
  		return zip;
  	}

  	public void setzip(int zip){
  		this.zip = zip;
  	}
}
