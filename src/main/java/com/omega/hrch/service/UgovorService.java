package com.omega.hrch.service;

import com.omega.hrch.model.Artikl;
import com.omega.hrch.model.Ugovor;
import com.omega.hrch.repository.ArtiklRepo;
import com.omega.hrch.repository.UgovorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UgovorService {
    @Autowired
    private UgovorRepo ugovorRepo;
    @Autowired
    private ArtiklRepo artiklRepo;

    private Map<String,String> mapping = new HashMap<>(){{put("KREIRANO","NARUCENO");put("NARUCENO","ISPORUCENO");}};

    public List<Map<String, Object>> getAllUgovor(String kupac, String status) {

        List<Map<String, Object>> rsp = new ArrayList<>();

        if(kupac!=null && status!=null){
            System.out.println("==============FILTER BY STATUS && KUPAC=============");
            for(Ugovor ugovor : ugovorRepo.findUgovorsByKupac(kupac)){
                Map<String, Object> tmp = new LinkedHashMap<>();
                if(ugovor.getStatus().equals(status)){
                    tmp.put("id",ugovor.getId());
                    tmp.put("kupac",ugovor.getKupac());
                    tmp.put("broj_ugovora",ugovor.getBroj_ugovora());
                    tmp.put("datum_akontacije",ugovor.getDatum_akontacije());
                    tmp.put("rok_isporuke",ugovor.getRok_isporuke());
                    tmp.put("status",ugovor.getStatus());
                    rsp.add(tmp);
                }
            }
        }else if(kupac!=null){
            System.out.println("==============FILTER BY KUPAC=============");
            for(Ugovor ugovor : ugovorRepo.findUgovorsByKupac(kupac)){
                Map<String, Object> tmp = new LinkedHashMap<>();
                    tmp.put("id",ugovor.getId());
                    tmp.put("kupac",ugovor.getKupac());
                    tmp.put("broj_ugovora",ugovor.getBroj_ugovora());
                    tmp.put("datum_akontacije",ugovor.getDatum_akontacije());
                    tmp.put("rok_isporuke",ugovor.getRok_isporuke());
                    tmp.put("status",ugovor.getStatus());
                    rsp.add(tmp);
            }
        }else if(status!=null){
            System.out.println("==============FILTER BY STATUS=============");
            for(Ugovor ugovor : ugovorRepo.findAllByStatusIs(status)){
                Map<String, Object> tmp = new LinkedHashMap<>();
                tmp.put("id",ugovor.getId());
                tmp.put("kupac",ugovor.getKupac());
                tmp.put("broj_ugovora",ugovor.getBroj_ugovora());
                tmp.put("datum_akontacije",ugovor.getDatum_akontacije());
                tmp.put("rok_isporuke",ugovor.getRok_isporuke());
                tmp.put("status",ugovor.getStatus());
                System.out.println(tmp);
                rsp.add(tmp);
            }
        }else{
            System.out.println("==============NO FILTERING=============");
            for(Ugovor ugovor : ugovorRepo.findAll()){
                Map<String, Object> tmp = new LinkedHashMap<>();
                tmp.put("id",ugovor.getId());
                tmp.put("kupac",ugovor.getKupac());
                tmp.put("broj_ugovora",ugovor.getBroj_ugovora());
                tmp.put("datum_akontacije",ugovor.getDatum_akontacije());
                tmp.put("rok_isporuke",ugovor.getRok_isporuke());
                tmp.put("status",ugovor.getStatus());
                rsp.add(tmp);
            }
        }
        return rsp;
    }

    public Ugovor findUgovorById(Long id) {
        try{
            return ugovorRepo.findById(id).orElseThrow(() -> new RuntimeException("Ugovor not found"));
        }catch(Exception exp){
                return new Ugovor();
        }

    }

    public List<Artikl> getUgovorArtiklsById(Long id) {
        try{
            return ugovorRepo.getReferenceById(id).getArtikls();
        }catch(Exception exp){
            return new ArrayList<>();
        }
    }

    public Long saveUgovor(Ugovor ugovor) {
        Ugovor newUgovor = new Ugovor(ugovor.getId(),ugovor.getKupac(),ugovor.getBroj_ugovora(),ugovor.getDatum_akontacije(),ugovor.getRok_isporuke(),"KREIRANO");
        ugovorRepo.save(newUgovor);
        for (Artikl artikl : ugovor.getArtikls()) {
            artikl.setStatus("KREIRANO");
            artikl.setUgovor(ugovor);
        }
        artiklRepo.saveAll(ugovor.getArtikls());
        return 1L;
    }

    public Long izmjeniUgovor(Ugovor ugovor) {
        System.out.println("==========IZMJENI UGOVOR=========");
        System.out.println(ugovor);
        Optional<Ugovor> myUgovor = ugovorRepo.findById(ugovor.getId());

        if(myUgovor.isPresent()){
            Ugovor editedUgovor = myUgovor.get();

            if(!ugovor.getKupac().equals(editedUgovor.getKupac())){
                editedUgovor.setKupac(ugovor.getKupac());
            }

            if(!ugovor.getBroj_ugovora().equals(editedUgovor.getBroj_ugovora())){
                editedUgovor.setBroj_ugovora(ugovor.getBroj_ugovora());
            }

            if(!ugovor.getDatum_akontacije().equals(editedUgovor.getDatum_akontacije())){
                editedUgovor.setDatum_akontacije(ugovor.getDatum_akontacije());
            }

            if(!ugovor.getRok_isporuke().equals(editedUgovor.getRok_isporuke())){
                if(editedUgovor.getStatus().equals("KREIRANO")||editedUgovor.getStatus().equals("NARUCENO")) {
                    editedUgovor.setRok_isporuke(ugovor.getRok_isporuke());
                }else{
                    return -1L;
                }
            }

            if(!ugovor.getRok_isporuke().equals(editedUgovor.getRok_isporuke())){
                editedUgovor.setRok_isporuke(ugovor.getRok_isporuke());
            }

            if(!ugovor.getStatus().equals(editedUgovor.getStatus())){
                if(ugovor.getStatus().equals(mapping.get(editedUgovor.getStatus()))) {
                    editedUgovor.setStatus(ugovor.getStatus());
                }else{
                    return -1L;
                }
            }

            System.out.println(editedUgovor);
            ugovorRepo.save(editedUgovor);

            return 1L;

        }else{
            return -1L;
        }
    }

    public Long izbrisiUgovor(Long id) {
        try {
            Ugovor ugovor = ugovorRepo.getReferenceById(id);
            if(ugovor.getStatus().equals("KREIRANO")) {
                ugovorRepo.deleteById(id);
                for (Artikl artikl : ugovor.getArtikls()) {
                    artiklRepo.deleteById(artikl.getId());
                }
                return 1L;
            }else{
                return -1L;
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
            return -1L;
        }
    }

}