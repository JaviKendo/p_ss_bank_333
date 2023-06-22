package com.bank.authorization.service;

import com.bank.authorization.dto.UserDTO;
import com.bank.authorization.entity.User;
import com.bank.authorization.exception.UserNotFoundException;
import com.bank.authorization.repository.UserRepository;
import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    @Timed(value = "findByProfileId_time", description = "Time taken to find user authorization")
    @Counted(value = "findByProfileId_count", description = "Number of times findByProfileId method has been invoked")
    public UserDTO findByProfileId(Long profileId) {
        User user = userRepository.findByProfileId(profileId);
        return modelMapper.map(user, UserDTO.class);
    }
    @Override
    @Timed(value = "findById.time", description = "Time taken to get authorization details by id")
    @Counted(value = "findById.count", description = "Number of times findById method has been invoked")
    public UserDTO findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found"));
        return modelMapper.map(user, UserDTO.class);
    }


    @Override
    @Timed(value = "findAllAuthorization.time", description = "Time taken to get all user authorizations")
    @Counted(value = "findAllAuthorization.count",
            description = "Number of times get all user authorizations method has been invoked")
    public List<UserDTO> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> modelMapper.map(user, UserDTO.class)).collect(Collectors.toList());
    }

    @Override
    @Timed(value = "save.time", description = "Time taken to save user authorization")
    @Counted(value = "save.count", description = "Number of times save method has been invoked")
    public UserDTO save(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        user = userRepository.save(user);
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    @Timed(value = "delete.time", description = "Time taken to delete user authorization")
    @Counted(value = "delete.count", description = "Number of times delete method has been invoked")
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    @Timed(value = "updateUser.time", description = "Time taken to update user authorization")
    @Counted(value = "updateUser.count",
            description = "Number of times update user authorization method has been invoked")
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

        // Обновление полей
        existingUser.setRole(userDTO.getRole());
        existingUser.setProfileId(userDTO.getProfileId());
        existingUser.setPassword(userDTO.getPassword());

        User updatedUser = userRepository.save(existingUser);

        return convertToUserDTO(updatedUser);
    }

    private UserDTO convertToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setRole(user.getRole());
        userDTO.setProfileId(user.getProfileId());
        userDTO.setPassword(user.getPassword());
        return userDTO;
    }
}

