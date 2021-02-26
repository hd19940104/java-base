package com.tencent.passport.musician.repository.dao;

import com.tencent.passport.musician.model.dmo.TRoleRightRelation;
import com.tencent.passport.musician.model.dmo.TRoleRightRelationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TRoleRightRelationMapper {
    int countByExample(TRoleRightRelationExample example);

    int deleteByExample(TRoleRightRelationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TRoleRightRelation record);

    int insertSelective(TRoleRightRelation record);

    List<TRoleRightRelation> selectByExample(TRoleRightRelationExample example);

    TRoleRightRelation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TRoleRightRelation record, @Param("example") TRoleRightRelationExample example);

    int updateByExample(@Param("record") TRoleRightRelation record, @Param("example") TRoleRightRelationExample example);

    int updateByPrimaryKeySelective(TRoleRightRelation record);

    int updateByPrimaryKey(TRoleRightRelation record);
}