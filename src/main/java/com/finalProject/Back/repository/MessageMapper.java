package com.finalProject.Back.repository;

import com.finalProject.Back.entity.Message;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MessageMapper {
    int save(Message message);
    List<Message> findByUserId(int userId);
}
