package com.hk.review.repository;

import com.hk.review.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findBySerialId(Long serialId);

    @Query("select u.id as id from User u where u.id = :id and u.isLogin = true")
    Optional<UserSecurityForm> findSecurityFormById(Long id);

    //@Modifying은 데이터베이스를 수정하는 쿼리에 사용된다. 보통 update, delete 쿼리와 함께 사용된다.
    //ClearAutomatically 속성은 수정 작업 후에 영속성 컨텍스를 지우도록 한다. 이는 변경된 엔티티 상태와 데이터베이스 상태를 일치시키기 위함이다.
    @Modifying(clearAutomatically = true)
    @Query("update User u set u.refreshToken = :refreshToken, u.isLogin = :isLogin where u.id = :id")
    void updateRefreshTokenAndLoginStatus(Long id, String refreshToken, Boolean isLogin);

    //특정 필드를 반환하기 위해 정의된 인터페이스이다. Spring Data JPA는 이 인터페이스를 사용하여 결과를 매핑한다.
    //인터페이스 프로젝션을 통해서 필요한 필드만 조회할 수 있다.
    interface UserSecurityForm {
        Long getId();
        String getPassword();
    }

}
