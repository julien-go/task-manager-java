package org.taskmanager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.taskmanager.Task;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TaskRepository {

    private static final String FILE_PATH = "src/main/resources/tasks.json";



    public static void saveTasks(List<Task> tasks) throws IOException {
        Gson gson = new Gson();
        List<Task> taskList = new ArrayList<>(tasks);
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(taskList, writer);
        }
    }

    public static List<Task>  loadTasks() {
        Gson gson = new Gson();
        List<Task> tasks = new ArrayList<>();
        Type listType = new TypeToken<List<Task>>() {}.getType();

        try (FileReader reader = new FileReader(FILE_PATH)) {
            tasks = gson.fromJson(reader, listType);
            if (tasks != null) {
                int maxId = 0;
                for (Task t : tasks) {
                    if (t.getId() > maxId) maxId = t.getId();
                    Task.setNextId(maxId + 1);
                }
            }
        } catch (IOException e) {
            return tasks;
        }

        return tasks;
    }

}