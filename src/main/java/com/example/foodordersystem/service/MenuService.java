package com.example.foodordersystem.service;

import com.example.foodordersystem.dto.MenuRequestDto;
import com.example.foodordersystem.dto.MenuResponseDto;
import com.example.foodordersystem.entity.Menu;
import com.example.foodordersystem.exception.NotFoundException;
import com.example.foodordersystem.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;
    private final ModelMapper modelMapper;

    public MenuResponseDto create(MenuRequestDto menuRequestDto) {
        if (menuRepository.existsByMenuName(menuRequestDto.getMenuName())) {
            throw new IllegalArgumentException("A menu with this name already exists.!");
        }
        Menu menu = modelMapper.map(menuRequestDto, Menu.class);
        return modelMapper.map(menuRepository.save(menu), MenuResponseDto.class);
    }

    public MenuResponseDto updateMenu(Long id, MenuRequestDto menuRequestDto) {
        Menu existingMenu = menuRepository.findById(id).orElse(null);
        if (existingMenu == null) {
            throw new NotFoundException("Menu not found with id " + id);
        }
        if (menuRepository.existsByMenuName(menuRequestDto.getMenuName())) {
            throw new IllegalArgumentException("A menu with this name already exists.");
        }
        existingMenu.setMenuName(menuRequestDto.getMenuName());
        existingMenu.setMenuDescription(menuRequestDto.getMenuDescription());
        Menu updatedMenu = menuRepository.save(existingMenu);
        return modelMapper.map(updatedMenu, MenuResponseDto.class);
    }

    public void deleteMenu(Long id) {
        Menu existingMenu = menuRepository.findById(id).orElse(null);
        if (existingMenu == null) {
            throw new NotFoundException("Menu not found with id " + id);
        }
        menuRepository.delete(existingMenu);
    }

    public MenuResponseDto getMenuById(Long id) {
        Menu menu = menuRepository.findById(id).orElse(null);
        if (menu == null) {
            throw new NotFoundException("Menu not found with id " + id);
        }
        return modelMapper.map(menu, MenuResponseDto.class);
    }

    public Optional<Menu> getMenusByName(String menuName) {
        Optional<Menu> menu = menuRepository.findByMenuName(menuName);
        if (menu.isEmpty()) {
            throw new NotFoundException("No menu found with the name: " + menuName);
        }
        return menu;
    }

    public List<MenuResponseDto> getAllMenus() {
        List<Menu> menus = menuRepository.findAll();
        return menus.stream()
                .map(menu -> modelMapper.map(menu, MenuResponseDto.class))
                .collect(Collectors.toList());
    }
}

