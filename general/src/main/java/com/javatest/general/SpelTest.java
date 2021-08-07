package com.javatest.general;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class SpelTest {

    @Value("#{{'name':'Nikola','dob':'10-July-1856'}['name']}")
    private String configValue;

}
