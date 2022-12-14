package ir.maktab.data.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@NamedQueries(
        @NamedQuery(name = "findCardByNumber", query = "FROM CreditCard c WHERE c.cardNum=:cardNumber")
)
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    Long id;
    @Column(length = 16)
    String cardNum;
    @Column(length = 4)
    String cvv2;
    @Temporal(value = TemporalType.DATE)
    Date expiryDate;
}
