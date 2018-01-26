package com.xiaoxz.bean;

import com.xiaoxz.util.Convertor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

/**
 * @author : xiaoxz
 * @Date: Created in 2018/1/25
 * @Modified by :
 **/
@Getter
@Setter
public class UserDto {

    private Integer id;
    private String userName;
    private String passWord;
    private String nickName;
    private String phone;


    public User convertToUser(UserDto userDto) {
        UserDtoConvertor convertor = new UserDtoConvertor();
        User user = convertor.doForward(userDto);
        return user;
    }

    public UserDto convertToUserDto(User user) {
        UserDtoConvertor convertor = new UserDtoConvertor();
        return convertor.backForward(user);
    }

    private static class UserDtoConvertor implements Convertor<UserDto, User> {

        @Override
        public User doForward(UserDto userDao) {
            User user = new User();
            BeanUtils.copyProperties(userDao, user);
            return user;
        }

        @Override
        public UserDto backForward(User user) {
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(user, userDto);
            return userDto;
        }
    }
}
