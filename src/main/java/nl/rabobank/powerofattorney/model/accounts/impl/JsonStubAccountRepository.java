package nl.rabobank.powerofattorney.model.accounts.impl;

import static nl.rabobank.powerofattorney.util.JsonUtil.asClass;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import nl.rabobank.powerofattorney.backingapi.ApiClient;
import nl.rabobank.powerofattorney.model.accounts.Account;
import nl.rabobank.powerofattorney.model.accounts.AccountRepository;
import nl.rabobank.powerofattorney.model.ids.AccountId;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class JsonStubAccountRepository implements AccountRepository {

    private final ApiClient apiClient;

    @Override
    @SneakyThrows
    public Account get(@NonNull AccountId id) {
        return asClass(Account.class,
                apiClient.get("accounts", id.getValue())
        );
    }
}
