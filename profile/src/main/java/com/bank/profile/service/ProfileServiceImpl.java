package com.bank.profile.service;

import com.bank.profile.entity.Profile;
import com.bank.profile.exception.ProfileNotFoundException;
import com.bank.profile.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;

    @Autowired
    public ProfileServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public List<Profile> getAllProfiles() {
        return profileRepository.findAll();
    }

    @Override
    public Profile getProfileById(long id) {
        return profileRepository.findById(id).orElseThrow(ProfileNotFoundException::new);
    }

    @Override
    @Transactional
    public void saveProfile(Profile profile) {
        profileRepository.save(profile);
    }

    @Override
    @Transactional
    public void updateProfile(long id, Profile updetedProfile) {
        updetedProfile.setId(id);
        profileRepository.save(updetedProfile);
    }

    @Override
    @Transactional
    public void deleteProfile(long id) {
        profileRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Optional<Profile> findByInn(Long inn) {
        return profileRepository.findByInn(inn);
    }

    @Override
    @Transactional
    public Optional<Profile> findBySnils(Long snils) {
        return profileRepository.findBySnils(snils);
    }
}
