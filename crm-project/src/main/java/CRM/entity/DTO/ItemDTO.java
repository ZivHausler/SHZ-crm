package CRM.entity.DTO;

import CRM.entity.Item;
import lombok.*;

import java.time.LocalDateTime;
import java.util.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO extends SharedContentDTO {

    private Long id;
    private AttributeDTO status;
    private AttributeDTO type;
    private Long section;
    private Long assignedToUserId;
    private LocalDateTime dueDate;
    private Integer importance;
    private Long boardId;
    private List<ItemDTO> subItems;

    public static ItemDTO getSharedContentFromDB(Item item) {
        ItemDTO itemDTO = new ItemDTO();

        itemDTO.setUser(UserDTO.createUserDTO(item.getUser()));
        itemDTO.setTitle(item.getName());
        itemDTO.setDescription(item.getDescription());
        itemDTO.setType(item.getType() == null ? null : AttributeDTO.createAttributeDTO(item.getType()));
        itemDTO.setStatus(item.getStatus() == null ? null : AttributeDTO.createAttributeDTO(item.getStatus()));
        itemDTO.setSection(item.getSection().getId());
        itemDTO.setCreationDate(item.getCreationDate());
        itemDTO.setId(item.getId());
        itemDTO.setImportance(item.getImportance());

        if (item.getParentItem() != null)
            itemDTO.setParentItem(ItemDTO.getParentItem(item));

        if (item.getItems().size() > 0)
            itemDTO.setSubItems(ItemDTO.getItemsDTOList(item.getItems()));

        return itemDTO;
    }

    public static ItemDTO getParentItem(Item item){
        ItemDTO itemDTO = new ItemDTO();

        itemDTO.setUser(UserDTO.createUserDTO(item.getUser()));
        itemDTO.setTitle(item.getName());
        itemDTO.setDescription(item.getDescription());
        itemDTO.setType(item.getType() == null ? null : AttributeDTO.createAttributeDTO(item.getType()));
        itemDTO.setStatus(item.getStatus() == null ? null : AttributeDTO.createAttributeDTO(item.getStatus()));
        itemDTO.setSection(item.getSection().getId());
        itemDTO.setCreationDate(item.getCreationDate());
        itemDTO.setId(item.getId());
        itemDTO.setImportance(item.getImportance());

        return itemDTO;
    }

    public static List<ItemDTO> getItemsDTOList(Set<Item> items) {
        List<ItemDTO> itemDTOList = new ArrayList<>();
        for (Item item : items) {
            itemDTOList.add(getSharedContentFromDB(item));
        }

        Collections.sort(itemDTOList, Comparator.comparingLong(ItemDTO::getId));

        return itemDTOList;
    }
}
