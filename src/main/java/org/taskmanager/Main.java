package org.taskmanager;

import java.io.IOException;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        List<Task> tasks = TaskRepository.loadTasks();

        Scanner scanner = new Scanner(System.in);

        while(true){
            System.out.println("Welcome to TaskManager ! What do you want to do ?");
            System.out.println("1 - Read all tasks");
            System.out.println("2 - Add a new task");
            System.out.println("3 - Delete a new task");
            System.out.println("4 - Mark a task as done");
            System.out.println("5 - Unmark a task as done");
            System.out.println("6 - Quit");

            if (!scanner.hasNextInt()) {
                System.out.println("You must enter a number!");
                scanner.nextLine();
                continue;
            }

            int choice = scanner.nextInt();

            switch(choice){
                case 1:
                    System.out.println("Let's read all tasks");
                    tasks = TaskRepository.loadTasks();
                    for(Task task : tasks) {
                        System.out.println(task.toString());
                    }
                    break;

                case 2:
                    System.out.println("Let's add a new task ! Enter the description :");
                    scanner.nextLine();
                    Task t = new Task(scanner.nextLine());
                    tasks.add(t);
                    TaskRepository.saveTasks(tasks);
                    System.out.println("Task added !");
                    break;
                case 3:
                    System.out.println("What task do you want to delete ? Please give the id");
                    scanner.nextLine();

                    try {
                        int inputId = scanner.nextInt();
                        Task taskToDelete = findTaskById(tasks, inputId);

                        if (taskToDelete != null) {
                            tasks.remove(inputId);
                            TaskRepository.saveTasks(tasks);
                            System.out.println("Task deleted !");
                        } else {
                            System.out.println("No task found with this id");
                        }
                    } catch (InputMismatchException e){
                        System.out.println("You must enter a number!");
                        scanner.nextLine();
                    }
                    break;
                case 4:
                    System.out.println("What task do you want to mark as done ? Please give the id");
                    scanner.nextLine();
                    try {
                        int inputId = scanner.nextInt();
                        Task taskToUpdate = findTaskById(tasks, inputId);

                        if (taskToUpdate != null) {
                            taskToUpdate.markAsDone();
                            TaskRepository.saveTasks(tasks);
                        } else {
                            System.out.println("No task found with this id");
                        }

                    } catch (InputMismatchException e){
                        System.out.println("You must enter a number!");
                        scanner.nextLine();
                    }
                    break;
                case 5:
                    System.out.println("What task do you want to unmark as done ? Please give the id");
                    scanner.nextLine();
                    try {
                        int inputId = scanner.nextInt();
                        Task taskToUpdate = findTaskById(tasks, inputId);

                        if (taskToUpdate != null) {
                            taskToUpdate.unmarkAsDone();
                            TaskRepository.saveTasks(tasks);
                        } else {
                            System.out.println("No task found with this id");
                        }
                    } catch (InputMismatchException e){
                        System.out.println("You must enter a number!");
                        scanner.nextLine();
                    }
                    break;
                case 6:
                    System.out.println("Bye !");
                    return;
                default:
                    System.out.println("Please tap 1 or 2");
            }
        }
    }

    private static Task findTaskById(List<Task> tasks, int id) {
        for (Task t : tasks) {
            if (t.getId() == id) {
                return t;
            }
        }
        return null;
    }
}