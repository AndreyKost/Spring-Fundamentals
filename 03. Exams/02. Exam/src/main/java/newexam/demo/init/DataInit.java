package newexam.demo.init;


import newexam.demo.service.ClassificationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class DataInit implements CommandLineRunner {
    private final ClassificationService classificationService;

    public DataInit(ClassificationService classificationService) {
        this.classificationService = classificationService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.classificationService.initCategories();
    }
}
