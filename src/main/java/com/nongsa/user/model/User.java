package com.nongsa.user.model;


import java.sql.Timestamp;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nongsa.user.constant.RoleType;
import com.nongsa.common.model.BaseEntity;
import com.nongsa.user.dto.JoinDto;
import com.nongsa.sns.model.Board;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 2, max = 100)
    @Column(unique = true)
    private String username;

    @NotBlank(message = "비밀번호는 필수 입력값입니다.")
    private String password;

    @NotBlank(message = "이메일은 필수 입력값입니다.")
    @Column(unique = true)
    private String email;

    @Column(length = 50)
    private String crop;

    @Column(length = 50)
    private String location;

    @Enumerated(EnumType.STRING)
    private RoleType role;

    private String oauth;//kakao,google

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"user"})
    @OrderBy("id desc")
    private List<Board> boards;

    @CreationTimestamp
    private Timestamp createDate;

    public static User createUser(JoinDto joinDto, BCryptPasswordEncoder passwordEncoder){
        User user = new User().builder()
                .username(joinDto.getUsername())
                .password(passwordEncoder.encode(joinDto.getPassword()))
                .email(joinDto.getEmail())
                .location(joinDto.getLocation())
                .crop(joinDto.getCrop())
                .role(RoleType.ADMIN).build();
        return user;
    }
}
