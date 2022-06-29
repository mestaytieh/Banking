package com.estaytieh.banking.repositories;

import com.estaytieh.banking.models.Transaction;
import com.estaytieh.banking.models.Userinfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserInfoRepository
    extends JpaRepository<Userinfo, Long> {

  Optional<Userinfo> findUserinfoByUserName(String userName);

}
