package com.netflix_clone.userservice.repository.profileRepository;

import com.netflix_clone.userservice.repository.domains.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long>, ProfileRepositoryCustom{
}
