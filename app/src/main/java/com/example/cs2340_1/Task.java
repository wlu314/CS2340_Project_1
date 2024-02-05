package com.example.cs2340_1;

public class Task {
    private String id;
    private String name;
    private String dueDate; // Simplified for example purposes
    private String course;
    private boolean isCompleted;

    public Task(String id, String name, String dueDate, String course) {
        this.id = id;
        this.name = name;
        this.dueDate = dueDate;
        this.course = course;
        this.isCompleted = false;
    }

    // Getters and Setters
    public String getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDueDate() { return dueDate; }
    public void setDueDate(String dueDate) { this.dueDate = dueDate; }

    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }

    public boolean isCompleted() { return isCompleted; }
    public void setCompleted(boolean completed) { isCompleted = completed; }
}
