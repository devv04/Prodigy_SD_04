import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class Task {
    private String description;
    private boolean isCompleted;

    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void markAsCompleted() {
        this.isCompleted = true;
    }

    @Override
    public String toString() {
        return (isCompleted ? "[Completed] " : "[Pending] ") + description;
    }
}

public class ToDoListGUI extends JFrame {
    private DefaultListModel<String> taskListModel;
    private ArrayList<Task> tasks;
    private JList<String> taskList;
    private JTextField taskInput;

    public ToDoListGUI() {
        // Initialize the list of tasks
        tasks = new ArrayList<>();

        // Set up the window
        setTitle("To-Do List Application");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create and set up the task list model
        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        JScrollPane scrollPane = new JScrollPane(taskList);

        // Create task input field
        taskInput = new JTextField(20);
        JButton addButton = new JButton("Add Task");

        // Panel for task input and button
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        inputPanel.add(new JLabel("New Task:"));
        inputPanel.add(taskInput);
        inputPanel.add(addButton);

        // Add inputPanel and scrollPane to the main window
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Create buttons for marking completed and deleting tasks
        JButton completeButton = new JButton("Mark as Completed");
        JButton deleteButton = new JButton("Delete Task");

        // Panel for control buttons
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        controlPanel.add(completeButton);
        controlPanel.add(deleteButton);

        add(controlPanel, BorderLayout.SOUTH);

        // Add action listeners for the buttons
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTask();
            }
        });

        completeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                markTaskAsCompleted();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteTask();
            }
        });
    }

    private void addTask() {
        String taskDescription = taskInput.getText();
        if (!taskDescription.isEmpty()) {
            Task task = new Task(taskDescription);
            tasks.add(task);
            taskListModel.addElement(task.toString());
            taskInput.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Task description cannot be empty.");
        }
    }

    private void markTaskAsCompleted() {
        int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex != -1) {
            Task task = tasks.get(selectedIndex);
            task.markAsCompleted();
            taskListModel.set(selectedIndex, task.toString());
        } else {
            JOptionPane.showMessageDialog(this, "Please select a task to mark as completed.");
        }
    }

    private void deleteTask() {
        int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex != -1) {
            tasks.remove(selectedIndex);
            taskListModel.remove(selectedIndex);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a task to delete.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ToDoListGUI().setVisible(true);
            }
        });
    }
}
