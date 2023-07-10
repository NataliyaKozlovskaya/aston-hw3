package com.aston.hw.models;
import lombok.*;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Cinema {

    private Integer id;
    private String name;
    private List<Film> filmList;

}