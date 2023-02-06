package kz.iitu.miras_aigera_diploma.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import kz.iitu.miras_aigera_diploma.model.dto.RoleDto;
import kz.iitu.miras_aigera_diploma.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

  @PostMapping
  @Operation(summary = "Save new role")
  public ResponseEntity<RoleDto> save(
      @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Request Body of Role") @RequestBody RoleDto roleDto) {
    return ResponseEntity.ok(roleService.saveRole(roleDto));
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Method to delete role by id")
  public ResponseEntity<?> delete(
      @Parameter(description = "Role id", example = "1", required = true) @PathVariable Long id) {
    roleService.deleteRole(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping("/all")
  @Operation(summary = "Method to get all roles")
  public ResponseEntity<List<RoleDto>> getAll() {
    return ResponseEntity.ok(roleService.getAllRoles());
  }
}
