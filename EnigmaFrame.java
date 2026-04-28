import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EnigmaFrame extends JFrame {


    private JComboBox<Integer> innerRotorBox;
    private JComboBox<Integer> middleRotorBox;
    private JComboBox<Integer> outerRotorBox;


    private JTextField startField;


    private JTextArea inputArea;
    private JTextArea outputArea;


    private JButton encryptButton;
    private JButton decryptButton;

    public EnigmaFrame() {
        super("Enigma Machine");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLayout(new BorderLayout(10, 10));


        JPanel settingsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        settingsPanel.setBorder(BorderFactory.createTitledBorder("Rotor Settings"));

        Integer[] rotorOptions = {1, 2, 3, 4, 5};

        JLabel innerLabel  = new JLabel("Inner:");
        innerRotorBox = new JComboBox<>(rotorOptions);

        JLabel middleLabel = new JLabel("Middle:");
        middleRotorBox = new JComboBox<>(rotorOptions);
        middleRotorBox.setSelectedIndex(1);

        JLabel outerLabel  = new JLabel("Outer:");
        outerRotorBox = new JComboBox<>(rotorOptions);
        outerRotorBox.setSelectedIndex(2);

        JLabel startLabel  = new JLabel("Start Position:");
        startField = new JTextField("###", 5);

        settingsPanel.add(innerLabel);
        settingsPanel.add(innerRotorBox);
        settingsPanel.add(middleLabel);
        settingsPanel.add(middleRotorBox);
        settingsPanel.add(outerLabel);
        settingsPanel.add(outerRotorBox);
        settingsPanel.add(startLabel);
        settingsPanel.add(startField);

        add(settingsPanel, BorderLayout.NORTH);


        JPanel ioPanel = new JPanel(new GridLayout(1, 2, 10, 0));

        JPanel inputPanel = new JPanel(new BorderLayout(5, 5));
        JLabel inputLabel = new JLabel("Input:");
        inputArea = new JTextArea(10, 20);
        inputArea.setLineWrap(true);
        inputArea.setWrapStyleWord(true);
        inputPanel.add(inputLabel, BorderLayout.NORTH);
        inputPanel.add(new JScrollPane(inputArea), BorderLayout.CENTER);

        JPanel outputPanel = new JPanel(new BorderLayout(5, 5));
        JLabel outputLabel = new JLabel("Output:");
        outputArea = new JTextArea(10, 20);
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);
        outputArea.setEditable(false);
        outputPanel.add(outputLabel, BorderLayout.NORTH);
        outputPanel.add(new JScrollPane(outputArea), BorderLayout.CENTER);

        ioPanel.add(inputPanel);
        ioPanel.add(outputPanel);
        ioPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

        add(ioPanel, BorderLayout.CENTER);


        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));

        encryptButton = new JButton("Encrypt");
        decryptButton = new JButton("Decrypt");

        encryptButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                runEnigma("encrypt");
            }
        });

        decryptButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                runEnigma("decrypt");
            }
        });

        buttonPanel.add(encryptButton);
        buttonPanel.add(decryptButton);

        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void runEnigma(String mode) {

        String start = startField.getText();
        if (start.length() != 3) {
            JOptionPane.showMessageDialog(this,
                    "Start position must be exactly 3 characters.",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String message = inputArea.getText().trim();
        if (message.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter a message in the Input area.",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }


        String[] args = {
                String.valueOf(innerRotorBox.getSelectedItem()),
                String.valueOf(middleRotorBox.getSelectedItem()),
                String.valueOf(outerRotorBox.getSelectedItem()),
                start,
                mode,
                message
        };

        String result = Comms.run(args);
        outputArea.setText(result);
    }
}