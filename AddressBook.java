import java.util.*;
import com.google.gson.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;


public class AddressBook implements AddressbookInterface{
	List<Person> records = new ArrayList<Person> ();

	public void openAddressBook(String file){
		file = "./files/" + file;
		try
		{
			Gson gson = new Gson();
			Reader reader = Files.newBufferedReader(Paths.get(file));
			List<Person> tmp = Arrays.asList(gson.fromJson(reader, Person[].class));
			for (int i = 0; i < tmp.size(); i++){
				Person ob = tmp.get(i);
				this.records.add(ob);
			}
			reader.close();
		}
		catch(IOException e)
 		{
    		e.printStackTrace();
 		}
	}
				
	public List<Person> getRecords(){
		return this.records;
	}

	class SortbyZipHelper implements Comparator<Person> 
	{ 
	    public int compare(Person a, Person b) 
	    { 
	    	int a_zip = a.getaddress().getzip();
	    	int b_zip = b.getaddress().getzip();

	    	String a_lname = a.getlname();
	    	String b_lname = b.getlname();

	    	if (a_zip - b_zip == 0){
	    		return a_lname.compareTo(b_lname);
	    	}
	    	else{
	    		return a_zip - b_zip;
	    	}
	    } 
	} 

	class SortbyNameHelper implements Comparator<Person> 
	{ 
	    public int compare(Person a, Person b) 
	    { 
	    	String a_fname = a.getfname();
	    	String b_fname = b.getfname();

	    	String a_lname = a.getlname();
	    	String b_lname = b.getlname();

	    	if (a_lname.compareTo(b_lname) == 0){
	    		return a_fname.compareTo(b_fname);
	    	}
	    	else{
	    		return a_lname.compareTo(b_lname);
	    	}
	    } 
	} 

	public void addPerson(String fname,String lname,String address,String city,String state,int zip,String phnno){
		Address a = new Address(address,city,state,zip);
		Person e = new Person(fname,lname,phnno,a);
		records.add(e);
	}

	public void deletePerson(String fname,String lname){
		int size = records.size();
		for (int i = 0; i < size; i++){
			String f = records.get(i).getfname();
			String l = records.get(i).getlname();
			if( (f.equals(fname)) && l.equals(lname)){
				records.remove(i);
				break;
			}
		}
	}

	public int findIndex(String fname,String lname){
		int size = records.size();
		int idx=-1;
		for (int i = 0; i < size; i++){
			String f = records.get(i).getfname();
			String l = records.get(i).getlname();
			if( (f.equals(fname)) && l.equals(lname)){
				idx = i;
				break;
			}
		}
		return idx;
	}

	public void editPerson(String value,String option,int idx){
		Person obj = records.get(idx);
		Address ad = obj.getaddress();
		if(option=="a"){
			ad.setaddress(value);
			obj.setaddress(ad);
		}
		else if(option=="c"){
			ad.setcity(value);
			obj.setaddress(ad);
		}
		else if(option=="s"){
			ad.setstate(value);
			obj.setaddress(ad);
		}
		else if(option=="p"){
			obj.setphnno(value);
		}
		
		records.set(idx,obj);
	}

	public void editPerson(int value,int idx){
			Person obj = records.get(idx);	
			Address ad = obj.getaddress();		
			ad.setzip(value);
			obj.setaddress(ad);			
			records.set(idx,obj);
		}


	public void SortbyZip(){
		Collections.sort(records, new SortbyZipHelper()); 
	}

	public void SortbyName(){
		Collections.sort(records, new SortbyNameHelper()); 
	}
 
	public void PrintMailinglabelformat(){
		int size = records.size();
		for (int i = 0; i < size; i++){
			System.out.println(records.get(i).getlname() + " " + records.get(i).getfname());
			System.out.println(records.get(i).getaddress().getaddress());
			System.out.println(records.get(i).getaddress().getcity());
			System.out.println(records.get(i).getaddress().getstate());
			System.out.println(records.get(i).getaddress().getzip());
			System.out.println(records.get(i).getphnno());
			System.out.println("");
		}
	}
}

