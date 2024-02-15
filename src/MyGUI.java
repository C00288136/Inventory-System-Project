    import javax.swing.JButton;
    import javax.swing.JFrame;
    import javax.swing.JOptionPane;
    import java.awt.FlowLayout;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    import java.sql.Connection;
    import java.sql.PreparedStatement;
    import java.sql.ResultSet;
    import java.sql.ResultSetMetaData;

    public class MyGUI extends JFrame implements ActionListener {

        private Connection connection; // Declare the connection at the class level

        // Constructor for GUI
        public MyGUI() {

            // Set up the frame
            setTitle("GUI with Buttons");
            setSize(300, 200);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(new FlowLayout(FlowLayout.CENTER)); // FlowLayout with center alignment

            // Create buttons
            JButton connectButton = new JButton("Connect");
            JButton disconnectButton = new JButton("Disconnect");
            JButton connectionStatusButton = new JButton("Show Connection Status");
            JButton showTableButton = new JButton("Show Table");

            // Add action listeners to buttons
            connectButton.addActionListener(this);
            disconnectButton.addActionListener(this);
            connectionStatusButton.addActionListener(this);
            showTableButton.addActionListener(this);

            // Add buttons to the frame
            add(connectButton);
            add(disconnectButton);
            add(connectionStatusButton);
            add(showTableButton);

            // Make the frame visible
            setVisible(true);
        }

        // ActionListener implementation for button clicks
        @Override
        public void actionPerformed(ActionEvent e) {
            String actionCommand = e.getActionCommand();

            // Perform different actions based on the button clicked
            switch (actionCommand) {
                case "Connect":
                    JOptionPane.showMessageDialog(this, "Connecting...");
                    // Add your connect logic here
                    connection = DatabaseConnector.connect(); // Initialize the connection
                    if (connection != null) {
                        JOptionPane.showMessageDialog(this, "Connected to the database!");
                    } else {
                        JOptionPane.showMessageDialog(this, "Connection failed.");
                    }
                    break;  
                case "Disconnect":
                    JOptionPane.showMessageDialog(this, "Disconnecting...");
                    DatabaseConnector.disconnect();
                    // Add your disconnect logic here
                    break;
                case "Show Connection Status":
                    // Add your show connection status logic here
                    break;
                case "Show Table":
                    ResultSet resultSet = null;
                    PreparedStatement pstat = null;
                    /*try {
                        pstat = connection.prepareStatement("SELECT * FROM employees;");
                        resultSet = pstat.executeQuery();
                        ResultSetMetaData metaData = resultSet.getMetaData();

                        StringBuilder displayText = new StringBuilder();

                        while(resultSet.next())
                        {
                            int empID = resultSet.getInt("Emp_ID");
                            String lastName = resultSet.getString("LastName");
                            String firstName = resultSet.getString("FirstName");
                            int age = resultSet.getInt("Age");
                            int phoneNo = resultSet.getInt("Phone_No");
                            String address = resultSet.getString("Address");

                            displayText.append("Emp_ID: ").append(empID).append(", LastName: ").append(lastName).append(", FirstName: ").append(firstName).append(", Age: ").append(age).append(", Phone_No: ").append(phoneNo).append(", Address: ").append(address).append("\n");
                        }

                        // Display the result in a JOptionPane
                        JOptionPane.showMessageDialog(null, displayText.toString(), "SQL Table Data", JOptionPane.INFORMATION_MESSAGE);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error accessing database", "Error", JOptionPane.ERROR_MESSAGE);
                    } */ // fucking shit!
                    break;
            }
        }

        // Main method to launch the application
        public static void main(String[] args) {
            new MyGUI();
        }
    }
