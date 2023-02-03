import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.SpringLayout;

public class Main {
    private static JFrame frame;
    private static SpringLayout layout = new SpringLayout();
    private static JButton insertCoin = new JButton("Insert Coin");
    private static JComboBox<Product> products;
    private static JList<Product> pending = new JList<>();
    private static JLabel pendingLabel = new JLabel("Pending Purchase");
    private static JLabel currentBalance = new JLabel("Balance: $0.00");
    private static JButton cancel = new JButton("Cancel");
    private static JButton confirm = new JButton("Confirm");
    private static JList<Product> bought = new JList<>();
    private static JLabel boughtLabel = new JLabel("Purchased Products");
    private static JButton reset = new JButton("Reset");

    private static double balance = 0.0d;

    public static void main(String[] args) {
        frame = new JFrame();
        frame.setTitle("Vending Machine");
        frame.setLayout(layout);
        initComponents();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        layout.putConstraint(SpringLayout.EAST, frame.getContentPane(), 5, SpringLayout.EAST, bought);
        layout.putConstraint(SpringLayout.SOUTH, frame.getContentPane(), 5, SpringLayout.SOUTH, bought);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void initComponents() {
        insertCoin.addActionListener(e -> {
            Coin coin = (Coin) JOptionPane.showInputDialog(frame, "Select a coin to insert:", "Select Coin",
                    JOptionPane.PLAIN_MESSAGE, null, Coin.values(), null);
            if(coin != null) {
                balance += coin.value;
                updateBalance();
            }
        });
        insertCoin.setPreferredSize(new Dimension(100, 30));
        layout.putConstraint(SpringLayout.NORTH, insertCoin, 5, SpringLayout.NORTH, frame.getContentPane());
        layout.putConstraint(SpringLayout.WEST, insertCoin, 5, SpringLayout.WEST, frame.getContentPane());
        frame.add(insertCoin);
        Product[] productsArray = new Product[Product.products.size()];
        products = new JComboBox<Product>(Product.products.toArray(productsArray));
        products.setPreferredSize(new Dimension(200, 30));
        layout.putConstraint(SpringLayout.NORTH, products, 5, SpringLayout.NORTH, frame.getContentPane());
        layout.putConstraint(SpringLayout.WEST, products, 10, SpringLayout.EAST, insertCoin);
        layout.putConstraint(SpringLayout.EAST, products, 0, SpringLayout.EAST, cancel);
        frame.add(products);
        reset.setPreferredSize(new Dimension(100, 30));
        reset.addActionListener(e -> {
            reset();
        });
        layout.putConstraint(SpringLayout.NORTH, reset, 5, SpringLayout.NORTH, frame.getContentPane());
        layout.putConstraint(SpringLayout.EAST, reset, 0, SpringLayout.EAST, pending);
        frame.add(reset);
        pendingLabel.setPreferredSize(new Dimension(150, 30));
        layout.putConstraint(SpringLayout.NORTH, pendingLabel, 5, SpringLayout.SOUTH, insertCoin);
        layout.putConstraint(SpringLayout.WEST, pendingLabel, 5, SpringLayout.WEST, frame.getContentPane());
        frame.add(pendingLabel);
        pending.setPreferredSize(new Dimension(500, 200));
        layout.putConstraint(SpringLayout.NORTH, pending, 5, SpringLayout.SOUTH, pendingLabel);
        layout.putConstraint(SpringLayout.WEST, pending, 5, SpringLayout.WEST, frame.getContentPane());
        frame.add(pending);
        currentBalance.setPreferredSize(new Dimension(150, 30));
        currentBalance.setHorizontalAlignment(JLabel.RIGHT);
        layout.putConstraint(SpringLayout.NORTH, currentBalance, 5, SpringLayout.SOUTH, insertCoin);
        layout.putConstraint(SpringLayout.EAST, currentBalance, -10, SpringLayout.WEST, cancel);
        frame.add(currentBalance);
        cancel.setPreferredSize(new Dimension(100, 30));
        layout.putConstraint(SpringLayout.NORTH, cancel, 5, SpringLayout.SOUTH, insertCoin);
        layout.putConstraint(SpringLayout.EAST, cancel, -10, SpringLayout.WEST, confirm);
        frame.add(cancel);
        confirm.setPreferredSize(new Dimension(100, 30));
        layout.putConstraint(SpringLayout.NORTH, confirm, 5, SpringLayout.SOUTH, insertCoin);
        layout.putConstraint(SpringLayout.EAST, confirm, 0, SpringLayout.EAST, pending);
        frame.add(confirm);
        boughtLabel.setPreferredSize(new Dimension(100, 30));
        layout.putConstraint(SpringLayout.NORTH, boughtLabel, 5, SpringLayout.SOUTH, pending);
        layout.putConstraint(SpringLayout.WEST, boughtLabel, 5, SpringLayout.WEST, frame.getContentPane());
        frame.add(boughtLabel);
        bought.setPreferredSize(new Dimension(500, 200));
        layout.putConstraint(SpringLayout.NORTH, bought, 5, SpringLayout.SOUTH, boughtLabel);
        layout.putConstraint(SpringLayout.WEST, bought, 5, SpringLayout.WEST, frame.getContentPane());
        frame.add(bought);
    }

    private static void updateBalance() {
        currentBalance.setText(String.format("Balance: $%.2f", balance));
    }

    private static void reset() {
        balance = 0.0d;
        updateBalance();
    }
}
