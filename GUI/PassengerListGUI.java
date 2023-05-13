package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Entity.Passenger;
import List.PassengerList;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PassengerListGUI extends JFrame implements ActionListener {

    private PassengerList passengerList;
    private JTable table;
    private DefaultTableModel model;
    private JTextField idField, nameField, contactField, emailField, searchField;
    private JComboBox<String> searchDropdown;
    private JPanel contentPane;

    public PassengerListGUI() {
        super("Passenger List");

        // Load passenger list from file
        passengerList = new PassengerList(100, "GUI/Resources/passengers.csv");
        passengerList.loadFromFile();

        // set the frame properties
        setTitle("Passenger List");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon("GUI/Resources/train.jpeg").getImage());

        // Create table model and table
        String[] columnNames = { "ID", "Name", "Contact", "Email" };
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setAutoCreateRowSorter(true);

        // Populate table with passengers
        for (Passenger passenger : passengerList.getAllPassengers()) {
            Object[] row = { passenger.getPassengerID(), passenger.getPassengerName(), passenger.getPassengerContact(),
                    passenger.getPassengerEmail() };
            model.addRow(row);
        }

        // Create input fields
        idField = new JTextField(10);
        nameField = new JTextField(10);
        contactField = new JTextField(10);
        emailField = new JTextField(10);

        // Create buttons
        JButton addButton = new JButton("Add");
        addButton.addActionListener(this);
        addButton.setBackground(new Color(0, 153, 0));
        addButton.setForeground(Color.WHITE);
        addButton.setFont(new Font("Arial", Font.BOLD, 14));
        JButton removeButton = new JButton("Remove");
        removeButton.addActionListener(this);
        removeButton.setBackground(new Color(204, 0, 0));
        removeButton.setForeground(Color.WHITE);
        removeButton.setFont(new Font("Arial", Font.BOLD, 14));
        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(this);
        updateButton.setBackground(new Color(0, 102, 204));
        updateButton.setForeground(Color.WHITE);
        updateButton.setFont(new Font("Arial", Font.BOLD, 14));
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(this);
        searchButton.setBackground(new Color(255, 153, 0));
        searchButton.setForeground(Color.WHITE);
        searchButton.setFont(new Font("Arial", Font.BOLD, 14));
        JButton backButton = new JButton("Go back to Dashboard");
        backButton.addActionListener(this);
        backButton.setBackground(new Color(255, 153, 0));
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Arial", Font.BOLD, 14));

        // Create dropdowns and search button
        String[] searchOptions = { "ID", "Name" };
        searchDropdown = new JComboBox<>(searchOptions);
        searchField = new JTextField(10);
        searchButton.addActionListener(this);

        // Create panel for input fields and buttons
        JPanel inputPanel = new JPanel(new GridLayout(3, 4, 10, 10));
        inputPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        inputPanel.setBackground(Color.WHITE);
        inputPanel.add(new JLabel("ID:"));
        inputPanel.add(idField);
        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Contact:"));
        inputPanel.add(contactField);
        inputPanel.add(new JLabel("Email:"));
        inputPanel.add(emailField);
        inputPanel.add(new JLabel("Search by:"));
        inputPanel.add(searchDropdown);
        inputPanel.add(new JLabel("Search text:"));
        inputPanel.add(searchField);
        inputPanel.add(searchButton);
        inputPanel.add(addButton);
        inputPanel.add(removeButton);
        inputPanel.add(updateButton);
        inputPanel.add(backButton);

        // Create content pane with background image
        contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon imageIcon = new ImageIcon("GUI/Resources/background.jpg");
                Image image = imageIcon.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        contentPane.setLayout(new BorderLayout());
        contentPane.add(new JScrollPane(table), BorderLayout.CENTER);
        contentPane.add(inputPanel, BorderLayout.SOUTH);

        // Add content pane to frame
        setContentPane(contentPane);

        setVisible(true);
    }

    public void updatePassenger(int passengerID, String passengerName, String passengerContact, String passengerEmail) {
        Passenger passenger = passengerList.getPassengerByID(passengerID);
        if (passenger != null) {
            passenger.setPassengerName(passengerName);
            passenger.setPassengerContact(passengerContact);
            passenger.setPassengerEmail(passengerEmail);
            passengerList.updatePassenger(passenger);
        } else {
            System.out.println("Passenger not found.");
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton button = (JButton) e.getSource();
            if (button.getText().equals("Search")) {
                String searchText = searchField.getText();
                if (searchDropdown.getSelectedItem().equals("ID")) {
                    int searchID = Integer.parseInt(searchText);
                    Passenger passenger = passengerList.getPassengerByID(searchID);
                    if (passenger != null) {
                        model.setRowCount(0);
                        Object[] row = { passenger.getPassengerID(), passenger.getPassengerName(),
                                passenger.getPassengerContact(), passenger.getPassengerEmail() };
                        model.addRow(row);
                    } else {
                        JOptionPane.showMessageDialog(contentPane, "Passenger with ID " + searchID + " not found.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else if (searchDropdown.getSelectedItem().equals("Name")) {
                    Passenger[] passengers = passengerList.getPassengersByName(searchText);
                    if (passengers != null && passengers.length > 0) {
                        model.setRowCount(0);
                        for (Passenger passenger : passengers) {
                            Object[] row = { passenger.getPassengerID(), passenger.getPassengerName(),
                                    passenger.getPassengerContact(), passenger.getPassengerEmail() };
                            model.addRow(row);
                        }
                    } else {
                        JOptionPane.showMessageDialog(contentPane, "Passenger with name " + searchText + " not found.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else if (button.getText().equals("Add")) {
                int id = Integer.parseInt(idField.getText());
                String name = nameField.getText();
                String contact = contactField.getText();
                String email = emailField.getText();
                Passenger passenger = new Passenger(id, name, contact, email);
                if (passengerList.getPassengerByID(id) == null) {
                    passengerList.addPassenger(passenger);
                    passengerList.saveToFile();
                    Object[] row = { id, name, contact, email };
                    model.addRow(row);
                    clearFields();
                } else {
                    JOptionPane.showMessageDialog(contentPane, "Passenger with ID " + id + " already exists.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    clearFields();
                }
            } else if (button.getText().equals("Remove")) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    int id = (int) model.getValueAt(selectedRow, 0);
                    Passenger passenger = passengerList.getPassengerByID(id);
                    passengerList.removePassenger(passenger);
                    model.removeRow(selectedRow);
                    clearFields();
                }
            } else if (button.getText().equals("Update")) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    int id = (int) model.getValueAt(selectedRow, 0);
                    Passenger passenger = passengerList.getPassengerByID(id);
                    String name = nameField.getText();
                    String contact = contactField.getText();
                    String email = emailField.getText();
                    passenger.setPassengerName(name);
                    passenger.setPassengerContact(contact);
                    passenger.setPassengerEmail(email);
                    passengerList.updatePassenger(passenger);
                    model.setValueAt(name, selectedRow, 1);
                    model.setValueAt(contact, selectedRow, 2);
                    model.setValueAt(email, selectedRow, 3);
                    clearFields();
                }
            } else if (button.getText().equals("Go back to Dashboard")) {
                dispose();
                DashboardGUI dashboardGUI = new DashboardGUI();
                dashboardGUI.setVisible(true);
            }
        }
    }

    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        contactField.setText("");
        emailField.setText("");
    }

    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // Use default look and feel
        }
        PassengerListGUI passengerListGUI = new PassengerListGUI();
        passengerListGUI.setVisible(true);
    }
}