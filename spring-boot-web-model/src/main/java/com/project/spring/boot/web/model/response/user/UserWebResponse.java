package com.project.spring.boot.web.model.response.user;

import com.project.spring.boot.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserWebResponse implements Serializable {
    private User user;
}
