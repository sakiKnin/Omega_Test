package com.omega.hrch.controller;

import com.omega.hrch.model.*;
import com.omega.hrch.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/ugovor")
public class UgovorController {
    @Autowired
    private UgovorService ugovorService;

    @GetMapping
    public List<Map<String, Object>> getAllUgovor(@RequestParam(required = false) String kupac,
                                                  @RequestParam(required = false) String status) {
        return ugovorService.getAllUgovor(kupac,status);
    }

    @RequestMapping(value="/getUgovorById", produces="application/json", method= RequestMethod.GET)
    public Ugovor getUgovor(@RequestParam(required = true) Long id){
        System.out.println(id);
        return ugovorService.findUgovorById(id);
    }

    @RequestMapping(value="/getUgovorArtiklsById", produces="application/json", method= RequestMethod.GET)
    public List<Artikl> getUgovorArtiklsById(@RequestParam(required = true) Long id){
        return ugovorService.getUgovorArtiklsById(id);
    }

    @RequestMapping(value="/dodajUgovor", produces="application/json", method= RequestMethod.POST)
    public Long dodajUgovor(@RequestBody Ugovor ugovor){
        return ugovorService.saveUgovor(ugovor);
    }

    @RequestMapping(value="/izmjeniUgovor", produces="application/json", method= RequestMethod.PUT)
    public Long izmjeniUgovor(@RequestBody Ugovor ugovor){
        return ugovorService.izmjeniUgovor(ugovor);
    }

    @RequestMapping(value="/izbrisiUgovor", produces="application/json", method= RequestMethod.DELETE)
    public Long izbrisiUgovor(@RequestParam(required = true) Long id){
        return ugovorService.izbrisiUgovor(id);
    }

}
