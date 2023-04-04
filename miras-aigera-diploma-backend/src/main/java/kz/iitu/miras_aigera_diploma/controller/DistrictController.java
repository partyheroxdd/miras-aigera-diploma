package kz.iitu.miras_aigera_diploma.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import kz.iitu.miras_aigera_diploma.model.dto.district.DistrictListInfoDto;
import kz.iitu.miras_aigera_diploma.service.DistrictService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/districts")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Tag(name = "Districts API", description = "Methods for work with districts")
@SecurityRequirement(name = "Bearer Authentication")
public class DistrictController {

  DistrictService districtService;

  @GetMapping("/{city}")
  @Operation(summary = "Method to get districts list only for Almaty, Astana")
  public ResponseEntity<List<DistrictListInfoDto>> findAll(
      @PathVariable @Parameter(description = "City name", example = "Almaty") String city) {
    return ResponseEntity.ok(districtService.findAllByCity(city));
  }
}
