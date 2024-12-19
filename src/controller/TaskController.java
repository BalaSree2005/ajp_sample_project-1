package controller;

import model.Task;
import view.TaskView;

import java.util.ArrayList;
import java.util.List;

public class TaskController {
    private List<Task> taskList;
    private TaskView view;
    private int taskCounter;

    public TaskController(TaskView view) {
        this.taskList = new ArrayList<>();
        this.view = view;
        this.taskCounter = 1;
    }

    public void run() {
        boolean exit = false;
        while (!exit) {
            int choice = view.showMenuAndGetChoice();
            switch (choice) {
                case 1: createTask(); break;
                case 2: viewAllTasks(); break;
                case 3: updateTask(); break;
                case 4: markTaskComplete(); break;
                case 5: exit = true; break;
                default: view.showMessage("Invalid choice! Try again.");
            }
        }
    }

    private void createTask() {
        String title = view.getTaskTitle();
        String description = view.getTaskDescription();
        int priority = view.getTaskPriority();
        Task newTask = new Task(taskCounter++, title, description, priority);
        taskList.add(newTask);
        view.showMessage("Task Created Successfully!");
    }

    private void viewAllTasks() {
        if (taskList.isEmpty()) {
            view.showMessage("No tasks available.");
        } else {
            for (Task task : taskList) {
                view.showTask(task.toString());
            }
        }
    }

    private void updateTask() {
        int id = view.getTaskId();
        for (Task task : taskList) {
            if (task.getId() == id) {
                task.setTitle(view.getTaskTitle());
                task.setDescription(view.getTaskDescription());
                task.setPriority(view.getTaskPriority());
                view.showMessage("Task Updated Successfully!");
                return;
            }
        }
        view.showMessage("Task Not Found!");
    }

    private void markTaskComplete() {
        int id = view.getTaskId();
        for (Task task : taskList) {
            if (task.getId() == id) {
                task.setComplete(true);
                view.showMessage("Task Marked as Complete!");
                return;
            }
        }
        view.showMessage("Task Not Found!");
    }
}
