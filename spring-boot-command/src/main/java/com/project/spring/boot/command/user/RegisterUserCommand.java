package com.project.spring.boot.command.user;

import com.project.spring.boot.command.Command;
import com.project.spring.boot.command.model.request.user.RegisterUserCommandRequest;
import com.project.spring.boot.web.model.response.user.UserWebResponse;

public interface RegisterUserCommand extends Command<RegisterUserCommandRequest, UserWebResponse> {

}
