package com.bank.profile.service;

import com.bank.profile.entity.Profile;

import java.util.List;
import java.util.Optional;

public interface ProfileService {
    List<Profile> getAllProfiles();
    Profile getProfileById(long id);
    void saveProfile(Profile profile);
    void updateProfile(long id, Profile updetedProfile);
    void deleteProfile(long id);
    Optional<Profile> findByInn(Long inn);
    Optional<Profile> findBySnils(Long snils);
}
