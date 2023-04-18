package com.techacademy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techacademy.entity.FollowList;

public interface FollowRepository extends JpaRepository<FollowList, Integer> {
    Boolean existsByEmployeeIdAndFollowerId(Integer eid,Integer fid);

    List<FollowList> findByEmployeeId(Integer id);

}
