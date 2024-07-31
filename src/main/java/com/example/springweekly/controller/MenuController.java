package com.example.springweekly.controller;

import com.example.springweekly.dto.MenuDTO;
import com.example.springweekly.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    private MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    // 새로운 메뉴 등록
    @PostMapping
    public ResponseEntity<MenuDTO> addMenu(@RequestBody MenuDTO newMenuDTO) {
        MenuDTO menuDTO = menuService.addMenu(newMenuDTO);
        return ResponseEntity.ok(menuDTO);
    }

    // 메뉴 조회
    @GetMapping
    public ResponseEntity<List<MenuDTO>> getAllMenu() {
        return ResponseEntity.ok(menuService.getAllMenu());
    }

    // 메뉴 수정
    @PutMapping("/{id}")
    public ResponseEntity<MenuDTO> updateMenu(@PathVariable Long id, @RequestBody MenuDTO updateMenuDTO) {
        return menuService.updateMenu(id, updateMenuDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 메뉴 삭제
    @DeleteMapping("/{id}")
    public boolean deleteMenu(@PathVariable Long id) {
        return menuService.deleteMenu(id);
    }

    // 특정 카테고리의 모든 메뉴 조회
    @GetMapping("/search")
    public ResponseEntity<List<MenuDTO>> getAllMenuByCategory(@RequestParam String category) {
       List<MenuDTO> menuDTOList = menuService.getAllmenuByCategory(category);
       return ResponseEntity.ok(menuDTOList);
    }
}
