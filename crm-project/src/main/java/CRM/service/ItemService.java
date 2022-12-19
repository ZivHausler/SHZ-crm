package CRM.service;

import CRM.entity.*;
import CRM.entity.requests.ItemRequest;
import CRM.repository.*;
import CRM.utils.Validations;
import CRM.utils.enums.ExceptionMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ItemService implements ServiceInterface {

    private static Logger logger = LogManager.getLogger(ItemService.class.getName());

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private TypeRepository typeRepository;
    @Autowired
    private StatusRepository statusRepository;

    public Item create(ItemRequest itemRequest) throws AccountNotFoundException {
        User user;
        Item parentItem;

        if (itemRequest.getParentItemId() == null) parentItem = null;
        else parentItem = Validations.doesIdExists(itemRequest.getParentItemId(), itemRepository);

        try {
            user = Validations.doesIdExists(itemRequest.getUserId(), userRepository);
        } catch (NoSuchElementException e) {
            throw new AccountNotFoundException(ExceptionMessage.ACCOUNT_DOES_NOT_EXISTS.toString());
        }

        Board board = Validations.doesIdExists(itemRequest.getBoardId(), boardRepository);
        Type type = Validations.doesIdExists(itemRequest.getTypeId(), typeRepository);
        Status status = Validations.doesIdExists(itemRequest.getStatusId(), statusRepository);

        Item item = Item.createNewItem(board, status, type, user, itemRequest.getTitle(), itemRequest.getDescription(), parentItem, itemRequest.getImportance());
        return itemRepository.save(item);
    }

    @Override
    public int delete(List<Long> ids) {
        int counter = ids.size();
        for (Long id : ids) {
            try {
                Item item = Validations.doesIdExists(id, itemRepository);
            } catch (NoSuchElementException e){
                ids.remove(id);
                counter--;
            }
        }
        itemRepository.deleteAllById(ids);
        return counter;
    }

    @Override
    public Item update(long id, String field, String content) {
        // checkIfExists
        // make sure there is such a field in Item -> use reflection!

        return null;
    }

    @Override
    public Item get(long id) {
        return Validations.doesIdExists(id, itemRepository);
    }

    @Override
    public List<SharedContent> getAllInItem(long itemId) {
        // checkIfExists
        // returns the list of items retrieved

        return null;
    }

    public List<Item> getAllInBoard(long boardId) {
        Board board = Validations.doesIdExists(boardId, boardRepository);
        return itemRepository.findAllByBoard(board);
    }
}
