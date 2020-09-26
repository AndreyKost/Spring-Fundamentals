package newexam.demo.model.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "classifications")
public class Classification extends BaseEntity {

    private ClassificationName classificationName;
    private String description;
    private Set<Task> tasks;

    public Classification() {
    }

    public Classification(ClassificationName classificationName,String description) {
        this.classificationName=classificationName;
        this.description=description;
    }


    public ClassificationName getClassificationName() {
        return classificationName;
    }

    @Enumerated(EnumType.ORDINAL)
    public void setClassificationName(ClassificationName classificationName) {
        this.classificationName = classificationName;
    }

    @Column(name="description",columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @OneToMany(mappedBy = "classification")
    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }
}
