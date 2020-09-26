package newexam.demo.service.impl;

import newexam.demo.model.entity.Classification;
import newexam.demo.model.entity.ClassificationName;
import newexam.demo.repository.ClassificationRepository;
import newexam.demo.service.ClassificationService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ClassificationServiceImpl implements ClassificationService {
    private final ClassificationRepository classificationRepository;
    private final ModelMapper modelMapper;

    public ClassificationServiceImpl(ClassificationRepository classificationRepository, ModelMapper modelMapper) {
        this.classificationRepository = classificationRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void initCategories() {
        if(this.classificationRepository.count()==0){
            Arrays.stream(ClassificationName.values())
                    .forEach(classificationName -> {
                        this.classificationRepository.save(new Classification(classificationName,String.format("Description for %s",classificationName.name())));
                    });
        }
    }

    @Override
    public Classification findByClassificationName(ClassificationName classificationName) {
        return this.classificationRepository.findByClassificationName(classificationName)
                .orElse(null);
    }
}
