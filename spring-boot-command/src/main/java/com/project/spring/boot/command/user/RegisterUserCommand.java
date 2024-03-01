package com.project.spring.boot.command.user;

import com.project.spring.boot.command.Command;
import com.project.spring.boot.command.model.request.RegisterUserCommandRequest;
import com.project.spring.boot.web.model.response.UserWebResponse;

public interface RegisterUserCommand extends Command<RegisterUserCommandRequest, UserWebResponse> {

}
