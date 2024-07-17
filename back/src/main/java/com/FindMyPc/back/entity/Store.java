package com.FindMyPc.back.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int storeID;

    private String name;
    private String location;
    private String websiteURL;
    private String logo;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Affected> affected;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Rating> ratings;

    public List<Affected> getAffected() {
        return affected;
    }

    public List<Rating> getRatings() {
        return ratings;
    }
}
