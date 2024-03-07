package com.project.spring.boot.command.user;

import com.project.spring.boot.command.Command;
import com.project.spring.boot.command.model.request.user.GetUserByTokenCommandRequest;
import com.project.spring.boot.web.model.response.user.UserWebResponse;

public interface GetUserByTokenCommand extends Command<GetUserByTokenCommandRequest, UserWebResponse> {
}
