package com.nongsa.sns.model;

import java.sql.Timestamp;

import javax.persistence.*;

import com.nongsa.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(name = "subscribe_unique", columnNames = {"fromUserId", "toUserId"})})
public class Subscribe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "fromUserId")
    @ManyToOne(fetch = FetchType.LAZY)
    private User fromUser;

    @JoinColumn(name = "toUserId")
    @ManyToOne(fetch = FetchType.LAZY)
    private User toUser;

    @CreationTimestamp
    private Timestamp createDate;
}
