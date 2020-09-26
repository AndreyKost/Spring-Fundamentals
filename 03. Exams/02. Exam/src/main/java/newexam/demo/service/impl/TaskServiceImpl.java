package newexam.demo.service.impl;

import newexam.demo.model.entity.ClassificationName;
import newexam.demo.model.entity.Task;
import newexam.demo.model.service.TaskServiceModel;
import newexam.demo.model.view.TaskViewModel;
import newexam.demo.repository.TaskRepository;
import newexam.demo.service.ClassificationService;
import newexam.demo.service.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {
    private final ModelMapper modelMapper;
    private final TaskRepository taskRepository;
    private final ClassificationService classificationService;

    public TaskServiceImpl(ModelMapper modelMapper, TaskRepository taskRepository, ClassificationService classificationService) {
        this.modelMapper = modelMapper;
        this.taskRepository = taskRepository;
        this.classificationService = classificationService;
    }

    @Override
    public void addTask(TaskServiceModel taskServiceModel) {
        Task task = this.modelMapper.map(taskServiceModel, Task.class);

        task.setClassification(this.classificationService
                .findByClassificationName(taskServiceModel.getClassification().getClassificationName()));

        this.taskRepository.saveAndFlush(task);
    }

    @Override
    public List<TaskViewModel> getTasks(ClassificationName classification) {
        return this.taskRepository.findAll().stream().filter(e-> e.getClassification().getClassificationName().equals(classification))
                .map(task -> modelMapper.map(task,TaskViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskViewModel> findAllTasks() {
        return taskRepository.findAll()
                .stream()
                .map(task -> {
                    TaskViewModel taskViewModel=this.modelMapper.map(task,TaskViewModel.class);
                    return taskViewModel;
                })
                .collect(Collectors.toList());
    }
}
