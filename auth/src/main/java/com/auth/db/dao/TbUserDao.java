package com.auth.db.dao;

import com.auth.db.pojo.TbUser;
import com.common.user.UserInfo;
import com.ruoyi.common.datasource.annotation.Master;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Mapper
@Master
public interface TbUserDao {
    public Integer haveRootUser();

    public int insert(HashMap param);

    public Integer searchIdByOpenId(String openId);

    public Set<String> searchUserPermissions(int userId);

    public TbUser searchById(int userId);

    public HashMap searchNameAndDept(int userId);

    public String searchUserHiredate(int userId);

    public HashMap searchUserSummary(int userId);

    public ArrayList<HashMap> searchUserGroupByDept(String keyword);

    public ArrayList<HashMap> searchMembers(List param);

    public HashMap searchUserInfo(int userId);

    public int searchDeptManagerId(int id);

    public int searchGmId();

    public List<HashMap> selectUserPhotoAndName(List param);

    public String searchMemberEmail(int id);

    public long searchUserCountInDept(int deptId);

    public int searchUserIdByEmail(String email);

    public int activeUserAccount(HashMap param);

    public int updateUserInfo(HashMap param);

    public int deleteUserById(int id);
}