package com.tianqi.common.validate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yuantianqi
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidateEntity {
    private String fields;
    private String message;
}
