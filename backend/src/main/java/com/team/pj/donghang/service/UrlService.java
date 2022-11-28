package com.team.pj.donghang.service;

import com.team.pj.donghang.domain.entity.User;

public interface UrlService {
    String generateUrl (User user);
    boolean isUrlExist(String url);
    void urlDelete(String url);

    User getUrlUser(String url);
}
