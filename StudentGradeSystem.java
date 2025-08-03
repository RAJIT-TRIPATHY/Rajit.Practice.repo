import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

class Student {
    String id;
    String name;
    double[] subjectGrades;
    Date dateOfEntry;

    public Student(String id, String name, double[] grades) {
        this.id = id;
        this.name = name;
        this.subjectGrades = grades;
        this.dateOfEntry = Calendar.getInstance().getTime();
    }

    public double calculateAverage() {
        double sum = 0;
        for (double grade : subjectGrades) {
            sum += grade;
        }
        return sum / subjectGrades.length;
    }

    public String calculateGrade() {
        double avg = calculateAverage();
        if (avg >= 90) return "A";
        else if (avg >= 80) return "B";
        else if (avg >= 70) return "C";
        else if (avg >= 60) return "D";
        else return "F";
    }

    public String displayDetails() {
        return "ID: " + id + "\nName: " + name + "\nAverage: " + calculateAverage() + 
               "\nGrade: " + calculateGrade() + "\nDate of Entry: " + dateOfEntry;
    }
}

public class StudentGradeSystem extends Frame {
    private final class ActionListenerImplementation implements ActionListener {
        @SuppressWarnings("override")
        public void actionPerformed(ActionEvent e) {
            addStudent();
        }
    }

    // GUI Components
    Label nameLabel, idLabel, gradeLabel;
    TextField nameField, idField, gradeField1, gradeField2, gradeField3;
    Button submitButton, displayButton;
    TextArea displayArea;

    ArrayList<Student> students = new ArrayList<>();

    public StudentGradeSystem() {
        // Setting up Frame
        setLayout(new FlowLayout());
        setTitle("Student Grade Management System");

        // ID and Name Fields
        idLabel = new Label("ID:");
        idField = new TextField(10);
        nameLabel = new Label("Name:");
        nameField = new TextField(20);

        // Grade Fields
        gradeLabel = new Label("Grades (3 Subjects):");
        gradeField1 = new TextField(5);
        gradeField2 = new TextField(5);
        gradeField3 = new TextField(5);

        // Buttons
        submitButton = new Button("Submit");
        displayButton = new Button("Display All");

        // Display Area
        displayArea = new TextArea(10, 50);

        // Adding Components
        add(idLabel); add(idField);
        add(nameLabel); add(nameField);
        add(gradeLabel); add(gradeField1); add(gradeField2); add(gradeField3);
        add(submitButton); add(displayButton);
        add(displayArea);

        // Action Listeners
        submitButton.addActionListener(new ActionListenerImplementation());

        displayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayAllStudents();
            }
        });

        // Add window listener to close the window properly
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
            }
        });

        setSize(600, 400);
        setVisible(true);
    }

    public void addStudent() {
        try {
            String id = idField.getText().trim();
            String name = nameField.getText().trim();

            // Validate fields
            if (id.isEmpty() || name.isEmpty()) {
                displayArea.setText("ID and Name fields cannot be empty.");
                return;
            }

            // Parse grades safely
            double[] grades = new double[3];
            grades[0] = parseGrade(gradeField1.getText().trim());
            grades[1] = parseGrade(gradeField2.getText().trim());
            grades[2] = parseGrade(gradeField3.getText().trim());

            Student student = new Student(id, name, grades);
            students.add(student);
            displayArea.setText("Student Added Successfully!");
            clearFields();
        } catch (Exception e) {
            displayArea.setText("Error: " + e.getMessage());
        }
    }

    // Method to parse grade safely
    private double parseGrade(String gradeText) throws NumberFormatException {
        if (gradeText.isEmpty()) {
            throw new NumberFormatException("Grade fields cannot be empty.");
        }
        return Double.parseDouble(gradeText);
    }

    // Method to clear input fields after adding a student
    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        gradeField1.setText("");
        gradeField2.setText("");
        gradeField3.setText("");
    }

    public void displayAllStudents() {
        if (students.isEmpty()) {
            displayArea.setText("No students to display.");
            return;
        }
        
        StringBuilder displayText = new StringBuilder();
        for (Student student : students) {
            displayText.append(student.displayDetails()).append("\n\n");
        }
        displayArea.setText(displayText.toString());
    }
    public static void main(String[] args) {
        new StudentGradeSystem();
    }

    public Label getNameLabel() {
        return nameLabel;
    }

    public void setNameLabel(Label nameLabel) {
        this.nameLabel = nameLabel;
    }

    public Label getIdLabel() {
        return idLabel;
    }

    public void setIdLabel(Label idLabel) {
        this.idLabel = idLabel;
    }

    public Label getGradeLabel() {
        return gradeLabel;
    }

    public void setGradeLabel(Label gradeLabel) {
        this.gradeLabel = gradeLabel;
    }

    public TextField getNameField() {
        return nameField;
    }

    public void setNameField(TextField nameField) {
        this.nameField = nameField;
    }

    public TextField getIdField() {
        return idField;
    }

    public void setIdField(TextField idField) {
        this.idField = idField;
    }

    public TextField getGradeField1() {
        return gradeField1;
    }

    public void setGradeField1(TextField gradeField1) {
        this.gradeField1 = gradeField1;
    }

    public TextField getGradeField2() {
        return gradeField2;
    }

    public void setGradeField2(TextField gradeField2) {
        this.gradeField2 = gradeField2;
    }

    public TextField getGradeField3() {
        return gradeField3;
    }

    public void setGradeField3(TextField gradeField3) {
        this.gradeField3 = gradeField3;
    }

    public Button getSubmitButton() {
        return submitButton;
    }

    public void setSubmitButton(Button submitButton) {
        this.submitButton = submitButton;
    }

    public Button getDisplayButton() {
        return displayButton;
    }

    public void setDisplayButton(Button displayButton) {
        this.displayButton = displayButton;
    }

    public TextArea getDisplayArea() {
        return displayArea;
    }

    public void setDisplayArea(TextArea displayArea) {
        this.displayArea = displayArea;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }
}
    