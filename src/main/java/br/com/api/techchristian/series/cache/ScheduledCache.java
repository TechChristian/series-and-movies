package br.com.api.techchristian.series.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class ScheduledCache {
    @CacheEvict(value = "movies", allEntries = true)
    @Scheduled(fixedRate = 30, timeUnit = TimeUnit.SECONDS)
    public void evictMovies() {
        log.info("clear cache... " + LocalDateTime.now());

    }

}
