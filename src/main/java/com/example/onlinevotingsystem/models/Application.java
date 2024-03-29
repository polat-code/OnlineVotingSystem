package com.example.onlinevotingsystem.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_id", nullable = false)
    private Long applicationId;

    @Column(name = "transcript_path", nullable = false)
    private String transcriptPath;

    @Column(name = "application_request", nullable = false)
    private String applicationRequest;

    @Column(name = "student_certificate", nullable = false)
    private String studentCertificate;

    @Column(name = "political_party_membership_inquiry", nullable = false)
    private String political;

    @OneToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name="f_user_id",
            referencedColumnName = "user_id"
    )
    private User user;

    // Status has 2 value approved or rejected
    @Column(name = "is_approved")
    private Boolean isApproved ;


    @Column(name = "is_review")
    private Boolean isReview;
}
