package prep.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import prep.model.entity.User;
import prep.model.service.UserServiceModel;
import prep.repository.UserRepository;
import prep.service.UserService;

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

        return this.modelMapper.map(user,UserServiceModel.class);
    }
}
