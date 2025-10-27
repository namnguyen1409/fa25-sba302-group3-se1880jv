package sba.group3.backendmvc.entity.user;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;
import sba.group3.backendmvc.entity.BaseEntity;
import sba.group3.backendmvc.entity.common.Address;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user_profile",
        schema = "user_management",
        indexes = {
        @Index(name = "idx_userprofile_full_name", columnList = "full_name")
})
@FieldDefaults(level = AccessLevel.PRIVATE)
@FieldNameConstants
public class UserProfile extends BaseEntity {
    @OneToOne(optional = false, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    User user;

    @Column(name = "full_name", nullable = false, length = 100)
    String fullName;

    @Column(name = "phone", length = 15, unique = true)
    String phone;

    @Column(name = "date_of_birth")
    LocalDate dateOfBirth;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name = "avatar_url")
    private String avatarUrl;

}