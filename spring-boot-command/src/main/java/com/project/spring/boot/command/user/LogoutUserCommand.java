package com.project.spring.boot.command.user;

import com.project.spring.boot.command.Command;
import com.project.spring.boot.command.model.request.user.LogoutUserCommandRequest;
import com.project.spring.boot.web.model.response.user.UserWebResponse;

public interface LogoutUserCommand extends Command<LogoutUserCommandRequest, UserWebResponse> {
}
