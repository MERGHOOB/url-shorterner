package LowLevelDesign;
import java.util.*;

/*
 * System Requirements:

	1) System will support the renting of different automobiles like cars, trucks, SUVs, vans, and motorcycles.

	2) Each vehicle should be added with a unique barcode and other details, including a parking stall number
	*which helps to locate the vehicle.

	3) System should be able to retrieve information like which member took a particular vehicle
	or what vehicles have been rented out by a specific member.

	4) System should collect a late-fee for vehicles returned after the due date.

	5) Members should be able to search the vehicle inventory and reserve any available vehicle.

	6) The system should be able to send notifications whenever the reservation is approaching the pick-up date,
	*  as well as when the vehicle is nearing the due date or has not been returned within the due date.

	7) The system will be able to read barcodes from vehicles.

	8) Members should be able to cancel their reservations.

	9) The system should maintain a vehicle log to track all events related to the vehicles.

	10) Members can add rental insurance to their reservation.

	11) Members can rent additional equipment, like navigation, child seat, ski rack, etc.

	12) Members can add additional services to their reservation, such as roadside assistance, additional driver, wifi, etc.
 *
 * */



public class VehicleRentalApplication {



    enum VehicleType{
        CAR, TRUCK, VAN, MOTORCYCLE
    }

    enum ReservationStatus{
        BOOKED, WAITING
    }

    enum VehicleCondition {
        SERVICING, ACCIDENT, OIL_CHANGE, OTHER
    }

    enum PaymentStatus {
        UNPAID, PENDING, PAID, CANCELLED, FAILED
    }

    enum AccountStatus{
        ACTIVE, INACTIVE
    }

    enum RentalInsurance{
        MONTH, WEEK, YEAR
    }

    enum Equipments{
        CHILD_SEAT, NAVIGATION, SKI_RACK
    }



    abstract class Person{
        private String firstName;
        private String lastName;
        private String email;
        private String phoneNo;
        private Address address;
    }

    class Address{
        private String street;
        private String state;
        private String country;
        private int zipcode;
    }

    abstract class Account{
        private int id;
        private String userName;
        private AccountStatus accountStatus;
        private Person person;

        abstract public boolean resetPassword();
    }

    class AdditionalDriver extends Person{
        private String licenseNumber;
        private int driverId;
    }

    class Member extends Account{
        private int totalBookings;
        private VehicleReservation vehicleReservation;
        Search searchTheVehicle;

        public List<VehicleReservation> getAllTheBookings(){return null;}


        public boolean resetPassword() {return false;}
    }

    class Vehicle{
        private String nameOfVehicle;
        private String vehicleNumber;
        private VehicleCondition vehicleStatus;
        private String barCode;
        private VehicleType vehicleType;
    }

    class VehicleLog{
        private Vehicle vehicle;
        private String description;
    }

    class VehicleReservation {
        private int reservationId;
        private Member bookedBy;
        private ReservationStatus reservationStatus;
        private Date dateOfBooking;
        private Date dateOfReturning;
        private Date dueDate;
        private VehicleLocation vehicleBookedLocation;
        private VehicleLocation vehicleReturnedLocation;
        private List<VehicleLog> vehicleCondition;
        private List<RentalInsurance> rentalInsurance;
        private Bill bill;

        private VehicleReservation getTheReservationDetail(int reservationId) {return null;}
        private boolean cancelReservation(int reservationId) {return false;}
        private boolean returnVehicle(int reservationId) {return false;}
        private boolean bookVehicle(Vehicle vehicle) {return false;}
        private List<AdditionalDriver> additionalDrivers;
        private List<Equipments> equipments;

    }

    class Bill{
        private int billNo;
        private VehicleReservation vehicleReservation;
        private Date dateOfGeneration;
        private int totalAmount;
        private PaymentStatus paymentStatus;
    }


    class VehicleLocation {
        private String name;
        private int parkingSlotNo;
        private Address location;
        private Address getLocation(){return null;}
    }


    class System{
        public Bill calculateTheAmount(int reservationId) {return null;}
        public Member vehicleLocator(String vehicleNumber) {return null;}
        public List<Vehicle> numberOfVehicleReservedByMember(Member member){return null;}
        public boolean sendNotification() {return false;}
    }


    interface Search {
        public List<Vehicle> searchByType(VehicleType type);
        public List<Vehicle> searchByModel(String model);
    }

    class VehicleInventory implements Search {
        private HashMap<String, List<Vehicle>> vehicleTypes;
        private HashMap<String, List<Vehicle>> vehicleModels;

        public List<Vehicle> searchByType(VehicleType vehicleType) {
            return vehicleTypes.get(vehicleType);
        }

        public List<Vehicle> searchByModel(String query) {
            return vehicleModels.get(query);
        }

    }


}

