package com.example.poidemo.mapper;

import com.example.poidemo.entity.Creativity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author night bird
 * @version 1.0
 * @date 2020/12/18 22:49
 */
@Mapper
public interface CreativityMapper {
    List<Creativity> selectAllDetailCreativity();
}
