package otus.spring.albot.lesson34.exceptionHandler;

import com.netflix.hystrix.exception.HystrixRuntimeException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {
    public static final String HYSTRIX_ERROR_PAGE = "hystrix_error";

    @ExceptionHandler(HystrixRuntimeException.class)
    public ModelAndView hystrixExceptionHandling() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName(HYSTRIX_ERROR_PAGE);
        return mav;
    }
}
