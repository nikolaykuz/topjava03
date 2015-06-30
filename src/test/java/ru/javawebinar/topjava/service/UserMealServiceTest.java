package ru.javawebinar.topjava.service;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.BaseEntity;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.util.DbPopulator;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static ru.javawebinar.topjava.MealTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserMealServiceTest {
    private final static int USER_ID = UserTestData.USER.getId();
    private final static int ADMIN_ID = UserTestData.ADMIN.getId();

    @Autowired
    protected UserMealService service;

    @Autowired
    private DbPopulator dbPopulator;

    @Before
    public void setUp() throws Exception {
        dbPopulator.execute();
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(BREAKFAST.getId(), USER_ID);
        MATCHER.assertListEquals(asList(LUNCH, DINNER), service.getAll(USER_ID));
    }

    @Test(expected = NotFoundException.class)
    public void testDeleteFromWrongUser() throws Exception {
        service.delete(BREAKFAST.getId(), ADMIN_ID);
    }

    @Test(expected = NotFoundException.class)
    public void testDeleteWrongMeal() throws Exception {
        service.delete(USER_ID, USER_ID);
    }

    @Test
    public void testGetBetweenDateTimes() throws Exception {
        List<UserMeal> userMeals = service.getBetweenDateTimes(LUNCH_DATE_TIME.minusMinutes(1), LUNCH_DATE_TIME.plusMinutes(1), USER_ID);
        MATCHER.assertListEquals(singletonList(LUNCH), userMeals);
    }

    @Test
    public void testGetAll() throws Exception {
        MATCHER.assertListEquals(asList(BREAKFAST, LUNCH, DINNER), service.getAll(USER_ID));
    }

    @Test
    public void testDeleteAll() throws Exception {
        service.deleteAll(USER_ID);
        MATCHER.assertListEquals(emptyList(), service.getAll(USER_ID));
    }

    @Ignore
    @Test
    public void testDeleteAllWrongUserId() throws Exception {
        service.deleteAll(BaseEntity.START_SEQ - 1);
    }

    @Test
    public void testUpdate() throws Exception {
        UserMeal lunchCopy = new UserMeal(LUNCH);
        lunchCopy.setCalories(999);
        MATCHER.assertEquals(lunchCopy, service.update(lunchCopy, USER_ID));
        MATCHER.assertEquals(lunchCopy, service.get(LUNCH.getId(), USER_ID));
    }

    @Test
    public void testSave() throws Exception {
        UserMeal userMeal = new UserMeal(null, LocalDateTime.now(), "Test meal", 777);
        MATCHER.assertEquals(userMeal, service.save(userMeal, UserTestData.USER.getId()));
    }
}