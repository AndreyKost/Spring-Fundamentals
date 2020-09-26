package wpartone.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import wpartone.error.Constants;
import wpartone.model.entity.Role;
import wpartone.model.entity.User;
import wpartone.model.service.RoleServiceModel;
import wpartone.model.service.UserServiceModel;
import wpartone.repository.UserRepository;
import wpartone.service.RoleService;
import wpartone.service.UserService;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService  {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final RoleService roleService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, RoleService roleService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.roleService = roleService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserServiceModel registerUser(UserServiceModel userServiceModel) {
        if(this.userRepository.count()==0){
            userServiceModel.setAuthorities(this.roleService.findAllRoles());
        } else {
            userServiceModel.setAuthorities(new LinkedHashSet<>());
            RoleServiceModel roleAuthorityAdd = this.roleService.findByAuth("USER");
            userServiceModel.getAuthorities().add(roleAuthorityAdd);
        }

        User user=this.modelMapper
                .map(userServiceModel,User.class);
            user.setPassword(this.bCryptPasswordEncoder.encode(userServiceModel.getPassword()));

        return  this.modelMapper.map(this.userRepository.saveAndFlush(user),UserServiceModel.class);
    }

    @Override
    public UserServiceModel findByUsername(String username) {

        return this.userRepository.findByUsername(username)
                .map(user -> this.modelMapper.map(user,UserServiceModel.class))
                .orElse(null);
    }

    @Override
    public List<String> findAllUsernames() {
        return this.userRepository.findAll()
                .stream()
                .map(User::getUsername)
                .collect(Collectors.toList());
    }

    @Override
    public void addRoleToUser(String username, String role) {
        User user=this.userRepository.findByUsername(username).orElse(null);

        boolean isRoleExists=false;

        for (Role authority : user.getAuthorities()) {
            if(authority.getAuthority().equals(role)){
                isRoleExists=true;
            }
        }

        if(!isRoleExists){
            Role roleEntity=this.modelMapper.map(this.roleService.findByAuth(role),Role.class);

            /*Set<Role> asdRoles=new HashSet<>();
            asdRoles.add();*/
            user.setAuthorities(Collections.singleton(roleEntity));
            this.userRepository.saveAndFlush(user);
        }

       /* if(!user.getRole().getName().equals(role)){
            Role roleEntity=this.modelMapper.map(this.roleService.findByName(role),Role.class);

            user.setRole(roleEntity);
            this.userRepository.saveAndFlush(user);
        }*/

    }

    @Override
    public UserServiceModel findById(String id) {

        return this.userRepository
                .findById(id)
                .map(user -> this.modelMapper.map(user,UserServiceModel.class))
                .orElse(null);

    }

    @Override
    public Long findTotalUserCount() {
        return this.userRepository.count();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(Constants.USERNAME_NOT_FOUND));
    }
}
