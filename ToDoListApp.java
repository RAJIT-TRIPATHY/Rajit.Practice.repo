import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;
import java.io.*;

class Task implements Cloneable {
    String description;
    String timestamp;

    Task(String description) {
        this.description = description;
        this.timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    public String toString() {
        return description + " (added on: " + timestamp + ")";
    }

    // Clone method to duplicate the task
    @Override
    protected Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}

public class ToDoListApp {
    private Frame frame;
    private TextArea taskListArea;
    private TextField taskInput;
    private Button addButton, deleteButton, cloneButton;
    @SuppressWarnings("FieldMayBeFinal")
    private List<Task> tasks;

    public ToDoListApp() {
        tasks = new ArrayList<>();
        
        frame = new Frame("To-Do List Application");
        frame.setSize(400, 400);
        frame.setLayout(new FlowLayout());

        taskInput = new TextField(30);
        addButton = new Button("Add Task");
        deleteButton = new Button("Delete Task");
        cloneButton = new Button("Clone Task");

        taskListArea = new TextArea(10, 30);
        taskListArea.setEditable(false);

        // Add a scrollbar to the TextArea
        taskListArea.setScrollbarVisibility(true);

        // Add components to frame
        frame.add(new Label("Enter Task:"));
        frame.add(taskInput);
        frame.add(addButton);
        frame.add(deleteButton);
        frame.add(cloneButton);
        frame.add(taskListArea);

        // Add action listeners
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addTask();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteTask();
            }
        });

        cloneButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cloneTask();
            }
        });

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

        frame.setVisible(true);
    }

    private void addTask() {
        String taskDescription = taskInput.getText().trim();
        if (!taskDescription.isEmpty()) {
            Task newTask = new Task(taskDescription);
            tasks.add(newTask);
            updateTaskList();
            taskInput.setText("");
        }
    }

    private void deleteTask() {
        String taskDescription = taskInput.getText().trim();
        if (!taskDescription.isEmpty()) {
            Iterator<Task> iterator = tasks.iterator();
            while (iterator.hasNext()) {
                Task task = iterator.next();
                if (task.description.equals(taskDescription)) {
                    iterator.remove(); // Safely remove from the list
                    updateTaskList();
                    taskInput.setText("");
                    break;
                }
            }
        }
    }

    private void cloneTask() {
        String taskDescription = taskInput.getText().trim();
        if (!taskDescription.isEmpty()) {
            for (Task task : tasks) {
                if (task.description.equals(taskDescription)) {
                    Task clonedTask = (Task) task.clone();
                    if (clonedTask != null) {
                        tasks.add(clonedTask);
                        updateTaskList();
                    }
                    break;
                }
            }
        }
    }

    private void updateTaskList() {
        taskListArea.setText(""); // Clear the text area
        for (Task task : tasks) {
            taskListArea.append(task.toString() + "\n");
        }
    }

    public static void main(String[] args) {
        new ToDoListApp();
    }
}
