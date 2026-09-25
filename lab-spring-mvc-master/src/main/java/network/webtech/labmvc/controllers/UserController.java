package network.webtech.labmvc.controllers;

import network.webtech.labmvc.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

//    @Autowired
//    private UserService userService;
//
//    @GetMapping("/{id}")
//    public ResponseEntity<User> findById(@PathVariable String id) {
//        User obj = this.userService.findById(id);
//        return ResponseEntity.ok().body(obj);
//    }
//
//    @PostMapping
//    public ResponseEntity<Void> create(@Valid @RequestBody UserCreateDTO obj) {
//        User user = this.userService.fromDTO(obj);
//        User newUser = this.userService.create(user);
//        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
//                .path("/{id}").buildAndExpand(newUser.getId()).toUri();
//        return ResponseEntity.created(uri).build();
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Void> update(@Valid @RequestBody UserUpdateDTO obj, @PathVariable String id) {
//        obj.setId(id);
//        User user = this.userService.fromDTO(obj);
//        this.userService.update(user);
//        return ResponseEntity.noContent().build();
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> delete(@PathVariable String id) {
//        this.userService.delete(id);
//        return ResponseEntity.noContent().build();
//    }

}
