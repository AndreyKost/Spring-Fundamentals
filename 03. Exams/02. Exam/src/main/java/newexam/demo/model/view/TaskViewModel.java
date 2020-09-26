package newexam.demo.model.view;

import newexam.demo.model.entity.Classification;
import newexam.demo.model.entity.Progress;
import newexam.demo.model.entity.User;

import java.util.Date;

public class TaskViewModel {
    private String name;
    private String description;
    private Progress progress;
    private Date dueDate;
    private Classification classification;
    private User user;

    public TaskViewModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Progress getProgress() {
        return progress;
    }

    public void setProgress(Progress progress) {
        this.progress = progress;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Classification getClassification() {
        return classification;
    }

    public void setClassification(Classification classification) {
        this.classification = classification;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
