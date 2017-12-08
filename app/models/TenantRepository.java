package models;

import com.google.inject.ImplementedBy;

import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;
@ImplementedBy(JPATenantRepository.class)
public interface TenantRepository {
    CompletionStage<Tenant> add(Tenant person);

    CompletionStage<Stream<Tenant>> list();
}
