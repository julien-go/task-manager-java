package org.taskmanager;

import java.time.LocalDate;

public class Task {

    private static int nextId = 1;

    final private int id;
    final private String description;
    private boolean isDone;

    Task(final String description){
        this.id = nextId++;
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone(){
        isDone = true;
    }

    public void unmarkAsDone(){
        isDone = false;
    }

    public int getId(){
        return id;
    }

    public static void setNextId(int value) {
        nextId = value;
    }

    @Override
    public String toString(){
        return (isDone ? "[x] " : "[ ] ") + "id: " + id + " - " + description;
    }
}
