package pl.com.bottega.qma.core.profiling;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ProfilingAspect {

  private static final Logger LOG = LoggerFactory.getLogger(ProfilingAspect.class.getName());

  @Around("@within(org.springframework.web.bind.annotation.RestController)")
  public Object profile(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
    long ts = System.currentTimeMillis();
    Object retVal = proceedingJoinPoint.proceed();
    long te = System.currentTimeMillis();
    long t = te - ts;
    LOG.info("Execution of {} took {}ms", proceedingJoinPoint.getSignature().toLongString(), t);

    return retVal;
  }

}
