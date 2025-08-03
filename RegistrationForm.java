import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class RegistrationForm {

    // AWT components
    private Frame frame;
    private Label nameLabel, emailLabel, timeLabel, dateLabel;
    private TextField nameField, emailField, timeField;
    private Button submitButton, cancelButton;
    private Choice dateChoice;

    public RegistrationForm() {
        frame = new Frame("Registration Form");
        frame.setBackground(new Color(144, 238, 144)); // Light Green

        // Labels
        nameLabel = new Label("Name:");
        emailLabel = new Label("Email:");
        timeLabel = new Label("Preferred Time (HH:mm):");
        dateLabel = new Label("Select Date:");

        Font labelFont = new Font("Arial", Font.PLAIN, 12);
        nameLabel.setFont(labelFont);
        emailLabel.setFont(labelFont);
        timeLabel.setFont(labelFont);
        dateLabel.setFont(labelFont);

        // TextFields
        nameField = new TextField();
        emailField = new TextField();
        timeField = new TextField();
        nameField.setFont(labelFont);
        emailField.setFont(labelFont);
        timeField.setFont(labelFont);

        // Buttons
        submitButton = new Button("Submit");
        cancelButton = new Button("Cancel");
        submitButton.setFont(labelFont);
        cancelButton.setFont(labelFont);

        // Date choice
        dateChoice = new Choice();
        for (int i = 1; i <= 31; i++) {
            dateChoice.add("2024-10-" + (i < 10 ? "0" + i : i));
        }

        frame.setLayout(new GridLayout(5, 2, 10, 10));
        frame.add(nameLabel);
        frame.add(nameField);
        frame.add(emailLabel);
        frame.add(emailField);
        frame.add(dateLabel);
        frame.add(dateChoice);
        frame.add(timeLabel);
        frame.add(timeField);
        frame.add(submitButton);
        frame.add(cancelButton);

        // Submit button listener
        submitButton.addActionListener(e -> {
            String name = nameField.getText();
            String email = emailField.getText();
            String preferredTime = timeField.getText();
            String selectedDate = dateChoice.getSelectedItem();

            if (name.isEmpty() || email.isEmpty() || preferredTime.isEmpty() || selectedDate.isEmpty()) {
                showMessage("All fields are required!");
                return;
            }

            if (!isValidEmail(email)) {
                showMessage("Please enter a valid email address.");
                return;
            }

            if (!isValidTimeFormat(preferredTime)) {
                showMessage("Please enter a valid time in HH:mm format.");
                return;
            }

            saveRegistrationToFile(name, email, selectedDate, preferredTime);
            showMessage("Registration Successful!\nName: " + name + "\nEmail: " + email +
                    "\nPreferred Date: " + selectedDate + "\nPreferred Time: " + preferredTime);
        });

        // Cancel button listener
        cancelButton.addActionListener(e -> {
            nameField.setText("");
            emailField.setText("");
            timeField.setText("");
            dateChoice.select(0);
        });

        // Resize handling
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                if (frame.getWidth() > 800 && frame.getHeight() > 600) {
                    frame.setLayout(new GridLayout(6, 2, 10, 10));
                } else {
                    frame.setLayout(new GridLayout(5, 2, 10, 10));
                }
                frame.invalidate();
                frame.repaint();
            }
        });

        frame.setSize(400, 250);
        frame.setVisible(true);
    }

    private void showMessage(String message) {
        Dialog dialog = new Dialog(frame, "Message", true);
        Label messageLabel = new Label(message, Label.CENTER);
        Button okButton = new Button("OK");

        messageLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        okButton.setFont(new Font("Arial", Font.PLAIN, 12));

        dialog.setLayout(new BorderLayout());
        dialog.add(messageLabel, BorderLayout.CENTER);
        dialog.add(okButton, BorderLayout.SOUTH);

        okButton.addActionListener(e -> dialog.setVisible(false));
        dialog.setSize(300, 150);
        dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                            "[a-zA-Z0-9_+&*-]+)*@" +
                            "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }

    private boolean isValidTimeFormat(String time) {
        String timeRegex = "^([01]?\\d|2[0-3]):[0-5]\\d$";
        return time.matches(timeRegex);
    }

    private void saveRegistrationToFile(String name, String email, String date, String time) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("registrations.txt", true))) {
            writer.write("Name: " + name + "\n" +
                         "Email: " + email + "\n" +
                         "Preferred Date: " + date + "\n" +
                         "Preferred Time: " + time + "\n" +
                         "-------------------------------\n");
            System.out.println("Registration saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving registration to file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        @SuppressWarnings("unused")
        RegistrationForm registrationForm = new RegistrationForm();
    }
}
