package sn.esmt.emploi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.esmt.emploi.entites.AppCVEntity;

@Repository
public interface AppCVRepository extends JpaRepository<AppCVEntity, Integer> {
    AppCVEntity findById(int id);
}
