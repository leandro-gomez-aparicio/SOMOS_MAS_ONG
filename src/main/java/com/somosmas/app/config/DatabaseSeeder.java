package com.somosmas.app.config;

import com.somosmas.app.model.entity.Activity;
import com.somosmas.app.repository.IActivityRepository;
import com.somosmas.app.util.TimestampUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private static final String IMAGE = "C:/Img";

    @Autowired
    IActivityRepository activityRepository;

    @Override
    public void run(String... args) throws Exception {
        loadActivityData();
    }

    private void loadActivityData() {
        if (activityRepository.count() == 0) {
            activityRepository.save(buildActivity(1L, "Apoyo escolar", "Apoyo escolar nivel primario"));
            activityRepository.save(buildActivity(2L, "Apoyo escolar", "Apoyo escolar nivel Secundario"));
            activityRepository.save(buildActivity(3L, "Tutorias", "Tutorias primaria"));
            activityRepository.save(buildActivity(4L, "Tutorias", "Tutorias secundaria"));
            activityRepository.save(buildActivity(5L, "Ayudantías", "2do año"));
            activityRepository.save(buildActivity(6L, "Acompañamiento escolar y familiar", "Monitorear el estado de los tutoreados"));
        }
    }

    private Activity buildActivity(long id, String name, String content) {
        return new Activity(id, name, content, IMAGE, TimestampUtil.getCurrentTime(), false);
    }

}
