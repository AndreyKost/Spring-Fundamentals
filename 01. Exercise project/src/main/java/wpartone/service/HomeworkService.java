package wpartone.service;

import wpartone.model.service.HomeworkServiceModel;

public interface HomeworkService {
    void add(HomeworkServiceModel homeworkServiceModel);

    HomeworkServiceModel findOneToCheck();// tuka e dobre da e view model vmesto service model da go opravq

    HomeworkServiceModel findById(String homeworkId);
}
