package com.example.springweekly.dto;


import com.example.springweekly.domain.Menu;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MenuDTO {
    private String name;
    private String category;
    private double price;
    private String description;

    public static MenuDTO convertToDTO(Menu menu) {
        MenuDTO menuDTO = new MenuDTO();
        menuDTO.setName(menu.getName());
        menuDTO.setCategory(menu.getCategory());
        menuDTO.setPrice(menu.getPrice());
        menuDTO.setDescription(menu.getDescription());
        return menuDTO;
    }

    public Menu convertToEntity() {
        Menu menu = new Menu();
        menu.setName(this.name);
        menu.setCategory(this.category);
        menu.setPrice(this.price);
        menu.setDescription(this.description);
        return menu;
    }
}
