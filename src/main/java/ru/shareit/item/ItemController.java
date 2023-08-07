package ru.shareit.item;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/items")
public class ItemController {

    private ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping()
    public Collection<ItemDto> findAll(@RequestHeader("X-ShareIt-User-Id") Long userId) {
        return itemService.findAll(userId);
    }

    @GetMapping("/{id}")
    public ItemDto getItem(@PathVariable long id) {
        return itemService.getItem(id);
    }

    @PostMapping()
    public ItemDto createItem(@Valid @RequestBody ItemDto item, @RequestHeader("X-ShareIt-User-Id") Long userId) {
        return itemService.createItem(item, userId);
    }

    @PatchMapping("/{id}")
    public ItemDto editItem(@Valid @RequestBody ItemDto item,
                            @PathVariable long id,
                            @RequestHeader("X-ShareIt-User-Id") Long userId) {
        return itemService.editItem(item, id, userId);
    }

    @DeleteMapping("/{id}")
    public String deleteItem(@PathVariable long id, @RequestHeader("X-ShareIt-User-Id") long userId) {
        itemService.deleteItem(id, userId);
        return "Вещь " + id + " была удалена";
    }
}