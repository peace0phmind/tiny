package cn.know.act.proton.system.web.rest;


import cn.know.act.proton.config.security.SecurityUtils;
import cn.know.act.proton.system.repository.jpa.UserJpaRepository;
import cn.know.act.proton.system.service.dto.MenuDTO;
import cn.know.act.proton.system.service.dto.PasswordDTO;
import cn.know.act.proton.system.service.dto.UserDTO;
import cn.know.act.proton.system.service.inf.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * REST controller for managing the current user's account.
 */
@RestController
@RequestMapping("/api")
public class AccountResource {

    private final Logger log = LoggerFactory.getLogger(AccountResource.class);
    private final UserJpaRepository userJpaRepository;
    private final UserService userService;

    public AccountResource(UserJpaRepository userJpaRepository, UserService userService) {
        this.userJpaRepository = userJpaRepository;
        this.userService = userService;
    }

    /**
     * {@code GET  /authenticate} : check if the user is authenticated, and return its login.
     *
     * @param request the HTTP request.
     * @return the login if the user is authenticated.
     */
    @GetMapping("/authenticate")
    public String isAuthenticated(HttpServletRequest request) {
        log.debug("REST request to check if the current user is authenticated");
        return request.getRemoteUser();
    }

    /**
     * {@code GET  /account} : get the current user.
     *
     * @return the current user.
     * @throws RuntimeException {@code 500 (Internal Server Error)} if the user couldn't be returned.
     */
    @GetMapping("/account")
    public UserDTO getAccount() {
        return userService.getUserWithAuthorities()
                .orElseThrow(() -> new AccountResourceException("User could not be found"));
    }

    @PostMapping("/account/password")
    public Boolean resetPassword(@Valid @RequestBody PasswordDTO passwordDTO) {
        return SecurityUtils.getCurrentUser().map(user -> userService.resetPassword(user.getId(), passwordDTO, true)).orElse(false);
    }

    /**
     * {@code GET  /account} : get the current user menus.
     *
     * @return the current user menus.
     * @throws RuntimeException {@code 500 (Internal Server Error)} if the user couldn't be returned.
     */
    @GetMapping("/account-menu")
    public List<MenuDTO> getMenus() {
        return userService.getUserMenus()
                .orElseThrow(() -> new AccountResourceException("User could not be found"));
    }

    private static class AccountResourceException extends RuntimeException {
        private AccountResourceException(String message) {
            super(message);
        }
    }

//    /**
//     * {@code POST  /account} : update the current user information.
//     *
//     * @param userDTO the current user information.
//     * @throws EmailAlreadyUsedException {@code 400 (Bad Request)} if the email is already used.
//     * @throws RuntimeException {@code 500 (Internal Server Error)} if the user login wasn't found.
//     */
//    @PostMapping("/account")
//    public void saveAccount(@Valid @RequestBody UserDTO userDTO) {
//        User user = SecurityUtils.getCurrentUser().orElseThrow(() -> new AccountResourceException("Current user login not found"));
//        Optional<User> existingUser = userRepository.findOneByEmailIgnoreCase(userDTO.getEmail());
//        if (existingUser.isPresent() && (!existingUser.get().getLogin().equalsIgnoreCase(userLogin))) {
//            throw new EmailAlreadyUsedException();
//        }
//        Optional<User> user = userRepository.findOneByLogin(userLogin);
//        if (!user.isPresent()) {
//            throw new AccountResourceException("User could not be found");
//        }
//        userService.updateUser(userDTO.getFirstName(), userDTO.getLastName(), userDTO.getEmail(),
//            userDTO.getLangKey(), userDTO.getImageUrl());
//    }
//
//    /**
//     * {@code POST  /account/change-password} : changes the current user's password.
//     *
//     * @param passwordChangeDto current and new password.
//     * @throws InvalidPasswordException {@code 400 (Bad Request)} if the new password is incorrect.
//     */
//    @PostMapping(path = "/account/change-password")
//    public void changePassword(@RequestBody PasswordDTO passwordChangeDto) {
//        if (!checkPasswordLength(passwordChangeDto.getNewPassword())) {
//            throw new InvalidPasswordException();
//        }
//        userService.changePassword(passwordChangeDto.getCurrentPassword(), passwordChangeDto.getNewPassword());
//    }
//
//    /**
//     * {@code POST   /account/reset-password/init} : Send an email to reset the password of the user.
//     *
//     * @param mail the mail of the user.
//     * @throws EmailNotFoundException {@code 400 (Bad Request)} if the email address is not registered.
//     */
//    @PostMapping(path = "/account/reset-password/init")
//    public void requestPasswordReset(@RequestBody String mail) {
//       mailService.sendPasswordResetMail(
//           userService.requestPasswordReset(mail)
//               .orElseThrow(EmailNotFoundException::new)
//       );
//    }
//
//    /**
//     * {@code POST   /account/reset-password/finish} : Finish to reset the password of the user.
//     *
//     * @param keyAndPassword the generated key and the new password.
//     * @throws InvalidPasswordException {@code 400 (Bad Request)} if the password is incorrect.
//     * @throws RuntimeException {@code 500 (Internal Server Error)} if the password could not be reset.
//     */
//    @PostMapping(path = "/account/reset-password/finish")
//    public void finishPasswordReset(@RequestBody KeyAndPasswordVM keyAndPassword) {
//        if (!checkPasswordLength(keyAndPassword.getNewPassword())) {
//            throw new InvalidPasswordException();
//        }
//        Optional<User> user =
//            userService.completePasswordReset(keyAndPassword.getNewPassword(), keyAndPassword.getKey());
//
//        if (!user.isPresent()) {
//            throw new AccountResourceException("No user was found for this reset key");
//        }
//    }
//
//    private static boolean checkPasswordLength(String password) {
//        return !StringUtils.isEmpty(password) &&
//            password.length() >= ManagedUserVM.PASSWORD_MIN_LENGTH &&
//            password.length() <= ManagedUserVM.PASSWORD_MAX_LENGTH;
//    }
}
