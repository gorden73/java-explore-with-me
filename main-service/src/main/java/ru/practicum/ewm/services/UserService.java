package ru.practicum.ewm.services;

import ru.practicum.ewm.apis.admins.dtos.users.UserDto;

import java.util.Collection;

public interface UserService {
    Collection<UserDto> getAllUsers(Integer[] ids, int from, int size);

    UserDto addUser(UserDto userDto);

    void removeUser(int userId);
}
