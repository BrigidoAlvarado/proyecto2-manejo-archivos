package org.archivos.ecommercegt.controllers;

import lombok.RequiredArgsConstructor;
import org.archivos.ecommercegt.config.ApplicationConfig;
import org.archivos.ecommercegt.dto.user.*;
import org.archivos.ecommercegt.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * The type User controller.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(ApplicationConfig.BASE_URL + "/user")
public class UserController {

    private final UserService userService;

    /**
     * Find user by top spent response entity.
     *
     * @param startDate the start date
     * @param endDate   the end date
     * @return the response entity
     */
    @GetMapping("/top/spent")
    public ResponseEntity<List<UserEarning>> findUserByTopSpent(
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate
    ) {
        List<UserEarning> userTopSpents = userService.findUserByTopSpent(startDate, endDate);
        return ResponseEntity.ok(userTopSpents);
    }

    /**
     * Find user by top products send response entity.
     *
     * @param startDate the start date
     * @param endDate   the end date
     * @return the response entity
     */
    @GetMapping("/top/products-send")
    public ResponseEntity<List<UserProductsSend>> findUserByTopProductsSend(
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate
    ) {
        List<UserProductsSend> userTopSpents = userService.finUserByProductsSend(startDate, endDate);
        return ResponseEntity.ok(userTopSpents);
    }

    /**
     * Find user by packages ordered response entity.
     *
     * @param startDate the start date
     * @param endDate   the end date
     * @return the response entity
     */
    @GetMapping("/top/packages-ordered")
    public ResponseEntity<List<UserPackagesOrdered>> findUserByPackagesOrdered(
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate
    ) {
        List<UserPackagesOrdered> userPackagesOrdered = userService.finUserByPackagesOrdered(startDate, endDate);
        return ResponseEntity.ok(userPackagesOrdered);
    }

    /**
     * Find users by products approve response entity.
     *
     * @return the response entity
     */
    @GetMapping("/top/products-approve")
    public ResponseEntity<List<UserProductsApprove>> findUsersByProductsApprove() {
        List<UserProductsApprove> userPackagesOrdered = userService.finUserByProductsApprove();
        return ResponseEntity.ok(userPackagesOrdered);
    }

    /**
     * Gets all users.
     *
     * @return the all users
     */
    @GetMapping("/all")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> userResponses = userService.getAllUsers();
        return ResponseEntity.ok(userResponses);
    }

    /**
     * Gets user by id.
     *
     * @param id the id
     * @return the user by id
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(
            @PathVariable int id
    ) {
        UserResponse userResponse = userService.getUserResponseById( id );
        return ResponseEntity.ok(userResponse);
    }

    /**
     * Update user response entity.
     *
     * @param user the user
     * @return the response entity
     */
    @PutMapping()
    public ResponseEntity<?> updateUser(
            @RequestBody UpdateUserRequest user
    ){
        userService.updateUser( user );
        return ResponseEntity.ok().build();
    }
}
