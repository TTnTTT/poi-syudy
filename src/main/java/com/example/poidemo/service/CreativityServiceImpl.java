package com.example.poidemo.service;

import com.example.poidemo.entity.Creativity;
import com.example.poidemo.mapper.CreativityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author night bird
 * @version 1.0
 * @date 2020/12/18 22:48
 */
@Service
public class CreativityServiceImpl implements CreativityService {
    @Autowired
    CreativityMapper creativityMapper;

    @Override
    public List<Creativity> selectAllDetailCreativity() {
        return creativityMapper.selectAllDetailCreativity();
    }
}
