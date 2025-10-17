package com.marcos.vaudoise.service;

import com.marcos.vaudoise.model.client.Client;
import com.marcos.vaudoise.model.client.ClientRepository;
import com.marcos.vaudoise.model.company.Company;
import com.marcos.vaudoise.model.company.CompanyRepository;
import com.marcos.vaudoise.model.contract.Contract;
import com.marcos.vaudoise.model.contract.ContractRepository;
import com.marcos.vaudoise.model.person.Person;
import com.marcos.vaudoise.model.person.PersonRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

@Service
public class TestService {
    private final ContractRepository contractRepository;
    private final PersonRepository personRepository;
    private final CompanyRepository companyRepository;

    public TestService(ContractRepository contractRepository, PersonRepository personRepository, CompanyRepository companyRepository) {
        this.contractRepository = contractRepository;
        this.personRepository = personRepository;
        this.companyRepository = companyRepository;
    }

    public String generateData(int numClients, int numContractsPerClient) {
        Random random = new Random();
        int clientsCreated = 0;
        int contractsCreated = 0;

        for (int i = 0; i < numClients; i++) {
            // Generate random client data
            String name = generateRandomName();
            String phone = generateRandomPhone();
            String email = generateRandomEmail(name);

            Client client;

            // Randomly choose between Person or Company
            if (random.nextBoolean()) {
                // Create Person
                Person person = new Person();
                person.setName(name);
                person.setPhone(phone);
                person.setEmail(email);
                person.setBirthDate(generateRandomBirthDate(random));
                client = personRepository.save(person);
            } else {
                // Create Company
                Company company = new Company();
                company.setName(name);
                company.setPhone(phone);
                company.setEmail(email);
                company.setCompanyIdentifier(generateRandomCompanyCode());
                client = companyRepository.save(company);
            }

            clientsCreated++;

            // Create contracts for this client
            for (int j = 0; j < numContractsPerClient; j++) {
                Contract contract = new Contract();
                contract.setClient(client);
                contract.setStartDate(generateRandomDate(random));
                contract.setEndDate(generateRandomEndDate(random));
                contract.setUpdateDate(LocalDateTime.now());
                contract.setCostAmount(generateRandomCostAmount(random));

                contractRepository.save(contract);
                contractsCreated++;
            }
        }

        return String.format("Successfully generated %d clients and %d contracts",
                           clientsCreated, contractsCreated);
    }

    private String generateRandomName() {
        String[] firstNames = {"Guillaume", "John", "Jane", "Michael", "Sarah", "David", "Emma",
                              "Robert", "Lisa", "James", "Maria", "William", "Anna"};
        String[] lastNames = {"GrandJean", "Smith", "Johnson", "Williams", "Brown", "Jones",
                             "Garcia", "Miller", "Davis", "Rodriguez", "Martinez"};
        String[] companyNames = {"Vaudoise", "TechCorp", "GlobalSolutions", "Innovation Inc",
                                "Enterprise Systems", "Digital Services", "SmartBusiness"};

        Random random = new Random();

        if (random.nextBoolean()) {
            // Generate person name
            return firstNames[random.nextInt(firstNames.length)] + " " +
                   lastNames[random.nextInt(lastNames.length)];
        } else {
            // Generate company name
            return companyNames[random.nextInt(companyNames.length)] + " " +
                   random.nextInt(1000);
        }
    }

    private String generateRandomPhone() {
        Random random = new Random();
        return String.format("+41 %d %d %d %d",
                           random.nextInt(90) + 10,
                           random.nextInt(900) + 100,
                           random.nextInt(90) + 10,
                           random.nextInt(90) + 10);
    }

    private String generateRandomEmail(String name) {
        String cleanName = name.toLowerCase()
                              .replace(" ", ".")
                              .replaceAll("[^a-z0-9.]", "");
        String[] domains = {"example.com", "test.ch", "vaudoise.ch", "email.com", "domain.net"};
        Random random = new Random();
        return cleanName + "@" + domains[random.nextInt(domains.length)];
    }

    private LocalDate generateRandomBirthDate(Random random) {
        int yearsAgo = random.nextInt(62) + 18;
        return LocalDate.now().minusYears(yearsAgo)
                             .minusDays(random.nextInt(365));
    }

    private String generateRandomCompanyCode() {
        Random random = new Random();
        return "CHE-" + String.format("%03d", random.nextInt(1000)) + "." +
               String.format("%03d", random.nextInt(1000)) + "." +
               String.format("%03d", random.nextInt(1000));
    }

    private LocalDateTime generateRandomDate(Random random) {
        int daysOffset = random.nextInt(60) - 30;
        return LocalDateTime.now().plusDays(daysOffset);
    }

    private LocalDateTime generateRandomEndDate(Random random) {
        if (random.nextInt(100) < 30) {
            return null;
        }

        int daysOffset = random.nextInt(60) - 30;
        return LocalDateTime.now().plusDays(daysOffset);
    }

    private float generateRandomCostAmount(Random random) {
        return 100 + random.nextFloat() * 9900;
    }
}
