package com.techacademy.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.techacademy.entity.FollowList;
import com.techacademy.repository.FollowRepository;

@Service
public class FollowService {
    private final FollowRepository repository;

    public FollowService(FollowRepository repository) {
        this.repository = repository;
    }

    public List<FollowList> getFollowList(Integer id){
        return repository.findByEmployeeId(id);
    }

    public boolean exitsFollowList(Integer eid,Integer fid) {
        return repository.existsByEmployeeIdAndFollowerId(eid,fid);
    }

    public FollowList saveFollowList(FollowList follow) {
        return repository.save(follow);

    }
}
