package com.aston.hw.models;
import lombok.*;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Film {

    private Integer film_id;
    private  String title;
    private Integer cinemaId;
    private List<Actor> actorList;

}
