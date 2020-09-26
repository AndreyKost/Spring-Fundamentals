package newexam.demo.service;

import newexam.demo.model.entity.Classification;
import newexam.demo.model.entity.ClassificationName;

public interface ClassificationService {
    void initCategories();

    Classification findByClassificationName(ClassificationName classificationName);
}
