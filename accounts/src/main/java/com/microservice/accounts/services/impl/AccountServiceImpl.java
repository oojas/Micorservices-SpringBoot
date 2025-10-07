package com.microservice.accounts.services.impl;

import com.microservice.accounts.constants.AccountsConstants;
import com.microservice.accounts.dto.AccountsDTO;
import com.microservice.accounts.dto.CustomerDTO;
import com.microservice.accounts.entities.Accounts;
import com.microservice.accounts.entities.Customer;
import com.microservice.accounts.exceptions.CustomerAlreadyExists;
import com.microservice.accounts.exceptions.ResourceNotFoundException;
import com.microservice.accounts.mapper.AccountsMapper;
import com.microservice.accounts.mapper.CustomerMapper;
import com.microservice.accounts.repository.AccountsRepository;
import com.microservice.accounts.repository.CustomerRepository;
import com.microservice.accounts.services.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountsService {
    @Autowired
    private AccountsRepository accountsRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public void createAccount(CustomerDTO customerDTO) {
        Customer customer= CustomerMapper.mapToCustomer(customerDTO,new Customer());
        Optional<Customer> customerOptional=customerRepository.findByMobileNumber(customerDTO.getMobileNumber());
        if(customerOptional.isPresent()){
            throw new CustomerAlreadyExists("Customer already exists with the phone number "+customerDTO.getMobileNumber());
        }
        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("ojgupta");
       customerRepository.save(customer);
       Accounts accounts=generateAccount(customer);
       accountsRepository.save(accounts);
    }

    @Override
    public CustomerDTO fetchAccountDetails(String mobileNumber) throws ResourceNotFoundException {
        Optional<Customer> customerOptional=customerRepository.findByMobileNumber(mobileNumber);
        if(customerOptional.isPresent()){
            Optional<Accounts> accountsOptional=accountsRepository.findByCustomerId(customerOptional.get().getCustomerId());
            if(accountsOptional.isPresent()){
                AccountsDTO accountsDTO=AccountsMapper.accEntityToDTO(accountsOptional.get(),new AccountsDTO());
                return CustomerMapper.mapToCustomerDto(customerOptional.get(),new CustomerDTO(),accountsDTO);
            }else{
                throw new ResourceNotFoundException("Account","Customer ID",String.valueOf(customerOptional.get().getCustomerId()));
            }
        }
        throw new ResourceNotFoundException("Customer","Mobile Number",mobileNumber);
    }

    private Accounts generateAccount(Customer customer){
        Accounts accounts=new Accounts();
        accounts.setCustomerId(customer.getCustomerId());
        long accountNumber=1000000000L+new Random().nextInt(90000000);
        accounts.setAccountNumber(accountNumber);
        accounts.setAccountType(AccountsConstants.SAVINGS);
        accounts.setBranchAddress(AccountsConstants.ADDRESS);
        accounts.setCreatedAt(LocalDateTime.now());
        accounts.setCreatedBy("ojgupta");
        return accounts;
    }
    @Override
    public boolean updateCustomer(CustomerDTO customerDTO) {
        boolean isUpdated=false;
        AccountsDTO accountsDTO=customerDTO.getAccountsInfo();
        if(accountsDTO!=null){
            Accounts accounts=accountsRepository.findById(accountsDTO.getAccountNumber()).orElseThrow(
                    ()->new ResourceNotFoundException("Account","AccountNumber",accountsDTO.getAccountNumber().toString())
            );
            AccountsMapper.accDTOToAccountsEntity(accounts,accountsDTO,false);
            accounts.setUpdatedAt(LocalDateTime.now());
            accounts.setUpdatedBy("OJGUPTAA");
            accounts=accountsRepository.save(accounts);
            Long customerID=accounts.getCustomerId();
            Customer customer=customerRepository.findById(customerID).orElseThrow(
                    ()->new ResourceNotFoundException("Customer","Customer ID",customerID.toString())
            );
            CustomerMapper.mapToCustomer(customerDTO,customer);
            customer.setUpdatedAt(LocalDateTime.now());
            customer.setUpdatedBy("OJGUPTAA");
            customerRepository.save(customer);
            isUpdated=true;
        }
      return isUpdated;
    }
}
