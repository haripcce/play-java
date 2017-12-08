package models;

import play.db.jpa.*;

import javax.inject.*;


import javax.persistence.*;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.concurrent.CompletableFuture.supplyAsync;

@Singleton
public class JPATenantRepository implements TenantRepository {
    private JPAApi jpaApi;
    private DatabaseExecutionContext executionContext;

    @Inject
    public JPATenantRepository(JPAApi api, DatabaseExecutionContext executionContext) {
        this.jpaApi = api;
        this.executionContext = executionContext;
    }

    @Override
    public CompletionStage<Stream<Tenant>> add(Tenant tenant) {
        return supplyAsync(() -> wrap(em -> insert(em, tenant)), executionContext).thenCompose(s -> list());

    }

    @Override
    public CompletionStage<Stream<Tenant>> list() {
        return supplyAsync(() -> wrap(em -> list(em)), executionContext);
    }


    private <T> T wrap(Function<EntityManager, T> function) {
        return jpaApi.withTransaction(function);
    }

    private Tenant insert(EntityManager em, Tenant tenant) {
        em.persist(tenant);
        return tenant;
    }

    private Stream<Tenant> list(EntityManager em) {
        List<Tenant> tenants = em.createQuery("select t from Tenant t", Tenant.class).getResultList();
        return tenants.stream();
    }
}
