package br.com.ffroliva.riskcontrol;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Application's configuration class.
 *
 * @author Flavio Oliva <a href="mailto:ffroliva@gmail.com">ffroliva@gmail.com</a>
 */
@Configuration
@ComponentScan(basePackages = {"br.com.ffroliva.*"})
public class AppConfiguration {
}