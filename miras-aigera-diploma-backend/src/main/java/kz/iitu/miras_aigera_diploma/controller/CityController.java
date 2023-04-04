package kz.iitu.miras_aigera_diploma.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import kz.iitu.miras_aigera_diploma.model.dto.city.CityListInfoDto;
import kz.iitu.miras_aigera_diploma.service.CityService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cities")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Tag(name = "Cities API", description = "Methods for work with cities")
@SecurityRequirement(name = "Bearer Authentication")
public class CityController {

  CityService cityService;

  @GetMapping
  @Operation(summary = "Method to get cities list")
  public ResponseEntity<List<CityListInfoDto>> findAll() {
    return ResponseEntity.ok(cityService.findAll());
  }
}
