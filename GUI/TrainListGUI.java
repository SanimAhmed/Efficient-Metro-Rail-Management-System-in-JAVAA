package GUI;

import Entity.Train;
import List.TrainList;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TrainListGUI extends JFrame implements ActionListener, TableModelListener {

    private TrainList trainList;
    private DefaultTableModel model;
    private JTable table;
    private JTextField idField, nameField, sourceField, destinationField, departureField, arrivalField, searchField;
    private JComboBox<String> searchDropdown;
    private JButton searchButton, addButton, removeButton, updateButton, backButton;
    private JPanel inputPanel, contentPane;

    // Font size adjustment
    private Font labelFont = new Font("Arial", Font.PLAIN, 18);
    private Font fieldFont = new Font("Arial", Font.PLAIN, 16);
    private Font buttonFont = new Font("Arial", Font.BOLD, 18);
    private Font tableFont = new Font("Arial", Font.PLAIN, 18);
    private Font comboFont = new Font("Arial", Font.PLAIN, 16);

    public TrainListGUI() {
        // Load train data from file
        trainList = new TrainList(100, "GUI/Resources/trainlist.txt");
        trainList.loadFromFile();

        // Set the frame properties
        setTitle("Train List");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon("GUI/Resources/train.jpeg").getImage());

        // Create table model and table
        String[] columnNames = { "ID", "Name", "Source", "Destination", "Departure Time", "Arrival Time" };
        model = new DefaultTableModel(columnNames, 0);
        model.addTableModelListener(this);
        table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        table.setAutoCreateRowSorter(true);

        // Adjust font and row height for table
        table.setFont(tableFont);
        table.setRowHeight(30);
        // Populate table with train data
        for (Train train : trainList.getAllTrains()) {
            Object[] row = { train.getTransportID(), train.getTransportName(), train.getSource(),
                    train.getDestination(), train.getDepartureTime(), train.getArrivalTime() };
            model.addRow(row);
        }

        // Create input panel with text fields and buttons
        idField = new JTextField(8);
        idField.setFont(fieldFont);
        nameField = new JTextField(8);
        nameField.setFont(fieldFont);
        sourceField = new JTextField(8);
        sourceField.setFont(fieldFont);
        destinationField = new JTextField(8);
        destinationField.setFont(fieldFont);
        departureField = new JTextField(8);
        departureField.setFont(fieldFont);
        arrivalField = new JTextField(8);
        arrivalField.setFont(fieldFont);
        searchField = new JTextField(8);
        searchField.setFont(fieldFont);
        searchDropdown = new JComboBox<>(new String[] { "ID", "Name" });
        searchDropdown.setFont(comboFont);

        // Buttons

        searchButton = new JButton("Search");
        searchButton.setFont(buttonFont);
        addButton = new JButton("Add");
        addButton.setFont(buttonFont);
        removeButton = new JButton("Remove");
        removeButton.setFont(buttonFont);
        updateButton = new JButton("Update");
        updateButton.setFont(buttonFont);
        backButton = new JButton("Go back to Dashboard");
        backButton.setFont(buttonFont);

        // Set button colors
        searchButton.setBackground(Color.BLUE);
        searchButton.setForeground(Color.WHITE);
        addButton.setBackground(Color.GREEN);
        addButton.setForeground(Color.WHITE);
        removeButton.setBackground(Color.RED);
        removeButton.setForeground(Color.WHITE);
        updateButton.setBackground(Color.ORANGE);
        updateButton.setForeground(Color.WHITE);
        backButton.setBackground(Color.GRAY);
        backButton.setForeground(Color.WHITE);

        searchButton.addActionListener(this);
        addButton.addActionListener(this);
        removeButton.addActionListener(this);
        updateButton.addActionListener(this);
        backButton.addActionListener(this);

        inputPanel = new JPanel(new GridLayout(4, 3, 10, 10));
        inputPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        inputPanel.setBackground(Color.WHITE);

        inputPanel.add(createLabel("ID:"));
        inputPanel.add(idField);
        inputPanel.add(createLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(createLabel("Source:"));
        inputPanel.add(sourceField);
        inputPanel.add(createLabel("Destination:"));
        inputPanel.add(destinationField);
        inputPanel.add(createLabel("Departure Time:"));
        inputPanel.add(departureField);
        inputPanel.add(createLabel("Arrival Time:"));
        inputPanel.add(arrivalField);
        inputPanel.add(createLabel("Search by:"));
        inputPanel.add(searchDropdown);
        inputPanel.add(createLabel("Search text:"));
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

        // Set frame properties
        setResizable(true);
        setVisible(true);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(labelFont);
        label.setBorder(new EmptyBorder(0, 0, 5, 5));
        return label;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton button = (JButton) e.getSource();
            if (button.getText().equals("Search")) {
                String searchText = searchField.getText();
                if (searchDropdown.getSelectedItem().equals("ID")) {
                    int searchID = Integer.parseInt(searchText);
                    Train train = trainList.getTrainByID(searchID);
                    if (train != null) {
                        model.setRowCount(0);
                        Object[] row = { train.getTransportID(), train.getTransportName(), train.getSource(),
                                train.getDestination(), train.getDepartureTime(), train.getArrivalTime() };
                        model.addRow(row);
                    } else {
                        JOptionPane.showMessageDialog(contentPane, "Train with ID " + searchID + " not found.", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } else if (searchDropdown.getSelectedItem().equals("Name")) {
                    Train[] trains = trainList.getTrainsByName(searchText);
                    if (trains != null && trains.length > 0) {
                        model.setRowCount(0);
                        for (Train train : trains) {
                            Object[] row = { train.getTransportID(), train.getTransportName(), train.getSource(),
                                    train.getDestination(), train.getDepartureTime(), train.getArrivalTime() };
                            model.addRow(row);
                        }
                    } else {
                        JOptionPane.showMessageDialog(contentPane, "Train with name " + searchText + " not found.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
            // Inside the addButton ActionListener
            else if (button.getText().equals("Add")) {
                int id = Integer.parseInt(idField.getText());
                String name = nameField.getText();
                String source = sourceField.getText();
                String destination = destinationField.getText();
                String departureTime = departureField.getText();
                String arrivalTime = arrivalField.getText();
                Train train = new Train(id, name, source, destination, departureTime, arrivalTime);

                if (trainList.getTrainByID(id) == null) {
                    trainList.addTrain(train);
                    trainList.saveToFile();
                    Object[] row = { id, name, source, destination, departureTime, arrivalTime };
                    model.addRow(row);
                    clearFields();
                } else {
                    JOptionPane.showMessageDialog(contentPane, "Train with ID " + id + " already exists.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    clearFields();
                }
            } else if (button.getText().equals("Remove")) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    int id = (int) model.getValueAt(selectedRow, 0);
                    Train train = trainList.getTrainByID(id);
                    trainList.removeTrain(train);
                    trainList.saveToFile();
                    model.removeRow(selectedRow);
                    clearFields();
                }
            } else if (button.getText().equals("Update")) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    int id = (int) model.getValueAt(selectedRow, 0);
                    Train train = trainList.getTrainByID(id);
                    if (train != null) {
                        String name = nameField.getText();
                        String source = sourceField.getText();
                        String destination = destinationField.getText();
                        String departureTime = departureField.getText();
                        String arrivalTime = arrivalField.getText();

                        if (!name.equals("")) {
                            train.setTransportName(name);
                            model.setValueAt(name, selectedRow, 1);
                        }
                        if (!source.equals("")) {
                            train.setSource(source);
                            model.setValueAt(source, selectedRow, 2);
                        }
                        if (!destination.equals("")) {
                            train.setDestination(destination);
                            model.setValueAt(destination, selectedRow, 3);
                        }
                        if (!departureTime.equals("")) {
                            train.setDepartureTime(departureTime);
                            model.setValueAt(departureTime, selectedRow, 4);
                        }
                        if (!arrivalTime.equals("")) {
                            train.setArrivalTime(arrivalTime);
                            model.setValueAt(arrivalTime, selectedRow, 5);
                        }
                        trainList.saveToFile(); // Save the updated train information to file
                        clearFields();
                    } else {
                        JOptionPane.showMessageDialog(contentPane, "Train with ID " + id + " not found.", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

            else if (button.getText().equals("Go back to Dashboard")) {
                dispose();
                new DashboardGUI();
            }

        }
    }

    public void clearFields() {
        idField.setText("");
        nameField.setText("");
        sourceField.setText("");
        destinationField.setText("");
        departureField.setText("");
        arrivalField.setText("");
        searchField.setText("");
        searchDropdown.setSelectedIndex(0);
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        if (e.getType() == TableModelEvent.UPDATE) {
            int row = e.getFirstRow();
            int column = e.getColumn();

            int id = (int) model.getValueAt(row, 0);
            String value = (String) model.getValueAt(row, column);
            Train train = trainList.getTrainByID(id);

            if (train != null) {
                switch (column) {
                    case 1:
                        train.setTransportName(value);
                        break;
                    case 2:
                        train.setSource(value);
                        break;
                    case 3:
                        train.setDestination(value);
                        break;
                    case 4:
                        train.setDepartureTime(value);
                        break;
                    case 5:
                        train.setArrivalTime(value);
                        break;
                }
                // Assume you have a method called updateTrain in your trainList class
                trainList.updateTrain(train);
            }
        }
    }

    public static void main(String[] args) {
        // Set look and feel to Nimbus
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

        // Run the GUI
        TrainListGUI trainListGUI = new TrainListGUI();
        trainListGUI.setVisible(true);
    }

}
