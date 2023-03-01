package data;

import java.io.*;
import business.Person;

public class RandomIO {

    private static final String MY_FILE_NAME = "src/people.dat";

    // Method to add person into the binary file.
    public static void addPerson(Person person) throws IOException {

        // Open the binary file for writing
        RandomAccessFile raFile = new RandomAccessFile(MY_FILE_NAME, "rw");

        // Position the file pointer to the end of the file to add new record at the end of the file.
        raFile.seek(raFile.length());

        // Write the person record to the file
        raFile.writeInt(person.getRecordNumber());
        writePaddedString(raFile, person.getFirstName(), Person.FIRST_NAME_MAX);
        writePaddedString(raFile, person.getLastName(), Person.LAST_NAME_MAX);
        writePaddedString(raFile, person.getPhone(), Person.PHONE_MAX);
        raFile.writeInt(person.getAge());
        raFile.close();
    }

    // Method to find person from the binary file.
    public static Person findPerson(int recordNumber) throws IOException {

        // Open the binary file for reading
        RandomAccessFile raFile = new RandomAccessFile(MY_FILE_NAME, "r");

        // Search for the record with the specified record number
        while (raFile.getFilePointer() < raFile.length()) {

            // Read the record number and fields of the all current record
            int currentRecordNumber = raFile.readInt();
            String firstName = readPaddedString(raFile, Person.FIRST_NAME_MAX);
            String lastName = readPaddedString(raFile, Person.LAST_NAME_MAX);
            String phone = readPaddedString(raFile, Person.PHONE_MAX);
            int age = raFile.readInt();

            // If the current record matches the specified record number,
            // create a new Person object with the data from the binary file
            if (currentRecordNumber == recordNumber) {
                Person person = new Person();
                person.setRecordNumber(currentRecordNumber);
                person.setFirstName(firstName);
                person.setLastName(lastName);
                person.setPhone(phone);
                person.setAge(age);

                // Close the file and return the Person object
                raFile.close();
                return person;
            }

        }
        // If no record found, close the file and return null
        raFile.close();
        return null;
    }

    // Method for write a string to a RandomAccessFile with a specified maximum size,
    // padding the remaining space with null bytes if the string is shorter than the maximum size.
    private static void writePaddedString(RandomAccessFile raFile, String str, int maxSize) throws IOException {
        byte[] bytes = str.getBytes("UTF-8");
        raFile.write(bytes, 0, Math.min(bytes.length, maxSize));
        for (int i = bytes.length; i < maxSize; i++) {
            raFile.writeByte(0);
        }
    }

    // Method for read a string from a RandomAccessFile with a specified maximum size
    private static String readPaddedString(RandomAccessFile raFile, int maxSize) throws IOException {
        byte[] bytes = new byte[maxSize];
        raFile.read(bytes);
        return new String(bytes, "UTF-8").trim();
    }
}