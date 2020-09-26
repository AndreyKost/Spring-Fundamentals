package myexam.service.impl;

import myexam.model.entity.User;
import myexam.model.service.UserServiceModel;
import myexam.repository.UserRepository;
import myexam.service.UserService;
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
    public UserServiceModel findBy(String username) {
        User user=this.userRepository.findByUsername(username).orElse(null);
        if(user==null){
            return null;
        }

        return this.modelMapper.map(user,UserServiceModel.class);

    }
}
