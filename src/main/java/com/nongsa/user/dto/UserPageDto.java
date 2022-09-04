package com.nongsa.user.dto;

import com.nongsa.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserPageDto {
    private boolean pageOwnerState;
    private int boardCount;
    private boolean subscribeState;
    private int subscribeCount;
    private int subscribedCount;
    private User user;
}
