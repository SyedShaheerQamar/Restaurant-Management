package org.restaurant.Domain;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CustomerOrder {

    private Integer m_id;
    private Integer c_id;
    private Integer u_id;
    private Integer item_price;
    private String c_date;


}
