package com.test.okipotest.controller;

import com.test.okipotest.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class ControllerLayer {
    @Autowired
    private ServiceLayer serviceLayer;

    @GetMapping("/checkAddress/{address}")
    public ResponseEntity<?> checkAddress(@PathVariable("address") String addr) {
        if (serviceLayer.checkAddress(addr).equals("operazione andata a buon fine"))
            return ResponseEntity.ok("operazione confermata");
        return ResponseEntity.badRequest().body("errore all'interno dell'operazione");
    }

    @GetMapping("/checkTransaction/{address}/{page}")
    public ResponseEntity<?> checkTransaction(@PathVariable("address") String addr, @PathVariable("page") int page) {
        return ResponseEntity.ok(serviceLayer.checkTransaction(addr,page-1));
    }


}
