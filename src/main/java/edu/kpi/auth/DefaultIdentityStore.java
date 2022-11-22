package edu.kpi.auth;

import edu.kpi.model.User;
import edu.kpi.service.UserServiceLocal;
import edu.kpi.utils.BCryptUtils;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.security.enterprise.authentication.mechanism.http.FormAuthenticationMechanismDefinition;
import jakarta.security.enterprise.authentication.mechanism.http.LoginToContinue;
import jakarta.security.enterprise.credential.Credential;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.security.enterprise.identitystore.IdentityStore;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import static jakarta.security.enterprise.identitystore.CredentialValidationResult.INVALID_RESULT;

@FormAuthenticationMechanismDefinition(loginToContinue = @LoginToContinue)
@ApplicationScoped // must be defined as CDI to be picked up by TomEE
public class DefaultIdentityStore implements IdentityStore {

    @EJB
    private UserServiceLocal userService;

    @Override
    public CredentialValidationResult validate(final Credential credential) {

        return Optional.of(credential)
                .filter(UsernamePasswordCredential.class::isInstance)
                .map(UsernamePasswordCredential.class::cast)
                .filter(this::isValid)
                .map(this::buildSuccessfulResult)
                .orElse(INVALID_RESULT);
    }

    @Override
    public Set<String> getCallerGroups(final CredentialValidationResult validationResult) {

        return Collections.singleton("user");
    }

    @Override
    public int priority() {

        return IdentityStore.super.priority();
    }

    @Override
    public Set<ValidationType> validationTypes() {

        return IdentityStore.super.validationTypes();
    }

    private boolean isValid(final UsernamePasswordCredential credential) {

        return userService.findUserByUsername(credential.getCaller())
                .map(User::getPassword)
                .filter(hash -> BCryptUtils.checkPassword(credential.getPasswordAsString(), hash))
                .isPresent();
    }

    private CredentialValidationResult buildSuccessfulResult(final UsernamePasswordCredential credential) {

        return new CredentialValidationResult(credential.getCaller(), Collections.singleton("user"));
    }
}
