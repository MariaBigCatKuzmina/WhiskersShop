package ru.kuzmina.wiskersshop.aop;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/statistic")
@RequiredArgsConstructor
public class AopController {
    private final AppLoggingAspect appLoggingAspect;

    @GetMapping
    public Map<String, Long> getStatistic(){
        return appLoggingAspect.getStatistic();
    }


}
