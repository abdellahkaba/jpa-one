package com.example.jpaap.repositories;

import com.example.jpaap.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface PatientRepository extends JpaRepository<Patient,Long> {
    //Utilisation d'une methode generique
    //Touver les patients qui sont malades
    public List<Patient> findByMalade(boolean m);
    //La pagination d'une methode cree
    Page <Patient> findByMalade(boolean m, Pageable pageable);
    //Donne moi tous les patients dont le score est inferieur
 //   List<Patient> findByMaladeAndScoreLessThan(boolean m,int score);
 //   List<Patient> findByMaladeIsTrueAndScoreLessThanAndScoreLessThan(int score);
  //  List<Patient> findByDateNaissBetween(Date d1, Date d2);
  //  List<Patient> findByDateNaissBetweenAndMaladeIsTrueOrNomLike(Date d1, Date d2,String nom);
    //Deuxieme methode
    @Query("select p from Patient p where p.nom like :x and p.score<:y")
    List<Patient> chercherPatients(@Param("x") String nom, @Param("y")int scoreMin);


}
