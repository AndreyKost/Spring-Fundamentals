package newexam.demo.service.impl;


import newexam.demo.model.entity.User;
import newexam.demo.model.service.UserServiceModel;
import newexam.demo.repository.UserRepository;
import newexam.demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserServiceModel register(UserServiceModel userServiceModel) {
        User user=this.modelMapper.map(userServiceModel,User.class);

        return this.modelMapper.map(this.userRepository.saveAndFlush(user),UserServiceModel.class);
    }



    @Override
    public UserServiceModel findByEmail(String email) {
        User user=this.userRepository.findByEmail(email).orElse(null);
        if(user==null){
            return null;
        }

        return this.modelMapper.map(user,UserServiceModel.class);
    }
}
