package kz.iitu.miras_aigera_diploma.controller;

import java.util.List;
import kz.iitu.miras_aigera_diploma.model.entity.Role;
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
@RequestMapping("/api/role")
public class RoleController {

  private final RoleService roleService;

  @GetMapping("/{id}")
  public ResponseEntity<Role> getRole(@PathVariable final Long id) {
    return ResponseEntity.ok(roleService.getRole(id));
  }

  @PostMapping
  public ResponseEntity<Role> save(@RequestBody Role role) {
    return ResponseEntity.ok(roleService.saveRole(role));
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<?> delete(@PathVariable Long id) {
    roleService.deleteRole(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping(value = "/all")
  public ResponseEntity<List<Role>> getAll() {
    return ResponseEntity.ok(roleService.getAllRoles());
  }
}
