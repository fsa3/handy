package is.hi.handy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HandyApplication {
    public static String instanceId = "0a915ad4-4103-4743-96ed-c2d40f7bbe1c";
    public static String secretKey = "2F57E4ED65D6FAC58726EC19C78C9BE42B2836A224C8CFF93E734C1DF8471239";

    public static void main(String[] args) {
        SpringApplication.run(HandyApplication.class, args);
    }

}
