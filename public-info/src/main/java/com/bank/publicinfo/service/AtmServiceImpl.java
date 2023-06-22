package com.bank.publicinfo.service;

import com.bank.publicinfo.audit.AuditActionType;
import com.bank.publicinfo.audit.Auditable;
import com.bank.publicinfo.entity.Atm;
import com.bank.publicinfo.repository.AtmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional
public class AtmServiceImpl implements AtmService {
    private final AtmRepository atmRepository;

    @Autowired
    public AtmServiceImpl(AtmRepository atmRepository) {
        this.atmRepository = atmRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Atm> getAllAtms() {
        return atmRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Atm getAtmById(Long id) {
        return atmRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Atm с идентификатором " + id + " не найден"));
    }

    @Override
    @Auditable(actionType = AuditActionType.CREATE)
    public void createAtm(Atm atm) {
        atmRepository.save(atm);
    }

    @Override
    @Auditable(actionType = AuditActionType.UPDATE)
    public void updateAtm(Atm atm) {
        atmRepository.save(atm);
    }

    @Override
    @Auditable(actionType = AuditActionType.DELETE)
    public void deleteAtm(Long id) {
        try {
            atmRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Atm с идентификатором " + id + " не найден");
        }
    }
}
