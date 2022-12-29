//package com.nhnacademy.board.entity;
//
//import lombok.*;
//
//import javax.persistence.*;
//import java.io.Serializable;
//
//@NoArgsConstructor
//@Getter
//@Setter
//@Entity
//@Table(name = "Likes")
//public class Like {
//
//    @EmbeddedId
//    private Pk pk;
//    @MapsId("postId")
//    @ManyToOne
//    @JoinColumn(name = "post_id")
//    private Post post;
//
//    @MapsId("userId")
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;
//
//    @NoArgsConstructor
//    @AllArgsConstructor
//    @EqualsAndHashCode
//    @Getter
//    @Setter
//    @Embeddable
//    public static class Pk implements Serializable {
//        @Column
//        private Long userId;
//        @Column
//        private Long postId;
//    }
//}
