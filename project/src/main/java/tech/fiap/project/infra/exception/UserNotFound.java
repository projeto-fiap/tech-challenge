package tech.fiap.project.infra.exception;

import org.springframework.http.HttpStatus;

public class UserNotFound extends BusinessException{
    public UserNotFound(String id) {
        super( "user.not.found", HttpStatus.NOT_FOUND,null, id);
    }

}
