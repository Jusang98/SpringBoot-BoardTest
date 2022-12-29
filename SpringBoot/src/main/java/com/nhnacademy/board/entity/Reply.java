package com.nhnacademy.board.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Replys")
public class Reply {
    @Id
    @Column(name = "reply_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long replyId;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "content")
    private String content;

//    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "reply_time")
    private Date replyTime;

}
