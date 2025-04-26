package com.yangsunkue.suncar.controller.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 서버레벨 데이터 조회를 담당하는 컨트롤러 입니다.
 */
@RestController
public class ServerController {

    @Autowired
    private Environment env;

    /**
     * 현재 서버가 사용중인 포트번호를 반환합니다.
     *
     * @return 현재 사용중인 포트번호
     */
    @GetMapping("/serverProfile")
    public String getServerProfile() {
        return env.getProperty("server.port", "8081");
    }
}
