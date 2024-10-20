package com.javaweb.api.admin;

import com.javaweb.constant.SystemConstant;
import com.javaweb.exception.MyException;
import com.javaweb.model.dto.PasswordDTO;
import com.javaweb.model.dto.UserDTO;
import com.javaweb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
public class UserAPI {

    @Autowired
    private IUserService userService;

    @PostMapping
    public ResponseEntity<UserDTO> createUsers(@RequestBody UserDTO newUser) {
        return ResponseEntity.ok(userService.insert(newUser));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUsers(@PathVariable("id") long id, @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.update(id, userDTO));
    }

    @PutMapping("/change-password/{id}")
    public ResponseEntity<String> changePasswordUser(@PathVariable("id") long id, @RequestBody PasswordDTO passwordDTO) {
        try {
            userService.updatePassword(id, passwordDTO);
            return ResponseEntity.ok(SystemConstant.UPDATE_SUCCESS);
        } catch (MyException e) {
            //LOGGER.error(e.getMessage());
            return ResponseEntity.ok(e.getMessage());
        }
    }

    @PutMapping("/password/{id}/reset")
    public ResponseEntity<UserDTO> resetPassword(@PathVariable("id") long id) {
        return ResponseEntity.ok(userService.resetPassword(id));
    }

    @PutMapping("/profile/{username}")
    public ResponseEntity<UserDTO> updateProfileOfUser(@PathVariable("username") String username, @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.updateProfileOfUser(username, userDTO));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUsers(@RequestBody long[] idList) {
        if (idList.length > 0) {
            userService.delete(idList);
        }
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/register")
    @Transactional
    public ResponseEntity<?> register(@Valid @RequestBody UserDTO userDTO , BindingResult result){

        try{
            if(result.hasErrors()){
                List<String> errorMessages = result.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .collect(Collectors.toList());
                return ResponseEntity.badRequest().body("errorMessages");
            }
            if(!userDTO.getPassword().equals(userDTO.getRetypePassword())) {
                return ResponseEntity.badRequest().body("Password not match");
            }
            userService.register(userDTO);
            return ResponseEntity.ok("REGISTER SUCCESSFULLY") ;
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Username already exists");
        }
    }
}
