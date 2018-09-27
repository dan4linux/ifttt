package net.swansonstuff.ifttt;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import net.swansonstuff.ifttt.health.TemplateHealthCheck;
import net.swansonstuff.ifttt.resource.ActionsResource;
import net.swansonstuff.ifttt.resource.IftttResource;
import net.swansonstuff.ifttt.resource.RootResource;
import net.swansonstuff.ifttt.resource.TestResource;
import net.swansonstuff.ifttt.resource.TriggersResource;
import net.swansonstuff.ifttt.resource.UserResource;

public class IftttApplication extends Application<IftttConfiguration>{

    public IftttConfiguration config;
    public static final IftttApplication instance = new IftttApplication();

    
    /**
     * @return the instance
     */
    public static IftttApplication getInstance() {
        return instance;
    }


    public IftttApplication() {
    }


    /**
     * @return the config
     */
    public IftttConfiguration getConfig() {
        return this.config;
    }


    /**
     * @param config the config to set
     */
    public void setConfig(IftttConfiguration config) {
        this.config = config;
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<IftttConfiguration> bootstrap) {
    }

    @Override
    public void run(IftttConfiguration configuration, Environment environment) {
        this.config = configuration;
        environment.healthChecks().register("template", new TemplateHealthCheck(configuration.getTemplate()));
        environment.jersey().register(new RootResource(configuration.getTemplate(), configuration.getDefaultName()));
        environment.jersey().register(new TestResource(configuration.getTemplate(), configuration.getDefaultName()));
        environment.jersey().register(new TriggersResource(configuration.getTemplate(), configuration.getDefaultName()));
        environment.jersey().register(new ActionsResource(configuration.getTemplate(), configuration.getDefaultName()));
        environment.jersey().register(new UserResource(configuration.getTemplate(), configuration.getDefaultName()));
        environment.jersey().register(new ExceptionMapper());
    }

    public static void main(String[] args) throws Exception {
        getInstance().run(args);
    }

}
