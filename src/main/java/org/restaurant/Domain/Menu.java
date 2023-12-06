package org.restaurant.Domain;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Menu {

    private Integer id;
    private String item_name;
    private String category;
    private Integer r_id;
    private Integer cost;

}
