package com.example.service;

public interface MemberAuthService<T> {
    Boolean checkPassword(T member, String rawPasswordOrAuthKey);
}
