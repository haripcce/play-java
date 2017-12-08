package controllers;

import models.JPATenantRepository;
import models.Tenant;
import models.TenantRepository;

import play.data.Form;
import play.data.FormFactory;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;

import views.html.manualform.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

import static play.libs.Json.toJson;

public class TenantController extends Controller {
    @Inject
    FormFactory formFactory;
    @Inject
    TenantRepository tenantRepository;
    @Inject
    HttpExecutionContext ec;
    //List<Tenant> tenants = new ArrayList<Tenant>();

    public CompletionStage<Result> list(Integer page) {
        return tenantRepository.list().thenApplyAsync(personStream -> {
            return ok(list.render(personStream.collect(Collectors.toList())));
        }, ec.current());

    }

    public Result addTenant() {

        return ok(showform.render());
        //return ok("Hello ");
    }

    public CompletionStage<Result> createTenant() {
        Form<Tenant> productForm = formFactory.form(Tenant.class);

        Tenant tenant = productForm.bindFromRequest().get();
        tenant.setGuid(UUID.randomUUID().toString());

        return tenantRepository.add(tenant).thenApplyAsync(personStream -> {
            return ok(list.render(personStream.collect(Collectors.toList())));
        }, ec.current());

    }

}
