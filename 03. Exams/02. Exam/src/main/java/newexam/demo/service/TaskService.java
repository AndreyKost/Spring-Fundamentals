package newexam.demo.service;

import newexam.demo.model.entity.ClassificationName;
import newexam.demo.model.service.TaskServiceModel;
import newexam.demo.model.view.TaskViewModel;

import java.util.List;

public interface TaskService {
    void addTask(TaskServiceModel taskServiceModel);

    List<TaskViewModel> getTasks(ClassificationName classification);

    List<TaskViewModel> findAllTasks();
}
