package com.ducks.goodsduck.commons.controller;

import com.ducks.goodsduck.commons.model.dto.JwtDto;
import com.ducks.goodsduck.commons.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
//@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class JwtController {

    private final JwtService jwtService;

    public JwtController(@Lazy JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/hi")
    public void aa(@RequestBody JwtDto jwtDto) {
        System.out.println(jwtDto.getId());
    }

    @PostMapping("/gen/token")
    public Map<String, Object> genToken(@RequestBody JwtDto jwtDto) {

        System.out.println("===================================");
        System.out.println("/api/v1/gen/token");
        System.out.println("===================================");

        String token = jwtService.createJwt(jwtDto, (20 * 1000 * 60));

        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("result", token);
        return map;
    }

    @GetMapping("/get/subject")
    public Map<String, Object> getSubject(@RequestParam("token") String token) {

        System.out.println("===================================");
        System.out.println("/api/v1/get/subject");
        System.out.println("===================================");

        Long subject = jwtService.getUserIdFromJwt(token);

        System.out.println("subject = " +  subject);

        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("result", subject);
        return map;
    }
}
