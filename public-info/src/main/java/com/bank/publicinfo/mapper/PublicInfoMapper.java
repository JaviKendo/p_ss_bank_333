package com.bank.publicinfo.mapper;

import com.bank.publicinfo.dto.*;
import com.bank.publicinfo.entity.*;
import org.mapstruct.factory.Mappers;

@org.mapstruct.Mapper
public interface PublicInfoMapper {
    PublicInfoMapper INSTANCE = Mappers.getMapper(PublicInfoMapper.class);

    Atm convertToAtm(AtmDTO atmDTO);

    AtmDTO convertToAtmDTO(Atm atm);

    Audit convertToAudit(AuditDTO auditDTO);

    public AuditDTO convertToAuditDTO(Audit audit);

    BankDetails convertToBankDetails(BankDetailsDTO bankDetailsDTO);

    BankDetailsDTO convertToBankDetailsDTO(BankDetails bankDetails);

    Branch convertToBranch(BranchDTO branchDTO);

    BranchDTO convertToBranchDTO(Branch branch);

    Certificate convertToCertificate(CertificateDTO certificateDTO);

    CertificateDTO convertToCertificateDTO(Certificate certificate);

    License convertToLicense(LicenseDTO licenseDTO);

    LicenseDTO convertToLicenseDTO(License license);
}
