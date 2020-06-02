public interface AddressbookInterface{
	public void openAddressBook(String file);
	public void addPerson(String fname,String lname,String address,String city,String state,int zip,String phnno);
	public void deletePerson(String fname,String lname);
	public int findIndex(String fname,String lname);
	public void editPerson(String value,String option,int idx);
	public void editPerson(int value,int idx);
	public void SortbyZip();
	public void SortbyName();
	public void PrintMailinglabelformat();
}