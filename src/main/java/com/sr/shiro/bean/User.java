package com.sr.shiro.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class User {
    private String id;
    private String nickName;
    private String email;
    private String pswd;
    private Date create_time;
    private Date last_login_time;
    private int status;



}
