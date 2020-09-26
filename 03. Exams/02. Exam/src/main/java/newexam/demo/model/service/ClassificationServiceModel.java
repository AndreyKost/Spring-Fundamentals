package newexam.demo.model.service;

import newexam.demo.model.entity.ClassificationName;
import newexam.demo.model.entity.Task;

import java.util.Set;

public class ClassificationServiceModel extends BaseServiceModel {
    private ClassificationName classificationName;
    private String description;
    private Set<TaskServiceModel> tasks;

    public ClassificationServiceModel() {
    }

    public ClassificationName getClassificationName() {
        return classificationName;
    }

    public void setClassificationName(ClassificationName classificationName) {
        this.classificationName = classificationName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<TaskServiceModel> getTasks() {
        return tasks;
    }

    public void setTasks(Set<TaskServiceModel> tasks) {
        this.tasks = tasks;
    }
}
