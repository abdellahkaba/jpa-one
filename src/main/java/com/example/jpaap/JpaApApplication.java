package com.example.jpaap;

import com.example.jpaap.entities.Patient;
import com.example.jpaap.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class JpaApApplication implements CommandLineRunner {
    @Autowired
    private PatientRepository patientRepository;
    public static void main(String[] args) {
        SpringApplication.run(JpaApApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //Enregister des patients par la methode save()
        patientRepository.save(
                new Patient(null,"Kaba Abdoulaye",new Date(),false,50));
        patientRepository.save(
                new Patient(null,"Mariam Conde",new Date(),false,90));
        patientRepository.save(
                new Patient(null,"Sira Kalle",new Date(),true,80));
        patientRepository.save(
                new Patient(null,"Mohamed Diallo",new Date(),false,97));
        patientRepository.save(
                new Patient(null,"Sidiki Camara",new Date(),true,200));
        patientRepository.save(
                new Patient(null,"Aram M'bye",new Date(),true,90));
        patientRepository.save(
                new Patient(null,"Zeinab Ndia",new Date(),false,56));
        patientRepository.save(
                new Patient(null,"Oumar Traore",new Date(),true,87));
        patientRepository.save(
                new Patient(null,"Koutoubou Kaba",new Date(),false,91));
        patientRepository.save(
                new Patient(null,"Jean Leno",new Date(),true,400));
        //Afficher la liste des patients par la methode findAll()
        List<Patient> patientList = patientRepository.findAll();
        //Chercher les patients par leur nom
        List<Patient> patientList1 = patientRepository.chercherPatients("%m%",97);
        //Affiche la liste des patients qui sont malade et pageable aussi
        Page<Patient> byMalade = patientRepository.findByMalade(true,PageRequest.of(0,4));
        patientList1.forEach(patient -> {
            System.out.println("=========================");
            System.out.println(patient.getId());
            System.out.println(patient.getNom());
            System.out.println(patient.getDateNaiss());
            System.out.println(patient.isMalade());
            System.out.println(patient.getScore());
        });
        System.out.println("*******************************");
        //Trouver l'information d'un patient par la methode findById()
        Patient patient = patientRepository.findById(1L).orElse(null);
        if (patient!=null){
            System.out.println(patient.getNom());
            System.out.println(patient.isMalade());
        }
        //On peut modifier une information de patient
        patient.setScore(100);
        //Puis on enregistre encore si l'id n'existe pas il insere au cas ou il fait la mise a jour
        patientRepository.save(patient);
        //Supprimer un patient
       // patientRepository.deleteById(1L);
        //Pagination
        //Page<Patient> patientPage = patientRepository.findAll(PageRequest.of(0,5));
      //  System.out.println("Total Page:" +patientPage.getTotalPages());
       // System.out.println("Total Element:" +patientPage.getTotalPages());
      //   System.out.println("Numero Page:" +patientPage.getTotalPages());
       // List<Patient> content = (List<Patient>) patientList.getFirst();

    }
}
