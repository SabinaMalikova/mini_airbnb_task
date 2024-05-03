package peaksoft;

import peaksoft.config.HibernateConfig;
import peaksoft.entity.*;
import peaksoft.enums.FamilyStatus;
import peaksoft.enums.Gender;
import peaksoft.enums.HouseType;
import peaksoft.service.*;
import peaksoft.service.serviceImpl.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        AgencyService agencyService = new AgencyServiceImpl();
        AddressService addressService = new AddressServiceImpl();
        CustomerService customerService = new CustomerServiceImpl();
        HouseService houseService = new HouseServiceImpl();
        OwnerService ownerService = new OwnerServiceImpl();
        Rent_info_Service rentInfoService = new Rent_info_ServiceImpl();

       while (true){
           System.out.println("""
                   1. save agency with address
                   2. get All Agency
                   3. get agency by Id 
                   4. update agency
                   5. delete agency 
                   ------------------
                   6. get Address By Id
                   7. get All Address With Agency
                   8. get Count Agencies By City
                   9. get All Region With Agency
                   10. update address
                   ------------------
                   11. save customer
                   12. save Customer With Rent-Info
                   13. get All Customers
                   14. get Customer By Id
                   15. update Customer
                   16. delete Customer
                   ------------------
                   17. save Owner
                   18. save Owner With House
                   19. assign Owner To Agency
                   20. delete owner
                   21. get Owner By Agency Id
                   22. get All Owners
                   23. update Owner
                   -------------------
                   24. save House With Assign To Owner
                   25. delete house
                   26. get All Houses By Region
                   27. get All House By Agency Id
                   28. get All Houses By Owner Id
                   29. get All Houses Between 2 check in Dates
                   30. get House By Id
                   31. update House
                   """);
           switch (new Scanner(System.in).nextLine()){
               case "1" -> {
                   Agency agency1 = new Agency("Peaksoft", 9965005005000L);
                   Address address1 = new Address("Bishkek","Chui","Vostok-5");

                   Agency agency2 = new Agency("Codify", 996700700700L);
                   Address address2 = new Address("Bishkek", "Chui","Turusbekova");

                   System.out.println(agencyService.saveAgency(agency1, address1));
                   System.out.println(agencyService.saveAgency(agency2, address2));
               }
               case "2" -> {
                   System.out.println(agencyService.getAllAgency());
               }
               case "3" -> {
                   System.out.println(agencyService.getAgencyById(5L));
               }
               case "4" -> {
                   Agency agency = new Agency("Microsoft",996800800800L);
                   System.out.println(agencyService.updateAgency(3L,agency));
               }
               case "5" -> {
                   System.out.println(agencyService.deleteAgency(4L));
               }
               case "6" -> {
                   System.out.println(addressService.getAddressById(5L));
               }
               case "7" -> {
                   System.out.println(addressService.getAllAddressWithAgency());
               }
               case "8" -> {
                   System.out.println(addressService.getCountAgenciesByCity("Bishkek"));
               }
               case "9" -> {
                   System.out.println(addressService.getAllRegionWithAgency());
               }
               case "10" -> {
                   Address address = new Address("Moscow", "Arbat","Karamelhui");
                   System.out.println(addressService.updateAddress(3L, address));
               }
               case "11" -> {
                   Customer customer1 = new Customer("Anna","Andreeva","anna@gmail.com", LocalDate.of(1999,12,12), Gender.FEMALE,"russian", FamilyStatus.SINGLE);
                   Customer customer2 = new Customer("Sasha","Shieva","sasha@gmail.com", LocalDate.of(1995,11,11), Gender.FEMALE,"russian", FamilyStatus.MARRIED);

                   System.out.println(customerService.saveCustomer(customer1));
                   System.out.println(customerService.saveCustomer(customer2));
               }
               case "12" -> {
                   Customer customer = new Customer("Garry", "Karaev", "garry@gmail.com", LocalDate.of(2000,10,10),Gender.MALE,"german",FamilyStatus.SINGLE);
                   RentInfo rentInfo = new RentInfo(LocalDate.of(2024,1,5), LocalDate.of(2024,1,6));
                   System.out.println(customerService.saveCustomerWithRentInfo(customer,1L,3L,LocalDate.of(2023,3,3),LocalDate.now()));
               }
               case "13" -> {
                   System.out.println(customerService.getAllCustomers());
               }
               case "14" -> {
                   System.out.println(customerService.getCustomerById(1L));
               }
               case "15" -> {
                   Customer customer = new Customer("Zarina", "Ivanova", "Zarina@gmail.com",LocalDate.of(2004,5,5),Gender.FEMALE,"ukraine",FamilyStatus.SINGLE);
                   System.out.println(customerService.updateCustomer(1L, customer));
               }
               case "16" -> {
                   System.out.println(customerService.deleteCustomer(1L));
               }
               case "17" -> {
                   Owner owner = new Owner("Ulan","Karaev","ulan@gmail.com",LocalDate.of(2002,4,4),Gender.MALE);
                   System.out.println(ownerService.saveOwner(owner));
               }
               case "18" -> {
                   Owner owner1 = new Owner("Sezim","Nurlanova","sezim@gmail.com",LocalDate.of(2005,3,3),Gender.FEMALE);
                   House house1 = new House(HouseType.APARTMENT, new BigDecimal(250.00000), 10, "perfecto", 4, true, new Address("Seoul", "Gangnam","dda1"));
                   Owner owner2 = new Owner("Artem","Asanov","artem@gmail.com",LocalDate.of(2002,2,2),Gender.MALE);
                   House house2 = new House(HouseType.HOUSE, new BigDecimal(550.00000), 9, "perfecto", 18, false,new Address("Seoul", "Gangnam","dda2"));

                   System.out.println(ownerService.saveOwnerWithHouse(owner1,house1));
                   System.out.println(ownerService.saveOwnerWithHouse(owner2, house2));
               }
               case "19" -> {
                   System.out.println(ownerService.assignOwnerToAgency(1L, 1L));
               }
               case "20" -> {
                   System.out.println(ownerService.deleteOwner(2L));
               }
               case "21" -> {
                   System.out.println(ownerService.getOwnerByAgencyId(1L));
               }
               case "22" -> {
                   System.out.println(ownerService.getAllOwners());
               }
               case "23" -> {
                   Owner owner = new Owner("Aiza","Asanova","aiza@gmail.com",LocalDate.of(2003,2,2),Gender.FEMALE);
                   System.out.println(ownerService.updateOwner(1L, owner));
               }
               case "24" -> {
                   House house = new House(HouseType.HOUSE, new BigDecimal(450.00000), 8, "good", 7, false,
                           new Address("Tokio", "Akihabara","ooo"),
                           new RentInfo(LocalDate.of(2024,1,5),LocalDate.of(2024,1,6)));
                   System.out.println(houseService.saveHouseWithAssignToOwner(house, 1L));
               }
               case "25" -> {
                   System.out.println(houseService.deleteHouse(1L));
               }
               case "26" -> {
                   System.out.println(houseService.getAllHousesByRegion("Gangnam"));
               }
               case "27" -> {
                   System.out.println(houseService.getAllHouseByAgencyId(2L));
               }
               case "28" -> {
                   System.out.println(houseService.getAllHousesByOwnerId(2L));
               }
               case "29" -> {
                   System.out.println(houseService.getAllHousesBetweenDates(LocalDate.of(2024, 1, 5), LocalDate.of(2024, 1, 6)));
               }
               case "30" -> {
                   System.out.println(houseService.getHouseById(2L));
               }
               case "31" -> {

//                   houseService.updateHouse()
               }


           }
       }
    }
}
