package com.tencent.passport.musician.repository.dao;

import com.tencent.passport.musician.model.dmo.TFuncAuth;
import com.tencent.passport.musician.model.dmo.TFuncAuthExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TFuncAuthMapper {
    int countByExample(TFuncAuthExample example);

    int deleteByExample(TFuncAuthExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TFuncAuth record);

    int insertSelective(TFuncAuth record);

    List<TFuncAuth> selectByExample(TFuncAuthExample example);

    TFuncAuth selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TFuncAuth record, @Param("example") TFuncAuthExample example);

    int updateByExample(@Param("record") TFuncAuth record, @Param("example") TFuncAuthExample example);

    int updateByPrimaryKeySelective(TFuncAuth record);

    int updateByPrimaryKey(TFuncAuth record);
}