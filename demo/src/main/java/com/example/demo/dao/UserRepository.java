    package com.example.demo.dao;

    import com.example.demo.model.User;
    import org.springframework.context.annotation.Bean;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.stereotype.Repository;

    @Repository
    public interface UserRepository extends JpaRepository<User, Integer> {
        boolean existsByEmailid(String emailid);
        User findByEmailid(String emailid);
    }
