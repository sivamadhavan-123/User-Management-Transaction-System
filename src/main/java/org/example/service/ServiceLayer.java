package org.example.service;
import org.example.dao.LoginDao;
import org.example.dao.UserDao;
import org.example.dto.LoginDto;
import org.example.dto.User;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;


public class ServiceLayer {


    public static LoginDto login(String username,String password){


        LoginDto user=LoginDao.findByUsername(username);

        if(user==null){
            return  null;
        }
        if(BCrypt.checkpw(password,user.getPassword())){

            return  user;
        }
        return  null;
    }


    public static List<User> pagenation(int pageSize, int pageNumber){
        int offset = (pageNumber - 1) * pageSize;
        List<User> user=UserDao.selectAll(pageSize,offset);
        return user;

    }



}
