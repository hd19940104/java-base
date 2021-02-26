package com.tencent.passport.musician.repository.dao;

import com.tencent.passport.musician.model.dmo.TRoleTree;
import com.tencent.passport.musician.model.dmo.TRoleTreeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TRoleTreeMapper {
    int countByExample(TRoleTreeExample example);

    int deleteByExample(TRoleTreeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TRoleTree record);

    int insertSelective(TRoleTree record);

    List<TRoleTree> selectByExample(TRoleTreeExample example);

    TRoleTree selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TRoleTree record, @Param("example") TRoleTreeExample example);

    int updateByExample(@Param("record") TRoleTree record, @Param("example") TRoleTreeExample example);

    int updateByPrimaryKeySelective(TRoleTree record);

    int updateByPrimaryKey(TRoleTree record);
}