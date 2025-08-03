import java.awt.*;
import java.awt.event.*;

public class Calculator {
    // Frame and components for the calculator
    private Frame frame;
    private TextField textField;
    private Button[] numberButtons;
    private Button addButton, subButton, mulButton, divButton, eqButton, clrButton, fullscreenButton;

    // Variables for storing operand and the result
    private String currentInput = "";
    private double firstOperand = 0;
    private double secondOperand = 0;
    private char operator;

    public Calculator() {
        // Initialize frame
        frame = new Frame("AWT Calculator");

        // Text field for displaying numbers and results
        textField = new TextField();
        textField.setEditable(false);

        // Create number buttons
        numberButtons = new Button[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new Button(String.valueOf(i));
        }

        // Initialize operator buttons
        addButton = new Button("+");
        subButton = new Button("-");
        mulButton = new Button("*");
        divButton = new Button("/");
        eqButton = new Button("=");
        clrButton = new Button("C");
        fullscreenButton = new Button("Fullscreen");

        // Set Layout for the frame
        frame.setLayout(new BorderLayout());

        // Add TextField to the top
        frame.add(textField, BorderLayout.NORTH);

        // Panel to hold number buttons and operator buttons
        Panel panel = new Panel();
        panel.setLayout(new GridLayout(5, 4, 10, 10));  // Adjusted to include fullscreen button

        // Add number buttons to the panel
        for (int i = 0; i < 10; i++) {
            panel.add(numberButtons[i]);
        }

        // Add operator buttons to the panel
        panel.add(addButton);
        panel.add(subButton);
        panel.add(mulButton);
        panel.add(divButton);
        panel.add(eqButton);
        panel.add(clrButton);
        panel.add(fullscreenButton);  // Fullscreen button added here

        // Add the panel to the frame
        frame.add(panel, BorderLayout.CENTER);

        // Action listeners for buttons
        for (Button numberButton : numberButtons) {
            numberButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    currentInput += e.getActionCommand();  // Append the pressed number to current input
                    textField.setText(currentInput);
                }
            });
        }

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firstOperand = Double.parseDouble(currentInput);
                currentInput = "";
                operator = '+';
                textField.setText("");
            }
        });

        subButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firstOperand = Double.parseDouble(currentInput);
                currentInput = "";
                operator = '-';
                textField.setText("");
            }
        });

        mulButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firstOperand = Double.parseDouble(currentInput);
                currentInput = "";
                operator = '*';
                textField.setText("");
            }
        });

        divButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firstOperand = Double.parseDouble(currentInput);
                currentInput = "";
                operator = '/';
                textField.setText("");
            }
        });

        eqButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                secondOperand = Double.parseDouble(currentInput);

                // Perform the calculation based on the operator
                double result = 0;
                switch (operator) {
                    case '+':
                        result = firstOperand + secondOperand;
                        break;
                    case '-':
                        result = firstOperand - secondOperand;
                        break;
                    case '*':
                        result = firstOperand * secondOperand;
                        break;
                    case '/':
                        if (secondOperand != 0) {
                            result = firstOperand / secondOperand;
                        } else {
                            textField.setText("Error: Div by 0");
                            return;
                        }
                        break;
                }
                textField.setText(String.valueOf(result));
                currentInput = String.valueOf(result);  // Set result as new input
            }
        });

        clrButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentInput = "";
                textField.setText("");
            }
        });

        fullscreenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Toggle fullscreen mode
                if (frame.getExtendedState() == Frame.MAXIMIZED_BOTH) {
                    frame.setExtendedState(Frame.NORMAL);  // Exit fullscreen
                } else {
                    frame.setExtendedState(Frame.MAXIMIZED_BOTH);  // Enter fullscreen
                }
            }
        });

        // Window Listener for handling window closing
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

        // Frame settings
        frame.setSize(400, 400);
        frame.setVisible(true);
        frame.setResizable(false);  // Make sure the window is not resizable
    }

    public static void main(String[] args) {
        // Run the calculator
        new Calculator();
    }
}
