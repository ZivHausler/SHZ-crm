package CRM.service;

import CRM.entity.Attribute;
import CRM.entity.Status;
import CRM.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusService implements AttributeService {

    @Autowired
    private StatusRepository statusRepository;

    @Override
    public int update(long id, Attribute object) {
        return 0;
    }

    /**
     * This function persists a new Status to the database by calling the save function in the StatusRepository class.
     * @param status The status object to be persisted.
     * @return The persisted status object.
     */
    @Override
    public Status create(Attribute status) {
        return statusRepository.save((Status) status);
    }
}
