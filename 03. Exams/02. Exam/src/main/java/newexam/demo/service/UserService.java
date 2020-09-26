package newexam.demo.service;


import newexam.demo.model.service.UserServiceModel;

public interface UserService {
    UserServiceModel register(UserServiceModel userServiceModel);

    UserServiceModel findByEmail(String email);
}
