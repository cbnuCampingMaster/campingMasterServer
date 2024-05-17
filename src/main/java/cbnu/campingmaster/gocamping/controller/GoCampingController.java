package cbnu.campingmaster.gocamping.controller;

import cbnu.campingmaster.gocamping.dto.GoCampingResponseDto;
import cbnu.campingmaster.gocamping.service.GoCampingService;
import cbnu.campingmaster.member.domain.Member;
import cbnu.campingmaster.member.dto.MemberSignInDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

@RestController
@RequiredArgsConstructor
@Slf4j
public class GoCampingController {

    private final GoCampingService goCampingService;

    @GetMapping("/get-keyword")
    public ResponseEntity<?> keywordSearch(@RequestParam String keyword) throws IOException, ParseException {
        return ResponseEntity.ok(goCampingService.searchByKeyword(keyword));
    }
}