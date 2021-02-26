package com.tencent.passport.musician.repository.dao;

import com.tencent.passport.musician.model.dmo.TAccountRoleRelation;
import com.tencent.passport.musician.model.dmo.TAccountRoleRelationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TAccountRoleRelationMapper {
    int countByExample(TAccountRoleRelationExample example);

    int deleteByExample(TAccountRoleRelationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TAccountRoleRelation record);

    int insertSelective(TAccountRoleRelation record);

    List<TAccountRoleRelation> selectByExample(TAccountRoleRelationExample example);

    TAccountRoleRelation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TAccountRoleRelation record, @Param("example") TAccountRoleRelationExample example);

    int updateByExample(@Param("record") TAccountRoleRelation record, @Param("example") TAccountRoleRelationExample example);

    int updateByPrimaryKeySelective(TAccountRoleRelation record);

    int updateByPrimaryKey(TAccountRoleRelation record);
}