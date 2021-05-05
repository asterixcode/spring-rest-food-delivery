//package com.asterixcode.asterixfoodapi;
//
//import com.asterixcode.asterixfoodapi.notification.NotifierEmail;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//// Configuration is a @Component - main objective: beans definition
//// instead of leaving to spring boot to initialize our beans (eg @Component),
//// we can instead don't use @Component and config the bean here.
//@Configuration
//public class NotificationConfig {
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
//}
