package entity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "student_2")
public class Student implements Serializable {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student1_seq")
    @SequenceGenerator(name = "student1_seq", sequenceName = "student1_seq", allocationSize = 1, initialValue = 1)
    int id;

    @Column(name = "full_name", nullable = false)
    String fullName;

    @Column(name = "birthday", nullable = false)
    Date birthday;

    @Column(name = "class_name", nullable = false)
    String className;

    @Column(name = "major", nullable = false)
    String major;

    @Column(name = "hometown", nullable = false)
    String hometown;

    @Column(name = "gender", nullable = false)
    String gender;

    @Column(name = "average_mark", nullable = false)
    double averageMark;

}
