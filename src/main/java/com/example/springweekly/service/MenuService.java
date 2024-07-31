package com.example.springweekly.service;

import com.example.springweekly.domain.Menu;
import com.example.springweekly.dto.MenuDTO;
import com.example.springweekly.repository.MenuRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MenuService {

    private final MenuRepository menuRepository;

    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public MenuDTO addMenu(MenuDTO newMenuDTO) {
        Menu newMenu = newMenuDTO.convertToEntity();
        Menu savedMenu = menuRepository.save(newMenu);
        return MenuDTO.convertToDTO(savedMenu);
    }

    public List<MenuDTO> getAllMenu() {
        List<Menu> menu = menuRepository.findAll();

        return menu.stream()
                .map(MenuDTO::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<MenuDTO> updateMenu(Long id, MenuDTO updateMenuDTO) {
        return menuRepository.findById(id)
                .map(m -> {
                    m.setName(updateMenuDTO.getName());
                    m.setCategory(updateMenuDTO.getCategory());
                    m.setPrice(updateMenuDTO.getPrice());
                    m.setDescription(updateMenuDTO.getDescription());
                    return MenuDTO.convertToDTO(menuRepository.save(m));
                });
    }

    public boolean deleteMenu(Long id) {
        return menuRepository.findById(id)
                .map(m -> {
                    menuRepository.delete(m);
                    return true;
                })
                .orElse(false);
    }

    public List<MenuDTO> getAllmenuByCategory(String Category) {
        List<Menu> menusByCategory = menuRepository.findByCategory(Category);
        return menusByCategory.stream().map(MenuDTO::convertToDTO).collect(Collectors.toList());
    }
}
