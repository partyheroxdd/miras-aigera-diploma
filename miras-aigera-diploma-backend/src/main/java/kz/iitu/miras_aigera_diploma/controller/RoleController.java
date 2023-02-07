package kz.iitu.miras_aigera_diploma.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import kz.iitu.miras_aigera_diploma.model.dto.user.RoleDto;
import kz.iitu.miras_aigera_diploma.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/roles")
@Tag(name = "Roles API", description = "Methods for work with roles")
public class RoleController {

  private final RoleService roleService;

  @GetMapping("/{id}")
  @Operation(summary = "Method to get role with Id")
  public ResponseEntity<RoleDto> getRole(
      @Parameter(description = "Role id", example = "1", required = true) @PathVariable final Long id) {
    return ResponseEntity.ok(roleService.getRole(id));
  }

  @GetMapping("/all")
  @Operation(summary = "Method to get all roles")
  public ResponseEntity<List<RoleDto>> getAll() {
    return ResponseEntity.ok(roleService.getAllRoles());
  }
}
