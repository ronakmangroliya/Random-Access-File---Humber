package presentation;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import business.Person;
import data.RandomIO;
import static javax.swing.JOptionPane.showMessageDialog;


// Gui class
public class PersonGUI extends JFrame implements ActionListener {

    // variables declaration
    JFrame frame;
    JPanel panel;
    private final JTextField recordNumField, firstNameField, lastNameField, ageField, phoneField;
    private final JButton addBtn, findBtn;

    public PersonGUI() {

        // creating a main frame
        frame = new JFrame("Random File Processing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(380, 350);
        frame.setLocationRelativeTo(null);

        // creating a main panel
        panel = new JPanel();

        // setting a main panel layout to the grid with six row and two column
        panel.setLayout(new GridLayout(6, 2, 15, 15));

        //creating empty border around the panel to create a some margin
        panel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        // creating all component with label and textField and adding into our main panel
        panel.add(new JLabel("Record #"));
        recordNumField = new JTextField(10);
        panel.add(recordNumField);

        panel.add(new JLabel("First Name"));
        firstNameField = new JTextField(20);
        panel.add(firstNameField);

        panel.add(new JLabel("Last Name"));
        lastNameField = new JTextField(25);
        panel.add(lastNameField);

        panel.add(new JLabel("Age"));
        ageField = new JTextField(3);
        panel.add(ageField);

        panel.add(new JLabel("Phone"));
        phoneField = new JTextField(10);
        panel.add(phoneField);

        //creating a button and click event
        addBtn = new JButton("Add");
        addBtn.addActionListener(this);
        panel.add(addBtn);

        findBtn = new JButton("Find");
        findBtn.addActionListener(this);
        panel.add(findBtn);

        // adding panel into the main frame
        frame.add(panel);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    // click event handling for Add and Find button
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addBtn) {
            try {
                // get data from textField and store into variables
                int recordNum = Integer.parseInt(recordNumField.getText());
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String phone = phoneField.getText();
                int age = Integer.parseInt(ageField.getText());

                // creating a person object from user given data
                Person person = new Person(recordNum, firstName, lastName, phone, age);

                //checking for size
                if (firstName.length()>Person.FIRST_NAME_MAX || lastName.length()>Person.LAST_NAME_MAX || phone.length()>Person.PHONE_MAX){
                    showMessageDialog(null, "Max length for firstname & lastname: 20 and phone number: 10");
                }
                else {
                    // calling a addPerson method from RandomIO class to add data into the binary file
                    RandomIO.addPerson(person);

                    JOptionPane.showMessageDialog(this, "Person added to file.");
                    // clear all field after add to file
                    clearMyAllFields();
                }

            }   catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input or Empty Field Not Allowed");
            }   catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }

        } else if (e.getSource() == findBtn) {
            try {

                // get data from record# textField and store into variables
                int recordNum = Integer.parseInt(recordNumField.getText());

                //  calling a findPerson method from RandomIO class to find specific record number from the binary file
                Person person = RandomIO.findPerson(recordNum);

                if (person == null) {
                    JOptionPane.showMessageDialog(this, "No person found with record number " + recordNum + ".");
                } else {
                    // if record number match get the data from object and set into textField
                    firstNameField.setText(person.getFirstName());
                    lastNameField.setText(person.getLastName());
                    phoneField.setText(person.getPhone());
                    ageField.setText(Integer.toString(person.getAge()));
                }
            }   catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid or Empty Field for Record # not allowed");
            }   catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        }
    }

    // Method for clear the textField
    private void clearMyAllFields() {
        recordNumField.setText("");
        firstNameField.setText("");
        lastNameField.setText("");
        ageField.setText("");
        phoneField.setText("");
    }

    public static void main(String[] args) {
        new PersonGUI();
    }
}