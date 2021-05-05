//package com.asterixcode.asterixfoodapi;
//
//import com.asterixcode.asterixfoodapi.notification.NotifierEmail;
//import com.asterixcode.asterixfoodapi.service.ActivateClientService;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
// this class is not needed as we have the Configs as separated classes
//
// Configuration is a @Component - main objective: beans definition
// instead of leaving to spring boot to initialize our beans (eg @Component),
// we can instead don't use @Component and config the bean here.
// @Configuration
// public class AsterixConfig {
//
//    // a method responsible for instantiate and configure an object of the type NotifierEmail
//    // @Bean -> indicate that the method instantiates, configs, and initializes a new object
//    // that will be managed by the spring container
//    @Bean
//    public NotifierEmail notifierEmail(){
//        NotifierEmail notifier = new NotifierEmail("smtp.asterix.mail.com");
//        notifier.setUpperCase(true);
//
//        return notifier;
//    }
//
//    @Bean
//    public ActivateClientService activateClientService(){
//        return new ActivateClientService(notifierEmail());
//        // we need to pass a notifier as parameter;
//        // we can't instantiate a new notifier,
//        // so the right way to go is to call the method above,
//        // that returns and instance of NotifierEmail,
//        // and as the method is annotated with @Bean,
//        // so it will have a @Bean already managed by spring
//
//        // if this bean depends on other beans that are located in
//        // different Config classes or even as @Component class,
//        // it has to be done dependency injection as well in the parameter
//
//    }
//}