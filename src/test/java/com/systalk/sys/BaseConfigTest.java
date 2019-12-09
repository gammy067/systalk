package com.systalk.sys;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.systalk.backend.config.ApplicationContextTest;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationContextTest.class })
public class BaseConfigTest {
	
	/**
	 * Before.
	 */
	@Before
	public void before() {

	}

    @After
    public void after() {
    }
    
    @Test
    public void test() {
    	
    }
}
