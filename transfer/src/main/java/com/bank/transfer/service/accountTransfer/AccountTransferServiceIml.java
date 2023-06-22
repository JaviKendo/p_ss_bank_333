package com.bank.transfer.service.accountTransfer;

import com.bank.transfer.audit.AuditTransferStorage;
import com.bank.transfer.dto.transferDto.AccountTransferDto;
import com.bank.transfer.mapper.AccountTransferMapper;
import com.bank.transfer.repository.AccountTransferRepository;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * Класс AccountTransferServiceIml имплементирующий интерфейс AccountTransferService хранит в себе бизнес логику
 * для работы с AccountTransfer'ом. Располагает поля <b>accountTransferRepository</b> и <b>accountTransferMapper</b>.
 * @author Кузьминых Юлиан
 * @version 1.0
 */
@Service
@Log(topic = "AccountTransferService")
@AllArgsConstructor
public class AccountTransferServiceIml implements AccountTransferService {
    /** Поле для связи с repository слоем AccountTransferRepository*/
    private final AccountTransferRepository accountTransferRepository;
    /** Поле для связи с mapper'ом, реализующем превращение AccountTransfer и AccountTransferDto друг в друга*/
    private final AccountTransferMapper accountTransferMapper;
    /** Поле для связи с кастомным временным хранилищем AccountTransfer'а.
     * В него помещается AccountTransfer до изменения, или созданный в первый раз*/
    private final AuditTransferStorage auditTransferStorage;
    /** Функция преобразования AccountTransferDto в AccountTransfer и сохранения/обновления
     * полученного AccountTransfer'а в БД. Также помещает информацию о состоянии AccountTransfer'а до изменения.
     * @param transferDto - AccountTransferDto переданный для сохранения/обновления
     * @exception java.sql.SQLException - может быть получено в случае повторения уникального поля accountNumber
     * класса AccountTransfer.
     * */
    @Override
    @Transactional
    public void saveTransfer(AccountTransferDto transferDto) {
        if (null != transferDto.getId()) {
            auditTransferStorage.setAccountTransfer(
                    accountTransferMapper.toEntity(getTransferById(transferDto.getId())));
        } else {
            auditTransferStorage.setAccountTransfer(accountTransferMapper.toEntity(transferDto));
        }
        accountTransferRepository.save(accountTransferMapper.toEntity(transferDto));
        log.info(transferDto + " сохранен/обновлен в базе данных");
    }

    /** Функция удаления существующего AccountTransfer'а из БД.
     * Также помещает информацию о состоянии AccountTransfer'а до изменения.
     * @param id - первичный ключ AccountTransfer'а
     * @exception EntityNotFoundException - может быть получено в случае получения несуществующего id.
     * */
    @Override
    @Transactional
    public void deleteTransferById(Long id) {
        auditTransferStorage.setAccountTransfer(accountTransferMapper.toEntity(getTransferById(id)));
        accountTransferRepository.deleteById(id);
        log.info("AccountTransfer с id = " + id + " удален из базы данных");
    }

    /** Функция получения существующего AccountTransfer'а из БД.
     * @param id - первичный ключ AccountTransfer'а
     * @exception EntityNotFoundException - может быть получено в случае получения несуществующего id.
     * */
    @Override
    @Transactional
    public AccountTransferDto getTransferById(Long id) {
        return accountTransferMapper.toDto(accountTransferRepository.getReferenceById(id));
    }
    /** Функция получения всех существующих AccountTransfer'ов из БД.
     @return возвращает список всех AccountTransfer'ов из БД.
     * */
    @Override
    @Transactional
    public List<AccountTransferDto> getAllTransfer() {
        return accountTransferMapper.toDtoList(accountTransferRepository.findAll());
    }
}
