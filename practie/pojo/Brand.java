package com.practie.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.util.Strings;

/**
 * @author 喵vamp
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Brand {
    private long id;
    private String brandName;
    private String companyName;
    private long ordered;
    private String description;
    private Integer status;

    public Brand(String brandName, String companyName, long ordered, String description, Integer status) {
        this.brandName = brandName;
        this.companyName = companyName;
        this.ordered = ordered;
        this.description = description;
        this.status = status;
    }

}
