package business;

//person class
public class Person {

    private int recordNumber;
    private String firstName, lastName, phone;
    public static int age;

    // constants representing the maximum allowed size of each field
    public static final int FIRST_NAME_MAX = 20;
    public static final int LAST_NAME_MAX = 25;
    public static final int PHONE_MAX = 10;


    // parameterized constructor to create a new Person object with given values
    public Person(int recordNumber, String firstName, String lastName, String phone, int age) {
        this.recordNumber = recordNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.age = age;
    }

    // default constructor to create a new empty Person object
    public Person() {
        this.recordNumber = 0;
        this.firstName = "";
        this.lastName = "";
        this.phone = "";
        this.age = 0;
    }


    // getters and setters for all fields
    public int getRecordNumber() {
        return recordNumber;
    }

    public void setRecordNumber(int recordNumber) {
        this.recordNumber = recordNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}