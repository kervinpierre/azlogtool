package nyc.citymsp.tools.azlogtool.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages="nyc.citymsp.tools.azlogtool")
@EnableJpaRepositories("nyc.citymsp.tools.azlogtool.repository")
@EntityScan(basePackages = "nyc.citymsp.tools.azlogtool.entity")
public class AzlogtoolApplication extends SpringBootServletInitializer
{
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application)
    {
        return application.sources(AzlogtoolApplication.class);
    }

    public static void main(String[] args)
    {
		SpringApplication.run(AzlogtoolApplication.class, args);
    }
}
