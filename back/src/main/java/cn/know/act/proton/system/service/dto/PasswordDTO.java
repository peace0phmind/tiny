package cn.know.act.proton.system.service.dto;

import lombok.Data;

import javax.validation.constraints.Size;

/**
 * A DTO representing a password change required data - current and new password.
 */
@Data
public class PasswordDTO {
    @Size(max = 64)
    private String currentPassword;

    @Size(min = 6, max = 64)
    private String newPassword;
}
