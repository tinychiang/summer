package com.summer.frame.commons;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户基本信息
 *
 * @author Tiny Chiang
 * @version 1.0.0
 * @date 2021-08-11
 */
@Data
public class AbstractCurrentUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String username;

}