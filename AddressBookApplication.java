import java.util.*;
import com.google.gson.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

// javac -cp .:gson-2.2.2.jar AddressBookApplication.java
// java -cp .:gson-2.2.2.jar AddressBookApplication

public class AddressBookApplication implements AddressbookInterface2{
	int curr_ab_idx = -1;
	List<AddressBook> pointers = new ArrayList<AddressBook> ();
	HashMap<Integer, Integer> isSaved = new HashMap<Integer, Integer>();

	public void toggle(){
		for(int i=0;i<pointers.size();i++){
			String i_str = String.valueOf(i);
			String i_str1 = String.valueOf(i+1);
			System.out.println("Enter " + i_str + " for Address_book_no: " + i_str1 + " with first entry:");
			System.out.println(pointers.get(i).getRecords().get(0).getfname());
			System.out.println(pointers.get(i).getRecords().get(0).getlname());
			System.out.println(pointers.get(i).getRecords().get(0).getphnno());
			System.out.println(pointers.get(i).getRecords().get(0).getaddress().getaddress());
			System.out.println(pointers.get(i).getRecords().get(0).getaddress().getcity());
			System.out.println(pointers.get(i).getRecords().get(0).getaddress().getstate());
			System.out.println(pointers.get(i).getRecords().get(0).getaddress().getzip());
		}
		int ul = pointers.size() - 1;
		System.out.println("Which address book do you want to work with?");
		Scanner sc2= new Scanner(System.in);
		int no = sc2.nextInt();
		// Eat the new line
		sc2.nextLine();
		if(no <= ul){
			curr_ab_idx = no;
		}
	}

	public void create(){
		AddressBook ab_pointer = new AddressBook();
		pointers.add(ab_pointer);
		curr_ab_idx += 1;
		isSaved.put(curr_ab_idx, 0);

	}

	public void saveAs(String f){
		f = "./files/" + f;
		List<Person> rec = pointers.get(curr_ab_idx).getRecords();
     	try {
     			Writer writer = new FileWriter(f);
     			Gson gson = new GsonBuilder().create();
     			gson.toJson(rec,writer);
     			writer.flush();
     			writer.close();
     		}
     	catch(IOException e)
     		{
        		e.printStackTrace();
     		}
	}

	public void savefinal(String f,int curr_ab_idx){
		f = "./files/" + f;
		List<Person> rec = pointers.get(curr_ab_idx).getRecords();
     	try {
     			Writer writer = new FileWriter(f);
     			Gson gson = new GsonBuilder().create();
     			gson.toJson(rec,writer);
     			writer.flush();
     			writer.close();
     		}
     	catch(IOException e)
     		{
        		e.printStackTrace();
     		}
	}



	public void diplayOptions(){
		Scanner sc= new Scanner(System.in);
		int a;
		int upper_flag=0;
		while(upper_flag==0){
			System.out.println("Select option:");
			System.out.println("1 for Create new address book");
			System.out.println("2 for Saving current address book");
			System.out.println("3 for opening existing address book");
			System.out.println("4 for Quitting application");
			System.out.println("5 for Toggling to some address book");
			a = sc.nextInt();
			switch(a){
				case 1:
					System.out.println("Create");
					this.create();
					int flag=0;
					while(flag==0){
						System.out.println("Select address book option:");
						System.out.println("1 for adding entry");
						System.out.println("2 for deleting entry");
						System.out.println("3 for editing entry");
						System.out.println("4 for sorting by zip");
						System.out.println("5 for sorting by name");
						System.out.println("6 for printing in mailing label format");
						System.out.println("7 for quitting");
						int c= sc.nextInt();
						String tmp1="",tmp2="",tmp3="",tmp4="",tmp5="",tmp7="";
						int tmp6=0;
						
						switch(c){
							case 1:
								// Eat the new line
	    						sc.nextLine();
								System.out.println("Enter first name:");
								tmp1= sc.nextLine();
								System.out.println("Enter last name:");
								tmp2= sc.nextLine();
								System.out.println("Enter address:");
								tmp3= sc.nextLine();
								System.out.println("Enter city:");
								tmp4= sc.nextLine();
								System.out.println("Enter state:");
								tmp5= sc.nextLine();
								System.out.println("Enter zip:");
								tmp6= sc.nextInt();
								// Eat the new line
	    						sc.nextLine();
								System.out.println("Enter phone no:");
								tmp7= sc.nextLine();

								pointers.get(curr_ab_idx).addPerson(tmp1,tmp2,tmp3,tmp4,tmp5,tmp6,tmp7);
								System.out.println("Added Successfully");
								
								break;
							case 2:
								// Eat the new line
	    						sc.nextLine();
								System.out.println("Enter first name:");
								tmp1= sc.nextLine();
								System.out.println("Enter last name:");
								tmp2= sc.nextLine();

								pointers.get(curr_ab_idx).deletePerson(tmp1,tmp2);
								System.out.println("Deleted Successfully");
								break;
							case 3:
								// Eat the new line
	    						sc.nextLine();
								System.out.println("Enter first name:");
								tmp1= sc.nextLine();
								System.out.println("Enter last name:");
								tmp2= sc.nextLine();

								int idx = pointers.get(curr_ab_idx).findIndex(tmp1,tmp2);
								if(idx==-1){
									System.out.println("Entry not found!");
									break;
								}

								
								int ch=0;
								while(ch != 6){
									System.out.println("Select address book option:");
									System.out.println("1 for editing address");
									System.out.println("2 for editing state");
									System.out.println("3 for editing city");
									System.out.println("4 for editing by zip");
									System.out.println("5 for editing by phone no");
									System.out.println("6 for exit");
									System.out.println("Enter choice");

									ch = sc.nextInt();
									// Eat the new line
	    							sc.nextLine();

	    							switch(ch){
	    								case 1:
	    									System.out.println("Enter address:");
	    									tmp3 =sc.nextLine(); 
	    									pointers.get(curr_ab_idx).editPerson(tmp3,"a",idx);
	    									break;
	    								case 2:
	    									System.out.println("Enter state:");
	    									tmp3 =sc.nextLine(); 
	    									pointers.get(curr_ab_idx).editPerson(tmp3,"s",idx);
	    									break;
	    								case 3:
	    									System.out.println("Enter city:");
	    									tmp3 =sc.nextLine(); 
	    									pointers.get(curr_ab_idx).editPerson(tmp3,"c",idx);
	    									break;
	    								case 4:
	    									System.out.println("Enter zip:");
	    									tmp6 = sc.nextInt();
	    									// Eat the new line
	    									sc.nextLine();
	    									pointers.get(curr_ab_idx).editPerson(tmp6,idx);
	    									break;
	    								case 5:
	    									System.out.println("Enter phone no:");
	    									tmp3 =sc.nextLine(); 
	    									pointers.get(curr_ab_idx).editPerson(tmp3,"p",idx);
	    									break;
	    								case 6:
	    									System.out.println("Done!");
	    									break;
	    								default:
	    									System.out.println("Invalid choice!");
	    									break;

	    							}

								}
							case 4:
								System.out.println("Sorted records by zip:");
								pointers.get(curr_ab_idx).SortbyZip();
								pointers.get(curr_ab_idx).PrintMailinglabelformat();
								System.out.println("sorted by zip Successfully");
								break;
							case 5:
								System.out.println("Sorted records by name:");
								pointers.get(curr_ab_idx).SortbyName();
								pointers.get(curr_ab_idx).PrintMailinglabelformat();
								System.out.println("sorted by Lastname Successfully");
								break;
							case 6:
								System.out.println("Printing records in mailing label format:");
								pointers.get(curr_ab_idx).PrintMailinglabelformat();
								break;
							case 7:
								System.out.println("Quiting!");
								flag=1;
								break;
							default:
								System.out.println("Invalid choice! Enter again.");

						}
					}
					break;
				case 2:
					System.out.println("Save");
					if(isSaved.containsKey(curr_ab_idx)){
						isSaved.replace(curr_ab_idx, 1);
					}
					
					// Eat the new line
					sc.nextLine();
					System.out.println("Enter file name:");
					String fl1 = sc.nextLine();
					saveAs(fl1);
					System.out.println("Saved Successfully");
					break;
				case 3:
					System.out.println("Open");
					// Eat the new line
					sc.nextLine();
					System.out.println("Enter file name:");
					String fl = sc.nextLine();
					AddressBook ab = new AddressBook();
					ab.openAddressBook(fl);
					pointers.add(ab);
					curr_ab_idx += 1;
					int edited = 0;
					int flag1=0;
					while(flag1==0){
						System.out.println("Select address book option:");
						System.out.println("1 for adding entry");
						System.out.println("2 for deleting entry");
						System.out.println("3 for editing entry");
						System.out.println("4 for sorting by zip");
						System.out.println("5 for sorting by name");
						System.out.println("6 for printing in mailing label format");
						System.out.println("7 for quitting");
						System.out.println("8 for renaming file");
						int c= sc.nextInt();
						String tmp1="",tmp2="",tmp3="",tmp4="",tmp5="",tmp7="";
						int tmp6=0;
						switch(c){
							case 1:
								edited = 1;
								System.out.println("Add");
								// Eat the new line
	    						sc.nextLine();
								System.out.println("Enter first name:");
								tmp1= sc.nextLine();
								System.out.println("Enter last name:");
								tmp2= sc.nextLine();
								System.out.println("Enter address:");
								tmp3= sc.nextLine();
								System.out.println("Enter city:");
								tmp4= sc.nextLine();
								System.out.println("Enter state:");
								tmp5= sc.nextLine();
								System.out.println("Enter zip:");
								tmp6= sc.nextInt();
								// Eat the new line
	    						sc.nextLine();
								System.out.println("Enter phone no:");
								tmp7= sc.nextLine();
								
								pointers.get(curr_ab_idx).addPerson(tmp1,tmp2,tmp3,tmp4,tmp5,tmp6,tmp7);
								System.out.println("Added Successfully");
								break;
							case 2:
								edited = 1;
								System.out.println("Delete");
								// Eat the new line
	    						sc.nextLine();
								System.out.println("Enter first name:");
								tmp1= sc.nextLine();
								System.out.println("Enter last name:");
								tmp2= sc.nextLine();

								pointers.get(curr_ab_idx).deletePerson(tmp1,tmp2);
								System.out.println("Deleted Successfully");
								break;
							case 3:
								edited = 1;
								System.out.println("Edit");
								// Eat the new line
	    						sc.nextLine();
								System.out.println("Enter first name:");
								tmp1= sc.nextLine();
								System.out.println("Enter last name:");
								tmp2= sc.nextLine();

								int idx = pointers.get(curr_ab_idx).findIndex(tmp1,tmp2);
								if(idx==-1){
									System.out.println("Entry not found!");
									break;
								}
								int ch=0;
								while(ch != 6){
									System.out.println("Select address book option:");
									System.out.println("1 for editing address");
									System.out.println("2 for editing state");
									System.out.println("3 for editing city");
									System.out.println("4 for editing by zip");
									System.out.println("5 for editing by phone no");
									System.out.println("6 for exit");
									System.out.println("Enter choice");

									ch = sc.nextInt();
									// Eat the new line
	    							sc.nextLine();

	    							switch(ch){
	    								case 1:
	    									System.out.println("Enter address:");
	    									tmp3 =sc.nextLine(); 
	    									pointers.get(curr_ab_idx).editPerson(tmp3,"a",idx);
	    									break;
	    								case 2:
	    									System.out.println("Enter state:");
	    									tmp3 =sc.nextLine(); 
	    									pointers.get(curr_ab_idx).editPerson(tmp3,"s",idx);
	    									break;
	    								case 3:
	    									System.out.println("Enter city:");
	    									tmp3 =sc.nextLine(); 
	    									pointers.get(curr_ab_idx).editPerson(tmp3,"c",idx);
	    									break;
	    								case 4:
	    									System.out.println("Enter zip:");
	    									tmp6 = sc.nextInt();
	    									// Eat the new line
	    									sc.nextLine();
	    									pointers.get(curr_ab_idx).editPerson(tmp6,idx);
	    									break;
	    								case 5:
	    									System.out.println("Enter phone no:");
	    									tmp3 =sc.nextLine(); 
	    									pointers.get(curr_ab_idx).editPerson(tmp3,"p",idx);
	    									break;
	    								case 6:
	    									System.out.println("Done!");
	    									break;
	    								default:
	    									System.out.println("Invalid choice!");
	    									break;

	    							}

								}
							case 4:
								System.out.println("Sorted records by zip:");
								pointers.get(curr_ab_idx).SortbyZip();
								pointers.get(curr_ab_idx).PrintMailinglabelformat();
								System.out.println("Sort by Zip Successfully");
								break;
							case 5:
								System.out.println("Sorted records by name:");
								pointers.get(curr_ab_idx).SortbyName();
								pointers.get(curr_ab_idx).PrintMailinglabelformat();
								System.out.println("Sort by LastName Successfully");
								break;
							case 6:
								System.out.println("Printing records in mailing label format:");
								pointers.get(curr_ab_idx).PrintMailinglabelformat();
								break;
							case 7:
									if (edited == 1){
										System.out.println("Save changes? (y/n)");
										Scanner sc2= new Scanner(System.in);
										String choice2 = sc2.nextLine();
										if(choice2.equals("y")){
											saveAs(fl);
											System.out.println("Changes saved!");
										}
									}
								System.out.println("Quiting!");
								flag1=1;
								break;
							case 8:
								System.out.println("Enter new filename:");
								Scanner sc2= new Scanner(System.in);
								String new_fn = sc2.nextLine();
								File oldName = new File("./files/"+fl);
								File newName = new File("./files/"+new_fn);
								if (oldName.renameTo(newName))  
						            System.out.println("Renamed successfully");         
						        else 
						            System.out.println("Error");  
							default:
								System.out.println("Invalid choice! Enter again.");

						}
					}
					break;
				case 4:
					System.out.println("Quitting from application!");
					int v;
					Scanner sc2= new Scanner(System.in);
					String f3;
					int num;
					String num_str;
					for (int k : isSaved.keySet()){  
						v = isSaved.get(k);
            			if(v==0){
            				num = k;
            				num_str = String.valueOf(num); 
            				System.out.println("Do you want to save address book no: (y/n) : " + num_str);
            				f3 = sc2.nextLine();
            				if(f3.equals("y")){
            					System.out.println("Enter filename:");
            					f3 = sc2.nextLine();
            					savefinal(f3,k);
            					System.out.println("Saved successfully!");
            				}
            			}
            		}
					upper_flag = 1;
					break;
				case 5:
					this.toggle();
					int flag2=0;
					while(flag2==0){
						System.out.println("Select address book option:");
						System.out.println("1 for adding entry");
						System.out.println("2 for deleting entry");
						System.out.println("3 for editing entry");
						System.out.println("4 for sorting by zip");
						System.out.println("5 for sorting by name");
						System.out.println("6 for printing in mailing label format");
						System.out.println("7 for quitting");
						int c= sc.nextInt();
						String tmp1="",tmp2="",tmp3="",tmp4="",tmp5="",tmp7="";
						int tmp6=0;
						switch(c){
							case 1:
								// Eat the new line
	    						sc.nextLine();
								System.out.println("Enter first name:");
								tmp1= sc.nextLine();
								System.out.println("Enter last name:");
								tmp2= sc.nextLine();
								System.out.println("Enter address:");
								tmp3= sc.nextLine();
								System.out.println("Enter city:");
								tmp4= sc.nextLine();
								System.out.println("Enter state:");
								tmp5= sc.nextLine();
								System.out.println("Enter zip:");
								tmp6= sc.nextInt();
								// Eat the new line
	    						sc.nextLine();
								System.out.println("Enter phone no:");
								tmp7= sc.nextLine();

								pointers.get(curr_ab_idx).addPerson(tmp1,tmp2,tmp3,tmp4,tmp5,tmp6,tmp7);
								System.out.println("Added Successfully");
								break;
							case 2:
								// Eat the new line
	    						sc.nextLine();
								System.out.println("Enter first name:");
								tmp1= sc.nextLine();
								System.out.println("Enter last name:");
								tmp2= sc.nextLine();

								pointers.get(curr_ab_idx).deletePerson(tmp1,tmp2);
								System.out.println("Deleted Successfully");
								break;
							case 3:
								String ans;
								// Eat the new line
	    						sc.nextLine();
								System.out.println("Enter first name:");
								tmp1= sc.nextLine();
								System.out.println("Enter last name:");
								tmp2= sc.nextLine();

								int idx = pointers.get(curr_ab_idx).findIndex(tmp1,tmp2);
								if(idx==-1){
									System.out.println("Entry not found!");
									break;
								}

								System.out.println("Do you want to edit address? (y/n)");
								ans= sc.nextLine();
								if(ans.equals("y")){
									System.out.println("Enter address:");
									tmp3= sc.nextLine();
									pointers.get(curr_ab_idx).editPerson(tmp3,"a",idx);
								}

								System.out.println("Do you want to edit city? (y/n)");
								ans= sc.nextLine();
								if(ans.equals("y")){
									System.out.println("Enter city:");
									tmp3= sc.nextLine();
									pointers.get(curr_ab_idx).editPerson(tmp3,"c",idx);
								}
								
								System.out.println("Do you want to edit state? (y/n)");
								ans= sc.nextLine();
								if(ans.equals("y")){
									System.out.println("Enter state:");
									tmp3= sc.nextLine();
									pointers.get(curr_ab_idx).editPerson(tmp3,"s",idx);
								}

								System.out.println("Do you want to edit phone no? (y/n)");
								ans= sc.nextLine();
								if(ans.equals("y")){
									System.out.println("Enter phone no:");
									tmp3= sc.nextLine();
									pointers.get(curr_ab_idx).editPerson(tmp3,"p",idx);
								}
								
								System.out.println("Do you want to edit zip? (y/n)");
								ans= sc.nextLine();
								if(ans.equals("y")){
									System.out.println("Enter zip:");
									tmp6= sc.nextInt();
									// Eat the new line
	    							sc.nextLine();

									pointers.get(curr_ab_idx).editPerson(tmp6,idx);
								}
								System.out.println("Edited Successfully");
								break;
							case 4:
								System.out.println("Sorted records by zip:");
								pointers.get(curr_ab_idx).SortbyZip();
								pointers.get(curr_ab_idx).PrintMailinglabelformat();
								System.out.println("Sorted by Zip Successfully");
								break;
							case 5:
								System.out.println("Sorted records by name:");
								pointers.get(curr_ab_idx).SortbyName();
								pointers.get(curr_ab_idx).PrintMailinglabelformat();
								System.out.println("Sorted by Lastname Successfully");
								break;
							case 6:
								System.out.println("Printing records in mailing label format:");
								pointers.get(curr_ab_idx).PrintMailinglabelformat();
								break;
							case 7:
								System.out.println("Quiting!");
								flag2=1;
								break;
							default:
								System.out.println("Invalid choice! Enter again.");

						}
					}
					break;
				default:
					System.out.println("Invalid choice!");
			}
		}
	}

	public static void main(String[] args) {
	    AddressBookApplication obj = new AddressBookApplication();
	    obj.diplayOptions();
  	}
  }