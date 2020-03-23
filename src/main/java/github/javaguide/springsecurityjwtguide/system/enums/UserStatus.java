package github.javaguide.springsecurityjwtguide.system.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * @author Chr.yl
 */

public enum UserStatus {
    CAN_USE("can use in system"),
    CAN_NOT_USE("can not use in system")

    //
    ;

    //    private String code;
    private String status;

    UserStatus(String status) {
        this.status = status;
    }
//
//
//    UserStatus(String code, String status) {
//        this.code = code;
//        this.status = status;
//    }
//
//    public String getCode() {
//        return code;
//    }
//
//    public String getStatus() {
//        return status;
//    }

    public String getName() {
        return this.status;
    }

    @JsonCreator
    public static UserStatus fromRole(String status) {
        for (UserStatus type : UserStatus.values()) {
            if (type.getName().equals(status)) {
                return type;
            }
        }
        return null;
    }
}
