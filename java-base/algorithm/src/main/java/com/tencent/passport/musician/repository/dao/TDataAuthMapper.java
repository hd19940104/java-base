package com.tencent.passport.musician.repository.dao;

import com.tencent.passport.musician.model.dmo.TDataAuth;
import com.tencent.passport.musician.model.dmo.TDataAuthExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TDataAuthMapper {
    int countByExample(TDataAuthExample example);

    int deleteByExample(TDataAuthExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TDataAuth record);

    int insertSelective(TDataAuth record);

    List<TDataAuth> selectByExampleWithBLOBs(TDataAuthExample example);

    List<TDataAuth> selectByExample(TDataAuthExample example);

    TDataAuth selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TDataAuth record, @Param("example") TDataAuthExample example);

    int updateByExampleWithBLOBs(@Param("record") TDataAuth record, @Param("example") TDataAuthExample example);

    int updateByExample(@Param("record") TDataAuth record, @Param("example") TDataAuthExample example);

    int updateByPrimaryKeySelective(TDataAuth record);

    int updateByPrimaryKeyWithBLOBs(TDataAuth record);

    int updateByPrimaryKey(TDataAuth record);
}