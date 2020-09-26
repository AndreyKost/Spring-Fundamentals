package newexam.demo.model.service;

import newexam.demo.model.entity.Classification;
import newexam.demo.model.entity.Progress;
import newexam.demo.model.entity.User;

import java.time.LocalDateTime;
import java.util.Date;

public class TaskServiceModel extends BaseServiceModel {
    private String name;
    private String description;
    private Progress progress;
    private Date dueDate;
    private ClassificationServiceModel classification;
    private UserServiceModel user;

    public TaskServiceModel() {
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

    public ClassificationServiceModel getClassification() {
        return classification;
    }

    public void setClassification(ClassificationServiceModel classification) {
        this.classification = classification;
    }

    public UserServiceModel getUser() {
        return user;
    }

    public void setUser(UserServiceModel user) {
        this.user = user;
    }
}
