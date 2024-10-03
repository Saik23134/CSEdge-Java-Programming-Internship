import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATMApplication {
    private JFrame frame;
    private JPanel panel;
    private JTextField amountField, accountField, messageField;
    private JButton withdrawButton, depositButton, transferButton, queryButton;
    int bal=10000,temp;
    
    public ATMApplication() {
        frame = new JFrame("ATM Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        
        panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));
        
        // Creating labels and text fields
        panel.add(new JLabel("Account Number:"));
        accountField = new JTextField();
        panel.add(accountField);
        
        panel.add(new JLabel("Amount:"));
        amountField = new JTextField();
        panel.add(amountField);
        
        withdrawButton = new JButton("Withdraw");
        depositButton = new JButton("Deposit");
        transferButton = new JButton("Transfer");
        queryButton = new JButton("Query");
        
        // Adding buttons to the panel
        panel.add(withdrawButton);
        panel.add(depositButton);
        panel.add(transferButton);
        panel.add(queryButton);
        
        // Message field to display results
        messageField = new JTextField();
        messageField.setEditable(false);
        panel.add(messageField);
        
        frame.add(panel);
        
        // Action listeners
        withdrawButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Withdrawal();
            }
        });
        
        depositButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Deposit();
            }
        });
        
        transferButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Transfer();
            }
        });
        
        queryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Query();
            }
        });

        frame.setVisible(true);
    }
    
    private void Withdrawal() {
        String account = accountField.getText();
        String amountText = amountField.getText();
        // Logic for withdrawal would go here
        messageField.setText("Withdrawn " + amountText + " from account " + account);
        temp=Integer.parseInt(amountText);
        bal-=temp;
    }
    
    private void Deposit() {
        String account = accountField.getText();
        String amountText = amountField.getText();
        // Logic for deposit would go here
        messageField.setText("Deposited " + amountText + " to account " + account);
        temp=Integer.parseInt(amountText);
        bal+=temp;
    }
    
    private void Transfer() {
        String account = accountField.getText();
        String amountText = amountField.getText();
        // Logic for fund transfer would go here
        messageField.setText("Transferred " + amountText + " from account " + account);
        temp=Integer.parseInt(amountText);
        bal-=temp;
        
    }
    
    private void Query() {
        String account = accountField.getText();
        // Logic for account query would go here
        messageField.setText("Account balance for " + account + " is $"+bal); 
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ATMApplication());
    }
}
