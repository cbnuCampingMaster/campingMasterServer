package cbnu.campingmaster.gocamping.controller;

import cbnu.campingmaster.gocamping.dto.GoCampingItemDto;
import cbnu.campingmaster.gocamping.service.CampsiteService;
import cbnu.campingmaster.gocamping.service.GoCampingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class GoCampingController {

    private final GoCampingService goCampingService;
    private final CampsiteService campsiteService;

    // 전체 데이터 호출
    @GetMapping("/basedList")
    public ResponseEntity<List<GoCampingItemDto>> baseSearch() throws IOException {
        JSONArray jsonArray = goCampingService.baseSearch();
        return getListResponseEntity(jsonArray);
    }

    // 키워드로 캠핑장 검색
    @GetMapping("/get-keyword")
    public ResponseEntity<?> keywordSearch(@RequestParam String keyword) throws IOException {
        JSONArray result = goCampingService.searchByKeyword(keyword);
        return getListResponseEntity(result);
    }

    // 위치 기반으로 캠핑장 검색
    @GetMapping("/get-location")
    public ResponseEntity<?> locationSearch(@RequestParam String mapX,
                                            @RequestParam String mapY,
                                            @RequestParam String radius) throws IOException {
        String result = goCampingService.searchByLocation(mapX, mapY, radius);
        return ResponseEntity.ok(result.toString());
    }

    private ResponseEntity<List<GoCampingItemDto>> getListResponseEntity(JSONArray jsonArray) {
        List<GoCampingItemDto> dtos = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            GoCampingItemDto dto = new GoCampingItemDto();
            goCampingService.setDtoFields(dto, jsonObject);
            dtos.add(dto);
            campsiteService.saveCampsite(dto);
        }
        return ResponseEntity.ok(dtos);
    }
}