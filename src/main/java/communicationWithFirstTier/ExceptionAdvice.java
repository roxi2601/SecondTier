package communicationWithFirstTier;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionAdvice
{
  @ResponseBody
  @ExceptionHandler(UserNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String UserNotFoundHandler(UserNotFoundException ex)
  {
    return  ex.getMessage();
  }
  @ResponseBody
  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  String exceptionHandler(Exception ex)
  {
    return ex.getMessage();
  }
  @ResponseBody
  @ExceptionHandler(RuntimeException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  String runtimeExceptionHandler(RuntimeException ex)
  {
    return ex.getMessage();
  }
  @ResponseBody
  @ExceptionHandler(ArtworkException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String exceptionHandler(ArtworkException ex)
  {
    return ex.getMessage();
  }
  @ResponseBody
  @ExceptionHandler(AccountException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String exceptionHandler(AccountException ex)
  {
    return ex.getMessage();
  }
}
